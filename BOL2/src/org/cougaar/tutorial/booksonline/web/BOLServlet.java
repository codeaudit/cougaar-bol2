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


import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;

import org.cougaar.core.blackboard.BlackboardClient;
import org.cougaar.core.component.ServiceBroker;
import org.cougaar.core.node.NodeIdentificationService;
import org.cougaar.core.service.AgentContainmentService;
import org.cougaar.core.service.BlackboardService;
import org.cougaar.core.service.DomainService;
import org.cougaar.core.service.LoggingService;
import org.cougaar.core.service.NamingService;
import org.cougaar.core.service.UIDService;
import org.cougaar.core.servlet.BaseServletComponent;


/**
 * Base Servlet Component used in BOL. Provides some utitlity
 * functions and services for subclasses.
 *
 * @author ttschampel
 */
public abstract class BOLServlet extends BaseServletComponent
    implements BlackboardClient {
    /** Servlet implementation */
    protected ComponentServlet componentServlet = null;
    /** Cougaar NodeIdentificationService */
    protected NodeIdentificationService nodeIdService;
    /** Cougaar AgentContainmentService */
    protected AgentContainmentService agentContainmentService;
    /** Cougaar ServiceBroker */
    protected ServiceBroker serviceBroker;
    /** Cougaar BlackboardService */
    protected BlackboardService blackboardService;
    /** Cougaar DomainService */
    protected DomainService domainService;
    /** Cougaar  NamingService */
    protected NamingService namingService;
    /** Cougaar UIDService */
    protected UIDService uidService;
    /** Cougaar Logging Service */
    protected LoggingService logging;

    /* (non-Javadoc)
     * @see org.cougaar.core.servlet.BaseServletComponent#getPath()
     */
    protected abstract String getPath();


    /* (non-Javadoc)
     * @see org.cougaar.core.servlet.BaseServletComponent#createServlet()
     */
    protected Servlet createServlet() {
        componentServlet = new ComponentServlet();

        return (Servlet) componentServlet;
    }


    /**
     * Handle processing of HTTP Request to this component
     *
     * @param request
     * @param response
     */
    protected abstract void serviceRequest(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException;


    /**
     * Initailize servlet
     *
     * @param config ServletConfig
     *
     * @throws ServletException ServletException
     */
    protected abstract void initServlet(ServletConfig config)
        throws ServletException;


    /**
     * Component load method, initializes Cougaar services
     */
    public void load() {
        this.serviceBroker = this.bindingSite.getServiceBroker();
        this.domainService = (DomainService) serviceBroker.getService(this,
                DomainService.class, null);
        this.blackboardService = (BlackboardService) serviceBroker.getService(this,
                BlackboardService.class, null);
        this.agentContainmentService = (AgentContainmentService) serviceBroker
            .getService(this, AgentContainmentService.class, null);
        this.namingService = (NamingService) serviceBroker.getService(this,
                NamingService.class, null);
        this.uidService = (UIDService) serviceBroker.getService(this,
                UIDService.class, null);
        this.nodeIdService = (NodeIdentificationService) serviceBroker
            .getService(this, NodeIdentificationService.class, null);
        this.logging = (LoggingService) serviceBroker.getService(this,
                LoggingService.class, null);

        super.load();
    }


    /* (non-Javadoc)
     * @see org.cougaar.core.blackboard.BlackboardClient#getBlackboardClientName()
     */
    public String getBlackboardClientName() {
        return this.getClass().getName();
    }


    /* (non-Javadoc)
     * @see org.cougaar.core.blackboard.BlackboardClient#currentTimeMillis()
     */
    public long currentTimeMillis() {
        return 0;
    }

    /**
     * Out HTTPServlet, just hands off processing of incoming request  to the
     * abstract execute() method
     *
     * @author ttschampel
     */
    private class ComponentServlet extends HttpServlet {
        public ComponentServlet() {
            if (JspFactory.getDefaultFactory() == null) {
                JspFactory.setDefaultFactory(new org.apache.jasper.runtime.JspFactoryImpl());
            }
        }

        public void init(ServletConfig config) {
            try {
                super.init(config);
                initServlet(config);
            } catch (Exception e) {
                if (logging.isErrorEnabled()) {
                    logging.error("Errot initializing sevlet", e);
                }
            }
        }


        public void service(HttpServletRequest request,
            HttpServletResponse response) {
            try {
            	if(logging.isDebugEnabled()){
            		logging.debug("Servicing request");
            	}
            
                serviceRequest(request, response);
            } catch (Exception e) {
                if (logging.isErrorEnabled()) {
                    logging.error("Error servicing request", e);
                }
            }
        }
    }
}
