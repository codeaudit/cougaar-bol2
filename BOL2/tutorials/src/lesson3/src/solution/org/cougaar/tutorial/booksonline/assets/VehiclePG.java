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
/** Primary client interface for VehiclePG.
 * Vehicles used for shipping
 *  @see NewVehiclePG
 *  @see VehiclePGImpl
 **/

package org.cougaar.tutorial.booksonline.assets;

import org.cougaar.planning.ldm.asset.Future_PG;
import org.cougaar.planning.ldm.asset.NewPropertyGroup;
import org.cougaar.planning.ldm.asset.Null_PG;
import org.cougaar.planning.ldm.asset.PropertyGroup;
import org.cougaar.planning.ldm.asset.UndefinedValueException;



public interface VehiclePG extends PropertyGroup, org.cougaar.planning.ldm.dq.HasDataQuality {
  /** type of vehicle(plane or truck) **/
  String getType();
  /** How much can it hold **/
  int getCapacity();
  /** what region the vehicle serves **/
  String getRegion();
  /** current load **/
  int getLoad();
  /** Vehicle ID **/
  String getVid();
  /** vehicle availability **/
  boolean getFree();
  /** number of loads waiting for this asset **/
  int getContainers();

  // introspection and construction
  /** the method of factoryClass that creates this type **/
  String factoryMethod = "newVehiclePG";
  /** the (mutable) class type returned by factoryMethod **/
  String mutableClass = "org.cougaar.tutorial.booksonline.assets.NewVehiclePG";
  /** the factory class **/
  Class factoryClass = org.cougaar.tutorial.booksonline.assets.PropertyGroupFactory.class;
  /** the (immutable) class type returned by domain factory **/
   Class primaryClass = org.cougaar.tutorial.booksonline.assets.VehiclePG.class;
  String assetSetter = "setVehiclePG";
  String assetGetter = "getVehiclePG";
  /** The Null instance for indicating that the PG definitely has no value **/
  VehiclePG nullPG = new Null_VehiclePG();

/** Null_PG implementation for VehiclePG **/
final class Null_VehiclePG
  implements VehiclePG, Null_PG
{
  public String getType() { throw new UndefinedValueException(); }
  public int getCapacity() { throw new UndefinedValueException(); }
  public String getRegion() { throw new UndefinedValueException(); }
  public int getLoad() { throw new UndefinedValueException(); }
  public String getVid() { throw new UndefinedValueException(); }
  public boolean getFree() { throw new UndefinedValueException(); }
  public int getContainers() { throw new UndefinedValueException(); }
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
    return VehiclePGImpl.class;
  }

  public boolean hasDataQuality() { return false; }
  public org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return null; }
}

/** Future PG implementation for VehiclePG **/
final class Future
  implements VehiclePG, Future_PG
{
  public String getType() {
    waitForFinalize();
    return _real.getType();
  }
  public int getCapacity() {
    waitForFinalize();
    return _real.getCapacity();
  }
  public String getRegion() {
    waitForFinalize();
    return _real.getRegion();
  }
  public int getLoad() {
    waitForFinalize();
    return _real.getLoad();
  }
  public String getVid() {
    waitForFinalize();
    return _real.getVid();
  }
  public boolean getFree() {
    waitForFinalize();
    return _real.getFree();
  }
  public int getContainers() {
    waitForFinalize();
    return _real.getContainers();
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
    return VehiclePGImpl.class;
  }
  public synchronized boolean hasDataQuality() {
    return (_real!=null) && _real.hasDataQuality();
  }
  public synchronized org.cougaar.planning.ldm.dq.DataQuality getDataQuality() {
    return (_real==null)?null:(_real.getDataQuality());
  }

  // Finalization support
  private VehiclePG _real = null;
  public synchronized void finalize(PropertyGroup real) {
    if (real instanceof VehiclePG) {
      _real=(VehiclePG) real;
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
