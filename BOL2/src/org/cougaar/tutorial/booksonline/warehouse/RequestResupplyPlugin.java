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
import org.cougaar.planning.ldm.plan.NewPrepositionalPhrase;
import org.cougaar.planning.ldm.plan.NewTask;
import org.cougaar.planning.ldm.plan.PlanElement;
import org.cougaar.planning.ldm.plan.PrepositionalPhrase;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.planning.ldm.plan.Verb;
import org.cougaar.tutorial.booksonline.assets.WarehouseAsset;
import org.cougaar.tutorial.booksonline.common.BOLComponentPlugin;
import org.cougaar.tutorial.booksonline.publisher.PublisherConstants;
import org.cougaar.tutorial.booksonline.util.BolBookOrder;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;
import org.cougaar.tutorial.booksonline.util.BookUtil;
import org.cougaar.tutorial.booksonline.util.UserDetails;
import org.cougaar.tutorial.booksonline.web.model.BookModel;
import org.cougaar.util.UnaryPredicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;


/**
 * Requests books from publisher
 *
 * @author ttschampel
 */
public class RequestResupplyPlugin extends BOLComponentPlugin {
  /** Plugin name */
  private static final String pluginName = "RequestResupplyPlugin";
  /** Prep. phrase constant for update inventory tasks */
  private static final String UPDATE_INVENTORY_PHRASE = "FOR_REPLENISH_WAREHOUSE_INVENTORY";
  /** Predicate for warn inventory tasks */
  private IncrementalSubscription warnInventorySubs = null;
  /** Predicate for warn inventory tasks */
  private UnaryPredicate warnInventoryPred = new UnaryPredicate() {
      public boolean execute(Object o) {
        if (o instanceof Task) {
          Task t = (Task) o;
          return t.getVerb().toString().equals(BolSocietyUtils.WARN_INVENTORY_VERB);
        }

        return false;
      }
    };

  /** Predicate for warehouse asset */
  private UnaryPredicate warehousePredicate = new UnaryPredicate() {
      public boolean execute(Object o) {
        return o instanceof WarehouseAsset;
      }
    };

