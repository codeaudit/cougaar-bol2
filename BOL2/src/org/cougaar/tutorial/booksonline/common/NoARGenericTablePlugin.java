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


import org.cougaar.core.blackboard.IncrementalSubscription;
import org.cougaar.core.service.LoggingService;
import org.cougaar.mlm.plugin.generic.GenericTablePlugin;


/**
 * <b>Description</b>: Extends the
 * <Code>org.cougaar.mlm.plugin.generic.GenericTablePlugin</Code> plugin to
 * to keep the GenericTablePlugin from subscribing to     allocations and
 * copying alocation results.
 *
 * @author Eric B. Martin, &copy;2000 Clark Software Engineering, Ltd. &
 *         Defense Advanced Research Projects Agency (DARPA)
 * @version 1.0
 */
public class NoARGenericTablePlugin extends GenericTablePlugin {
    protected LoggingService logging;

    /**
     * Get LoggingService
     *
     * @return LoggingService
     */
    public LoggingService getLoggingService() {
        return logging;
    }


    /**
     * <b>Description</b>: Sets up subscriptions without subscribing to
     * allocations.  This method is copied from
     * GenericTablePlugin.makeSubscriptions() and the code which subscribes to
     * allocations was commented out and replaced with code that makes the
     * allocation subscription list zero length. <br>
     * <b>Notes</b>:<br>
     * -
     */
    protected void makeSubscriptions() {
        for (int i = 0; i < taskPreds.length; i++) {
            tasksSub[i] = (IncrementalSubscription) getBlackboardService()
                                                        .subscribe(taskPreds[i]);
        }

        // Make the allocation subscription list of size zero
        /*    for (int i = 0; i < allocPreds.length; i++)
           {
             allocationsSub[i] = (IncrementalSubscription)subscribe(allocPreds[i]);
           }*/
        allocationsSub = new IncrementalSubscription[0];

        organizationSub = (IncrementalSubscription) getBlackboardService()
                                                        .subscribe(newOrganizationPred());
    }
}
