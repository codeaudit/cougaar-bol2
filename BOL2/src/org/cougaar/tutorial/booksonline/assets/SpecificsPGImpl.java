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
/** Implementation of SpecificsPG.
 *  @see SpecificsPG
 *  @see NewSpecificsPG
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

public class SpecificsPGImpl extends java.beans.SimpleBeanInfo
  implements NewSpecificsPG, Cloneable
{
  public SpecificsPGImpl() {
  }

  // Slots

  private String theShipMessage;
  public String getShipMessage(){ return theShipMessage; }
  public void setShipMessage(String shipMessage) {
    theShipMessage=shipMessage;
  }
  private int thePhotoIndex;
  public int getPhotoIndex(){ return thePhotoIndex; }
  public void setPhotoIndex(int photoIndex) {
    thePhotoIndex=photoIndex;
  }
  private int theMediaType;
  public int getMediaType(){ return theMediaType; }
  public void setMediaType(int mediaType) {
    theMediaType=mediaType;
  }
  private int thePageTrackCount;
  public int getPageTrackCount(){ return thePageTrackCount; }
  public void setPageTrackCount(int pageTrackCount) {
    thePageTrackCount=pageTrackCount;
  }
  private long theReleaseDate;
  public long getReleaseDate(){ return theReleaseDate; }
  public void setReleaseDate(long releaseDate) {
    theReleaseDate=releaseDate;
  }
  private String theISBNASIN;
  public String getISBNASIN(){ return theISBNASIN; }
  public void setISBNASIN(String ISBNASIN) {
    theISBNASIN=ISBNASIN;
  }
  private String theDimension;
  public String getDimension(){ return theDimension; }
  public void setDimension(String dimension) {
    theDimension=dimension;
  }


  public SpecificsPGImpl(SpecificsPG original) {
    theShipMessage = original.getShipMessage();
    thePhotoIndex = original.getPhotoIndex();
    theMediaType = original.getMediaType();
    thePageTrackCount = original.getPageTrackCount();
    theReleaseDate = original.getReleaseDate();
    theISBNASIN = original.getISBNASIN();
    theDimension = original.getDimension();
  }

  public boolean hasDataQuality() { return false; }
  public org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return null; }

  // static inner extension class for real DataQuality Support
  public final static class DQ extends SpecificsPGImpl implements org.cougaar.planning.ldm.dq.NewHasDataQuality {
   public DQ() {
    super();
   }
   public DQ(SpecificsPG original) {
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


  private transient SpecificsPG _locked = null;
  public PropertyGroup lock(Object key) {
    if (_locked == null)_locked = new _Locked(key);
    return _locked; }
  public PropertyGroup lock() { return lock(null); }
  public NewPropertyGroup unlock(Object key) { return this; }

  public Object clone() throws CloneNotSupportedException {
    return new SpecificsPGImpl(SpecificsPGImpl.this);
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
      properties[0]= new PropertyDescriptor("shipMessage", SpecificsPG.class, "getShipMessage", null);
      properties[1]= new PropertyDescriptor("photoIndex", SpecificsPG.class, "getPhotoIndex", null);
      properties[2]= new PropertyDescriptor("mediaType", SpecificsPG.class, "getMediaType", null);
      properties[3]= new PropertyDescriptor("pageTrackCount", SpecificsPG.class, "getPageTrackCount", null);
      properties[4]= new PropertyDescriptor("releaseDate", SpecificsPG.class, "getReleaseDate", null);
      properties[5]= new PropertyDescriptor("ISBNASIN", SpecificsPG.class, "getISBNASIN", null);
      properties[6]= new PropertyDescriptor("dimension", SpecificsPG.class, "getDimension", null);
    } catch (Exception e) { 
      org.cougaar.util.log.Logging.getLogger(SpecificsPG.class).error("Caught exception",e);
    }
  }

  public PropertyDescriptor[] getPropertyDescriptors() {
    return properties;
  }
  private final class _Locked extends java.beans.SimpleBeanInfo
    implements SpecificsPG, Cloneable, LockedPG
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
         return SpecificsPGImpl.this;
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
      return new SpecificsPGImpl(SpecificsPGImpl.this);
    }

    public String getShipMessage() { return SpecificsPGImpl.this.getShipMessage(); }
    public int getPhotoIndex() { return SpecificsPGImpl.this.getPhotoIndex(); }
    public int getMediaType() { return SpecificsPGImpl.this.getMediaType(); }
    public int getPageTrackCount() { return SpecificsPGImpl.this.getPageTrackCount(); }
    public long getReleaseDate() { return SpecificsPGImpl.this.getReleaseDate(); }
    public String getISBNASIN() { return SpecificsPGImpl.this.getISBNASIN(); }
    public String getDimension() { return SpecificsPGImpl.this.getDimension(); }
  public final boolean hasDataQuality() { return SpecificsPGImpl.this.hasDataQuality(); }
  public final org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return SpecificsPGImpl.this.getDataQuality(); }
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
      return SpecificsPGImpl.class;
    }

  }

}
