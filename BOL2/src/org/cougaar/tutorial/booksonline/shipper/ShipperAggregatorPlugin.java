/*
 * <copyright>
 *  Copyright 1997-2003 Cougaar Software, Inc.
 *  under sponsorship of the Defense Advanced Research Projects
 *  Agency (DARPA).
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the Cougaar Open Source License as published by
 *  DARPA on the Cougaar Open Source Website (www.cougaar.org).
 *
 *  THE COUGAAR SOFTWARE AND ANY DERIVATIVE SUPPLIED BY LICENSOR IS
 *  PROVIDED "AS IS" WITHOUT WARRANTIES OF ANY KIND, WHETHER EXPRESS OR
 *  IMPLIED, INCLUDING (BUT NOT LIMITED TO) ALL IMPLIED WARRANTIES OF
 *  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE, AND WITHOUT
 *  ANY WARRANTIES AS TO NON-INFRINGEMENT.  IN NO EVENT SHALL COPYRIGHT
 *  HOLDER BE LIABLE FOR ANY DIRECT, SPECIAL, INDIRECT OR CONSEQUENTIAL
 *  DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE OF DATA OR PROFITS,
 *  TORTIOUS CONDUCT, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 *  PERFORMANCE OF THE COUGAAR SOFTWARE.
 *
 * </copyright>
 *
 * CHANGE RECORD
 * -
 */


package org.cougaar.tutorial.booksonline.shipper;


import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import org.cougaar.core.blackboard.IncrementalSubscription;
import org.cougaar.glm.packer.ProportionalDistributor;
import org.cougaar.planning.ldm.plan.Aggregation;
import org.cougaar.planning.ldm.plan.Allocation;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.AspectType;
import org.cougaar.planning.ldm.plan.AspectValue;
import org.cougaar.planning.ldm.plan.NewAlert;
import org.cougaar.planning.ldm.plan.NewComposition;
import org.cougaar.planning.ldm.plan.NewMPTask;
import org.cougaar.planning.ldm.plan.PlanElementForAssessor;
import org.cougaar.planning.ldm.plan.Role;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.planning.ldm.plan.Verb;
import org.cougaar.tutorial.booksonline.assets.GISPG;
import org.cougaar.tutorial.booksonline.assets.NewGISPG;
import org.cougaar.tutorial.booksonline.assets.NewVehiclePG;
import org.cougaar.tutorial.booksonline.assets.ShippingFleetAsset;
import org.cougaar.tutorial.booksonline.common.BOLComponentPlugin;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;
import org.cougaar.tutorial.booksonline.util.TutorialUtils;
import org.cougaar.tutorial.booksonline.util.UserDetails;
import org.cougaar.util.UnaryPredicate;


/**
 * The ShipperAggregatorPlugin subscribes to SHIP_ROUTE_VERBS.  It assigns each
 * package to a truck, adding packages to that asset until the asset is full
 * or the leave_by time has been reached
 *
 * @author mabrams
 */
public class ShipperAggregatorPlugin extends BOLComponentPlugin {
  private static UnaryPredicate allRouteTasksPredicate = new UnaryPredicate() {
      public boolean execute(Object o) {
        if (o instanceof Task) {
          Task t = (Task) o;
          if (t.getVerb().equals(Verb.getVerb(BolSocietyUtils.SHIP_ROUTE_VERB))) {
            return true;
          }
        }

        return false;
      }
    };

  private static UnaryPredicate allRouteTaskAllocationsPredicate = new UnaryPredicate() {
      public boolean execute(Object o) {
        return TutorialUtils.isAllocationWithVerb(o, "VEHICLEROUTER");
      }
    };

  private UnaryPredicate ShippingFleetAssetPredicate = new UnaryPredicate() {
      public boolean execute(Object o) {
        if (o instanceof ShippingFleetAsset) {
          return true;
        }

        return false;
      }
    };

  private String pluginName = "ShipperAggregatorPlugin";
  private boolean incomingLoad = false;
  private double drivingTime = 1000.0;
  private int wakeAfterTime = 5000;
  private Hashtable assetsForRegion = null;
  private IncrementalSubscription allRouteTasksSubscription;

