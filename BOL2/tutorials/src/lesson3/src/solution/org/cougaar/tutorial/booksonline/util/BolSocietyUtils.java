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


import org.cougaar.planning.ldm.PlanningFactory;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.AspectType;
import org.cougaar.planning.ldm.plan.AspectValue;
import org.cougaar.planning.ldm.plan.ClusterObjectFactory;
import org.cougaar.planning.ldm.plan.NewPrepositionalPhrase;
import org.cougaar.planning.ldm.plan.NewTask;
import org.cougaar.planning.ldm.plan.PlanElement;
import org.cougaar.planning.ldm.plan.Preference;
import org.cougaar.planning.ldm.plan.ScoringFunction;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.planning.ldm.plan.TaskScoreTable;
import org.cougaar.planning.ldm.plan.Verb;
import org.cougaar.planning.ldm.plan.Workflow;

import java.text.NumberFormat;

import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Vector;


/**
 * <b>Description</b>: Handy functions for working in all areas of the BOL
 * society.
 *
 * @author Every One, &copy;2000 Clark Software Engineering, Ltd. & Defense
 *         Advanced Research Projects Agency (DARPA)
 * @version 1.0
 */
public class BolSocietyUtils {
  // ---------------------------------------------------------------------------------------------------------------------
  // Definition Values
  // ---------------------------------------------------------------------------------------------------------------------
  /** <b>Description</b>: Verb for scheduling. */
  public static final String DUMMY_VERB = "Dummy";
  /**
   * <b>Description</b>: Verb for ordering book, subscribed to on the
   * OrderManager cluster.
   */
  public static final String ORDER_VERB = "ORDERBOOKS";
  /** <b>Description</b>: Verb for finding a book, used in the PSP. */
  public static final String SEARCH_VERB = "SEARCH";
  /**
   * <b>Description</b>: Verb for verifying credit limit, subscribed to on the
   * Payment cluster.
   */
  public static final String PAYMENT_VERB = "CLEARPAYMENT"; // verify the purchasers ability to get the money
  /**
   * <b>Description</b>: Verb for transfering money after shipment, subscribed
   * to on the Payment cluster.
   */
  public static final String FINAL_PAYMENT_VERB = "GETTHEMONEY";
  /**
   * <b>Description</b>: Verb for requesting books, subscribed to on the
   * Warehouse cluster.
   */
  public static final String WAREHOUSE_VERB = "BOOKSFROMWAREHOUSE";
  /**
   * <b>Description</b>: Verb for drop shipment or replenishing supply in
   * Warehouse, subscribed to on the Publisher cluster.
   */
  public static final String PUBLISHER_VERB = "BOOKSFROMPUBLISHER";
  /**
   * <b>Description</b>: Verb for getting books packed up, subscribed to on the
   * Warehouse cluster.
   */
  public static final String WAREHOUSEPACK_VERB = "PACKBOOKSATWAREHOUSE";
  /**
   * <b>Description</b>: Verb for getting books packed up at the Publisher,
   * subscribed to on the Publisher cluster.
   */
  public static final String PUBLISHERPACK_VERB = "PACKBOOKSATPUBLISHER";
  /** <b>Description</b>: Verb for shipment route. */
  public static final String SHIP_ROUTE_VERB = "SHIPROUTE";
  /**
   * <b>Description</b>: Verb for requesting shipment of books, subscribed to
   * on the Shipper cluster.
   */
  public static final String SHIPPER_VERB = "SHIPBOOKS";
  /**
   * <b>Description</b>: Verb for adding a new review to an item (book, CD,
   * whatever), subscribed to on the Warehouse cluster. <br>
   * <br>
   * <b>Notes</b>:<br>
   * - Subscribed to at the WarehouseLDMPlugin
   */
  public static final String UPDATE_REVIEW_VERB = "UPDATEREVIEW";
  /** <b>Description</b>: Verb for actual packing of books at Shipper. */
  public static final String PACKER_VERB = "PACKER";
  /** <b>Description</b>: Verb for actual packing of books at Warehouse. */
  public static final String WPACKER_VERB = "WPACKER";

