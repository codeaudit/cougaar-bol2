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


import org.cougaar.core.blackboard.IncrementalSubscription;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.AllocationResultAggregator;
import org.cougaar.planning.ldm.plan.AspectType;
import org.cougaar.planning.ldm.plan.AspectValue;
import org.cougaar.planning.ldm.plan.Constraint;
import org.cougaar.planning.ldm.plan.Expansion;
import org.cougaar.planning.ldm.plan.NewConstraint;
import org.cougaar.planning.ldm.plan.NewPrepositionalPhrase;
import org.cougaar.planning.ldm.plan.NewTask;
import org.cougaar.planning.ldm.plan.NewWorkflow;
import org.cougaar.planning.ldm.plan.Preference;
import org.cougaar.planning.ldm.plan.PrepositionalPhrase;
import org.cougaar.planning.ldm.plan.ScoringFunction;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.planning.ldm.plan.Verb;
import org.cougaar.tutorial.booksonline.common.BOLComponentPlugin;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;
import org.cougaar.tutorial.booksonline.util.UserDetails;
import org.cougaar.util.UnaryPredicate;

import java.sql.Date;

import java.util.Enumeration;
import java.util.Vector;


/**
 * Expands the boosFromWareHouse task from the OrderManager into the
 *
 * @author ttschampel
 */
public class WarehouseExpanderPlugin extends BOLComponentPlugin {
  private static final String pluginName = "WarehouseExanderPlugin";
  /** Subscription to booksfromwarehouse task */
  private IncrementalSubscription booksFromWarehouseSubs = null;
  /** Predicate for booksforwarehouse task */
  private UnaryPredicate booksFromWarehousePred = new UnaryPredicate() {
      public boolean execute(Object o) {
        if (o instanceof Task) {
          Task t = (Task) o;
          return t.getVerb().toString().equals(BolSocietyUtils.WAREHOUSE_VERB);

        }

        return false;
      }
    };

  /**
   * Set up subscription to the books from warehouse task
   */
  protected void setupSubscriptions() {
    booksFromWarehouseSubs = (IncrementalSubscription) getBlackboardService()
                                                         .subscribe(booksFromWarehousePred);


  }


  /**
   * Execute Subscriptions
   */
  protected void execute() {
    if (logging.isDebugEnabled()) {
      logging.debug(pluginName + " executing");
    }

    checkBooksFromWarehouse();
  }


  /**
   * Check for tasks from order manager to pack books
   */
  private void checkBooksFromWarehouse() {
    Enumeration enumeration = booksFromWarehouseSubs.getAddedList();
    while (enumeration.hasMoreElements()) {
      Task parentTask = (Task) enumeration.nextElement();
      if (logging.isDebugEnabled()) {
        logging.debug("Received book from warehouse task");
      }

      NewWorkflow new_wf = getPlanningFactory().newWorkflow();
      new_wf.setAllocationResultAggregator(AllocationResultAggregator.DEFAULT);
      new_wf.setParentTask(parentTask);

      // create allocation of task to warehouse (warehouse allocates shipping task so pass shipping info as well)
      PrepositionalPhrase isbnPP = parentTask.getPrepositionalPhrase(BolSocietyUtils.ISBN_PREPOSITION);
      int numBooks = (int) parentTask.getPreferredValue(AspectType.QUANTITY);
      Task packTask = createPackTask((String) isbnPP.getIndirectObject(),
          numBooks);
      new_wf.addTask(packTask);
      ((NewTask) packTask).setWorkflow(new_wf);
      ((NewTask) packTask).setParentTask(parentTask);

      //publish unconstrained task
      getBlackboardService().publishAdd(packTask);


      // create allocation of task to shipper
      PrepositionalPhrase methodPP = parentTask.getPrepositionalPhrase(BolSocietyUtils.SHIPMETHOD_PREPOSITION);
      PrepositionalPhrase userPP = parentTask.getPrepositionalPhrase(BolSocietyUtils.USERDETAIL_PREPOSITION);
      Task shipTask = createShippingTask((String) methodPP.getIndirectObject(),
          (UserDetails) userPP.getIndirectObject(), numBooks);
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

      getBlackboardService().publishAdd(new_wf);
      getBlackboardService().publishAdd(new_exp);
    }
  }


