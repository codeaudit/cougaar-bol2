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
 * Created on Jun 17, 2003
 *
 *
 */
package org.cougaar.tutorial.booksonline.web.orders;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cougaar.planning.ldm.PlanningFactory;
import org.cougaar.planning.ldm.plan.Task;
import org.cougaar.tutorial.booksonline.util.BolBookOrder;
import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;
import org.cougaar.tutorial.booksonline.util.UserDetails;
import org.cougaar.tutorial.booksonline.web.BOLServlet;
import org.cougaar.tutorial.booksonline.web.WebConstants;
import org.cougaar.tutorial.booksonline.web.model.BookModel;
import org.cougaar.tutorial.booksonline.web.model.ShoppingCart;
import org.cougaar.tutorial.booksonline.web.model.ShoppingCartItem;


/**
 * Order Manager Servlet, serves as the main web entry point.
 *
 * @author ttschampel
 */
public class OrderManagerServlet extends BOLServlet implements WebConstants {
    HttpServlet _index = new index();
    HttpServlet _searchresults = new searchresults();
    HttpServlet _cart = new cart();
    HttpServlet _bookdetails = new bookdetails();
    HttpServlet _checkout = new checkout();
    HttpServlet _ordercomplete = new ordercomplete();

    /**
     * Get Servlet Path
     *
     * @return Path to this servlet
     */
    protected String getPath() {
        return "/bol";
    }


    /**
     * Service request implementation.  Do appropriate action based on the mode
     * request parameter
     *
     * @param request Servlet Request
     * @param response Servlet Response
     *
     * @throws ServletException Servlet Exception
     * @throws IOException IO Exception
     */
    protected void serviceRequest(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        String modeStr = request.getParameter("mode");
        if (logging.isDebugEnabled()) {
            logging.debug("Mode:" + modeStr);
        }

        if (modeStr == null) {
            //send to default page
            _index.service(request, response);
        } else {
            int mode = Integer.parseInt(modeStr);
            switch (mode) {
                case WebConstants.MODE_BOOK_SEARCH:
                    searchBooks(request, response);
                    break;
                case WebConstants.MODE_VIEW_CART:
                    viewCart(request, response);
                    break;
                case WebConstants.MODE_VIEW_DETAILS:
                    viewBookDetails(request, response);
                    break;
                case WebConstants.MODE_ADD_TO_CART:
                    addToCart(request, response);
                    break;
                case WebConstants.MODE_CHECK_OUT:
                    checkout(request, response);
                    break;
                case WebConstants.MODE_SUBMIT_ORDER:
                    submitOrder(request, response);
                    break;
                case WebConstants.MODE_UPDATE_CART:
                    updateCart(request, response);
                    break;
            }
        }
    }


    /**
     * Initialize Servlet, initialize jsps
     *
     * @param config Servlet Config
     *
     * @throws ServletException Servlet Exception
     */
    protected void initServlet(ServletConfig config)
        throws ServletException {
        _index.init(config);
        _searchresults.init(config);
        _cart.init(config);
        _bookdetails.init(config);
        _checkout.init(config);
        _ordercomplete.init(config);
    }


    private void checkout(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        if (logging.isDebugEnabled()) {
            logging.debug("Checkout");
        }

        request.setAttribute("cart", request.getSession().getAttribute("cart"));
        _checkout.service(request, response);
    }


