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


package org.cougaar.tutorial.booksonline.ordermanager;


import org.cougaar.core.blackboard.IncrementalSubscription;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.AllocationResultAggregator;
import org.cougaar.planning.ldm.plan.AspectType;
import org.cougaar.planning.ldm.plan.AspectValue;
import org.cougaar.planning.ldm.plan.Constraint;
import org.cougaar.planning.ldm.plan.Expansion;
import org.cougaar.planning.ldm.plan.NewConstraint;
import org.cougaar.planning.ldm.plan.NewPrepositionalPhrase;
import org.cougaar.planning.ldm.plan.NewTask;
import org.cougaar.planning.ldm.plan.NewWorkflow;
import org.cougaar.planning.ldm.plan.PlanElement;
import org.cougaar.planning.ldm.plan.Preference;
import org.cougaar.planning.ldm.plan.PrepositionalPhrase;
import org.cougaar.planning.ldm.plan.ScoringFunction;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.planning.ldm.plan.Verb;
import org.cougaar.tutorial.booksonline.common.BOLComponentPlugin;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;
import org.cougaar.tutorial.booksonline.util.TutorialUtils;
import org.cougaar.tutorial.booksonline.util.UserDetails;
import org.cougaar.util.UnaryPredicate;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Vector;


/**
 * This is the main driver fo rthe order manager cluster.  It subscribes to
 * order book tasks and then creates an expansion containing the subtasks to
 * complete the parent order books task.
 *
 * @author mabrams
 */
public class OrderManagerExpanderPlugin extends BOLComponentPlugin {
    private static final String pluginName = "OrderManagerExpanderPlugin";
    /** subscription to all order book tasks (NEW and CHANGED) */
    private IncrementalSubscription allOrderTasks = null;
    /**
     * Subscription for all 'BOOKORDER' Expansions, we want to get the changed
     * subscriptions so we can check status
     */
    private IncrementalSubscription allOrderExpansions;
    /** Predicate for Task containing ORDER_VERB verb */
    private UnaryPredicate allOrderTasksPredicate = new UnaryPredicate() {
            public boolean execute(Object o) {
                return TutorialUtils.isTaskWithVerb(o,
                    BolSocietyUtils.ORDER_VERB);
            }
        };

    /** Subscription to completed order tasks */
    private IncrementalSubscription completedSubs = null;
    /** Predicate for completed order tasks */
    private UnaryPredicate completedOrderTasks = new UnaryPredicate() {
            public boolean execute(Object o) {
                if (o instanceof PlanElement) {
                    PlanElement pe = (PlanElement) o;
                    if (pe != null) {
                        Task t = pe.getTask();
                        return t.getVerb().toString().equals(BolSocietyUtils.ORDER_VERB);

                    }
                }

                return false;
            }
        };

    /**
     * sets up subscriptions to all of the predicates
     */
    protected void setupSubscriptions() {
        completedSubs = (IncrementalSubscription) getBlackboardService()
                                                      .subscribe(completedOrderTasks);
        allOrderTasks = (IncrementalSubscription) getBlackboardService()
                                                      .subscribe(allOrderTasksPredicate);
    }


    /**
     * Load the plugin and setup logging
     */
    public void load() {
        super.load();
    }


    /**
     * Checks for new order books tasks, and expands any it finds.
     */
    protected void execute() {
        if (logging.isDebugEnabled()) {
            logging.debug("Executing " + pluginName);
        }


        // check for book orders
        Enumeration e = allOrderTasks.getAddedList();
        while (e.hasMoreElements()) {
            Task parentTask = (Task) e.nextElement();
            expandNewOrderTasks(parentTask);
        }

        //check for completed orders
        Enumeration completedOrders = completedSubs.elements();
        while (completedOrders.hasMoreElements()) {
            PlanElement pe = (PlanElement) completedOrders.nextElement();
            if ((pe != null) && (pe.getReportedResult() != null)
                && (pe.getReportedResult().isSuccess() == true)) {
                Task orderTask = pe.getTask();
                if (logging.isDebugEnabled()) {
                    logging.debug(
                        "****************************************************Received order complete notification...");
                }

                getBlackboardService().publishRemove(orderTask);
            }
        }
    }


