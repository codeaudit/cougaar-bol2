/*
 * Created on Jul 7, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.cougaar.tutorial.booksonline.web.model;

/**
 * @author ttschampel
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShoppingCartItem {
		private String isbn;
		private int quantity;
		private float price;
		private String title;
		private boolean available;
		
		/**
		 * @return
		 */
		public boolean isAvailable() {
			return available;
		}

		/**
		 * @param available
		 */
		public void setAvailable(boolean available) {
			this.available = available;
		}

		/**
		 * @return
		 */
		public String getIsbn() {
			return isbn;
		}

		/**
		 * @param isbn
		 */
		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		/**
		 * @return
		 */
		public float getPrice() {
			return price;
		}

		/**
		 * @param price
		 */
		public void setPrice(float price) {
			this.price = price;
		}

		/**
		 * @return
		 */
		public int getQuantity() {
			return quantity;
		}

		/**
		 * @param quantity
		 */
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		/**
		 * @return
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * @param title
		 */
		public void setTitle(String title) {
			this.title = title;
		}

}
