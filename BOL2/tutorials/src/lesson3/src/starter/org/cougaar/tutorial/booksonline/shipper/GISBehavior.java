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


package org.cougaar.tutorial.booksonline.shipper;


import org.cougaar.planning.ldm.asset.Asset;
import org.cougaar.planning.ldm.asset.PGDelegate;
import org.cougaar.planning.ldm.asset.PropertyGroup;
import org.cougaar.tutorial.booksonline.assets.GISPG;
import org.cougaar.tutorial.booksonline.util.gis.GISDepotCitiesFile;
import org.cougaar.tutorial.booksonline.util.gis.GISWarehouseLocsFile;
import org.cougaar.tutorial.booksonline.util.gis.GISWarehouseLocsRecord;

import java.util.Hashtable;


/**
 * 
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class GISBehavior extends Asset implements PGDelegate {
    private GISPG gisPG;
    private Hashtable routes;
    private Hashtable depotCities;
    private Hashtable etaTable;

    // this constructor is called by the constructor of GISPG
    public GISBehavior(GISPG pg) {
        gisPG = pg;


        //    depotCities = gisFile.getDepots ();
        //    etaTable = gisWareLocsFile.getETAtable ();
    }

    // here are the delegated-to methods
    public double calcETA(String destDepot) {
        // load the data from the files
        GISWarehouseLocsFile gisWareLocsFile = new GISWarehouseLocsFile(
                "GISWarehouseLocs.txt");

        GISWarehouseLocsRecord locRec = (GISWarehouseLocsRecord) gisWareLocsFile.getByDepotHashtable()
                                                                                .get(destDepot);

        return locRec.etaDays + 1; // always add a day for local shipping time
    }


    /**
     * Gets the nearest depot
     *
     * @param destCity Destination city
     * @param destState Destination state
     *
     * @return nearest depot
     */
    public String getNearestDepot(String destCity, String destState) {
        // load the data from the files
        GISDepotCitiesFile depCitsFile = new GISDepotCitiesFile(
                "GISDepotCities.txt");

        String depot = (String) depCitsFile.getByDeliveryCityHashtable().get(destCity);

        if (depot == null) {
            depot = new String();
        }

        return depot;

    }


    /**
     * Get the route number for a destination depot
     *
     * @param destDepot destinaton depot
     *
     * @return route number
     */
    public int getRouteNumber(String destDepot) {
        // load the data from the files
        GISWarehouseLocsFile gisWareLocsFile = new GISWarehouseLocsFile(
                "GISWarehouseLocs.txt");

        GISWarehouseLocsRecord locRec = (GISWarehouseLocsRecord) gisWareLocsFile.getByDepotHashtable()
                                                                                .get(destDepot);

        if (locRec != null) {
            return locRec.routeNumber;
        } else {
            return 0;
        }
    }


    // implement PGDelegate
    public PGDelegate copy(PropertyGroup pg) {
        return new GISBehavior((GISPG) pg);
    }
}