    /**
     * <b>Description</b>: Check for a new book order task, expand it, publish
     * the first part, get the ball rolling.
     *
     * @param parentTask the parent taks of the expansion to create
     */
    private void expandNewOrderTasks(Task parentTask) {
        if (logging.isDebugEnabled()) {
            logging.debug("Added Expander Task");
        }


        // Create workflow
        // Create expansion and workflow to represent the expansion
        // of this task
        NewWorkflow new_wf = getPlanningFactory().newWorkflow();
        new_wf.setAllocationResultAggregator(AllocationResultAggregator.DEFAULT);
        new_wf.setParentTask(parentTask);


        // set up the way we need to score the success of these expansion tasks,
        //
        // create the clear payment task, make sure the requester has the credit
        float price = (float) parentTask.getPreferredValue(AspectType.COST);
        PrepositionalPhrase ccPP = parentTask.getPrepositionalPhrase(BolSocietyUtils.CCINFO_PREPOSITION);

        Task clearPayTask = createClearPaymentTask(price,
                (String) ccPP.getIndirectObject());
        new_wf.addTask(clearPayTask);
        ((NewTask) clearPayTask).setWorkflow(new_wf);
        ((NewTask) clearPayTask).setParentTask(parentTask);

        //publish the first task in the workflow to the blackboard
        getBlackboardService().publishAdd(clearPayTask);

        // create allocation of task to warehouse (warehouse allocates shipping task so pass shipping info as well)
        PrepositionalPhrase isbnPP = parentTask.getPrepositionalPhrase(BolSocietyUtils.ISBN_PREPOSITION);
        PrepositionalPhrase userPP = parentTask.getPrepositionalPhrase(BolSocietyUtils.USERDETAIL_PREPOSITION);
        PrepositionalPhrase methPP = parentTask.getPrepositionalPhrase(BolSocietyUtils.SHIPMETHOD_PREPOSITION);
        int numBooks = (int) parentTask.getPreferredValue(AspectType.QUANTITY);

        // add the warehous task
        Task wareTask = createWarehouseTask((String) isbnPP.getIndirectObject(),
                numBooks, (UserDetails) userPP.getIndirectObject(),
                (String) methPP.getIndirectObject(),
                (double) currentTimeMillis());
        new_wf.addTask(wareTask);
        ((NewTask) wareTask).setWorkflow(new_wf);
        ((NewTask) wareTask).setParentTask(parentTask);


        //		publishAdd (wareTask);   commented out to enforce constraints, this is publishAdd'ed after payment comes back completed
        // add the final "get the actual money" task
        Task finalPayAuthTask = createFinalPayAuthTask(price,
                (String) ccPP.getIndirectObject());
        new_wf.addTask(finalPayAuthTask);
        ((NewTask) finalPayAuthTask).setWorkflow(new_wf);
        ((NewTask) finalPayAuthTask).setParentTask(parentTask);


        // we need to track these sub tasks and their allocation results for when
        // we aggregate allocation results later
        // Task[] bothTasks = { clearPayTask, wareTask, finalPayAuthTask };
        // ExpanderTaskTracker expTrack = new ExpanderTaskTracker(bothTasks);
        // parentTrackers.put(parentTask, expTrack);
        // getBlackboardService().publishChange(parentTrackers);
        // add Constraints so they all run in order
        Vector constraints = new Vector();

        // End(clearPayTask) must be before Start(wareTask)
        NewConstraint clearPayFirst = (getPlanningFactory()).newConstraint();
        clearPayFirst.setConstrainingTask(clearPayTask);
        clearPayFirst.setConstrainingAspect(AspectType.END_TIME);
        clearPayFirst.setConstrainedTask(wareTask);
        clearPayFirst.setConstrainedAspect(AspectType.START_TIME);
        clearPayFirst.setConstraintOrder(Constraint.BEFORE);
        constraints.addElement(clearPayFirst);

        NewConstraint actualPayLast = getPlanningFactory().newConstraint();
        actualPayLast.setConstrainingTask(wareTask);
        actualPayLast.setConstrainingAspect(AspectType.END_TIME);
        actualPayLast.setConstrainedTask(finalPayAuthTask);
        actualPayLast.setConstrainedAspect(AspectType.START_TIME);
        actualPayLast.setConstraintOrder(Constraint.BEFORE);
        constraints.addElement(actualPayLast);

        new_wf.setConstraints(constraints.elements());

        // add plan element as expansion interface
        AllocationResult estAR = null;
        Expansion new_exp = getPlanningFactory().createExpansion(parentTask
                .getPlan(), parentTask, new_wf, estAR);

        getBlackboardService().publishAdd(new_wf);
        getBlackboardService().publishAdd(new_exp);

    }


