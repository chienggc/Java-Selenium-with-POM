Feature: Technical Test Form

  @positive
  Scenario: TF0001 User able to submit form after filled up form details
    Given User navigates to test form page
    When User enters first name: "John"
    And User enters last name: "Doe"
    And User enters email: "john.doe@example.com"
    And User selects gender: "Male"
    And User enters mobile num: "82221234"
    And User selects date of birth: "12/10/1995"
    And User selects hobbies : "Sports,Music,Reading"
    And User uploads file: "download.png"
    And User selects location: "North"
    And User enters address: "Jalan 123, 970111, state, Country"
    And User submits the form
    Then verified form submitted successfully

  @positive
  Scenario: TF0002 User is able to submit the form without filling in optional fields
    Given User navigates to test form page
    When User enters first name: "John"
    And User enters last name: "Doe"
    And User enters email: "john.doe@example.com"
    And User selects gender: "Male"
    And User enters mobile num: "82221234"
    And User selects date of birth: "12/10/1995"
    And User selects hobbies : "Sports,Music,Reading"
    And User submits the form
    Then verified form submitted successfully

  @negative
  Scenario Outline: TF0002 User submit empty form and verify mandatory field have error message
    Given User navigates to test form page
    When User submits the form
    Then verified field: "<fieldName>" "<haveOrNo>" validation message: "<errorMsg>"
    Examples:
      | fieldName       | haveOrNo | errorMsg               |
      | First Name      | have     | This field is required     |
      | Last Name       | have     | This field is required     |
      | Email           | have     | This field is required     |
      | Gender          | have     | This field is required     |
      | Mobile number   | have     | This field is required     |
      | Date of Birth   | have     | This field is required     |
      | Hobbies         | have     | This field is required     |

  @negative
  Scenario Outline: TF0005 Ensure Mobile number field validation is working
    Given User navigates to test form page
    When User enters mobile num: "<mobileNum>"
    Then verified field: "Mobile number" "<haveOrNo>" validation message: "Please enter a valid mobile number"
    #123
    Examples:
      | mobileNum   | haveOrNo |
      | 22111234    | have     | # invalid ,not start with 8 or 9
      | 00000000    | have     | # invalid ,not start with 8 or 9
      | !@#$%^&*    | have     | # Special characters should not be valid
      | 821112349   | have     | # Length of the number should be 8 digits
      | 8211123     | have     | # Missing one digit, should fail
      | 88AA99BB    | have     | # Alphanumeric, should fail
      | ABCDEFGH    | have     | # Letters, should fail
      | 82111234    | have no  | # valid
      | 92111234    | have no  | # valid


