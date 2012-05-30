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
/** Abstract Asset Skeleton implementation
 * Implements default property getters, and additional property
 * lists.
 * Intended to be extended by org.cougaar.planning.ldm.asset.Asset
 **/

package org.cougaar.tutorial.booksonline.assets;

import org.cougaar.planning.ldm.measure.*;
import org.cougaar.planning.ldm.asset.*;
import org.cougaar.planning.ldm.plan.*;
import java.util.*;


import java.io.Serializable;
import java.beans.PropertyDescriptor;
import java.beans.IndexedPropertyDescriptor;

public abstract class AssetSkeleton extends org.cougaar.planning.ldm.asset.Asset {

  protected AssetSkeleton() {}

  protected AssetSkeleton(AssetSkeleton prototype) {
    super(prototype);
  }

  /**                 Default PG accessors               **/

  /** Search additional properties for a AuthorPG instance.
   * @return instance of AuthorPG or null.
   **/
  public AuthorPG getAuthorPG()
  {
    AuthorPG _tmp = (AuthorPG) resolvePG(AuthorPG.class);
    return (_tmp==AuthorPG.nullPG)?null:_tmp;
  }

  /** Test for existence of a AuthorPG
   **/
  public boolean hasAuthorPG() {
    return (getAuthorPG() != null);
  }

  /** Set the AuthorPG property.
   * The default implementation will create a new AuthorPG
   * property and add it to the otherPropertyGroup list.
   * Many subclasses override with local slots.
   **/
  public void setAuthorPG(PropertyGroup aAuthorPG) {
    if (aAuthorPG == null) {
      removeOtherPropertyGroup(AuthorPG.class);
    } else {
      addOtherPropertyGroup(aAuthorPG);
    }
  }

  /** Search additional properties for a ReviewPG instance.
   * @return instance of ReviewPG or null.
   **/
  public ReviewPG getReviewPG()
  {
    ReviewPG _tmp = (ReviewPG) resolvePG(ReviewPG.class);
    return (_tmp==ReviewPG.nullPG)?null:_tmp;
  }

  /** Test for existence of a ReviewPG
   **/
  public boolean hasReviewPG() {
    return (getReviewPG() != null);
  }

  /** Set the ReviewPG property.
   * The default implementation will create a new ReviewPG
   * property and add it to the otherPropertyGroup list.
   * Many subclasses override with local slots.
   **/
  public void setReviewPG(PropertyGroup aReviewPG) {
    if (aReviewPG == null) {
      removeOtherPropertyGroup(ReviewPG.class);
    } else {
      addOtherPropertyGroup(aReviewPG);
    }
  }

  /** Search additional properties for a VehiclePG instance.
   * @return instance of VehiclePG or null.
   **/
  public VehiclePG getVehiclePG()
  {
    VehiclePG _tmp = (VehiclePG) resolvePG(VehiclePG.class);
    return (_tmp==VehiclePG.nullPG)?null:_tmp;
  }

  /** Test for existence of a VehiclePG
   **/
  public boolean hasVehiclePG() {
    return (getVehiclePG() != null);
  }

  /** Set the VehiclePG property.
   * The default implementation will create a new VehiclePG
   * property and add it to the otherPropertyGroup list.
   * Many subclasses override with local slots.
   **/
  public void setVehiclePG(PropertyGroup aVehiclePG) {
    if (aVehiclePG == null) {
      removeOtherPropertyGroup(VehiclePG.class);
    } else {
      addOtherPropertyGroup(aVehiclePG);
    }
  }

  /** Search additional properties for a ShipperPG instance.
   * @return instance of ShipperPG or null.
   **/
  public ShipperPG getShipperPG()
  {
    ShipperPG _tmp = (ShipperPG) resolvePG(ShipperPG.class);
    return (_tmp==ShipperPG.nullPG)?null:_tmp;
  }

  /** Test for existence of a ShipperPG
   **/
  public boolean hasShipperPG() {
    return (getShipperPG() != null);
  }

  /** Set the ShipperPG property.
   * The default implementation will create a new ShipperPG
   * property and add it to the otherPropertyGroup list.
   * Many subclasses override with local slots.
   **/
  public void setShipperPG(PropertyGroup aShipperPG) {
    if (aShipperPG == null) {
      removeOtherPropertyGroup(ShipperPG.class);
    } else {
      addOtherPropertyGroup(aShipperPG);
    }
  }

  /** Search additional properties for a InvListPG instance.
   * @return instance of InvListPG or null.
   **/
  public InvListPG getInvListPG()
  {
    InvListPG _tmp = (InvListPG) resolvePG(InvListPG.class);
    return (_tmp==InvListPG.nullPG)?null:_tmp;
  }

