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


package org.cougaar.tutorial.booksonline.publisher;


import com.cougaarsoftware.common.service.DatabaseService;
import com.cougaarsoftware.common.service.DatabaseServiceProvider;

import org.cougaar.core.blackboard.IncrementalSubscription;
import org.cougaar.core.component.ServiceBroker;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.AllocationResultAggregator;
import org.cougaar.planning.ldm.plan.AspectType;
import org.cougaar.planning.ldm.plan.AspectValue;
import org.cougaar.planning.ldm.plan.ClusterObjectFactory;
import org.cougaar.planning.ldm.plan.Constraint;
import org.cougaar.planning.ldm.plan.Disposition;
import org.cougaar.planning.ldm.plan.Expansion;
import org.cougaar.planning.ldm.plan.MPTask;
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
import org.cougaar.tutorial.booksonline.assets.GISPG;
import org.cougaar.tutorial.booksonline.assets.NewGISPG;
import org.cougaar.tutorial.booksonline.common.BOLComponentPlugin;
import org.cougaar.tutorial.booksonline.shipper.GISBehavior;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;
import org.cougaar.tutorial.booksonline.util.BookUtil;
import org.cougaar.tutorial.booksonline.util.UserDetails;
import org.cougaar.tutorial.booksonline.web.model.BookModel;
import org.cougaar.util.UnaryPredicate;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;


/**
 * Subscribes to the completion of printRun Tasks and the addition of
 * PublishOrderTasks.  Expands the PublishOrderTasks into the
 * PackBooksAtWarehouse and ShipBooks Workflow.  With the completion of the
 * printRun task, the inventory is updated and the expansion is built in order
 * to try to process the task again.
 *
 * @author ttschampel
 * @version $Revision: 1.1 $
 */
public class PublisherExpanderPlugin extends BOLComponentPlugin {
    private static final String pluginName = "PublisherExpanderPlugin";
    /** Predicate for Publisher Order Tasks */
    private static UnaryPredicate allPublisherOrderTasksPredicate = new UnaryPredicate() {
            public boolean execute(Object o) {
                if (o instanceof Task) {
                    Task t = (Task) o;
                    return t.getVerb().equals(Verb.getVerb(
                            PublisherConstants.ORDER_VERB));
                }

                return false;
            }
        };

    /** DatabaseService */
    private DatabaseService dbService = null;
    /** Subscription to all publisher order tasks */
    private IncrementalSubscription allPublisherOrderTasksSubscription;
    /** Subscription to complete print runs */
    private IncrementalSubscription completePrintRunSubs = null;
    /** Predicate for completed print runs */
    private UnaryPredicate completedPrintPredicate = new UnaryPredicate() {
            public boolean execute(Object o) {
                if (o instanceof PlanElement) {
                    PlanElement pe = (PlanElement) o;
                    Task task = pe.getTask();
                    if (task.getVerb().toString().equals(PublisherConstants.PRINTRUN_VERB)) {
                        return (pe.getReportedResult() != null)
                        && pe.getReportedResult().isSuccess();
                    }
                }

                return false;
            }
        };

    /** Predicate for failed publisherpack tasks */
    private UnaryPredicate failedPackTasksPredicate = new UnaryPredicate() {
            public boolean execute(Object o) {
                if (o instanceof Task) {
                    Task t = (Task) o;
                    if (t.getVerb().equals(BolSocietyUtils.PUBLISHERPACK_VERB)) {
                        PlanElement pe = t.getPlanElement();
                        if (pe != null) {
                            if (pe instanceof Disposition) {
                                Disposition disp = (Disposition) pe;
                                return (disp.isSuccess() == false);
                            }
                        }
                    }
                }

                return false;
            }
        };

    /**
     * Load the plugin and setup logging
     */
    public void load() {
        super.load();
        ServiceBroker sb = getBindingSite().getServiceBroker();

        DatabaseServiceProvider dbServiceProvider = new DatabaseServiceProvider(getBindingSite()
                                                                                    .getServiceBroker());
        getBindingSite().getServiceBroker().addService(DatabaseService.class,
            dbServiceProvider);
        dbService = (DatabaseService) this.getServiceBroker().getService(this,
                DatabaseService.class, null);
    }


