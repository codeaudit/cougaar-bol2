/*
 * <copyright>
 *  Copyright 2000-2003 Cougaar Software, Inc.
 *  All Rights Reserved
 * </copyright>
 */


/*
 * Created on Jun 17, 2003
 *
 *
 */
package org.cougaar.tutorial.booksonline.web;


import javax.servlet.Servlet;

import org.cougaar.core.servlet.BaseServletComponent;


/**
 * Base Servlet Component used in BOL. Provides some utitlity
 * functions and services for subclasses.
 *
 * @author ttschampel
 */
public abstract class BOLServlet extends BaseServletComponent
    {
    
    protected abstract String getPath();


   
    protected Servlet createServlet() {
       return null;
    }


    
}