  /** Test for existence of a InvListPG
   **/
  public boolean hasInvListPG() {
    return (getInvListPG() != null);
  }

  /** Set the InvListPG property.
   * The default implementation will create a new InvListPG
   * property and add it to the otherPropertyGroup list.
   * Many subclasses override with local slots.
   **/
  public void setInvListPG(PropertyGroup aInvListPG) {
    if (aInvListPG == null) {
      removeOtherPropertyGroup(InvListPG.class);
    } else {
      addOtherPropertyGroup(aInvListPG);
    }
  }

  /** Search additional properties for a StockPG instance.
   * @return instance of StockPG or null.
   **/
  public StockPG getStockPG()
  {
    StockPG _tmp = (StockPG) resolvePG(StockPG.class);
    return (_tmp==StockPG.nullPG)?null:_tmp;
  }

  /** Test for existence of a StockPG
   **/
  public boolean hasStockPG() {
    return (getStockPG() != null);
  }

  /** Set the StockPG property.
   * The default implementation will create a new StockPG
   * property and add it to the otherPropertyGroup list.
   * Many subclasses override with local slots.
   **/
  public void setStockPG(PropertyGroup aStockPG) {
    if (aStockPG == null) {
      removeOtherPropertyGroup(StockPG.class);
    } else {
      addOtherPropertyGroup(aStockPG);
    }
  }

  /** Search additional properties for a GISPG instance.
   * @return instance of GISPG or null.
   **/
  public GISPG getGISPG()
  {
    GISPG _tmp = (GISPG) resolvePG(GISPG.class);
    return (_tmp==GISPG.nullPG)?null:_tmp;
  }

  /** Test for existence of a GISPG
   **/
  public boolean hasGISPG() {
    return (getGISPG() != null);
  }

  /** Set the GISPG property.
   * The default implementation will create a new GISPG
   * property and add it to the otherPropertyGroup list.
   * Many subclasses override with local slots.
   **/
  public void setGISPG(PropertyGroup aGISPG) {
    if (aGISPG == null) {
      removeOtherPropertyGroup(GISPG.class);
    } else {
      addOtherPropertyGroup(aGISPG);
    }
  }

  /** Search additional properties for a OverviewPG instance.
   * @return instance of OverviewPG or null.
   **/
  public OverviewPG getOverviewPG()
  {
    OverviewPG _tmp = (OverviewPG) resolvePG(OverviewPG.class);
    return (_tmp==OverviewPG.nullPG)?null:_tmp;
  }

  /** Test for existence of a OverviewPG
   **/
  public boolean hasOverviewPG() {
    return (getOverviewPG() != null);
  }

  /** Set the OverviewPG property.
   * The default implementation will create a new OverviewPG
   * property and add it to the otherPropertyGroup list.
   * Many subclasses override with local slots.
   **/
  public void setOverviewPG(PropertyGroup aOverviewPG) {
    if (aOverviewPG == null) {
      removeOtherPropertyGroup(OverviewPG.class);
    } else {
      addOtherPropertyGroup(aOverviewPG);
    }
  }

  /** Search additional properties for a AccountPG instance.
   * @return instance of AccountPG or null.
   **/
  public AccountPG getAccountPG()
  {
    AccountPG _tmp = (AccountPG) resolvePG(AccountPG.class);
    return (_tmp==AccountPG.nullPG)?null:_tmp;
  }

  /** Test for existence of a AccountPG
   **/
  public boolean hasAccountPG() {
    return (getAccountPG() != null);
  }

  /** Set the AccountPG property.
   * The default implementation will create a new AccountPG
   * property and add it to the otherPropertyGroup list.
   * Many subclasses override with local slots.
   **/
  public void setAccountPG(PropertyGroup aAccountPG) {
    if (aAccountPG == null) {
      removeOtherPropertyGroup(AccountPG.class);
    } else {
      addOtherPropertyGroup(aAccountPG);
    }
  }

  /** Search additional properties for a PricePG instance.
   * @return instance of PricePG or null.
   **/
  public PricePG getPricePG()
  {
    PricePG _tmp = (PricePG) resolvePG(PricePG.class);
    return (_tmp==PricePG.nullPG)?null:_tmp;
  }

  /** Test for existence of a PricePG
   **/
  public boolean hasPricePG() {
    return (getPricePG() != null);
  }

  /** Set the PricePG property.
   * The default implementation will create a new PricePG
   * property and add it to the otherPropertyGroup list.
   * Many subclasses override with local slots.
   **/
  public void setPricePG(PropertyGroup aPricePG) {
    if (aPricePG == null) {
      removeOtherPropertyGroup(PricePG.class);
    } else {
      addOtherPropertyGroup(aPricePG);
    }
  }