    /**
     * Setup Subscriptions
     */
    protected void setupSubscriptions() {
        allPublisherOrderTasksSubscription = (IncrementalSubscription) getBlackboardService()
                                                                           .subscribe(allPublisherOrderTasksPredicate);
        completePrintRunSubs = (IncrementalSubscription) getBlackboardService()
                                                             .subscribe(completedPrintPredicate);

    }


    /**
     * Process Subscriptions
     */
    protected void execute() {
        if (logging.isDebugEnabled()) {
            logging.debug(pluginName + " executing");
        }

        checkForNewOrders();
        checkForCompletedPrintTasks();
    }


    /**
     * Check for complete print runs, update inventory levels, and try to
     * complete pending orders.
     */
    private void checkForCompletedPrintTasks() {
        Enumeration enumeration = completePrintRunSubs.getChangedList();
        boolean newBooks = false;
        while (enumeration.hasMoreElements()) {
            newBooks = true;
            PlanElement pe = (PlanElement) enumeration.nextElement();
            MPTask printRunTask = (MPTask) pe.getTask();
            Enumeration printJobTasks = printRunTask.getComposition()
                                                    .getCombinedTask()
                                                    .getParentTasks();
            while (printJobTasks.hasMoreElements()) {
                Task printerTask = (Task) printJobTasks.nextElement();
                getBlackboardService().publishRemove(printerTask);
                //get book isbn and quantities
                String orderString = (String) printerTask.getPrepositionalPhrase(BolSocietyUtils.ISBN_PREPOSITION)
                                                         .getIndirectObject();
                StringTokenizer tokenizer = new StringTokenizer(orderString, ";");
                int numberForPrint = 0;
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    int numberAvailable = 0;
                    int colpos = token.indexOf(":");
                    String bookISBN = token.substring(0, colpos);
                    numberForPrint = Integer.parseInt(token.substring(colpos
                                + 1, token.length()));
                    //					decrement inventory and add book to asset group
                    try {
                        //									check inventory level for this book
                        Map parameters = new HashMap();
                        parameters.put(BolSocietyUtils.Database.BOOK_ISBN_PARAMETER,
                            bookISBN);

                        ArrayList results = (ArrayList) dbService.executeQuery(BolSocietyUtils.Database.PUBLISHER_FIND_BOOK_BY_ISBN_QUERY,
                                parameters);

                        if (results.size() > 0) {
                            Object[] objects = (Object[]) results.get(0);
                            BookModel book = BookUtil.getBookModelFromDatabase(objects);

                            //increase inventory
                            parameters = new HashMap();
                            parameters.put(BolSocietyUtils.Database.BOOK_ISBN_PARAMETER,
                                book.getIsbn());
                            parameters.put(BolSocietyUtils.Database.INVENTORY_LEVEL_PARAMETER,
                                "" + (book.getShelf() + numberForPrint));
                            dbService.executeStatement(BolSocietyUtils.Database.PUBLISHER_UPDATE_INVENTORY_QUERY,
                                parameters);
                        }
                    } catch (SQLException sqlex) {
                        if (logging.isErrorEnabled()) {
                            logging.error("Error updating inventory", sqlex);
                        }
                    }
                }
            }

            getBlackboardService().publishRemove(printRunTask);
        }

