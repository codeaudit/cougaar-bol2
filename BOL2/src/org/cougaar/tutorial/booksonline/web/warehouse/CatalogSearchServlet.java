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
package org.cougaar.tutorial.booksonline.web.warehouse;


import com.cougaarsoftware.common.service.DatabaseService;
import com.cougaarsoftware.common.service.DatabaseServiceProvider;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cougaar.tutorial.booksonline.util.BolSocietyUtils;
import org.cougaar.tutorial.booksonline.util.BookUtil;
import org.cougaar.tutorial.booksonline.web.BOLServlet;
import org.cougaar.tutorial.booksonline.web.WebConstants;
import org.cougaar.tutorial.booksonline.web.model.BookModel;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Servlet that performs searches against the warehouse's database
 *
 * @author ttschampel
 */
public class CatalogSearchServlet extends BOLServlet implements WebConstants {
  DatabaseService dbService = null;

  /**
   * Get Path to this servlet
   *
   * @return Get Servlet Path
   */
  protected String getPath() {
    return CATALOG_SEARCH;
  }


  /**
   * Load Component
   */
  public void load() {
    super.load();

    dbService = getService(this, DatabaseService.class, null);

  }


  /**
   * Service HTTP Request
   *
   * @param request Servlet REquest
   * @param response Servlet Response
   *
   * @throws ServletException Exception
   * @throws IOException Exception
   */
  protected void serviceRequest(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }


  /**
   *Initialize this servlet (blank impl)
   */
  protected void initServlet(ServletConfig config)
    throws ServletException {
  }


  /**
   * Process Servlet Request
   *
   * @param request Servlet Request
   * @param response Servlet Response
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    try {
      String modeStr = request.getParameter(MODE);
      int mode = Integer.parseInt(modeStr);


      switch (mode) {
        case MODE_BOOK_SEARCH:
          bookSearch(request, response);
          break;
        case MODE_GET_CATALOG:
          getCatalog(response);
          break;
        case MODE_VIEW_DETAILS:
          getBook(request, response);
          break;
        default:
          if (logging.isDebugEnabled()) {
            logging.debug("Wrong mode for book search");
          }
      }
    } catch (Exception e) {
      if (logging.isErrorEnabled()) {
        logging.error("Error searching for books", e);
      }
    }
  }


  private void getBook(HttpServletRequest request, HttpServletResponse response) {
    String isbn = request.getParameter(BOOK_ISBN_NAME);
    try {
      PrintWriter out = response.getWriter();
      Map parameters = new HashMap();
      parameters.put(BolSocietyUtils.Database.BOOK_ISBN_PARAMETER, isbn);
      ArrayList results = (ArrayList) dbService.executeQuery(BolSocietyUtils.Database.GET_BOOK_BY_ISBN_QUERY_NAME,
          parameters);
      if (results.size() > 0) {
        Object[] objects = (Object[]) results.get(0);
        BookModel book = BookUtil.getBookModelFromDatabase(objects);
        outputBookDetails(out, book);
      }
    } catch (SQLException sqlex) {
      if (logging.isErrorEnabled()) {
        logging.error("Error querying database for book", sqlex);
      }
    } catch (IOException io) {
      if (logging.isErrorEnabled()) {
        logging.error("Error outputing results:", io);
      }
    }
  }


  /**
   * Searchs for books based on parameters
   *
   * @param request Servlet REquest
   * @param response Servlet Response
   */
  private void bookSearch(HttpServletRequest request,
    HttpServletResponse response) {
    String keyword = request.getParameter(KEYWORD_NAME);
    String searchType = request.getParameter(SEARCH_TYPE_NAME);
    if (keyword == null) {
      getCatalog(response);
    } else {
      try {
        PrintWriter out = response.getWriter();

        if (logging.isDebugEnabled()) {
          logging.debug("Searching for books by:" + searchType + "/" + keyword);
        }

        ArrayList results = new ArrayList();
        Map parameters = new HashMap();
        parameters.put(BolSocietyUtils.Database.BOOK_SEARCH_PARAMETER, keyword);
        try {
          if (searchType.equals("Title")) {
            results = (ArrayList) dbService.executeQuery(BolSocietyUtils.Database.SEARCH_BOOK_BY_TITLE_QUERY_NAME,
                parameters);
          } else if (searchType.equals("Author")) {
            results = (ArrayList) dbService.executeQuery(BolSocietyUtils.Database.SEARCH_BOOK_BY_AUTHOR_QUERY_NAME,
                parameters);

          } else if (searchType.equals("Subject")) {
            results = (ArrayList) dbService.executeQuery(BolSocietyUtils.Database.SEARCH_BOOK_BY_SUBJECT_QUERY_NAME,
                parameters);

          }

          for (int i = 0; i < results.size(); i++) {
            Object[] objects = (Object[]) results.get(i);
            BookModel book = BookUtil.getBookModelFromDatabase(objects);
            outputBookDetails(out, book);

          }
        } catch (SQLException sqlex) {
          if (logging.isErrorEnabled()) {
            logging.error("Error searching for books", sqlex);

          }
        }

        out.flush();
      } catch (IOException io) {
        if (logging.isErrorEnabled()) {
          logging.error("Error outputing results:", io);
        }
      }
    }
  }


  private void outputBookDetails(PrintWriter out, BookModel book) {
    boolean inStock = book.getShelf() > 0;

    out.println(book.getIsbn() + ";" + book.getTitle() + ";" + book.getAuthor()
      + ";" + book.getEndPrice() + ";" + book.getListPrice() + ";"
      + book.getOurPrice() + ";" + inStock + ";" + book.getPublisher() + ";"
      + book.getPublisherNotes() + ";" + book.getLength() + ";" + book.getTOC());

  }


  /**
   * Get all books in the catalog
   *
   * @param response Servlet Response
   */
  private void getCatalog(HttpServletResponse response) {
    try {
      PrintWriter out = response.getWriter();
    } catch (IOException io) {
      if (logging.isErrorEnabled()) {
        logging.error("Error outputing results:", io);
      }
    }
  }
}
