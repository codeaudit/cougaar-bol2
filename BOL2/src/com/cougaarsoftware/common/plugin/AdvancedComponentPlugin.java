/*
 * <copyright>
 *  Copyright 2000-2003 Cougaar Software, Inc.
 *  All Rights Reserved
 * </copyright>
 */


package com.cougaarsoftware.common.plugin;


import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.cougaar.core.component.ServiceBroker;
import org.cougaar.core.node.NodeIdentificationService;
import org.cougaar.core.plugin.ComponentPlugin;
import org.cougaar.core.service.AgentContainmentService;
import org.cougaar.core.service.DomainService;
import org.cougaar.core.service.LoggingService;
import org.cougaar.core.service.UIDService;
import org.cougaar.core.service.community.CommunityService;
import org.cougaar.planning.ldm.PlanningFactory;
import org.cougaar.planning.ldm.asset.ItemIdentificationPG;
import org.cougaar.planning.ldm.asset.NewItemIdentificationPG;
import org.cougaar.planning.ldm.asset.NewTypeIdentificationPG;
import org.cougaar.planning.ldm.asset.TypeIdentificationPG;
import org.cougaar.planning.ldm.plan.AllocationResult;
import org.cougaar.planning.ldm.plan.AspectType;
import org.cougaar.planning.ldm.plan.AspectValue;
import org.cougaar.planning.ldm.plan.Constraint;
import org.cougaar.planning.ldm.plan.Disposition;
import org.cougaar.planning.ldm.plan.NewConstraint;
import org.cougaar.planning.ldm.plan.NewTask;
import org.cougaar.planning.ldm.plan.NewWorkflow;
import org.cougaar.planning.ldm.plan.PlanElement;
import org.cougaar.planning.ldm.plan.Preference;
import org.cougaar.planning.ldm.plan.PrepositionalPhrase;
import org.cougaar.planning.ldm.plan.ScoringFunction;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.planning.ldm.plan.TimeAspectValue;
import org.cougaar.planning.ldm.plan.Verb;
import org.cougaar.planning.ldm.plan.Workflow;
import org.cougaar.planning.service.PrototypeRegistryService;

import com.cougaarsoftware.util.MobilityUtil;


/**
 * Support variables and functions for the Cougaar Software Collaborative
 * Supply Chain Management family of applications.  This helps ensure
 * consistent verb definition and referencing.
 *
 * @author Todd M. Carrico, ttschampel, soster
 * @version 1.0
 */
public abstract class AdvancedComponentPlugin extends ComponentPlugin {
    /** Logging Service */
    protected LoggingService logging;
    private boolean loggingActive = false;
    /** Community Service */
    protected CommunityService communityService;
    private boolean communityServiceActive = false;
    /** Plugin parameters */
    protected Map pluginParameters = null;
    /** Agent Containment service */
    protected AgentContainmentService agentContainmentService;
    private boolean agentContainmentServiceActive = false;
    /** Node identification service */
    protected NodeIdentificationService nodeIDService;
    private boolean nodeIDServiceActive = false;
    /** Domain Service */
    protected DomainService domainService;
    private boolean domainServiceActive;
    private boolean prototypeRegistryServiceActive = false;
    /** Prototype Registry Service */
    protected PrototypeRegistryService prototypeRegistryService;
    private boolean uidServiceActive = false;
    /** UIDService */
    protected UIDService uidService;

    //    /** Aspect type value for completion preferences */
    //    protected int aspectTypeComplete;
    //    /** AspectValue value for completion preference */
    //    protected double aspectValueComplete;
    //    /** AspectValue value for in complete completion preference */
    //    protected double aspectValueIncomplete;
    /**
     * Creates new AdvancedComponentPlugIn
     */
    public AdvancedComponentPlugin() {
        super();
    }

    /**
     * set UIDService at load time
     *
     * @param service UIDService
     */
    public void setUIDService(UIDService service) {
        this.uidService = service;
        this.uidServiceActive = true;
    }


    /**
     * Get UIDService
     *
     * @return UIDService
     */
    public UIDService getUIDService() {
        return this.uidService;
    }


    /**
     * set PrototypeRegistryService at load time
     *
     * @param service PrototypeRegistryService
     */
    public void setPrototypeRegistryService(PrototypeRegistryService service) {
        this.prototypeRegistryService = service;
        this.prototypeRegistryServiceActive = true;
    }


