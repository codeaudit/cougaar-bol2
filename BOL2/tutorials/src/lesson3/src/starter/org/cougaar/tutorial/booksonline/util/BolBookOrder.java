/*
 * <copyright>
 *  Copyright 1997-2003 Cougaar Software, Inc.
 *  under sponsorship of the Defense Advanced Research Projects
 *  Agency (DARPA).
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the Cougaar Open Source License as published by
 *  DARPA on the Cougaar Open Source Website (www.cougaar.org).
 *
 *  THE COUGAAR SOFTWARE AND ANY DERIVATIVE SUPPLIED BY LICENSOR IS
 *  PROVIDED "AS IS" WITHOUT WARRANTIES OF ANY KIND, WHETHER EXPRESS OR
 *  IMPLIED, INCLUDING (BUT NOT LIMITED TO) ALL IMPLIED WARRANTIES OF
 *  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE, AND WITHOUT
 *  ANY WARRANTIES AS TO NON-INFRINGEMENT.  IN NO EVENT SHALL COPYRIGHT
 *  HOLDER BE LIABLE FOR ANY DIRECT, SPECIAL, INDIRECT OR CONSEQUENTIAL
 *  DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE OF DATA OR PROFITS,
 *  TORTIOUS CONDUCT, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 *  PERFORMANCE OF THE COUGAAR SOFTWARE.
 *
 * </copyright>
 *
 * CHANGE RECORD
 * -
 */


package org.cougaar.tutorial.booksonline.util;


import java.io.Serializable;


/**
 * Book Order Value Object
 *
 * @author ttschampel
 * @version $Revision: 1.1 $
 */
public class BolBookOrder implements Serializable {
  /** ISBN */
  public String isbn;
  /** Title */
  public String title;
  /** Number of books! */
  public int count;
  /** Price */
  public float price;

  /**
   * Creates a new BolBookOrder object.
   */
  public BolBookOrder() {
  }


  /**
   * Constructor
   *
   * @param bookIsbn isbn
   * @param orderCount number books in order
   * @param totalPrice price of order
   *
   * @deprecated Use BolBookOrder(String bookIsbn, String bookTitle, int
   *             orderCount, float totalPrice).
   */
  public BolBookOrder(String bookIsbn, int orderCount, float totalPrice) {
    // Make the book title an empty string since it was not given
    this(bookIsbn, "", orderCount, totalPrice);
  }


  /**
   * Creates a new BolBookOrder object.
   *
   * @param bookIsbn isbn
   * @param bookTitle book title
   * @param orderCount books in order
   * @param totalPrice price of order
   */
  public BolBookOrder(String bookIsbn, String bookTitle, int orderCount,
    float totalPrice) {
    isbn = bookIsbn;
    count = orderCount;
    price = totalPrice;
    title = bookTitle;
  }

  /**
   * Implemented toString()
   *
   * @return Isbn + Count
   */
  public String toString() {
    return new String(isbn + ":" + count);
  }
}
