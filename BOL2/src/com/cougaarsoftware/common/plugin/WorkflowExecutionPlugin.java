/*
 * <copyright>
 *  Copyright 2000-2003 Cougaar Software, Inc.
 *  All Rights Reserved
 * </copyright>
 */


package com.cougaarsoftware.common.plugin;


import java.util.Enumeration;

import org.cougaar.core.blackboard.IncrementalSubscription;
import org.cougaar.planning.ldm.plan.Constraint;
import org.cougaar.planning.ldm.plan.ConstraintEvent;
import org.cougaar.planning.ldm.plan.Expansion;
import org.cougaar.planning.ldm.plan.SettableConstraintEvent;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.planning.ldm.plan.Workflow;
import org.cougaar.planning.plugin.util.PluginHelper;
import org.cougaar.util.UnaryPredicate;


/**
 * This is a workflow execution plugin.  It simply walks over a workflow and
 * publishes unconstrained tasks in that workflow.    NOTE:  This plugin
 * relies upon the Cougaar infastructure to propogate allocation results from
 * subtasks to parent tasks.  To ensure that the infastructure will propogate
 * results, you must set the parent task when create subtasks for a workflow
 *
 * @author mabrams
 */
public abstract class WorkflowExecutionPlugin extends AdvancedComponentPlugin {
    protected String pluginName = "WorkflowExecutionPlugin";
    protected IncrementalSubscription allWorkflowTasks;
    protected IncrementalSubscription allWorkflowExpansions;
    protected IncrementalSubscription allWorkflowPlanElements;

    /**
     * Predicate to setup subscriptions to all tasks of a workflow this class
     * is monitoring.  Overide to specify the specify the tasks  this plugin
     * should subcribe to.
     *
     * @return the unary predicate which is true for the workflow sub tasks
     */
    protected abstract UnaryPredicate allWorkflowTasksPredicate();


    /**
     * Predicate to setup subscriptions to all plan elements of tasks in a
     * workflow.
     *
     * @return the unary predicate which is true for the workflow plan elements
     */
    protected abstract UnaryPredicate allWorkflowPlanElementsPredicate();


    /**
     * Predicate to setup subscriptions to all expansions this class is
     * monitoring.  Overide to specify the specify the expansions this plugin
     * should subcribe to.
     *
     * @return the unary predicate which is true for the workflow sub tasks
     */
    protected abstract UnaryPredicate allWorkflowExpansionsPredicate();


    /**
     * Support for any start up processing, like reading in plugin params.
     */
    public void load() {
        super.load();

        pluginParameters = readParameters();

        if (logging.isDebugEnabled()) {
            logging.debug("Plugin " + pluginName + "loaded.");
        }
    }


    /**
     * Sets up the subscriptions for all expansions, main tasks, and sub tasks
     * monitored by this class
     */
    protected void setupSubscriptions() {
        allWorkflowTasks = (IncrementalSubscription) getBlackboardService()
                                                         .subscribe(allWorkflowTasksPredicate());
        allWorkflowExpansions = (IncrementalSubscription) getBlackboardService()
                                                              .subscribe(allWorkflowExpansionsPredicate());
        allWorkflowPlanElements = (IncrementalSubscription) getBlackboardService()
                                                                .subscribe(allWorkflowPlanElementsPredicate());

        if (logging.isDebugEnabled()) {
            logging.debug("Plugin " + pluginName
                + "completed setupSubscriptions.");
        }
    }


    /**
     * The main execute method of the plugin.  Simply checks for any changes to
     * expansions and updates allocations results for the expansions
     * subscribed to by this class.
     */
    protected void execute() {
        if (logging.isDebugEnabled()) {
            logging.debug("Executing");
        }

        checkForExpansionChanges();
        PluginHelper.updateAllocationResult(allWorkflowExpansions);
    }


    /**
     * Check to see if there are any tasks that have become unconstrained and
     * if so, publish them
     */
    private void checkForExpansionChanges() {
        Enumeration e = allWorkflowExpansions.elements();
        while (e.hasMoreElements()) {
            if (logging.isDebugEnabled()) {
                logging.debug(pluginName + " Checking My Expansions");
            }

            Expansion exp = (Expansion) e.nextElement();
            Workflow wf = exp.getWorkflow();
            Constraint c = null;
            try {
                c = wf.getNextPendingConstraint();
            } catch (IllegalArgumentException iae) {
                //ignore
                continue;
            }

            if ((c != null)
                && areConstrainingTasksComplete(wf, c.getConstrainedTask())) {
                ConstraintEvent ce = c.getConstrainedEventObject();
                if (ce instanceof SettableConstraintEvent) {
                    SettableConstraintEvent sce = (SettableConstraintEvent) ce;
                    sce.setValue(c.computeValidConstrainedValue());
                    Task taskToPublish = c.getConstrainedTask();
                    if (logging.isDebugEnabled()) {
                        logging.debug(pluginName + " Publishing: "
                            + taskToPublish);
                    }

                    getBlackboardService().publishAdd(taskToPublish);
                }
            }
        }
    }


    /**
     * Checks the constraining tasks of a constrained task in a workflow for
     * completion.
     *
     * @param wf The workflow the constrained task is in
     * @param constrainedTask The contrained task
     *
     * @return true if all of the tasks constraining the constrained tasks are
     *         complete
     */
    public boolean areConstrainingTasksComplete(Workflow wf,
        Task constrainedTask) {
        Enumeration e = wf.getConstraints();
        boolean subTasksComplete = false;
        if (logging.isDebugEnabled()) {
            logging.debug(pluginName
                + " checking for complete constraining tasks");
        }
        while (e.hasMoreElements()) {
            Constraint c = (Constraint) e.nextElement();
            if (c.getConstrainedTask().equals(constrainedTask)) {
                Task constrainingTask = c.getConstrainingTask();
                if ((constrainingTask.getPlanElement() == null)
                    || (constrainingTask.getPlanElement().getReportedResult() == null)) {
                    subTasksComplete = false;
                    break;
                } else if (!this.isCompleted(constrainingTask)) {
                    subTasksComplete = false;
                    break;
                } else {
                    subTasksComplete = true;
                }
            }
        }

        return subTasksComplete;
    }
}
