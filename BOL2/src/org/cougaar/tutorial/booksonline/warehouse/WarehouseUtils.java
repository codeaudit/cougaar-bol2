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


package org.cougaar.tutorial.booksonline.warehouse;


import org.cougaar.planning.ldm.PlanningFactory;
import org.cougaar.tutorial.booksonline.assets.BookAsset;
import org.cougaar.tutorial.booksonline.assets.NewAuthorPG;
import org.cougaar.tutorial.booksonline.assets.NewOverviewPG;
import org.cougaar.tutorial.booksonline.assets.NewPricePG;
import org.cougaar.tutorial.booksonline.assets.NewPublisherPG;
import org.cougaar.tutorial.booksonline.assets.NewReviewPG;
import org.cougaar.tutorial.booksonline.assets.NewShipperPG;
import org.cougaar.tutorial.booksonline.assets.NewSpecificsPG;
import org.cougaar.tutorial.booksonline.assets.NewStockPG;
import org.cougaar.tutorial.booksonline.assets.ShippingReqAsset;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;
import org.cougaar.tutorial.booksonline.web.model.BookModel;


/**
 * <b>Description</b>: Static utility functions and constants specific to the
 * Warehouse.
 *
 * @author ttschampel
 * @version 1.0
 */
public class WarehouseUtils {
  // ---------------------------------------------------------------------------------------------------------------------
  // Definition Values
  // ---------------------------------------------------------------------------------------------------------------------
  /**
   * <b>Description</b>: The constant in the inventory file to indicate this
   * item is a CD. <br>
   * <br>
   * <b>Notes</b>:<br>
   * - Matches up with CDFLAGVAL
   */
  protected static String CDSTRINGVAL = "CD";
  /**
   * <b>Description</b>: The internal constant to indicate this item is a CD. <br>
   * <br>
   * <b>Notes</b>:<br>
   * - Matches up with CDSTRINGVAL
   */
  protected static final int CDFLAGVAL = 1;
  /**
   * <b>Description</b>: The constant in the inventory file to indicate this
   * item is a hard cover book. <br>
   * <br>
   * <b>Notes</b>:<br>
   * - Matches up with HARDCOVERFLAGVAL
   */
  protected static String HARDCOVERSTRINGVAL = "HBOOK";
  /**
   * <b>Description</b>: The internal constant to indicate this item is a hard
   * cover book. <br>
   * <br>
   * <b>Notes</b>:<br>
   * - Matches up with HARDCOVERSTRINGVAL
   */
  protected static final int HARDCOVERFLAGVAL = 2;
  /**
   * <b>Description</b>: The constant in the inventory file to indicate this
   * item is a soft cover book. <br>
   * <br>
   * <b>Notes</b>:<br>
   * - Matches up with SOFTCOVERFLAGVAL
   */
  protected static String SOFTCOVERSTRINGVAL = "SBOOK";
  /**
   * <b>Description</b>: The internal constant to indicate this item is a CD. <br>
   * <br>
   * <b>Notes</b>:<br>
   * - Matches up with SOFTCOVERSTRINGVAL
   */
  protected static final int SOFTCOVERFLAGVAL = 3;
  /** <b>Description</b>: The default file name of the inventory data file. */
  protected static final String INVENTORY_FILE = "bolinventory.txt";

  // ---------------------------------------------------------------------------------------------------------------------
  // Public Member Methods
  // ---------------------------------------------------------------------------------------------------------------------
  //
  // Auxiliary Functions to fill in properties
  //
  /**
   * <b>Description</b>: Create and load the OverviewPG property group and put
   * it into the bookAsset. <br>
   *
   * @param bookAsset Book to load property group into
   * @param title Book title
   * @param toc Table of contents
   * @param rank Book sales rank
   * @param pubNotes Publisher notes of book
   * @param subjWords Search key words
   * @param theLDMF LDM factory with which to create property group
   */
  protected static void fillOverviewPG(BookAsset bookAsset, String title,
    String toc, int rank, String pubNotes, String subjWords,
    PlanningFactory theLDMF) {
    NewOverviewPG overv_pg = (NewOverviewPG) theLDMF.createPropertyGroup(
        "OverviewPG");

    overv_pg.setTitle(title);
    if (title != null) {
      overv_pg.setSearchableTitle(title.toLowerCase());
    }

    overv_pg.setTableOfContents(toc);
    overv_pg.setSalesRank(rank);
    overv_pg.setPublisherNotes(pubNotes);
    if (subjWords != null) {
      overv_pg.setSubjectKeywords(subjWords.toLowerCase());
    }

    bookAsset.setOverviewPG(overv_pg);
  }


