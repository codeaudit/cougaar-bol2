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
import org.cougaar.planning.ldm.plan.Disposition;
import org.cougaar.planning.ldm.plan.NewPrepositionalPhrase;
import org.cougaar.planning.ldm.plan.NewTask;
import org.cougaar.planning.ldm.plan.PlanElement;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.planning.ldm.plan.Verb;
import org.cougaar.tutorial.booksonline.common.BOLComponentPlugin;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;
import org.cougaar.tutorial.booksonline.util.BookUtil;
import org.cougaar.tutorial.booksonline.web.model.BookModel;
import org.cougaar.util.UnaryPredicate;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;


/**
 * Subscribes to failed PackBooksAtWarehouse tasks and     then builds
 * PrintBook Tasks for the books that have a shortfall in the publisher's
 * warehouse
 *
 * @author ttschampel
 */
public class PublisherAccessorPlugin extends BOLComponentPlugin {
    /** plugin name */
    private static final String pluginName = "PublisherAccessorPlugin";
    /** DatabaseService */
    private DatabaseService dbService = null;
    /** Subscription to failed pack and warehouse tasks */
    private IncrementalSubscription failedTaskSubs;
    /** Predicate for pack at warehouse task */
    private UnaryPredicate failedTaskPredicate = new UnaryPredicate() {
            public boolean execute(Object o) {
                if (o instanceof Disposition) {
                    Disposition pe = (Disposition) o;

                    return (pe.isSuccess() == false);


                }

                return false;
            }
        };

    /**
     * Setup subscription to failed tasks
     */
    protected void setupSubscriptions() {
        failedTaskSubs = (IncrementalSubscription) getBlackboardService()
                                                       .subscribe(failedTaskPredicate);

    }


    /**
     * Load component and database service
     */
    public void load() {
        super.load();
        DatabaseServiceProvider dbServiceProvider = new DatabaseServiceProvider(getBindingSite()
                                                                                    .getServiceBroker());
        getBindingSite().getServiceBroker().addService(DatabaseService.class,
            dbServiceProvider);
        dbService = (DatabaseService) this.getServiceBroker().getService(this,
                DatabaseService.class, null);
    }


    /**
     * Execute subscriptions
     */
    protected void execute() {
        if (logging.isDebugEnabled()) {
            logging.debug(pluginName + " executing");
        }

        checkForFailedTasks();
    }


    /**
     * Check for failed tasks
     */
    private void checkForFailedTasks() {
        Enumeration enumeration = failedTaskSubs.getAddedList();
        while (enumeration.hasMoreElements()) {
            PlanElement pe = (PlanElement) enumeration.nextElement();
            Task failedTask = pe.getTask();
            if (logging.isDebugEnabled()) {
                logging.debug("Received failed task " + failedTask);
            }

            //			get book isbn and quantities
            String orderString = (String) failedTask.getPrepositionalPhrase(BolSocietyUtils.ISBN_PREPOSITION)
                                                    .getIndirectObject();
            StringTokenizer tokenizer = new StringTokenizer(orderString, ";");

            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                int colpos = token.indexOf(":");
                String bookISBN = token.substring(0, colpos);
                int numOrdered = Integer.parseInt(token.substring(colpos + 1,
                            token.length()));
                //check inventory level for this book
                Map parameters = new HashMap();
                parameters.put(BolSocietyUtils.Database.BOOK_ISBN_PARAMETER,
                    bookISBN);
                try {
                    ArrayList results = (ArrayList) dbService.executeQuery(BolSocietyUtils.Database.PUBLISHER_FIND_BOOK_BY_ISBN_QUERY,
                            parameters);

                    if (results.size() > 0) {
                        Object[] objects = (Object[]) results.get(0);
                        BookModel book = BookUtil.getBookModelFromDatabase(objects);
                        if (book.getShelf() < numOrdered) {
                            int numberNeeded = numOrdered - book.getShelf();

                            //short fall create print books task
                            NewTask printBooksTask = getPlanningFactory()
                                                         .newTask();
                            printBooksTask.setVerb(Verb.getVerb(
                                    PublisherConstants.PRINTER_VERB));
                            Vector phrases = new Vector();
                            NewPrepositionalPhrase isbnPP = getPlanningFactory()
                                                                .newPrepositionalPhrase();
                            isbnPP.setPreposition(BolSocietyUtils.ISBN_PREPOSITION);
                            isbnPP.setIndirectObject(book.getIsbn() + ":"
                                + numberNeeded);
                            phrases.add(isbnPP);
                            printBooksTask.setPrepositionalPhrases(phrases
                                .elements());
                            if (logging.isDebugEnabled()) {
                                logging.debug("Creating print task for "
                                    + book.getIsbn() + " for " + numberNeeded
                                    + " books");
                            }

                            getBlackboardService().publishAdd(printBooksTask);
                        }
                    }
                } catch (SQLException sqlex) {
                    if (logging.isErrorEnabled()) {
                        logging.error("error checking inventory for book", sqlex);
                    }
                }
            }
        }
    }
}
