package com.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.concurrent.TimeUnit;

public class BrowserSetup {

    private static WebDriver driver;

    // Private constructor to prevent instantiation
    private BrowserSetup() {}

    // Singleton method to get the WebDriver instance
    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = Config.getBrowser(); // Get the browser type from config-qat.properties
            String headless = System.getProperty("headless", "false");

            // Set up the driver for the specified browser
            if ("chrome".equalsIgnoreCase(browser)) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-web-security");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("start-maximized");  // Start Chrome maximized
                if (headless.equals("true")) {
                    options.addArguments("--headless");  // Enable headless mode
                }
                driver = new ChromeDriver(options);
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");


            } else if ("firefox".equalsIgnoreCase(browser)) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--disable-web-security");
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver(options);
            } else {
                // Default to Chrome if the browser type is not recognized
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
            }

            // Common timeout settings for all browsers
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        }
        return driver;
    }

    // Method to quit the driver when tests are finished
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();  // This will close the browser and end the session
            driver = null;  // Nullify the driver instance
        }
    }

    // Method to reset WebDriver instance (in case of a fresh start)
    public static void resetDriver() {
        quitDriver();  // Quit any existing driver session
        driver = null;  // Reset the driver to null
    }
}
