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


import com.cougaarsoftware.common.plugin.WorkflowExecutionPlugin;

import org.cougaar.planning.ldm.plan.Expansion;
import org.cougaar.planning.ldm.plan.PlanElement;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;
import org.cougaar.util.UnaryPredicate;


/**
 * This is an extention of the <code>WorkflowExecutionPlugin</code> from the
 * CSIUtil library.  This class defines the <code>UnaryPredicate</code>s for
 * the subscriptions to tasks from a workflow, expansions, and plan elements.
 *
 * @author mabrams
 *
 * @see WorkflowExecutionPlugin
 */
public class BOLWorkflowExecutionPlugin extends WorkflowExecutionPlugin {
    protected String pluginName = "ExampleWorkflowExecutionPlugin";

    /**
     * Unary predicate for all tasks within a workflow to be monitored by this
     * plugin
     *
     * @return UnaryPredicate
     */
    protected UnaryPredicate allWorkflowTasksPredicate() {
        return new UnaryPredicate() {
                public boolean execute(Object o) {
                    if (o instanceof Task) {
                        Task t = (Task) o;
                        if (t.getPrepositionalPhrase(
                                BolSocietyUtils.GENERIC_WORKFLOW_PREPOSITION) != null) {
                            return true;
                        }
                    }

                    return false;
                }
            };
    }


    /**
     * UnaryPredicate for all PlanElements within a workflow to be monitored by
     * this plugin
     *
     * @return UnayPredicate
     */
    protected UnaryPredicate allWorkflowPlanElementsPredicate() {
        return new UnaryPredicate() {
                public boolean execute(Object o) {
                    if (o instanceof PlanElement) {
                        PlanElement pe = (PlanElement) o;

                        if (pe.getTask().getPrepositionalPhrase(BolSocietyUtils.GENERIC_WORKFLOW_PREPOSITION) != null) {
                            return true;
                        }
                    }

                    return false;
                }
            };
    }


    /**
     * UnaryPredicate for all workflow expansions to be monitored by this
     * plugin.
     *
     * @return UnaryPredicate
     */
    protected UnaryPredicate allWorkflowExpansionsPredicate() {
        return new UnaryPredicate() {
                public boolean execute(Object o) {
                    if (o instanceof Expansion) {
                        Expansion expansion = (Expansion) o;
                        if ((expansion.getWorkflow().getParentTask()
                                      .getPrepositionalPhrase(BolSocietyUtils.GENERIC_WORKFLOW_PREPOSITION) != null)) {
                            return true;
                        }
                    }

                    return false;
                }
            };
    }
}
