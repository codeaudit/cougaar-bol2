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


import org.cougaar.core.blackboard.IncrementalSubscription;
import org.cougaar.core.component.ServiceBroker;
import org.cougaar.core.service.ThreadService;
import org.cougaar.planning.ldm.plan.Allocation;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.AspectType;
import org.cougaar.planning.ldm.plan.AspectValue;
import org.cougaar.planning.ldm.plan.PlanElementForAssessor;
import org.cougaar.tutorial.booksonline.assets.ShippingFleetAsset;
import org.cougaar.tutorial.booksonline.common.BOLComponentPlugin;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;
import org.cougaar.tutorial.booksonline.util.TutorialUtils;
import org.cougaar.util.UnaryPredicate;

import java.util.Enumeration;


/**
 * Monitors VEHICLEROUTER tasks and queues them for completion when the
 *
 * @author mabrams
 */
public class ShipperAssessorPlugin extends BOLComponentPlugin {
  private static UnaryPredicate allRouteTaskAllocationsPredicate = new UnaryPredicate() {
      public boolean execute(Object o) {
        return (TutorialUtils.isAllocationWithVerb(o, "VEHICLEROUTER"));
      }
    };

  private IncrementalSubscription allRouteTaskAllocationsSubscription;
  private long wakeAfterTime = 5000;
  /** Cougaar Thread Service */
  private ThreadService threadService = null;
  private String pluginName = "ShipperAssessorPlugin";

  /**
   * Sets up the <code>ThreadService</code>
   */
  public void load() {
    super.load();
    ServiceBroker sb = getBindingSite().getServiceBroker();
    threadService = (ThreadService) sb.getService(this, ThreadService.class,
        null);
  }


  /* (non-Javadoc)
   * @see org.cougaar.tutorial.booksonline.common.BOLComponentPlugin#setupSubscriptions()
   */
  protected void setupSubscriptions() {
    allRouteTaskAllocationsSubscription = (IncrementalSubscription) getBlackboardService()
                                                                      .subscribe(allRouteTaskAllocationsPredicate);

  }


