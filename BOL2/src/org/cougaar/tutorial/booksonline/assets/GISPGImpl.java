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
/** Implementation of GISPG.
 *  @see GISPG
 *  @see NewGISPG
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

public class GISPGImpl extends java.beans.SimpleBeanInfo
  implements NewGISPG, Cloneable
{
  public GISPGImpl() {
  }

  // Slots

  private String theSourceCity;
  public String getSourceCity(){ return theSourceCity; }
  public void setSourceCity(String sourceCity) {
    theSourceCity=sourceCity;
  }
  private double theFromLat;
  public double getFromLat(){ return theFromLat; }
  public void setFromLat(double fromLat) {
    theFromLat=fromLat;
  }
  private double theFromLong;
  public double getFromLong(){ return theFromLong; }
  public void setFromLong(double fromLong) {
    theFromLong=fromLong;
  }

  private org.cougaar.tutorial.booksonline.shipper.GISBehavior gis = null;
  public org.cougaar.tutorial.booksonline.shipper.GISBehavior getGis() {
    return gis;
  }
  public void setGis(org.cougaar.tutorial.booksonline.shipper.GISBehavior _gis) {
    if (gis != null) throw new IllegalArgumentException("gis already set");
    gis = _gis;
  }
  public double calcETA(String destDepot) { return gis.calcETA(destDepot);  }
  public String getNearestDepot(String destCity, String destState) { return gis.getNearestDepot(destCity, destState);  }
  public int getRouteNumber(String destDepot) { return gis.getRouteNumber(destDepot);  }

  public GISPGImpl(GISPG original) {
    theSourceCity = original.getSourceCity();
    theFromLat = original.getFromLat();
    theFromLong = original.getFromLong();
  }

  public boolean hasDataQuality() { return false; }
  public org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return null; }

  // static inner extension class for real DataQuality Support
  public final static class DQ extends GISPGImpl implements org.cougaar.planning.ldm.dq.NewHasDataQuality {
   public DQ() {
    super();
   }
   public DQ(GISPG original) {
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


  private transient GISPG _locked = null;
  public PropertyGroup lock(Object key) {
    if (_locked == null)_locked = new _Locked(key);
    return _locked; }
  public PropertyGroup lock() { return lock(null); }
  public NewPropertyGroup unlock(Object key) { return this; }

  public Object clone() throws CloneNotSupportedException {
    GISPGImpl _tmp = new GISPGImpl(this);
    if (gis != null) {
      _tmp.gis = (org.cougaar.tutorial.booksonline.shipper.GISBehavior) gis.copy(_tmp);
    }
    return _tmp;
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

  private final static PropertyDescriptor properties[] = new PropertyDescriptor[3];
  static {
    try {
      properties[0]= new PropertyDescriptor("sourceCity", GISPG.class, "getSourceCity", null);
      properties[1]= new PropertyDescriptor("fromLat", GISPG.class, "getFromLat", null);
      properties[2]= new PropertyDescriptor("fromLong", GISPG.class, "getFromLong", null);
    } catch (Exception e) { 
      org.cougaar.util.log.Logging.getLogger(GISPG.class).error("Caught exception",e);
    }
  }

  public PropertyDescriptor[] getPropertyDescriptors() {
    return properties;
  }
  private final class _Locked extends java.beans.SimpleBeanInfo
    implements GISPG, Cloneable, LockedPG
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
         return GISPGImpl.this;
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
      GISPGImpl _tmp = new GISPGImpl(this);
      if (gis != null) {
        _tmp.gis = (org.cougaar.tutorial.booksonline.shipper.GISBehavior) gis.copy(_tmp);
      }
      return _tmp;
    }

    public String getSourceCity() { return GISPGImpl.this.getSourceCity(); }
    public double getFromLat() { return GISPGImpl.this.getFromLat(); }
    public double getFromLong() { return GISPGImpl.this.getFromLong(); }
  public double calcETA(String destDepot) {
    return GISPGImpl.this.calcETA(destDepot);
  }
  public String getNearestDepot(String destCity, String destState) {
    return GISPGImpl.this.getNearestDepot(destCity, destState);
  }
  public int getRouteNumber(String destDepot) {
    return GISPGImpl.this.getRouteNumber(destDepot);
  }
  public final boolean hasDataQuality() { return GISPGImpl.this.hasDataQuality(); }
  public final org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return GISPGImpl.this.getDataQuality(); }
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
      return GISPGImpl.class;
    }

  }

}
