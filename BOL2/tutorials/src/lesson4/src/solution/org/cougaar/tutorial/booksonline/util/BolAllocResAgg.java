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


package org.cougaar.tutorial.booksonline.util;


import org.cougaar.planning.ldm.asset.Asset;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.AllocationResultAggregator;
import org.cougaar.planning.ldm.plan.AspectValue;
import org.cougaar.planning.ldm.plan.AuxiliaryQueryType;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.planning.ldm.plan.TaskScoreTable;
import org.cougaar.planning.ldm.plan.TypedQuantityAspectValue;
import org.cougaar.planning.ldm.plan.Workflow;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;


/**
 * Simple Aggregator for AllocationResults specified with AspectValues.
 * Particularly useful for results with TypedQuantityAspects. Does the right
 * computation for workflows which are made up of equally important tasks with
 * no inter-task constraints. START_TIME is minimized. END_TIME is maximized.
 * DURATION is overall END_TIME - overall START_TIME. COST is summed. DANGER
 * is maximized. RISK is maximized. QUANTITY is summed. INTERVAL is summed.
 * TOTAL_QUANTITY is summed. TOTAL_SHIPMENTS is summed. CUSTOMER_SATISFACTION
 * is averaged. TYPED_QUANTITY is summed for each type(asset). For
 * AuxiliaryQuery information, if all the query values are the same across
 * subtasks or one subtask has query info it will be place in the  aggregate
 * result.  However, if there are conflicting query values, no information
 * will be put in the aggregated result. returns null when there are no
 * subtasks or any task has no result.
 */
public class BolAllocResAgg implements AllocationResultAggregator {
  /**
   * Creates a new BolAllocResAgg object.
   */
  public BolAllocResAgg() {
  }

