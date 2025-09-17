package com.smartshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
    // JDBC driver class for MySQL
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    
    // Database connection URL (smartshop is the DB name)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/smartshop";
    
    // Database credentials
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    /**
     * Establishes and returns a connection to the database.
     * 
     * @return Connection object if successful, otherwise null
     * @throws SQLException if database connection fails
     */
    public static Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName(DB_DRIVER);
            
            // Create connection using URL, username and password
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            // Handle case when driver is not found in classpath
            e.printStackTrace();
        }
        return con; // Return the connection object (null if failed)
    }	
}