    private void submitOrder(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        if (logging.isDebugEnabled()) {
            logging.debug("submit order");
        }

        this.blackboardService.openTransaction();
        Vector bookOrderVec = new Vector();
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
        ArrayList items = (ArrayList) cart.getItems();
        for (int i = 0; i < items.size(); i++) {
            ShoppingCartItem item = (ShoppingCartItem) items.get(i);
            BolBookOrder bookOrder = new BolBookOrder(item.getIsbn(),
                    item.getTitle(), item.getQuantity(),
                    item.getQuantity() * item.getPrice());
            bookOrderVec.add(bookOrder);
        }

        String shippingMethod = request.getParameter(WebConstants.SHIPPING_METHOD);

        UserDetails userDetails = new UserDetails();
        userDetails.address1 = request.getParameter(WebConstants.PAYMENT_ADDRESS_1);
        userDetails.address2 = request.getParameter(WebConstants.PAYMENT_ADDRESS_2);
        userDetails.ccExpDate = request.getParameter(WebConstants.PAYMENT_CARD_EXPIRATION);
        userDetails.ccNumber = request.getParameter(WebConstants.PAYMENT_CARD_NUMBER);
        userDetails.ccType = request.getParameter(WebConstants.PAYMENT_TYPE_NAME);
        userDetails.city = request.getParameter(WebConstants.CITY);
        userDetails.state = request.getParameter(WebConstants.STATE);
        userDetails.zip = request.getParameter(WebConstants.ZIP);
        Task task = BolSocietyUtils.createOrderTask((PlanningFactory) this.domainService
                .getFactory("planning"), bookOrderVec, userDetails.ccNumber,
                userDetails, shippingMethod, BolSocietyUtils.ORDER_VERB);
        this.blackboardService.publishAdd(task);
        this.blackboardService.closeTransaction();
        cart = null;
        request.getSession().setAttribute("cart", cart);
        request.setAttribute("UID", task.getUID());
        _ordercomplete.service(request, response);
    }


    private void updateCart(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        if (logging.isDebugEnabled()) {
            logging.debug("Update shopping cart");
        }

        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
        if (cart != null) {
            ArrayList items = (ArrayList) cart.getItems();
            ArrayList newItems = new ArrayList();
            String[] isbns = request.getParameterValues(WebConstants.BOOK_ISBN_NAME);
            for (int i = 0; i < isbns.length; i++) {
                String isbn = isbns[i];
                ShoppingCartItem item = null;
                for (int x = 0; x < items.size(); x++) {
                    ShoppingCartItem _item = (ShoppingCartItem) items.get(x);
                    if (_item.getIsbn().equals(isbn)) {
                        item = _item;
                        break;
                    }
                }

                int quantity = Integer.parseInt(request.getParameter(isbn + ":"
                            + WebConstants.BOOK_QUANTITY));
                String delete = request.getParameter(isbn + ":"
                        + WebConstants.BOOK_REMOVE);
                if (((delete != null) && delete.equals("on"))
                    || (quantity == 0)) {
                    if (logging.isDebugEnabled()) {
                        logging.debug("removeing " + isbn + " from cart");
                    }
                } else {
                    item.setQuantity(quantity);
                    newItems.add(item);
                }
            }

            cart.setItems(newItems);
            request.getSession().setAttribute("cart", cart);
        }

        request.setAttribute("cart", cart);
        _cart.service(request, response);


    }


    private void addToCart(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        ShoppingCart cart = null;
        if (request.getSession().getAttribute("cart") != null) {
            cart = (ShoppingCart) request.getSession().getAttribute("cart");


        }

        if (cart == null) {
            cart = new ShoppingCart();
        }

        String[] isbns = request.getParameterValues(WebConstants.BOOK_ISBN_NAME);
        for (int i = 0; i < isbns.length; i++) {
            String isbn = isbns[i];
            String urlWarehouse = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + "/$" + WAREHOUSE_REG_NAME + CATALOG_SEARCH + "?" + MODE + "="
                + MODE_VIEW_DETAILS + "&" + BOOK_ISBN_NAME + "="
                + URLEncoder.encode(isbn, "UTF-8");

            URL url = new URL(urlWarehouse);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                        url.openStream()));
            String readLine = reader.readLine();
            BookModel book = null;
            while (readLine != null) {
                book = getBookFromOutput(readLine);
                ArrayList items = (ArrayList) cart.getItems();
                if (items == null) {
                    items = new ArrayList();
                }

                boolean alreadyInCart = false;
                for (int y = 0; y < items.size(); y++) {
                    ShoppingCartItem _item = (ShoppingCartItem) items.get(y);
                    if (_item.getIsbn().equals(book.getIsbn())) {
                        alreadyInCart = true;
                        break;
                    }
                }

                if (alreadyInCart) {
                    continue;
                }

                ShoppingCartItem item = new ShoppingCartItem();
                item.setIsbn(book.getIsbn());
                item.setQuantity(1);
                item.setTitle(book.getTitle());
                item.setPrice(book.getOurPrice());
                items.add(item);
                cart.setItems(items);
                readLine = reader.readLine();

            }

