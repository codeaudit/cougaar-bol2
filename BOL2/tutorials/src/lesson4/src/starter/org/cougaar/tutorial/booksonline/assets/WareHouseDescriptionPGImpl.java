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
/** Implementation of WareHouseDescriptionPG.
 *  @see WareHouseDescriptionPG
 *  @see NewWareHouseDescriptionPG
 **/

package org.cougaar.tutorial.booksonline.assets;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.cougaar.planning.ldm.asset.LockedPG;
import org.cougaar.planning.ldm.asset.NewPropertyGroup;
import org.cougaar.planning.ldm.asset.PropertyGroup;

public class WareHouseDescriptionPGImpl extends java.beans.SimpleBeanInfo
  implements NewWareHouseDescriptionPG, Cloneable
{
  public WareHouseDescriptionPGImpl() {
  }

  // Slots

  private String theName;
  public String getName(){ return theName; }
  public void setName(String name) {
    if (name!=null) name=name.intern();
    theName=name;
  }
  private String theAddress1;
  public String getAddress1(){ return theAddress1; }
  public void setAddress1(String address1) {
    if (address1!=null) address1=address1.intern();
    theAddress1=address1;
  }
  private String theAddress2;
  public String getAddress2(){ return theAddress2; }
  public void setAddress2(String address2) {
    if (address2!=null) address2=address2.intern();
    theAddress2=address2;
  }
  private String theCity;
  public String getCity(){ return theCity; }
  public void setCity(String city) {
    if (city!=null) city=city.intern();
    theCity=city;
  }
  private String theState;
  public String getState(){ return theState; }
  public void setState(String state) {
    if (state!=null) state=state.intern();
    theState=state;
  }
  private String theZipCode;
  public String getZipCode(){ return theZipCode; }
  public void setZipCode(String zipCode) {
    if (zipCode!=null) zipCode=zipCode.intern();
    theZipCode=zipCode;
  }
  private String theCcExpDate;
  public String getCcExpDate(){ return theCcExpDate; }
  public void setCcExpDate(String ccExpDate) {
    if (ccExpDate!=null) ccExpDate=ccExpDate.intern();
    theCcExpDate=ccExpDate;
  }
  private long theCcNumber;
  public long getCcNumber(){ return theCcNumber; }
  public void setCcNumber(long ccNumber) {
    theCcNumber=ccNumber;
  }


  public WareHouseDescriptionPGImpl(WareHouseDescriptionPG original) {
    theName = original.getName();
    theAddress1 = original.getAddress1();
    theAddress2 = original.getAddress2();
    theCity = original.getCity();
    theState = original.getState();
    theZipCode = original.getZipCode();
    theCcExpDate = original.getCcExpDate();
    theCcNumber = original.getCcNumber();
  }

  public boolean hasDataQuality() { return false; }
  public org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return null; }

  // static inner extension class for real DataQuality Support
  public final static class DQ extends WareHouseDescriptionPGImpl implements org.cougaar.planning.ldm.dq.NewHasDataQuality {
   public DQ() {
    super();
   }
   public DQ(WareHouseDescriptionPG original) {
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


  private transient WareHouseDescriptionPG _locked = null;
  public PropertyGroup lock(Object key) {
    if (_locked == null)_locked = new _Locked(key);
    return _locked; }
  public PropertyGroup lock() { return lock(null); }
  public NewPropertyGroup unlock(Object key) { return this; }

  public Object clone() throws CloneNotSupportedException {
    return new WareHouseDescriptionPGImpl(WareHouseDescriptionPGImpl.this);
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
    if (theName!= null) theName=theName.intern();
    if (theAddress1!= null) theAddress1=theAddress1.intern();
    if (theAddress2!= null) theAddress2=theAddress2.intern();
    if (theCity!= null) theCity=theCity.intern();
    if (theState!= null) theState=theState.intern();
    if (theZipCode!= null) theZipCode=theZipCode.intern();
    if (theCcExpDate!= null) theCcExpDate=theCcExpDate.intern();
  }

  private final static PropertyDescriptor properties[] = new PropertyDescriptor[8];
  static {
    try {
      properties[0]= new PropertyDescriptor("name", WareHouseDescriptionPG.class, "getName", null);
      properties[1]= new PropertyDescriptor("address1", WareHouseDescriptionPG.class, "getAddress1", null);
      properties[2]= new PropertyDescriptor("address2", WareHouseDescriptionPG.class, "getAddress2", null);
      properties[3]= new PropertyDescriptor("city", WareHouseDescriptionPG.class, "getCity", null);
      properties[4]= new PropertyDescriptor("state", WareHouseDescriptionPG.class, "getState", null);
      properties[5]= new PropertyDescriptor("zipCode", WareHouseDescriptionPG.class, "getZipCode", null);
      properties[6]= new PropertyDescriptor("ccExpDate", WareHouseDescriptionPG.class, "getCcExpDate", null);
      properties[7]= new PropertyDescriptor("ccNumber", WareHouseDescriptionPG.class, "getCcNumber", null);
    } catch (Exception e) { 
      org.cougaar.util.log.Logging.getLogger(WareHouseDescriptionPG.class).error("Caught exception",e);
    }
  }

  public PropertyDescriptor[] getPropertyDescriptors() {
    return properties;
  }
  private final class _Locked extends java.beans.SimpleBeanInfo
    implements WareHouseDescriptionPG, Cloneable, LockedPG
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
         return WareHouseDescriptionPGImpl.this;
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
      return new WareHouseDescriptionPGImpl(WareHouseDescriptionPGImpl.this);
    }

    public String getName() { return WareHouseDescriptionPGImpl.this.getName(); }
    public String getAddress1() { return WareHouseDescriptionPGImpl.this.getAddress1(); }
    public String getAddress2() { return WareHouseDescriptionPGImpl.this.getAddress2(); }
    public String getCity() { return WareHouseDescriptionPGImpl.this.getCity(); }
    public String getState() { return WareHouseDescriptionPGImpl.this.getState(); }
    public String getZipCode() { return WareHouseDescriptionPGImpl.this.getZipCode(); }
    public String getCcExpDate() { return WareHouseDescriptionPGImpl.this.getCcExpDate(); }
    public long getCcNumber() { return WareHouseDescriptionPGImpl.this.getCcNumber(); }
  public final boolean hasDataQuality() { return WareHouseDescriptionPGImpl.this.hasDataQuality(); }
  public final org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return WareHouseDescriptionPGImpl.this.getDataQuality(); }
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
      return WareHouseDescriptionPGImpl.class;
    }

  }

}
