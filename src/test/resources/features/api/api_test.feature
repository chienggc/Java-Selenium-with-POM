Feature: API Response Validation

  @api
  Scenario: Verify Google Pixel 6 Pro exists in the response with correct data
    Given I call the API to fetch the list of objects
    Then I should see "Google Pixel 6 Pro" in the response
    And the product should have "Cloudy White" as color
    And the product should have "128 GB" as capacity

  @api
  Scenario: Create a new product with custom payload
    Given I post a new product to the API with the following payload:
      """
      {
         "name": "Apple MacBook Pro 16",
         "data": {
            "year": 2019,
            "price": 1849.99,
            "CPU model": "Intel Core i9",
            "Hard disk size": "1 TB"
         }
      }
      """
    Then I should receive an id in the response
