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


import org.cougaar.planning.ldm.trigger.TriggerMonitor;
import org.cougaar.planning.ldm.trigger.TriggerTimeBasedMonitor;
import org.cougaar.planning.plugin.legacy.PluginDelegate;


/**
 * A TriggerTimeBasedMonitor is a kind of monitor that generates an interrupt
 * at regular intervals to check for a particular condition on a fixed set of
 * objects Uses system time
 */
/**
 * <b>Description</b>: WakeAfterMonitor A TriggerTimeBasedMonitor is a kind of
 * monitor that generates an interrupt at regular intervals to check for a
 * particular condition on a fixed set of objects Uses system time <br>
 * <br>
 * <b>Notes</b>:<br>
 * -
 *
 * @author Frank Cooley, &copy;2000 Clark Software Engineering, Ltd. & Defense
 *         Advanced Research Projects Agency (DARPA)
 * @version 1.0
 */
public class WakeAfterMonitor extends TriggerTimeBasedMonitor
  implements TriggerMonitor {
  private Object[] my_objects;
  long my_last_ran;
  long my_msec_interval;
  boolean oneShot = false;

  // ---------------------------------------------------------------------------------------------------------------------
  // Public Constructors
  // ---------------------------------------------------------------------------------------------------------------------
  /**
   * <b>Description</b>: Constructor to build the object <br><b>Notes</b>:<br>
   *
   * @param msec_interval long interval to check
   * @param objects Object[] array of objects for this trigger
   * @param pID PlugInDelegate <br>
   */
  public WakeAfterMonitor(long msec_interval, Object[] objects,
    PluginDelegate pID) {
    super(msec_interval, objects, pID);
    my_objects = objects;
    my_msec_interval = msec_interval;
    my_last_ran = pID.currentTimeMillis();
  }

  // ---------------------------------------------------------------------------------------------------------------------
  // Public Member Methods
  // ---------------------------------------------------------------------------------------------------------------------
  /**
   * <b>Description</b>: getMsecInterval - get the timing interval
   * <br><b>Notes</b>:<br>
   *
   * @return long <br>
   */
  public long getMsecInterval() {
    return my_msec_interval;
  }


  /**
   * <b>Description</b>: getAssociatedObjects - get the trigger objects
   * <br><b>Notes</b>:<br>
   *
   * @return Object[] <br>
   */
  public Object[] getAssociatedObjects() {
    return my_objects;
  }


  /**
   * <b>Description</b>: ReadyToRun <br><b>Notes</b>:<br>
   *
   * @param pid PlugInDelegate
   *
   * @return boolean <br>
   */
  public boolean ReadyToRun(PluginDelegate pid) {
    boolean ready = (pid.currentTimeMillis() - my_last_ran) > my_msec_interval;
    if (!oneShot && ready) {
      oneShot = true;
      return ready;
    } else {
      return false;
    }
  }


  /**
   * <b>Description</b>: IndicateRan <br><b>Notes</b>:<br>
   *
   * @param pid PlugInDelegate
   */
  public void IndicateRan(PluginDelegate pid) {
    my_last_ran = pid.currentTimeMillis();
  }


  /**
   * <b>Description</b>: getRemainingTime <br><b>Notes</b>:<br>
   *
   * @return long <br>
   */
  public long getRemainingTime() {
    if (oneShot) {
      return 0;
    } else {
      return (my_msec_interval - (System.currentTimeMillis() - my_last_ran));
    }
  }
}