        if (newBooks) {
            //publish change all failed tasks, so we can try again.
            Collection failedList = getBlackboardService().query(failedPackTasksPredicate);
            Iterator iterator = failedList.iterator();
            while (iterator.hasNext()) {
                //remove expansion
                if (logging.isDebugEnabled()) {
                    logging.debug("Trying order again...");
                }

                Task oldTask = (Task) iterator.next();
                Task parentTask = oldTask.getWorkflow().getParentTask();
                PlanElement exp = parentTask.getPlanElement();
                getBlackboardService().publishRemove(exp);
                getBlackboardService().publishRemove(oldTask);
                buildExpansion(parentTask);
            }
        }
    }


    /**
     * Checks for new PublishOrder Tasks.
     */
    private void checkForNewOrders() {
        Enumeration orderTasksEnum = allPublisherOrderTasksSubscription
            .getAddedList();
        while (orderTasksEnum.hasMoreElements()) {
            Task parentTask = (Task) orderTasksEnum.nextElement();
            buildExpansion(parentTask);


        }
    }


    /**
     * Build the workflow for completing a publish order task
     *
     * @param parentTask The PublishOrder Task
     */
    private void buildExpansion(Task parentTask) {
        if (logging.isDebugEnabled()) {
            logging.debug(pluginName + " Got Publisher Order Task to Expanded");
        }


        // Create workflow
        // Create expansion and workflow to represent the expansion
        // of this task
        NewWorkflow new_wf = getPlanningFactory().newWorkflow();
        new_wf.setAllocationResultAggregator(AllocationResultAggregator.DEFAULT);
        new_wf.setParentTask(parentTask);

        PrepositionalPhrase isbnPP = parentTask.getPrepositionalPhrase(BolSocietyUtils.ISBN_PREPOSITION);
        int numBooks = (int) parentTask.getPreferredValue(AspectType.QUANTITY);
        if (logging.isDebugEnabled()) {
            logging.debug("PublisherExpander: numbooks = " + numBooks);
        }

        Task packTask = createPackTask((String) isbnPP.getIndirectObject(),
                numBooks, getPlanningFactory());
        new_wf.addTask(packTask);
        ((NewTask) packTask).setWorkflow(new_wf);
        ((NewTask) packTask).setParentTask(new_wf.getParentTask());

        PrepositionalPhrase methodPP = parentTask.getPrepositionalPhrase(BolSocietyUtils.SHIPMETHOD_PREPOSITION);
        PrepositionalPhrase userPP = parentTask.getPrepositionalPhrase(BolSocietyUtils.USERDETAIL_PREPOSITION);
        Task shipTask = createShippingTask((UserDetails) userPP
                .getIndirectObject(), (String) methodPP.getIndirectObject(),
                getPlanningFactory(), numBooks);
        new_wf.addTask(shipTask);
        ((NewTask) shipTask).setWorkflow(new_wf);
        ((NewTask) shipTask).setParentTask(parentTask);

        // add Constraints so they all run in order
        Vector constraints = new Vector();

        NewConstraint haveBook = getPlanningFactory().newConstraint();
        haveBook.setConstrainingTask(packTask);
        haveBook.setConstrainingAspect(AspectType.END_TIME);
        haveBook.setConstrainedTask(shipTask);
        haveBook.setConstrainedAspect(AspectType.START_TIME);
        haveBook.setConstraintOrder(Constraint.BEFORE);

        constraints.addElement(haveBook);

        new_wf.setConstraints(constraints.elements());


        // add plan element as expansion
        AllocationResult estAR = null;

        Expansion new_exp = getPlanningFactory().createExpansion(parentTask
                .getPlan(), parentTask, new_wf, estAR);
        getBlackboardService().publishAdd(packTask);
        getBlackboardService().publishAdd(new_wf);
        getBlackboardService().publishAdd(new_exp);
    }


    /**
     * <b>Description</b>: Create a pacing task to get the books packed. <br>
     * <b>Notes</b>:<br>
     * - <br>
     *
     * @param isbn String
     * @param quantity int
     * @param theCOF ClusterObjectFactory
     *
     * @return Task
     */
    private Task createPackTask(String isbn, int quantity,
        ClusterObjectFactory theCOF) {
        NewTask task = theCOF.newTask();

        task.setDirectObject(null);
        task.setVerb(new Verb(BolSocietyUtils.PUBLISHERPACK_VERB));

        // add the isbn preposition
        Vector preps = new Vector();
        NewPrepositionalPhrase npp = BolSocietyUtils.createPrepPhrase(isbn,
                BolSocietyUtils.ISBN_PREPOSITION, theCOF);
        preps.add(npp);

        NewPrepositionalPhrase workflowPP = theCOF.newPrepositionalPhrase();
        workflowPP.setPreposition(BolSocietyUtils.GENERIC_WORKFLOW_PREPOSITION);
        preps.add(workflowPP);
        task.setPrepositionalPhrases(preps.elements());
        task.setPlan(theCOF.getRealityPlan());

        // add the requested quantity as a preference, only the exact amount is preferred
        Vector newPreferences = new Vector();

        // lose 25% for every day we miss, after 4 days it's too late
        ScoringFunction scorefcn = ScoringFunction.createPreferredAtValue(AspectValue
                .newAspectValue(AspectType.QUANTITY, quantity), .95);

        // this counts 33% towards the total completion ability, need to find the book and ship it too, all are equally important
        Preference pref = theCOF.newPreference(AspectType.QUANTITY, scorefcn,
                0.333);

        newPreferences.addElement(pref);

        // show final completion (i.e. the performance of this task is now in the past)
        ScoringFunction complScorefcn = ScoringFunction.createPreferredAtValue(AspectValue
                .newAspectValue(BolSocietyUtils.COMPLETED_ASPECT,
                    BolSocietyUtils.ISCOMPLETED), 1.0);


        // this counts 0 against the preference score, since it indicates final completion of the task
        // it isn't used to show how well planned the task is
        Preference complPref = theCOF.newPreference(BolSocietyUtils.COMPLETED_ASPECT,
                complScorefcn, 0.0);
        newPreferences.addElement(complPref);
        task.setPreferences(newPreferences.elements());
        return task;
    }


    /**
     * <b>Description</b>: Create a shipping task to ship the books to either
     * customer or Warehouse. <br>
     * <b>Notes</b>:<br>
     * - <br>
     *
     * @param ud UserDetails
     * @param shippingMethod String
     * @param theCOF ClusterObjectFactory
     * @param quantity Quantity of books
     *
     * @return Task
     */
    private Task createShippingTask(UserDetails ud, String shippingMethod,
        ClusterObjectFactory theCOF, int quantity) {
        if (logging.isDebugEnabled()) {
            logging.debug("PublisherExpander: CreateShippingTask: " + quantity);
        }

        NewTask task = theCOF.newTask();
        task.setVerb(new Verb(BolSocietyUtils.SHIPPER_VERB));

        Vector preps = new Vector();
        NewPrepositionalPhrase npp = BolSocietyUtils.createPrepPhrase(ud,
                BolSocietyUtils.USERDETAIL_PREPOSITION, theCOF);
        preps.add(npp);
        npp = BolSocietyUtils.createPrepPhrase(shippingMethod,
                BolSocietyUtils.SHIPMETHOD_PREPOSITION, theCOF);
        preps.add(npp);
        NewPrepositionalPhrase workflowPP = getPlanningFactory()
                                                .newPrepositionalPhrase();
        workflowPP.setPreposition(BolSocietyUtils.GENERIC_WORKFLOW_PREPOSITION);
        preps.add(workflowPP);
        task.setPrepositionalPhrases(preps.elements());
        task.setPlan(theCOF.getRealityPlan());
        GregorianCalendar calToday = new GregorianCalendar();
        calToday.add(GregorianCalendar.DATE, 20);
        double shipBeforeDay = calToday.getTime().getTime();


        // add the desired "hold money until" day as a preference, holding it less than the time required is ok
        Vector newPreferences = new Vector();

        ScoringFunction qscorefcn = ScoringFunction.createPreferredAtValue(AspectValue
                .newAspectValue(AspectType.QUANTITY, quantity), .95);

        // this counts 33% towards the total completion ability, need to find the book and ship it too, all are equally important
        Preference qpref = theCOF.newPreference(AspectType.QUANTITY, qscorefcn,
                0.333);
        newPreferences.addElement(qpref);
        // lose 5% for every day we miss, after 20 days it's too late, the credit card is no longer on hold for us
        ScoringFunction scorefcn = ScoringFunction.createNearOrAbove(AspectValue
                .newAspectValue(AspectType.END_TIME, shipBeforeDay), .05);

        // this counts 33% towards the total completion ability, need to find the book and ship it too, all are equally important
        Preference pref = theCOF.newPreference(AspectType.END_TIME, scorefcn,
                1.0);
        newPreferences.addElement(pref);

        // show final completion (i.e. the performance of this task is now in the past)
        ScoringFunction complScorefcn = ScoringFunction.createPreferredAtValue(AspectValue
                .newAspectValue(BolSocietyUtils.COMPLETED_ASPECT,
                    BolSocietyUtils.ISCOMPLETED), 1.0);


        // this counts 0 against the preference score, since it indicates final completion of the task
        // it isn't used to show how well planned the task is
        Preference complPref = theCOF.newPreference(BolSocietyUtils.COMPLETED_ASPECT,
                complScorefcn, 0.0);
        newPreferences.addElement(complPref);
        task.setPreferences(newPreferences.elements());

        GISPG gis_pg = (GISPG) getPlanningFactory().createPropertyGroup("GISPG");
        ((NewGISPG) gis_pg).setSourceCity(new String("Dayton, OH"));
        task.setDirectObject(new GISBehavior(gis_pg));

        return task;

    }
}
