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
import org.cougaar.planning.ldm.plan.Aggregation;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.MPTask;
import org.cougaar.planning.ldm.plan.NewComposition;
import org.cougaar.planning.ldm.plan.NewMPTask;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.planning.ldm.plan.Verb;
import org.cougaar.tutorial.booksonline.common.BOLComponentPlugin;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;
import org.cougaar.util.UnaryPredicate;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;


/**
 * Subscribes to printer tasks, and aggregates them until a threshold in the
 * number of books to be printed has been reached.  At that point a  PrintRun
 * task is published.
 *
 * @author ttschampel
 */
public class PublisherPrinterAggregatorPlugin extends BOLComponentPlugin {
    /** Plugin name */
    private static final String pluginName = "PublisherPrinterAggregatorPlugin";
    /** Threshold of printing a run */
    private static final int threshold = 500;
    /** Subscription to printer tasks */
    private IncrementalSubscription allPrinterTasks;
    /** UnaryPreciate for printer tasks */
    private UnaryPredicate allPrinterTasksPredicate = new UnaryPredicate() {
            public boolean execute(Object o) {
                if (o instanceof Task) {
                    Task t = (Task) o;
                    return t.getVerb().toString().equals(PublisherConstants.PRINTER_VERB);
                }

                return false;

            }
        };

    /** Predicate for print run task */
    private UnaryPredicate allPrintRunPredicate = new UnaryPredicate() {
            public boolean execute(Object o) {
                if (o instanceof Task) {
                    Task t = (Task) o;
                    return t.getVerb().toString().equals(PublisherConstants.PRINTRUN_VERB);
                }

                return false;
            }
        };

    /**
     * Setup subscriptions
     */
    protected void setupSubscriptions() {
        allPrinterTasks = (IncrementalSubscription) getBlackboardService()
                                                        .subscribe((allPrinterTasksPredicate));
    }


    /**
     * Process subscriptions
     */
    protected void execute() {
        if (logging.isDebugEnabled()) {
            logging.debug(pluginName + " executing");
        }

        checkforNewPrinterTasks();
    }


    /**
     * Check for new printer tasks.
     */
    private void checkforNewPrinterTasks() {
        Enumeration enumeration = allPrinterTasks.elements();
        Vector waitingTasks = new Vector();
        int numberOfBooksForPrinting = 0;
        while (enumeration.hasMoreElements()) {
            Task printerTask = (Task) enumeration.nextElement();

            //get book isbn and quantities
            String orderString = (String) printerTask.getPrepositionalPhrase(BolSocietyUtils.ISBN_PREPOSITION)
                                                     .getIndirectObject();
            StringTokenizer tokenizer = new StringTokenizer(orderString, ";");
            int numberForPrint = 0;
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                int colpos = token.indexOf(":");
                numberForPrint = Integer.parseInt(token.substring(colpos + 1,
                            token.length()));
            }

            if (logging.isDebugEnabled()) {
                logging.debug("Examining printTask");
            }

            boolean alreadyAggregated = false;

            //Check if print job is already aggregated into a print run
            Collection runTasks = getBlackboardService().query(allPrintRunPredicate);
            Iterator iterator = runTasks.iterator();
            while (iterator.hasNext()) {
                MPTask runTask = (MPTask) iterator.next();
                Enumeration aggTasks = runTask.getComposition().getCombinedTask()
                                              .getParentTasks();
                while (aggTasks.hasMoreElements()) {
                    Task t = (Task) aggTasks.nextElement();
                    if (t.getUID().equals(printerTask.getUID())) {
                        alreadyAggregated = true;
                        break;
                    }
                }
            }

            if (!alreadyAggregated) {
                waitingTasks.add(printerTask);
                numberOfBooksForPrinting = numberOfBooksForPrinting
                    + numberForPrint;

            }
        }

        if (numberOfBooksForPrinting >= threshold) {
            if (logging.isDebugEnabled()) {
                logging.debug("Threshold met, create print run");
            }

            NewMPTask printRunTask = getPlanningFactory().newMPTask();
            printRunTask.setParentTasks(waitingTasks.elements());
            printRunTask.setVerb(Verb.getVerb(PublisherConstants.PRINTRUN_VERB));

            NewComposition runcomposition = getPlanningFactory().newComposition();
            runcomposition.setCombinedTask(printRunTask);


            AllocationResult runestAR = null;
            Aggregation aggregation = getPlanningFactory().createAggregation(printRunTask
                    .getPlan(), printRunTask, runcomposition, runestAR);

            //printRunTask.set
            runcomposition.addAggregation(aggregation);
            getBlackboardService().publishAdd(aggregation);
            printRunTask.setPlan(printRunTask.getPlan());
            printRunTask.setComposition(runcomposition);

            getBlackboardService().publishAdd(printRunTask);

        }
    }
}
