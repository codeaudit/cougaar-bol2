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


package org.cougaar.tutorial.booksonline.warehouse;


import com.cougaarsoftware.common.service.DatabaseService;
import com.cougaarsoftware.common.service.DatabaseServiceProvider;

import org.cougaar.core.blackboard.IncrementalSubscription;
import org.cougaar.core.util.UID;
import org.cougaar.planning.ldm.asset.AssetGroup;
import org.cougaar.planning.ldm.asset.NewItemIdentificationPG;
import org.cougaar.planning.ldm.plan.Allocation;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.AllocationResultAggregator;
import org.cougaar.planning.ldm.plan.AspectType;
import org.cougaar.planning.ldm.plan.AspectValue;
import org.cougaar.planning.ldm.plan.Expansion;
import org.cougaar.planning.ldm.plan.NewPrepositionalPhrase;
import org.cougaar.planning.ldm.plan.NewTask;
import org.cougaar.planning.ldm.plan.NewWorkflow;
import org.cougaar.planning.ldm.plan.PlanElement;
import org.cougaar.planning.ldm.plan.Preference;
import org.cougaar.planning.ldm.plan.PrepositionalPhrase;
import org.cougaar.planning.ldm.plan.Role;
import org.cougaar.planning.ldm.plan.ScoringFunction;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.planning.ldm.plan.Verb;
import org.cougaar.planning.service.PrototypeRegistryService;
import org.cougaar.tutorial.booksonline.assets.BookAsset;
import org.cougaar.tutorial.booksonline.assets.PackerAsset;
import org.cougaar.tutorial.booksonline.common.BOLComponentPlugin;
import org.cougaar.tutorial.booksonline.publisher.PublisherConstants;
import org.cougaar.tutorial.booksonline.util.BolBookOrder;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;
import org.cougaar.tutorial.booksonline.util.BookUtil;
import org.cougaar.tutorial.booksonline.util.UserDetails;
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
import java.util.StringTokenizer;
import java.util.Vector;


/**
 * WarehouseAllocatorPlugin  allocates books currently  in inventory to a
 * packer to pack and decrements the inventory. If not enough inventory, will
 * allocate as much from inventory  as possible, then request a drop shipment
 * to the customer  directly from the publisher
 *
 * @author ttschampel
 * @version $Revision: 1.2 $
 */
public class WarehouseAllocatorPlugin extends BOLComponentPlugin {
  private static final String pluginName = "WarehouseAllocatorPlugin";
  private static int[] packingAspectTypes = {
    AspectType.START_TIME, AspectType.END_TIME, AspectType.QUANTITY,
    BolSocietyUtils.PACK_DATE_ASPECT, BolSocietyUtils.DROP_SHIP_DATE_ASPECT,
    BolSocietyUtils.COMPLETED_ASPECT
  };
  /** Time for packer to pack book */
  public static double packerTime = 1000.0;
  /** DatabaseService */
  private DatabaseService dbService = null;
  /** PrototypeRegistryService */
  private PrototypeRegistryService prototypeRegistryService = null;
  /** Subscription to all book order tasks */
  private IncrementalSubscription allWarehouseTasks;
  /** Predicate for all tasks of verb 'Warehouse */
  private UnaryPredicate allWarehouseTasksPredicate = new UnaryPredicate() {
      public boolean execute(Object o) {
        if (o instanceof Task) {
          Task t = (Task) o;
          return t.getVerb().toString().equals(BolSocietyUtils.WAREHOUSEPACK_VERB);
        }

        return false;
      }
    };

  private IncrementalSubscription allocationResults = null;
  private UnaryPredicate allocationResultsPredicate = new UnaryPredicate() {
      public boolean execute(Object o) {
        if (o instanceof PlanElement) {
          PlanElement pe = (PlanElement) o;
          if (pe.getReceivedResult() != null) {
            Task t = pe.getTask();
            if (t.getVerb().equals(Verb.getVerb(BolSocietyUtils.SHIPPER_VERB))
              || t.getVerb().toString().equals(PublisherConstants.ORDER_VERB)) {
              return true;

            }
          }
        }

        return false;
      }
    };

  /** Predicate for all packer assets */
  private UnaryPredicate allPackerAssetsPredicate = new UnaryPredicate() {
      public boolean execute(Object o) {
        return o instanceof PackerAsset;
      }
    };

