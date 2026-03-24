package com.saucedemo.config;

import com.codeborne.selenide.Configuration;

public class DriverManager {
    
    public static void setupDriver() {
        String browser = System.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        
        Configuration.browser = browser;
        Configuration.headless = headless;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.screenshots = true;
        Configuration.savePageSource = false;
        Configuration.reopenBrowserOnFail = false;
    }
    
    public static void setBrowser(String browser) {
        Configuration.browser = browser;
    }
    
    public static void setHeadless(boolean headless) {
        Configuration.headless = headless;
    }
    
    public static void setBrowserSize(String size) {
        Configuration.browserSize = size;
    }
}
