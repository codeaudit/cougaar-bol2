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
/** Implementation of VehiclePG.
 *  @see VehiclePG
 *  @see NewVehiclePG
 **/

package org.cougaar.tutorial.booksonline.assets;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.cougaar.planning.ldm.asset.LockedPG;
import org.cougaar.planning.ldm.asset.NewPropertyGroup;
import org.cougaar.planning.ldm.asset.PropertyGroup;

public class VehiclePGImpl extends java.beans.SimpleBeanInfo
  implements NewVehiclePG, Cloneable
{
  public VehiclePGImpl() {
  }

  // Slots

  private String theType;
  public String getType(){ return theType; }
  public void setType(String type) {
    if (type!=null) type=type.intern();
    theType=type;
  }
  private int theCapacity;
  public int getCapacity(){ return theCapacity; }
  public void setCapacity(int capacity) {
    theCapacity=capacity;
  }
  private String theRegion;
  public String getRegion(){ return theRegion; }
  public void setRegion(String region) {
    if (region!=null) region=region.intern();
    theRegion=region;
  }
  private int theLoad;
  public int getLoad(){ return theLoad; }
  public void setLoad(int load) {
    theLoad=load;
  }
  private String theVid;
  public String getVid(){ return theVid; }
  public void setVid(String vid) {
    if (vid!=null) vid=vid.intern();
    theVid=vid;
  }
  private boolean theFree;
  public boolean getFree(){ return theFree; }
  public void setFree(boolean free) {
    theFree=free;
  }
  private int theContainers;
  public int getContainers(){ return theContainers; }
  public void setContainers(int containers) {
    theContainers=containers;
  }


  public VehiclePGImpl(VehiclePG original) {
    theType = original.getType();
    theCapacity = original.getCapacity();
    theRegion = original.getRegion();
    theLoad = original.getLoad();
    theVid = original.getVid();
    theFree = original.getFree();
    theContainers = original.getContainers();
  }

  public boolean hasDataQuality() { return false; }
  public org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return null; }

  // static inner extension class for real DataQuality Support
  public final static class DQ extends VehiclePGImpl implements org.cougaar.planning.ldm.dq.NewHasDataQuality {
   public DQ() {
    super();
   }
   public DQ(VehiclePG original) {
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


  private transient VehiclePG _locked = null;
  public PropertyGroup lock(Object key) {
    if (_locked == null)_locked = new _Locked(key);
    return _locked; }
  public PropertyGroup lock() { return lock(null); }
  public NewPropertyGroup unlock(Object key) { return this; }

  public Object clone() throws CloneNotSupportedException {
    return new VehiclePGImpl(VehiclePGImpl.this);
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

  private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
    in.defaultReadObject();
    if (theType!= null) theType=theType.intern();
    if (theRegion!= null) theRegion=theRegion.intern();
    if (theVid!= null) theVid=theVid.intern();
  }

  private final static PropertyDescriptor properties[] = new PropertyDescriptor[7];
  static {
    try {
      properties[0]= new PropertyDescriptor("type", VehiclePG.class, "getType", null);
      properties[1]= new PropertyDescriptor("capacity", VehiclePG.class, "getCapacity", null);
      properties[2]= new PropertyDescriptor("region", VehiclePG.class, "getRegion", null);
      properties[3]= new PropertyDescriptor("load", VehiclePG.class, "getLoad", null);
      properties[4]= new PropertyDescriptor("vid", VehiclePG.class, "getVid", null);
      properties[5]= new PropertyDescriptor("free", VehiclePG.class, "getFree", null);
      properties[6]= new PropertyDescriptor("containers", VehiclePG.class, "getContainers", null);
    } catch (Exception e) { 
      org.cougaar.util.log.Logging.getLogger(VehiclePG.class).error("Caught exception",e);
    }
  }

  public PropertyDescriptor[] getPropertyDescriptors() {
    return properties;
  }
  private final class _Locked extends java.beans.SimpleBeanInfo
    implements VehiclePG, Cloneable, LockedPG
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
         return VehiclePGImpl.this;
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
      return new VehiclePGImpl(VehiclePGImpl.this);
    }

    public String getType() { return VehiclePGImpl.this.getType(); }
    public int getCapacity() { return VehiclePGImpl.this.getCapacity(); }
    public String getRegion() { return VehiclePGImpl.this.getRegion(); }
    public int getLoad() { return VehiclePGImpl.this.getLoad(); }
    public String getVid() { return VehiclePGImpl.this.getVid(); }
    public boolean getFree() { return VehiclePGImpl.this.getFree(); }
    public int getContainers() { return VehiclePGImpl.this.getContainers(); }
  public final boolean hasDataQuality() { return VehiclePGImpl.this.hasDataQuality(); }
  public final org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return VehiclePGImpl.this.getDataQuality(); }
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
      return VehiclePGImpl.class;
    }

  }

}
