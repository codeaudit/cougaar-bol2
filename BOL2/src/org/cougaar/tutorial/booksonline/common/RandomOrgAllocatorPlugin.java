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


package org.cougaar.tutorial.booksonline.common;


import org.cougaar.core.service.LoggingService;
import org.cougaar.glm.ldm.asset.Organization;
import org.cougaar.planning.ldm.plan.HasRelationships;
import org.cougaar.planning.ldm.plan.Relationship;
import org.cougaar.planning.ldm.plan.RelationshipSchedule;
import org.cougaar.planning.ldm.plan.Role;
import org.cougaar.util.TimeSpan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;


/**
 * <b>Description</b>: This class extends the NoARGenericTablePlugin to add
 * functionality for selecting from multiple organizations. <br>
 * <br>
 * <b>Notes</b>:<br>
 * - Randomly picks between organizations
 *
 * @author Eric B. Martin, &copy;2000 Clark Software Engineering, Ltd. &
 *         Defense Advanced Research Projects Agency (DARPA)
 * @version 1.0
 */
public class RandomOrgAllocatorPlugin extends NoARGenericTablePlugin

//public class RandomOrgAllocatorPlugin extends org.cougaar.mlm.plugin.generic.GenericTablePlugin
 {
    private LoggingService logging;

    /**
     * Set the Logging Service
     *
     * @param service Logging Service
     */
    public void setLoggingService(LoggingService service) {
        this.logging = service;
    }


    /**
     * <b>Description</b>: Over-rides
     * GenericTablePlugin.findCapableOrganization() method to randomly pick
     * between organizations capable of performing the task.   <br>
     * <b>Notes</b>:<br>
     * -  <br>
     *
     * @param allocC Alocation information of the task, roles and relationship
     *        to base choosing an organizational asset
     *
     * @return The organizational asset choosen to be allocated
     */
    protected Organization findCapableOrganization(AllocateCommandInfo allocC) {
        // find ourself first
        for (Iterator orgIter = organizationSub.getCollection().iterator();
            orgIter.hasNext();) {
            Organization org = (Organization) orgIter.next();
            if (org.isSelf()) {
                while (allocC != null) {
                    // All HasRelationships having all roles
                    Collection intersection = new ArrayList();
                    boolean first = true;
                    RelationshipSchedule schedule = org.getRelationshipSchedule();

                    for (int i = 0; i < allocC.allocateRoles.length; i++) {
                        Role role = Role.getRole(allocC.allocateRoles[i]);
                        Collection orgCollection = schedule
                            .getMatchingRelationships(role, TimeSpan.MIN_VALUE,
                                TimeSpan.MAX_VALUE);

                        for (Iterator iter = orgCollection.iterator();
                            iter.hasNext();) {
                            HasRelationships other = schedule.getOther((Relationship) iter
                                    .next());

                            if (other instanceof Organization) {
                                if (first) {
                                    intersection.add(other);
                                } else {
                                    if (!intersection.contains(other)) {
                                        iter.remove();
                                    }
                                }
                            }
                        }

                        first = false;
                    }

                    // If we found any matches return one, else try the default 
                    if (intersection.size() > 0) {
                        if (logging.isDebugEnabled()) {
                            logging.debug("RandomOrgAllocatorPlugin::Orgs: "
                                + intersection);
                        }

                        Vector organizations = new Vector(intersection);

                        // From the list of possible organizational assets, choose one
                        Organization selectedOrganization = (Organization) organizations
                            .elementAt((int) (currentTimeMillis() % organizations
                                .size()));

                        return (selectedOrganization);
                    } else {
                        // ?EBM? What does this do for me?
                        allocC = allocC.defaultAlloc;
                    }
                }
            }
        }

        return (null);
    }
}
