package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class DBConnUtil {

    // Method to establish connection from properties file
    public static Connection getConnection(String propertyFileName) {
        Connection connection = null;

        try {
            // Load properties from the file
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream(propertyFileName);
            props.load(fis);

            String driver = props.getProperty("driver");
            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            // Load JDBC driver and create the connection
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);

        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("DB connection error: " + e.getMessage());
        }

        return connection;
    }
}


