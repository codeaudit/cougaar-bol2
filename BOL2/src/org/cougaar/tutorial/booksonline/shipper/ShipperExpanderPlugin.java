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


package org.cougaar.tutorial.booksonline.shipper;


import org.cougaar.core.blackboard.IncrementalSubscription;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.AllocationResultAggregator;
import org.cougaar.planning.ldm.plan.AspectType;
import org.cougaar.planning.ldm.plan.AspectValue;
import org.cougaar.planning.ldm.plan.ClusterObjectFactory;
import org.cougaar.planning.ldm.plan.Expansion;
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

import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Vector;


/**
 * Expands SHIP_BOOKS tasks
 *
 * @author mabrams
 */
public class ShipperExpanderPlugin extends BOLComponentPlugin {
  private static UnaryPredicate shipBooksPredicate = new UnaryPredicate() {
      public boolean execute(Object o) {
        if (o instanceof Task) {
          Task t = (Task) o;
          return t.getVerb().equals(Verb.getVerb(BolSocietyUtils.SHIPPER_VERB));
        }

        return false;
      }
    };

  private String pluginName = "ShipperExpanderPlugin";
  private IncrementalSubscription shipBooksSubscription;


  /* (non-Javadoc)
   * @see org.cougaar.tutorial.booksonline.common.BOLComponentPlugin#setupSubscriptions()
   */
  protected void setupSubscriptions() {
    shipBooksSubscription = (IncrementalSubscription) getBlackboardService()
                                                        .subscribe(shipBooksPredicate);
  }


  /* (non-Javadoc)
   * @see org.cougaar.tutorial.booksonline.common.BOLComponentPlugin#execute()
   */
  protected void execute() {
    if (logging.isDebugEnabled()) {
      logging.debug(pluginName + " execute");
    }

    checkShipTasks();

  }


  /**
   * Check for new ship books tasks and expand any that are found
   */
  private void checkShipTasks() {
    Enumeration e = shipBooksSubscription.getAddedList();
    while (e.hasMoreElements()) {
      Task task = (Task) e.nextElement();

      NewWorkflow new_wf = getPlanningFactory().newWorkflow();
      new_wf.setAllocationResultAggregator(AllocationResultAggregator.DEFAULT);
      new_wf.setParentTask(task);


      PrepositionalPhrase userpp = task.getPrepositionalPhrase(BolSocietyUtils.USERDETAIL_PREPOSITION);
      UserDetails ud = (UserDetails) userpp.getIndirectObject();
      GISBehavior gb = (GISBehavior) task.getDirectObject();

      PrepositionalPhrase methodpp = task.getPrepositionalPhrase(BolSocietyUtils.SHIPMETHOD_PREPOSITION);
      String shipMethod = (String) methodpp.getIndirectObject();
      int quantity = (int) task.getPreferredValue(AspectType.QUANTITY);

      GregorianCalendar today = new GregorianCalendar();
      today.add(Calendar.DATE, 3); // project ship date 3 days from now
      double shipBy = (double) today.getTime().getTime();

      long yyyymmdd = today.get(Calendar.YEAR) * 10000;
      yyyymmdd += ((today.get(Calendar.MONTH) + 1) * 100);
      yyyymmdd += today.get(Calendar.DATE);

      int[] aspect_types = {
        AspectType.START_TIME, BolSocietyUtils.SHIP_DATE_ASPECT,
        BolSocietyUtils.COMPLETED_ASPECT
      };

      double[] results = {
        currentTimeMillis(), shipBy, BolSocietyUtils.ISCOMPLETED
      };

      Task routeTask = createRouteTask(getPlanningFactory(), ud, shipMethod,
          quantity, gb);
      ((NewTask) routeTask).setWorkflow(new_wf);
      ((NewTask) routeTask).setParentTask(task);
      new_wf.addTask(routeTask);

      AllocationResult estAR = null;
      Expansion new_exp = getPlanningFactory().createExpansion(task.getPlan(),
          task, new_wf, estAR);

      //  publish the "route" task
      getBlackboardService().publishAdd(routeTask);
      getBlackboardService().publishAdd(new_wf);
      getBlackboardService().publishAdd(new_exp);

    }
  }


  /**
   * Creates a Route Task
   *
   * @param theCOF
   * @param ud
   * @param shippingMethod
   * @param quantity
   * @param gisb
   *
   * @return
   */
  public Task createRouteTask(ClusterObjectFactory theCOF, UserDetails ud,
    String shippingMethod, int quantity, GISBehavior gisb) {
    NewTask task = theCOF.newTask();
    task.setDirectObject(gisb);

    task.setVerb(new Verb(BolSocietyUtils.SHIP_ROUTE_VERB));

    Vector preps = new Vector();
    NewPrepositionalPhrase usernpp = BolSocietyUtils.createPrepPhrase(ud,
        BolSocietyUtils.USERDETAIL_PREPOSITION, theCOF);
    preps.add(usernpp);

    NewPrepositionalPhrase npp = BolSocietyUtils.createPrepPhrase(shippingMethod,
        BolSocietyUtils.SHIPMETHOD_PREPOSITION, theCOF);
    preps.add(npp);

    NewPrepositionalPhrase workflowPP = theCOF.newPrepositionalPhrase();
    workflowPP.setPreposition(BolSocietyUtils.GENERIC_WORKFLOW_PREPOSITION);
    preps.add(workflowPP);

    task.setPrepositionalPhrases(preps.elements());
    task.setPlan(theCOF.getRealityPlan());
    GregorianCalendar calToday = new GregorianCalendar();
    calToday.add(GregorianCalendar.DATE, 20);
    double shipBeforeDay = (double) calToday.get(Calendar.YEAR) * 10000;
    shipBeforeDay += ((double) calToday.get(Calendar.MONTH + 1) * 100);
    shipBeforeDay += (double) calToday.get(Calendar.DAY_OF_MONTH);

    // add the desired "hold money until" day as a preference, holding it less than the time required is ok
    Vector newPreferences = new Vector();

    // lose 5% for every day we miss, after 20 days it's too late, the credit card is no longer on hold for us
    ScoringFunction scorefcn = ScoringFunction.createPreferredAtValue(AspectValue
        .newAspectValue(AspectType.END_TIME, shipBeforeDay), .05);

    // this counts 33% towards the total completion ability, need to find the book and ship it too, all are equally important
    Preference pref = theCOF.newPreference(AspectType.END_TIME, scorefcn, 1.0);
    newPreferences.addElement(pref);

    // show final completion (i.e. the performance of this task is now in the past)
    ScoringFunction complScorefcn = ScoringFunction.createPreferredAtValue(AspectValue
        .newAspectValue(BolSocietyUtils.COMPLETED_ASPECT,
          BolSocietyUtils.ISCOMPLETED), 1.0);


    // this counts 0 against the preference score, since it indicates final completion of the task
    // it isn't used to show how well planned the task is
    Preference complPref = theCOF.newPreference(BolSocietyUtils.COMPLETED_ASPECT,
        complScorefcn, 0.0);
    newPreferences.addElement(complPref);
    ScoringFunction scorequan = ScoringFunction.createPreferredAtValue(AspectValue
        .newAspectValue(AspectType.QUANTITY, quantity), .95);

    // this counts 33% towards the total completion ability, need to find the book and ship it too, all are equally important
    Preference quanpref = theCOF.newPreference(AspectType.QUANTITY, scorequan,
        0.333);
    newPreferences.addElement(quanpref);
    task.setPreferences(newPreferences.elements());

    return task;
  }
}
