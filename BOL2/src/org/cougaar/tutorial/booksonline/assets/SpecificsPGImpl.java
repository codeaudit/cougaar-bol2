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
/** Implementation of SpecificsPG.
 *  @see SpecificsPG
 *  @see NewSpecificsPG
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

public class SpecificsPGImpl extends java.beans.SimpleBeanInfo
  implements NewSpecificsPG, Cloneable
{
  public SpecificsPGImpl() {
  }

  // Slots

  private String theShipMessage;
  public String getShipMessage(){ return theShipMessage; }
  public void setShipMessage(String shipMessage) {
    theShipMessage=shipMessage;
  }
  private int thePhotoIndex;
  public int getPhotoIndex(){ return thePhotoIndex; }
  public void setPhotoIndex(int photoIndex) {
    thePhotoIndex=photoIndex;
  }
  private int theMediaType;
  public int getMediaType(){ return theMediaType; }
  public void setMediaType(int mediaType) {
    theMediaType=mediaType;
  }
  private int thePageTrackCount;
  public int getPageTrackCount(){ return thePageTrackCount; }
  public void setPageTrackCount(int pageTrackCount) {
    thePageTrackCount=pageTrackCount;
  }
  private long theReleaseDate;
  public long getReleaseDate(){ return theReleaseDate; }
  public void setReleaseDate(long releaseDate) {
    theReleaseDate=releaseDate;
  }
  private String theISBNASIN;
  public String getISBNASIN(){ return theISBNASIN; }
  public void setISBNASIN(String ISBNASIN) {
    theISBNASIN=ISBNASIN;
  }
  private String theDimension;
  public String getDimension(){ return theDimension; }
  public void setDimension(String dimension) {
    theDimension=dimension;
  }


  public SpecificsPGImpl(SpecificsPG original) {
    theShipMessage = original.getShipMessage();
    thePhotoIndex = original.getPhotoIndex();
    theMediaType = original.getMediaType();
    thePageTrackCount = original.getPageTrackCount();
    theReleaseDate = original.getReleaseDate();
    theISBNASIN = original.getISBNASIN();
    theDimension = original.getDimension();
  }

  public boolean equals(Object other) {

    if (!(other instanceof SpecificsPG)) {
      return false;
    }

    SpecificsPG otherSpecificsPG = (SpecificsPG) other;

    if (getShipMessage() == null) {
      if (otherSpecificsPG.getShipMessage() != null) {
        return false;
      }
    } else if (!(getShipMessage().equals(otherSpecificsPG.getShipMessage()))) {
      return false;
    }

    if (!(getPhotoIndex() == otherSpecificsPG.getPhotoIndex())) {
      return false;
    }

    if (!(getMediaType() == otherSpecificsPG.getMediaType())) {
      return false;
    }

    if (!(getPageTrackCount() == otherSpecificsPG.getPageTrackCount())) {
      return false;
    }

    if (!(getReleaseDate() == otherSpecificsPG.getReleaseDate())) {
      return false;
    }

    if (getISBNASIN() == null) {
      if (otherSpecificsPG.getISBNASIN() != null) {
        return false;
      }
    } else if (!(getISBNASIN().equals(otherSpecificsPG.getISBNASIN()))) {
      return false;
    }

    if (getDimension() == null) {
      if (otherSpecificsPG.getDimension() != null) {
        return false;
      }
    } else if (!(getDimension().equals(otherSpecificsPG.getDimension()))) {
      return false;
    }

    return true;
  }

  public boolean hasDataQuality() { return false; }
  public org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return null; }

  // static inner extension class for real DataQuality Support
  public final static class DQ extends SpecificsPGImpl implements org.cougaar.planning.ldm.dq.NewHasDataQuality {
   public DQ() {
    super();
   }
   public DQ(SpecificsPG original) {
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


  private transient SpecificsPG _locked = null;
  public PropertyGroup lock(Object key) {
    if (_locked == null)_locked = new _Locked(key);
    return _locked; }
  public PropertyGroup lock() { return lock(null); }
  public NewPropertyGroup unlock(Object key) { return this; }

  public Object clone() throws CloneNotSupportedException {
    return new SpecificsPGImpl(SpecificsPGImpl.this);
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

  private final static PropertyDescriptor properties[] = new PropertyDescriptor[7];
  static {
    try {
      properties[0]= new PropertyDescriptor("shipMessage", SpecificsPG.class, "getShipMessage", null);
      properties[1]= new PropertyDescriptor("photoIndex", SpecificsPG.class, "getPhotoIndex", null);
      properties[2]= new PropertyDescriptor("mediaType", SpecificsPG.class, "getMediaType", null);
      properties[3]= new PropertyDescriptor("pageTrackCount", SpecificsPG.class, "getPageTrackCount", null);
      properties[4]= new PropertyDescriptor("releaseDate", SpecificsPG.class, "getReleaseDate", null);
      properties[5]= new PropertyDescriptor("ISBNASIN", SpecificsPG.class, "getISBNASIN", null);
      properties[6]= new PropertyDescriptor("dimension", SpecificsPG.class, "getDimension", null);
    } catch (Exception e) { 
      org.cougaar.util.log.Logging.getLogger(SpecificsPG.class).error("Caught exception",e);
    }
  }

  public PropertyDescriptor[] getPropertyDescriptors() {
    return properties;
  }
  private final class _Locked extends java.beans.SimpleBeanInfo
    implements SpecificsPG, Cloneable, LockedPG
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
         return SpecificsPGImpl.this;
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
      return new SpecificsPGImpl(SpecificsPGImpl.this);
    }

    public boolean equals(Object object) { return SpecificsPGImpl.this.equals(object); }
    public String getShipMessage() { return SpecificsPGImpl.this.getShipMessage(); }
    public int getPhotoIndex() { return SpecificsPGImpl.this.getPhotoIndex(); }
    public int getMediaType() { return SpecificsPGImpl.this.getMediaType(); }
    public int getPageTrackCount() { return SpecificsPGImpl.this.getPageTrackCount(); }
    public long getReleaseDate() { return SpecificsPGImpl.this.getReleaseDate(); }
    public String getISBNASIN() { return SpecificsPGImpl.this.getISBNASIN(); }
    public String getDimension() { return SpecificsPGImpl.this.getDimension(); }
  public final boolean hasDataQuality() { return SpecificsPGImpl.this.hasDataQuality(); }
  public final org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return SpecificsPGImpl.this.getDataQuality(); }
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
      return SpecificsPGImpl.class;
    }

  }

}
