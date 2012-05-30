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

/* @generated Tue May 29 19:40:17 EDT 2012 from bol_assets.def - DO NOT HAND EDIT */
package org.cougaar.tutorial.booksonline.assets;
import org.cougaar.planning.ldm.asset.*;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Vector;
import java.beans.PropertyDescriptor;
import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
/** Simple Representation of a warehouse **/

public class WarehouseAsset extends Asset {

  public WarehouseAsset() {
    myWareHouseDescriptionPG = null;
  }

  public WarehouseAsset(WarehouseAsset prototype) {
    super(prototype);
    myWareHouseDescriptionPG=null;
  }

  /** For infrastructure only - use org.cougaar.core.domain.Factory.copyInstance instead. **/
  public Object clone() throws CloneNotSupportedException {
    WarehouseAsset _thing = (WarehouseAsset) super.clone();
    if (myWareHouseDescriptionPG!=null) _thing.setWareHouseDescriptionPG(myWareHouseDescriptionPG.lock());
    return _thing;
  }

  /** create an instance of the right class for copy operations **/
  public Asset instanceForCopy() {
    return new WarehouseAsset();
  }

  /** create an instance of this prototype **/
  public Asset createInstance() {
    return new WarehouseAsset(this);
  }

  protected void fillAllPropertyGroups(Vector v) {
    super.fillAllPropertyGroups(v);
    { Object _tmp = getWareHouseDescriptionPG();
    if (_tmp != null && !(_tmp instanceof Null_PG)) {
      v.addElement(_tmp);
    } }
  }

  private transient WareHouseDescriptionPG myWareHouseDescriptionPG;

  public WareHouseDescriptionPG getWareHouseDescriptionPG() {
    WareHouseDescriptionPG _tmp = (myWareHouseDescriptionPG != null) ?
      myWareHouseDescriptionPG : (WareHouseDescriptionPG)resolvePG(WareHouseDescriptionPG.class);
    return (_tmp == WareHouseDescriptionPG.nullPG)?null:_tmp;
  }
  public void setWareHouseDescriptionPG(PropertyGroup arg_WareHouseDescriptionPG) {
    if (!(arg_WareHouseDescriptionPG instanceof WareHouseDescriptionPG))
      throw new IllegalArgumentException("setWareHouseDescriptionPG requires a WareHouseDescriptionPG argument.");
    myWareHouseDescriptionPG = (WareHouseDescriptionPG) arg_WareHouseDescriptionPG;
  }

  // generic search methods
  public PropertyGroup getLocalPG(Class c, long t) {
    if (WareHouseDescriptionPG.class.equals(c)) {
      return (myWareHouseDescriptionPG==WareHouseDescriptionPG.nullPG)?null:myWareHouseDescriptionPG;
    }
    return super.getLocalPG(c,t);
  }

  public PropertyGroupSchedule getLocalPGSchedule(Class c) {
    return super.getLocalPGSchedule(c);
  }

  public void setLocalPG(Class c, PropertyGroup pg) {
    if (WareHouseDescriptionPG.class.equals(c)) {
      myWareHouseDescriptionPG=(WareHouseDescriptionPG)pg;
    } else
      super.setLocalPG(c,pg);
  }

  public void setLocalPGSchedule(PropertyGroupSchedule pgSchedule) {
      super.setLocalPGSchedule(pgSchedule);
  }

  public PropertyGroup removeLocalPG(Class c) {
    PropertyGroup removed = null;
    if (WareHouseDescriptionPG.class.equals(c)) {
      removed=myWareHouseDescriptionPG;
      myWareHouseDescriptionPG=null;
    } else {
      removed=super.removeLocalPG(c);
    }
    return removed;
  }

  public PropertyGroup removeLocalPG(PropertyGroup pg) {
    Class pgc = pg.getPrimaryClass();
    if (WareHouseDescriptionPG.class.equals(pgc)) {
      PropertyGroup removed=myWareHouseDescriptionPG;
      myWareHouseDescriptionPG=null;
      return removed;
    } else {}
    return super.removeLocalPG(pg);
  }

  public PropertyGroupSchedule removeLocalPGSchedule(Class c) {
   {
      return super.removeLocalPGSchedule(c);
    }
  }

  public PropertyGroup generateDefaultPG(Class c) {
    if (WareHouseDescriptionPG.class.equals(c)) {
      return (myWareHouseDescriptionPG= new WareHouseDescriptionPGImpl());
    } else
      return super.generateDefaultPG(c);
  }

  // dumb serialization methods

  private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
      if (myWareHouseDescriptionPG instanceof Null_PG || myWareHouseDescriptionPG instanceof Future_PG) {
        out.writeObject(null);
      } else {
        out.writeObject(myWareHouseDescriptionPG);
      }
  }

  private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
    in.defaultReadObject();
      myWareHouseDescriptionPG=(WareHouseDescriptionPG)in.readObject();
  }
  // beaninfo support
  private static PropertyDescriptor properties[];
  static {
    try {
      properties = new PropertyDescriptor[1];
      properties[0] = new PropertyDescriptor("WareHouseDescriptionPG", WarehouseAsset.class, "getWareHouseDescriptionPG", null);
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