  /**
   * Load componet, get DatbaseService and  PrototypeRegistryService
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
   * Setup subscriptions to tasks from warehouse to pack books
   */
  public void setupSubscriptions() {
    // look for order tasks
    allWarehouseTasks = (IncrementalSubscription) getBlackboardService()
                                                    .subscribe(allWarehouseTasksPredicate);

    allocationResults = (IncrementalSubscription) getBlackboardService()
                                                    .subscribe(allocationResultsPredicate);
  }


  /**
   * Process Subscriptions
   */
  public void execute() {
    if (logging.isDebugEnabled()) {
      logging.debug(pluginName + " executing");

    }

    checkForPackTasks();
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
      if ((pe.getObservedResult() == null)
        || ((pe.getReceivedResult() != null)
        && (pe.getReceivedResult().getConfidenceRating() != pe.getObservedResult()
                                                              .getConfidenceRating()))) {
        if (logging.isDebugEnabled()) {
          logging.debug("Rolling up allocation results for" + pe.getTask());
        }


        AllocationResult repAr = pe.getReceivedResult();
        pe.setObservedResult(repAr);
        pe.setEstimatedResult(repAr);
        getBlackboardService().publishChange(pe);
      }
    }
  }


  /**
   * Check for new packer tasks
   */
  private void checkForPackTasks() {
    Enumeration wTasksE = allWarehouseTasks.getAddedList();
    while (wTasksE.hasMoreElements()) {
      Task task = (Task) wTasksE.nextElement();

      //Vector of BookAssets used in AssetGroup for packer
      Vector books = new Vector();
      AllocationResult estAR = null;
      PrepositionalPhrase pp = task.getPrepositionalPhrase(BolSocietyUtils.ISBN_PREPOSITION);
      String allOrderString = (String) pp.getIndirectObject();
      String dropshipOrderString = null;
      boolean haveEnough = true;
      int numberOfBooksAvailable = 0;

      //Check if there is enough books for the order.
      StringTokenizer tokenizer = new StringTokenizer(allOrderString, ";");
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
        parameters.put(BolSocietyUtils.Database.BOOK_ISBN_PARAMETER, bookISBN);
        try {
          ArrayList results = (ArrayList) dbService.executeQuery(BolSocietyUtils.Database.GET_BOOK_BY_ISBN_QUERY_NAME,
              parameters);

          if (results.size() > 0) {
            Object[] objects = (Object[]) results.get(0);
            BookModel book = BookUtil.getBookModelFromDatabase(objects);

            if ((book.getShelf() < numOrdered) || (book.getShelf() < 5)) {
              if (logging.isDebugEnabled()) {
                logging.debug("Inventory low, sending warning");
              }

              if (book.getShelf() < numOrdered) {
                numberAvailable = book.getShelf();
              }

              NewTask warningTask = getPlanningFactory().newTask();
              warningTask.setVerb(Verb.getVerb(
                  BolSocietyUtils.WARN_INVENTORY_VERB));
              Vector phrases = new Vector();
              NewPrepositionalPhrase isbnPhrase = getPlanningFactory()
                                                    .newPrepositionalPhrase();
              isbnPhrase.setPreposition(BolSocietyUtils.WARN_INVENTORY_ISBN_PHRASE);
              isbnPhrase.setIndirectObject(book.getIsbn());
              phrases.add(isbnPhrase);
              NewPrepositionalPhrase titlePhrase = getPlanningFactory()
                                                     .newPrepositionalPhrase();
              titlePhrase.setIndirectObject(book.getTitle());
              titlePhrase.setPreposition(BolSocietyUtils.WARN_INVENTORY_TITLE_PHRASE);
              phrases.add(titlePhrase);
              NewPrepositionalPhrase costPhrase = getPlanningFactory()
                                                    .newPrepositionalPhrase();
              costPhrase.setPreposition(BolSocietyUtils.WARN_INVENTORY_COST_PHRASE);
              costPhrase.setIndirectObject(new Float(book.getOurPrice()));
              phrases.add(costPhrase);
              warningTask.setPrepositionalPhrases(phrases.elements());
              getBlackboardService().publishAdd(warningTask);
            }

            numberOfBooksAvailable = numberOfBooksAvailable + numberAvailable;

            if (book.getShelf() < numOrdered) {
              if (logging.isDebugEnabled()) {
                logging.debug("Not enough books in inventory");
              }

              //drop shipment required
              haveEnough = false;


              if (dropshipOrderString != null) {
                dropshipOrderString = dropshipOrderString + ";";
              } else {
                dropshipOrderString = "";
              }

              dropshipOrderString = dropshipOrderString + book.getIsbn() + ":"
                + (numOrdered - book.getShelf());
            }

            //decrement inventory
            parameters = new HashMap();
            parameters.put(BolSocietyUtils.Database.BOOK_ISBN_PARAMETER,
              book.getIsbn());
            parameters.put(BolSocietyUtils.Database.INVENTORY_LEVEL_PARAMETER,
              "" + (book.getShelf() - numberAvailable));

            dbService.executeStatement(BolSocietyUtils.Database.UPDATE_INVENTORY_QUERY_NAME,
              parameters);

            //add books that can be packed to asset group
            BookAsset bookAsset = null;

            //see if prototype exists for this book
            if (!prototypeRegistryService.isPrototypeCached(book.getIsbn())) {
              BookAsset prototype = (BookAsset) getPlanningFactory()
                                                  .createPrototype(BookAsset.class,
                  book.getIsbn());
              NewItemIdentificationPG itemIdentificationPG = (NewItemIdentificationPG) getPlanningFactory()
                                                                                         .createPropertyGroup("ItemIdentificationPG");
              itemIdentificationPG.setItemIdentification(book.getIsbn());
              prototype.setItemIdentificationPG(itemIdentificationPG);
              prototype = WarehouseUtils.createBookAssetFromBookModel(book,
                  getPlanningFactory());
              if (logging.isDebugEnabled()) {
                logging.debug("Caching prototype "
                  + itemIdentificationPG.getItemIdentification() + " under"
                  + BolSocietyUtils.OBJECT_PROTOTYPE);
              }

              this.prototypeRegistryService.cachePrototype(book.getIsbn(),
                prototype);


            }

            bookAsset = (BookAsset) getPlanningFactory()
                                      .createInstance(book.getIsbn(),
                book.getIsbn()).getPrototype();
            NewItemIdentificationPG itemIdentificationPG = (NewItemIdentificationPG) getPlanningFactory()
                                                                                       .createPropertyGroup("ItemIdentificationPG");
            itemIdentificationPG.setItemIdentification(book.getIsbn());
            bookAsset.setItemIdentificationPG(itemIdentificationPG);

            bookAsset.createAggregate(numberAvailable);
            books.add(bookAsset);

          }
        } catch (SQLException sqlex) {
          if (logging.isErrorEnabled()) {
            logging.error("Error checking inventory for books", sqlex);
          }
        }
      }

      //update the ship task to ship only numberofBooksAvailable
      Enumeration workflowTasks = task.getWorkflow().getTasks();
      while (workflowTasks.hasMoreElements()) {
        NewTask wft = (NewTask) workflowTasks.nextElement();
        if (wft.getVerb().toString().equals(BolSocietyUtils.SHIPPER_VERB)) {
          // lose 90% for every book we can't ship, if we can't ship all the books we fail
          ScoringFunction numBksScorefcn = ScoringFunction
            .createStrictlyAtValue(AspectValue.newAspectValue(
                AspectType.QUANTITY, numberOfBooksAvailable));

          // this counts 70% towards completion if we can't deliver them then we've failed completely
          Preference numBksPref = getPlanningFactory().newPreference(AspectType.QUANTITY,
              numBksScorefcn, .70);
          wft.setPreference(numBksPref);
          getBlackboardService().publishChange(wft.getWorkflow());
        }
      }

      if (numberOfBooksAvailable > 0) {
        //allocate to packer
        allocateToPacker(numberOfBooksAvailable, task, books);
      }


      if (!haveEnough) {
        createDropShipment(task, books, dropshipOrderString);
      }
    }
  }


  /**
   * Create drop shipment
   *
   * @param task
   * @param books
   * @param dropshipOrderString
   */
  private void createDropShipment(Task task, Vector books,
    String dropshipOrderString) {
    if (logging.isDebugEnabled()) {
      logging.debug("Creating drop shipments for books that are not in stock");
    }


    //Attach drop shipment task to parent of task.
    Task parentTask = null;
    Collection tasks = blackboard.query(parentUIDPredicate(
          task.getParentTaskUID()));
    Iterator iterator = tasks.iterator();
    if (iterator.hasNext()) {
      parentTask = (Task) iterator.next();
    } else {
      if (logging.isErrorEnabled()) {
        logging.error("Can't find parent task of " + task);
      }

      return;
    }

    NewWorkflow nwf = (NewWorkflow) task.getWorkflow();

    Vector bookVector = new Vector();

    // order the rest direct from the publisher
    StringTokenizer tokenizer = new StringTokenizer(dropshipOrderString, ";");
    while (tokenizer.hasMoreTokens()) {
      String token = tokenizer.nextToken();
      int colpos = token.indexOf(":");
      String bookISBN = token.substring(0, colpos);
      int numOrdered = Integer.parseInt(token.substring(colpos + 1,
            token.length()));
      BookAsset book = null;
      for (int i = 0; i < books.size(); i++) {
        BookAsset _bookModel = (BookAsset) books.get(i);
        if (_bookModel.getItemIdentificationPG().getItemIdentification().equals(bookISBN)) {
          book = _bookModel;
          break;
        }
      }

      bookVector.add(new BolBookOrder(book.getItemIdentificationPG()
                                          .getItemIdentification(),
          book.getOverviewPG().getTitle(), numOrdered,
          (book.getPricePG().getOurPrice() * numOrdered)));
    }

    String ccInfo = new String(); // these books are paid for, no need for CC info
    String shippingMethod = (String) parentTask.getPrepositionalPhrase(BolSocietyUtils.SHIPMETHOD_PREPOSITION)
                                               .getIndirectObject();
    UserDetails userdetails = (UserDetails) parentTask.getPrepositionalPhrase(BolSocietyUtils.USERDETAIL_PREPOSITION)
                                                      .getIndirectObject();

    NewTask dropShipTask = (NewTask) BolSocietyUtils.createOrderTask(getPlanningFactory(),
        bookVector, ccInfo, userdetails, shippingMethod,
        PublisherConstants.ORDER_VERB);

    nwf.addTask(dropShipTask);
    dropShipTask.setParentTask(parentTask);
    dropShipTask.setWorkflow(nwf);
    NewPrepositionalPhrase workflowPP = getPlanningFactory()
                                          .newPrepositionalPhrase();
    workflowPP.setPreposition(BolSocietyUtils.GENERIC_WORKFLOW_PREPOSITION);
    dropShipTask.addPrepositionalPhrase(workflowPP);
    getBlackboardService().publishChange(parentTask.getPlanElement());
    getBlackboardService().publishChange(nwf);
    getBlackboardService().publishAdd(dropShipTask);
  }


  /**
   * Find a Task based on UID
   *
   * @param parentUID UID of a parent Task
   *
   * @return UnaryPredicate
   */
  private UnaryPredicate parentUIDPredicate(final UID parentUID) {
    return new UnaryPredicate() {
        public boolean execute(Object o) {
          if (o instanceof Task) {
            return ((Task) o).getUID().equals(parentUID);
          }

          return false;
        }
      };
  }


  /**
   * Allocate to packer
   *
   * @param numberAvailable
   * @param task
   * @param books
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
    AssetGroup booksAsset = (AssetGroup) getPlanningFactory().createInstance(new AssetGroup());
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
      BolSocietyUtils.PACK_DATE_ASPECT, BolSocietyUtils.DROP_SHIP_DATE_ASPECT,
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
      logging.debug("WarehouseAllocator: projected pack date is: " + yyyymmdd);
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
   * <b>Description</b>: Find the packer who currently is the least scheduled. <br>
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
      Enumeration numScheds = asset.getRoleSchedule().getRoleScheduleElements();

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


  /**
   * <b>Description</b>: Looks at the Plugin parameters for the packerTime
   * value.
   */
  private void parseParameters() {
    // Look through the Plugin parameters for the packer time
    Vector pVec = new Vector(getParameters());
    if (pVec.size() > 0) {
      String assets = (String) pVec.elementAt(0);
      int intTime = Integer.parseInt(assets);
      packerTime = (double) intTime;
    }
  }
}
