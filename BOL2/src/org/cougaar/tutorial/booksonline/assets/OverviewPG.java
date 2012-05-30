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
/** Primary client interface for OverviewPG.
 * Characteristics and properties of the object
 *  @see NewOverviewPG
 *  @see OverviewPGImpl
 **/

package org.cougaar.tutorial.booksonline.assets;

import org.cougaar.planning.ldm.measure.*;
import org.cougaar.planning.ldm.asset.*;
import org.cougaar.planning.ldm.plan.*;
import java.util.*;



public interface OverviewPG extends PropertyGroup, org.cougaar.planning.ldm.dq.HasDataQuality {
  String getTitle();
  String getTableOfContents();
  int getSalesRank();
  String getPublisherNotes();
  String getSubjectKeywords();
  String getSearchableTitle();

  // introspection and construction
  /** the method of factoryClass that creates this type **/
  String factoryMethod = "newOverviewPG";
  /** the (mutable) class type returned by factoryMethod **/
  String mutableClass = "org.cougaar.tutorial.booksonline.assets.NewOverviewPG";
  /** the factory class **/
  Class factoryClass = org.cougaar.tutorial.booksonline.assets.PropertyGroupFactory.class;
  /** the (immutable) class type returned by domain factory **/
   Class primaryClass = org.cougaar.tutorial.booksonline.assets.OverviewPG.class;
  String assetSetter = "setOverviewPG";
  String assetGetter = "getOverviewPG";
  /** The Null instance for indicating that the PG definitely has no value **/
  OverviewPG nullPG = new Null_OverviewPG();

/** Null_PG implementation for OverviewPG **/
final class Null_OverviewPG
  implements OverviewPG, Null_PG
{
  public String getTitle() { throw new UndefinedValueException(); }
  public String getTableOfContents() { throw new UndefinedValueException(); }
  public int getSalesRank() { throw new UndefinedValueException(); }
  public String getPublisherNotes() { throw new UndefinedValueException(); }
  public String getSubjectKeywords() { throw new UndefinedValueException(); }
  public String getSearchableTitle() { throw new UndefinedValueException(); }
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
    return OverviewPGImpl.class;
  }

  public boolean hasDataQuality() { return false; }
  public org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return null; }
}

/** Future PG implementation for OverviewPG **/
final class Future
  implements OverviewPG, Future_PG
{
  public String getTitle() {
    waitForFinalize();
    return _real.getTitle();
  }
  public String getTableOfContents() {
    waitForFinalize();
    return _real.getTableOfContents();
  }
  public int getSalesRank() {
    waitForFinalize();
    return _real.getSalesRank();
  }
  public String getPublisherNotes() {
    waitForFinalize();
    return _real.getPublisherNotes();
  }
  public String getSubjectKeywords() {
    waitForFinalize();
    return _real.getSubjectKeywords();
  }
  public String getSearchableTitle() {
    waitForFinalize();
    return _real.getSearchableTitle();
  }
  public boolean equals(Object object) {
    waitForFinalize();
    return _real.equals(object);
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
    return OverviewPGImpl.class;
  }
  public synchronized boolean hasDataQuality() {
    return (_real!=null) && _real.hasDataQuality();
  }
  public synchronized org.cougaar.planning.ldm.dq.DataQuality getDataQuality() {
    return (_real==null)?null:(_real.getDataQuality());
  }

  // Finalization support
  private OverviewPG _real = null;
  public synchronized void finalize(PropertyGroup real) {
    if (real instanceof OverviewPG) {
      _real=(OverviewPG) real;
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
