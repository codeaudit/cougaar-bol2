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
 * Created on Jun 20, 2003
 *
 *
 */
package org.cougaar.tutorial.booksonline.web.warehouse;


import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cougaar.tutorial.booksonline.web.BOLServlet;
import org.cougaar.tutorial.booksonline.web.WebConstants;


/**
 * Servlet that performs searches against the warehouse's database
 *
 * @author ttschampel
 */
public class CatalogSearchServlet extends BOLServlet implements WebConstants {
   /**
   * Get Path to this servlet
   *
   * @return Get Servlet Path
   */
  protected String getPath() {
    return CATALOG_SEARCH;
  }


 


  /**
   * Service HTTP Request
   *
   * @param request Servlet REquest
   * @param response Servlet Response
   *
   * @throws ServletException Exception
   * @throws IOException Exception
   */
  protected void serviceRequest(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
   
  }


  /**
   *Initialize this servlet (blank impl)
   */
  protected void initServlet(ServletConfig config)
    throws ServletException {
  }


  
}
