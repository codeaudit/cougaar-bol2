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


import org.cougaar.core.blackboard.IncrementalSubscription;
import org.cougaar.core.component.ServiceBroker;
import org.cougaar.core.service.ThreadService;
import org.cougaar.planning.ldm.plan.MPTask;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.tutorial.booksonline.common.BOLComponentPlugin;
import org.cougaar.tutorial.booksonline.common.PerformJob;
import org.cougaar.util.UnaryPredicate;

import java.util.Enumeration;


/**
 * Subscribes to print run tasks and models a print run being completed by
 * using the TreadService and PerformJob TimerTask.
 *
 * @author ttschampel
 */
public class PublisherPrinterPlugin extends BOLComponentPlugin {
    /** Plugin name */
    private static final String pluginName = "PublisherPrinterPlugin";
    /** Subscription to print run tasks */
    private IncrementalSubscription printRunSubs = null;
    /** Predicate for print run tasks */
    private UnaryPredicate printRunPredicate = new UnaryPredicate() {
            public boolean execute(Object o) {
                if (o instanceof Task) {
                    Task t = (Task) o;
                    return t.getVerb().toString().equals(PublisherConstants.PRINTRUN_VERB);
                }

                return false;
            }
        };

    /** Time to print */
    private long timeToPrint = 1000;
    /** Cougaar Thread Service */
    private ThreadService threadService = null;

    /**
     * Setup subscriptions
     */
    protected void setupSubscriptions() {
        printRunSubs = (IncrementalSubscription) getBlackboardService()
                                                     .subscribe(printRunPredicate);

    }


    /**
     * Load component and get threading service
     */
    public void load() {
        super.load();
        ServiceBroker sb = getBindingSite().getServiceBroker();
        threadService = (ThreadService) sb.getService(this,
                ThreadService.class, null);
    }


    /**
     * Execute subscriptions
     */
    protected void execute() {
        if (logging.isDebugEnabled()) {
            logging.debug(pluginName + " executing");
        }

        checkForPrintRuns();
    }


    /**
     * Check for new printRun tasks on the blackboard and then schedule the
     * jobs to be completed using a performjob and the threadingservice.
     */
    private void checkForPrintRuns() {
        Enumeration enumeration = printRunSubs.getAddedList();
        while (enumeration.hasMoreElements()) {
            MPTask printRun = (MPTask) enumeration.nextElement();
            PerformJob printPerformJob = new PerformJob(getBlackboardService(),
                    printRun, logging, getPlanningFactory());
            threadService.schedule(printPerformJob, timeToPrint);

        }
    }
}
