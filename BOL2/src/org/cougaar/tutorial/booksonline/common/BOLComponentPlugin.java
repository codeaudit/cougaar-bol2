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


import org.cougaar.core.agent.service.alarm.Alarm;
import org.cougaar.core.plugin.ComponentPlugin;
import org.cougaar.core.service.DomainService;
import org.cougaar.core.service.LoggingService;
import org.cougaar.core.service.UIDService;
import org.cougaar.planning.ldm.PlanningFactory;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.AspectType;
import org.cougaar.planning.ldm.plan.AspectValue;
import org.cougaar.planning.ldm.plan.Disposition;
import org.cougaar.planning.ldm.plan.PlanElement;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.tutorial.booksonline.domain.BOLFactory;


/**
 * A Component Plugin with  some utility methods added for convenience
 *
 * @author ttschampel, mabrams
 */
public abstract class BOLComponentPlugin extends ComponentPlugin {
    protected LoggingService logging;
    protected DomainService domainService;
    protected UIDService uidService;

    /**
     * Set UID Service
     *
     * @param service UIDService
     */
    public void setUIDService(UIDService service) {
        this.uidService = service;
    }


    /**
     * Set LoggingService
     *
     * @param service LoggingService
     */
    public void setLoggingService(LoggingService service) {
        this.logging = service;

    }


    /**
     * Set Domain Service
     *
     * @param service DomainService
     */
    public void setDomainService(DomainService service) {
        this.domainService = service;
    }


    /**
     * Get DomainService
     *
     * @return DomainService
     */
    public DomainService getDomainService() {
        return this.domainService;
    }


    /**
     * Retrieves the BOL Factory from the DomainService
     *
     * @return BOLFactory
     */
    protected BOLFactory getFactory() {
        return (BOLFactory) domainService.getFactory("BOL");
    }


    /**
     * Retrieves the Planning Factory from the DomainService
     *
     * @return PlanningFactory
     */
    protected PlanningFactory getPlanningFactory() {
        return (PlanningFactory) domainService.getFactory("planning");
    }


    /**
     * Abstract Setup Subscriptions method
     */
    protected abstract void setupSubscriptions();


    /**
     * Abstract execute method
     */
    protected abstract void execute();


    /**
     * Sets a task to completed.  For plugins that use this method, complete is
     * assumed to mean a confidence value of 1.0 and isSuccess should return
     * true.
     *
     * @param task the <code>Task</code> to set to complete
     *
     * @see setCompleted(Task task, boolean success)
     */
    protected void setCompleted(Task task) {
        setCompleted(task, true);
    }


    /**
     * Convenience method to specify period of time to wait before stimulating
     * plugin (based on COUGAAR scenario time). Note that this facility is not
     * appropriate to use for  load-balancing purposes, as scenario time is
     * discontinuous and may even stop.
     *
     * @param delayTime (Scenario) milliseconds to wait before waking.
     *
     * @return Alarm
     */
    public Alarm wakeAfter(long delayTime) {
        long absTime = getAlarmService().currentTimeMillis() + delayTime;
        PluginAlarm pa = new PluginAlarm(absTime);
        getAlarmService().addAlarm(pa);
        return pa;
    }


    /**
     * Create an allocation result
     *
     * @param rating
     * @param success
     * @param aspectTypes
     * @param aspectValues
     *
     * @return
     */
    protected AllocationResult createAllocationResult(double rating,
        boolean success, int[] aspectTypes, double[] aspectValues) {
        AllocationResult rs = null;
        AspectValue[] values = new AspectValue[aspectTypes.length];
        for (int i = 0; i < aspectTypes.length; i++) {
            values[i] = AspectValue.newAspectValue(aspectTypes[i],
                    aspectValues[i]);
        }

        rs = getPlanningFactory().newAllocationResult(rating, success, values);
        return rs;

    }


    /**
     * Sets a task to completed.  For plugins that use this method, complete is
     * assumed to mean a confidence value of 1.0 and isSuccess should return
     * true.
     *
     * @param task the <code>Task</code> to set to complete
     * @param success <code>boolean</code> indicating if task was successfull
     *        or not
     */
    protected void setCompleted(Task task, boolean success) {
        if (logging.isDebugEnabled()) {
            logging.debug("Setting :" + task + " to complete");
        }

        AspectValue[] values = new AspectValue[2];
        values[0] = AspectValue.newAspectValue(AspectType.END_TIME,
                System.currentTimeMillis());
        values[1] = AspectValue.newAspectValue(AspectType.START_TIME,
                System.currentTimeMillis());

        AllocationResult result = getPlanningFactory().newAllocationResult(1.0,
                success, values);
        if (task.getPlanElement() == null) {
            Disposition disp = getPlanningFactory().createDisposition(task
                    .getPlan(), task, result);
            disp.setEstimatedResult(result);
            disp.setObservedResult(result);

            getBlackboardService().publishAdd(disp);
        } else {
            PlanElement pe = task.getPlanElement();
            pe.setEstimatedResult(result);
            pe.setObservedResult(result);

            getBlackboardService().publishChange(pe);
        }
    }

    /**
     * Implementation of an Alarm
     */
    public class PluginAlarm implements Alarm {
        private long expiresAt;
        private boolean expired = false;

        public PluginAlarm(long expirationTime) {
            expiresAt = expirationTime;
        }

        public long getExpirationTime() {
            return expiresAt;
        }


        public synchronized void expire() {
            if (!expired) {
                expired = true;
                getBlackboardService().signalClientActivity();
            }
        }


        public boolean hasExpired() {
            return expired;
        }


        public synchronized boolean cancel() {
            boolean was = expired;
            expired = true;
            return was;
        }
    }
}
