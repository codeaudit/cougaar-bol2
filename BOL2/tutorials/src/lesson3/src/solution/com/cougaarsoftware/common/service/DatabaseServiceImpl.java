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
 * Created on Jul 2, 2003
 *
 */
package com.cougaarsoftware.common.service;


import javax.xml.parsers.DocumentBuilderFactory;

import org.cougaar.core.component.ServiceBroker;
import org.cougaar.core.component.ServiceRevokedEvent;
import org.cougaar.core.component.ServiceRevokedListener;
import org.cougaar.core.service.LoggingService;
import org.cougaar.util.ConfigFinder;
import org.cougaar.util.DBConnectionPool;
import org.cougaar.util.Parameters;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Database Service Implementation
 */
public class DatabaseServiceImpl implements DatabaseService {
  private static final String QUERY_FILE_NAME = "q.xml";
  private static final String QUERY_ELEMENT = "query";
  private static final String ROOT_ELEMENT = "queryfile";
  private static final String DATABASE_ELEMENT = "database";
  /** Logging Service */
  private LoggingService logging;
  protected ServiceBroker serviceBroker;
  private Map dbProperties = null;

  /**
   * Creates a new DatabaseServiceImpl object.
   *
   * @param sb Service Broker
   */
  public DatabaseServiceImpl(ServiceBroker sb) {
    super();
    setServiceBroker(sb);
    load();
  }

  /**
   * Sets the serviceBroker attribute.
   *
   * @param sb ServiceBroker
   */
  public void setServiceBroker(ServiceBroker sb) {
    this.serviceBroker = sb;
  }


  /**
   * This loads the logging service.
   *
   * @throws RuntimeException Run Time Exception
   */
  public void load() {
    /*
     * setup the logging service
     */
    if (logging == null) {
      logging = (LoggingService) serviceBroker.getService(this,
          LoggingService.class,
          new ServiceRevokedListener() {
            public void serviceRevoked(ServiceRevokedEvent re) {
              if (LoggingService.class.equals(re.getService())) {
                logging = null;
              }
            }
          });
    }

    if (logging == null) {
      throw new RuntimeException("Unable to obtain logging service");
    }

    this.parseQueryFile();

  }