  /** Search additional properties for a PublisherPG instance.
   * @return instance of PublisherPG or null.
   **/
  public PublisherPG getPublisherPG()
  {
    PublisherPG _tmp = (PublisherPG) resolvePG(PublisherPG.class);
    return (_tmp==PublisherPG.nullPG)?null:_tmp;
  }

  /** Test for existence of a PublisherPG
   **/
  public boolean hasPublisherPG() {
    return (getPublisherPG() != null);
  }

  /** Set the PublisherPG property.
   * The default implementation will create a new PublisherPG
   * property and add it to the otherPropertyGroup list.
   * Many subclasses override with local slots.
   **/
  public void setPublisherPG(PropertyGroup aPublisherPG) {
    if (aPublisherPG == null) {
      removeOtherPropertyGroup(PublisherPG.class);
    } else {
      addOtherPropertyGroup(aPublisherPG);
    }
  }

  /** Search additional properties for a PaymentPG instance.
   * @return instance of PaymentPG or null.
   **/
  public PaymentPG getPaymentPG()
  {
    PaymentPG _tmp = (PaymentPG) resolvePG(PaymentPG.class);
    return (_tmp==PaymentPG.nullPG)?null:_tmp;
  }

  /** Test for existence of a PaymentPG
   **/
  public boolean hasPaymentPG() {
    return (getPaymentPG() != null);
  }

  /** Set the PaymentPG property.
   * The default implementation will create a new PaymentPG
   * property and add it to the otherPropertyGroup list.
   * Many subclasses override with local slots.
   **/
  public void setPaymentPG(PropertyGroup aPaymentPG) {
    if (aPaymentPG == null) {
      removeOtherPropertyGroup(PaymentPG.class);
    } else {
      addOtherPropertyGroup(aPaymentPG);
    }
  }

  /** Search additional properties for a SpecificsPG instance.
   * @return instance of SpecificsPG or null.
   **/
  public SpecificsPG getSpecificsPG()
  {
    SpecificsPG _tmp = (SpecificsPG) resolvePG(SpecificsPG.class);
    return (_tmp==SpecificsPG.nullPG)?null:_tmp;
  }

  /** Test for existence of a SpecificsPG
   **/
  public boolean hasSpecificsPG() {
    return (getSpecificsPG() != null);
  }

  /** Set the SpecificsPG property.
   * The default implementation will create a new SpecificsPG
   * property and add it to the otherPropertyGroup list.
   * Many subclasses override with local slots.
   **/
  public void setSpecificsPG(PropertyGroup aSpecificsPG) {
    if (aSpecificsPG == null) {
      removeOtherPropertyGroup(SpecificsPG.class);
    } else {
      addOtherPropertyGroup(aSpecificsPG);
    }
  }

  /** Search additional properties for a WareHouseDescriptionPG instance.
   * @return instance of WareHouseDescriptionPG or null.
   **/
  public WareHouseDescriptionPG getWareHouseDescriptionPG()
  {
    WareHouseDescriptionPG _tmp = (WareHouseDescriptionPG) resolvePG(WareHouseDescriptionPG.class);
    return (_tmp==WareHouseDescriptionPG.nullPG)?null:_tmp;
  }

  /** Test for existence of a WareHouseDescriptionPG
   **/
  public boolean hasWareHouseDescriptionPG() {
    return (getWareHouseDescriptionPG() != null);
  }

  /** Set the WareHouseDescriptionPG property.
   * The default implementation will create a new WareHouseDescriptionPG
   * property and add it to the otherPropertyGroup list.
   * Many subclasses override with local slots.
   **/
  public void setWareHouseDescriptionPG(PropertyGroup aWareHouseDescriptionPG) {
    if (aWareHouseDescriptionPG == null) {
      removeOtherPropertyGroup(WareHouseDescriptionPG.class);
    } else {
      addOtherPropertyGroup(aWareHouseDescriptionPG);
    }
  }

  /** Search additional properties for a PackTimePG instance.
   * @return instance of PackTimePG or null.
   **/
  public PackTimePG getPackTimePG()
  {
    PackTimePG _tmp = (PackTimePG) resolvePG(PackTimePG.class);
    return (_tmp==PackTimePG.nullPG)?null:_tmp;
  }

  /** Test for existence of a PackTimePG
   **/
  public boolean hasPackTimePG() {
    return (getPackTimePG() != null);
  }

  /** Set the PackTimePG property.
   * The default implementation will create a new PackTimePG
   * property and add it to the otherPropertyGroup list.
   * Many subclasses override with local slots.
   **/
  public void setPackTimePG(PropertyGroup aPackTimePG) {
    if (aPackTimePG == null) {
      removeOtherPropertyGroup(PackTimePG.class);
    } else {
      addOtherPropertyGroup(aPackTimePG);
    }
  }

}