  // Name of organization support role provided by manager
  /** OrderManager Role */
  public static String ORDER_MANAGER_ROLE = "OrderManager";
  /** Payment Authority Role */
  public static String PAYMENT_ROLE = "PaymentAuthority";
  /** Shipping Vendor Role */
  public static String SHIPPER_ROLE = "ShipmentVendor";
  /** Warehouse role */
  public static String WAREHOUSE_ROLE = "WarehouseControl";
  /** Review update role */
  public static String REVIEW_UPDATE_ROLE = "ReviewUpdateControl";
  /** Publisher role */
  public static String PUBLISHER_ROLE = "PublishingHouse";
  /** Inventory Prototype cache name */
  public static String INVENTORY_PROTOTYPE = "InventoryAsset";
  /** Warhouse Prototyp cache name */
  public static String WAREHOUSE_PROTOTYPE = "WarehouseAsset";
  /** Object Prototyp cache name */
  public static String OBJECT_PROTOTYPE = "ObjectAsset";
  /** Packer Prototype cache name */
  public static String PACKER_PROTOTYPE = "PackerAsset";
  /** Verb Constant for inventory levels */
  public static final String WARN_INVENTORY_VERB = "warnLowInventoryLevel";
  /** Prep phrase for isbn */
  public static final String WARN_INVENTORY_ISBN_PHRASE = "isbn";
  /** Prep phrase for cost */
  public static final String WARN_INVENTORY_COST_PHRASE = "cost";
  /** Prep phrase for the title of the book with low inventory */
  public static final String WARN_INVENTORY_TITLE_PHRASE = "title";

