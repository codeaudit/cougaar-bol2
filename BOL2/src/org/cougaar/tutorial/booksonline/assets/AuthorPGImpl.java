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
/** Implementation of AuthorPG.
 *  @see AuthorPG
 *  @see NewAuthorPG
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

public class AuthorPGImpl extends java.beans.SimpleBeanInfo
  implements NewAuthorPG, Cloneable
{
  public AuthorPGImpl() {
  }

  // Slots

  private String theDisplayName;
  public String getDisplayName(){ return theDisplayName; }
  public void setDisplayName(String displayName) {
    theDisplayName=displayName;
  }
  private String theAffiliation;
  public String getAffiliation(){ return theAffiliation; }
  public void setAffiliation(String affiliation) {
    theAffiliation=affiliation;
  }
  private String theInterview;
  public String getInterview(){ return theInterview; }
  public void setInterview(String interview) {
    theInterview=interview;
  }
  private String theSearchableName;
  public String getSearchableName(){ return theSearchableName; }
  public void setSearchableName(String searchableName) {
    theSearchableName=searchableName;
  }


  public AuthorPGImpl(AuthorPG original) {
    theDisplayName = original.getDisplayName();
    theAffiliation = original.getAffiliation();
    theInterview = original.getInterview();
    theSearchableName = original.getSearchableName();
  }

  public boolean equals(Object other) {

    if (!(other instanceof AuthorPG)) {
      return false;
    }

    AuthorPG otherAuthorPG = (AuthorPG) other;

    if (getDisplayName() == null) {
      if (otherAuthorPG.getDisplayName() != null) {
        return false;
      }
    } else if (!(getDisplayName().equals(otherAuthorPG.getDisplayName()))) {
      return false;
    }

    if (getAffiliation() == null) {
      if (otherAuthorPG.getAffiliation() != null) {
        return false;
      }
    } else if (!(getAffiliation().equals(otherAuthorPG.getAffiliation()))) {
      return false;
    }

    if (getInterview() == null) {
      if (otherAuthorPG.getInterview() != null) {
        return false;
      }
    } else if (!(getInterview().equals(otherAuthorPG.getInterview()))) {
      return false;
    }

    if (getSearchableName() == null) {
      if (otherAuthorPG.getSearchableName() != null) {
        return false;
      }
    } else if (!(getSearchableName().equals(otherAuthorPG.getSearchableName()))) {
      return false;
    }

    return true;
  }

  public boolean hasDataQuality() { return false; }
  public org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return null; }

  // static inner extension class for real DataQuality Support
  public final static class DQ extends AuthorPGImpl implements org.cougaar.planning.ldm.dq.NewHasDataQuality {
   public DQ() {
    super();
   }
   public DQ(AuthorPG original) {
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


  private transient AuthorPG _locked = null;
  public PropertyGroup lock(Object key) {
    if (_locked == null)_locked = new _Locked(key);
    return _locked; }
  public PropertyGroup lock() { return lock(null); }
  public NewPropertyGroup unlock(Object key) { return this; }

  public Object clone() throws CloneNotSupportedException {
    return new AuthorPGImpl(AuthorPGImpl.this);
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

  private final static PropertyDescriptor properties[] = new PropertyDescriptor[4];
  static {
    try {
      properties[0]= new PropertyDescriptor("displayName", AuthorPG.class, "getDisplayName", null);
      properties[1]= new PropertyDescriptor("affiliation", AuthorPG.class, "getAffiliation", null);
      properties[2]= new PropertyDescriptor("interview", AuthorPG.class, "getInterview", null);
      properties[3]= new PropertyDescriptor("searchableName", AuthorPG.class, "getSearchableName", null);
    } catch (Exception e) { 
      org.cougaar.util.log.Logging.getLogger(AuthorPG.class).error("Caught exception",e);
    }
  }

  public PropertyDescriptor[] getPropertyDescriptors() {
    return properties;
  }
  private final class _Locked extends java.beans.SimpleBeanInfo
    implements AuthorPG, Cloneable, LockedPG
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
         return AuthorPGImpl.this;
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
      return new AuthorPGImpl(AuthorPGImpl.this);
    }

    public boolean equals(Object object) { return AuthorPGImpl.this.equals(object); }
    public String getDisplayName() { return AuthorPGImpl.this.getDisplayName(); }
    public String getAffiliation() { return AuthorPGImpl.this.getAffiliation(); }
    public String getInterview() { return AuthorPGImpl.this.getInterview(); }
    public String getSearchableName() { return AuthorPGImpl.this.getSearchableName(); }
  public final boolean hasDataQuality() { return AuthorPGImpl.this.hasDataQuality(); }
  public final org.cougaar.planning.ldm.dq.DataQuality getDataQuality() { return AuthorPGImpl.this.getDataQuality(); }
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
      return AuthorPGImpl.class;
    }

  }

}