  /**
   * Load plugin
   */
  public void load() {
    super.load();

  }


  /**
   * Subscription to ROUTE_TASK_VERB(s)
   */
  protected void setupSubscriptions() {
    allRouteTasksSubscription = (IncrementalSubscription) getBlackboardService()
                                                            .subscribe(allRouteTasksPredicate);

  }


  /**
   * Checks for new our changes ROUTE_TASK_VERBS and assigns them to a truck
   */
  protected void execute() {
    Enumeration addedEnum = allRouteTasksSubscription.getAddedList();
    incomingLoad = false;
    while (addedEnum.hasMoreElements()) {
      Task task = (Task) addedEnum.nextElement();
      if (logging.isDebugEnabled()) {
        logging.debug(pluginName + " got task " + task);
      }

      incomingLoad = true;
      int quantity = (int) task.getPreferredValue(AspectType.QUANTITY);
      findFleetAsset(task, quantity);
      wakeAfter(wakeAfterTime);
    }

    Enumeration changedEnum = allRouteTasksSubscription.getChangedList();
    while (changedEnum.hasMoreElements()) {
      Task task = (Task) changedEnum.nextElement();
      if (logging.isDebugEnabled()) {
        logging.debug(pluginName + " changed task " + task);
      }

      incomingLoad = true;
      int quantity = (int) task.getPreferredValue(AspectType.QUANTITY);
      findFleetAsset(task, quantity);
      wakeAfter(wakeAfterTime);
    }

    boolean timeRemaining = false;
    if (!incomingLoad) {
      // we got here because of the timer
      Collection col = getBlackboardService().query(allRouteTaskAllocationsPredicate);
      Iterator allocIter = col.iterator();
      double time = currentTimeMillis();
      while (allocIter.hasNext()) {
        Allocation alloc = (Allocation) allocIter.next();

        if (!alloc.getEstimatedResult().isSuccess()) {
          if (alloc.getStartTime() < time) {
            //task is not marked as successful but it's time to start it up
            getBlackboardService().publishChange(alloc);
          } else {
            timeRemaining = true;
          }
        }
      }
    }

    if (timeRemaining) {
      //BolSocietyUtils.out.println("AGG: wakeafter Shipper Aggregator");
      wakeAfter(wakeAfterTime);
    }
  }


  /**
   * Find the fleet asset based on the geographic information
   *
   * @param task
   * @param quantity
   */
  private void findFleetAsset(Task task, int quantity) {
    //  task is ship_route_task
    UserDetails ud = (UserDetails) task.getPrepositionalPhrase(BolSocietyUtils.USERDETAIL_PREPOSITION)
                                       .getIndirectObject();
    String city = ud.getCity();
    GISBehavior gb = (GISBehavior) task.getDirectObject();
    if (gb == null) {
      GISPG gis_pg = (GISPG) getPlanningFactory().createPropertyGroup("GISPG");
      ((NewGISPG) gis_pg).setSourceCity(new String("Dayton, OH"));
      gb = new GISBehavior(gis_pg);
    }

    int route = gb.getRouteNumber(city);

    String method = (String) task.getPrepositionalPhrase(BolSocietyUtils.SHIPMETHOD_PREPOSITION)
                                 .getIndirectObject();
    ShippingFleetAsset myAsset = null;

    if (method.equals("Next Day Air") || method.equals("2nd Day Air")) {
      // get plane asset for this region
      myAsset = getAssetForRegion("plane", route, quantity, task);
      //BolSocietyUtils.out.println("AGG: plane = " + myAsset);
    } else if (method.equals("Ground")) {
      // get truck asset for this region
      myAsset = getAssetForRegion("Truck", route, quantity, task);
      //BolSocietyUtils.out.println("AGG: truck = " + myAsset);
    }
  }


