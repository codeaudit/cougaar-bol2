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
/** Primary client interface for WareHouseDescriptionPG.
 * Characteristics of a warehouse description
 *  @see NewWareHouseDescriptionPG
 *  @see WareHouseDescriptionPGImpl
 **/

package org.cougaar.tutorial.booksonline.assets;

import org.cougaar.planning.ldm.asset.Future_PG;
import org.cougaar.planning.ldm.asset.NewPropertyGroup;
import org.cougaar.planning.ldm.asset.Null_PG;
import org.cougaar.planning.ldm.asset.PropertyGroup;
import org.cougaar.planning.ldm.asset.UndefinedValueException;



public interface WareHouseDescriptionPG extends PropertyGroup, org.cougaar.planning.ldm.dq.HasDataQuality {
  String getName();
  String getAddress1();
  String getAddress2();
  String getCity();
  String getState();
  String getZipCode();
  String getCcExpDate();
  long getCcNumber();

  // introspection and construction
  /** the method of factoryClass that creates this type **/
  String factoryMethod = "newWareHouseDescriptionPG";
  /** the (mutable) class type returned by factoryMethod **/
  String mutableClass = "org.cougaar.tutorial.booksonline.assets.NewWareHouseDescriptionPG";
  /** the factory class **/
  Class factoryClass = org.cougaar.tutorial.booksonline.assets.PropertyGroupFactory.class;
  /** the (immutable) class type returned by domain factory **/
   Class primaryClass = org.cougaar.tutorial.booksonline.assets.WareHouseDescriptionPG.class;
  String assetSetter = "setWareHouseDescriptionPG";
  String assetGetter = "getWareHouseDescriptionPG";
  /** The Null instance for indicating that the PG definitely has no value **/
  WareHouseDescriptionPG nullPG = new Null_WareHouseDescriptionPG();

/** Null_PG implementation for WareHouseDescriptionPG **/
final class Null_WareHouseDescriptionPG
  implements WareHouseDescriptionPG, Null_PG
{
  public String getName() { throw new UndefinedValueException(); }
  public String getAddress1() { throw new UndefinedValueException(); }
  public String getAddress2() { throw new UndefinedValueException(); }
  public String getCity() { throw new UndefinedValueException(); }
  public String getState() { throw new UndefinedValueException(); }
  public String getZipCode() { throw new UndefinedValueException(); }
  public String getCcExpDate() { throw new UndefinedValueException(); }
  public long getCcNumber() { throw new UndefinedValueException(); }
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
    return WareHouseDescriptionPGImpl.class;
  }

  public boolean hasDataQuality() { return false; }
  public org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return null; }
}

/** Future PG implementation for WareHouseDescriptionPG **/
final class Future
  implements WareHouseDescriptionPG, Future_PG
{
  public String getName() {
    waitForFinalize();
    return _real.getName();
  }
  public String getAddress1() {
    waitForFinalize();
    return _real.getAddress1();
  }
  public String getAddress2() {
    waitForFinalize();
    return _real.getAddress2();
  }
  public String getCity() {
    waitForFinalize();
    return _real.getCity();
  }
  public String getState() {
    waitForFinalize();
    return _real.getState();
  }
  public String getZipCode() {
    waitForFinalize();
    return _real.getZipCode();
  }
  public String getCcExpDate() {
    waitForFinalize();
    return _real.getCcExpDate();
  }
  public long getCcNumber() {
    waitForFinalize();
    return _real.getCcNumber();
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
    return WareHouseDescriptionPGImpl.class;
  }
  public synchronized boolean hasDataQuality() {
    return (_real!=null) && _real.hasDataQuality();
  }
  public synchronized org.cougaar.planning.ldm.dq.DataQuality getDataQuality() {
    return (_real==null)?null:(_real.getDataQuality());
  }

  // Finalization support
  private WareHouseDescriptionPG _real = null;
  public synchronized void finalize(PropertyGroup real) {
    if (real instanceof WareHouseDescriptionPG) {
      _real=(WareHouseDescriptionPG) real;
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
