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

/* @generated Fri Jul 25 10:24:44 EDT 2003 from bol_assets.def - DO NOT HAND EDIT */
package org.cougaar.tutorial.booksonline.assets;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import org.cougaar.planning.ldm.asset.Asset;
import org.cougaar.planning.ldm.asset.Future_PG;
import org.cougaar.planning.ldm.asset.Null_PG;
import org.cougaar.planning.ldm.asset.PropertyGroup;
import org.cougaar.planning.ldm.asset.PropertyGroupSchedule;
/** Representation of a book in our inventory including author, title, and pricing info **/

public class BookAsset extends Asset {

  public BookAsset() {
    myOverviewPG = null;
    myAuthorPG = null;
    mySpecificsPG = null;
    myPricePG = null;
    myReviewPG = null;
    myPublisherPG = null;
  }

  public BookAsset(BookAsset prototype) {
    super(prototype);
    myOverviewPG=null;
    myAuthorPG=null;
    mySpecificsPG=null;
    myPricePG=null;
    myReviewPG=null;
    myPublisherPG=null;
  }

  /** For infrastructure only - use org.cougaar.core.domain.Factory.copyInstance instead. **/
  public Object clone() throws CloneNotSupportedException {
    BookAsset _thing = (BookAsset) super.clone();
    if (myOverviewPG!=null) _thing.setOverviewPG(myOverviewPG.lock());
    if (myAuthorPG!=null) _thing.setAuthorPG(myAuthorPG.lock());
    if (mySpecificsPG!=null) _thing.setSpecificsPG(mySpecificsPG.lock());
    if (myPricePG!=null) _thing.setPricePG(myPricePG.lock());
    if (myReviewPG!=null) _thing.setReviewPG(myReviewPG.lock());
    if (myPublisherPG!=null) _thing.setPublisherPG(myPublisherPG.lock());
    return _thing;
  }

  /** create an instance of the right class for copy operations **/
  public Asset instanceForCopy() {
    return new BookAsset();
  }

  /** create an instance of this prototype **/
  public Asset createInstance() {
    return new BookAsset(this);
  }

  protected void fillAllPropertyGroups(Vector v) {
    super.fillAllPropertyGroups(v);
    { Object _tmp = getOverviewPG();
    if (_tmp != null && !(_tmp instanceof Null_PG)) {
      v.addElement(_tmp);
    } }
    { Object _tmp = getAuthorPG();
    if (_tmp != null && !(_tmp instanceof Null_PG)) {
      v.addElement(_tmp);
    } }
    { Object _tmp = getSpecificsPG();
    if (_tmp != null && !(_tmp instanceof Null_PG)) {
      v.addElement(_tmp);
    } }
    { Object _tmp = getPricePG();
    if (_tmp != null && !(_tmp instanceof Null_PG)) {
      v.addElement(_tmp);
    } }
    { Object _tmp = getReviewPG();
    if (_tmp != null && !(_tmp instanceof Null_PG)) {
      v.addElement(_tmp);
    } }
    { Object _tmp = getPublisherPG();
    if (_tmp != null && !(_tmp instanceof Null_PG)) {
      v.addElement(_tmp);
    } }
  }

  private transient OverviewPG myOverviewPG;

  public OverviewPG getOverviewPG() {
    OverviewPG _tmp = (myOverviewPG != null) ?
      myOverviewPG : (OverviewPG)resolvePG(OverviewPG.class);
    return (_tmp == OverviewPG.nullPG)?null:_tmp;
  }
  public void setOverviewPG(PropertyGroup arg_OverviewPG) {
    if (!(arg_OverviewPG instanceof OverviewPG))
      throw new IllegalArgumentException("setOverviewPG requires a OverviewPG argument.");
    myOverviewPG = (OverviewPG) arg_OverviewPG;
  }

  private transient AuthorPG myAuthorPG;

  public AuthorPG getAuthorPG() {
    AuthorPG _tmp = (myAuthorPG != null) ?
      myAuthorPG : (AuthorPG)resolvePG(AuthorPG.class);
    return (_tmp == AuthorPG.nullPG)?null:_tmp;
  }
  public void setAuthorPG(PropertyGroup arg_AuthorPG) {
    if (!(arg_AuthorPG instanceof AuthorPG))
      throw new IllegalArgumentException("setAuthorPG requires a AuthorPG argument.");
    myAuthorPG = (AuthorPG) arg_AuthorPG;
  }

  private transient SpecificsPG mySpecificsPG;

  public SpecificsPG getSpecificsPG() {
    SpecificsPG _tmp = (mySpecificsPG != null) ?
      mySpecificsPG : (SpecificsPG)resolvePG(SpecificsPG.class);
    return (_tmp == SpecificsPG.nullPG)?null:_tmp;
  }
  public void setSpecificsPG(PropertyGroup arg_SpecificsPG) {
    if (!(arg_SpecificsPG instanceof SpecificsPG))
      throw new IllegalArgumentException("setSpecificsPG requires a SpecificsPG argument.");
    mySpecificsPG = (SpecificsPG) arg_SpecificsPG;
  }

