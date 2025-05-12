package com.hexaware.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
//Utility class for establishing database connections.
public class DBConnUtil {
    public static Connection getConnection() {
    	//Establishes and returns a database connection.
        Connection conn = null;
        try {
            Properties props = DBPropertyUtil.loadProperties();
            Class.forName(props.getProperty("db.driver"));
            conn = DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.username"),
                props.getProperty("db.password")
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}