    /**
     * get PrototypeRegistryService
     *
     * @return PrototypeRegistryService
     */
    public PrototypeRegistryService getPrototypeRegistryService() {
        return this.prototypeRegistryService;
    }


    /**
     * set DomainService at load time
     *
     * @param service DomainService
     */
    public void setDomainService(DomainService service) {
        this.domainService = service;
        this.domainServiceActive = true;
    }


    /**
     * Get DomainService
     *
     * @return DomainService
     */
    public DomainService getDomainService() {
        return this.domainService;
    }


   

    


    /**
     * Set LoggingService at load time
     *
     * @param service LoggingService
     */
    public void setLoggingService(LoggingService service) {
        this.logging = service;
        this.loggingActive = true;
    }


    /**
     * Get LoggingService
     *
     * @return LoggingService
     */
    public LoggingService getLoggingService() {
        return this.logging;
    }


    /**
     * Get a reference to the community service
     *
     * @return the community service
     */
    public CommunityService getCommunityService() {
        return this.communityService;
    }


    /**
     * Set community service at load time
     *
     * @param service CommunityService
     */
    public void setCommunityService(CommunityService service) {
        this.communityService = service;
        communityServiceActive = true;
    }


    /**
     * Get node identification service at load time
     *
     * @param service NodeIdentificationService
     */
    public void setNodeIdentificationService(NodeIdentificationService service) {
        this.nodeIDService = service;
        this.nodeIDServiceActive = true;
    }


    /**
     * Get Node Identification Service at load time
     *
     * @return NodeIdentificationService
     */
    public NodeIdentificationService getNodeIdentificationService() {
        return this.nodeIDService;
    }


    /**
     * Set AgentContainmentService at load time
     *
     * @param service AgentContainmentService
     */
    public void setAgentContainmentService(AgentContainmentService service) {
        this.agentContainmentService = service;
        this.agentContainmentServiceActive = true;
    }


    /**
     * Get AgentContainmentService
     *
     * @return AgentContainmentService
     */
    public AgentContainmentService getAgentContainmentService() {
        return this.agentContainmentService;
    }


    /**
     * Release logging service
     */
    protected void closeLogging() {
        ServiceBroker sb = getBindingSite().getServiceBroker();
        if (loggingActive) {
            sb.releaseService(this, LoggingService.class, logging);
            loggingActive = false;
        }
    }


    /**
     * Release Node ID service
     */
    protected void closeNodeIDService() {
        ServiceBroker sb = getBindingSite().getServiceBroker();
        if (nodeIDServiceActive) {
            sb.releaseService(this, NodeIdentificationService.class,
                nodeIDService);
            nodeIDServiceActive = false;
        }
    }


    /**
     * Release Containment Service
     */
    protected void closeAgentContainment() {
        ServiceBroker sb = getBindingSite().getServiceBroker();
        if (agentContainmentServiceActive) {
            sb.releaseService(this, AgentContainmentService.class,
                agentContainmentService);
            agentContainmentServiceActive = false;
        }
    }


    /**
     * Release Domain Service
     */
    protected void closeDomainService() {
        ServiceBroker sb = getBindingSite().getServiceBroker();
        if (domainServiceActive) {
            sb.releaseService(this, DomainService.class, domainService);
            domainServiceActive = false;
        }
    }


  


    /**
     * Release the community service
     */
    protected void closeCommunityService() {
        ServiceBroker sb = getBindingSite().getServiceBroker();
        if (communityServiceActive) {
            sb.releaseService(this, CommunityService.class, communityService);
            communityServiceActive = false;
        }
    }


    /**
     * Release PrototypeRegistryService
     */
    protected void closePrototypeRegistryService() {
        ServiceBroker sb = getBindingSite().getServiceBroker();
        if (prototypeRegistryServiceActive) {
            sb.releaseService(this, PrototypeRegistryService.class,
                prototypeRegistryService);
            this.prototypeRegistryServiceActive = false;
        }
    }


    /**
     * Release the UIDService
     */
    protected void closeUIDService() {
        ServiceBroker sb = getBindingSite().getServiceBroker();
        if (this.uidServiceActive) {
            sb.releaseService(this, UIDService.class, uidService);
            this.uidServiceActive = false;
        }
    }


