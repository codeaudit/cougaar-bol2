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
/** Primary client interface for GISPG.
 * Estimated time of arrival PG based on location
 *  @see NewGISPG
 *  @see GISPGImpl
 **/

package org.cougaar.tutorial.booksonline.assets;

import org.cougaar.planning.ldm.measure.*;
import org.cougaar.planning.ldm.asset.*;
import org.cougaar.planning.ldm.plan.*;
import java.util.*;



public interface GISPG extends PropertyGroup, org.cougaar.planning.ldm.dq.HasDataQuality {
  String getSourceCity();
  double getFromLat();
  double getFromLong();

  double calcETA(String destDepot);
  String getNearestDepot(String destCity, String destState);
  int getRouteNumber(String destDepot);
  // introspection and construction
  /** the method of factoryClass that creates this type **/
  String factoryMethod = "newGISPG";
  /** the (mutable) class type returned by factoryMethod **/
  String mutableClass = "org.cougaar.tutorial.booksonline.assets.NewGISPG";
  /** the factory class **/
  Class factoryClass = org.cougaar.tutorial.booksonline.assets.PropertyGroupFactory.class;
  /** the (immutable) class type returned by domain factory **/
   Class primaryClass = org.cougaar.tutorial.booksonline.assets.GISPG.class;
  String assetSetter = "setGISPG";
  String assetGetter = "getGISPG";
  /** The Null instance for indicating that the PG definitely has no value **/
  GISPG nullPG = new Null_GISPG();

/** Null_PG implementation for GISPG **/
final class Null_GISPG
  implements GISPG, Null_PG
{
  public String getSourceCity() { throw new UndefinedValueException(); }
  public double getFromLat() { throw new UndefinedValueException(); }
  public double getFromLong() { throw new UndefinedValueException(); }
  public org.cougaar.tutorial.booksonline.shipper.GISBehavior getGis() {
    throw new UndefinedValueException();
  }
  public void setGis(org.cougaar.tutorial.booksonline.shipper.GISBehavior _gis) {
    throw new UndefinedValueException();
  }
  public double calcETA(String destDepot) { throw new UndefinedValueException(); }
  public String getNearestDepot(String destCity, String destState) { throw new UndefinedValueException(); }
  public int getRouteNumber(String destDepot) { throw new UndefinedValueException(); }
  public boolean equals(Object object) { throw new UndefinedValueException(); }
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
    return GISPGImpl.class;
  }

  public boolean hasDataQuality() { return false; }
  public org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return null; }
}

/** Future PG implementation for GISPG **/
final class Future
  implements GISPG, Future_PG
{
  public String getSourceCity() {
    waitForFinalize();
    return _real.getSourceCity();
  }
  public double getFromLat() {
    waitForFinalize();
    return _real.getFromLat();
  }
  public double getFromLong() {
    waitForFinalize();
    return _real.getFromLong();
  }
  public boolean equals(Object object) {
    waitForFinalize();
    return _real.equals(object);
  }
  public double calcETA(String destDepot) {
    waitForFinalize();
    return _real.calcETA(destDepot);
  }
  public String getNearestDepot(String destCity, String destState) {
    waitForFinalize();
    return _real.getNearestDepot(destCity, destState);
  }
  public int getRouteNumber(String destDepot) {
    waitForFinalize();
    return _real.getRouteNumber(destDepot);
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
    return GISPGImpl.class;
  }
  public synchronized boolean hasDataQuality() {
    return (_real!=null) && _real.hasDataQuality();
  }
  public synchronized org.cougaar.planning.ldm.dq.DataQuality getDataQuality() {
    return (_real==null)?null:(_real.getDataQuality());
  }

  // Finalization support
  private GISPG _real = null;
  public synchronized void finalize(PropertyGroup real) {
    if (real instanceof GISPG) {
      _real=(GISPG) real;
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