            reader.close();
        }

        request.getSession().setAttribute("cart", cart);
        request.setAttribute("cart", cart);
        _cart.service(request, response);
    }


    private void viewBookDetails(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        if (logging.isDebugEnabled()) {
            logging.debug("View book details");
        }

        String isbn = request.getParameter(BOOK_ISBN_NAME);
        if (logging.isDebugEnabled()) {
            logging.debug("Getting details for " + isbn);
        }

        String urlWarehouse = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort() + "/$"
            + WAREHOUSE_REG_NAME + CATALOG_SEARCH + "?" + MODE + "="
            + MODE_VIEW_DETAILS + "&" + BOOK_ISBN_NAME + "="
            + URLEncoder.encode(isbn, "UTF-8");

        if (logging.isDebugEnabled()) {
            logging.debug("Trying bookdetails with url:" + urlWarehouse);
        }

        URL url = new URL(urlWarehouse);

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                    url.openStream()));
        String readLine = reader.readLine();
        BookModel book = null;
        while (readLine != null) {
            book = getBookFromOutput(readLine);
            readLine = reader.readLine();

        }

        reader.close();
        request.setAttribute("book", book);

        _bookdetails.service(request, response);
    }


    private void viewCart(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        if (logging.isDebugEnabled()) {
            logging.debug("View Cart");

        }

        ShoppingCart cart = null;
        if (request.getSession() != null) {
            cart = (ShoppingCart) request.getSession().getAttribute("cart");

        }

        request.setAttribute("cart", cart);
        _cart.service(request, response);
    }


    /**
     * Perform book search
     *
     * @param request
     * @param response
     *
     * @throws ServletException Servlet Exception
     * @throws IOException IOException
     */
    private void searchBooks(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter(KEYWORD_NAME);
        String searchType = request.getParameter(SEARCH_TYPE_NAME);
        if (logging.isDebugEnabled()) {
            logging.debug("Searching for :" + keyword + " using search type:"
                + searchType);
        }

        String urlWarehouse = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort() + "/$"
            + WAREHOUSE_REG_NAME + CATALOG_SEARCH + "?" + MODE + "="
            + MODE_BOOK_SEARCH + "&" + SEARCH_TYPE_NAME + "="
            + URLEncoder.encode(searchType, "UTF-8") + "&" + KEYWORD_NAME + "="
            + URLEncoder.encode(keyword, "UTF-8");

        if (logging.isDebugEnabled()) {
            logging.debug("Trying search with url:" + urlWarehouse);
        }

        URL url = new URL(urlWarehouse);

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                    url.openStream()));
        String readLine = reader.readLine();
        ArrayList books = new ArrayList();
        while (readLine != null) {
            BookModel book = getBookFromOutput(readLine);
            books.add(book);
            readLine = reader.readLine();

        }

        reader.close();
        request.setAttribute("books", books);
        _searchresults.service(request, response);

    }


    private BookModel getBookFromOutput(String readLine) {
        BookModel book = new BookModel();
        StringTokenizer tokenizer = new StringTokenizer(readLine, ";");
        int index = 0;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            switch (index) {
                case 0:
                    book.setIsbn(token);
                    break;
                case 1:
                    book.setTitle(token);
                    break;
                case 2:
                    book.setAuthor(token);
                    break;
                case 3:
                    //end price
                    break;
                case 4:
                    //list price
                    if (token.length() > 0) {
                        book.setListPrice(Float.parseFloat(token));
                    }

                    break;
                case 5:
                    //our price
                    if (token.length() > 0) {
                        book.setOurPrice(Float.parseFloat(token));
                    }

                    break;
                case 6:
                    //in stock
                    book.setInStock(Boolean.valueOf(token).booleanValue());
                    break;
                case 7:
                    book.setPublisher(token);
                    break;
                case 8:
                    book.setPublisherNotes(token);
                    break;
                case 9:
                    book.setLength(token);
                    break;
                case 10:
                    book.setTOC(token);
                    break;
            }

            index++;
        }

        return book;
    }
}