    /**
     * <b>Description</b>: Create the task that will verify the user has enough
     * money for this transaction. <br>
     * <b>Notes</b>:<br>
     * - This task will go to the Payment cluster. <br>
     *
     * @param price Amount of money to verify
     * @param ccInfo Credit card number to verify against
     *
     * @return org.cougaar.planning.ldm.plan.Task The thing to publish into the
     *         log plan that will activate the Payment cluster
     */
    private Task createClearPaymentTask(float price, String ccInfo) {
        NewTask task = getPlanningFactory().newTask();

        task.setDirectObject(null);
        task.setVerb(Verb.getVerb(BolSocietyUtils.PAYMENT_VERB));

        Vector preps = new Vector();

        //Credit Card info prep. phrase with credit card # as indirect object
        NewPrepositionalPhrase npp = getPlanningFactory()
                                         .newPrepositionalPhrase();
        npp.setPreposition(BolSocietyUtils.CCINFO_PREPOSITION);


        // KLUDGE so we don't have to type in a credit card number in the PSP interface
        // if it's null, empty or ends with two blanks it is not a valid account number
        if ((ccInfo == null) || (ccInfo.length() == 0) || ccInfo.endsWith("  ")) {
            ccInfo = new String("1234568");
        }

        npp.setIndirectObject(ccInfo);
        preps.add(npp);

        NewPrepositionalPhrase workflowPP = getPlanningFactory()
                                                .newPrepositionalPhrase();
        workflowPP.setPreposition(BolSocietyUtils.GENERIC_WORKFLOW_PREPOSITION);
        preps.add(workflowPP);

        task.setPrepositionalPhrases(preps.elements());

        task.setPlan(getPlanningFactory().getRealityPlan());

        GregorianCalendar calToday = new GregorianCalendar();
        calToday.setTime(new Date(currentTimeMillis()));

        calToday.add(GregorianCalendar.DATE, 30);

        long blockTillDay = calToday.getTime().getTime();

        int yyyymmdd = calToday.get(Calendar.YEAR) * 10000;
        yyyymmdd += ((calToday.get(Calendar.MONTH) + 1) * 100);
        yyyymmdd += calToday.get(Calendar.DAY_OF_MONTH);

        if (logging.isDebugEnabled()) {
            logging.debug("Requesting payment task with money blocked until: "
                + yyyymmdd);
        }

        // add the desired "hold money until" day as a preference, holding it more than the time requested is ok
        Vector newPreferences = new Vector();

        // lose 5% for every day we miss, if we can't hold the money for at least 10 days it's no good. The book will not have shipped by then. We assume.
        ScoringFunction blockScorefcn = ScoringFunction.createNearOrAbove(AspectValue
                .newAspectValue(AspectType.END_TIME, blockTillDay), .05);

        // this counts 50% towards the total completion ability, need to find the book and ship it too, all are equally important
        Preference blockPref = getPlanningFactory().newPreference(AspectType.END_TIME,
                blockScorefcn, 0.5);

        newPreferences.addElement(blockPref);


        // lose 95% for every dollar we can't put a hold on, if he hits his credit limit in
        // the middle of this transaction, even if only by a dollar, it's "no sale"
        ScoringFunction priceScorefcn = ScoringFunction.createNearOrBelow(AspectValue
                .newAspectValue(AspectType.COST, (double) price), .95);

        // this counts 50% towards the total completion ability, need to find the book and ship it too, all are equally important
        Preference pricePref = getPlanningFactory().newPreference(AspectType.COST,
                priceScorefcn, 0.5);

        newPreferences.addElement(pricePref);


        // every part of the book order task has an indication to show final completion (i.e. when the performance
        // of the task transistions to the past)
        ScoringFunction complScorefcn = ScoringFunction.createPreferredAtValue(AspectValue
                .newAspectValue(BolSocietyUtils.COMPLETED_ASPECT,
                    BolSocietyUtils.ISCOMPLETED), 1.0);


        // this counts 0 against the preference score, since it indicates final completion of the task
        // it isn't used to show how well planned the task is
        Preference complPref = getPlanningFactory().newPreference(BolSocietyUtils.COMPLETED_ASPECT,
                complScorefcn, 0.0);

        newPreferences.addElement(complPref);

        task.setPreferences(newPreferences.elements());

        return task;
    }


