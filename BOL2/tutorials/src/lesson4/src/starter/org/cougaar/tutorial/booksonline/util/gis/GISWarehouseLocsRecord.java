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


/**
 * <b>Description</b>: Internal representation of GIS Warehouse location
 * information. <br>
 * <br>
 * <b>Notes</b>:<br>
 * - This is strictly a data storage class.
 *
 * @author Ed Meier, &copy;2000 Clark Software Engineering, Ltd. & Defense
 *         Advanced Research Projects Agency (DARPA)
 * @version 1.0
 */
public class GISWarehouseLocsRecord {
  // ---------------------------------------------------------------------------------------------------------------------
  // Public Member Variables
  // ---------------------------------------------------------------------------------------------------------------------
  /** <b>Description</b>: Name of the city in which the warehouse resides. */
  public String warehouseCity;
  /** <b>Description</b>: Name of the city in which the depot resides. */
  public String depotCity;
  /**
   * <b>Description</b>: Number of days travel between warehouse and depot
   * city.
   */
  public int etaDays;
  /**
   * <b>Description</b>: Route number assinged to route between warehouse and
   * depot city.
   */
  public int routeNumber;

  // ---------------------------------------------------------------------------------------------------------------------
  // Public Constructor
  // ---------------------------------------------------------------------------------------------------------------------
  /**
   * <b>Description</b>: A simple default constructor, no arguments, completely
   * empty. <br>
   * <b>Notes</b>:<br>
   * - This class is strictly to encapsulate the warehouse depot GIS
   * information.
   */
  public GISWarehouseLocsRecord() {
  }
}
