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


package org.cougaar.tutorial.booksonline.util;


import java.io.Serializable;


/**
 * <b>Description</b>: UserDetails Utility class to model user information. <br>
 * <br>
 * <b>Notes</b>:<br>
 * -
 *
 * @author Frank Cooley, &copy;2000 Clark Software Engineering, Ltd. & Defense
 *         Advanced Research Projects Agency (DARPA)
 * @version 1.0
 */
public class UserDetails implements Serializable {
  /** username*/
  public String userName;
  /** address 1 */
  public String address1;
  /** address 2 */
  public String address2;
  /** city */
  public String city;
  /** state */
  public String state;
  /** zip */
  public String zip;
  /** credit card type */
  public String ccType;
  /** credit card number */
  public String ccNumber;
  /** credit card expiration date */
  public String ccExpDate;

  // ---------------------------------------------------------------------------------------------------------------------
  // Public Constructors
  // ---------------------------------------------------------------------------------------------------------------------
  /**
   * <b>Description</b>: Default constructor <br><b>Notes</b>:<br><br>
   */
  public UserDetails() {
  }


  /**
   * <b>Description</b>: constructor  to build the object <br><b>Notes</b>:<br>
   *
   * @param pname String
   * @param paddress1 String
   * @param paddress2 String
   * @param pcity String
   * @param pstate String
   * @param pzip String <br>
   */
  public UserDetails(String pname, String paddress1, String paddress2,
    String pcity, String pstate, String pzip) {
    userName = pname;
    address1 = paddress1;
    address2 = paddress2;
    city = pcity;
    state = pstate;
    zip = pzip;
  }


  /**
   * <b>Description</b>: constructor  to build the object <br><b>Notes</b>:<br>
   *
   * @param pname String
   * @param paddress1 String
   * @param paddress2 String
   * @param pcity String
   * @param pstate String
   * @param pzip String
   * @param ptype String
   * @param pnumber String
   * @param pexpdate String <br>
   */
  public UserDetails(String pname, String paddress1, String paddress2,
    String pcity, String pstate, String pzip, String ptype, String pnumber,
    String pexpdate) {
    userName = pname;
    address1 = paddress1;
    address2 = paddress2;
    city = pcity;
    state = pstate;
    zip = pzip;

    ccType = ptype;
    ccNumber = pnumber;
    ccExpDate = pexpdate;
  }

  // ---------------------------------------------------------------------------------------------------------------------
  // Public Member Methods
  // ---------------------------------------------------------------------------------------------------------------------
  /**
   * <b>Description</b>: getCity - return the city <br><b>Notes</b>:<br>
   *
   * @return String <br>
   */
  public String getCity() {
    return city;
  }


  /**
   * <b>Description</b>: equals <br><b>Notes</b>:<br>
   *
   * @param object Object
   *
   * @return boolean <br>
   */
  public boolean equals(Object object) {
    if (object.toString().equals(this.toString())) {
      return (true);
    }

    return (false);
  }


  /**
   * <b>Description</b>: toString <br><b>Notes</b>:<br>
   *
   * @return String <br>
   */
  public String toString() {
    return (new String(userName + " " + address1 + " " + address2 + " " + city
      + " " + state + " " + zip));

  }
}
