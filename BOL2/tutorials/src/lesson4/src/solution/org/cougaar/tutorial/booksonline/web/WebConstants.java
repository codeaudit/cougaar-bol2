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
 * Created on Jun 20, 2003
 *
 *
 */
package org.cougaar.tutorial.booksonline.web;


/**
 * Constants used for the web-tier. Many of these are left over from the old
 * BOL.  These will be cleaned up in the future.
 *
 * @author ttschampel
 */
public interface WebConstants {
  /** Item Token */
  public static final char ITEM_TOKEN = (char) 0xFF;
  /** Property token */
  public static final char PROPERTY_TOKEN = (char) 0x01;
  /** Book search mode */
  public static final int MODE_BOOK_SEARCH = 2;
  /** Get Catalog mode */
  public static final int MODE_GET_CATALOG = 3;
  /** Mode parameter */
  public static final String MODE = "mode";
  /** Catalog search mode */
  public static final String CATALOG_SEARCH_MODE = MODE + "="
    + MODE_BOOK_SEARCH;
  /** Get Catalog mode */
  public static final String GET_CATALOG_MODE = MODE + "=" + MODE_GET_CATALOG;
  /** Catalog Search Servlet path */
  public static final String CATALOG_SEARCH = "/CatalogServlet";
  /** Catalog Searh uri */
  public static final String CATALOG_SEARCH_URL = CATALOG_SEARCH + "?GET=BOL";
  /** Warehouse name */
  public static final String WAREHOUSE_REG_NAME = "Warehouse";
  /** Warehouse XML */
  public static final String XMLWAREHOUSE_REG_NAME = "WarehouseXML";
  /** OrderManager name */
  public static final String ORDERMANAGER_REG_NAME = "OrderManager";
  /** Admin status Servlet name */
  public static final String ADMIN_STATUS = "/ADMIN_STATUS";
  /** Order Chart Servlet name */
  public static final String BOLORDERCHART = "/BOLORDERCHART";
  /** Char Servlet name */
  public static final String CHART_DATA = "/BOLOrderChartKeepAliveServlet";
  /** Not Assinged mode */
  public static final int MODE_NOT_ASSIGNED = -1;
  /** Default page mode */
  public static final int MODE_DEFAULT_PAGE = 0;
  /** Blank page mode */
  public static final int MODE_BLANK_PAGE = 1;
  /** view cart mode */
  public static final int MODE_VIEW_CART = 4;
  /** view details mode */
  public static final int MODE_VIEW_DETAILS = 5;
  /** checkout mode */
  public static final int MODE_CHECK_OUT = 6;
  /** submit order mode */
  public static final int MODE_SUBMIT_ORDER = 7;
  /** write review mode */
  public static final int MODE_WRITE_REVIEW = 8;
  /** check order status mode */
  public static final int MODE_CHECK_ORDER_STATUS = 9;
  /** frameset page mode */
  public static final int MODE_FRAMESET_PAGE = 10;
  /** display status frame mode */
  public static final int MODE_DISPLAY_STATUS_FRAME = 11;
  /** display welcome window mode */
  public static final int MODE_DISPLAY_WELCOME_WINDOW = 12;
  /** display command window mode */
  public static final int MODE_DISPLAY_COMMAND_WINDOW = 13;
  /** orders in progress mode */
  public static final int MODE_ORDERS_IN_PROCESS = 14;
  /** completed orders mode */
  public static final int MODE_COMPLETED_ORDERS = 15;
  /** DOCUMENT ME! */
  public static final int MODE_SEARCH_ORDERS_FORM = 16;
  /** DOCUMENT ME! */
  public static final int MODE_SEARCH_ORDERS = 17;
  /** DOCUMENT ME! */
  public static final int MODE_VIEW_ORDER_DETAILS = 18;
  /** DOCUMENT ME! */
  public static final int MODE_APPLET_DATA_STREAM = 20;
  /** DOCUMENT ME! */
  public static final int MODE_ADD_TO_CART = 21;
  /** DOCUMENT ME! */
  public static final int MODE_UPDATE_CART = 22;
  /** DOCUMENT ME! */
  public static final String Servlet_URL_NAME = "servletURL";
  /** DOCUMENT ME! */
  public static final String MAIN_FORM_NAME = "mainForm";
  /** DOCUMENT ME! */
  public static final String SHOPPING_CART_NAME = "shoppingCart";
  /** DOCUMENT ME! */
  public static final String IN_CART_CHECK_NAME = "inCartCheck";
  /** DOCUMENT ME! */
  public static final String NOT_IN_CART_CHECK_NAME = "notInCartCheck";
  /** DOCUMENT ME! */
  public static final String BOOK_DETAIL_ID_NAME = "bookDetailID";
  /** DOCUMENT ME! */
  public static final String KEYWORD_NAME = "keyword";
  /** DOCUMENT ME! */
  public static final String SEARCH_TYPE_NAME = "searchType";
  /** DOCUMENT ME! */
  public static final String STATUS_TYPE_NAME = "status";
  /** DOCUMENT ME! */
  public static final String SHIP_DATE_NAME = "date";
  /** DOCUMENT ME! */
  public static final String BOOK_DETAIL_SEARCH_NAME = "detail";
  /** DOCUMENT ME! */
  public static final String BOOK_ISBN_NAME = "bookISBN";
  /** DOCUMENT ME! */
  public static final String BOOK_TITLE_NAME = "bookTitle";
  /** DOCUMENT ME! */
  public static final String BOOK_PRICE_NAME = "bookPrice";
  /** DOCUMENT ME! */
  public static final String BOOK_REMOVE = "bookRemove";
  /** DOCUMENT ME! */
  public static final String BOOK_QUANTITY = "bookQuantity";
  /** DOCUMENT ME! */
  public static final String CART_TOTAL_NAME = "cartTotal";
  /** DOCUMENT ME! */
  public static final String ORDER_ID_NAME = "orderID";
  /** DOCUMENT ME! */
  public static final String ORDER_STATUS_NAME = "orderStatus";
  /** DOCUMENT ME! */
  public static final String REVIEW_NAME = "review";
  /** DOCUMENT ME! */
  public static final String ITEM_ID_NAME = "itemID";
  /** DOCUMENT ME! */
  public static final String ITEM_QTY_NAME = "itemQty";
  /** DOCUMENT ME! */
  public static final String ITEM_TITLE_NAME = "itemTitle";
  /** DOCUMENT ME! */
  public static final String ITEM_PRICE_NAME = "itemPrice";
  /** DOCUMENT ME! */
  public static final String PAYMENT_TYPE_NAME = "paymentType";
  /** DOCUMENT ME! */
  public static final String PAYMENT_CARD_NUMBER = "cardNumber";
  /** DOCUMENT ME! */
  public static final String PAYMENT_CARD_EXPIRATION = "cardExpiration";
  /** DOCUMENT ME! */
  public static final String PAYMENT_NAME = "paymentName";
  /** DOCUMENT ME! */
  public static final String PAYMENT_ADDRESS_1 = "paymentAddress1";
  /** DOCUMENT ME! */
  public static final String PAYMENT_ADDRESS_2 = "paymentAddress2";
  /** DOCUMENT ME! */
  public static final String CITY = "city";
  /** DOCUMENT ME! */
  public static final String STATE = "state";
  /** DOCUMENT ME! */
  public static final String ZIP = "zip";
  /** DOCUMENT ME! */
  public static final String SHIPPING_METHOD = "shippingMethod";
  /** DOCUMENT ME! */
  public static final String PAYMENT_VISA = "VISA";
  /** DOCUMENT ME! */
  public static final String PAYMENT_MASTERCARD = "Mastercard";
  /** DOCUMENT ME! */
  public static final String PAYMENT_AMERICAN_EXPRESS = "American Express";
  /** DOCUMENT ME! */
  public static final String SHIPPING_GROUND = "Ground";
  /** DOCUMENT ME! */
  public static final String SHIPPING_NEXTDAY = "Next Day Air";
  /** DOCUMENT ME! */
  public static final String SHIPPING_2NDDAY = "2nd Day Air";
  /** DOCUMENT ME! */
  public static final String SEARCH_TITLE = "Title";
  /** DOCUMENT ME! */
  public static final String SEARCH_AUTHOR = "Author";
  /** DOCUMENT ME! */
  public static final String SEARCH_SUBJECT = "Subject";
}
