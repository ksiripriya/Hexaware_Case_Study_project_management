package com.hexaware.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getConnectionString(String propertyFileName) {
        Properties props = new Properties();
        String connectionString = null;

        try (FileInputStream fis = new FileInputStream(propertyFileName)) {
            props.load(fis);
            String url = props.getProperty("url");
            String user = props.getProperty("username");
            String password = props.getProperty("password");
            connectionString = url + "?user=" + user + "&password=" + password;
        } catch (IOException e) {
            System.out.println("Error reading property file: " + e.getMessage());
        }

        return connectionString;
    }
}
