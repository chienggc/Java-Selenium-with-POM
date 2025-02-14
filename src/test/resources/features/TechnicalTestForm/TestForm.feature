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
# can use scenarios if running multiple iterations of the same scenario with different sets of data
#    Examples:
#      | firstName | lastName | email                  | gender | mobileNum | dob        | hobbies                | file         | location | address                    |
#      | John      | Doe      | john.doe@example.com    | Male   | 82221234  | 12/10/1995 | Sports, Music, Reading | download.png | North    | Jalan 123, 970111, state, Country |
#      | Jane      | Smith    | jane.smith@example.com  | Female | 82221235  | 01/01/1990 | Reading, Music         | file2.png    | South    | Jalan 456, 970112, state, Country |
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

#  @negative
#  Scenario Outline: TF0002 User submit empty form and verify mandatory field have error message
#    Given User navigates to test form page
#    When User submits the form
#    Then verify field: "<fieldName>" able to have validation message: "<errorMsg>"
#    Examples:
#      | fieldName       | errorMsg                   |
#      | First Name      | This field is required     |
#      | Last Name       | This field is required     |
#      | Email           | This field is required     |
#      | Gender          | This field is required     |
#      | Mobile number   | This field is required     |
#      | Date of Birth   | This field is required     |
#      | Hobbies         | This field is required     |



