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
/** Implementation of WareHouseDescriptionPG.
 *  @see WareHouseDescriptionPG
 *  @see NewWareHouseDescriptionPG
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

public class WareHouseDescriptionPGImpl extends java.beans.SimpleBeanInfo
  implements NewWareHouseDescriptionPG, Cloneable
{
  public WareHouseDescriptionPGImpl() {
  }

  // Slots

  private String theName;
  public String getName(){ return theName; }
  public void setName(String name) {
    theName=name;
  }
  private String theAddress1;
  public String getAddress1(){ return theAddress1; }
  public void setAddress1(String address1) {
    theAddress1=address1;
  }
  private String theAddress2;
  public String getAddress2(){ return theAddress2; }
  public void setAddress2(String address2) {
    theAddress2=address2;
  }
  private String theCity;
  public String getCity(){ return theCity; }
  public void setCity(String city) {
    theCity=city;
  }
  private String theState;
  public String getState(){ return theState; }
  public void setState(String state) {
    theState=state;
  }
  private String theZipCode;
  public String getZipCode(){ return theZipCode; }
  public void setZipCode(String zipCode) {
    theZipCode=zipCode;
  }
  private String theCcExpDate;
  public String getCcExpDate(){ return theCcExpDate; }
  public void setCcExpDate(String ccExpDate) {
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

  public boolean equals(Object other) {

    if (!(other instanceof WareHouseDescriptionPG)) {
      return false;
    }

    WareHouseDescriptionPG otherWareHouseDescriptionPG = (WareHouseDescriptionPG) other;

    if (getName() == null) {
      if (otherWareHouseDescriptionPG.getName() != null) {
        return false;
      }
    } else if (!(getName().equals(otherWareHouseDescriptionPG.getName()))) {
      return false;
    }

    if (getAddress1() == null) {
      if (otherWareHouseDescriptionPG.getAddress1() != null) {
        return false;
      }
    } else if (!(getAddress1().equals(otherWareHouseDescriptionPG.getAddress1()))) {
      return false;
    }

    if (getAddress2() == null) {
      if (otherWareHouseDescriptionPG.getAddress2() != null) {
        return false;
      }
    } else if (!(getAddress2().equals(otherWareHouseDescriptionPG.getAddress2()))) {
      return false;
    }

    if (getCity() == null) {
      if (otherWareHouseDescriptionPG.getCity() != null) {
        return false;
      }
    } else if (!(getCity().equals(otherWareHouseDescriptionPG.getCity()))) {
      return false;
    }

    if (getState() == null) {
      if (otherWareHouseDescriptionPG.getState() != null) {
        return false;
      }
    } else if (!(getState().equals(otherWareHouseDescriptionPG.getState()))) {
      return false;
    }

    if (getZipCode() == null) {
      if (otherWareHouseDescriptionPG.getZipCode() != null) {
        return false;
      }
    } else if (!(getZipCode().equals(otherWareHouseDescriptionPG.getZipCode()))) {
      return false;
    }

    if (getCcExpDate() == null) {
      if (otherWareHouseDescriptionPG.getCcExpDate() != null) {
        return false;
      }
    } else if (!(getCcExpDate().equals(otherWareHouseDescriptionPG.getCcExpDate()))) {
      return false;
    }

    if (!(getCcNumber() == otherWareHouseDescriptionPG.getCcNumber())) {
      return false;
    }

    return true;
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

    public boolean equals(Object object) { return WareHouseDescriptionPGImpl.this.equals(object); }
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