  /**
   * Create pack task
   *
   * @param isbn
   * @param quantity
   *
   * @return
   */
  private Task createPackTask(String isbn, int quantity) {
    NewTask task = getPlanningFactory().newTask();

    task.setDirectObject(null);
    task.setVerb(new Verb(BolSocietyUtils.WAREHOUSEPACK_VERB));

    // add the isbn preposition
    Vector preps = new Vector();
    NewPrepositionalPhrase npp = getPlanningFactory().newPrepositionalPhrase();
    npp.setPreposition(BolSocietyUtils.ISBN_PREPOSITION);
    npp.setIndirectObject(isbn);
    preps.add(npp);
    NewPrepositionalPhrase workflowPP = getPlanningFactory()
                                          .newPrepositionalPhrase();
    workflowPP.setPreposition(BolSocietyUtils.GENERIC_WORKFLOW_PREPOSITION);
    preps.add(workflowPP);
    task.setPrepositionalPhrases(preps.elements());
    task.setPlan(getPlanningFactory().getRealityPlan());

    // add the requested quantity as a preference, only the exact amount is preferred
    Vector newPreferences = new Vector();

    // lose 25% for every day we miss, after 4 days it's too late
    ScoringFunction scorefcn = ScoringFunction.createPreferredAtValue(AspectValue
        .newAspectValue(AspectType.QUANTITY, quantity), .95);

    // this counts 33% towards the total completion ability, need to find the book and ship it too, all are equally important
    Preference pref = getPlanningFactory().newPreference(AspectType.QUANTITY,
        scorefcn, 1.0);

    newPreferences.addElement(pref);

    // show final completion (i.e. the performance of this task is now in the past)
    ScoringFunction complScorefcn = ScoringFunction.createPreferredAtValue(AspectValue
        .newAspectValue(BolSocietyUtils.COMPLETED_ASPECT,
          BolSocietyUtils.ISCOMPLETED), 1.0);


    // this counts 0 against the preference score, since it indicates final completion of the task
    // it isn't used to show how well planned the task is
    Preference complPref = getPlanningFactory().newPreference(BolSocietyUtils.COMPLETED_ASPECT,
        complScorefcn, 0.0);

    newPreferences.addElement(complPref);


    task.setPreferences(newPreferences.elements());

    return task;
  }


  /**
   * Create Shipping Task
   *
   * @param shippingMethod Method of shipping
   * @param ud User Details
   * @param numBooks Number of books to ship
   *
   * @return Shipping Task
   */
  private Task createShippingTask(String shippingMethod, UserDetails ud,
    int numBooks) {
    NewTask task = getPlanningFactory().newTask();

    task.setDirectObject(null);
    task.setVerb(new Verb(BolSocietyUtils.SHIPPER_VERB));
    Vector preps = new Vector();
    NewPrepositionalPhrase npp = null;

    npp = getPlanningFactory().newPrepositionalPhrase();
    npp.setPreposition(BolSocietyUtils.USERDETAIL_PREPOSITION);
    npp.setIndirectObject(ud);
    preps.add(npp);

    npp = getPlanningFactory().newPrepositionalPhrase();
    npp.setPreposition(BolSocietyUtils.SHIPMETHOD_PREPOSITION);
    npp.setIndirectObject(shippingMethod);
    preps.add(npp);
    NewPrepositionalPhrase workflowPP = getPlanningFactory()
                                          .newPrepositionalPhrase();
    workflowPP.setPreposition(BolSocietyUtils.GENERIC_WORKFLOW_PREPOSITION);
    preps.add(workflowPP);
    task.setPrepositionalPhrases(preps.elements());

    task.setPlan(getPlanningFactory().getRealityPlan());

    // add the desired "hold money until" day as a preference, holding it less than the time required is ok
    Vector newPreferences = new Vector();


    // Make the scoreing function return a value of 1.0 (failed) at 20 days (converted into milliseconds) from the current
    // time since the credit card is no longer on hold for us after 20 days
    long milliseconds20days = 20 * 24 * 60 * 60 * 1000;
    ScoringFunction scorefcn = ScoringFunction.createNearOrAbove(AspectValue
        .newAspectValue(AspectType.END_TIME, (double) currentTimeMillis()),
        1.0 / (double) milliseconds20days);

    if (logging.isDebugEnabled()) {
      logging.debug(
        "WarehouseExpander: CreateShippingTask: Requesting shipping before: "
        + new Date(currentTimeMillis() + milliseconds20days));
    }

    // this counts 30% towards the total completion ability, if we can't get it there on time that's bad but not fatal
    Preference pref = getPlanningFactory().newPreference(AspectType.END_TIME,
        scorefcn, .3);

    newPreferences.addElement(pref);

    // lose 90% for every book we can't ship, if we can't ship all the books we fail
    ScoringFunction numBksScorefcn = ScoringFunction.createStrictlyAtValue(AspectValue
        .newAspectValue(AspectType.QUANTITY, numBooks));

    // this counts 70% towards completion if we can't deliver them then we've failed completely
    Preference numBksPref = getPlanningFactory().newPreference(AspectType.QUANTITY,
        numBksScorefcn, .70);

    newPreferences.addElement(numBksPref);


    // show final completion (i.e. the performance of this task is now in the past)
    ScoringFunction complScorefcn = ScoringFunction.createPreferredAtValue(AspectValue
        .newAspectValue(BolSocietyUtils.COMPLETED_ASPECT,
          BolSocietyUtils.ISCOMPLETED), 1.0);


    // this counts 0 against the preference score, since it indicates final completion of the task
    // it isn't used to show how well planned the task is
    Preference complPref = getPlanningFactory().newPreference(BolSocietyUtils.COMPLETED_ASPECT,
        complScorefcn, 0.0);

    newPreferences.addElement(complPref);

    task.setPreferences(newPreferences.elements());

    return task;
  }
}