  // Search constants
  /** Seach sucess constant */
  public static double SEARCH_SUCCESS = 0.0;
  /** <b>Description</b>: Keyword specifying preposition for type of search. */
  public static final String SEARCH_PREPOSITION = "SEARCH TYPE";
  /**
   * <b>Description</b>: Keyword specifying preposition for a keyword search of
   * the titles in the inventory.
   */
  public static final String KEYWORD_PREPOSITION = "KEYWORD";
  /** <b>Description</b>: Keyword specifying preposition for type of search. */
  public static final String IDENT_PREPOSITION = "IDENT";
  /**
   * <b>Description</b>: Keyword specifying preposition of
   * "isbn:quantiy;isbn:quantity" format actual on book order task.
   */
  public static final String ISBN_PREPOSITION = "WITHISBNASIN";
  /**
   * <b>Description</b>: Keyword specifying preposition identifying shipping
   * destination.
   */
  public static final String SHIPDEST_PREPOSITION = "SHIPTO";
  /**
   * <b>Description</b>: Keyword specifying preposition identifying shipping
   * destination city.
   */
  public static final String SHIPCITY_PREPOSITION = "SHIPTOCITY";
  /**
   * <b>Description</b>: Keyword specifying preposition containing user detail
   * information.
   */
  public static final String USERDETAIL_PREPOSITION = "USERDETAIL";
  /**
   * <b>Description</b>: Keyword specifying preposition containing user detail
   * information.
   */
  public static final String ORDERDETAILS_PREPOSITION = "ORDERDETAILS";
  /**
   * <b>Description</b>: Keyword specifying preposition of shipping preference.
   */
  public static final String SHIPMETHOD_PREPOSITION = "SHIPVIA";
  /**
   * <b>Description</b>: Keyword specifying preposition containing credit card
   * info.
   */
  public static final String CCINFO_PREPOSITION = "WITHCREDITCARD";
  /** <b>Description</b>: Keyword specifying preposition for new review text. */
  public static final String REVIEWTEXT_PREPOSITION = "WITHREVIEWTEXT";
  /** <b>Description</b>: Keyword specifying preposition for packer text. */
  public static final String PACKER_PREPOSITION = "PACKERTEXT";
  /** <b>Description</b>: The book order has finished this task, or sub-task. */
  public static final double ISCOMPLETED = 1.0;
  /**
   * Prepositional Phrase used to tag a task to be monitored by the
   * WorkflowExcecutionPlugin
   */
  public static final String GENERIC_WORKFLOW_PREPOSITION = "WITHINGENERICWORKFLOW";
  /**
   * <b>Description</b>: The book order has NOT finished this task, or
   * sub-task, and is still active. <br>
   * <br>
   * <b>Notes</b>:<br>
   * - Any notes about the value goes here
   */
  public static final double ISNOTCOMPLETED = 0.0;
  /**
   * <b>Description</b>: The book order has been cancelled, either by the user
   * or the log plan has achieved an "illegal" state. <br>
   * <br>
   * <b>Notes</b>:<br>
   * - Any notes about the value goes here
   */
  public static final double ISCANCELLED = 1.0;
  /** <b>Description</b>: The book order not been cancelled. */
  public static final double ISNOTCANCELLED = 0.0;
  /**
   * <b>Description</b>: Aspect Type number for the completed aspect value. <br>
   * <br>
   * <b>Notes</b>:<br>
   * - This is based on the current last available aspect string count.
   */
  public static final int COMPLETED_ASPECT = AspectType.ASPECT_STRINGS.length
    + 2;
  /**
   * <b>Description</b>: Aspect Type number for the pack date aspect value. <br>
   * <br>
   * <b>Notes</b>:<br>
   * - This is based on the current last available aspect string count.
   */
  public static final int PACK_DATE_ASPECT = COMPLETED_ASPECT + 1;
  /**
   * <b>Description</b>: Aspect Type number for the shipping date aspect value. <br>
   * <br>
   * <b>Notes</b>:<br>
   * - This is based on the current last available aspect string count.
   */
  public static final int SHIP_DATE_ASPECT = PACK_DATE_ASPECT + 1;
  /**
   * <b>Description</b>: Aspect Type number for the drop ship date aspect
   * value. <br>
   * <br>
   * <b>Notes</b>:<br>
   * - This is based on the current last available aspect string count.
   */
  public static final int DROP_SHIP_DATE_ASPECT = SHIP_DATE_ASPECT + 1;
  /**
   * <b>Description</b>: Aspect Type number for the cancelled transaction
   * aspect value. <br>
   * <br>
   * <b>Notes</b>:<br>
   * - This is based on the current last available aspect string count.
   */
  public static final int CANCELLED_ASPECT = DROP_SHIP_DATE_ASPECT + 1;
  /**
   * <b>Description</b>: Aspect Type number for the cancelled transaction
   * aspect value. <br>
   * <br>
   * <b>Notes</b>:<br>
   * - This is based on the current last available aspect string count.
   */
  public static final int FULL_LOAD_ASPECT = CANCELLED_ASPECT + 1;
  /** <b>Description</b>: Total number of the BOL specific aspect types. */
  public static final int BOL_ASPECT_COUNT = 6;
  /** <b>Description</b>: Maximum number of days to plan an order. */
  public static final int CALENDAR_HORIZON_DAYS = 100;

  // ---------------------------------------------------------------------------------------------------------------------
  // Public Member Methods
  // ---------------------------------------------------------------------------------------------------------------------
  /**
   * <b>Description</b>: Based on the preference aspect type run the function
   * to get the score. <br>
   *
   * @param allocation The allocation result to be scored
   * @param preference The preference object that does the scoring.
   *
   * @return The score of the allocation result
   */
  public static double getPreferenceScore(AllocationResult allocation,
    Preference preference) {
    return (preference.getScoringFunction().getScore(AspectValue.newAspectValue(
        preference.getAspectType(),
        allocation.getValue(preference.getAspectType()))));
  }


