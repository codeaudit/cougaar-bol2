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


package org.cougaar.tutorial.booksonline.common;


import org.cougaar.core.service.BlackboardService;
import org.cougaar.core.service.LoggingService;
import org.cougaar.planning.ldm.PlanningFactory;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.AspectValue;
import org.cougaar.planning.ldm.plan.Disposition;
import org.cougaar.planning.ldm.plan.PlanElement;
import org.cougaar.planning.ldm.plan.Task;

import java.util.TimerTask;


/**
 * A timer task that just models performing a task by setting a task to
 * completion and publishing the change to the Blackboard.
 *
 * @author ttschampel, mabrams
 */
public class PerformJob extends TimerTask {
    private BlackboardService bbs;
    private Task task;
    private LoggingService logging;
    private PlanningFactory planningFactory;

    /**
     * Creates a new PerformJob object.
     *
     * @param _bbs BlackboardService
     * @param _task Task to perform
     * @param _logging LoggingService
     * @param _pf PlanningFactory
     */
    public PerformJob(BlackboardService _bbs, Task _task,
        LoggingService _logging, PlanningFactory _pf) {
        this.bbs = _bbs;
        this.task = _task;
        this.logging = _logging;
        this.planningFactory = _pf;
    }

    /**
     * TimerTask run method.  Just complete the task we were assigned to
     * perform.
     */
    public void run() {
        if (logging.isDebugEnabled()) {
            logging.debug("Finished performing job");
        }

        bbs.openTransaction();
        AllocationResult estAR = null;
        if ((task.getPlanElement() != null)
            && (task.getPlanElement().getEstimatedResult() != null)) {
            estAR = task.getPlanElement().getEstimatedResult();
            task.getPlanElement().setObservedResult(estAR);
            if (logging.isDebugEnabled()) {
                logging.debug("Publishing change to plan Element");
            }

            bbs.publishChange(task.getPlanElement());
        } else if (task.getPlanElement() != null) {
            PlanElement pe = task.getPlanElement();
            estAR = planningFactory.newAllocationResult(1.0, true,
                    new AspectValue[0]);
            pe.setEstimatedResult(estAR);
            pe.setObservedResult(estAR);
            if (logging.isDebugEnabled()) {
                logging.debug("Publishing change to plan Element");
            }

            bbs.publishChange(pe);


        } else if (task.getPlanElement() == null) {
            estAR = planningFactory.newAllocationResult(1.0, true,
                    new AspectValue[0]);
            Disposition disp = planningFactory.createDisposition(task.getPlan(),
                    task, estAR);
            disp.setObservedResult(estAR);
            disp.setEstimatedResult(estAR);
            if (logging.isDebugEnabled()) {
                logging.debug("Publishing new disposition");
            }

            bbs.publishAdd(disp);
        }

        bbs.closeTransaction();
    }
}
