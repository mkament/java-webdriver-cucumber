@unitconverters

Feature: Unit Converters functionality verifications
#Can click on Temperature, Weight, Length, etc.
#Can set from which Unit and to which Unit
#Can set any From field value and verify any result in To value

  @unitconverters1
    # Temperature: 54 Fahrenheit to Celsius result is 12.2
    Scenario Outline: Verify convertion of various units
      Given I open "converter" page
      When I navigate to "<unit>" tab
      And I enter "<quantity>" and "<from>" and also "<to>" info
      Then I verify that the expected result is "<expected>"
      Examples:
  | unit        | quantity | from       | to        | expected|
  |Temperature  | 54       |Fahrenheit  | Celsius   |12.2     |
  |Weight       | 170      |Pound       |  Kilogram |77       |
  |Length       | 50       |Mile        | Kilometer |80.4     |

#
#  @unitconverters1
#    # Weight: 170 Pound to Pound is 77
#  Scenario: Verify ability to convert weight units
#    Given I open "converter" page
#    When I navigate to "Weight" tab
#    And I enter "170" and "Pound" and also "Kilogram" info
#    Then I verify that the expected result is "77"
#
#  @unitconverters1
#    # Length: 50 Mile to Kilometer is 80.4
#  Scenario: Verify ability to convert length units
#    Given I open "converter" page
#    When I navigate to "Length" tab
#    And I enter "50" and "Mile" and also "Kilometer" info
#    Then I verify that the expected result is "80.4"


