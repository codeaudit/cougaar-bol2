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
import org.cougaar.planning.ldm.asset.AssetGroup;
import org.cougaar.planning.ldm.asset.NewItemIdentificationPG;
import org.cougaar.planning.ldm.plan.Allocation;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.AllocationResultAggregator;
import org.cougaar.planning.ldm.plan.AspectType;
import org.cougaar.planning.ldm.plan.AspectValue;
import org.cougaar.planning.ldm.plan.Disposition;
import org.cougaar.planning.ldm.plan.Expansion;
import org.cougaar.planning.ldm.plan.NewPrepositionalPhrase;
import org.cougaar.planning.ldm.plan.NewTask;
import org.cougaar.planning.ldm.plan.NewWorkflow;
import org.cougaar.planning.ldm.plan.PlanElement;
import org.cougaar.planning.ldm.plan.Role;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.planning.ldm.plan.Verb;
import org.cougaar.planning.service.PrototypeRegistryService;
import org.cougaar.tutorial.booksonline.assets.BookAsset;
import org.cougaar.tutorial.booksonline.assets.PackerAsset;
import org.cougaar.tutorial.booksonline.common.BOLComponentPlugin;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;
import org.cougaar.tutorial.booksonline.util.BookUtil;
import org.cougaar.tutorial.booksonline.warehouse.WarehouseUtils;
import org.cougaar.tutorial.booksonline.web.model.BookModel;
import org.cougaar.util.UnaryPredicate;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;


/**
 * Subscribes to PackBooksAtWarehouse Tasks and tries to  allocate the task to
 * a Packer when enough books exist in the publisher's warehouse, otherwise
 * the task is marked as failed through a Failed Disposition.  Also,
 * allocation results  from the ShipBooks task are subscribed to, in order to
 * copy the reported result to the estimated and observetd result slots of the
 * plan element.
 *
 * @author ttschampel
 * @version $Revision: 1.2 $
 */
public class PublisherAllocatorPlugin extends BOLComponentPlugin {
    /** Plugin name */
    private static final String pluginName = "PublisherAllocatorPlugin";
    /** Predicate for packBooks Task */
    private static UnaryPredicate packBooksPredicate = new UnaryPredicate() {
            public boolean execute(Object o) {
                if (o instanceof Task) {
                    Task t = (Task) o;
                    return t.getVerb().equals(Verb.getVerb(
                            BolSocietyUtils.PUBLISHERPACK_VERB));
                }

                return false;
            }
        };

    /** DatabaseService */
    private DatabaseService dbService = null;
    /** PrototypeRegistryService */
    private PrototypeRegistryService prototypeRegistryService = null;
    /** Subscription to pack books task */
    private IncrementalSubscription packBooksSubscription;
    /** Predicate for all packer assets */
    private UnaryPredicate allPackerAssetsPredicate = new UnaryPredicate() {
            public boolean execute(Object o) {
                return o instanceof PackerAsset;
            }
        };

    /** Subscripton to ship book allocation results */
    private IncrementalSubscription allocationResults = null;
    /** Predicate for allocation results from the ShipBook Tasks */
    private UnaryPredicate allocationResultsPredicate = new UnaryPredicate() {
            public boolean execute(Object o) {
                if (o instanceof PlanElement) {
                    PlanElement pe = (PlanElement) o;
                    if (pe.getReportedResult() != null) {
                        Task t = pe.getTask();
                        if (t.getVerb().toString().equals(BolSocietyUtils.SHIPPER_VERB)) {
                            return true;

                        }
                    }
                }

                return false;
            }
        };

    /** Time for a packer to pack books */
    private long packerTime = 1000;

    /**
     * Load the plugin and setup prototype registry service and database
     * service
     */
    public void load() {
        super.load();
        DatabaseServiceProvider dbServiceProvider = new DatabaseServiceProvider(getBindingSite()
                                                                                    .getServiceBroker());
        getBindingSite().getServiceBroker().addService(DatabaseService.class,
            dbServiceProvider);
        dbService = (DatabaseService) this.getServiceBroker().getService(this,
                DatabaseService.class, null);
        prototypeRegistryService = (PrototypeRegistryService) getBindingSite()
                                                                  .getServiceBroker()
                                                                  .getService(this,
                PrototypeRegistryService.class, null);
    }