  /**
   * Calculate allocation result
   *
   * @param wf Workflow
   * @param tst Task Score Table
   * @param currentar Allocation Result
   *
   * @return Allocation Result
   */
  public AllocationResult calculate(Workflow wf, TaskScoreTable tst,
    AllocationResult currentar) {
    List aggregAR = new ArrayList();
    boolean suc = true;
    double rating = 0.0;
    int count = 0;

    Enumeration tasks = wf.getTasks();
    if ((tasks == null) || (!tasks.hasMoreElements())) {
      return null;
    }

    if (currentar == null) {
      return null; // don't know what to return
    }


    String[] auxqsummary = new String[AuxiliaryQueryType.AQTYPE_COUNT];

    // initialize all values to UNDEFINED for comparison purposes below.
    final String UNDEFINED = "UNDEFINED";
    for (int aqs = 0; aqs < auxqsummary.length; aqs++) {
      auxqsummary[aqs] = UNDEFINED;
    }

    //   BolSocietyUtils.out.println ("BolAllocResAgg:calculate - aux query string inited");
    boolean canBeComplete = true;
    while (tasks.hasMoreElements()) {
      Task t = (Task) tasks.nextElement();
      count++;

      // get the allocation result passed in for the task
      AllocationResult ar = tst.getAllocationResult(t);
      if (ar == null) {
        //          BolSocietyUtils.out.println ("BolAllocResAgg:calculate - processing allocation result, NULL");
        canBeComplete = false;
        suc = false; // can't be successful
        continue; // we build what we can and skip the undefined onesf
      }

      //        BolSocietyUtils.out.println ("BolAllocResAgg:calculate - processing allocation result, not null");
      suc = suc && ar.isSuccess(); // if any sub-task fails the whole thing is a failure

      rating += ar.getConfidenceRating(); // summed here then divided by "count" to get an average rating for the parent

      // handle the Aspect Values of this Allocation Result as an array
      AspectValue[] someresults = ar.getAspectValueResults();

      // process each type of value into our conflated result
      for (int b = 0; b < someresults.length; b++) {
        //          BolSocietyUtils.out.println ("BolAllocResAgg:calculate - processing av index: " + b);
        AspectValue sumav = null;

        AspectValue srav = someresults[b];

        //          BolSocietyUtils.out.println ("BolAllocResAgg:calculate - processing aspect value is: " + srav.getValue());
        int thisat = srav.getAspectType();

        for (ListIterator lit = aggregAR.listIterator(); lit.hasNext();) {
          AspectValue thisav = (AspectValue) lit.next();
          if (thisav.getAspectType() == thisat) {
            //              BolSocietyUtils.out.println ("BolAllocResAgg:calculate - matches existing type: " + thisat );
            if (thisat != TYPED_QUANTITY) {
              sumav = thisav;
            }
            else {
              // check the asset
              Asset tqasset = ((TypedQuantityAspectValue) thisav).getAsset();
              if (tqasset.equals(((TypedQuantityAspectValue) srav).getAsset())) {
                sumav = thisav;
              }
            }
          }
        }

        // if we still don't have a matching sum AspectValue, make a new one for this aspect
        if (sumav == null) {
          //            BolSocietyUtils.out.println ("BolAllocResAgg:calculate - type hasn't been processed before, adding it" );
          if (thisat != TYPED_QUANTITY) {
            sumav = AspectValue.newAspectValue(thisat, 0.0);
          }
          else {
            sumav = new TypedQuantityAspectValue(((TypedQuantityAspectValue) srav)
                .getAsset(), 0.0);
          }

          aggregAR.add(sumav);

        }

        // accumulate the values for the defined aspects
        if (thisat == START_TIME) {
          // if we just initialized this, use the new value
          if (sumav.getValue() != 0.0) {
            double nst = Math.min(sumav.getValue(), srav.getValue());
            sumav.dupAspectValue(nst);
          } else {
            sumav.dupAspectValue(srav.getValue());
          }
        }
        else if ((thisat == END_TIME) || (thisat == DANGER) || (thisat == RISK)) {
          double newmaxv = Math.max(sumav.getValue(), srav.getValue());
          sumav.dupAspectValue(newmaxv);
        }
        else if (thisat <= _LAST_ASPECT) {
          // if its a standard aspect type its a simple summation, even for TYPED_QUANTITY
          double newsumv = sumav.getValue() + srav.getValue();
          sumav.dupAspectValue(newsumv);
        }
        else {
          // if this is the completed aspect but we have an empty task slot then we can't be
          // complete
          if ((thisat == BolSocietyUtils.COMPLETED_ASPECT) && (!canBeComplete)) {
            sumav.dupAspectValue(BolSocietyUtils.ISNOTCOMPLETED);
          } else {
            // if its anything else (aka one of the BOL aspects) it's a straight copy
            sumav.dupAspectValue(srav.getValue());
          }
        }
      }
       // end of for loop for allocationresult aspect types


      // Sum up the auxiliaryquery data.  If there are conflicting data
      // values, send back nothing for that type.  If only one subtask
      // has information about a querytype, send it back in the
      // aggregated result.
      //        BolSocietyUtils.out.println ("BolAllocResAgg:calculate - summing up auxiliary query data" );
      for (int aq = 0; aq < AuxiliaryQueryType.AQTYPE_COUNT; aq++) {
        String data = ar.auxiliaryQuery(aq);
        if (data != null) {
          String sumdata = auxqsummary[aq];
          if (sumdata.equals(UNDEFINED)) {
            // there's not a value yet, so use this one.
            auxqsummary[aq] = data;
          } else if (!data.equals(sumdata)) {
            // there's a conflict, pass back null
            auxqsummary[aq] = null;
          }
        }
      }
    }
     // end of while looping through tasks.


    //    BolSocietyUtils.out.println ("BolAllocResAgg:calculate - calculating duration" );
    // now make AspectVales for DURATION and CUSTOMER_SATISFACTION
    double overallstart = 0.0;
    double overallend = 0.0;
    for (ListIterator l = aggregAR.listIterator(); l.hasNext();) {
      AspectValue timeav = (AspectValue) l.next();
      int timeavaspect = timeav.getAspectType();
      if (timeavaspect == START_TIME) {
        overallstart = timeav.getValue();
      } else if (timeavaspect == END_TIME) {
        overallend = timeav.getValue();
      }
    }

    // if the start time and end time are defined, find or create a duration aspectvalue
    AspectValue theduration = null;
    if ((overallstart != 0.0) && (overallend != 0.0)) {
      for (ListIterator litdur = aggregAR.listIterator(); litdur.hasNext();) {
        AspectValue durav = (AspectValue) litdur.next();
        if (DURATION == durav.getAspectType()) {
          theduration = durav;
          // reset the value
          durav.dupAspectValue(overallend - overallstart);
        }
      }

      // if we didn't find a duration av, create one
      if (theduration == null) {
        AspectValue duration = AspectValue.newAspectValue(DURATION,
            overallend - overallstart);
        aggregAR.add(duration);
      }
    }


    //      BolSocietyUtils.out.println ("BolAllocResAgg:calculate - calculating customer service");
    // if there is a CUSTOMER_SATISFACTION av, divide the total sum by the number of tasks
    for (ListIterator litcs = aggregAR.listIterator(); litcs.hasNext();) {
      AspectValue csav = (AspectValue) litcs.next();
      if (CUSTOMER_SATISFACTION == csav.getAspectType()) {
        csav.dupAspectValue(csav.getValue() / count);
      }
    }

    rating /= count;


    // now that we have processed all the results, we have to place them into the slots of the
    // parent allocation result block
    AspectValue[] currentavs = currentar.getAspectValueResults();

    AspectValue[] newavs = (AspectValue[]) aggregAR.toArray(new AspectValue[aggregAR
        .size()]);


    //      BolSocietyUtils.out.println ("BolAllocResAgg:calculate - transfering to parent" );
    for (int ii = 0; ii < currentavs.length; ii++) {
      int currType = currentavs[ii].getAspectType();

      for (int jj = 0; jj < newavs.length; jj++) {
        if (currType == newavs[jj].getAspectType()) {
          //              BolSocietyUtils.out.println ("BolAllocResAgg:calculate - assigning type: " + currType );
          // accumulate the values for the defined aspects
          if (currType == START_TIME) {
            // if we just initialized this, use the new value
            if (currentavs[ii].getValue() != 0.0) {
              double nst = Math.min(currentavs[ii].getValue(),
                  newavs[jj].getValue());
              currentavs[ii].dupAspectValue(nst);
            }
            else {
              currentavs[ii].dupAspectValue(newavs[jj].getValue());
            }
          }
          else if ((currType == END_TIME) || (currType == DANGER)
            || (currType == RISK)) {
            double newmaxv = Math.max(currentavs[ii].getValue(),
                newavs[jj].getValue());
            currentavs[ii].dupAspectValue(newmaxv);
          }
          else if (currType <= _LAST_ASPECT) {
            // if its a standard aspect type its a simple summation, even for TYPED_QUANTITY
            double newsumv = currentavs[ii].getValue() + newavs[jj].getValue();
            currentavs[ii].dupAspectValue(newsumv);
          } else {
            //              BolSocietyUtils.out.println ("BolAllocResAgg:calculate - assigning BOL aspect " + currType );
            //              BolSocietyUtils.out.println ("BolAllocResAgg:calculate - in value is: " + newavs[jj].getValue() );
            // if this is the completed aspect but we have an empty task slot then we can't be
            // complete
            if ((currType == BolSocietyUtils.COMPLETED_ASPECT)
              && (!canBeComplete)) {
              //               BolSocietyUtils.out.println (" SET TO NOT COMPLETED " );
              currentavs[ii].dupAspectValue(BolSocietyUtils.ISNOTCOMPLETED);
            } else {
              // if its anything else (aka one of the BOL aspects) it's a straight copy
              currentavs[ii].dupAspectValue(newavs[jj].getValue());
              //                BolSocietyUtils.out.println ("BolAllocResAgg:calculate - out value is: " + currentavs[ii].getValue() );
            }
          }
        }
         // end for jj
      }
       // end for ii
    }

    AllocationResult artoreturn = new AllocationResult(rating, suc, currentavs);

    for (int aqt = 0; aqt < auxqsummary.length; aqt++) {
      String aqdata = auxqsummary[aqt];
      if ((aqdata != null) && (aqdata != UNDEFINED)) {
        artoreturn.addAuxiliaryQueryInfo(aqt, aqdata);
      }
    }

    return artoreturn;

  }
}
