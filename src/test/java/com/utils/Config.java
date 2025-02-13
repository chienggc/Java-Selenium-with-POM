package com.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;

public class Config {
    private static Properties properties = new Properties();

    static {
        // Get the environment (e.g., "dev", "prod") from system properties or default to "dev"
        String env = System.getProperty("env", "qat");

        // Determine the correct properties file based on the environment
        String propertiesFile = "src/test/java/com/properties/config-" + env + ".properties";

        // Load the properties file corresponding to the environment
        try (InputStream input = new FileInputStream(propertiesFile)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + propertiesFile);
                System.exit(1); // Uncomment this line if you want to stop execution on failure
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Get browser configuration
    public static String getBrowser() {
        return properties.getProperty("browser", "chrome"); // Default to Chrome if not found
    }

    // Get the URL to navigate to
    public static String getUrl() {
        return properties.getProperty("url", "https://www.mom.gov.sg/eservices/services/apply-for-work-holiday-pass");
    }
}
