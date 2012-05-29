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
import org.cougaar.planning.ldm.asset.NewItemIdentificationPG;
import org.cougaar.planning.ldm.plan.Allocation;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.AspectType;
import org.cougaar.planning.ldm.plan.PrepositionalPhrase;
import org.cougaar.planning.ldm.plan.Role;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.tutorial.booksonline.assets.NewPaymentPG;
import org.cougaar.tutorial.booksonline.assets.PaymentAsset;
import org.cougaar.tutorial.booksonline.common.BOLComponentPlugin;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;
import org.cougaar.util.UnaryPredicate;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;


/**
 * Subscribes to ClearPayment and FinalPayment Tasks and then Allocates the
 * task to a PaymentAsset.
 *
 * @author ttschampel
 */
public class PaymentAllocatorPlugin extends BOLComponentPlugin {
    /** Plugin name */
    private static final String pluginName = "PaymentAllocatorPlugin";
    /** Subscription to CLEAR PAYMENT TASK */
    private IncrementalSubscription clearPaymentSubs = null;
    /** Predicate for CLEAR PAYMENT TASK */
    private UnaryPredicate clearPaymentPredicate = new UnaryPredicate() {
            public boolean execute(Object o) {
                if (o instanceof Task) {
                    Task task = (Task) o;
                    if ((task.getVerb() != null)
                        && task.getVerb().toString().equals(BolSocietyUtils.PAYMENT_VERB)) {
                        return true;
                    }
                }

                return false;
            }
        };

    /** Subscription to FINALPAYMENT TASK */
    private IncrementalSubscription finalPaymentSubs = null;
    /** Predicate for FINALPAYMENT TASK */
    private UnaryPredicate finalPaymentPredicate = new UnaryPredicate() {
            public boolean execute(Object o) {
                if (o instanceof Task) {
                    Task task = (Task) o;
                    if ((task.getVerb() != null)
                        && task.getVerb().toString().equals(BolSocietyUtils.FINAL_PAYMENT_VERB)) {
                        return true;
                    }
                }

                return false;
            }
        };

    /**
     * Predicate to find payment asset
     *
     * @param ccNumber Credit Card Number
     *
     * @return UnaryPredicate
     */
    private UnaryPredicate paymentAssetPredicate(final String ccNumber) {
        return new UnaryPredicate() {
                public boolean execute(Object o) {
                    if (o instanceof PaymentAsset) {
                        PaymentAsset pa = (PaymentAsset) o;
                        return (pa.getItemIdentificationPG() != null)
                        && (pa.getItemIdentificationPG().getItemIdentification() != null)
                        && pa.getItemIdentificationPG().getItemIdentification()
                             .equals(ccNumber);
                    }

                    return false;
                }
            };
    }


    /**
     * Setup Subscriptions
     */
    public void setupSubscriptions() {
        finalPaymentSubs = (IncrementalSubscription) getBlackboardService()
                                                         .subscribe(finalPaymentPredicate);
        clearPaymentSubs = (IncrementalSubscription) getBlackboardService()
                                                         .subscribe(clearPaymentPredicate);

    }


    /**
     * Process Subscritions
     */
    public void execute() {
        if (logging.isDebugEnabled()) {
            logging.debug("Executing " + pluginName);
        }

        processClearPaymentTasks();
        processFinalPaymentTasks();

    }


    /**
     * Creates a payment Asset for the credit card to be charged and allocates
     * the final payment task to this asset.
     */
    private void processFinalPaymentTasks() {
        Enumeration enm = finalPaymentSubs.getAddedList();
        while (enm.hasMoreElements()) {
            Task finalPaymentTask = (Task) enm.nextElement();
            if (logging.isDebugEnabled()) {
                logging.debug("Received final payment task");
            }

            PrepositionalPhrase ccPhrase = finalPaymentTask
                .getPrepositionalPhrase(BolSocietyUtils.CCINFO_PREPOSITION);
            long creditCardNum = Long.parseLong((String) ccPhrase
                    .getIndirectObject());
            Collection creditCards = getBlackboardService().query(paymentAssetPredicate(creditCardNum
                        + ""));
            Iterator iterator = creditCards.iterator();
            if (iterator.hasNext()) {
                if (logging.isDebugEnabled()) {
                    logging.debug("Charging " + creditCardNum);
                }

                //just complete task for now.
                this.setCompleted(finalPaymentTask);
                getBlackboardService().publishChange(finalPaymentTask);
            } else {
                if (logging.isErrorEnabled()) {
                    logging.error("Can't find credit card asset! "
                        + creditCardNum);
                }
            }
        }
    }


    /**
     * Creates a payment Asset for the credit card to be cleared and allocates
     * the clear payment task to this asset.
     */
    private void processClearPaymentTasks() {
        Enumeration enm = clearPaymentSubs.getAddedList();
        while (enm.hasMoreElements()) {
            Task clearPaymentTask = (Task) enm.nextElement();
            try {
                if (logging.isDebugEnabled()) {
                    logging.debug("Processing clear payment task");
                }

                //Get payment information
                PrepositionalPhrase ccPhrase = clearPaymentTask
                    .getPrepositionalPhrase(BolSocietyUtils.CCINFO_PREPOSITION);
                long creditCardNum = Long.parseLong((String) ccPhrase
                        .getIndirectObject());
                if (logging.isDebugEnabled()) {
                    logging.debug("Received credit card number:"
                        + creditCardNum);
                }

                //Get cost for payment asset
                double cost = clearPaymentTask.getPreferredValue(AspectType.COST);
                if (logging.isDebugEnabled()) {
                    logging.debug("Cost:" + cost);
                }

                //Create payment asset
                PaymentAsset paymentAsset = (PaymentAsset) getPlanningFactory()
                                                               .createInstance(Constants.PAYMENT_PROTOTYPE);
                NewPaymentPG nppg = (NewPaymentPG) getPlanningFactory()
                                                       .createPropertyGroup("PaymentPG");
                nppg.setAmtAuthorized((float) cost);
                NewItemIdentificationPG itemIdentificationPG = (NewItemIdentificationPG) getPlanningFactory()
                                                                                             .createPropertyGroup("ItemIdentificationPG");
                itemIdentificationPG.setItemIdentification("" + creditCardNum);
                paymentAsset.setItemIdentificationPG(itemIdentificationPG);
                paymentAsset.setPaymentPG(nppg);
                getBlackboardService().publishAdd(paymentAsset);
                AllocationResult estAR = null;
                Allocation allocation = getPlanningFactory().createAllocation(clearPaymentTask
                        .getPlan(), clearPaymentTask, paymentAsset, estAR,
                        Role.ASSIGNED);
                getBlackboardService().publishAdd(allocation);
            } catch (NumberFormatException nfe) {
                if (logging.isErrorEnabled()) {
                    logging.error("Error parsing credit card or cost", nfe);
                }
            }
        }
    }
}