    /**
     * Release all services
     */
    public void unload() {
        super.unload();
        closeLogging();
       closeCommunityService();
        closeAgentContainment();
        closeDomainService();
        closeNodeIDService();
        closePrototypeRegistryService();
        closeUIDService();
    }


    /**
     * Get the END_TIME preference for the task
     *
     * @param t the task
     *
     * @return the END_TIME preference for the task
     */
    public long getEndTime(Task t) {
        long end = 0;
        Preference pref = getPreference(t, AspectType.END_TIME);
        if (pref != null) {
            TimeAspectValue tav = (TimeAspectValue) pref.getScoringFunction()
                                                        .getBest()
                                                        .getAspectValue();
            end = tav.timeValue();
        }

        return end;
    }


    /**
     * Get the START_TIME preference for the task
     *
     * @param t the task
     *
     * @return the START_TIME preference for the task or 0
     */
    public long getStartTime(Task t) {
        long start = 0;
        Preference pref = getPreference(t, AspectType.START_TIME);
        if (pref != null) {
            TimeAspectValue tav = (TimeAspectValue) pref.getScoringFunction()
                                                        .getBest()
                                                        .getAspectValue();
            start = tav.timeValue();
        }

        return start;
    }


    /**
     * Get the DURATION preference for the task
     *
     * @param t the task
     *
     * @return the DURATION preference or 0
     */
    protected int getDuration(Task t) {
        double start = 0.0;
        Preference pref = getPreference(t, AspectType.DURATION);
        if (pref != null) {
            start = pref.getScoringFunction().getBest().getAspectValue()
                        .getValue();
        }

        return (int) start;
    }