  private transient PricePG myPricePG;

  public PricePG getPricePG() {
    PricePG _tmp = (myPricePG != null) ?
      myPricePG : (PricePG)resolvePG(PricePG.class);
    return (_tmp == PricePG.nullPG)?null:_tmp;
  }
  public void setPricePG(PropertyGroup arg_PricePG) {
    if (!(arg_PricePG instanceof PricePG))
      throw new IllegalArgumentException("setPricePG requires a PricePG argument.");
    myPricePG = (PricePG) arg_PricePG;
  }

  private transient ReviewPG myReviewPG;

  public ReviewPG getReviewPG() {
    ReviewPG _tmp = (myReviewPG != null) ?
      myReviewPG : (ReviewPG)resolvePG(ReviewPG.class);
    return (_tmp == ReviewPG.nullPG)?null:_tmp;
  }
  public void setReviewPG(PropertyGroup arg_ReviewPG) {
    if (!(arg_ReviewPG instanceof ReviewPG))
      throw new IllegalArgumentException("setReviewPG requires a ReviewPG argument.");
    myReviewPG = (ReviewPG) arg_ReviewPG;
  }

  private transient PublisherPG myPublisherPG;

  public PublisherPG getPublisherPG() {
    PublisherPG _tmp = (myPublisherPG != null) ?
      myPublisherPG : (PublisherPG)resolvePG(PublisherPG.class);
    return (_tmp == PublisherPG.nullPG)?null:_tmp;
  }
  public void setPublisherPG(PropertyGroup arg_PublisherPG) {
    if (!(arg_PublisherPG instanceof PublisherPG))
      throw new IllegalArgumentException("setPublisherPG requires a PublisherPG argument.");
    myPublisherPG = (PublisherPG) arg_PublisherPG;
  }

  // generic search methods
  public PropertyGroup getLocalPG(Class c, long t) {
    if (OverviewPG.class.equals(c)) {
      return (myOverviewPG==OverviewPG.nullPG)?null:myOverviewPG;
    }
    if (AuthorPG.class.equals(c)) {
      return (myAuthorPG==AuthorPG.nullPG)?null:myAuthorPG;
    }
    if (SpecificsPG.class.equals(c)) {
      return (mySpecificsPG==SpecificsPG.nullPG)?null:mySpecificsPG;
    }
    if (PricePG.class.equals(c)) {
      return (myPricePG==PricePG.nullPG)?null:myPricePG;
    }
    if (ReviewPG.class.equals(c)) {
      return (myReviewPG==ReviewPG.nullPG)?null:myReviewPG;
    }
    if (PublisherPG.class.equals(c)) {
      return (myPublisherPG==PublisherPG.nullPG)?null:myPublisherPG;
    }
    return super.getLocalPG(c,t);
  }

  public PropertyGroupSchedule getLocalPGSchedule(Class c) {
    return super.getLocalPGSchedule(c);
  }

  public void setLocalPG(Class c, PropertyGroup pg) {
    if (OverviewPG.class.equals(c)) {
      myOverviewPG=(OverviewPG)pg;
    } else
    if (AuthorPG.class.equals(c)) {
      myAuthorPG=(AuthorPG)pg;
    } else
    if (SpecificsPG.class.equals(c)) {
      mySpecificsPG=(SpecificsPG)pg;
    } else
    if (PricePG.class.equals(c)) {
      myPricePG=(PricePG)pg;
    } else
    if (ReviewPG.class.equals(c)) {
      myReviewPG=(ReviewPG)pg;
    } else
    if (PublisherPG.class.equals(c)) {
      myPublisherPG=(PublisherPG)pg;
    } else
      super.setLocalPG(c,pg);
  }

  public void setLocalPGSchedule(PropertyGroupSchedule pgSchedule) {
      super.setLocalPGSchedule(pgSchedule);
  }

  public PropertyGroup removeLocalPG(Class c) {
    PropertyGroup removed = null;
    if (OverviewPG.class.equals(c)) {
      removed=myOverviewPG;
      myOverviewPG=null;
    } else
    if (AuthorPG.class.equals(c)) {
      removed=myAuthorPG;
      myAuthorPG=null;
    } else
    if (SpecificsPG.class.equals(c)) {
      removed=mySpecificsPG;
      mySpecificsPG=null;
    } else
    if (PricePG.class.equals(c)) {
      removed=myPricePG;
      myPricePG=null;
    } else
    if (ReviewPG.class.equals(c)) {
      removed=myReviewPG;
      myReviewPG=null;
    } else
    if (PublisherPG.class.equals(c)) {
      removed=myPublisherPG;
      myPublisherPG=null;
    } else
      removed=super.removeLocalPG(c);
    return removed;
  }