    /**
     * Setup subscritpions
     */
    protected void setupSubscriptions() {
        packBooksSubscription = (IncrementalSubscription) getBlackboardService()
                                                              .subscribe(packBooksPredicate);
        allocationResults = (IncrementalSubscription) getBlackboardService()
                                                          .subscribe(allocationResultsPredicate);
    }


    /**
     * Process subscriptions
     */
    protected void execute() {
        if (logging.isDebugEnabled()) {
            logging.debug(pluginName + " executing");
        }

        processPackRequest();
        processAllocationResults();
    }


    /**
     * Copies the reported allocation results to the estimated and observed
     * results.
     */
    private void processAllocationResults() {
        Enumeration e = allocationResults.getChangedList();
        while (e.hasMoreElements()) {
            PlanElement pe = (PlanElement) e.nextElement();
            if (pe.getObservedResult() == null) {
                AllocationResult repAr = pe.getReportedResult();
                pe.setObservedResult(repAr);
                pe.setEstimatedResult(repAr);
                getBlackboardService().publishChange(pe);

            }
        }
    }


    /**
     * Process pack tasks
     */
    private void processPackRequest() {
        Enumeration enumeration = packBooksSubscription.getAddedList();
        while (enumeration.hasMoreElements()) {
            Task task = (Task) enumeration.nextElement();
            boolean haveEnough = true;
			
            //Map of isbn --> BookModel
            Map bookMap = new HashMap();

            //Map of isbn --> Quantity needed
            Map bookQtyMap = new HashMap();

            //get number requested
            int numRequested = (int) task.getPreferredValue(AspectType.QUANTITY);


            //get book isbn and quantities
            String orderString = (String) task.getPrepositionalPhrase(BolSocietyUtils.ISBN_PREPOSITION)
                                              .getIndirectObject();
            StringTokenizer tokenizer = new StringTokenizer(orderString, ";");
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                int numberAvailable = 0;
                int colpos = token.indexOf(":");
                String bookISBN = token.substring(0, colpos);
                int numOrdered = Integer.parseInt(token.substring(colpos + 1,
                            token.length()));
                numberAvailable = numOrdered;
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
                            if (logging.isDebugEnabled()) {
                                logging.debug(
                                    "Inventory too low to fullfill request, fail task");
                            }


                            AspectValue[] values = new AspectValue[0];

                            AllocationResult failedResult = getPlanningFactory()
                                                                .newAllocationResult(1.0,
                                    false, values);
                            Disposition failedDisp = getPlanningFactory()
                                                         .createFailedDisposition(task
                                    .getPlan(), task, failedResult);
                            getBlackboardService().publishAdd(failedDisp);

                            haveEnough = false;
                            break;

                        } else {
                            bookMap.put(book.getIsbn(), book);
                            bookQtyMap.put(book.getIsbn(),
                                new Integer(numOrdered));
                        }
                    }
                } catch (SQLException sqlex) {
                    if (logging.isErrorEnabled()) {
                        logging.error("Error checking inventory", sqlex);
                    }
                }
            }

            if (haveEnough) {
                if (logging.isDebugEnabled()) {
                    logging.debug("Have enough books to fullfill order");
                }

                Set keyset = bookMap.keySet();
                Iterator keyIterator = keyset.iterator();

                //Vector of BookAsset for AssetGroup
                Vector books = new Vector();
                AssetGroup assetGroup;

                while (keyIterator.hasNext()) {
                    String isbn = (String) keyIterator.next();
                    BookModel book = (BookModel) bookMap.get(isbn);
                    int quantityOrdered = ((Integer) bookQtyMap.get(isbn))
                        .intValue();

                    //decrement inventory and add book to asset group
                    try {
                        //					decrement inventory
                        Map parameters = new HashMap();
                        parameters.put(BolSocietyUtils.Database.BOOK_ISBN_PARAMETER,
                            book.getIsbn());
                        parameters.put(BolSocietyUtils.Database.INVENTORY_LEVEL_PARAMETER,
                            "" + (book.getShelf() - quantityOrdered));
                        dbService.executeStatement(BolSocietyUtils.Database.PUBLISHER_UPDATE_INVENTORY_QUERY,
                            parameters);
                        //add books that can be packed to asset group
                        BookAsset bookAsset = null;

                        //see if prototype exists for this book
                        if (!prototypeRegistryService.isPrototypeCached(
                                book.getIsbn())) {
                            BookAsset prototype = (BookAsset) getPlanningFactory()
                                                                  .createPrototype(BookAsset.class,
                                    book.getIsbn());
                            NewItemIdentificationPG itemIdentificationPG = (NewItemIdentificationPG) getPlanningFactory()
                                                                                                         .createPropertyGroup("ItemIdentificationPG");
                            itemIdentificationPG.setItemIdentification(book
                                .getIsbn());
                            prototype.setItemIdentificationPG(itemIdentificationPG);
                            prototype = WarehouseUtils
                                .createBookAssetFromBookModel(book,
                                    getPlanningFactory());
                            if (logging.isDebugEnabled()) {
                                logging.debug("Caching prototype "
                                    + itemIdentificationPG
                                    .getItemIdentification() + " under"
                                    + BolSocietyUtils.OBJECT_PROTOTYPE);
                            }

                            this.prototypeRegistryService.cachePrototype(book
                                .getIsbn(), prototype);
                        }

                        bookAsset = (BookAsset) getPlanningFactory()
                                                    .createInstance(book
                                .getIsbn(), book.getIsbn()).getPrototype();
                        NewItemIdentificationPG itemIdentificationPG = (NewItemIdentificationPG) getPlanningFactory()
                                                                                                     .createPropertyGroup("ItemIdentificationPG");
                        itemIdentificationPG.setItemIdentification(book.getIsbn());
                        bookAsset.setItemIdentificationPG(itemIdentificationPG);
                        bookAsset.createAggregate(quantityOrdered);
                        books.add(bookAsset);
                    } catch (SQLException sqlex) {
                        if (logging.isErrorEnabled()) {
                            logging.error("Error updateing inventory", sqlex);
                        }
                    }
                }

                //Allocate to packer
                allocateToPacker(numRequested, task, books);

            }
        }
    }


    /**
     * Allocate to packer
     *
     * @param numberAvailable Total number of books to pack
     * @param task The PackBooksAtWarehouse Task
     * @param books Vector of BookAssets including in the packing order
     */
    private void allocateToPacker(int numberAvailable, Task task, Vector books) {
        if (logging.isDebugEnabled()) {
            logging.debug("Have enough books for the order");

        }

        //				get least busy packer
        PackerAsset packer = getLeastUsedPackerAsset();

        //Create packer verb
        NewTask packerTask = getPlanningFactory().newTask();
        packerTask.setVerb(Verb.getVerb(BolSocietyUtils.PACKER_VERB));

        Vector preps = new Vector();
        NewPrepositionalPhrase workflowPP = getPlanningFactory()
                                                .newPrepositionalPhrase();
        workflowPP.setPreposition(BolSocietyUtils.GENERIC_WORKFLOW_PREPOSITION);
        preps.add(workflowPP);
        packerTask.setPrepositionalPhrases(preps.elements());

        //create expansion of 
        NewWorkflow nwf = getPlanningFactory().newWorkflow();
        nwf.setAllocationResultAggregator(AllocationResultAggregator.DEFAULT);
        nwf.setParentTask(task);
        nwf.addTask(packerTask);
        packerTask.setWorkflow(nwf);
        packerTask.setParentTask(task);

        //set direct object to packer asset
        AssetGroup booksAsset = (AssetGroup) getPlanningFactory()
                                                 .createInstance(new AssetGroup());
        uidService.registerUniqueObject(booksAsset);
        NewItemIdentificationPG newIPG = (NewItemIdentificationPG) getPlanningFactory()
                                                                       .createPropertyGroup("ItemIdentificationPG");
        newIPG.setItemIdentification(booksAsset.getUID().toString());
        booksAsset.setItemIdentificationPG(newIPG);
        booksAsset.setAssets(books);


        packerTask.setDirectObject(booksAsset);
        getBlackboardService().publishAdd(packerTask);

        //allocate task to packer
        double taskStart = 0.0;
        double taskEnd = 0.0;
        if (packer != null) // an available packer asset was returned
         { // asset will be null if all are allocated
            double calToday = currentTimeMillis();
            taskStart = calToday;
            taskEnd = taskStart + packerTime;
        }

        int[] aspect_types = {
            AspectType.START_TIME, AspectType.END_TIME, AspectType.QUANTITY,
            BolSocietyUtils.PACK_DATE_ASPECT,
            BolSocietyUtils.DROP_SHIP_DATE_ASPECT,
            BolSocietyUtils.COMPLETED_ASPECT
        };
        double packBy = 0.0;
        long yyyymmdd;
        GregorianCalendar today = new GregorianCalendar();
        Date nowdate = new Date(currentTimeMillis());
        today.setTime(new Date(currentTimeMillis()));
        today.add(Calendar.DATE, 2); // project pack date 2 days from now
        packBy = (double) today.getTime().getTime();
        yyyymmdd = today.get(Calendar.YEAR) * 10000;
        yyyymmdd += ((today.get(Calendar.MONTH) + 1) * 100);
        yyyymmdd += today.get(Calendar.DATE);
        if (logging.isDebugEnabled()) {
            logging.debug("WarehouseAllocator: projected pack date is: "
                + yyyymmdd);
        }

        double dropShipBy = 0.0;
        double[] results = {
            taskStart, taskEnd, (double) numberAvailable, packBy, dropShipBy,
            BolSocietyUtils.ISNOTCOMPLETED
        };
        AspectValue[] values = new AspectValue[6];
        values[0] = AspectValue.newAspectValue(aspect_types[0], results[0]);
        values[1] = AspectValue.newAspectValue(aspect_types[1], results[1]);
        values[2] = AspectValue.newAspectValue(aspect_types[2], results[2]);
        values[3] = AspectValue.newAspectValue(aspect_types[3], results[3]);
        values[4] = AspectValue.newAspectValue(aspect_types[4], results[4]);
        values[5] = AspectValue.newAspectValue(aspect_types[5], results[5]);

        AllocationResult estAR = getPlanningFactory().newAllocationResult(1.0, // rating
                true, // success,
                values);
        Allocation allocation = getPlanningFactory().createAllocation(packerTask
                .getPlan(), packerTask, packer, estAR, Role.ASSIGNED);
        getBlackboardService().publishAdd(allocation);
        estAR = null;
        Expansion new_exp = getPlanningFactory().createExpansion(task.getPlan(),
                task, nwf, estAR);
        getBlackboardService().publishAdd(new_exp);
        getBlackboardService().publishAdd(nwf);
        if (logging.isDebugEnabled()) {
            logging.debug("Allocate packer task to "
                + packer.getItemIdentificationPG().getItemIdentification());
        }
    }


    /**
     * <b>Description</b>: Find the packer who currently is the least
     * scheduled. <br>
     * <b>Notes</b>:<br>
     * - Any notes about the method goes here <br>
     *
     * @return The packer asset who is least busy.
     */
    private PackerAsset getLeastUsedPackerAsset() {
        PackerAsset asset = null;
        PackerAsset returnAsset = null;
        int count = 1000;
        Collection allPackerAssets = getBlackboardService().query(allPackerAssetsPredicate);
        Iterator iterator = allPackerAssets.iterator();
        while (iterator.hasNext()) {
            asset = (PackerAsset) iterator.next();
            Enumeration numScheds = asset.getRoleSchedule()
                                         .getRoleScheduleElements();

            int i = 0;
            while (numScheds.hasMoreElements()) {
                numScheds.nextElement();
                i++;
            }

            if (i < count) {
                returnAsset = asset;
            }
        }

        return returnAsset;
    }
}
