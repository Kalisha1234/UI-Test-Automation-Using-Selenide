package com.saucedemo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestDataReader {
    
    private static Properties properties;
    
    static {
        properties = new Properties();
        try (InputStream input = TestDataReader.class.getClassLoader()
                .getResourceAsStream("testdata/testdata.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public static String getStandardUsername() {
        return getProperty("standard.user.username");
    }
    
    public static String getStandardPassword() {
        return getProperty("standard.user.password");
    }
    
    public static String getLockedOutUsername() {
        return getProperty("locked.out.user.username");
    }
    
    public static String getProblemUsername() {
        return getProperty("problem.user.username");
    }
    
    public static String getPerformanceGlitchUsername() {
        return getProperty("performance.glitch.user.username");
    }
    
    public static String getErrorUsername() {
        return getProperty("error.user.username");
    }
    
    public static String getVisualUsername() {
        return getProperty("visual.user.username");
    }
    
    public static String getAppUrl() {
        return getProperty("app.url");
    }
    
    public static String getCheckoutFirstName() {
        return getProperty("checkout.firstname");
    }
    
    public static String getCheckoutLastName() {
        return getProperty("checkout.lastname");
    }
    
    public static String getCheckoutPostalCode() {
        return getProperty("checkout.postalcode");
    }
}