  /** DatabaseService */
  private DatabaseService dbService = null;
  /** Subscription to completed update inventory tasks */
  private IncrementalSubscription updateInvSubs;
  /** Predicate for complete inventory tasks */
  private UnaryPredicate updateInvPredicate = new UnaryPredicate() {
      public boolean execute(Object o) {
        if (o instanceof PlanElement) {
          PlanElement pe = (PlanElement) o;
          Task t = pe.getTask();
          return (t.getVerb().equals(Verb.getVerb(PublisherConstants.ORDER_VERB))
          && (t.getPrepositionalPhrase(UPDATE_INVENTORY_PHRASE) != null) && pe.getReportedResult()!=null
          	&& pe.getReportedResult().isSuccess());
        }

        return false;
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


  }


  /**
   * setup subscription to warn inventory level
   */
  public void setupSubscriptions() {
    warnInventorySubs = (IncrementalSubscription) getBlackboardService()
                                                    .subscribe(warnInventoryPred);
    updateInvSubs = (IncrementalSubscription) getBlackboardService().subscribe(updateInvPredicate);
  }


  /**
   * Process subscriptions
   */
  public void execute() {
    if (logging.isDebugEnabled()) {
      logging.debug(pluginName + " executing");
    }

    checkForNewWarnings();
    checkCompletedInvTasks();
  }


  /**
   * check for completed inventory update tasks and update inventory
   * accordingly.
   */
  private void checkCompletedInvTasks() {
    Enumeration enumeration = updateInvSubs.getChangedList();
    while (enumeration.hasMoreElements()) {
      PlanElement pe = (PlanElement) enumeration.nextElement();
      Task t = pe.getTask();
      PrepositionalPhrase isbnPhrase = t.getPrepositionalPhrase(BolSocietyUtils.ISBN_PREPOSITION);
      String isbn = (String) isbnPhrase.getIndirectObject();
      int qty = Integer.parseInt(isbn.substring(isbn.indexOf(":") + 1,
            isbn.length()));
      isbn = isbn.substring(0, isbn.indexOf(":"));
      if (logging.isDebugEnabled()) {
        logging.debug("Got inventory from publsiher for " + isbn + "(" + qty
          + ")");

      }
		getBlackboardService().publishRemove(t);
      try {
        Map parameters = new HashMap();
        parameters.put(BolSocietyUtils.Database.BOOK_ISBN_PARAMETER, isbn);

        ArrayList results = (ArrayList) dbService.executeQuery(BolSocietyUtils.Database.GET_BOOK_BY_ISBN_QUERY_NAME,
            parameters);

        if (results.size() > 0) {
          Object[] objects = (Object[]) results.get(0);
          BookModel book = BookUtil.getBookModelFromDatabase(objects);
          int currentInventoryLevel = book.getShelf();

          parameters = new HashMap();
          parameters.put(BolSocietyUtils.Database.BOOK_ISBN_PARAMETER, isbn);
          parameters.put(BolSocietyUtils.Database.INVENTORY_LEVEL_PARAMETER,
            "" + (currentInventoryLevel + qty));
          dbService.executeStatement(BolSocietyUtils.Database.UPDATE_INVENTORY_QUERY_NAME,
            parameters);

        }
      } catch (Exception e) {
        if (logging.isErrorEnabled()) {
          logging.error("Error updating inventory", e);
        }
      }
    }
  }


  /**
   * Check for new warnings
   */
  private void checkForNewWarnings() {
    Enumeration enumeration = warnInventorySubs.getAddedList();
    while (enumeration.hasMoreElements()) {
      Task task = (Task) enumeration.nextElement();
      PrepositionalPhrase isbnPhrase = task.getPrepositionalPhrase(BolSocietyUtils.WARN_INVENTORY_ISBN_PHRASE);
      PrepositionalPhrase costPhrase = task.getPrepositionalPhrase(BolSocietyUtils.WARN_INVENTORY_COST_PHRASE);
      PrepositionalPhrase titlePhrase = task.getPrepositionalPhrase(BolSocietyUtils.WARN_INVENTORY_TITLE_PHRASE);
      float cost = ((Float) costPhrase.getIndirectObject()).floatValue();
      String isbn = (String) isbnPhrase.getIndirectObject();
      String title = (String) titlePhrase.getIndirectObject();

      UserDetails warehouseLocation = null;
      WarehouseAsset warehouse = null;
      Collection warehouseList = getBlackboardService().query(warehousePredicate);
      Iterator warehouseIter = warehouseList.iterator();
      if (warehouseIter.hasNext()) {
        warehouse = (WarehouseAsset) warehouseIter.next();
        warehouseLocation = new UserDetails();
        warehouseLocation.address1 = warehouse.getWareHouseDescriptionPG()
                                              .getAddress1();
        warehouseLocation.address2 = warehouse.getWareHouseDescriptionPG()
                                              .getAddress2();
        warehouseLocation.city = warehouse.getWareHouseDescriptionPG().getCity();
        warehouseLocation.state = warehouse.getWareHouseDescriptionPG()
                                           .getState();
        warehouseLocation.zip = warehouse.getWareHouseDescriptionPG()
                                         .getZipCode();
        warehouseLocation.ccExpDate = warehouse.getWareHouseDescriptionPG()
                                               .getCcExpDate();
        warehouseLocation.ccNumber = ""
          + warehouse.getWareHouseDescriptionPG().getCcNumber();
      } else {
        if (logging.isErrorEnabled()) {
          logging.error("No warehouse asset found!");
        }
      }

      String shippingMethod = "Next Day Air";
      boolean alreadyWarned = false;
      Collection currentWarnings = getBlackboardService().query(warnInventoryPred);
      Iterator iter = currentWarnings.iterator();
      while (iter.hasNext()) {
        Task t = (Task) iter.next();
        String _isbn = (String) t.getPrepositionalPhrase(BolSocietyUtils.WARN_INVENTORY_ISBN_PHRASE)
                                 .getIndirectObject();
        if (!(_isbn.equals(isbn))) {
          alreadyWarned = true;
          break;
        }
      }

      if (alreadyWarned) {
        getBlackboardService().publishRemove(task);
      } else {
        //Create replenish book task for the Publisher
        NewTask replenishTask = getPlanningFactory().newTask();
        Vector bookVector = new Vector();
        bookVector.add(new BolBookOrder(isbn, title, 1000, (cost * 1000)));
        NewTask getBooksTask = (NewTask) BolSocietyUtils.createOrderTask(getPlanningFactory(),
            bookVector, "warehouse CC number", warehouseLocation,
            shippingMethod, PublisherConstants.ORDER_VERB);
        NewPrepositionalPhrase workflowPP = getPlanningFactory()
                                              .newPrepositionalPhrase();
        workflowPP.setPreposition(BolSocietyUtils.GENERIC_WORKFLOW_PREPOSITION);
        getBooksTask.addPrepositionalPhrase(workflowPP);

        NewPrepositionalPhrase replenishPP = getPlanningFactory()
                                               .newPrepositionalPhrase();
        replenishPP.setPreposition(UPDATE_INVENTORY_PHRASE);
        replenishPP.setIndirectObject("TRUE");
        getBooksTask.addPrepositionalPhrase(replenishPP);


        getBlackboardService().publishAdd(getBooksTask);
      }
    }
  }
}
