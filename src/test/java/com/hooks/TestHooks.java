package com.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import com.utils.BrowserSetup;

public class TestHooks {

    private static WebDriver driver;

    @Before("@ui")
    public void setUp() {
        driver = BrowserSetup.getDriver();
        System.out.println("Driver initialized: " + driver);
    }


    @Before("@api")  // This hook will run only for API tests (tagged with @api)
    public void setupAPI() {
        // Set up RestAssured configuration for API tests (optional)
        RestAssured.baseURI = "https://api.restful-api.dev";
        RestAssured.given()
                .log().all();  // Log request details (headers, body, etc.)
    }

    @After("@api")  // This hook will run only for API tests (tagged with @api)
    public void tearDownAPI() {
        // Set up RestAssured configuration for API tests (optional)
        RestAssured.given()
                .then()
                .log().all();  // Log response details (headers, body, etc.)
    }

    @After("@ui")
    public void tearDown() {
        if (driver != null) {
            BrowserSetup.quitDriver();
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


