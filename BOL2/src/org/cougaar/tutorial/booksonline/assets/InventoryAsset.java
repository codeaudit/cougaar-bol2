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

/* @generated Tue Jun 15 07:45:54 EDT 2004 from bol_assets.def - DO NOT HAND EDIT */
package org.cougaar.tutorial.booksonline.assets;
import org.cougaar.planning.ldm.asset.*;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Vector;
import java.beans.PropertyDescriptor;
import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
/** Catalog list of books available in the warehouse **/

public class InventoryAsset extends Asset {

  public InventoryAsset() {
    myInvListPG = null;
  }

  public InventoryAsset(InventoryAsset prototype) {
    super(prototype);
    myInvListPG=null;
  }

  /** For infrastructure only - use org.cougaar.core.domain.Factory.copyInstance instead. **/
  public Object clone() throws CloneNotSupportedException {
    InventoryAsset _thing = (InventoryAsset) super.clone();
    if (myInvListPG!=null) _thing.setInvListPG(myInvListPG.lock());
    return _thing;
  }

  /** create an instance of the right class for copy operations **/
  public Asset instanceForCopy() {
    return new InventoryAsset();
  }

  /** create an instance of this prototype **/
  public Asset createInstance() {
    return new InventoryAsset(this);
  }

  protected void fillAllPropertyGroups(Vector v) {
    super.fillAllPropertyGroups(v);
    { Object _tmp = getInvListPG();
    if (_tmp != null && !(_tmp instanceof Null_PG)) {
      v.addElement(_tmp);
    } }
  }

  private transient InvListPG myInvListPG;

  public InvListPG getInvListPG() {
    InvListPG _tmp = (myInvListPG != null) ?
      myInvListPG : (InvListPG)resolvePG(InvListPG.class);
    return (_tmp == InvListPG.nullPG)?null:_tmp;
  }
  public void setInvListPG(PropertyGroup arg_InvListPG) {
    if (!(arg_InvListPG instanceof InvListPG))
      throw new IllegalArgumentException("setInvListPG requires a InvListPG argument.");
    myInvListPG = (InvListPG) arg_InvListPG;
  }

  // generic search methods
  public PropertyGroup getLocalPG(Class c, long t) {
    if (InvListPG.class.equals(c)) {
      return (myInvListPG==InvListPG.nullPG)?null:myInvListPG;
    }
    return super.getLocalPG(c,t);
  }

  public PropertyGroupSchedule getLocalPGSchedule(Class c) {
    return super.getLocalPGSchedule(c);
  }

  public void setLocalPG(Class c, PropertyGroup pg) {
    if (InvListPG.class.equals(c)) {
      myInvListPG=(InvListPG)pg;
    } else
      super.setLocalPG(c,pg);
  }

  public void setLocalPGSchedule(PropertyGroupSchedule pgSchedule) {
      super.setLocalPGSchedule(pgSchedule);
  }

  public PropertyGroup removeLocalPG(Class c) {
    PropertyGroup removed = null;
    if (InvListPG.class.equals(c)) {
      removed=myInvListPG;
      myInvListPG=null;
    } else
      removed=super.removeLocalPG(c);
    return removed;
  }

  public PropertyGroup removeLocalPG(PropertyGroup pg) {
    PropertyGroup removed = null;
    Class pgc = pg.getPrimaryClass();
    if (InvListPG.class.equals(pgc)) {
      removed=myInvListPG;
      myInvListPG=null;
    } else
      removed= super.removeLocalPG(pg);
    return removed;
  }

  public PropertyGroupSchedule removeLocalPGSchedule(Class c) {
    PropertyGroupSchedule removed = null;
    return removed;
  }

  public PropertyGroup generateDefaultPG(Class c) {
    if (InvListPG.class.equals(c)) {
      return (myInvListPG= new InvListPGImpl());
    } else
      return super.generateDefaultPG(c);
  }

  // dumb serialization methods

  private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
      if (myInvListPG instanceof Null_PG || myInvListPG instanceof Future_PG) {
        out.writeObject(null);
      } else {
        out.writeObject(myInvListPG);
      }
  }

  private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
    in.defaultReadObject();
      myInvListPG=(InvListPG)in.readObject();
  }
  // beaninfo support
  private static PropertyDescriptor properties[];
  static {
    try {
      properties = new PropertyDescriptor[1];
      properties[0] = new PropertyDescriptor("InvListPG", InventoryAsset.class, "getInvListPG", null);
    } catch (IntrospectionException ie) {}
  }

  public PropertyDescriptor[] getPropertyDescriptors() {
    PropertyDescriptor[] pds = super.getPropertyDescriptors();
    PropertyDescriptor[] ps = new PropertyDescriptor[pds.length+1];
    System.arraycopy(pds, 0, ps, 0, pds.length);
    System.arraycopy(properties, 0, ps, pds.length, 1);
    return ps;
  }
}
