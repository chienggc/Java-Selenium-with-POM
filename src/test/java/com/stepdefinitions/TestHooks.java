package com.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import com.utils.BrowserSetup;

public class TestHooks {

    private static WebDriver driver;

    @Before
    public void setUp() {
        driver = BrowserSetup.getDriver();
        System.out.println("Driver initialized: " + driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
//            BrowserSetup.quitDriver();
            System.out.println("Driver quit successfully.");
        } else {
            System.out.println("Driver already initialized: " + driver);
        }   
    }

    // Make this method static so it can be accessed from static contexts
    public static WebDriver getDriver() {
        if (driver == null) {
            // Initialize the driver if it's null (i.e., the previous session was quit)
            driver = BrowserSetup.getDriver();
        }
        return driver;
    }
}


