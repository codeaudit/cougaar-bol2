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
 * Created on Apr 16, 2003
 *
 * To change this generated comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.cougaar.tutorial.booksonline.domain;


import org.cougaar.core.domain.DomainAdapter;
import org.cougaar.core.service.DomainService;
import org.cougaar.planning.ldm.LogPlan;
import org.cougaar.planning.ldm.LogPlanImpl;
import org.cougaar.planning.ldm.PlanningFactory;
import org.cougaar.planning.ldm.plan.AspectType;
import org.cougaar.planning.ldm.plan.AspectValue;
import org.cougaar.planning.ldm.plan.LongAspectValue;
import org.cougaar.planning.ldm.plan.TimeAspectValue;
import org.cougaar.planning.ldm.plan.AspectType.Factory;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;


/**
 * The Domain for Books Online.
 *
 * @author ttschampel
 */
public class BOLDomain extends DomainAdapter {
    //Aspect Type Factories
    /** Completed Aspect */
    Factory Completed = new Factory() {
            public int getKey() {
                return BolSocietyUtils.COMPLETED_ASPECT;
            }


            public String getName() {
                return "COMPLETED";
            }


            public AspectValue newAspectValue(Object o) {
                return LongAspectValue.create(BolSocietyUtils.COMPLETED_ASPECT,
                    o);
            }
        };

    /** Cancelled Aspect */
    Factory Cancelled = new Factory() {
            public int getKey() {
                return BolSocietyUtils.CANCELLED_ASPECT;
            }


            public String getName() {
                return "CANCELLED";
            }


            public AspectValue newAspectValue(Object o) {
                return LongAspectValue.create(BolSocietyUtils.CANCELLED_ASPECT,
                    o);
            }
        };

    /** Drop ship date aspect */
    Factory DropShipDate = new Factory() {
            public int getKey() {
                return BolSocietyUtils.DROP_SHIP_DATE_ASPECT;
            }


            public String getName() {
                return "DROPSHIPDATE";
            }


            public AspectValue newAspectValue(Object o) {
                return TimeAspectValue.create(BolSocietyUtils.DROP_SHIP_DATE_ASPECT,
                    o);
            }
        };

    /** Full load aspect */
    Factory FullLoad = new Factory() {
            public int getKey() {
                return BolSocietyUtils.FULL_LOAD_ASPECT;
            }


            public String getName() {
                return "FULLLOAD";
            }


            public AspectValue newAspectValue(Object o) {
                return LongAspectValue.create(BolSocietyUtils.FULL_LOAD_ASPECT,
                    o);
            }
        };

    /** pack date */
    Factory PackDate = new Factory() {
            public int getKey() {
                return BolSocietyUtils.PACK_DATE_ASPECT;
            }


            public String getName() {
                return "PACKDATE";
            }


            public AspectValue newAspectValue(Object o) {
                return TimeAspectValue.create(BolSocietyUtils.PACK_DATE_ASPECT,
                    o);
            }
        };

    /** ship date */
    Factory ShipDate = new Factory() {
            public int getKey() {
                return BolSocietyUtils.SHIP_DATE_ASPECT;
            }


            public String getName() {
                return "SHIPDATE";
            }


            public AspectValue newAspectValue(Object o) {
                return TimeAspectValue.create(BolSocietyUtils.SHIP_DATE_ASPECT,
                    o);
            }
        };

    /**
     * Get the Domain Name
     *
     * @return Domain name
     */
    public String getDomainName() {
        return "BOLDomain";
    }


    /**
     * Load the domain and custom aspect types used throughout BOL
     */
    public void load() {
        super.load();
        loadCustomAspectTypes();
    }


    /**
     * Load Custom AspectType Factories
     */
    private void loadCustomAspectTypes() {
        if (AspectType.registry.get(BolSocietyUtils.COMPLETED_ASPECT) == null) {
            AspectType.registry.registerFactory(Completed);
        }

        if (AspectType.registry.get(BolSocietyUtils.CANCELLED_ASPECT) == null) {
            AspectType.registry.registerFactory(Cancelled);
        }

        if (AspectType.registry.get(BolSocietyUtils.DROP_SHIP_DATE_ASPECT) == null) {
            AspectType.registry.registerFactory(DropShipDate);
        }

        if (AspectType.registry.get(BolSocietyUtils.FULL_LOAD_ASPECT) == null) {
            AspectType.registry.registerFactory(FullLoad);
        }

        if (AspectType.registry.get(BolSocietyUtils.PACK_DATE_ASPECT) == null) {
            AspectType.registry.registerFactory(PackDate);
        }

        if (AspectType.registry.get(BolSocietyUtils.SHIP_DATE_ASPECT) == null) {
            AspectType.registry.registerFactory(ShipDate);
        }
    }


    /**
     * Load BOLFactory
     */
    protected void loadFactory() {
        DomainService ds = (DomainService) getBindingSite().getServiceBroker()
                                                      .getService(this,
                DomainService.class, null);
        setFactory(new BOLFactory((PlanningFactory) ds.getFactory("planning")));

    }


    /**
     * Load Logic Providers
     */
    protected void loadLPs() {
    }


    /**
     * Load XPlan
     */
    protected void loadXPlan() {
        LogPlan logPlan =  new LogPlanImpl();
       
        setXPlan(logPlan);


    }
}