  /**
   * Get the asset for a particular region
   *
   * @param type
   * @param route
   * @param quantity
   * @param task
   *
   * @return
   */
  private ShippingFleetAsset getAssetForRegion(String type, int route,
    int quantity, Task task) {
    ShippingFleetAsset sfa = null;
    String region = null;

    switch (route) {
      case 110:
        region = "East";
        break;
      case 120:
        region = "West";
        break;
      case 130:
        region = "North";
        break;
      case 140:
        region = "South";
        break;
      case 150:
        region = "North";
        break;
      default:
        region = "East";
    }

    String assetFinder = type + region;


    //  Get the allocation for the loading asset
    //  if it's null, return the first one
    Allocation currentAlloc = findLoadingTaskAllocation(assetFinder, task);
    if (currentAlloc != null) {
      updateTheCurrentLoad(currentAlloc, quantity, task);
      return (ShippingFleetAsset) currentAlloc.getAsset();
    }

    return null;
  }


  /**
   * Updates the current load of an asset
   *
   * @param allocation
   * @param quantity
   * @param task
   */
  private void updateTheCurrentLoad(Allocation allocation, int quantity,
    Task task) {
    //  get the current quantity etc. from the allocation
    double loadQuantity = allocation.getEstimatedResult().getValue(AspectType.QUANTITY);
    double taskStart = allocation.getEstimatedResult().getValue(AspectType.START_TIME);
    double taskEnd = allocation.getEstimatedResult().getValue(AspectType.END_TIME);
    ShippingFleetAsset sfa = (ShippingFleetAsset) allocation.getAsset();

    //  add this task to the combined task parent set
    NewMPTask mp = (NewMPTask) allocation.getTask();
    Enumeration parents = mp.getComposition().getCombinedTask().getParentTasks();
    Vector vParents = new Vector(20);
    while (parents.hasMoreElements()) {
      Task parentTask = (Task) parents.nextElement();
      vParents.add(parentTask);
    }

    vParents.add(task); //  add the current one
    Enumeration newParents = vParents.elements();
    mp.setParentTasks(newParents);

    

    //  make the allocation
    int number = (int) loadQuantity + quantity;
    int[] aspect_types = {
      AspectType.START_TIME, AspectType.END_TIME, AspectType.QUANTITY,
      BolSocietyUtils.COMPLETED_ASPECT, BolSocietyUtils.FULL_LOAD_ASPECT
    };
    double[] results = { taskStart, taskEnd, number, (double) 0.0, (double) 0.0 };
    AllocationResult newAR = this.createAllocationResult(1.0, false,
        aspect_types, results);
    ((PlanElementForAssessor) allocation).setEstimatedResult(newAR);
    getBlackboardService().publishChange(allocation);

  }


