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


/*
 * Created on Jul 2, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cougaarsoftware.common.service;


import org.cougaar.core.component.ServiceBroker;
import org.cougaar.core.component.ServiceProvider;


/**
 * The Database Service Provider
 *
 * @author ttschampel To change the template for this generated type comment go
 *         to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 *         Comments
 */
public class DatabaseServiceProvider implements ServiceProvider {
  DatabaseServiceImpl databaseImplRef = null;
  String queryFile = null;

  /**
   * Constructor
   *
   * @param sb The service broker for the service.
   */
  public DatabaseServiceProvider(ServiceBroker sb) {
    databaseImplRef = new DatabaseServiceImpl(sb);
  }

  /**
   * Returns a reference to GUI Service
   *
   * @param sb Service broker
   * @param requestor The requestor
   * @param serviceClass The service class
   *
   * @return The Service
   */
  public Object getService(ServiceBroker sb, Object requestor,
    Class serviceClass) {
    if (DatabaseService.class.isAssignableFrom(serviceClass)) {
      if (databaseImplRef == null) {
        databaseImplRef = new DatabaseServiceImpl(sb);
      } else {
        databaseImplRef.setServiceBroker(sb);
      }

      return databaseImplRef;
    } else {
      return databaseImplRef;
    }
  }


  /**
   * Releases the Service
   *
   * @param sb Service Broker
   * @param requestor Requesting Object
   * @param serviceClass Service Class
   * @param service Service Object
   */
  public void releaseService(ServiceBroker sb, Object requestor,
    Class serviceClass, Object service) {
  }
}
