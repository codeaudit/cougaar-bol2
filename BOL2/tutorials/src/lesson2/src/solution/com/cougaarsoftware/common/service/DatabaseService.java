/*
 * <copyright>
 *  Copyright 2000-2003 Cougaar Software, Inc.
 *  All Rights Reserved
 * </copyright>
 */


/*
 * Created on Jul 2, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cougaarsoftware.common.service;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * A Service providing functionality to execute SQL statements and to
 * retrieve data from a database using SQL statements identified in 
 * the database service's xml file.
 *
 * @author ttschampel To change the template for this generated type comment go
 *         to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 *         Comments
 */
public interface DatabaseService {
    /**
     * Execute a SQL Statement
     *
     * @param statementName Statement name as it appears in the .q file
     * @param parameterMappings Mappings
     *
     * @throws SQLException
     */
    public void executeStatement(String statementName, Map parameterMappings)
        throws SQLException;


    /**
     * Execute a SQL Query
     *
     * @param queryName Name of Query (From q.xml file)
     * @param parameterMappings Map of parameter to value mappings 
     * 	parameter name should be the :PARAMETER_NAME: found in the q.xml
     *
     * @return List of Object Arrays pulled from the ResultSet
     *
     * @throws SQLException SQL Exception
     */
    public List executeQuery(String queryName, Map parameterMappings)
        throws SQLException;
}
