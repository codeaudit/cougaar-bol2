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


/*
 * Created on Jul 9, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.cougaar.tutorial.booksonline.shipper;


import org.cougaar.core.service.BlackboardService;
import org.cougaar.core.service.LoggingService;
import org.cougaar.planning.ldm.PlanningFactory;
import org.cougaar.planning.ldm.plan.Allocation;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.AspectType;
import org.cougaar.planning.ldm.plan.AspectValue;
import org.cougaar.planning.ldm.plan.MPTask;
import org.cougaar.planning.ldm.plan.PlanElement;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.tutorial.booksonline.assets.ShippingFleetAsset;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;

import java.util.Enumeration;
import java.util.TimerTask;


/**
 * Marks SHIP_ROUTE tasks as complete
 *
 * @author ttschampel, mabrams
 */
public class ShipperPerformJob extends TimerTask {
  private BlackboardService bbs;
  private PlanningFactory pf;
  private Allocation alloc;
  private LoggingService logging;

  /**
   * Creates a new PerformJob object.
   *
   * @param _bbs BlackboardService
   * @param _pf Planning Factory
   * @param _alloc Allocation
   * @param _logging LoggingService
   */
  public ShipperPerformJob(BlackboardService _bbs, PlanningFactory _pf,
    Allocation _alloc, LoggingService _logging) {
    this.bbs = _bbs;
    this.pf = _pf;
    this.alloc = _alloc;
    this.logging = _logging;
  }

  /**
   * TimerTask execution method
   */
  public void run() {
    if (logging.isDebugEnabled()) {
      logging.debug("Shipper Finished performing job");
    }

    bbs.openTransaction();

    ShippingFleetAsset sfa = (ShippingFleetAsset) alloc.getAsset();

    double startTime = alloc.getStartTime();
    double endTime = alloc.getEndTime();
    double numAvail = alloc.getEstimatedResult().getValue(AspectType.QUANTITY);
    if (logging.isDebugEnabled()) {
      logging.debug("^^$$shipperMark: num avail " + numAvail);
    }

    int[] aspect_types = {
      AspectType.START_TIME, AspectType.END_TIME, AspectType.QUANTITY,
      BolSocietyUtils.COMPLETED_ASPECT, BolSocietyUtils.FULL_LOAD_ASPECT
    };
    double[] results = {
      startTime, endTime, (double) numAvail, BolSocietyUtils.ISCOMPLETED, 0.0
    };

    AspectValue[] aspectValues = new AspectValue[5];
    aspectValues[0] = AspectValue.newAspectValue(aspect_types[0], results[0]);
    aspectValues[1] = AspectValue.newAspectValue(aspect_types[1], results[1]);
    aspectValues[2] = AspectValue.newAspectValue(aspect_types[2], results[2]);
    aspectValues[3] = AspectValue.newAspectValue(aspect_types[3], results[3]);
    aspectValues[4] = AspectValue.newAspectValue(aspect_types[4], results[4]);

    String region = sfa.getVehiclePG().getRegion();
    String type = sfa.getVehiclePG().getType();
    String id = sfa.getVehiclePG().getVid();
    ShipperUtils.resetAssetLoad(sfa, (int) 0.0, true);
    if (logging.isDebugEnabled()) {
      logging.debug("ShipperMarkAsDone: marking as done " + region + " " + type
        + " " + id);
    }

    //  setting AR as successful
    boolean success = true;
    AllocationResult newAR = pf.newAllocationResult(1.0, // rating
        success, // success,
        aspectValues);
    alloc.setEstimatedResult(newAR);
    ////		alloc.getTask().getPlanElement().setObservedResult(newAR);
    if (logging.isDebugEnabled()) {
      logging.debug("^^^^mark success   = "
        + alloc.getEstimatedResult().isSuccess());
    }

    MPTask mpTask = (MPTask) alloc.getTask();
    Enumeration e = mpTask.getComposition().getCombinedTask().getParentTasks();
    while (e.hasMoreElements()) {
      Task task = (Task) e.nextElement();
      PlanElement taskPE = (PlanElement) task.getPlanElement();
      taskPE.setEstimatedResult(newAR);
      taskPE.setObservedResult(newAR);
      Task shipTask = task.getWorkflow().getParentTask();
      if (shipTask.getPlanElement().getEstimatedResult() == null) {
        PlanElement pe = (PlanElement) shipTask.getPlanElement();
        pe.setEstimatedResult(newAR);
        bbs.publishChange(pe);

      }

      bbs.publishChange(taskPE);
    }

    bbs.publishChange(alloc);
    bbs.closeTransaction();
  }
}
