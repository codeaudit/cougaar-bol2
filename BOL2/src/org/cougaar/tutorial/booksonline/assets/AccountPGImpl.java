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
/** Implementation of AccountPG.
 *  @see AccountPG
 *  @see NewAccountPG
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

public class AccountPGImpl extends java.beans.SimpleBeanInfo
  implements NewAccountPG, Cloneable
{
  public AccountPGImpl() {
  }

  // Slots

  private String theCustomeraccount;
  public String getCustomeraccount(){ return theCustomeraccount; }
  public void setCustomeraccount(String customeraccount) {
    theCustomeraccount=customeraccount;
  }
  private String theCustomername;
  public String getCustomername(){ return theCustomername; }
  public void setCustomername(String customername) {
    theCustomername=customername;
  }
  private String theCustomeraddress;
  public String getCustomeraddress(){ return theCustomeraddress; }
  public void setCustomeraddress(String customeraddress) {
    theCustomeraddress=customeraddress;
  }
  private double theCustomerbalance;
  public double getCustomerbalance(){ return theCustomerbalance; }
  public void setCustomerbalance(double customerbalance) {
    theCustomerbalance=customerbalance;
  }
  private double theCustomerlimit;
  public double getCustomerlimit(){ return theCustomerlimit; }
  public void setCustomerlimit(double customerlimit) {
    theCustomerlimit=customerlimit;
  }


  public AccountPGImpl(AccountPG original) {
    theCustomeraccount = original.getCustomeraccount();
    theCustomername = original.getCustomername();
    theCustomeraddress = original.getCustomeraddress();
    theCustomerbalance = original.getCustomerbalance();
    theCustomerlimit = original.getCustomerlimit();
  }

  public boolean equals(Object other) {

    if (!(other instanceof AccountPG)) {
      return false;
    }

    AccountPG otherAccountPG = (AccountPG) other;

    if (getCustomeraccount() == null) {
      if (otherAccountPG.getCustomeraccount() != null) {
        return false;
      }
    } else if (!(getCustomeraccount().equals(otherAccountPG.getCustomeraccount()))) {
      return false;
    }

    if (getCustomername() == null) {
      if (otherAccountPG.getCustomername() != null) {
        return false;
      }
    } else if (!(getCustomername().equals(otherAccountPG.getCustomername()))) {
      return false;
    }

    if (getCustomeraddress() == null) {
      if (otherAccountPG.getCustomeraddress() != null) {
        return false;
      }
    } else if (!(getCustomeraddress().equals(otherAccountPG.getCustomeraddress()))) {
      return false;
    }

    if (!(getCustomerbalance() == otherAccountPG.getCustomerbalance())) {
      return false;
    }

    if (!(getCustomerlimit() == otherAccountPG.getCustomerlimit())) {
      return false;
    }

    return true;
  }

  public boolean hasDataQuality() { return false; }
  public org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return null; }

  // static inner extension class for real DataQuality Support
  public final static class DQ extends AccountPGImpl implements org.cougaar.planning.ldm.dq.NewHasDataQuality {
   public DQ() {
    super();
   }
   public DQ(AccountPG original) {
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


  private transient AccountPG _locked = null;
  public PropertyGroup lock(Object key) {
    if (_locked == null)_locked = new _Locked(key);
    return _locked; }
  public PropertyGroup lock() { return lock(null); }
  public NewPropertyGroup unlock(Object key) { return this; }

  public Object clone() throws CloneNotSupportedException {
    return new AccountPGImpl(AccountPGImpl.this);
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

  private final static PropertyDescriptor properties[] = new PropertyDescriptor[5];
  static {
    try {
      properties[0]= new PropertyDescriptor("customeraccount", AccountPG.class, "getCustomeraccount", null);
      properties[1]= new PropertyDescriptor("customername", AccountPG.class, "getCustomername", null);
      properties[2]= new PropertyDescriptor("customeraddress", AccountPG.class, "getCustomeraddress", null);
      properties[3]= new PropertyDescriptor("customerbalance", AccountPG.class, "getCustomerbalance", null);
      properties[4]= new PropertyDescriptor("customerlimit", AccountPG.class, "getCustomerlimit", null);
    } catch (Exception e) { 
      org.cougaar.util.log.Logging.getLogger(AccountPG.class).error("Caught exception",e);
    }
  }

  public PropertyDescriptor[] getPropertyDescriptors() {
    return properties;
  }
  private final class _Locked extends java.beans.SimpleBeanInfo
    implements AccountPG, Cloneable, LockedPG
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
         return AccountPGImpl.this;
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
      return new AccountPGImpl(AccountPGImpl.this);
    }

    public boolean equals(Object object) { return AccountPGImpl.this.equals(object); }
    public String getCustomeraccount() { return AccountPGImpl.this.getCustomeraccount(); }
    public String getCustomername() { return AccountPGImpl.this.getCustomername(); }
    public String getCustomeraddress() { return AccountPGImpl.this.getCustomeraddress(); }
    public double getCustomerbalance() { return AccountPGImpl.this.getCustomerbalance(); }
    public double getCustomerlimit() { return AccountPGImpl.this.getCustomerlimit(); }
  public final boolean hasDataQuality() { return AccountPGImpl.this.hasDataQuality(); }
  public final org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return AccountPGImpl.this.getDataQuality(); }
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
      return AccountPGImpl.class;
    }

  }

}