  /**
   * <b>Description</b>: Return all preferences attached to the given task.
   * <br>
   *
   * @param task The task to pull the preference off of
   *
   * @return Vector of preferences from the task
   */
  public static Vector getAllPreferences(Task task) {
    Vector preferenceList = new Vector(0);
    Enumeration preferences = task.getPreferences();

    // Get each preference from the task and add it to the list
    for (int i = 0; preferences.hasMoreElements(); i++) {
      preferenceList.add(preferences.nextElement());
    }

    return (preferenceList);
  }


  /**
   * <b>Description</b>: Converts the given value to a printable currency
   * string. <br>
   * <b>Notes</b>:<br>
   * - This method uses the US locale for currency formatting <br>
   *
   * @param value Number to convert
   *
   * @return The printable currency form of the given value, or null if there
   *         was a problem formatting the value
   *
   * @see #currency(String)
   */
  public static String currency(double value) {
    try {
      return (NumberFormat.getCurrencyInstance(Locale.US).format(value));
    } catch (IllegalArgumentException e) {
    }

    return (null);
  }


  /**
   * <b>Description</b>: Converts the given value to a printable currency
   * string. <br>
   * <b>Notes</b>:<br>
   * - This method uses the US locale for currency formatting <br>
   *
   * @param value String to convert
   *
   * @return The printable currency form of the given string, or null if there
   *         was a problem formatting the string
   *
   * @see #currency(double)
   */
  public static String currency(String value) {
    try {
      return (currency(Double.parseDouble(value)));
    } catch (NumberFormatException e) {
    }

    return (null);
  }


  /**
   * <b>Description</b>: Parses the ISBN prepositional phrase string getting
   * each ISBN number and quantity. <br>
   * <b>Notes</b>:<br>
   * - Uses the "isbn:count" pattern where "isbn" is the item ISBN number and
   * "count" is the quantity<BR> - ";" will only be inserted between
   * isbn/count pairs, i.e. there will not be a ";" at the end of the ISBN
   * prepositional phrase string <br>
   *
   * @param prepositions Preposition string from task
   *
   * @return An array of String arrays where String[][0] is the ISBN and
   *         String[][1] is the count
   */
  public static String[][] parseISBNPrepositions(String prepositions) {
    // Since we do not know how many values there will be up front, we will temporarily store the values in vectors
    Vector isbnStrings = new Vector(0);
    Vector countStrings = new Vector(0);


    // Since the format of the ISBN prepositional phrase string we get DOES NOT have a ";" appended to the end of the
    // string, we will append one to it if the string is not of zero length so that we can parse the entire string within
    // the loop
    // It was a great idea to compact all this into one preposition, the guy who thought of it was a genius.
    // This is stupid and we SHOULD change it, but its what we've got right now!
    if (prepositions.length() > 0) {
      prepositions += ";";
    }


    // Loop through the string pulling out the value pairs as we go
    // Note that String.indexOf(character, startPos) allows startPos to be greater than the actual string length
    int separatorIndex = 0;
    for (int lastIndex = 0, i = prepositions.indexOf(';', lastIndex); i != -1;
      lastIndex = i + 1, i = prepositions.indexOf(';', lastIndex)) {
      separatorIndex = prepositions.indexOf(':', lastIndex);
      isbnStrings.add(prepositions.substring(lastIndex, separatorIndex));
      countStrings.add(prepositions.substring(separatorIndex + 1, i));
    }

    // Create the final array of value pairs based on the size of the ISBN vector and add the values to it
    String[][] prepositionList = new String[isbnStrings.size()][2];
    for (int i = 0; i < prepositionList.length; i++) {
      prepositionList[i][0] = (String) isbnStrings.elementAt(i);
      prepositionList[i][1] = (String) countStrings.elementAt(i);
    }

    return (prepositionList);
  }


