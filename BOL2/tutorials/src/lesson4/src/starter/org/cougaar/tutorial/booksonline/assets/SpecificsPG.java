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
/** Primary client interface for SpecificsPG.
 * facts about this specific book, cd, dvd, or whatever
 *  @see NewSpecificsPG
 *  @see SpecificsPGImpl
 **/

package org.cougaar.tutorial.booksonline.assets;

import org.cougaar.planning.ldm.asset.Future_PG;
import org.cougaar.planning.ldm.asset.NewPropertyGroup;
import org.cougaar.planning.ldm.asset.Null_PG;
import org.cougaar.planning.ldm.asset.PropertyGroup;
import org.cougaar.planning.ldm.asset.UndefinedValueException;



public interface SpecificsPG extends PropertyGroup, org.cougaar.planning.ldm.dq.HasDataQuality {
  /** message as to when this can ship **/
  String getShipMessage();
  /** index into some (as yet undefined) array of images of the products **/
  int getPhotoIndex();
  /** flag values indicating which form this item takes; CD, DVD, VHS, Book, Beta **/
  int getMediaType();
  /** number of pages in book or number of tracks on CD or lenght of movie in minutes **/
  int getPageTrackCount();
  /** month and year item is available for general consumption **/
  long getReleaseDate();
  /** specific identifying number for this item, not to be confused with a serial number of each physical copy of this **/
  String getISBNASIN();
  /** text describing the shape or the item, if applicable **/
  String getDimension();

  // introspection and construction
  /** the method of factoryClass that creates this type **/
  String factoryMethod = "newSpecificsPG";
  /** the (mutable) class type returned by factoryMethod **/
  String mutableClass = "org.cougaar.tutorial.booksonline.assets.NewSpecificsPG";
  /** the factory class **/
  Class factoryClass = org.cougaar.tutorial.booksonline.assets.PropertyGroupFactory.class;
  /** the (immutable) class type returned by domain factory **/
   Class primaryClass = org.cougaar.tutorial.booksonline.assets.SpecificsPG.class;
  String assetSetter = "setSpecificsPG";
  String assetGetter = "getSpecificsPG";
  /** The Null instance for indicating that the PG definitely has no value **/
  SpecificsPG nullPG = new Null_SpecificsPG();

/** Null_PG implementation for SpecificsPG **/
final class Null_SpecificsPG
  implements SpecificsPG, Null_PG
{
  public String getShipMessage() { throw new UndefinedValueException(); }
  public int getPhotoIndex() { throw new UndefinedValueException(); }
  public int getMediaType() { throw new UndefinedValueException(); }
  public int getPageTrackCount() { throw new UndefinedValueException(); }
  public long getReleaseDate() { throw new UndefinedValueException(); }
  public String getISBNASIN() { throw new UndefinedValueException(); }
  public String getDimension() { throw new UndefinedValueException(); }
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
    return SpecificsPGImpl.class;
  }

  public boolean hasDataQuality() { return false; }
  public org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return null; }
}

/** Future PG implementation for SpecificsPG **/
final class Future
  implements SpecificsPG, Future_PG
{
  public String getShipMessage() {
    waitForFinalize();
    return _real.getShipMessage();
  }
  public int getPhotoIndex() {
    waitForFinalize();
    return _real.getPhotoIndex();
  }
  public int getMediaType() {
    waitForFinalize();
    return _real.getMediaType();
  }
  public int getPageTrackCount() {
    waitForFinalize();
    return _real.getPageTrackCount();
  }
  public long getReleaseDate() {
    waitForFinalize();
    return _real.getReleaseDate();
  }
  public String getISBNASIN() {
    waitForFinalize();
    return _real.getISBNASIN();
  }
  public String getDimension() {
    waitForFinalize();
    return _real.getDimension();
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
    return SpecificsPGImpl.class;
  }
  public synchronized boolean hasDataQuality() {
    return (_real!=null) && _real.hasDataQuality();
  }
  public synchronized org.cougaar.planning.ldm.dq.DataQuality getDataQuality() {
    return (_real==null)?null:(_real.getDataQuality());
  }

  // Finalization support
  private SpecificsPG _real = null;
  public synchronized void finalize(PropertyGroup real) {
    if (real instanceof SpecificsPG) {
      _real=(SpecificsPG) real;
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
