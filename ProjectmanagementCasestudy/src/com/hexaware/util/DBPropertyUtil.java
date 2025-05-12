package com.hexaware.util;

	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.Properties;
//Utility class for loading database configuration properties from a file.

	public class DBPropertyUtil {
		//Loads and returns database connection properties.
	    public static Properties loadProperties() {
	        Properties props = new Properties();
	        try {
	            FileInputStream fis = new FileInputStream("resources/db.properties");
	            props.load(fis);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return props;
	    }
	}

