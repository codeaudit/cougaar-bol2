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


import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.cougaar.glm.ldm.asset.Organization;
import org.cougaar.glm.ldm.asset.OrganizationPG;
import org.cougaar.planning.ldm.plan.Allocation;
import org.cougaar.planning.ldm.plan.PlanElement;
import org.cougaar.planning.ldm.plan.Preference;
import org.cougaar.planning.ldm.plan.Role;
import org.cougaar.planning.ldm.plan.Task;


/**
 * Utility class for writing all tutorial plugin classes
 */
public class TutorialUtils {
  /**
   * Return the preference for the given aspect
   *
   * @param task for which to return given preference
   * @param aspect_type aspect type
   *
   * @return Preference (or null) from task for given aspect
   */
  public static Preference getPreference(Task task, int aspect_type) {
    Preference aspect_pref = null;
    for (Enumeration e = task.getPreferences(); e.hasMoreElements();) {
      Preference pref = (Preference) e.nextElement();
      if (pref.getAspectType() == aspect_type) {
        aspect_pref = pref;
        break;
      }
    }

    return aspect_pref;
  }


  /**
   * Create a simple free-floating GUI button with a label
   *
   * @param button_label DOCUMENT ME!
   * @param frame_label DOCUMENT ME!
   * @param listener DOCUMENT ME!
   */
  public static void createGUI(String button_label, String frame_label,
    ActionListener listener) {
    JFrame frame = new JFrame(frame_label);
    frame.getContentPane().setLayout(new FlowLayout());
    JPanel panel = new JPanel();

    // Create the button
    JButton button = new JButton(button_label);

    // Register a listener for the button
    button.addActionListener(listener);
    panel.add(button);
    frame.getContentPane().add("Center", panel);
    frame.pack();
    frame.setVisible(true);
  }


  /**
   * Grab first asset from a list of assets and return it
   *
   * @param objects of assets from which to return first  (or null if there are
   *        none)
   *
   * @return Object from list
   */
  public static Object getFirstObject(Enumeration objects) {
    Object object = null;
    if (objects.hasMoreElements()) {
      object = objects.nextElement();
    }

    return object;
  }


  /**
   * Is given object an Organization that has given role among given capable
   * roles?
   *
   * @param o to test for role
   * @param role role to check for on object
   *
   * @return boolean indicating whether object is Organization with given role
   */
  public static boolean isSupplierOrganization(Object o, String role) {
    if (o instanceof Organization) {
      Organization org = (Organization) o;
      String[] roles = new String[] { role };

      OrganizationPG orgPG = null;

      if (roles != null) {
        // Does this org have all the roles we need?
        if (orgPG == null) {
          orgPG = org.getOrganizationPG();
        }

        // ALP821 fix, apparently our own org id comes to us with no roles, go figure
        if ((orgPG == null) || (orgPG.getRoles() == null)) {
          // an organization without any roles can't be what we're looking for
          return (false);
        }

        Object[] caproles = orgPG.getRoles().toArray();
        int ncaproles = caproles.length;
        if ((ncaproles == 0) && (roles.length > 0)) {
          // Org does nothing?
          return (false);
        }

        for (int i = 0; i < roles.length; i++) {
          String findRole = roles[i];
          int j = 0;
          while (true) {
            String thisRole = ((Role) caproles[j]).toString();
            if (thisRole.equals(findRole)) {
              // Good: Org has role[i]
              break;
            }

            if (++j >= ncaproles) {
              // Bad: Org lacks role[i]
              return (false);
            }
          }
        }

        // Good: Org has all the roles
      }

      return (true);
    }

    return (false);
  }


  /**
   * Is given object a task with given verb?
   *
   * @param o to test as task with verb
   * @param verb verb for object
   *
   * @return boolean indicating if object is a task with given verb
   */
  public static boolean isTaskWithVerb(Object o, String verb) {
    if (o instanceof Task) {
      Task task = (Task) o;
      return task.getVerb().equals(verb);
    }

    return false;
  }


  /**
   * Is given object an allocation whose task with given verb?
   *
   * @param o to test as allocation with task with verb
   * @param verb verb for object
   *
   * @return boolean indicating if object is a allocation with given verb/task
   */
  public static boolean isAllocationWithVerb(Object o, String verb) {
    if (o instanceof Allocation) {
      Allocation alloc = (Allocation) o;
      return alloc.getTask().getVerb().equals(verb);
    }

    return false;
  }


  /**
   * Is given object a plan_element whose task with given verb?
   *
   * @param o to test as plan_element with task with verb
   * @param verb verb for object
   *
   * @return boolean indicating if object is a PE with given verb/task
   */
  public static boolean isPlanElementWithVerb(Object o, String verb) {
    if (o instanceof PlanElement) {
      PlanElement plan_element = (PlanElement) o;
      return plan_element.getTask().getVerb().equals(verb);
    }

    return false;
  }


  // Create a date at a given year/month/day
  public static Date createDate(int year, int month, int day) {
    Calendar my_calendar = Calendar.getInstance();
    my_calendar.set(year, month - 1, day, 0, 0, 0);
    return my_calendar.getTime();
  }
}