  // Fill in AuthorPG on BookAsset
  /**
   * <b>Description</b>: Create and load the AuthorPG property group and put it
   * into the bookAsset. <br>
   *
   * @param bookAsset Book to load property group into
   * @param displayName Author name with capitalization
   * @param affil Author affiliations
   * @param interv Text of author interview
   * @param theLDMF LDM factory with which to create property group
   */
  protected static void fillAuthorPG(BookAsset bookAsset, String displayName,
    String affil, String interv, PlanningFactory theLDMF) {
    NewAuthorPG auth_pg = (NewAuthorPG) theLDMF.createPropertyGroup("AuthorPG");

    auth_pg.setDisplayName(displayName);
    if (displayName != null) {
      auth_pg.setSearchableName(displayName.toLowerCase());
    }

    auth_pg.setAffiliation(affil);
    auth_pg.setInterview(interv);

    bookAsset.setAuthorPG(auth_pg);

  }


  /**
   * <b>Description</b>: Create and load the SpecifcsPG property group and put
   * it into the bookAsset. <br>
   *
   * @param bookAsset Book to load property group into
   * @param shipMess shipping delay message for this item
   * @param photoIdx index of item (book, CD, whatever) photo in some (as yet
   *        undefined) list
   * @param mediaType Constant indicating format of item (CD, book)
   * @param lengthCount Number of pages, CD tracks in item
   * @param relDate Release date of item in YYYYMMDD format
   * @param isbnOrAsin Standard industry assigned number of item
   * @param dimenText String specifying dimensions of item
   * @param theLDMF LDM factory with which to create property group
   */
  protected static void fillSpecificsPG(BookAsset bookAsset, String shipMess,
    int photoIdx, int mediaType, int lengthCount, long relDate,
    String isbnOrAsin, String dimenText, PlanningFactory theLDMF) {
    NewSpecificsPG spefs_pg = (NewSpecificsPG) theLDMF.createPropertyGroup(
        "SpecificsPG");

    spefs_pg.setShipMessage(shipMess);
    spefs_pg.setPhotoIndex(photoIdx);
    spefs_pg.setMediaType(mediaType);
    spefs_pg.setPageTrackCount(lengthCount);
    spefs_pg.setReleaseDate(relDate);
    spefs_pg.setISBNASIN(isbnOrAsin);
    spefs_pg.setDimension(dimenText);

    bookAsset.setSpecificsPG(spefs_pg);

  }


  /**
   * <b>Description</b>: Create and load the PricePG property group and put it
   * into the bookAsset. <br>
   *
   * @param asset Book to load property group into
   * @param lPrice List price
   * @param ourPrice Discounted price
   * @param endDate last day ourPrice is offered in YYYYMMDD format
   * @param theLDMF LDM factory with which to create property group
   */
  protected static void fillPricePG(BookAsset asset, float lPrice,
    float ourPrice, long endDate, PlanningFactory theLDMF) {
    // Note : You can create a property by name or by class
    //(if PropertyFactory is registered)
    NewPricePG price_pg = (NewPricePG) theLDMF.createPropertyGroup(org.cougaar.tutorial.booksonline.assets.PricePG.class);

    price_pg.setListPrice(lPrice);
    price_pg.setOurPrice(ourPrice);
    price_pg.setEndDateOfDiscount(endDate);

    asset.setPricePG(price_pg);
  }


  /**
   * <b>Description</b>: Create and load the PublisherPG property group and put
   * it into the bookAsset. <br>
   *
   * @param asset Book to load property group into
   * @param pubName Publisher Name
   * @param address Isn't this obvious?
   * @param city Isn't this obvious?
   * @param state Isn't this obvious?
   * @param theLDMF LDM factory with which to create property group
   */
  protected static void fillPublisherPG(BookAsset asset, String pubName,
    String address, String city, String state, PlanningFactory theLDMF) {
    // Note : You can create a property by name or by class
    //(if PropertyFactory is registered)
    NewPublisherPG publ_pg = (NewPublisherPG) theLDMF.createPropertyGroup(org.cougaar.tutorial.booksonline.assets.PublisherPG.class);

    publ_pg.setName(new String(pubName));
    publ_pg.setAddress(new String(address));
    publ_pg.setCity(new String(city));
    publ_pg.setState(new String(state));

    asset.setPublisherPG(publ_pg);
  }


