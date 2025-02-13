#Feature: Application (Stage 2).
#
#  Scenario: US002 Navigate to Stage 2 page, and fill in all the details
#    Given User fill in all the details with Nationality: "American" and continue to Stage 2 Application
#    When User upload files: "download.png"
#    And User enter name: "GuanChoon"
#    And User enter alias: "Chieng"
#    And User input DOB: "24 Oct 2005"
#    And User enter sex: "Male"
#    And User selects travel doc: "International Passport"
#    And User enter Travel document number: "K123456789"
#    And User input Travel document issue date: "03 Feb 2023"
#    And User input Travel document expired date: "02 Feb 2030"
#    And User selects Country Or Region of birth: "Malaysia"
#    And User selects State or Province of birth: "Sarawak"
#    And User selects Country Or Region of origin: "Malaysia"
#    And User selects State or Province of origin: "Sarawak"
#    And User enter address: "No.123, Jalan Lily, 97000 Bintulu, Sarawak, Malaysia"
#    And User enter religion: "Christian"
#    And User enter marital status: "Single"
#    And User enter mobile number: "01115823222"
#    And User enter email: "chienggc9387@gmail.com"
#    And User enter race: "Malay"
#    Then Verified candidate particular is filled successfully
#
#  Scenario: US003 Fill in Stage 1 & 2, then back to stage 1 edit fin num, and back to stage 2 verify previous input remain in stage 2
#    Given User fill in all the details with Nationality: "American" and continue to Stage 2 Application
#    When User upload files: "download.png"
#    And User enter name: "GuanChoon"
#    And User enter alias: "Chieng"
#    And User input DOB: "24 Oct 2005"
#    And User enter sex: "Male"
#    And User selects travel doc: "International Passport"
#    And User enter Travel document number: "K123456789"
#    And User input Travel document issue date: "03 Feb 2023"
#    And User input Travel document expired date: "02 Feb 2030"
#    And User selects Country Or Region of birth: "Malaysia"
#    And User selects State or Province of birth: "Sarawak"
#    And User selects Country Or Region of origin: "Malaysia"
#    And User selects State or Province of origin: "Sarawak"
#    And User enter address: "No.123, Jalan Lily, 97000 Bintulu, Sarawak, Malaysia"
#    And User enter religion: "Christian"
#    And User enter marital status: "Single"
#    And User enter mobile number: "01115823222"
#    And User enter email: "chienggc9387@gmail.com"
#    And User enter race: "Malay"
#    Then Verified candidate particular is filled successfully
#    When User navigate to stage one to Edit Fin Number
#    And User edits for Fin Number: "G3000014K"
#    Then Verified edited fin:"G3000014K" showing successfully and back to Stage 2
#    And Verified details previously entered in Stage 2 able to show up correctly
#
#  Scenario: US009 Fill in Stage 2 details and proceed without optional field (Mobile Num and Alias)
#    Given User fill in all the details with Nationality: "American" and continue to Stage 2 Application
#    When User upload files: "download.png"
#    And User enter name: "GuanChoon"
#    And User input DOB: "24 Oct 2005"
#    And User enter sex: "Male"
#    And User selects travel doc: "International Passport"
#    And User enter Travel document number: "K123456789"
#    And User input Travel document issue date: "03 Feb 2023"
#    And User input Travel document expired date: "02 Feb 2030"
#    And User selects Country Or Region of birth: "Malaysia"
#    And User selects State or Province of birth: "Sarawak"
#    And User selects Country Or Region of origin: "Malaysia"
#    And User selects State or Province of origin: "Sarawak"
#    And User enter address: "No.123, Jalan Lily, 97000 Bintulu, Sarawak, Malaysia"
#    And User enter religion: "Christian"
#    And User enter marital status: "Single"
#    And User enter email: "chienggc9387@gmail.com"
#    And User enter race: "Malay"
#    Then Verified candidate particular is filled successfully
#
#  Scenario: US010 Ensure Stage 2 will have extra field and able to input for fields when Nationality is malaysian
#    Given User fill in all the details with Nationality: "Malaysian" and continue to Stage 2 Application
#    When User upload files: "download.png"
#    And User enter name: "GuanChoon"
#    And User enter alias: "Chieng"
#    And User input DOB: "24 Oct 2005"
#    And User enter sex: "Male"
#    And User selects Country Or Region of birth: "Malaysia"
#    And User selects State or Province of birth: "Sarawak"
#    And User enter New Malaysian identity card number: "961024135501"
#    And User selects Malaysian identity card colour: "Blue"
#    Then verified Malaysian IC and IC's color able to show and input
#
#  Scenario: US011 Navigate to Stage 2 page, and upload 2 files at the same time
#    Given User fill in all the details with Nationality: "American" and continue to Stage 2 Application
#    When User upload files: "download.png,choon.png"
#    Then Verified "multiple files" is not allowed for upload
#
#  Scenario: US012 Navigate to Stage 2 page, and verify when age > 25 and < 18, will prompt error
#    Given User fill in all the details with Nationality: "American" and continue to Stage 2 Application
#    When User input DOB: "03 Feb 2025"
#    Then Verified only 18 to 25 age is allowed
#    When User input DOB: "03 Feb 1990"
#    Then Verified only 18 to 25 age is allowed
#
#  Scenario: US0013 Navigate to Stage 2 page, and verify when age > 25 and < 18, will prompt error
#    Given User fill in all the details with Nationality: "American" and continue to Stage 2 Application
#    When User input Travel document issue date: "03 Feb 2023"
#    And User input Travel document expired date: "02 Feb 2023"
#    Then Verified only current or future date is allowed
#
#  Scenario Outline:: US0014 Unable Upload invalid file format
#    Given User fill in all the details with Nationality: "American" and continue to Stage 2 Application
#    When User upload files: "<fileName>"
#    Then Verified "invalid file format" is not allowed for upload
#    Examples:
#      | fileName     |
#      | git.exe      |
#      | git.gif |
