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
/** Primary client interface for InvListPG.
 * Parallel lists of book information for the catalog display
 *  @see NewInvListPG
 *  @see InvListPGImpl
 **/

package org.cougaar.tutorial.booksonline.assets;

import java.util.List;

import org.cougaar.planning.ldm.asset.Future_PG;
import org.cougaar.planning.ldm.asset.NewPropertyGroup;
import org.cougaar.planning.ldm.asset.Null_PG;
import org.cougaar.planning.ldm.asset.PropertyGroup;
import org.cougaar.planning.ldm.asset.UndefinedValueException;



public interface InvListPG extends PropertyGroup, org.cougaar.planning.ldm.dq.HasDataQuality {
  List getOverviewList();
  /** test to see if an element is a member of the overviewList Collection **/
  boolean inOverviewList(OverviewPG element);

  /** array getter for beans **/
  OverviewPG[] getOverviewListAsArray();

  /** indexed getter for beans **/
  OverviewPG getIndexedOverviewList(int index);

  List getAuthList();
  /** test to see if an element is a member of the authList Collection **/
  boolean inAuthList(AuthorPG element);

  /** array getter for beans **/
  AuthorPG[] getAuthListAsArray();

  /** indexed getter for beans **/
  AuthorPG getIndexedAuthList(int index);

  List getPublList();
  /** test to see if an element is a member of the publList Collection **/
  boolean inPublList(PublisherPG element);

  /** array getter for beans **/
  PublisherPG[] getPublListAsArray();

  /** indexed getter for beans **/
  PublisherPG getIndexedPublList(int index);

  List getSpefsList();
  /** test to see if an element is a member of the spefsList Collection **/
  boolean inSpefsList(SpecificsPG element);

  /** array getter for beans **/
  SpecificsPG[] getSpefsListAsArray();

  /** indexed getter for beans **/
  SpecificsPG getIndexedSpefsList(int index);

  List getPriceList();
  /** test to see if an element is a member of the priceList Collection **/
  boolean inPriceList(PricePG element);

  /** array getter for beans **/
  PricePG[] getPriceListAsArray();

  /** indexed getter for beans **/
  PricePG getIndexedPriceList(int index);

  List getReviewList();
  /** test to see if an element is a member of the reviewList Collection **/
  boolean inReviewList(ReviewPG element);

  /** array getter for beans **/
  ReviewPG[] getReviewListAsArray();

  /** indexed getter for beans **/
  ReviewPG getIndexedReviewList(int index);

  List getStockList();
  /** test to see if an element is a member of the stockList Collection **/
  boolean inStockList(StockPG element);

  /** array getter for beans **/
  StockPG[] getStockListAsArray();

  /** indexed getter for beans **/
  StockPG getIndexedStockList(int index);


  // introspection and construction
  /** the method of factoryClass that creates this type **/
  String factoryMethod = "newInvListPG";
  /** the (mutable) class type returned by factoryMethod **/
  String mutableClass = "org.cougaar.tutorial.booksonline.assets.NewInvListPG";
  /** the factory class **/
  Class factoryClass = org.cougaar.tutorial.booksonline.assets.PropertyGroupFactory.class;
  /** the (immutable) class type returned by domain factory **/
   Class primaryClass = org.cougaar.tutorial.booksonline.assets.InvListPG.class;
  String assetSetter = "setInvListPG";
  String assetGetter = "getInvListPG";
  /** The Null instance for indicating that the PG definitely has no value **/
  InvListPG nullPG = new Null_InvListPG();

/** Null_PG implementation for InvListPG **/
final class Null_InvListPG
  implements InvListPG, Null_PG
{
  public List getOverviewList() { throw new UndefinedValueException(); }
  public boolean inOverviewList(OverviewPG element) { return false; }
  public OverviewPG[] getOverviewListAsArray() { return null; }
  public OverviewPG getIndexedOverviewList(int index) { throw new UndefinedValueException(); }
  public List getAuthList() { throw new UndefinedValueException(); }
  public boolean inAuthList(AuthorPG element) { return false; }
  public AuthorPG[] getAuthListAsArray() { return null; }
  public AuthorPG getIndexedAuthList(int index) { throw new UndefinedValueException(); }
  public List getPublList() { throw new UndefinedValueException(); }
  public boolean inPublList(PublisherPG element) { return false; }
  public PublisherPG[] getPublListAsArray() { return null; }
  public PublisherPG getIndexedPublList(int index) { throw new UndefinedValueException(); }
  public List getSpefsList() { throw new UndefinedValueException(); }
  public boolean inSpefsList(SpecificsPG element) { return false; }
  public SpecificsPG[] getSpefsListAsArray() { return null; }
  public SpecificsPG getIndexedSpefsList(int index) { throw new UndefinedValueException(); }
  public List getPriceList() { throw new UndefinedValueException(); }
  public boolean inPriceList(PricePG element) { return false; }
  public PricePG[] getPriceListAsArray() { return null; }
  public PricePG getIndexedPriceList(int index) { throw new UndefinedValueException(); }
  public List getReviewList() { throw new UndefinedValueException(); }
  public boolean inReviewList(ReviewPG element) { return false; }
  public ReviewPG[] getReviewListAsArray() { return null; }
  public ReviewPG getIndexedReviewList(int index) { throw new UndefinedValueException(); }
  public List getStockList() { throw new UndefinedValueException(); }
  public boolean inStockList(StockPG element) { return false; }
  public StockPG[] getStockListAsArray() { return null; }
  public StockPG getIndexedStockList(int index) { throw new UndefinedValueException(); }
  public Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
  }
  public NewPropertyGroup unlock(Object key) { return null; }
  public PropertyGroup lock(Object key) { return null; }
  public PropertyGroup lock() { return null; }
  public PropertyGroup copy() { return null; }
  public Class getPrimaryClass(){return primaryClass;}
  public String getAssetGetMethod() {return assetGetter;}
  public String getAssetSetMethod() {return assetSetter;}
  public Class getIntrospectionClass() {
    return InvListPGImpl.class;
  }

