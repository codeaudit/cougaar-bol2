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


/*
 * Created on Jul 3, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.cougaar.tutorial.booksonline.util;


import org.cougaar.tutorial.booksonline.web.model.BookModel;

import java.math.BigDecimal;


/**
 * Utility methods for Books
 *
 * @author ttschampel
 */
public class BookUtil {
  /**
   * Get Book Model from a Database query result
   *
   * @param objects Array of results from the database stored as Objects
   *
   * @return book model
   */
  public static BookModel getBookModelFromDatabase(Object[] objects) {
    BookModel bookModel = new BookModel();

    String title = (String) objects[0];
    String toc = (String) objects[1];
    String pNotes = (String) objects[3];
    String author = (String) objects[5];
     String lnth = (String) objects[11];
    String isbn = (String) objects[13];
    float list = Float.parseFloat((String) objects[15]);
    float ourPrice = ((BigDecimal) objects[16]).floatValue();
    String endPrice = (String) objects[17];
    int numReviews = ((Integer) objects[19]).intValue();
    float avgRating = ((BigDecimal) objects[20]).floatValue();
    String pubName = (String) objects[21];
    String pubAddress = (String) objects[22];
    String pubcity = (String) objects[23];
    String state = (String) objects[24];
    int shelf = ((Integer) objects[25]).intValue();
    int onOrder = ((Integer) objects[26]).intValue();
    bookModel.setPublisherAddress(pubAddress);
    bookModel.setPublisherCity(pubcity);
    bookModel.setPublisherState(state);
    bookModel.setAvgRating(avgRating);
    bookModel.setNumReviews(numReviews);
    bookModel.setTitle(title);
    bookModel.setAuthor(author);
    bookModel.setIsbn(isbn);
    bookModel.setEndPrice(Long.parseLong(endPrice));
    bookModel.setListPrice(list);
    bookModel.setOurPrice(ourPrice);
    bookModel.setInStock(true);
    bookModel.setShelf(shelf);
    bookModel.setOnOrder(onOrder);
    bookModel.setPublisher(pubName);
    bookModel.setLength(lnth);
    bookModel.setPublisherNotes(pNotes);
    bookModel.setTOC(toc);
    return bookModel;
  }
}