  /* (non-Javadoc)
   * @see org.cougaar.tutorial.booksonline.common.BOLComponentPlugin#execute()
   */
  protected void execute() {
    Enumeration e = allRouteTaskAllocationsSubscription.getChangedList();
    while (e.hasMoreElements()) {
      if (logging.isDebugEnabled()) {
        logging.debug(pluginName + " execute");
      }

      Allocation allocation = (Allocation) e.nextElement();
      ShippingFleetAsset sfa = (ShippingFleetAsset) allocation.getAsset();
      int load = sfa.getVehiclePG().getLoad();
      boolean freeProperty = sfa.getVehiclePG().getFree();
      boolean success = false;
      
      // find the time period
      double currentTime = currentTimeMillis();
      double startTime = allocation.getEstimatedResult().getValue(AspectType.START_TIME);
      double endTime = allocation.getEstimatedResult().getValue(AspectType.END_TIME);
      double quant = allocation.getEstimatedResult().getValue(AspectType.QUANTITY);
      double completed = allocation.getEstimatedResult().getValue(BolSocietyUtils.COMPLETED_ASPECT);
      double full = allocation.getEstimatedResult().getValue(BolSocietyUtils.FULL_LOAD_ASPECT);
      double timePeriod = 0.0;


      if (completed == 1.0) {
        if (logging.isDebugEnabled()) {
          logging.debug(pluginName + " task completed, exiting");
        }

        return; //  we have triggered this one already
      }

      if (((full == 1.0) || (currentTime >= startTime))) {
        if (logging.isDebugEnabled()) {
          logging.debug(pluginName
            + " load capacity met or estimated start-no-later time was reached");
        }

        //  load capacity was met or estimated task start-no-later-than time was reached
        if (allocation.getEstimatedResult().isSuccess()) //  this load was delivered
         {
          //  we are ready to run another shipment for this asset
          //BolSocietyUtils.out.println("$$shipperAssessor: load " );
          double lowestStartTime = currentTimeMillis() + 10000000;
          Allocation lowestRoleSchedule = null;
          success = true; // change for successful return
          for (Enumeration numScheds = sfa.getRoleSchedule()
                                          .getRoleScheduleElements();
            numScheds.hasMoreElements();) {
            Allocation roleSchedule = (Allocation) numScheds.nextElement();
            double roleStart = roleSchedule.getStartTime();

            //BolSocietyUtils.out.println("$$shipperAssessor: roleschedule start time  " + roleStart);
            if ((roleStart > startTime) && (roleStart < lowestStartTime)) {
              lowestStartTime = roleStart;
              lowestRoleSchedule = roleSchedule;
              //BolSocietyUtils.out.println("$$shipperAssessor: got a role to run " );
            }
          }

          if (lowestRoleSchedule != null) {
            Allocation newAllocation = (Allocation) lowestRoleSchedule;
            double newstartTime = newAllocation.getEstimatedResult().getValue(AspectType.START_TIME);
            double newendTime = newAllocation.getEstimatedResult().getValue(AspectType.END_TIME);
            double newquant = newAllocation.getEstimatedResult().getValue(AspectType.QUANTITY);
            int[] newaspect_types = {
              AspectType.START_TIME, AspectType.END_TIME, AspectType.QUANTITY,
              BolSocietyUtils.COMPLETED_ASPECT, BolSocietyUtils.FULL_LOAD_ASPECT
            };
            double[] newresults = {
              newstartTime, newendTime, newquant, (double) 0.0, full
            };
            AllocationResult newAR = this.createAllocationResult(1.0, false,
                newaspect_types, newresults);

            ((PlanElementForAssessor) newAllocation).setEstimatedResult(newAR);
            getBlackboardService().publishChange(newAllocation);
          }


          //  set complete for original allocation
          //BolSocietyUtils.out.println("^^^^shipper assessor republish alloc"  );
          int[] aspect_types = {
            AspectType.START_TIME, AspectType.END_TIME, AspectType.QUANTITY,
            BolSocietyUtils.COMPLETED_ASPECT, BolSocietyUtils.FULL_LOAD_ASPECT
          };
          double[] results = {
            startTime, endTime, quant, BolSocietyUtils.ISCOMPLETED, full
          };
          AllocationResult oldAR = this.createAllocationResult(1.0, success,
              aspect_types, results);
          ((PlanElementForAssessor) allocation).setEstimatedResult(oldAR);
          getBlackboardService().publishChange(allocation);
          //BolSocietyUtils.out.println("$$shipperAssessor: publish alloc   " );
        } else if (freeProperty && (load == 0.0)) //  asset is free and load is 0
         {
          //BolSocietyUtils.out.println("^^$$shipperAssessor: load  " +  load + " free " + freeProperty);
          if (logging.isDebugEnabled()) {
            logging.debug("^^^^start time = " + startTime + " currentTime = "
              + currentTime + " endtime = " + endTime);
          }

          if (endTime > currentTime) {
            timePeriod = endTime - currentTime;
          } else {
            timePeriod = wakeAfterTime;
          }

          
          //PluginDelegate delegate = getDelegate();
          if (logging.isDebugEnabled()) {
            logging.debug("^^^^shipper assessor trigger set  = " + timePeriod);
          }

          //WakeAfterMonitor myMonitor = new  WakeAfterMonitor((long)timePeriod, allocArray, delegate);
          ShipperPerformJob performJob = new ShipperPerformJob(getBlackboardService(),
              getPlanningFactory(), allocation, logging);
          threadService.getThread(this, performJob).schedule((long)timePeriod);
         

          // getBlackboardService().publishAdd(trigger);
        } //  end if free and load = 0
        else {
          
          AspectValue[] values = new AspectValue[4];

          AllocationResult oldAR = getPlanningFactory().newAllocationResult(1.0,
              success, values);
          ((PlanElementForAssessor) allocation).setEstimatedResult(oldAR);
          getBlackboardService().publishChange(allocation);
        }
      }

      // end if full or beyond time
    }

    // end while
  }
}
