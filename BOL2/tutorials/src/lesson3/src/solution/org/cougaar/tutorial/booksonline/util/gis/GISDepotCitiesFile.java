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


package org.cougaar.tutorial.booksonline.util.gis;


import java.util.Hashtable;


/**
 * <b>Description</b>: Read from the file containing our depots and the cities
 * they serve. <br>
 * <br>
 * <b>Notes</b>:<br>
 * - The file is tab delimited
 *
 * @author Ed Meier, &copy;2000 Clark Software Engineering, Ltd. & Defense
 *         Advanced Research Projects Agency (DARPA)
 * @version 1.0
 */
public class GISDepotCitiesFile {
  private Hashtable byDeliveryCity = new Hashtable(5);

  // ---------------------------------------------------------------------------------------------------------------------
  // Public Constructor
  // ---------------------------------------------------------------------------------------------------------------------
  /**
   * <b>Description</b>: Read in the contents of the file into internal
   * storage. <br>
   *
   * @param fileName Relative path to the file to read in.
   */
  public GISDepotCitiesFile(String fileName) {
    String depotCity = null; // need to keep a separate copy
    String fileRecord;
    try {
      java.io.BufferedReader invFile = new java.io.BufferedReader(new java.io.FileReader(
            fileName));

      while ((fileRecord = invFile.readLine()) != null) {
        if (fileRecord.length() <= 0) {
          continue;
        }

        if (fileRecord.charAt(0) != '#') // ignore comments
         {
          if (fileRecord.charAt(0) != '\t') {
            // we only read warehouseCity once for all its following records
            depotCity = new String(fileRecord.trim());
          }
          else {
            int epos = fileRecord.indexOf('\t');
            int bpos = epos + 1;
            epos = fileRecord.indexOf('\t', bpos);
            String deliveryCity = null;
            if (epos > 0) {
              deliveryCity = new String(fileRecord.substring(bpos, epos));
            } else {
              deliveryCity = new String(fileRecord.substring(bpos));
            }

            //             System.out.println ("deliveryCity: " + deliveryCity + "  depotCity: " + depotCity );
            byDeliveryCity.put(deliveryCity, depotCity);

          }
        }
      }
    }
     catch (java.io.FileNotFoundException fnf) {
      System.err.println("reference file " + fileName
        + " in inventory data file, bolinventory.txt, not found");
    }
     catch (java.io.IOException ioexc) {
      System.err.println("can't communicate with reference file " + fileName
        + " in inventory data file, bolinventory.txt, IO exception");

    }
  }

  // ---------------------------------------------------------------------------------------------------------------------
  // Public Member Methods
  // ---------------------------------------------------------------------------------------------------------------------
  /**
   * <b>Description</b>: Return the internal hashtable. <br>
   * <b>Notes</b>:<br>
   * - No clone is made of the hashtable <br>
   *
   * @return Hashtable, keys are city to deliver to, values are the servicing
   *         depot
   */
  public Hashtable getByDeliveryCityHashtable() {
    return byDeliveryCity;
  }
}
