Feature: Application (Stage 1).

#  @smoke
#  Scenario: US001 Navigate to Stage 1 page, and fill in all the details
#    Given User navigate to working holiday web page
#    When User proceed to Stage 1 Application Page
#    Then Verified user landed on Stage 1 Application Page Successfully
#    When User selects Nationality: "Malaysian"
#    And User selects Country: "Japan"
#    And User selects Residency Status: "Yes, I am currently working/studying/staying in Singapore"
#    And User inputs for Fin Number: "G3000013M"
#    Then Verified user is "eligible" for WHP
#    And able continue to Stage 2 page

#  @fast
#  Scenario: US004 Select invalid country and user not eligible for a Work Holiday Pass
#    Given User navigate to working holiday web page
#    When User proceed to Stage 1 Application Page
#    Then Verified user landed on Stage 1 Application Page Successfully
#    When User selects Nationality: "Malaysian"
#    And User selects Country: "Others"
#    Then Verified user is "not eligible" for WHP

#  @test
#  Scenario: US005 Proceed stage 2 with worked/studied/stayed in Singapore in the past, and remember fin number
#    Given User navigate to working holiday web page
#    When User proceed to Stage 1 Application Page
#    Then Verified user landed on Stage 1 Application Page Successfully
#    When User selects Nationality: "Malaysian"
#    And User selects Country: "Japan"
#    And User selects Residency Status: "Yes, I have worked/studied/stayed in Singapore in the past"
#    And User selects "remember" for Fin Number
#    And User remembers and inputs for Fin Number: "G3000013M"
#    Then Verified user is "eligible" for WHP
#    And able continue to Stage 2 page

#  Scenario: US006 Proceed stage 2 with worked/studied/stayed in Singapore in the past, and not remember fin number
#    Given User navigate to working holiday web page
#    When User proceed to Stage 1 Application Page
#    Then Verified user landed on Stage 1 Application Page Successfully
#    When User selects Nationality: "Malaysian"
#    And User selects Country: "Japan"
#    And User selects Residency Status: "Yes, I have worked/studied/stayed in Singapore in the past"
#    And User selects "Not remember" for Fin Number
#    Then Verified user is "eligible" for WHP
#    And able continue to Stage 2 page

#  Scenario: US007 Proceed stage 2 without worked/studied/stayed in Singapore in the past by select No
#    Given User navigate to working holiday web page
#    When User proceed to Stage 1 Application Page
#    Then Verified user landed on Stage 1 Application Page Successfully
#    When User selects Nationality: "Malaysian"
#    And User selects Country: "Japan"
#    And User selects Residency Status: "No"
#    Then Verified user is "eligible" for WHP
#    And able continue to Stage 2 page
#
#  Scenario: US008 Proceed stage 2 without worked/studied/stayed in Singapore But have Fin issue by Singapore
#    Given User navigate to working holiday web page
#    When User proceed to Stage 1 Application Page
#    Then Verified user landed on Stage 1 Application Page Successfully
#    When User selects Nationality: "Malaysian"
#    And User selects Country: "Japan"
#    And User selects Residency Status: "No, but I have a Foreign Identification Number (FIN) issued by Singapore"
#    And User inputs for Fin Number: "G3000013M"
#    Then Verified user is "eligible" for WHP
#    And able continue to Stage 2 page