  /**
   * Find an asset to load
   *
   * @param key
   * @param task
   *
   * @return Allocation!
   */
  private Allocation findLoadingTaskAllocation(String key, Task task) {
    // from the hashtable, keyed by region+type get a vector of assets
    // find the asset whose role schedule start time is greatest( the last one allocated)
    // return the allocation for this asset role schedule as the most likely candidate
    // for loading the order onto. If the load won't fit, begin a new load after ending the
    // current load (if there is one)
    if (assetsForRegion == null) {
      assetsForRegion = new Hashtable(3);
      Collection col = getBlackboardService().query(ShippingFleetAssetPredicate);
      Iterator assetIter = col.iterator();

      //  build a multiple parent task and NewComposition for each asset when this plugin runs the first time
      while (assetIter.hasNext()) {
        ShippingFleetAsset fleet = (ShippingFleetAsset) assetIter.next();
        String fleetVehicleType = (String) fleet.getVehiclePG().getType();
        String region = (String) fleet.getVehiclePG().getRegion();
        String assetFinder = fleetVehicleType + region;

        if (!assetsForRegion.containsKey(assetFinder)) {
          Vector newAssets = new Vector(1);
          newAssets.add(fleet);
          assetsForRegion.put(assetFinder, newAssets);
        } else // new instance, but same region and type as another
         {
          //  add the asset, which is for this region and type to the composite hashtable
          Vector newAssets = (Vector) assetsForRegion.get(assetFinder);
          newAssets.add(fleet);
        }
      }
    }

    Vector assets = (Vector) assetsForRegion.get(key);
    Allocation highestRoleSchedule = null;
    double startTime = currentTimeMillis();
    ShippingFleetAsset sfa = null;
    ShippingFleetAsset highestsfa = null;

    //  For each asset, choose the one which has the latest start time
    try {
      if (assets != null) {
        for (int i = 0; i < assets.size(); i++) {
          sfa = (ShippingFleetAsset) assets.elementAt(i);
          for (Enumeration numScheds = sfa.getRoleSchedule()
                                          .getRoleScheduleElements();
            numScheds.hasMoreElements();) {
            Allocation roleSchedule = (Allocation) numScheds.nextElement();
            double roleStart = roleSchedule.getStartTime();
            if (logging.isDebugEnabled()) {
              logging.debug("$$shipperAggregator: asset = "
                + sfa.getVehiclePG().getVid() + " role start = " + roleStart
                + " start " + startTime);
            }

            //  If the role schedule start time is greater than the last role schedule start time
            if ((roleStart > startTime)
              && (roleSchedule.getEstimatedResult().getValue(BolSocietyUtils.COMPLETED_ASPECT) != 1.0)
              && (roleSchedule.getEstimatedResult().getValue(BolSocietyUtils.FULL_LOAD_ASPECT) != 1.0)) {
              startTime = roleStart;
              highestRoleSchedule = roleSchedule;
              highestsfa = sfa;
              if (logging.isDebugEnabled()) {
                logging.debug("$$shipperAggregator: choose asset "
                  + sfa.getVehiclePG().getVid());
              }
            }
          }
        }
      }

      if (highestRoleSchedule == null) //  no role schedule yet
       {
        sfa = findLeastUsedAsset(key);
        int quantity = (int) task.getPreferredValue(AspectType.QUANTITY);
        if (!orderLargerThanCapacity(sfa, quantity)) { // if the load won't ever fit, just return a null allocation without updating
          highestRoleSchedule = beginNewLoad(task, sfa);
        }
      } else //  Check that the load will fit, if not start a new load
       {
        int quantity = (int) task.getPreferredValue(AspectType.QUANTITY);
        int loadQuantity = (int) highestRoleSchedule.getEstimatedResult()
                                                    .getValue(AspectType.QUANTITY);
        int capacity = sfa.getVehiclePG().getCapacity();
        if (logging.isDebugEnabled()) {
          logging.debug("^^^^ findasset: capacity = " + capacity + " load = "
            + loadQuantity + " task quantity = " + quantity);
        }

        if (!orderLargerThanCapacity(highestsfa, quantity)) // if the load won't ever fit, just return without updating
         {
          if ((loadQuantity + quantity) > capacity) {
            setLoadFull(highestRoleSchedule.getTask());
            sfa = findLeastUsedAsset(key);
            highestRoleSchedule = beginNewLoad(task, sfa);
          }
        } else {
          highestRoleSchedule = null;
        }
      }

      if (logging.isDebugEnabled()) {
        logging.debug("^^^^ Out of find");
      }
    } catch (NullPointerException e) {
    }

    return highestRoleSchedule;
  }


 


  /**
   * <b>Description</b>: findLeastUsedAsset - Find the asset that matches and
   * continues the loading process     <br><b>Notes</b>:<br>
   *
   * @param key key
   *
   * @return ShippingFleetAsset                  -    <br>
   */
  private ShippingFleetAsset findLeastUsedAsset(String key) {
    // from the hashtable, keyed by region+type get a vector of assets
    // find the asset whose role schedule start time is greatest( the last one allocated)
    // return the allocation for this asset role schedule as the most likely candidate
    // for loading the order onto.
    Vector assets = (Vector) assetsForRegion.get(key);
    int lastValue = 10000;
    ShippingFleetAsset returnedAsset = null;
    ShippingFleetAsset sfa = null;
    try {
      //  For each asset, choose the one which has the latest start time
      for (int i = 0; i < assets.size(); i++) {
        sfa = (ShippingFleetAsset) assets.elementAt(i);
        int containers = sfa.getVehiclePG().getContainers();

        if (containers < lastValue) {
          returnedAsset = sfa;
          lastValue = sfa.getVehiclePG().getContainers();
          if (logging.isDebugEnabled()) {
            logging.debug("^^^^findLeast: select on container less = true " + i
              + " count = " + lastValue + " id = "
              + sfa.getVehiclePG().getVid());
          }
        }
      }
    } catch (NullPointerException e) {
    }

    return returnedAsset; // least used or if all zero, the first one
  }


