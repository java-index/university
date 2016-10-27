package com.smarterama.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionFactory {
	
	private static final Logger LOG = Logger.getLogger(ConnectionFactory.class);
	
	private static String url = "jdbc:postgresql://localhost:5432/university";
    private static String user = "postgres";
    private static String password = "sql159654";
    
    public static Connection getConnection() throws SQLException {
    	LOG.debug("Get connection");
    	try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(url, user, password);
	}
}	
