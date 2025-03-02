package com.stepdefinitions.api;

import com.service.ApiService;  // Import ApiService class for API calls
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiStepDefinitions {

    private Response response;
    private ApiService apiService;  // Declare an instance of ApiService

    public ApiStepDefinitions() {
        apiService = new ApiService();  // Initialize the ApiService
    }

    @Given("I call the API to fetch the list of objects")
    public void i_call_the_api_to_fetch_the_list_of_objects() {
        // Send GET request to the API using ApiService
        response = apiService.get("/objects");

        // Validate the response status code
        response.then().statusCode(200);
    }

    @Then("I should see {string} in the response")
    public void i_should_see_in_the_response(String productName) {
        // Validate that the response contains the product name
        response.then().body("name", hasItem(productName));
    }

    @Then("the product should have {string} as color")
    public void the_product_should_have_as_color(String color) {
        // Validate that the product's color in the data matches the expected value
        response.then().body("data.color", hasItem(color));
    }

    @Then("the product should have {string} as capacity")
    public void the_product_should_have_as_capacity(String capacity) {
        // Validate that the product's capacity in the data matches the expected value
        response.then().body("data.capacity", hasItem(capacity));
    }

    @Given("I post a new product to the API with the following payload:")
    public void i_post_a_new_product_to_the_api_with_the_following_payload(String payload) {
        // Send POST request with the dynamic payload from feature file using ApiService
        response = apiService.post("/objects", payload);

        // Validate the response status code
        response.then().statusCode(201);  // Expecting HTTP 201 (Created)
    }

    @Then("I should receive an id in the response")
    public void i_should_receive_an_id_in_the_response() {
        // Validate that the response contains a valid id with length greater than 0
        String id = response.jsonPath().getString("id");
        assertThat(id, notNullValue());  // id should not be null
        assertThat(id.length(), greaterThan(0));  // id should have a length greater than 0
    }
}
