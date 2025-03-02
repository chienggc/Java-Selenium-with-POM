package com.utils;

import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;

public class Config {
    private static Properties properties = new Properties();

    static {
        // Get the environment (e.g., "dev", "prod") from system properties or default to "qat"
        String env = System.getProperty("env", "qat");

        // Correct path to the properties file located in src/test/resources/com/properties/
        String propertiesFile = "com/properties/config-" + env + ".properties";

        // Load the properties file corresponding to the environment
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream(propertiesFile)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + propertiesFile);
                System.exit(1); // Stop execution if the properties file is not found
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Get browser configuration
    public static String getBrowser() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            browser = properties.getProperty("browser", "chrome");
        }
        return browser;
    }

    // Get the URL to navigate to
    public static String getUrl() {
        return properties.getProperty("url", "https://www.mom.gov.sg/eservices/services/apply-for-work-holiday-pass");
    }

    // Get base URL
    public static String getBaseUrl() {
        return properties.getProperty("baseUrl", "https://default-base-url.com");
    }
}