    /**
     * <b>Description</b>: Create the task that will get books from the
     * warehouse. <br>
     * <b>Notes</b>:<br>
     * - Static function, can be called from anywhere <br>
     *
     * @param isbn Cryptic string containing doublet of ISBN and quantity,
     *        separated by :, of books being requested, doublets are separated
     *        by ;
     * @param quantity Total number of books in the order
     * @param ud UserDetails object containing delivery address for order
     * @param shipMeth Preferred shipping method as per user
     * @param millis ClusterObjectFactory, COUGAAR internal object to create
     *        parts of task
     *
     * @return org.cougaar.planning.ldm.plan.Task The thing to publish into the
     *         log plan so the warehouse will get busy
     */
    private Task createWarehouseTask(String isbn, int quantity, UserDetails ud,
        String shipMeth, double millis) {
        NewTask task = getPlanningFactory().newTask();
        task.setDirectObject(null);
        task.setVerb(Verb.getVerb(BolSocietyUtils.WAREHOUSE_VERB));

        // add the preposition data
        Vector preps = new Vector();
        NewPrepositionalPhrase npp = getPlanningFactory()
                                         .newPrepositionalPhrase();
        npp.setPreposition(BolSocietyUtils.ISBN_PREPOSITION);
        npp.setIndirectObject(isbn);
        preps.add(npp);

        NewPrepositionalPhrase userNpp = getPlanningFactory()
                                             .newPrepositionalPhrase();
        userNpp.setPreposition(BolSocietyUtils.USERDETAIL_PREPOSITION);
        userNpp.setIndirectObject(ud);
        preps.add(userNpp);

        NewPrepositionalPhrase methNpp = getPlanningFactory()
                                             .newPrepositionalPhrase();
        methNpp.setPreposition(BolSocietyUtils.SHIPMETHOD_PREPOSITION);
        methNpp.setIndirectObject(shipMeth);
        preps.add(methNpp);

        NewPrepositionalPhrase workflowPP = getPlanningFactory()
                                                .newPrepositionalPhrase();
        workflowPP.setPreposition(BolSocietyUtils.GENERIC_WORKFLOW_PREPOSITION);
        preps.add(workflowPP);

        task.setPrepositionalPhrases(preps.elements());
        task.setPlan(getPlanningFactory().getRealityPlan());

        Vector newPreferences = new Vector();

        // add the requested quantity as a preference, only the exact amount is preferred
        ScoringFunction scorefcn = ScoringFunction.createPreferredAtValue(AspectValue
                .newAspectValue(AspectType.QUANTITY, quantity), .95);
        Preference pref = getPlanningFactory().newPreference(AspectType.QUANTITY,
                scorefcn, 0.25);

        newPreferences.addElement(pref);

        // show final completion (i.e. the performance of this task is now in the past)
        ScoringFunction complScorefcn = ScoringFunction.createPreferredAtValue(AspectValue
                .newAspectValue(BolSocietyUtils.COMPLETED_ASPECT,
                    BolSocietyUtils.ISCOMPLETED), 1.0);


        // this counts 0 against the preference score, since it indicates final completion of the task
        // it isn't used to show how well planned the task is
        Preference complPref = getPlanningFactory().newPreference(BolSocietyUtils.COMPLETED_ASPECT,
                complScorefcn, 0.0);

        newPreferences.addElement(complPref);

        // add start and end time so constraints will work
        ScoringFunction startScorefcn = ScoringFunction.createPreferredAtValue(AspectValue
                .newAspectValue(AspectType.START_TIME, (double) millis), .001);
        Preference startPref = getPlanningFactory().newPreference(AspectType.START_TIME,
                startScorefcn, 0.25);

        newPreferences.addElement(startPref);

        // add start and end time so constraints will work
        ScoringFunction endScorefcn = ScoringFunction.createPreferredAtValue(AspectValue
                .newAspectValue(AspectType.END_TIME, (double) millis), .001);
        Preference endPref = getPlanningFactory().newPreference(AspectType.END_TIME,
                endScorefcn, 0.25);

        newPreferences.addElement(endPref);

        task.setPreferences(newPreferences.elements());

        return task;

    }


