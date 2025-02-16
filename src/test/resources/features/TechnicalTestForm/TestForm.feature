Feature: Technical Test Form

# input with data table
  @positive
  Scenario: TF0001 User able to submit form after filled up ALL field
    Given User navigates to test form page
    When User enters the following details:
      | Field          | Value                        |
      | First Name     | choon                        |
      | Last Name      | guan                         |
      | Email          | chieng.gc@example.com        |
      | Gender         | Male                         |
      | Mobile number  | 82221234                     |
      | Date of Birth  | 12/10/1995                   |
      | Hobbies        | Sports,Music,Reading         |
      | File           | download.png                 |
      | Location       | North                        |
      | Address        | Jalan 123, 970111, state, Country |
    And User submits the form
    Then verified form submitted successfully

      # input with keyword
  @positive
  Scenario: TF0002 User is able to submit the form without filling in optional fields
    Given User navigates to test form page
    When User enters first name: "choon"
    And User enters last name: "guan"
    And User enters email: "chieng.gc@example.com"
    And User selects gender: "Male"
    And User enters mobile num: "82221234"
    And User selects date of birth: "12/10/1995"
    And User selects hobbies : "Sports,Music,Reading"
    And User submits the form
    Then verified form submitted successfully

    # input with Scenario Outline
  @negative
  Scenario Outline: TF0003 User submits empty form and verify mandatory field have error message
    Given User navigates to test form page
    When User submits the form
    Then verified field: "<fieldName>" "<haveOrNo>" validation message: "<errorMsg>"
    Examples:
      | fieldName       | haveOrNo | errorMsg               |
      | First Name      | have     | This field is required |
      | Last Name       | have     | This field is required |
      | Email           | have     | This field is required |
      | Gender          | have     | This field is required |
      | Mobile number   | have     | This field is required |
      | Date of Birth   | have     | This field is required |
      | Hobbies         | have     | This field is required |

  @fieldValidation
  Scenario Outline: TF0004 Ensure Mobile number field validation is working
    Given User navigates to test form page
    When User enters mobile num: "<mobileNum>"
    Then verified field: "Mobile number" "<haveOrNo>" validation message: "<vMessage>"
    Examples:
      | mobileNum   | haveOrNo | vMessage |
      | 22111234    | have     | Please enter a valid mobile number |
      | 00000000    | have     | Please enter a valid mobile number |
      | !@#$%^&*    | have     | This field is required |
      | 821112349   | have     | Please enter a valid mobile number |
      | 8211123     | have     | Please enter a valid mobile number |
      | 88AA99BB    | have     | Please enter a valid mobile number |
      | ABCDEFGH    | have     | This field is required |
      | 82111234    | have no  | Please enter a valid mobile number |
      | 92111234    | have no  | Please enter a valid mobile number |

  @fieldValidation
  Scenario Outline: TF0005 Ensure email validation is working
    Given User navigates to test form page
    When User enters email: "<email>"
    Then verified field: "Email" "<haveOrNo>" validation message: "Please enter a valid email"
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

  @fieldValidation
  Scenario Outline: TF0006 Ensure check box check and uncheck is working
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

  @fieldValidation
  Scenario Outline: TF0007 Ensure radio button selection is working
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

  @fieldValidation
  Scenario: TF0008 Ensure input is stored in text field correctly
    Given User navigates to test form page
    When User enters first name: "John"
    Then verified "First Name" text field contains: "John"
    When User enters last name: "Doe"
    Then verified "Last Name" text field contains: "Doe"
    When User enters email: "john.doe@example.com"
    Then verified "Email" text field contains: "john.doe@example.com"
    When User enters mobile num: "82221234"
    Then verified "Mobile number" text field contains: "8222 1234"

  @fieldValidation
  Scenario: TF0009 Ensure input is stored in text area field correctly
    Given User navigates to test form page
    When User enters address: "Jalan 123, 970111, state, Country"
    Then verified "Address" text area contains: "Jalan 123, 970111, st  ate, Country"

  @failed
  Scenario: TF0010 Demo failed test cases
    Given User navigates to test form page
    When User enters address: "Jalan 123, 970111, state, Country"
    Then verified "Address" text area contains: "1234"

  @calendar
  Scenario: TF00011 Ensure selected date is reflect correct on date field
    Given User navigates to test form page
    When User selects date of birth: "12/10/1995"
    Then verified "Date of Birth" text field contains: "12/10/1995"

  @calendar
  Scenario: TF00012 Verify by default, today's date will be circled from calendar
    Given User navigates to test form page
    When User clicks on calendar button to expand calendar
    Then Verified today's date is circled from calendar

  @calendar
  Scenario: TF00013 Ensure after date is selected, it will be highlighted
    Given User navigates to test form page
    When User select today's date
    Then Verified today's date is selected from calendar

  @calendar
  Scenario: TF00014 Verify by default, today's date will be circled from calendar
    Given User navigates to test form page
    When User clicks on calendar button to expand calendar
    Then Verified future date is disabled

  @fileuploadvalidation
  Scenario: TF0015 
    Given User navigates to test form page
    When User uploads file: "git.exe"
    Then verified field: "Attachment" "have" validation message: "Your file's extension ending in *.exe is not allowed"
    When User uploads file: "morethan7mb.zip"
    Then verified field: "Attachment" "have" validation message: "1You have exceeded the file size limit, please upload a file below 7 MB"



  #selection field