  public PropertyGroup removeLocalPG(PropertyGroup pg) {
    PropertyGroup removed = null;
    Class pgc = pg.getPrimaryClass();
    if (OverviewPG.class.equals(pgc)) {
      removed=myOverviewPG;
      myOverviewPG=null;
    } else
    if (AuthorPG.class.equals(pgc)) {
      removed=myAuthorPG;
      myAuthorPG=null;
    } else
    if (SpecificsPG.class.equals(pgc)) {
      removed=mySpecificsPG;
      mySpecificsPG=null;
    } else
    if (PricePG.class.equals(pgc)) {
      removed=myPricePG;
      myPricePG=null;
    } else
    if (ReviewPG.class.equals(pgc)) {
      removed=myReviewPG;
      myReviewPG=null;
    } else
    if (PublisherPG.class.equals(pgc)) {
      removed=myPublisherPG;
      myPublisherPG=null;
    } else
      removed= super.removeLocalPG(pg);
    return removed;
  }

  public PropertyGroupSchedule removeLocalPGSchedule(Class c) {
    PropertyGroupSchedule removed = null;
    return removed;
  }

  public PropertyGroup generateDefaultPG(Class c) {
    if (OverviewPG.class.equals(c)) {
      return (myOverviewPG= new OverviewPGImpl());
    } else
    if (AuthorPG.class.equals(c)) {
      return (myAuthorPG= new AuthorPGImpl());
    } else
    if (SpecificsPG.class.equals(c)) {
      return (mySpecificsPG= new SpecificsPGImpl());
    } else
    if (PricePG.class.equals(c)) {
      return (myPricePG= new PricePGImpl());
    } else
    if (ReviewPG.class.equals(c)) {
      return (myReviewPG= new ReviewPGImpl());
    } else
    if (PublisherPG.class.equals(c)) {
      return (myPublisherPG= new PublisherPGImpl());
    } else
      return super.generateDefaultPG(c);
  }

  // dumb serialization methods

  private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
      if (myOverviewPG instanceof Null_PG || myOverviewPG instanceof Future_PG) {
        out.writeObject(null);
      } else {
        out.writeObject(myOverviewPG);
      }
      if (myAuthorPG instanceof Null_PG || myAuthorPG instanceof Future_PG) {
        out.writeObject(null);
      } else {
        out.writeObject(myAuthorPG);
      }
      if (mySpecificsPG instanceof Null_PG || mySpecificsPG instanceof Future_PG) {
        out.writeObject(null);
      } else {
        out.writeObject(mySpecificsPG);
      }
      if (myPricePG instanceof Null_PG || myPricePG instanceof Future_PG) {
        out.writeObject(null);
      } else {
        out.writeObject(myPricePG);
      }
      if (myReviewPG instanceof Null_PG || myReviewPG instanceof Future_PG) {
        out.writeObject(null);
      } else {
        out.writeObject(myReviewPG);
      }
      if (myPublisherPG instanceof Null_PG || myPublisherPG instanceof Future_PG) {
        out.writeObject(null);
      } else {
        out.writeObject(myPublisherPG);
      }
  }

  private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
    in.defaultReadObject();
      myOverviewPG=(OverviewPG)in.readObject();
      myAuthorPG=(AuthorPG)in.readObject();
      mySpecificsPG=(SpecificsPG)in.readObject();
      myPricePG=(PricePG)in.readObject();
      myReviewPG=(ReviewPG)in.readObject();
      myPublisherPG=(PublisherPG)in.readObject();
  }
  // beaninfo support
  private static PropertyDescriptor properties[];
  static {
    try {
      properties = new PropertyDescriptor[6];
      properties[0] = new PropertyDescriptor("OverviewPG", BookAsset.class, "getOverviewPG", null);
      properties[1] = new PropertyDescriptor("AuthorPG", BookAsset.class, "getAuthorPG", null);
      properties[2] = new PropertyDescriptor("SpecificsPG", BookAsset.class, "getSpecificsPG", null);
      properties[3] = new PropertyDescriptor("PricePG", BookAsset.class, "getPricePG", null);
      properties[4] = new PropertyDescriptor("ReviewPG", BookAsset.class, "getReviewPG", null);
      properties[5] = new PropertyDescriptor("PublisherPG", BookAsset.class, "getPublisherPG", null);
    } catch (IntrospectionException ie) {}
  }

  public PropertyDescriptor[] getPropertyDescriptors() {
    PropertyDescriptor[] pds = super.getPropertyDescriptors();
    PropertyDescriptor[] ps = new PropertyDescriptor[pds.length+6];
    System.arraycopy(pds, 0, ps, 0, pds.length);
    System.arraycopy(properties, 0, ps, pds.length, 6);
    return ps;
  }
}