    /**
     * <b>Description</b>: Create the task that will transfer the money from
     * the user credit account into our vacation fund (you have your business
     * model, we have ours). <br>
     * <b>Notes</b>:<br>
     * - This should be going to the Payment cluster <br>
     *
     * @param price Amount of money to transfer
     * @param ccInfo Credit card number to transfer from
     *
     * @return org.cougaar.planning.ldm.plan.Task The thing to publish into the
     *         log plan that will activate the Payment cluster
     */
    private Task createFinalPayAuthTask(float price, String ccInfo) {
        NewTask task = getPlanningFactory().newTask();

        task.setDirectObject(null);
        task.setVerb(Verb.getVerb(BolSocietyUtils.FINAL_PAYMENT_VERB));

        Vector preps = new Vector();

        NewPrepositionalPhrase npp = getPlanningFactory()
                                         .newPrepositionalPhrase();
        npp.setPreposition(BolSocietyUtils.CCINFO_PREPOSITION);


        // KLUDGE so we don't have to type in a credit card number in the PSP interface
        // if it's null, empty or ends with two blanks it is not a valid account number
        if ((ccInfo == null) || (ccInfo.length() == 0) || ccInfo.endsWith("  ")) {
            ccInfo = new String("1234568");
        }

        npp.setIndirectObject(ccInfo);
        preps.add(npp);

        NewPrepositionalPhrase workflowPP = getPlanningFactory()
                                                .newPrepositionalPhrase();
        workflowPP.setPreposition(BolSocietyUtils.GENERIC_WORKFLOW_PREPOSITION);
        preps.add(workflowPP);

        task.setPrepositionalPhrases(preps.elements());

        task.setPlan(getPlanningFactory().getRealityPlan());

        if (logging.isDebugEnabled()) {
            logging.debug("Requesting actual payment of funds");
        }

        // add the desired "hold money until" day as a preference, holding it more than the time requested is ok
        Vector newPreferences = new Vector();


        // lose 95% for every dollar we can't get payment on, if he hits his credit limit in
        // the middle of this transaction, even if only by a dollar, it's "no sale"
        ScoringFunction priceScorefcn = ScoringFunction.createNearOrBelow(AspectValue
                .newAspectValue(AspectType.COST, (double) price), .95);

        // this counts 50% towards the total completion ability, need to find the book and ship it too, all are equally important
        Preference pricePref = getPlanningFactory().newPreference(AspectType.COST,
                priceScorefcn, 0.5);

        newPreferences.addElement(pricePref);


        // every part of the book order task has an indication to show final completion (i.e. when the performance
        // of the task transistions to the past)
        ScoringFunction complScorefcn = ScoringFunction.createPreferredAtValue(AspectValue
                .newAspectValue(BolSocietyUtils.COMPLETED_ASPECT,
                    BolSocietyUtils.ISCOMPLETED), 1.0);


        // this counts 0 against the preference score, since it indicates final completion of the task
        // it isn't used to show how well planned the task is
        Preference complPref = getPlanningFactory().newPreference(BolSocietyUtils.COMPLETED_ASPECT,
                complScorefcn, 0.0);

        newPreferences.addElement(complPref);

        task.setPreferences(newPreferences.elements());

        return task;

    }
}