    /**
     * Return the preference for the given aspect
     *
     * @param task for which to return given preference
     * @param aspect_type aspect type
     *
     * @return Preference (or null) from task for given aspect
     */
    public Preference getPreference(Task task, int aspect_type) {
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
     * Return the preference for the given aspect
     *
     * @param task for which to return given preference
     * @param phrase aspect type
     *
     * @return Preference (or null) from task for given aspect
     */
    public PrepositionalPhrase getPreposition(Task task, String phrase) {
        PrepositionalPhrase pPhrase = null;
        for (Enumeration e = task.getPrepositionalPhrases();
            e.hasMoreElements();) {
            PrepositionalPhrase prep = (PrepositionalPhrase) e.nextElement();
            if (prep.getPreposition().equals(phrase)) {
                pPhrase = prep;
                break;
            }
        }

        return pPhrase;
    }


    /**
     * Conveinence method to get the IndirectObject from the task for the named
     * PrepositionalPhrase
     *
     * @param task the task
     * @param phrase the phrase of the PrepositionalPhrase
     *
     * @return the IndirectObject
     */
    public String getPrepositionValue(Task task, String phrase) {
        PrepositionalPhrase pPhrase = null;
        for (Enumeration e = task.getPrepositionalPhrases();
            e.hasMoreElements();) {
            PrepositionalPhrase prep = (PrepositionalPhrase) e.nextElement();
            if (prep.getPreposition().equals(phrase)) {
                pPhrase = prep;
                break;
            }
        }

        return (String) pPhrase.getIndirectObject();
    }


    /**
     * Conveinence method to setup start, end, and duration time preferences
     * with strict scoring functions.
     *
     * @param new_task the task to attach the preferences to
     * @param start the start time in milliseconds
     * @param duration the duration in milliseconds
     * @param deadline the end time in milliseconds
     *
     * @throws IllegalArgumentException throw if start > deadline or duration >
     *         (deadline - start)
     */
    public void setTimePreferences(NewTask new_task, long start, long duration,
        long deadline) {
        if (start > deadline) {
            throw new IllegalArgumentException(
                "Inconsistent Arguments: task cannot end before it starts.");
        } else if (duration > (deadline - start)) {
            throw new IllegalArgumentException(
                "Inconsistent Arguments: duration cannot be longer than deadline - start.");
        }

        // Establish preferences for task
        Vector preferences = new Vector();

        // Add a start_time, end_time, and duration strict preference
        ScoringFunction scorefcn = ScoringFunction.createStrictlyAtValue(TimeAspectValue
                .newAspectValue(AspectType.START_TIME, start));
        Preference pref = getPlanningFactory().newPreference(AspectType.START_TIME,
                scorefcn);
        preferences.add(pref);

        scorefcn = ScoringFunction.createStrictlyAtValue(TimeAspectValue
                .newAspectValue(AspectType.END_TIME, deadline));
        pref = getPlanningFactory().newPreference(AspectType.END_TIME, scorefcn);
        preferences.add(pref);

        scorefcn = ScoringFunction.createStrictlyAtValue(TimeAspectValue
                .newAspectValue(AspectType.DURATION, duration));
        pref = getPlanningFactory().newPreference(AspectType.DURATION, scorefcn);
        preferences.add(pref);

        new_task.setPreferences(preferences.elements());
    }


    /**
     * Conveience method for getting the PlanningFactory
     *
     * @return the PlanningFactory
     */
    protected PlanningFactory getPlanningFactory() {
        return ((PlanningFactory) getDomainService().getFactory("planning"));
    }


    /**
     * Create and populate an ItemIdentification property group
     *
     * @param name itemID for the NewItemIdentificationPG
     *
     * @return the created ItemIdentificationPG
     */
    protected ItemIdentificationPG getItemIdentificationPG(String name) {
        NewItemIdentificationPG new_item_id_pg = (NewItemIdentificationPG) getPlanningFactory()
                                                                               .createPropertyGroup("ItemIdentificationPG");
        new_item_id_pg.setItemIdentification(name);
        return new_item_id_pg;
    }


    /**
     * Create and populate an TypeIdentification property group
     *
     * @param name the typeid for the TypeIdentificationPG
     *
     * @return the create TypeIdentificationPG
     */
    protected TypeIdentificationPG getTypeIdentificationPG(String name) {
        NewTypeIdentificationPG new_type_id_pg = (NewTypeIdentificationPG) getPlanningFactory()
                                                                               .createPropertyGroup("TypeIdentificationPG");
        new_type_id_pg.setTypeIdentification(name);
        return new_type_id_pg;
    }


    /**
     * Conveinence method to setup  end and duration time preferences with
     * strict scoring functions.
     *
     * @param new_task the task to attach the preferences to
     * @param duration the duration in milliseconds
     * @param deadline the end time in milliseconds
     */
    public void setTimePreferences(NewTask new_task, long duration,
        long deadline) {
        // Establish preferences for task (just duration and deadline, not start)
        Vector preferences = new Vector();

        ScoringFunction scorefcn = ScoringFunction.createStrictlyAtValue(TimeAspectValue
                .newAspectValue(AspectType.DURATION, duration));
        Preference pref = getPlanningFactory().newPreference(AspectType.DURATION,
                scorefcn);
        preferences.add(pref);

        scorefcn = ScoringFunction.createStrictlyAtValue(TimeAspectValue
                .newAspectValue(AspectType.END_TIME, deadline));
        pref = getPlanningFactory().newPreference(AspectType.END_TIME, scorefcn);
        preferences.add(pref);

        new_task.setPreferences(preferences.elements());
    }


    // Create a date at a given year/month/day
    public static java.util.Date createDate(int year, int month, int day) {
        Calendar my_calendar = Calendar.getInstance();
        my_calendar.set(year, month - 1, day, 0, 0, 0);
        return my_calendar.getTime();
    }


    /**
     * Load the plugin an setup logging.
     */
    public void load() {
        super.load();
    }


    /**
     * Read the Plugin parameters(Accepts key/value pairs) Typically, put in
     * load() of plugins...like <code> public void load() { super.load();
     * pluginParameters = readParamters(); someVariable =
     * (String)map.get(ParamName); }</code>
     *
     * @return map of key value pairs or null when params are empty
     */
    protected Map readParameters() {
        Collection p = getParameters();

        if (p.isEmpty()) {
            logging.debug("No Paramters found...");
            return null;
        }

        HashMap map = new HashMap();
        int idx;

        for (Iterator i = p.iterator(); i.hasNext();) {
            String s = (String) i.next();
            if ((idx = s.indexOf('=')) != -1) {
                String key = s.substring(0, idx);
                String value = s.substring(idx + 1, s.length());
                map.put(key.trim(), value.trim());
            }
        }

        return map;
    }


    /**
     * Create a task.
     *
     * @param verb the Verb to attach to the created Task
     * @param parent_task the Task to set as the parent of the created task
     * @param wf the Workflow to attach the task to
     *
     * @return the constructed Task
     */
    protected NewTask makeTask(String verb, Task parent_task, Workflow wf) {
        return makeTask(Verb.getVerb(verb), parent_task, wf);
    }


    /**
     * Create a task.
     *
     * @param verb the Verb to attach to the created Task
     * @param parent_task the Task to set as the parent of the created task
     * @param wf the Workflow to attach the task to
     *
     * @return the constructed Task
     */
    protected NewTask makeTask(Verb verb, Task parent_task, Workflow wf) {
        NewTask new_task = getPlanningFactory().newTask();

        new_task.setParentTask(parent_task);
        new_task.setWorkflow(wf);
        //added 6/23/2003  MA
        ((NewWorkflow) wf).addTask(new_task);
        // Set the verb as given
        new_task.setVerb(verb);

        // Copy important fields from the parent task
        new_task.setPlan(parent_task.getPlan());
        new_task.setDirectObject(parent_task.getDirectObject());
        new_task.setPrepositionalPhrases(parent_task.getPrepositionalPhrases());

        return new_task;
    }


    /**
     * Build a Constraint
     *
     * @param constrainingTask Constraining Task
     * @param constrainedTask Constrained Task
     * @param constraintType Type of Constraint
     * @param constrainingAspectType Constraining Aspect Type
     * @param constrainedAspectType Constrained Aspect Type
     *
     * @return new Constraint
     */
    protected Constraint buildConstraint(Task constrainingTask,
        Task constrainedTask, int constraintType, int constrainingAspectType,
        int constrainedAspectType) {
        NewConstraint c1 = getPlanningFactory().newConstraint();
        c1.setConstrainingTask(constrainingTask);
        c1.setConstrainingAspect(constrainingAspectType);
        c1.setConstrainedTask(constrainedTask);
        c1.setConstrainedAspect(constrainedAspectType);
        c1.setConstraintOrder(constraintType);
        return (c1);

    }


    /**
     * Build contraint with no aspects to compare.  Meaning not interpreted by
     * infrastructure.
     *
     * @param constrainingTask
     * @param constrainedTask
     * @param constraintType
     *
     * @return
     */
    protected Constraint buildSimpleConstraint(Task constrainingTask,
        Task constrainedTask, int constraintType) {
        NewConstraint c1 = getPlanningFactory().newConstraint();
        c1.setConstrainingTask(constrainingTask);
        c1.setConstrainedTask(constrainedTask);
        c1.setConstraintOrder(constraintType);
        return (c1);
    }


    /**
     * Sets a task to completed.  For plugins that use this method, complete is
     * assumed to mean a confidence value of 1.0 and isSuccess should return
     * true.
     *
     * @param task the <code>Task</code> to set to complete
     */
    protected void setCompleted(Task task) {
        if (logging.isDebugEnabled()) {
            logging.debug("Setting :" + task + " to complete");
        }

        AspectValue[] values = new AspectValue[2];
        values[0] = AspectValue.newAspectValue(AspectType.END_TIME,
                System.currentTimeMillis());
        values[1] = AspectValue.newAspectValue(AspectType.START_TIME,
                System.currentTimeMillis());

        AllocationResult result = getPlanningFactory().newAllocationResult(1.0,
                true, values);
        if (task.getPlanElement() == null) {
            Disposition disp = getPlanningFactory().createDisposition(task
                    .getPlan(), task, result);
            disp.setEstimatedResult(result);
            disp.setObservedResult(result);

            getBlackboardService().publishAdd(disp);
        } else {
            PlanElement pe = task.getPlanElement();
            pe.setEstimatedResult(result);
            pe.setObservedResult(result);

            getBlackboardService().publishChange(pe);
        }
    }


    /**
     * Sets a task to completed.  For plugins that use this method, complete is
     * assumed to mean a confidence value of 1.0 and isSuccess should return
     * true.
     *
     * @param task the <code>Task</code> to set to complete
     * @param success <code>boolean</code> indicating if task was successfull
     *        or not
     */
    protected void setCompleted(Task task, boolean success) {
        if (logging.isDebugEnabled()) {
            logging.debug("Setting :" + task + " to complete");
        }

        AspectValue[] values = new AspectValue[2];
        values[0] = AspectValue.newAspectValue(AspectType.END_TIME,
                System.currentTimeMillis());
        values[1] = AspectValue.newAspectValue(AspectType.START_TIME,
                System.currentTimeMillis());

        AllocationResult result = getPlanningFactory().newAllocationResult(1.0,
                success, values);
        if (task.getPlanElement() == null) {
            Disposition disp = getPlanningFactory().createDisposition(task
                    .getPlan(), task, result);
            disp.setEstimatedResult(result);
            disp.setObservedResult(result);

            getBlackboardService().publishAdd(disp);
        } else {
            PlanElement pe = task.getPlanElement();
            pe.setEstimatedResult(result);
            pe.setObservedResult(result);

            getBlackboardService().publishChange(pe);
        }
    }


     /**
     * Determines if a specified task is completed by having a plan element
     * with reported result with 100% confidence, and success.
     *
     * @param task the Task to check for completion of
     *
     * @return true iff the the task is complete by the criteria described
     *         above
     */
    protected boolean isCompleted(Task task) {
        PlanElement pe = task.getPlanElement();
        if (pe == null) {
            return false;
        }

        AllocationResult ar = pe.getReportedResult();
        if (ar != null) {
            return ar.isSuccess() && (ar.getConfidenceRating() == 1.0);
        } else {
            return false;
        }
    }


    /**
     * Determines if a specified task is failed by having a plan element with
     * reported result with 100% confidence, and failure.
     *
     * @param task the Task to check for completion of
     *
     * @return true iff the the task is complete by the criteria described
     *         above
     */
    protected boolean isFailure(Task task) {
        PlanElement pe = task.getPlanElement();
        if (pe == null) {
            return false;
        }

        AllocationResult ar = pe.getReportedResult();
        if (ar != null) {
            return !ar.isSuccess() && (ar.getConfidenceRating() == 1.0);
        } else {
            return false;
        }
    }


    /**
     * Utility method to cache a prototype instance
     *
     * @param type Class of prototype to create
     * @param cacheName Name to cache prototype under in the prototype registry
     *        service
     */
    protected void cachePrototype(Class type, String cacheName) {
        getPrototypeRegistryService().cachePrototype(cacheName,
            getPlanningFactory().createPrototype(type, cacheName));
    }


    /**
     * Create a new instance of an asset
     *
     * @param identifier
     * @param cacheName
     *
     * @return
     */
    protected Object createAsset(String identifier, String cacheName) {
        return createAsset(identifier, cacheName, true);
    }


    /**
     * Create a new instance of an asset
     *
     * @param identifier
     * @param cacheName
     * @param appendUniqueId
     *
     * @return
     */
    protected Object createAsset(String identifier, String cacheName,
        boolean appendUniqueId) {
        String id = identifier;
        if (appendUniqueId) {
            id = identifier + this.getUIDService().nextUID().toString();
        }

        return getPlanningFactory().createInstance(cacheName, id);
    }


   


    /**
     * Add an Agent to this Node. Will open a transaction if inTransaction is
     * false
     *
     * @param agentName the name of the Agent to create and add
     * @param inTransaction whether or not a transaction is currently open
     */
    protected void addAgent(String agentName, boolean inTransaction) {
        MobilityUtil.addAgent(agentName, logging, getDomainService(),
            getBlackboardService(), inTransaction,
            this.nodeIDService.getMessageAddress());
    }


    /**
     * Remove the specified agent from the node. Will open a new transaction if
     * inTransaction is false.
     *
     * @param agentName agent to remove
     * @param inTransaction set to true if a transaction is currently open.
     */
    protected void removeAgent(String agentName, boolean inTransaction) {
        MobilityUtil.removeAgent(agentName, logging, getDomainService(),
            getBlackboardService(), inTransaction,
            this.nodeIDService.getMessageAddress());
    }


    /**
     * Add a plugin to this agent dynamically.
     *
     * @param pluginName the fully specifed plugin name
     */
    protected void addPlugin(String pluginName) {
        MobilityUtil.addPlugin(pluginName, logging, this.agentContainmentService);
    }


    /**
     * Remove a plugin from this agent dynamically.
     *
     * @param pluginName the fully specifed plugin name
     */
    protected void removePlugin(String pluginName) {
        MobilityUtil.removePlugin(pluginName, logging,
            this.agentContainmentService);
    }
}