  /**
   * <b>Description</b>: Load a StockPG property group. <br>
   *
   * @param stock_pg StockPG property group to load
   * @param stockCount Number in stock
   * @param onOrderCount Number of items ordered but not arrived
   * @param suppName Name of supplier for ordering more
   * @param theLDMF LDM factory with which to create property group
   */
  protected static void fillStockPG(NewStockPG stock_pg, int stockCount,
    int onOrderCount, String suppName) {
    //    NewStockPG stock_pg = (NewStockPG)theLDMF.createPropertyGroup("StockPG");  for reference
    stock_pg.setShelfCount(stockCount);
    stock_pg.setOnOrderCount(onOrderCount);
    stock_pg.setSupplierName(suppName);

  }


  /**
   * <b>Description</b>: Create and load a ShipperPG property group and put it
   * into the shipAsset. <br>
   *
   * @param shipAsset ShippingReqAsset to load into
   * @param shipperName Name of shipper
   * @param shipperMethod User specified shipping option (overnight, 3 day,
   *        etc.)
   * @param shippingAddress To where the item goes
   * @param theLDMF LDM factory with which to create property group
   */
  protected static void fillShipperPG(ShippingReqAsset shipAsset,
    String shipperName, String shipperMethod, String shippingAddress,
    PlanningFactory theLDMF) {
    NewShipperPG ship_pg = (NewShipperPG) theLDMF.createPropertyGroup(
        "ShipperPG");

    ship_pg.setShipperName(shipperName);
    ship_pg.setDestinationAddress(shippingAddress);
    ship_pg.setMethod(shipperMethod);
    shipAsset.setShipperPG(ship_pg);

  }


  /**
   * <b>Description</b>: Create and load the ReviewPG property group and put it
   * into the bookAsset. <br>
   *
   * @param bookAsset Book to load property group into
   * @param reviewText Text of reviews of this item
   * @param avgRating Median rating of this item
   * @param numReviews Total number of reviews of this item
   * @param theLDMF LDM factory with which to create property group
   */
  protected static void fillReviewPG(BookAsset bookAsset, String reviewText,
    float avgRating, int numReviews, PlanningFactory theLDMF) {
    NewReviewPG revw_pg = (NewReviewPG) theLDMF.createPropertyGroup("ReviewPG");

    revw_pg.setReview(reviewText);
    revw_pg.setAvgRating(avgRating);
    revw_pg.setNumberOfReviews(numReviews);

    bookAsset.setReviewPG(revw_pg);

  }


  /**
   * Create Book Asset from a BookModel
   *
   * @param book BookModel
   * @param theLDMF PlanningFactory
   *
   * @return BookAsset
   */
  public static BookAsset createBookAssetFromBookModel(BookModel book,
    PlanningFactory theLDMF) {
    // Create an asset based on an empty book prototype
    BookAsset bookAsset = (BookAsset) theLDMF.createInstance(BolSocietyUtils.OBJECT_PROTOTYPE);

    // overview
    fillOverviewPG(bookAsset, book.getTitle(), book.getTOC(), book.getRank(),
      book.getPublisherNotes(), book.getSubject(), theLDMF);

    // author
    fillAuthorPG(bookAsset, book.getAuthor(), book.getAffil(),
      book.getInterview(), theLDMF);


    // specifics
    fillSpecificsPG(bookAsset, book.getShippingMessage(), book.getPhotoId(),
      book.getMediaType(), Integer.parseInt(book.getLength()),
      book.getReleaseDate(), book.getIsbn(), book.getDimension(), theLDMF);

    //price
    fillPricePG(bookAsset, book.getListPrice(), book.getListPrice(),
      book.getEndPrice(), theLDMF);

    // review
    fillReviewPG(bookAsset, book.getReviewText(), book.getAvgRating(),
      book.getNumReviews(), theLDMF);

    //publisher
    fillPublisherPG(bookAsset, book.getPublisher(), book.getPublisherAddress(),
      book.getPublisherCity(), book.getPublisherState(), theLDMF);


    return bookAsset;
  }
}
