/*
 * <copyright>
 *  Copyright 2000-2003 Cougaar Software, Inc.
 *  All Rights Reserved
 * </copyright>
 */


package com.cougaarsoftware.util;


import org.cougaar.core.component.ComponentDescription;
import org.cougaar.core.mobility.AddTicket;
import org.cougaar.core.mobility.RemoveTicket;
import org.cougaar.core.mobility.ldm.AgentControl;
import org.cougaar.core.mobility.ldm.MobilityFactory;
import org.cougaar.core.mobility.ldm.TicketIdentifier;
import org.cougaar.core.mts.MessageAddress;
import org.cougaar.core.service.AgentContainmentService;
import org.cougaar.core.service.BlackboardService;
import org.cougaar.core.service.DomainService;
import org.cougaar.core.service.LoggingService;


/**
 * Utility methods for Agent and Plugin Mobility  For Agent Mobility to work
 * these must be added to .ini files: plugin =
 * org.cougaar.core.mobility.service.RootMobilityPlugin     plugin =
 * org.cougaar.core.mobility.service.RedirectMovePlugin And then add the
 * mobility domain to the LDMDomain.ini file:     mobility =
 * org.cougaar.core.mobility.ldm.MobilityDomain
 *
 * @author ttschampel
 */
public class MobilityUtil {
    /**
     * Don't construct this class
     */
    private MobilityUtil() {
    }

    /**
     * Add Agent To Society. Opens a transaction if inTransaction is false;
     *
     * @param agentName Name of agent
     * @param logging logging service
     * @param domainService domain service
     * @param blackboardService blackboard service
     * @param inTransaction whether or not a transaction is open
     * @param nodeAgentId node id
     */
    public static void addAgent(String agentName, LoggingService logging,
        DomainService domainService, BlackboardService blackboardService,
        boolean inTransaction, MessageAddress nodeAgentId) {
        if (logging.isDebugEnabled()) {
            logging.debug("Add agent " + agentName);
        }

        MobilityFactory mobilityFactory = (MobilityFactory) domainService
            .getFactory("mobility");

        MessageAddress mobileAgentAddress = MessageAddress.getMessageAddress(agentName);

        //String destNode= this.nodeAgentId.toString();
        MessageAddress destNodeAddress = nodeAgentId;

        //Create ticket id	
        TicketIdentifier ticketIdentifier = (TicketIdentifier) mobilityFactory
            .createTicketIdentifier();

        //Create add ticket
        AddTicket addticket = new AddTicket(ticketIdentifier,
                mobileAgentAddress, destNodeAddress);

        //Create Add Agent control
        AgentControl addControl = mobilityFactory.createAgentControl(null,
                destNodeAddress, addticket);

        if (!inTransaction) {
            blackboardService.openTransaction();
        }

        blackboardService.publishAdd(addControl);

        if (!inTransaction) {
            blackboardService.closeTransaction();
        }

        if (logging.isDebugEnabled()) {
            logging.debug(
                "Just sent Message thru MessageTransport to NodeAgent to load agent - "
                + agentName);
        }
    }


    /**
     * Remove agent from society
     *
     * @param agentName
     * @param logging
     * @param domainService
     * @param blackboardService
     * @param inTransaction
     * @param nodeAgentId
     */
    public static void removeAgent(String agentName, LoggingService logging,
        DomainService domainService, BlackboardService blackboardService,
        boolean inTransaction, MessageAddress nodeAgentId) {
        if (logging.isDebugEnabled()) {
            logging.debug(" NOW removeAgent = " + agentName);
        }

        MobilityFactory mobilityFactory = (MobilityFactory) domainService
            .getFactory("mobility");

        MessageAddress mobileAgentAddress = MessageAddress.getMessageAddress(agentName);
        MessageAddress destNodeAddress = nodeAgentId;

        //Create ticket id	
        TicketIdentifier ticketIdentifier = (TicketIdentifier) mobilityFactory
            .createTicketIdentifier();

        //Create remove ticket
        RemoveTicket removeticket = new RemoveTicket(ticketIdentifier,
                mobileAgentAddress, destNodeAddress);

        //Create Agent Control for removal of agent
        AgentControl removeControl = mobilityFactory.createAgentControl(null,
                mobileAgentAddress, removeticket);

        if (!inTransaction) {
            blackboardService.openTransaction();
        }

        blackboardService.publishAdd(removeControl);

        if (!inTransaction) {
            blackboardService.closeTransaction();
        }

        if (logging.isDebugEnabled()) {
            logging.debug(
                "Just sent Message thru MessageTransport to NodeAgent to remove agent - "
                + agentName);
        }
    }


    /**
     * Add Plugin to agent
     *
     * @param pluginName
     * @param logging
     * @param agentContainmentService
     */
    public static void addPlugin(String pluginName, LoggingService logging,
        AgentContainmentService agentContainmentService) {
        ComponentDescription desc = createComponentDescriptionForPlugin(pluginName);

        if (logging.isDebugEnabled()) {
            logging.debug("Adding plugin to agent ContainmentService:"
                + pluginName);
        }

        agentContainmentService.add(desc);
    }


    /**
     * Remove plugin from agent
     *
     * @param pluginName
     * @param logging
     * @param agentContainmentService
     */
    public static void removePlugin(String pluginName, LoggingService logging,
        AgentContainmentService agentContainmentService) {
        ComponentDescription desc = createComponentDescriptionForPlugin(pluginName);

        if (logging.isDebugEnabled()) {
            logging.debug("Removing plugin to agent ContainmentService:"
                + pluginName);
        }

        agentContainmentService.remove(desc);
    }


    /**
     * Create component description
     *
     * @param pluginName fully specified name of the plugin
     *
     * @return the created ComponentDescription
     */
    public static ComponentDescription createComponentDescriptionForPlugin(
        String pluginName) {
        return new ComponentDescription(pluginName,
            "Node.AgentManager.Agent.PluginManager.Plugin", pluginName, null, //codebase url
            null, //parameters
            null, null, null);
    }
}
