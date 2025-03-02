package com.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiService {

    private static String BASE_URL;

    static {
        // Load the environment (qat, prod, etc.)
        String env = System.getProperty("env", "qat");

        // Correct path to the properties file located in src/test/resources/com/properties/
        String propertiesFile = "com/properties/config-" + env + ".properties";
        Properties properties = new Properties();

        try (InputStream input = ApiService.class.getClassLoader().getResourceAsStream(propertiesFile)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + propertiesFile);
            } else {
                // Load properties from the file
                properties.load(input);
                BASE_URL = properties.getProperty("baseUrl");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Setup base URI globally for all requests
        if (BASE_URL == null || BASE_URL.isEmpty()) {
            throw new IllegalArgumentException("Base URL is not defined in the properties file.");
        }

        RestAssured.baseURI = BASE_URL;
    }

    public ApiService() {
        // The constructor now doesn't need to do anything as the static block sets up the base URI
    }

    public Response get(String endpoint) {
        return RestAssured.given()
                .when()
                .get(endpoint);
    }

    public Response post(String endpoint, String payload) {
        return RestAssured.given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post(endpoint);
    }

    // Other methods like PUT, DELETE, etc. can be added here for more flexibility
}
