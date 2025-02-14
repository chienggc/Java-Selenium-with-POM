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

  @negative @positive
  Scenario Outline: TF0005 Ensure Mobile number field validation is working
    Given User navigates to test form page
    When User enters mobile num: "<mobileNum>"
    Then verified field: "Mobile number" "<haveOrNo>" validation message: "Please enter a valid mobile number"
    Examples:
      | mobileNum   | haveOrNo |
      | 22111234    | have     |
      | 00000000    | have     |
      | !@#$%^&*    | have     |
      | 821112349   | have     |
      | 8211123     | have     |
      | 88AA99BB    | have     |
      | ABCDEFGH    | have     |
      | 82111234    | have no  |
      | 92111234    | have no  |


  @negative @positive
  Scenario Outline: TF0005 Ensure email validation is working
    Given User navigates to test form page
    When User enters email: "<email>"
    Then verified field: "Email" "<haveOrNo>" validation message: "Please enter a valid email"
    #!
    Examples:
      | email                   | haveOrNo |
      | chienggc9387@gmail.com  | have no  |
      | chienggc9387@icloud.com | have no  |
      | tester@tech.gov.sg      | have no  |
      | 12344123                | have     |
      | 12344123@123123123      | have     |
      | 123123gmail.com         | have     |
      | 1@#@!#@!@gmail.com      | have     |
      | 12321321@ .com          | have     |

  Scenario Outline: Ensure check box check and uncheck is working
    Given User navigates to test form page
    When User selects hobbies : "<field>"
    Then verified "<field>" is "selected"
    When User selects hobbies : "<field>"
    Then verified "<field>" is "not selected"
    Examples:
      | field     |
      | Sports    |
      | Music     |
      | Reading   |


  Scenario Outline: Ensure radio button selection is working
    Given User navigates to test form page
    Then verified "Male" is "not selected"
    And verified "Female" is "not selected"
    When User selects gender: "<firstGenderSelection>"
    Then verified "<firstGenderSelection>" is "selected"
    And verified "<secondGenderSelection>" is "not selected"
    When User selects gender: "<secondGenderSelection>"
    Then verified "<secondGenderSelection>" is "selected"
    And verified "<firstGenderSelection>" is "not selected"
    Examples:
      | firstGenderSelection | secondGenderSelection  |
      | Male                 | Female                 |
      | Female               | Male                   |




