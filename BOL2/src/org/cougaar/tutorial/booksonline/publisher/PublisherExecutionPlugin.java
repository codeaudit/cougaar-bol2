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
import org.cougaar.core.service.ThreadService;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.tutorial.booksonline.common.BOLComponentPlugin;
import org.cougaar.tutorial.booksonline.common.PerformJob;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;
import org.cougaar.util.UnaryPredicate;

import java.util.Enumeration;


/**
 * Subscribes to Packer Tasks, and models the packer packing the books using
 * the ThreadingService and the PerformJob TimerTask to complete  the task.
 *
 * @author ttschampel
 */
public class PublisherExecutionPlugin extends BOLComponentPlugin {
    /** Plugin Name */
    private static final String pluginName = "PublisherExecutionPlugin";
    /** Subscription to packer tasks */
    private IncrementalSubscription packerSubs = null;
    /** Predicate for packer tasks */
    private UnaryPredicate packerPredicate = new UnaryPredicate() {
            public boolean execute(Object o) {
                if (o instanceof Task) {
                    Task t = (Task) o;
                    return t.getVerb().toString().equals(BolSocietyUtils.PACKER_VERB);
                }

                return false;
            }
        };

    /** DatabaseService */
    private DatabaseService dbService = null;
    /** Cougaar Thread Service */
    private ThreadService threadService = null;

    /**
     * Setup subscription to packer tasks
     */
    protected void setupSubscriptions() {
        packerSubs = (IncrementalSubscription) getBlackboardService().subscribe(packerPredicate);

    }


    /**
     * Load component and get thread service
     */
    public void load() {
        super.load();
        ServiceBroker sb = getBindingSite().getServiceBroker();
        threadService = (ThreadService) sb.getService(this,
                ThreadService.class, null);
        DatabaseServiceProvider dbServiceProvider = new DatabaseServiceProvider(getBindingSite()
                                                                                    .getServiceBroker());
        getBindingSite().getServiceBroker().addService(DatabaseService.class,
            dbServiceProvider);
        dbService = (DatabaseService) this.getServiceBroker().getService(this,
                DatabaseService.class, null);
    }


    /**
     * Execute Subscriptions
     */
    protected void execute() {
        if (logging.isDebugEnabled()) {
            logging.debug(pluginName + " executing");
        }

        checkForPackerTasks();

    }


    /**
     * Check for new packer tasks
     */
    private void checkForPackerTasks() {
        Enumeration enumeration = packerSubs.getAddedList();
        while (enumeration.hasMoreElements()) {
            Task packerTask = (Task) enumeration.nextElement();
            PerformJob performJob = new PerformJob(getBlackboardService(),
                    packerTask, logging, getPlanningFactory());
            long packingTime = 10000;
            threadService.schedule(performJob, packingTime);

            if (logging.isDebugEnabled()) {
                logging.debug("received packer task");
            }
        }
    }
}
