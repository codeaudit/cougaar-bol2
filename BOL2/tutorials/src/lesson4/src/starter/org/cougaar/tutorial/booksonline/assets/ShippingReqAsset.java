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
/** Simple representation of shipping assignment **/

public class ShippingReqAsset extends Asset {

  public ShippingReqAsset() {
    myShipperPG = null;
  }

  public ShippingReqAsset(ShippingReqAsset prototype) {
    super(prototype);
    myShipperPG=null;
  }

  /** For infrastructure only - use org.cougaar.core.domain.Factory.copyInstance instead. **/
  public Object clone() throws CloneNotSupportedException {
    ShippingReqAsset _thing = (ShippingReqAsset) super.clone();
    if (myShipperPG!=null) _thing.setShipperPG(myShipperPG.lock());
    return _thing;
  }

  /** create an instance of the right class for copy operations **/
  public Asset instanceForCopy() {
    return new ShippingReqAsset();
  }

  /** create an instance of this prototype **/
  public Asset createInstance() {
    return new ShippingReqAsset(this);
  }

  protected void fillAllPropertyGroups(Vector v) {
    super.fillAllPropertyGroups(v);
    { Object _tmp = getShipperPG();
    if (_tmp != null && !(_tmp instanceof Null_PG)) {
      v.addElement(_tmp);
    } }
  }

  private transient ShipperPG myShipperPG;

  public ShipperPG getShipperPG() {
    ShipperPG _tmp = (myShipperPG != null) ?
      myShipperPG : (ShipperPG)resolvePG(ShipperPG.class);
    return (_tmp == ShipperPG.nullPG)?null:_tmp;
  }
  public void setShipperPG(PropertyGroup arg_ShipperPG) {
    if (!(arg_ShipperPG instanceof ShipperPG))
      throw new IllegalArgumentException("setShipperPG requires a ShipperPG argument.");
    myShipperPG = (ShipperPG) arg_ShipperPG;
  }

  // generic search methods
  public PropertyGroup getLocalPG(Class c, long t) {
    if (ShipperPG.class.equals(c)) {
      return (myShipperPG==ShipperPG.nullPG)?null:myShipperPG;
    }
    return super.getLocalPG(c,t);
  }

  public PropertyGroupSchedule getLocalPGSchedule(Class c) {
    return super.getLocalPGSchedule(c);
  }

  public void setLocalPG(Class c, PropertyGroup pg) {
    if (ShipperPG.class.equals(c)) {
      myShipperPG=(ShipperPG)pg;
    } else
      super.setLocalPG(c,pg);
  }

  public void setLocalPGSchedule(PropertyGroupSchedule pgSchedule) {
      super.setLocalPGSchedule(pgSchedule);
  }

  public PropertyGroup removeLocalPG(Class c) {
    PropertyGroup removed = null;
    if (ShipperPG.class.equals(c)) {
      removed=myShipperPG;
      myShipperPG=null;
    } else
      removed=super.removeLocalPG(c);
    return removed;
  }

  public PropertyGroup removeLocalPG(PropertyGroup pg) {
    PropertyGroup removed = null;
    Class pgc = pg.getPrimaryClass();
    if (ShipperPG.class.equals(pgc)) {
      removed=myShipperPG;
      myShipperPG=null;
    } else
      removed= super.removeLocalPG(pg);
    return removed;
  }

  public PropertyGroupSchedule removeLocalPGSchedule(Class c) {
    PropertyGroupSchedule removed = null;
    return removed;
  }

  public PropertyGroup generateDefaultPG(Class c) {
    if (ShipperPG.class.equals(c)) {
      return (myShipperPG= new ShipperPGImpl());
    } else
      return super.generateDefaultPG(c);
  }

  // dumb serialization methods

  private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
      if (myShipperPG instanceof Null_PG || myShipperPG instanceof Future_PG) {
        out.writeObject(null);
      } else {
        out.writeObject(myShipperPG);
      }
  }

  private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
    in.defaultReadObject();
      myShipperPG=(ShipperPG)in.readObject();
  }
  // beaninfo support
  private static PropertyDescriptor properties[];
  static {
    try {
      properties = new PropertyDescriptor[1];
      properties[0] = new PropertyDescriptor("ShipperPG", ShippingReqAsset.class, "getShipperPG", null);
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