  /**
   * Execute a SQL Statement
   *
   * @param statementName Statement name as it appears in the .q file
   * @param parameterMappings Mappings
   *
   * @throws SQLException
   */
  public void executeStatement(String statementName, Map parameterMappings)
    throws SQLException {
    if (logging.isDebugEnabled()) {
      logging.debug("Excecuting statement:" + statementName);
    }

    String query = getQuery(statementName, parameterMappings);
    if (logging.isDebugEnabled()) {
      logging.debug("Query retrieved from .q file:" + query);
    }

    Connection connection = null;
    Statement statement = null;
    try {
      connection = getConnection(statementName);
      statement = connection.createStatement();
      statement.execute(query);
    } catch (SQLException sqlex) {
      if (logging.isErrorEnabled()) {
        logging.error("Error executing statement named:" + statementName, sqlex);

      }

      throw sqlex;
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }

        if (connection != null) {
          connection.close();
        }
      } catch (SQLException sqlex2) {
      }
    }
  }


  /**
   * Execute SQL Query
   *
   * @param queryName name of query
   * @param parameterMappings query parameters and values
   *
   * @return List of Object[]
   *
   * @throws SQLException SQL Exception
   */
  public List executeQuery(String queryName, Map parameterMappings)
    throws SQLException {
    ArrayList results = new ArrayList();
    ResultSet rs = null;
    String query = getQuery(queryName, parameterMappings);
    if (logging.isDebugEnabled()) {
      logging.debug("Excecuting query:" + query);
    }

    Connection connection = null;
    Statement stmt = null;
    try {
      connection = getConnection(queryName);
      stmt = connection.createStatement();
      rs = stmt.executeQuery(query);
      ResultSetMetaData md = rs.getMetaData();
      int ncols = md.getColumnCount();
      Object[] row = new Object[ncols];
      while (rs.next()) {
        row = new Object[ncols];
        for (int i = 0; i < ncols; i++) {
          row[i] = rs.getObject(i + 1);
        }

        results.add(row);
      }

      if (logging.isDebugEnabled()) {
        logging.debug("Returning:" + results.size() + " results");
      }
    } catch (SQLException sqlex) {
      if (logging.isErrorEnabled()) {
        logging.error("Error executing query:" + queryName, sqlex);

      }

      throw sqlex;
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }

        if (stmt != null) {
          stmt.close();
        }

        if (connection != null) {
          connection.close();
        }
      } catch (SQLException sqlex2) {
      }
    }

    return results;

  }


  /**
   * Get Connection from DBConnectionPool
   *
   * @param queryName Name of Query from q.xml
   *
   * @return Connection
   *
   * @throws SQLException SQL Connection
   */
  protected Connection getConnection(String queryName)
    throws SQLException {
    try {
      Query query = (Query) dbProperties.get(queryName);
      String database = query.url;
      String username = query.username;
      String password = query.password;
      return DBConnectionPool.getConnection(database, username, password);
    } catch (SQLException sqlex) {
      logging.error("Error getting connection from DBConnectionPool", sqlex);
      throw sqlex;
    } catch (NullPointerException npe) {
      if (logging.isErrorEnabled()) {
        logging.error("No query defined by " + queryName);

      }

      throw new SQLException();
    }
  }


  /**
   * Parse the query file. If no query file present a RuntimeException is
   * thrown
   */
  private void parseQueryFile() {
    dbProperties = new HashMap();
    try {
      ConfigFinder configFinder = new ConfigFinder();

      File xmlFile = configFinder.locateFile(QUERY_FILE_NAME);

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      Document doc = factory.newDocumentBuilder().parse(xmlFile);
      NodeList queryFileList = doc.getElementsByTagName(ROOT_ELEMENT);
      for (int i = 0; i < queryFileList.getLength(); i++) {
        Element queryFileElement = (Element) queryFileList.item(i);
        NodeList databaseList = queryFileElement.getElementsByTagName(DATABASE_ELEMENT);
        for (int x = 0; x < databaseList.getLength(); x++) {
          HashMap queryMap = new HashMap();
          Element databaseElement = (Element) databaseList.item(x);
          NamedNodeMap attributes = databaseElement.getAttributes();
          Node urlNode = attributes.getNamedItem("url");
          Node usernameNode = attributes.getNamedItem("username");
          Node passwordNode = attributes.getNamedItem("password");
          Node driverNode = attributes.getNamedItem("driver");

          String url = urlNode.getNodeValue();
          String username = usernameNode.getNodeValue();
          String password = passwordNode.getNodeValue();
          String driver = driverNode.getNodeValue();
          if (url.indexOf("${") >= 0) {
            url = url.substring(2, url.length() - 1);
            url = Parameters.findParameter(url);
          }

          if (username.indexOf("${") >= 0) {
            username = username.substring(2, username.length() - 1);
            username = Parameters.findParameter(username);
          }

          if (password.indexOf("${") >= 0) {
            password = password.substring(2, password.length() - 1);
            password = Parameters.findParameter(password);
          }

          if (driver.indexOf("${") >= 0) {
            driver = driver.substring(2, driver.length() - 1);
            driver = Parameters.findParameter(driver);
          }

          try {
            DBConnectionPool.registerDriver(driver);
            if (logging.isInfoEnabled()) {
              logging.info("Testing connection to database");

            }

            try {
              Connection connection = DBConnectionPool.getConnection(url,
                  username, password);
              connection.close();
              if (logging.isInfoEnabled()) {
                logging.info("Successfully connected to " + url);

              }
            } catch (SQLException sqlex) {
              if (logging.isErrorEnabled()) {
                logging.error("Error connectiont to database", sqlex);
              }
            }
          } catch (Exception e) {
            if (logging.isErrorEnabled()) {
              logging.error("Error loading driver " + driver, e);
            }
          }

          NodeList queryList = databaseElement.getElementsByTagName(QUERY_ELEMENT);
          for (int q = 0; q < queryList.getLength(); q++) {
            Element queryElement = (Element) queryList.item(q);
            NamedNodeMap queryAttributes = queryElement.getAttributes();
            Node queryNameNode = queryAttributes.getNamedItem("name");
            NodeList elementDataList = queryElement.getChildNodes();
            Node queryTextNode = null;
            for (int z = 0; z < elementDataList.getLength(); z++) {
              if (queryTextNode == null) {
                queryTextNode = elementDataList.item(z);
              } else if (elementDataList.item(z) instanceof CDATASection) {
                queryTextNode = elementDataList.item(z);
              } else if (!(queryTextNode instanceof CDATASection)) {
                queryTextNode = elementDataList.item(z);
              }
            }

            String queryText = queryTextNode.getNodeValue();
            String queryName = queryNameNode.getNodeValue();
            Query query = new Query();
            query.password = password;
            query.dbDriver = driver;
            query.queryName = queryName;
            query.queryText = queryText;
            if (logging.isDebugEnabled()) {
              logging.debug(queryText);
            }

            query.url = url;
            query.username = username;
            dbProperties.put(queryName, query);
          }
        }
      }
    } catch (Exception e) {
      if (logging.isErrorEnabled()) {
        logging.error("Error parsing xml config file", e);
      }
    }
  }


  /**
   * Get Query syntax with parameter replacement
   *
   * @param queryName
   * @param parameters
   *
   * @return
   */
  private String getQuery(String queryName, Map parameters) {
    String queryText = null;
    try {
      Query query = (Query) dbProperties.get(queryName);
      queryText = query.queryText;
      if (parameters != null) {
        Set keys = parameters.keySet();
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()) {
          String parameterName = (String) iterator.next();
          String parameterValue = (String) parameters.get(parameterName);
          int index = queryText.indexOf(":" + parameterName + ":");
          while (index >= 0) {
            String part1 = queryText.substring(0, index) + parameterValue;
            String part2 = queryText.substring(index + parameterName.length()
                + 2, queryText.length());
            queryText = part1 + part2;

            index = queryText.indexOf(":" + parameterName + ":");

          }
        }
      }
    } catch (Exception e) {
      if (logging.isErrorEnabled()) {
        logging.error("Error getting query " + queryName, e);
      }
    }

    return queryText;
  }

  /**
   * Query Value object
   *
   * @author ttschampel
   */
  private class Query {
    public String dbDriver;
    public String username;
    public String password;
    public String url;
    public String queryText;
    public String queryName;

    public Query() {
    }
  }
}