  /**
   * <b>Description</b>: Create a book order task. <br>
   *
   * @param pf Factory object for creating cluster objects
   * @param bo Vector of BolBookOrder objects
   * @param ccInfo Credit card data
   * @param ud Shipping address destination
   * @param shipMethod User preferred method of shipment
   * @param verb Verb to assign to this book order task
   *
   * @return A book Order Task ready to publish
   */
  public static Task createOrderTask(PlanningFactory pf, Vector bo,
    String ccInfo, UserDetails ud, String shipMethod, String verb) {
    NewTask task = pf.newTask();
    task.setDirectObject(null);
    task.setVerb(new Verb(verb));
    setOrderPrepPhrases(task, bo, ud, ccInfo, shipMethod, pf);
    setOrderPreferences(task, bo, pf);
    task.setPlan(pf.getRealityPlan());

    return (task);
  }


  /**
   * <b>Description</b>: Create all the prepositional phrases for a book order
   * task and load them. <br>
   *
   * @param task Book order task to load prep phrases onto
   * @param bo Vector of BolBookOrder objects
   * @param ud Shipping address destination
   * @param ccInfo Credit card data
   * @param shipMethod User preferred method of shipment
   * @param theCOF Factory object for creating cluster objects
   */
  public static void setOrderPrepPhrases(NewTask task, Vector bo,
    UserDetails ud, String ccInfo, String shipMethod,
    ClusterObjectFactory theCOF) {
    Vector preps = new Vector(0);

    NewPrepositionalPhrase npp = theCOF.newPrepositionalPhrase();
    npp.setPreposition(ISBN_PREPOSITION);

    String isbnString = "";

    for (int i = 0; i < bo.size(); i++) {
      if (bo.get(i) instanceof BolBookOrder) {
        BolBookOrder bolbo = (BolBookOrder) bo.get(i);

        if (isbnString.length() > 0) {
          isbnString += ";";
        }

        isbnString += bolbo.toString();
      }
    }

    npp.setIndirectObject(isbnString);
    preps.add(npp);

    npp = theCOF.newPrepositionalPhrase();
    npp.setPreposition("WITHINGENERICWORKFLOW");
    preps.add(npp);

    // Add the book order details vector as a preposition
    npp = theCOF.newPrepositionalPhrase();
    npp.setPreposition(ORDERDETAILS_PREPOSITION);
    npp.setIndirectObject(bo);
    preps.add(npp);

    npp = theCOF.newPrepositionalPhrase();
    npp.setPreposition(USERDETAIL_PREPOSITION);
    npp.setIndirectObject(ud);
    preps.add(npp);

    npp = theCOF.newPrepositionalPhrase();
    npp.setPreposition(SHIPMETHOD_PREPOSITION);
    npp.setIndirectObject(shipMethod);
    preps.add(npp);

    npp = theCOF.newPrepositionalPhrase();
    npp.setPreposition(CCINFO_PREPOSITION);
    npp.setIndirectObject(ccInfo);
    preps.add(npp);

    task.setPrepositionalPhrases(preps.elements());
  }


