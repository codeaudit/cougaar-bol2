/*
 * <copyright>
 *  Copyright 1997-2003 BBNT Solutions, LLC
 *  under sponsorship of the Defense Advanced Research Projects Agency (DARPA).
 * 
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the Cougaar Open Source License as published by
 *  DARPA on the Cougaar Open Source Website (www.cougaar.org).
 * 
 *  THE COUGAAR SOFTWARE AND ANY DERIVATIVE SUPPLIED BY LICENSOR IS
 *  PROVIDED 'AS IS' WITHOUT WARRANTIES OF ANY KIND, WHETHER EXPRESS OR
 *  IMPLIED, INCLUDING (BUT NOT LIMITED TO) ALL IMPLIED WARRANTIES OF
 *  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE, AND WITHOUT
 *  ANY WARRANTIES AS TO NON-INFRINGEMENT.  IN NO EVENT SHALL COPYRIGHT
 *  HOLDER BE LIABLE FOR ANY DIRECT, SPECIAL, INDIRECT OR CONSEQUENTIAL
 *  DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE OF DATA OR PROFITS,
 *  TORTIOUS CONDUCT, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 *  PERFORMANCE OF THE COUGAAR SOFTWARE.
 * </copyright>
 */

/* @generated Fri Jul 25 10:24:45 EDT 2003 from properties.def - DO NOT HAND EDIT */
/** AbstractFactory implementation for Properties.
 * Prevents clients from needing to know the implementation
 * class(es) of any of the properties.
 **/

package org.cougaar.tutorial.booksonline.assets;




public class PropertyGroupFactory {
  // brand-new instance factory
  public static NewVehiclePG newVehiclePG() {
    return new VehiclePGImpl();
  }
  // instance from prototype factory
  public static NewVehiclePG newVehiclePG(VehiclePG prototype) {
    return new VehiclePGImpl(prototype);
  }

  // brand-new instance factory
  public static NewAuthorPG newAuthorPG() {
    return new AuthorPGImpl();
  }
  // instance from prototype factory
  public static NewAuthorPG newAuthorPG(AuthorPG prototype) {
    return new AuthorPGImpl(prototype);
  }

  // brand-new instance factory
  public static NewPackTimePG newPackTimePG() {
    return new PackTimePGImpl();
  }
  // instance from prototype factory
  public static NewPackTimePG newPackTimePG(PackTimePG prototype) {
    return new PackTimePGImpl(prototype);
  }

  // brand-new instance factory
  public static NewInvListPG newInvListPG() {
    return new InvListPGImpl();
  }
  // instance from prototype factory
  public static NewInvListPG newInvListPG(InvListPG prototype) {
    return new InvListPGImpl(prototype);
  }

  // brand-new instance factory
  public static NewShipperPG newShipperPG() {
    return new ShipperPGImpl();
  }
  // instance from prototype factory
  public static NewShipperPG newShipperPG(ShipperPG prototype) {
    return new ShipperPGImpl(prototype);
  }

  // brand-new instance factory
  public static NewStockPG newStockPG() {
    return new StockPGImpl();
  }
  // instance from prototype factory
  public static NewStockPG newStockPG(StockPG prototype) {
    return new StockPGImpl(prototype);
  }

  // brand-new instance factory
  public static NewPricePG newPricePG() {
    return new PricePGImpl();
  }
  // instance from prototype factory
  public static NewPricePG newPricePG(PricePG prototype) {
    return new PricePGImpl(prototype);
  }

  // brand-new instance factory
  public static NewSpecificsPG newSpecificsPG() {
    return new SpecificsPGImpl();
  }
  // instance from prototype factory
  public static NewSpecificsPG newSpecificsPG(SpecificsPG prototype) {
    return new SpecificsPGImpl(prototype);
  }

  // brand-new instance factory
  public static NewAccountPG newAccountPG() {
    return new AccountPGImpl();
  }
  // instance from prototype factory
  public static NewAccountPG newAccountPG(AccountPG prototype) {
    return new AccountPGImpl(prototype);
  }

  // brand-new instance factory
  public static NewReviewPG newReviewPG() {
    return new ReviewPGImpl();
  }
  // instance from prototype factory
  public static NewReviewPG newReviewPG(ReviewPG prototype) {
    return new ReviewPGImpl(prototype);
  }

  // brand-new instance factory
  public static NewWareHouseDescriptionPG newWareHouseDescriptionPG() {
    return new WareHouseDescriptionPGImpl();
  }
  // instance from prototype factory
  public static NewWareHouseDescriptionPG newWareHouseDescriptionPG(WareHouseDescriptionPG prototype) {
    return new WareHouseDescriptionPGImpl(prototype);
  }

  // brand-new instance factory
  public static NewPublisherPG newPublisherPG() {
    return new PublisherPGImpl();
  }
  // instance from prototype factory
  public static NewPublisherPG newPublisherPG(PublisherPG prototype) {
    return new PublisherPGImpl(prototype);
  }

  // brand-new instance factory
  public static NewGISPG newGISPG() {
    return new GISPGImpl();
  }
  // instance from prototype factory
  public static NewGISPG newGISPG(GISPG prototype) {
    return new GISPGImpl(prototype);
  }

  // brand-new instance factory
  public static NewPaymentPG newPaymentPG() {
    return new PaymentPGImpl();
  }
  // instance from prototype factory
  public static NewPaymentPG newPaymentPG(PaymentPG prototype) {
    return new PaymentPGImpl(prototype);
  }

  // brand-new instance factory
  public static NewOverviewPG newOverviewPG() {
    return new OverviewPGImpl();
  }
  // instance from prototype factory
  public static NewOverviewPG newOverviewPG(OverviewPG prototype) {
    return new OverviewPGImpl(prototype);
  }

  /** Abstract introspection information.
   * Tuples are {<classname>, <factorymethodname>}
   * return value of <factorymethodname> is <classname>.
   * <factorymethodname> takes zero or one (prototype) argument.
   **/
  public static String properties[][]={
    {"org.cougaar.tutorial.booksonline.assets.VehiclePG", "newVehiclePG"},
    {"org.cougaar.tutorial.booksonline.assets.AuthorPG", "newAuthorPG"},
    {"org.cougaar.tutorial.booksonline.assets.PackTimePG", "newPackTimePG"},
    {"org.cougaar.tutorial.booksonline.assets.InvListPG", "newInvListPG"},
    {"org.cougaar.tutorial.booksonline.assets.ShipperPG", "newShipperPG"},
    {"org.cougaar.tutorial.booksonline.assets.StockPG", "newStockPG"},
    {"org.cougaar.tutorial.booksonline.assets.PricePG", "newPricePG"},
    {"org.cougaar.tutorial.booksonline.assets.SpecificsPG", "newSpecificsPG"},
    {"org.cougaar.tutorial.booksonline.assets.AccountPG", "newAccountPG"},
    {"org.cougaar.tutorial.booksonline.assets.ReviewPG", "newReviewPG"},
    {"org.cougaar.tutorial.booksonline.assets.WareHouseDescriptionPG", "newWareHouseDescriptionPG"},
    {"org.cougaar.tutorial.booksonline.assets.PublisherPG", "newPublisherPG"},
    {"org.cougaar.tutorial.booksonline.assets.GISPG", "newGISPG"},
    {"org.cougaar.tutorial.booksonline.assets.PaymentPG", "newPaymentPG"},
    {"org.cougaar.tutorial.booksonline.assets.OverviewPG", "newOverviewPG"}
  };
}
