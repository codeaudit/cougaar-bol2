/*
 * Created on Jul 7, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.cougaar.tutorial.booksonline.web.model;

import java.util.List;

/**
 * @author ttschampel
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShoppingCart {
	List items;
	
	
	/**
	 * @return
	 */
	public List getItems() {
		return items;
	}

	/**
	 * @param items
	 */
	public void setItems(List items) {
		this.items = items;
	}

	public float getTotal(){
		float total = 0.00f;
		if(items!=null){
			for(int i=0;i<items.size();i++){
				ShoppingCartItem item = (ShoppingCartItem)items.get(i);
				total = total + (item.getPrice() * item.getQuantity());
			}	
		}
		return total;
	}
}