  /**
   * <b>Description</b>: Create the preference on a book order task. <br>
   *
   * @param task Book order task to load the preferences on
   * @param boVec Vector of BolBookOrder objects
   * @param theCOF Factory object for creating cluster objects
   */
  public static void setOrderPreferences(NewTask task, Vector boVec,
    ClusterObjectFactory theCOF) {
    Vector newPreferences = new Vector();

    int totalOrderCount = 0;
    float totalPrice = (float) 0.0;

    // we score on the total for the order and the total for the price
    for (int ii = 0; ii < boVec.size(); ii++) {
      totalOrderCount += ((BolBookOrder) boVec.get(ii)).count;
      totalPrice += (((BolBookOrder) boVec.get(ii)).price * ((BolBookOrder) boVec
      .get(ii)).count);

    }

    ScoringFunction scorefcn = ScoringFunction.createPreferredAtValue(AspectValue
        .newAspectValue(AspectType.QUANTITY, totalOrderCount), .95);

    // this counts 50% towards the total preference score
    Preference pref = theCOF.newPreference(AspectType.QUANTITY, scorefcn, 0.5);

    newPreferences.addElement(pref);


    // lose 95% for every dollar we can't hold, if he hits his credit limit in
    // the middle of this transaction, even if only by a dollar, it's "no sale"
    ScoringFunction priceScorefcn = ScoringFunction.createNearOrBelow(AspectValue
        .newAspectValue(AspectType.COST, (double) totalPrice), .95);

    // this counts 50% towards the total preference score
    Preference pricePref = theCOF.newPreference(AspectType.COST, priceScorefcn,
        0.5);

    newPreferences.addElement(pricePref);

    // every part of the book order task has an indication to show final completion (i.e. the performance task is now in the past)
    ScoringFunction complScorefcn = ScoringFunction.createPreferredAtValue(AspectValue
        .newAspectValue(BolSocietyUtils.COMPLETED_ASPECT,
          BolSocietyUtils.ISCOMPLETED), 1.0);


    // this counts 0 against the preference score, since it indicates final completion of the task
    // it isn't used to show how well planned the task is
    Preference complPref = theCOF.newPreference(BolSocietyUtils.COMPLETED_ASPECT,
        complScorefcn, 0.0);

    newPreferences.addElement(complPref);


    task.setPreferences(newPreferences.elements());


  }


  /**
   * <b>Description</b>: Create a task to add a review of an item (book, CD,
   * etc). <br>
   *
   * @param theCOF Factory object for creating cluster objects
   * @param isbn ID of item which has been newly reviewed
   * @param review The text of the new review
   *
   * @return Task to get the item's review updated
   */
  public static Task createUpdateReviewTask(ClusterObjectFactory theCOF,
    String isbn, String review) {
    NewTask task = theCOF.newTask();

    task.setDirectObject(null);

    task.setVerb(new Verb(UPDATE_REVIEW_VERB));

    setUpdReviewPrepPhrases(task, isbn, review, theCOF);

    setUpdReviewPreference(task, theCOF);

    task.setPlan(theCOF.getRealityPlan());

    return task;

  }


  private static void setUpdReviewPrepPhrases(NewTask task, String isbn,
    String reviewText, ClusterObjectFactory theCOF) {
    Vector preps = new Vector();

    NewPrepositionalPhrase npp = theCOF.newPrepositionalPhrase();
    npp.setPreposition(ISBN_PREPOSITION);

    npp.setIndirectObject(isbn);
    preps.add(npp);

    npp = theCOF.newPrepositionalPhrase();
    npp.setPreposition(REVIEWTEXT_PREPOSITION);
    npp.setIndirectObject(reviewText);
    preps.add(npp);

    task.setPrepositionalPhrases(preps.elements());

  }


  private static void setUpdReviewPreference(NewTask task,
    ClusterObjectFactory theCOF) {
    Vector newPreferences = new Vector();
    int totalOrderCount = 0;
    float totalPrice = (float) 0.0;


    ScoringFunction scorefcn = ScoringFunction.createStrictlyAtValue((AspectValue
        .newAspectValue(AspectType.CUSTOMER_SATISFACTION, 1.0)));

    // this counts 50% towards the total completion ability
    Preference pref = theCOF.newPreference(AspectType.CUSTOMER_SATISFACTION,
        scorefcn, 1.0);

    newPreferences.addElement(pref);

    task.setPreferences(newPreferences.elements());


  }


  /**
   * <b>Description</b>: Create a preference on search risk of success <br>
   *
   * @param task whose preferences are to be modified
   * @param theCOF from which to create preferences
   */
  public static void modifySearchPreferences(Task task,
    ClusterObjectFactory theCOF) {
    // BolSocietyUtils.out.println ("Setting Preference for search : " );
    Vector newPreferences = new Vector();
    ScoringFunction scorefcn = ScoringFunction.createStrictlyAtValue(AspectValue
        .newAspectValue(AspectType.RISK, SEARCH_SUCCESS));
    Preference pref = theCOF.newPreference(AspectType.RISK, scorefcn, 1.0);
    newPreferences.addElement(pref);
    ((NewTask) task).setPreferences(newPreferences.elements());
  }


