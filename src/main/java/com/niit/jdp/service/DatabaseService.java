package com.niit.jdp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {

    // This is the URL of the database.
    private static final String URL = "jdbc:mysql://localhost:3306/jukebox";
    // A constant variable for USERNAME & PASSWORD
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Shivam@1999";

    // A private variable of type Connection.
    private Connection connection;

    public DatabaseService() {
        this.connection = null;
    }

    public Connection getConnection() {
        return connection;
    }


    /**
     * > This function connects to the database and returns true if the connection is successful
     *
     * @return A boolean value.
     */
    public boolean connect() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection != null;
    }


    /**
     * If the connection is not null, print "Connected to the database", otherwise print "Not connected to the database
     * check USERNAME and PASSWORD"
     */
    public void printConnectionStatus() {
        if (connection != null) {
            System.out.println("Connected to the database");
        } else {
            System.err.println("Not connected to the database check USERNAME and PASSWORD");
        }
    }
}
