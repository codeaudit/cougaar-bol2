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


import org.cougaar.planning.ldm.PlanningFactory;
import org.cougaar.planning.ldm.plan.Allocation;
import org.cougaar.tutorial.booksonline.assets.NewShipperPG;
import org.cougaar.tutorial.booksonline.assets.NewVehiclePG;
import org.cougaar.tutorial.booksonline.assets.ShippingFleetAsset;
import org.cougaar.tutorial.booksonline.assets.ShippingReqAsset;

import java.util.Vector;


/**
 * Utility Methods to fill the Shipping Asset's PGs
 *
 * @author ttschampel
 * @version $Revision: 1.1 $
 */
public class ShipperUtils {
  protected static String SHIPREQ_PROTOTYPE = "ShippinReqObject";
  protected static String PACKER_PROTOTYPE = "PackerObject";

  /**
   * Fill the Shipper PG
   *
   * @param payAsset Payment Asset
   * @param dest Destination
   * @param carrier Carrier
   * @param method Shipping Method
   * @param theLDMF PlanningFactory
   */
  protected static void fillShipperPG(ShippingReqAsset payAsset, String dest,
    String carrier, String method, PlanningFactory theLDMF) {
    NewShipperPG shipp_pg = (NewShipperPG) theLDMF.createPropertyGroup(
        "ShipperPG");
    shipp_pg.setDestinationAddress(dest);
    shipp_pg.setShipperName(carrier);
    shipp_pg.setMethod(method);
    payAsset.setShipperPG(shipp_pg);

  }


  /**
   * <b>Description</b>: adjustContainerCount clear the load for this asset
   * <br><b>Notes</b>:<br>
   *
   * @param asset Shipping Fleet Asset
   * @param increment increment
   */
  public static void adjustContainerCount(ShippingFleetAsset asset,
    int increment) {
    NewVehiclePG v_pg = (NewVehiclePG) asset.getVehiclePG();
    int numContainers = v_pg.getContainers();
    v_pg.setContainers(numContainers + increment);
    //BolSocietyUtils.out.println("^^^^adjusting container count " +  numContainers + " - " +increment);
  }


  /**
   * <b>Description</b>: getBestStartTime - finds the asset which is free or
   * else the last on the list <br><b>Notes</b>:<br>
   *
   * @param allocations Vector
   *
   * @return double                  - <br>
   */
  protected static double getBestStartTime(Vector allocations) {
    double bestStartTime = 0.0;
    for (int i = 0; i < allocations.size(); i++) {
      Allocation allocation = (Allocation) allocations.elementAt(i);
      double time = allocation.getEndTime();
      if (time > bestStartTime) {
        bestStartTime = time;
      }
    }

    return bestStartTime;
  }


  /**
   * <b>Description</b>: getBestStartTime - find the optimum time to start the
   * packing     <br><b>Notes</b>:<br>
   *
   * @param allocations Vector           -
   * @param mytime double
   *
   * @return double     <br>
   */
  protected static double getBestStartTime(Vector allocations, double mytime) {
    double bestStartTime = mytime; //  current time
    for (int i = 0; i < (allocations.size() - 1); i++) {
      if (allocations != null) {
        Allocation allocation = (Allocation) allocations.elementAt(i);
        long time = allocation.getEndTime();
        if (time >= bestStartTime) {
          bestStartTime = time;
        }
      }
    }

    //BolSocietyUtils.out.println("^^^^return beststart - "  + bestStartTime);
    return bestStartTime;
  }


  /**
   * Rest the asset load on shipping fleet asset 
   *
   * @param sfa Shipping Fleet Asset
   * @param load Load
   * @param free free
   */
  public static void resetAssetLoad(ShippingFleetAsset sfa, int load,
    boolean free) {
    NewVehiclePG v_pg = (NewVehiclePG) sfa.getVehiclePG();
    int capacity = sfa.getVehiclePG().getCapacity();
    String region = sfa.getVehiclePG().getRegion();
    String type = sfa.getVehiclePG().getType();
    String id = sfa.getVehiclePG().getVid();
    int containers = sfa.getVehiclePG().getContainers();
    int increment = 0;
    if (free) {
      increment = -1; //  if load is free, decrement the container count
    }

    changeLoadProperty(v_pg, region, capacity, load, type, id, free, sfa,
      increment, containers);
  }


  /**
   * <b>Description</b>: changeLoadProperty - edit the property group
   * <br><b>Notes</b>:<br>
   *
   * @param v_pg NewVehiclePG
   * @param regionName String
   * @param capacity int
   * @param currentLoad int
   * @param type String
   * @param id vehcile property group id
   * @param free boolean
   * @param sfa ShippingFleetAsset
   * @param increment int
   * @param containers vehicle containers
   */
  public static void changeLoadProperty(NewVehiclePG v_pg, String regionName,
    int capacity, int currentLoad, String type, String id, boolean free,
    ShippingFleetAsset sfa, int increment, int containers) {
    v_pg.setContainers(containers + increment);
    v_pg.setCapacity(capacity);
    v_pg.setRegion(regionName);
    v_pg.setType(type);
    v_pg.setLoad(currentLoad);
    v_pg.setFree(free);
    v_pg.setVid(id);
    String assetFinder = type + regionName;
  }
}