  public boolean hasDataQuality() { return false; }
  public org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return null; }
}

/** Future PG implementation for InvListPG **/
final class Future
  implements InvListPG, Future_PG
{
  public List getOverviewList() {
    waitForFinalize();
    return _real.getOverviewList();
  }
  public boolean inOverviewList(OverviewPG element) {
    waitForFinalize();
    return _real.inOverviewList(element);
  }
  public OverviewPG[] getOverviewListAsArray() {
    waitForFinalize();
    return _real.getOverviewListAsArray();
  }
  public OverviewPG getIndexedOverviewList(int index) {
    waitForFinalize();
    return _real.getIndexedOverviewList(index);
  }
  public List getAuthList() {
    waitForFinalize();
    return _real.getAuthList();
  }
  public boolean inAuthList(AuthorPG element) {
    waitForFinalize();
    return _real.inAuthList(element);
  }
  public AuthorPG[] getAuthListAsArray() {
    waitForFinalize();
    return _real.getAuthListAsArray();
  }
  public AuthorPG getIndexedAuthList(int index) {
    waitForFinalize();
    return _real.getIndexedAuthList(index);
  }
  public List getPublList() {
    waitForFinalize();
    return _real.getPublList();
  }
  public boolean inPublList(PublisherPG element) {
    waitForFinalize();
    return _real.inPublList(element);
  }
  public PublisherPG[] getPublListAsArray() {
    waitForFinalize();
    return _real.getPublListAsArray();
  }
  public PublisherPG getIndexedPublList(int index) {
    waitForFinalize();
    return _real.getIndexedPublList(index);
  }
  public List getSpefsList() {
    waitForFinalize();
    return _real.getSpefsList();
  }
  public boolean inSpefsList(SpecificsPG element) {
    waitForFinalize();
    return _real.inSpefsList(element);
  }
  public SpecificsPG[] getSpefsListAsArray() {
    waitForFinalize();
    return _real.getSpefsListAsArray();
  }
  public SpecificsPG getIndexedSpefsList(int index) {
    waitForFinalize();
    return _real.getIndexedSpefsList(index);
  }
  public List getPriceList() {
    waitForFinalize();
    return _real.getPriceList();
  }
  public boolean inPriceList(PricePG element) {
    waitForFinalize();
    return _real.inPriceList(element);
  }
  public PricePG[] getPriceListAsArray() {
    waitForFinalize();
    return _real.getPriceListAsArray();
  }
  public PricePG getIndexedPriceList(int index) {
    waitForFinalize();
    return _real.getIndexedPriceList(index);
  }
  public List getReviewList() {
    waitForFinalize();
    return _real.getReviewList();
  }
  public boolean inReviewList(ReviewPG element) {
    waitForFinalize();
    return _real.inReviewList(element);
  }
  public ReviewPG[] getReviewListAsArray() {
    waitForFinalize();
    return _real.getReviewListAsArray();
  }
  public ReviewPG getIndexedReviewList(int index) {
    waitForFinalize();
    return _real.getIndexedReviewList(index);
  }
  public List getStockList() {
    waitForFinalize();
    return _real.getStockList();
  }
  public boolean inStockList(StockPG element) {
    waitForFinalize();
    return _real.inStockList(element);
  }
  public StockPG[] getStockListAsArray() {
    waitForFinalize();
    return _real.getStockListAsArray();
  }
  public StockPG getIndexedStockList(int index) {
    waitForFinalize();
    return _real.getIndexedStockList(index);
  }
  public Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
  }
  public NewPropertyGroup unlock(Object key) { return null; }
  public PropertyGroup lock(Object key) { return null; }
  public PropertyGroup lock() { return null; }
  public PropertyGroup copy() { return null; }
  public Class getPrimaryClass(){return primaryClass;}
  public String getAssetGetMethod() {return assetGetter;}
  public String getAssetSetMethod() {return assetSetter;}
  public Class getIntrospectionClass() {
    return InvListPGImpl.class;
  }
  public synchronized boolean hasDataQuality() {
    return (_real!=null) && _real.hasDataQuality();
  }
  public synchronized org.cougaar.planning.ldm.dq.DataQuality getDataQuality() {
    return (_real==null)?null:(_real.getDataQuality());
  }

  // Finalization support
  private InvListPG _real = null;
  public synchronized void finalize(PropertyGroup real) {
    if (real instanceof InvListPG) {
      _real=(InvListPG) real;
      notifyAll();
    } else {
      throw new IllegalArgumentException("Finalization with wrong class: "+real);
    }
  }
  private synchronized void waitForFinalize() {
    while (_real == null) {
      try {
        wait();
      } catch (InterruptedException _ie) {
        // We should really let waitForFinalize throw InterruptedException
        Thread.interrupted();
      }
    }
  }
}
}
