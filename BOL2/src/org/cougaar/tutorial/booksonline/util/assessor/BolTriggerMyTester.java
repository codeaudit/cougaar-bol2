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


package org.cougaar.tutorial.booksonline.util.assessor;


import org.cougaar.planning.ldm.trigger.TriggerTester;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;


/**
 * <b>Description</b>: BolTriggerMyTester  A Trigger Tester to determine if an
 * allocation is stale <br>
 * <br>
 * <b>Notes</b>:<br>
 * -
 *
 * @author Frank Cooley, &copy;2000 Clark Software Engineering, Ltd. & Defense
 *         Advanced Research Projects Agency (DARPA)
 * @version 1.0
 */
public class BolTriggerMyTester implements TriggerTester {
  private transient boolean stale;

  // ---------------------------------------------------------------------------------------------------------------------
  // Public Member Methods
  // ---------------------------------------------------------------------------------------------------------------------
  /**
   * <b>Description</b>: Test - Return indication if any allocation in group is
   * stale <br><b>Notes</b>:<br>
   *
   * @param objects Objects[] array of trigger objects
   *
   * @return boolean <br>
   */
  public boolean Test(Object[] objects) {
    // Check if any of the objects are 'stale' allocations
    // reset stale flag each time
    stale = false;
    List objectlist = Arrays.asList(objects);
    ListIterator lit = objectlist.listIterator();
    while (lit.hasNext()) {
      // just to be safe for now, get the object as an Object and
      // check if its an Allocation before checking the stale flag.
      Object o = (Object) lit.next();
    }

    return true;
  }
}