  /**
   * <b>Description</b>: orderLargerThanCapacity - Find the asset that matches
   * and continues the loading process <br><b>Notes</b>:<br>
   *
   * @param sfa sfa
   * @param quantity quantity
   *
   * @return boolean                  - <br>
   */
  private boolean orderLargerThanCapacity(ShippingFleetAsset sfa, int quantity) {
    try {
      if (quantity > sfa.getVehiclePG().getCapacity()) {
        String msg = "ShipperAggregator: order too large for container!!";
        if (logging.isDebugEnabled()) {
          logging.debug(msg);
        }

        NewAlert alert = getPlanningFactory().newAlert();
        alert.setAlertText(msg);
        getBlackboardService().publishAdd(alert);
        return true;
      }
    } catch (NullPointerException e) {
    }

    return false;
  }


  /**
   * <b>Description</b>: beginNewLoad - start a vehicle with a clean slate
   * <br><b>Notes</b>:<br>
   *
   * @param task CompositeObject
   * @param myAsset ShippingFleetAsset
   *
   * @return void                  -    <br>
   */
  private Allocation beginNewLoad(Task task, ShippingFleetAsset myAsset) {
    NewComposition comp = makeNewTask(true);

    //  add 1 to the assets container property
    ShipperUtils.adjustContainerCount(myAsset, 1);
    if (logging.isDebugEnabled()) {
      logging.debug("AGG: begin load");
    }

    Vector routeAllocations = getAllocations(myAsset);
    double time = 0.0;
    if (routeAllocations != null) {
      time = ShipperUtils.getBestStartTime(routeAllocations,
          currentTimeMillis() + wakeAfterTime);
    } else {
      time = currentTimeMillis() + wakeAfterTime;
    }


    //  the asset has been declared "not free" already get a new asset, even it's not free
    //  and make it current asset will be set free when task end time is reached.
    double taskStart = time;
    double taskEnd = time + drivingTime;
    if (logging.isDebugEnabled()) {
      logging.debug("ShipperAgg: taskStart = " + taskStart);
      logging.debug("ShipperAgg: taskEnd = " + taskEnd);
    }

    double number = 0.0;
    int[] aspect_types = {
      AspectType.START_TIME, AspectType.END_TIME, AspectType.QUANTITY,
      BolSocietyUtils.COMPLETED_ASPECT, BolSocietyUtils.FULL_LOAD_ASPECT
    };
    double[] results = {
      taskStart, taskEnd, (double) number, (double) 0.0, (double) 0.0
    };


    AspectValue[] aspectValues = new AspectValue[5];
    aspectValues[0] = AspectValue.newAspectValue(aspect_types[0], results[0]);
    aspectValues[1] = AspectValue.newAspectValue(aspect_types[1], results[1]);
    aspectValues[2] = AspectValue.newAspectValue(aspect_types[2], results[2]);
    aspectValues[3] = AspectValue.newAspectValue(aspect_types[3], results[3]);
    aspectValues[4] = AspectValue.newAspectValue(aspect_types[4], results[4]);
    AllocationResult ar = getPlanningFactory().newAllocationResult(1.0, false,
        aspectValues);
    Allocation allocation = getPlanningFactory().createAllocation(comp.getCombinedTask()
                                                                      .getPlan(),
        comp.getCombinedTask(), myAsset, ar, Role.ASSIGNED);
    getBlackboardService().publishAdd(allocation);

    return allocation;
  }