  /**
   * <b>Description</b>: Set up order preference on a book order task. <br>
   *
   * @param task The task to receive the new preferences
   * @param theCOF Factory object for creating cluster objects
   */
  public static void modifyOrderPreferences(Task task,
    ClusterObjectFactory theCOF) {
    int billing_day = (int) (Math.random() * CALENDAR_HORIZON_DAYS);

    // BolSocietyUtils.out.println ("Requesting payment before : " + billing_day);
    Vector newPreferences = new Vector();

    // lose 25% for every day we miss, after 4 days it's too late
    ScoringFunction scorefcn = ScoringFunction.createNearOrAbove(AspectValue
        .newAspectValue(AspectType.END_TIME, billing_day), .25);

    // this counts 33% towards the total completion ability, need to find the book and ship it too, all are equally important
    Preference pref = theCOF.newPreference(AspectType.END_TIME, scorefcn, 0.333);

    newPreferences.addElement(pref);

    ((NewTask) task).setPreferences(newPreferences.elements());

  }


  /**
   * <b>Description</b>: Get the score of the aspect type from the task. <br>
   *
   * @param task The task from which to get the aspect value
   * @param aspectType The type number of the aspect to score
   *
   * @return The score of this aspect on this task
   */
  public static double getAspectScore(Task task, int aspectType) {
    double retscore = 1.0; // non-zero is failure

    Preference pref = TutorialUtils.getPreference(task, aspectType);

    ScoringFunction scf = pref.getScoringFunction();

    AspectValue[] allAspVals = task.getPlanElement().getEstimatedResult()
                                   .getAspectValueResults();

    for (int ii = 0; ii < allAspVals.length; ii++) {
      if (allAspVals[ii].getAspectType() == aspectType) {
        retscore = scf.getScore(allAspVals[ii]);
      }
    }

    return retscore;

  }


  /**
   * <b>Description</b>: Conflate the Allocation Results of sub-tasks. <br>
   * <b>Notes</b>:<br>
   * - Only ARs the parent is interested in are conflated. <br>
   *
   * @param wf The workflow of the expansion task to process
   *
   * @return An allocation result for the parent task with merged sub-task ARs
   */
  public static AllocationResult doAllocationResultAggregation(Workflow wf) {
    TaskScoreTable tst = buildTSTfromWF(wf);

    BolAllocResAgg bolARAgg = new BolAllocResAgg();

    AspectValue quanAV = AspectValue.newAspectValue(AspectType.QUANTITY, 0);
    AspectValue packDateAV = AspectValue.newAspectValue(BolSocietyUtils.PACK_DATE_ASPECT,
        0);
    AspectValue shipDateAV = AspectValue.newAspectValue(BolSocietyUtils.SHIP_DATE_ASPECT,
        0);
    AspectValue dropShipDateAV = AspectValue.newAspectValue(BolSocietyUtils.DROP_SHIP_DATE_ASPECT,
        0);
    AspectValue compAV = AspectValue.newAspectValue(BolSocietyUtils.COMPLETED_ASPECT,
        0);
    AspectValue cancelAV = AspectValue.newAspectValue(BolSocietyUtils.CANCELLED_ASPECT,
        0);
    AspectValue startAV = AspectValue.newAspectValue(AspectType.START_TIME, 0);
    AspectValue endAV = AspectValue.newAspectValue(AspectType.END_TIME, 0);

    AspectValue[] parentAVs = {
      quanAV, packDateAV, shipDateAV, dropShipDateAV, compAV, cancelAV, startAV,
      endAV
    };
    AllocationResult parentAR = new AllocationResult(1.0, true, parentAVs);

    AllocationResult estAR = bolARAgg.calculate(wf, tst, parentAR);

    return estAR;

  }


