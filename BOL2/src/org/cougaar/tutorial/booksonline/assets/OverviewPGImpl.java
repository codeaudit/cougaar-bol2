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
/** Implementation of OverviewPG.
 *  @see OverviewPG
 *  @see NewOverviewPG
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

public class OverviewPGImpl extends java.beans.SimpleBeanInfo
  implements NewOverviewPG, Cloneable
{
  public OverviewPGImpl() {
  }

  // Slots

  private String theTitle;
  public String getTitle(){ return theTitle; }
  public void setTitle(String title) {
    theTitle=title;
  }
  private String theTableOfContents;
  public String getTableOfContents(){ return theTableOfContents; }
  public void setTableOfContents(String tableOfContents) {
    theTableOfContents=tableOfContents;
  }
  private int theSalesRank;
  public int getSalesRank(){ return theSalesRank; }
  public void setSalesRank(int salesRank) {
    theSalesRank=salesRank;
  }
  private String thePublisherNotes;
  public String getPublisherNotes(){ return thePublisherNotes; }
  public void setPublisherNotes(String publisherNotes) {
    thePublisherNotes=publisherNotes;
  }
  private String theSubjectKeywords;
  public String getSubjectKeywords(){ return theSubjectKeywords; }
  public void setSubjectKeywords(String subjectKeywords) {
    theSubjectKeywords=subjectKeywords;
  }
  private String theSearchableTitle;
  public String getSearchableTitle(){ return theSearchableTitle; }
  public void setSearchableTitle(String searchableTitle) {
    theSearchableTitle=searchableTitle;
  }


  public OverviewPGImpl(OverviewPG original) {
    theTitle = original.getTitle();
    theTableOfContents = original.getTableOfContents();
    theSalesRank = original.getSalesRank();
    thePublisherNotes = original.getPublisherNotes();
    theSubjectKeywords = original.getSubjectKeywords();
    theSearchableTitle = original.getSearchableTitle();
  }

  public boolean hasDataQuality() { return false; }
  public org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return null; }

  // static inner extension class for real DataQuality Support
  public final static class DQ extends OverviewPGImpl implements org.cougaar.planning.ldm.dq.NewHasDataQuality {
   public DQ() {
    super();
   }
   public DQ(OverviewPG original) {
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


  private transient OverviewPG _locked = null;
  public PropertyGroup lock(Object key) {
    if (_locked == null)_locked = new _Locked(key);
    return _locked; }
  public PropertyGroup lock() { return lock(null); }
  public NewPropertyGroup unlock(Object key) { return this; }

  public Object clone() throws CloneNotSupportedException {
    return new OverviewPGImpl(OverviewPGImpl.this);
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

  private final static PropertyDescriptor properties[] = new PropertyDescriptor[6];
  static {
    try {
      properties[0]= new PropertyDescriptor("title", OverviewPG.class, "getTitle", null);
      properties[1]= new PropertyDescriptor("tableOfContents", OverviewPG.class, "getTableOfContents", null);
      properties[2]= new PropertyDescriptor("salesRank", OverviewPG.class, "getSalesRank", null);
      properties[3]= new PropertyDescriptor("publisherNotes", OverviewPG.class, "getPublisherNotes", null);
      properties[4]= new PropertyDescriptor("subjectKeywords", OverviewPG.class, "getSubjectKeywords", null);
      properties[5]= new PropertyDescriptor("searchableTitle", OverviewPG.class, "getSearchableTitle", null);
    } catch (Exception e) { 
      org.cougaar.util.log.Logging.getLogger(OverviewPG.class).error("Caught exception",e);
    }
  }

  public PropertyDescriptor[] getPropertyDescriptors() {
    return properties;
  }
  private final class _Locked extends java.beans.SimpleBeanInfo
    implements OverviewPG, Cloneable, LockedPG
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
         return OverviewPGImpl.this;
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
      return new OverviewPGImpl(OverviewPGImpl.this);
    }

    public String getTitle() { return OverviewPGImpl.this.getTitle(); }
    public String getTableOfContents() { return OverviewPGImpl.this.getTableOfContents(); }
    public int getSalesRank() { return OverviewPGImpl.this.getSalesRank(); }
    public String getPublisherNotes() { return OverviewPGImpl.this.getPublisherNotes(); }
    public String getSubjectKeywords() { return OverviewPGImpl.this.getSubjectKeywords(); }
    public String getSearchableTitle() { return OverviewPGImpl.this.getSearchableTitle(); }
  public final boolean hasDataQuality() { return OverviewPGImpl.this.hasDataQuality(); }
  public final org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return OverviewPGImpl.this.getDataQuality(); }
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
      return OverviewPGImpl.class;
    }

  }

}