  /**
   * <b>Description</b>: makeNewTask - make the composite task
   * <br><b>Notes</b>:<br>
   *
   * @param publish boolean
   *
   * @return NewComposition                  - <br>
   */
  public NewComposition makeNewTask(boolean publish) {
    NewComposition comp = getPlanningFactory().newComposition();
    NewMPTask routeTask = getPlanningFactory().newMPTask();
    routeTask.setPlan(getPlanningFactory().getRealityPlan());
    routeTask.setVerb(Verb.getVerb("VEHICLEROUTER"));
    comp.setCombinedTask(routeTask);
    ProportionalDistributor pDist = new ProportionalDistributor();
    comp.setDistributor(pDist);
    routeTask.setComposition(comp);

    if (publish) {
      getBlackboardService().publishAdd(routeTask);
    }

    // BolSocietyUtils.out.println("Shipper AGG: create vehicle route task " + routeTask);
    return comp;
  }


  /**
   * <b>Description</b>: getAllocations - get all tasks for this route
   * <br><b>Notes</b>:<br>
   *
   * @param sfa ShippingFleetAsset the vehicle asset
   *
   * @return Vector tasks                  - <br>
   */
  private Vector getAllocations(ShippingFleetAsset sfa) {
    String routeID = sfa.getVehiclePG().getVid();
    Collection col = getBlackboardService().query(allRouteTaskAllocationsPredicate);
    Iterator allocIter = col.iterator();
    int mumAllocations = col.size();
    if (mumAllocations < 1) {
      return null;
    }

    // See if this route asset is allocated here
    Vector result = new Vector(3);
    while (allocIter.hasNext()) {
      Allocation alloc = (Allocation) allocIter.next();
      ShippingFleetAsset asset = (ShippingFleetAsset) alloc.getAsset();

      String sfaID = asset.getVehiclePG().getVid();

      //BolSocietyUtils.out.println("^^^^Agg.getAlloc asset = " + sfaID);
      if (sfaID.equals(routeID)) {
        result.add(alloc); //  all tasks that have this route allocated
      }
    }

    return result;
  }


  /**
   * <b>Description</b>: setLoadFull - set this load as filled
   * <br><b>Notes</b>:<br>
   *
   * @param task sfa
   */
  private void setLoadFull(Task task) {
    //  task is VEHICLEROUTE task
    Allocation allocation = (Allocation) task.getPlanElement();
    double loadQuantity = allocation.getEstimatedResult().getValue(AspectType.QUANTITY);
    double taskStart = allocation.getEstimatedResult().getValue(AspectType.START_TIME);
    double taskEnd = allocation.getEstimatedResult().getValue(AspectType.END_TIME);
    ShippingFleetAsset sfa = (ShippingFleetAsset) allocation.getAsset();

    //  make the allocation
    int number = (int) loadQuantity;
    int[] aspect_types = {
      AspectType.START_TIME, AspectType.END_TIME, AspectType.QUANTITY,
      BolSocietyUtils.COMPLETED_ASPECT, BolSocietyUtils.FULL_LOAD_ASPECT
    };
    double[] results = { taskStart, taskEnd, number, (double) 0.0, (double) 1.0 };

    AspectValue[] aspectValues = new AspectValue[5];
    aspectValues[0] = AspectValue.newAspectValue(aspect_types[0], results[0]);
    aspectValues[1] = AspectValue.newAspectValue(aspect_types[1], results[1]);
    aspectValues[2] = AspectValue.newAspectValue(aspect_types[2], results[2]);
    aspectValues[3] = AspectValue.newAspectValue(aspect_types[3], results[3]);
    aspectValues[4] = AspectValue.newAspectValue(aspect_types[4], results[4]);
    AllocationResult newAR = getPlanningFactory().newAllocationResult(1.0,
        false, aspectValues);
    ((PlanElementForAssessor) allocation).setEstimatedResult(newAR);
    getBlackboardService().publishChange(allocation);
  }
}
