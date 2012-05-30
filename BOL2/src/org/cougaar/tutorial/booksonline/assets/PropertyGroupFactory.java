/*
 * <copyright>
 *  
 *  Copyright 1997-2004 BBNT Solutions, LLC
 *  under sponsorship of the Defense Advanced Research Projects
 *  Agency (DARPA).
 * 
 *  You can redistribute this software and/or modify it under the
 *  terms of the Cougaar Open Source License as published on the
 *  Cougaar Open Source Website (www.cougaar.org).
 * 
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *  "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 *  A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 *  OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 *  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 *  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 *  OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 * </copyright>
 */

/* @generated Tue May 29 19:40:18 EDT 2012 from properties.def - DO NOT HAND EDIT */
/** AbstractFactory implementation for Properties.
 * Prevents clients from needing to know the implementation
 * class(es) of any of the properties.
 **/

package org.cougaar.tutorial.booksonline.assets;

import org.cougaar.planning.ldm.measure.*;
import org.cougaar.planning.ldm.asset.*;
import org.cougaar.planning.ldm.plan.*;
import java.util.*;



public class PropertyGroupFactory {
  // brand-new instance factory
  public static NewAuthorPG newAuthorPG() {
    return new AuthorPGImpl();
  }
  // instance from prototype factory
  public static NewAuthorPG newAuthorPG(AuthorPG prototype) {
    return new AuthorPGImpl(prototype);
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
  public static NewVehiclePG newVehiclePG() {
    return new VehiclePGImpl();
  }
  // instance from prototype factory
  public static NewVehiclePG newVehiclePG(VehiclePG prototype) {
    return new VehiclePGImpl(prototype);
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
  public static NewInvListPG newInvListPG() {
    return new InvListPGImpl();
  }
  // instance from prototype factory
  public static NewInvListPG newInvListPG(InvListPG prototype) {
    return new InvListPGImpl(prototype);
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
  public static NewGISPG newGISPG() {
    return new GISPGImpl();
  }
  // instance from prototype factory
  public static NewGISPG newGISPG(GISPG prototype) {
    return new GISPGImpl(prototype);
  }

  // brand-new instance factory
  public static NewOverviewPG newOverviewPG() {
    return new OverviewPGImpl();
  }
  // instance from prototype factory
  public static NewOverviewPG newOverviewPG(OverviewPG prototype) {
    return new OverviewPGImpl(prototype);
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
  public static NewPricePG newPricePG() {
    return new PricePGImpl();
  }
  // instance from prototype factory
  public static NewPricePG newPricePG(PricePG prototype) {
    return new PricePGImpl(prototype);
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
  public static NewPaymentPG newPaymentPG() {
    return new PaymentPGImpl();
  }
  // instance from prototype factory
  public static NewPaymentPG newPaymentPG(PaymentPG prototype) {
    return new PaymentPGImpl(prototype);
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
  public static NewWareHouseDescriptionPG newWareHouseDescriptionPG() {
    return new WareHouseDescriptionPGImpl();
  }
  // instance from prototype factory
  public static NewWareHouseDescriptionPG newWareHouseDescriptionPG(WareHouseDescriptionPG prototype) {
    return new WareHouseDescriptionPGImpl(prototype);
  }

  // brand-new instance factory
  public static NewPackTimePG newPackTimePG() {
    return new PackTimePGImpl();
  }
  // instance from prototype factory
  public static NewPackTimePG newPackTimePG(PackTimePG prototype) {
    return new PackTimePGImpl(prototype);
  }

  /** Abstract introspection information.
   * Tuples are {<classname>, <factorymethodname>}
   * return value of <factorymethodname> is <classname>.
   * <factorymethodname> takes zero or one (prototype) argument.
   **/
  public static String properties[][]={
    {"org.cougaar.tutorial.booksonline.assets.AuthorPG", "newAuthorPG"},
    {"org.cougaar.tutorial.booksonline.assets.ReviewPG", "newReviewPG"},
    {"org.cougaar.tutorial.booksonline.assets.VehiclePG", "newVehiclePG"},
    {"org.cougaar.tutorial.booksonline.assets.ShipperPG", "newShipperPG"},
    {"org.cougaar.tutorial.booksonline.assets.InvListPG", "newInvListPG"},
    {"org.cougaar.tutorial.booksonline.assets.StockPG", "newStockPG"},
    {"org.cougaar.tutorial.booksonline.assets.GISPG", "newGISPG"},
    {"org.cougaar.tutorial.booksonline.assets.OverviewPG", "newOverviewPG"},
    {"org.cougaar.tutorial.booksonline.assets.AccountPG", "newAccountPG"},
    {"org.cougaar.tutorial.booksonline.assets.PricePG", "newPricePG"},
    {"org.cougaar.tutorial.booksonline.assets.PublisherPG", "newPublisherPG"},
    {"org.cougaar.tutorial.booksonline.assets.PaymentPG", "newPaymentPG"},
    {"org.cougaar.tutorial.booksonline.assets.SpecificsPG", "newSpecificsPG"},
    {"org.cougaar.tutorial.booksonline.assets.WareHouseDescriptionPG", "newWareHouseDescriptionPG"},
    {"org.cougaar.tutorial.booksonline.assets.PackTimePG", "newPackTimePG"},
  };
}
