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


package org.cougaar.tutorial.booksonline.payment;


import org.cougaar.core.blackboard.IncrementalSubscription;
import org.cougaar.planning.ldm.plan.Allocation;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.AspectType;
import org.cougaar.planning.ldm.plan.AspectValue;
import org.cougaar.planning.ldm.plan.PlanElement;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.tutorial.booksonline.assets.PaymentAsset;
import org.cougaar.tutorial.booksonline.common.BOLComponentPlugin;
import org.cougaar.util.UnaryPredicate;

import java.util.Enumeration;


/**
 * Subscribes to alloations to payment assets and authorizes credit charges on
 * the payment assets.  Then publishes the observed results to the blackboard
 *
 * @author ttschampel, mabrams
 */
public class CreditVerificationPlugin extends BOLComponentPlugin {
    private static final String pluginName = "CreditVerificationPlugin";
    /** Subscription to allocations */
    private IncrementalSubscription allocationSubs = null;
    /** Predicate for allocations */
    private UnaryPredicate allocationPredicate = new UnaryPredicate() {
            public boolean execute(Object o) {
                return o instanceof Allocation;
            }
        };

    /**
     * Setup subscription to allocations
     * 
     */
    public void setupSubscriptions() {
        allocationSubs = (IncrementalSubscription) getBlackboardService()
                                                       .subscribe(allocationPredicate);

    }


    /**
     * Process subscriptions
     */
    public void execute() {
        if (logging.isDebugEnabled()) {
            logging.debug("Executing:" + pluginName);
        }

        processAllocations();

    }


    /**
     * Process new allocations
     */
    private void processAllocations() {
        Enumeration allocations = allocationSubs.getAddedList();
        while (allocations.hasMoreElements()) {
            Allocation allocation = (Allocation) allocations.nextElement();
            if (logging.isDebugEnabled()) {
                logging.debug("Received payment allocation");
            }

            Task task = allocation.getTask();
            PaymentAsset payment = (PaymentAsset) allocation.getAsset();
            float amount = payment.getPaymentPG().getAmtAuthorized();
            String ccNumber = payment.getItemIdentificationPG()
                                     .getItemIdentification();
            if (logging.isDebugEnabled()) {
                logging.debug("Authorizing payment amount of " + amount
                    + "  for ccnum:" + ccNumber);
            }


            //for now just authorize this amount (set task to completion by setting observed results);
            AspectValue[] values = new AspectValue[3];
            values[0] = AspectValue.newAspectValue(AspectType.END_TIME,
                    System.currentTimeMillis());
            values[1] = AspectValue.newAspectValue(AspectType.START_TIME,
                    System.currentTimeMillis());
            values[2] = AspectValue.newAspectValue(AspectType.COST, amount);
            AllocationResult result = getPlanningFactory().newAllocationResult(1.0,
                    true, values);

            PlanElement pe = task.getPlanElement();
            pe.setEstimatedResult(result);
            pe.setObservedResult(result);

            getBlackboardService().publishChange(pe);


            //            this.setObservedResults(task, types, values);
        }
    }
}
