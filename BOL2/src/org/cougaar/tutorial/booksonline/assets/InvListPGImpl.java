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

/* @generated Tue Jun 15 07:45:54 EDT 2004 from properties.def - DO NOT HAND EDIT */
/** Implementation of InvListPG.
 *  @see InvListPG
 *  @see NewInvListPG
 **/

package org.cougaar.tutorial.booksonline.assets;

import org.cougaar.planning.ldm.measure.*;
import org.cougaar.planning.ldm.asset.*;
import org.cougaar.planning.ldm.plan.*;
import java.util.*;



import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.beans.PropertyDescriptor;
import java.beans.IndexedPropertyDescriptor;

public class InvListPGImpl extends java.beans.SimpleBeanInfo
  implements NewInvListPG, Cloneable
{
  public InvListPGImpl() {
  }

  // Slots

  private List theOverviewList = new Vector();
  public List getOverviewList(){ return theOverviewList; }
  public boolean inOverviewList(OverviewPG _element) {
    return (theOverviewList==null)?false:(theOverviewList.contains(_element));
  }
  public OverviewPG[] getOverviewListAsArray() {
    if (theOverviewList == null) return new OverviewPG[0];
    int l = theOverviewList.size();
    OverviewPG[] v = new OverviewPG[l];
    int i=0;
    for (Iterator n=theOverviewList.iterator(); n.hasNext(); ) {
      v[i]=(OverviewPG) n.next();
      i++;
    }
    return v;
  }
  public OverviewPG getIndexedOverviewList(int _index) {
    if (theOverviewList == null) return null;
    for (Iterator _i = theOverviewList.iterator(); _i.hasNext();) {
      OverviewPG _e = (OverviewPG) _i.next();
      if (_index == 0) return _e;
      _index--;
    }
    return null;
  }
  public void setOverviewList(List overviewList) {
    theOverviewList=overviewList;
  }
  public void clearOverviewList() {
    theOverviewList.clear();
  }
  public boolean removeFromOverviewList(OverviewPG _element) {
    return theOverviewList.remove(_element);
  }
  public boolean addToOverviewList(OverviewPG _element) {
    return theOverviewList.add(_element);
  }
  private List theAuthList = new Vector();
  public List getAuthList(){ return theAuthList; }
  public boolean inAuthList(AuthorPG _element) {
    return (theAuthList==null)?false:(theAuthList.contains(_element));
  }
  public AuthorPG[] getAuthListAsArray() {
    if (theAuthList == null) return new AuthorPG[0];
    int l = theAuthList.size();
    AuthorPG[] v = new AuthorPG[l];
    int i=0;
    for (Iterator n=theAuthList.iterator(); n.hasNext(); ) {
      v[i]=(AuthorPG) n.next();
      i++;
    }
    return v;
  }
  public AuthorPG getIndexedAuthList(int _index) {
    if (theAuthList == null) return null;
    for (Iterator _i = theAuthList.iterator(); _i.hasNext();) {
      AuthorPG _e = (AuthorPG) _i.next();
      if (_index == 0) return _e;
      _index--;
    }
    return null;
  }
  public void setAuthList(List authList) {
    theAuthList=authList;
  }
  public void clearAuthList() {
    theAuthList.clear();
  }
  public boolean removeFromAuthList(AuthorPG _element) {
    return theAuthList.remove(_element);
  }
  public boolean addToAuthList(AuthorPG _element) {
    return theAuthList.add(_element);
  }
  private List thePublList = new Vector();
  public List getPublList(){ return thePublList; }
  public boolean inPublList(PublisherPG _element) {
    return (thePublList==null)?false:(thePublList.contains(_element));
  }
  public PublisherPG[] getPublListAsArray() {
    if (thePublList == null) return new PublisherPG[0];
    int l = thePublList.size();
    PublisherPG[] v = new PublisherPG[l];
    int i=0;
    for (Iterator n=thePublList.iterator(); n.hasNext(); ) {
      v[i]=(PublisherPG) n.next();
      i++;
    }
    return v;
  }
  public PublisherPG getIndexedPublList(int _index) {
    if (thePublList == null) return null;
    for (Iterator _i = thePublList.iterator(); _i.hasNext();) {
      PublisherPG _e = (PublisherPG) _i.next();
      if (_index == 0) return _e;
      _index--;
    }
    return null;
  }
  public void setPublList(List publList) {
    thePublList=publList;
  }
  public void clearPublList() {
    thePublList.clear();
  }
  public boolean removeFromPublList(PublisherPG _element) {
    return thePublList.remove(_element);
  }
  public boolean addToPublList(PublisherPG _element) {
    return thePublList.add(_element);
  }
  private List theSpefsList = new Vector();
  public List getSpefsList(){ return theSpefsList; }
  public boolean inSpefsList(SpecificsPG _element) {
    return (theSpefsList==null)?false:(theSpefsList.contains(_element));
  }
  public SpecificsPG[] getSpefsListAsArray() {
    if (theSpefsList == null) return new SpecificsPG[0];
    int l = theSpefsList.size();
    SpecificsPG[] v = new SpecificsPG[l];
    int i=0;
    for (Iterator n=theSpefsList.iterator(); n.hasNext(); ) {
      v[i]=(SpecificsPG) n.next();
      i++;
    }
    return v;
  }
  public SpecificsPG getIndexedSpefsList(int _index) {
    if (theSpefsList == null) return null;
    for (Iterator _i = theSpefsList.iterator(); _i.hasNext();) {
      SpecificsPG _e = (SpecificsPG) _i.next();
      if (_index == 0) return _e;
      _index--;
    }
    return null;
  }
  public void setSpefsList(List spefsList) {
    theSpefsList=spefsList;
  }
  public void clearSpefsList() {
    theSpefsList.clear();
  }
  public boolean removeFromSpefsList(SpecificsPG _element) {
    return theSpefsList.remove(_element);
  }
  public boolean addToSpefsList(SpecificsPG _element) {
    return theSpefsList.add(_element);
  }
  private List thePriceList = new Vector();
  public List getPriceList(){ return thePriceList; }
  public boolean inPriceList(PricePG _element) {
    return (thePriceList==null)?false:(thePriceList.contains(_element));
  }
  public PricePG[] getPriceListAsArray() {
    if (thePriceList == null) return new PricePG[0];
    int l = thePriceList.size();
    PricePG[] v = new PricePG[l];
    int i=0;
    for (Iterator n=thePriceList.iterator(); n.hasNext(); ) {
      v[i]=(PricePG) n.next();
      i++;
    }
    return v;
  }
  public PricePG getIndexedPriceList(int _index) {
    if (thePriceList == null) return null;
    for (Iterator _i = thePriceList.iterator(); _i.hasNext();) {
      PricePG _e = (PricePG) _i.next();
      if (_index == 0) return _e;
      _index--;
    }
    return null;
  }
  public void setPriceList(List priceList) {
    thePriceList=priceList;
  }
  public void clearPriceList() {
    thePriceList.clear();
  }
  public boolean removeFromPriceList(PricePG _element) {
    return thePriceList.remove(_element);
  }
  public boolean addToPriceList(PricePG _element) {
    return thePriceList.add(_element);
  }
  private List theReviewList = new Vector();
  public List getReviewList(){ return theReviewList; }
  public boolean inReviewList(ReviewPG _element) {
    return (theReviewList==null)?false:(theReviewList.contains(_element));
  }
  public ReviewPG[] getReviewListAsArray() {
    if (theReviewList == null) return new ReviewPG[0];
    int l = theReviewList.size();
    ReviewPG[] v = new ReviewPG[l];
    int i=0;
    for (Iterator n=theReviewList.iterator(); n.hasNext(); ) {
      v[i]=(ReviewPG) n.next();
      i++;
    }
    return v;
  }
  public ReviewPG getIndexedReviewList(int _index) {
    if (theReviewList == null) return null;
    for (Iterator _i = theReviewList.iterator(); _i.hasNext();) {
      ReviewPG _e = (ReviewPG) _i.next();
      if (_index == 0) return _e;
      _index--;
    }
    return null;
  }
  public void setReviewList(List reviewList) {
    theReviewList=reviewList;
  }
  public void clearReviewList() {
    theReviewList.clear();
  }
  public boolean removeFromReviewList(ReviewPG _element) {
    return theReviewList.remove(_element);
  }
  public boolean addToReviewList(ReviewPG _element) {
    return theReviewList.add(_element);
  }
  private List theStockList = new Vector();
  public List getStockList(){ return theStockList; }
  public boolean inStockList(StockPG _element) {
    return (theStockList==null)?false:(theStockList.contains(_element));
  }
  public StockPG[] getStockListAsArray() {
    if (theStockList == null) return new StockPG[0];
    int l = theStockList.size();
    StockPG[] v = new StockPG[l];
    int i=0;
    for (Iterator n=theStockList.iterator(); n.hasNext(); ) {
      v[i]=(StockPG) n.next();
      i++;
    }
    return v;
  }
  public StockPG getIndexedStockList(int _index) {
    if (theStockList == null) return null;
    for (Iterator _i = theStockList.iterator(); _i.hasNext();) {
      StockPG _e = (StockPG) _i.next();
      if (_index == 0) return _e;
      _index--;
    }
    return null;
  }
  public void setStockList(List stockList) {
    theStockList=stockList;
  }
  public void clearStockList() {
    theStockList.clear();
  }
  public boolean removeFromStockList(StockPG _element) {
    return theStockList.remove(_element);
  }
  public boolean addToStockList(StockPG _element) {
    return theStockList.add(_element);
  }


  public InvListPGImpl(InvListPG original) {
    theOverviewList = original.getOverviewList();
    theAuthList = original.getAuthList();
    thePublList = original.getPublList();
    theSpefsList = original.getSpefsList();
    thePriceList = original.getPriceList();
    theReviewList = original.getReviewList();
    theStockList = original.getStockList();
  }

  public boolean hasDataQuality() { return false; }
  public org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return null; }

  // static inner extension class for real DataQuality Support
  public final static class DQ extends InvListPGImpl implements org.cougaar.planning.ldm.dq.NewHasDataQuality {
   public DQ() {
    super();
   }
   public DQ(InvListPG original) {
    super(original);
   }
   public Object clone() { return new DQ(this); }
   private transient org.cougaar.planning.ldm.dq.DataQuality _dq = null;
   public boolean hasDataQuality() { return (_dq!=null); }
   public org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return _dq; }
   public void setDataQuality(org.cougaar.planning.ldm.dq.DataQuality dq) { _dq=dq; }
   private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
    if (out instanceof org.cougaar.core.persist.PersistenceOutputStream) out.writeObject(_dq);
   }
   private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
    in.defaultReadObject();
    if (in instanceof org.cougaar.core.persist.PersistenceInputStream) _dq=(org.cougaar.planning.ldm.dq.DataQuality)in.readObject();
   }
    
    private final static PropertyDescriptor properties[]=new PropertyDescriptor[1];
    static {
      try {
        properties[0]= new PropertyDescriptor("dataQuality", DQ.class, "getDataQuality", null);
      } catch (Exception e) { e.printStackTrace(); }
    }
    public PropertyDescriptor[] getPropertyDescriptors() {
      PropertyDescriptor[] pds = super.properties;
      PropertyDescriptor[] ps = new PropertyDescriptor[pds.length+properties.length];
      System.arraycopy(pds, 0, ps, 0, pds.length);
      System.arraycopy(properties, 0, ps, pds.length, properties.length);
      return ps;
    }
  }


  private transient InvListPG _locked = null;
  public PropertyGroup lock(Object key) {
    if (_locked == null)_locked = new _Locked(key);
    return _locked; }
  public PropertyGroup lock() { return lock(null); }
  public NewPropertyGroup unlock(Object key) { return this; }

  public Object clone() throws CloneNotSupportedException {
    return new InvListPGImpl(InvListPGImpl.this);
  }

  public PropertyGroup copy() {
    try {
      return (PropertyGroup) clone();
    } catch (CloneNotSupportedException cnse) { return null;}
  }

  public Class getPrimaryClass() {
    return primaryClass;
  }
  public String getAssetGetMethod() {
    return assetGetter;
  }
  public String getAssetSetMethod() {
    return assetSetter;
  }

  private final static PropertyDescriptor properties[] = new PropertyDescriptor[7];
  static {
    try {
      properties[0]= new IndexedPropertyDescriptor("overviewList", InvListPG.class, "getOverviewListAsArray", null, "getIndexedOverviewList", null);
      properties[1]= new IndexedPropertyDescriptor("authList", InvListPG.class, "getAuthListAsArray", null, "getIndexedAuthList", null);
      properties[2]= new IndexedPropertyDescriptor("publList", InvListPG.class, "getPublListAsArray", null, "getIndexedPublList", null);
      properties[3]= new IndexedPropertyDescriptor("spefsList", InvListPG.class, "getSpefsListAsArray", null, "getIndexedSpefsList", null);
      properties[4]= new IndexedPropertyDescriptor("priceList", InvListPG.class, "getPriceListAsArray", null, "getIndexedPriceList", null);
      properties[5]= new IndexedPropertyDescriptor("reviewList", InvListPG.class, "getReviewListAsArray", null, "getIndexedReviewList", null);
      properties[6]= new IndexedPropertyDescriptor("stockList", InvListPG.class, "getStockListAsArray", null, "getIndexedStockList", null);
    } catch (Exception e) { 
      org.cougaar.util.log.Logging.getLogger(InvListPG.class).error("Caught exception",e);
    }
  }

  public PropertyDescriptor[] getPropertyDescriptors() {
    return properties;
  }
  private final class _Locked extends java.beans.SimpleBeanInfo
    implements InvListPG, Cloneable, LockedPG
  {
    private transient Object theKey = null;
    _Locked(Object key) { 
      if (this.theKey == null) this.theKey = key;
    }  

    public _Locked() {}

    public PropertyGroup lock() { return this; }
    public PropertyGroup lock(Object o) { return this; }

    public NewPropertyGroup unlock(Object key) throws IllegalAccessException {
       if( theKey.equals(key) ) {
         return InvListPGImpl.this;
       } else {
         throw new IllegalAccessException("unlock: mismatched internal and provided keys!");
       }
    }

    public PropertyGroup copy() {
      try {
        return (PropertyGroup) clone();
      } catch (CloneNotSupportedException cnse) { return null;}
    }


    public Object clone() throws CloneNotSupportedException {
      return new InvListPGImpl(InvListPGImpl.this);
    }

    public List getOverviewList() { return InvListPGImpl.this.getOverviewList(); }
  public boolean inOverviewList(OverviewPG _element) {
    return InvListPGImpl.this.inOverviewList(_element);
  }
  public OverviewPG[] getOverviewListAsArray() {
    return InvListPGImpl.this.getOverviewListAsArray();
  }
  public OverviewPG getIndexedOverviewList(int _index) {
    return InvListPGImpl.this.getIndexedOverviewList(_index);
  }
    public List getAuthList() { return InvListPGImpl.this.getAuthList(); }
  public boolean inAuthList(AuthorPG _element) {
    return InvListPGImpl.this.inAuthList(_element);
  }
  public AuthorPG[] getAuthListAsArray() {
    return InvListPGImpl.this.getAuthListAsArray();
  }
  public AuthorPG getIndexedAuthList(int _index) {
    return InvListPGImpl.this.getIndexedAuthList(_index);
  }
    public List getPublList() { return InvListPGImpl.this.getPublList(); }
  public boolean inPublList(PublisherPG _element) {
    return InvListPGImpl.this.inPublList(_element);
  }
  public PublisherPG[] getPublListAsArray() {
    return InvListPGImpl.this.getPublListAsArray();
  }
  public PublisherPG getIndexedPublList(int _index) {
    return InvListPGImpl.this.getIndexedPublList(_index);
  }
    public List getSpefsList() { return InvListPGImpl.this.getSpefsList(); }
  public boolean inSpefsList(SpecificsPG _element) {
    return InvListPGImpl.this.inSpefsList(_element);
  }
  public SpecificsPG[] getSpefsListAsArray() {
    return InvListPGImpl.this.getSpefsListAsArray();
  }
  public SpecificsPG getIndexedSpefsList(int _index) {
    return InvListPGImpl.this.getIndexedSpefsList(_index);
  }
    public List getPriceList() { return InvListPGImpl.this.getPriceList(); }
  public boolean inPriceList(PricePG _element) {
    return InvListPGImpl.this.inPriceList(_element);
  }
  public PricePG[] getPriceListAsArray() {
    return InvListPGImpl.this.getPriceListAsArray();
  }
  public PricePG getIndexedPriceList(int _index) {
    return InvListPGImpl.this.getIndexedPriceList(_index);
  }
    public List getReviewList() { return InvListPGImpl.this.getReviewList(); }
  public boolean inReviewList(ReviewPG _element) {
    return InvListPGImpl.this.inReviewList(_element);
  }
  public ReviewPG[] getReviewListAsArray() {
    return InvListPGImpl.this.getReviewListAsArray();
  }
  public ReviewPG getIndexedReviewList(int _index) {
    return InvListPGImpl.this.getIndexedReviewList(_index);
  }
    public List getStockList() { return InvListPGImpl.this.getStockList(); }
  public boolean inStockList(StockPG _element) {
    return InvListPGImpl.this.inStockList(_element);
  }
  public StockPG[] getStockListAsArray() {
    return InvListPGImpl.this.getStockListAsArray();
  }
  public StockPG getIndexedStockList(int _index) {
    return InvListPGImpl.this.getIndexedStockList(_index);
  }
  public final boolean hasDataQuality() { return InvListPGImpl.this.hasDataQuality(); }
  public final org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return InvListPGImpl.this.getDataQuality(); }
    public Class getPrimaryClass() {
      return primaryClass;
    }
    public String getAssetGetMethod() {
      return assetGetter;
    }
    public String getAssetSetMethod() {
      return assetSetter;
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
      return properties;
    }

    public Class getIntrospectionClass() {
      return InvListPGImpl.class;
    }

  }

}