  /**
   * Utitlity Method to Create an AllocationResult
   *
   * @param factory PlanningFactory
   * @param success Result success
   * @param rating Confidence rating
   * @param types Array of aspect types
   * @param values Array of aspect values
   *
   * @return AllocationResult
   */
  public static AllocationResult createAllocationResult(
    PlanningFactory factory, boolean success, double rating, int[] types,
    double[] values) {
    AspectValue[] aspectValues = new AspectValue[types.length];
    for (int i = 0; i < types.length; i++) {
      aspectValues[i] = AspectValue.newAspectValue(types[i], values[i]);
    }

    return factory.newAllocationResult(rating, success, aspectValues);
  }


  /**
   * <b>Description</b>: Construct a TaskScoreTable object based on this
   * Workflow object. <br>
   *
   * @param wf Description of any parameters goes here
   *
   * @return A TaskScoreTable object for this workflow
   */
  public static TaskScoreTable buildTSTfromWF(Workflow wf) {
    Enumeration enum = wf.getTasks();

    // Hashtable myTSTHash = new Hashtable();
    List taskList = (List) new Vector();
    List arList = (List) new Vector();

    // first load the tasks so we can count them (I hate this solution!)
    while (enum.hasMoreElements()) {
      Task myTask = (Task) enum.nextElement();
      taskList.add(myTask);

    }

    // convert the tasks to an array
    Task[] ttype = new Task[taskList.size()];
    Task[] tIn = (Task[]) taskList.toArray(ttype);

    // load up the existing allocation results, leave the others as null array entries
    AllocationResult[] arIn = new AllocationResult[taskList.size()];

    for (int ii = 0; ii < tIn.length; ii++) {
      PlanElement pe = tIn[ii].getPlanElement();
      if (pe != null) {
        AllocationResult arTest = pe.getEstimatedResult();
        if (arTest != null) {
          arIn[ii] = arTest;
        }
      }
    }

    TaskScoreTable tst = new TaskScoreTable(tIn, arIn);

    return tst;

  }


  /**
   * <b>Description</b>: Construct a new prepositional phrase. <br>
   *
   * @param indirectObj The object to put into the new prepositional phrase
   * @param preposition The keyword of this prepostion
   * @param theCOF Factory object for creating cluster objects
   *
   * @return A new prepositional phrase
   */
  public static NewPrepositionalPhrase createPrepPhrase(Object indirectObj,
    String preposition, ClusterObjectFactory theCOF) {
    NewPrepositionalPhrase npp = theCOF.newPrepositionalPhrase();
    npp.setPreposition(preposition);
    npp.setIndirectObject(indirectObj);
    return npp;
  }

  public static class Database {
    public static final String SEARCH_BOOK_BY_TITLE_QUERY_NAME = "FindByTitle";
    public static final String SEARCH_BOOK_BY_AUTHOR_QUERY_NAME = "FindByAuthor";
    public static final String SEARCH_BOOK_BY_SUBJECT_QUERY_NAME = "FindBySubject";
    public static final String BOOK_SEARCH_PARAMETER = "search";
    public static final String BOOK_ISBN_PARAMETER = "isbn";
    public static final String INVENTORY_LEVEL_PARAMETER = "inventoryLevel";
    public static final String GET_BOOK_BY_ISBN_QUERY_NAME = "GetBookByIsbn";
    public static final String UPDATE_INVENTORY_QUERY_NAME = "updateInventory";
    public static final String GET_ALL_BOOKS = "getAllBooks";

    //publisher queries
    public static final String PUBLISHER_FIND_BOOK_BY_ISBN_QUERY = "PublisherGetBookByIsbn";
    public static final String PUBLISHER_UPDATE_INVENTORY_QUERY = "PublisherUpdateInventory";

    private Database() {
    }
  }
}
