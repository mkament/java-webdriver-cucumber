@quote
Feature: Quote Predefined

  @quote1
  Scenario: Responsive UI behavior
    Given I open url "https://skryabin.com/market/quote.html"
    When I resize window to 1024 and 1000
    Then element with xpath "//b[@id='location']" should be displayed
    And element with xpath "//b[@id='currentDate']" should be displayed
    And element with xpath "//b[@id='currentTime']" should be displayed
    When I resize window to 400 and 614
    Then element with xpath "//b[@id='location']" should not be displayed
    And element with xpath "//b[@id='currentDate']" should not be displayed
    And element with xpath "//b[@id='currentTime']" should not be displayed

  @quote_predefined2
  Scenario: Responsive UI behavior 2
    Given I open url "https://skryabin.com/market/quote.html"
    When I type "Z" into element with xpath "//input[@name='username']"
    And I click on element with xpath "//input[@name='email']"
    Then element with xpath "//label[@id='username-error']" should be displayed
    When I type "Io" into element with xpath "//input[@name='username']"
    Then element with xpath "//label[@id='username-error']" should not be displayed

  @quote_predefined3
  Scenario: Responsive UI behavior 3
    Given I open url "https://skryabin.com/market/quote.html"
    When I type "Zd4324dd" into element with xpath "//input[@name='email']"
    And I type "Z" into element with xpath "//input[@name='username']"
    Then element with xpath "//label[@id='email-error']" should be displayed
    When I type "dmv@onebox.com" into element with xpath "//input[@name='email']"
    And I type "Z" into element with xpath "//input[@name='username']"
    Then element with xpath "//label[@id='email-error']" should not be displayed

  @quote_predefined4
  Scenario: Responsive UI behavior 4
    Given I open url "https://skryabin.com/market/quote.html"
    When I type "" into element with xpath "//input[@id='password']"
    Then element with xpath "//input[@id='confirmPassword']" should be disabled

  @quote_predefined5
  Scenario: Responsive UI behavior 5
    Given I open url "https://skryabin.com/market/quote.html"
    When I click on element with xpath "//input[@id='name']"
    And I click on element with xpath "//label[contains(text(),'First Name')]"
    Then element with xpath "//label[contains(text(),'First Name')]" should be displayed
    And I type "Jan" into element with xpath "//input[@id='firstName']"
    And I type "Van" into element with xpath "//input[@id='middleName']"
    And I type "Dork" into element with xpath "//input[@id='lastName']"
    And I click on element with xpath "//span[contains(text(),'Save')]"
    And I wait for 2 sec
    Then element with xpath "//input[@id='name']" should have attribute "value" as "Jan Van Dork"


  @quote_predefined6
  Scenario: Responsive UI behavior 6
    Given I open url "https://skryabin.com/market/quote.html"
    When I click on element with xpath "//button[@id='formSubmit']"
    And I wait for 2 sec
    Then element with xpath "//label[@for='agreedToPrivacyPolicy']" should have text as "- Must check!"
    When I click on element with xpath "//input[@name='agreedToPrivacyPolicy'][@type='checkbox']"
    Then element with xpath "//label[@for='agreedToPrivacyPolicy']" should not have text as "- Must check!"
    And I wait for 2 sec


  @quote_predefined7
  Scenario: Responsive UI behavior 7
    Given I open url "https://skryabin.com/market/quote.html"
    And I type "+13433243488459" into element with xpath "//input[@name='phone']"
    And I click on element with xpath "//select[@name='countryOfOrigin']"
    And I click on element with xpath "//option[contains(text(),'Belarus')]"
    And I click on element with xpath "//label[@class='checkbox_radio']/input[@value='male']"
    And I click on element with xpath "//input[@name='allowedToContact']"
    Then I type "55 Mueller Drive, San Juan, CA 99321" into element with xpath "//textarea[@id='address']"
    And I click on element with xpath "//option[contains(text(),'Toyota')]"
    And I scroll to the element with xpath "//button[@id='thirdPartyButton']" with offset 1
    And I click on element with xpath "//button[@id='thirdPartyButton']"
    And I accept alert
    Then I type "01/01/1901" into element with xpath "//input[@id='dateOfBirth']"
    And I click on element with xpath "//input[@name='email']"
      #just to make calendar dialog box disappear.
    Then I wait for 2 sec
    When I type "dmv@onebox.com" into element with xpath "//input[@name='email']"
    Then I wait for 5 sec

  @quote_predefined8
  Scenario: Responsive UI behavior 8
    Given I open url "https://skryabin.com/market/quote.html"
    When I type "Zond" into element with xpath "//input[@name='username']"
    And I type "dmv@onebox.com" into element with xpath "//input[@name='email']"
    And I type "ZPT1234~" into element with xpath "//input[@id='password']"
    And I type "ZPT1234~" into element with xpath "//input[@id='confirmPassword']"
    And I type "King Kong" into element with xpath "//input[@id='name']"
    And I type "+13433243488459" into element with xpath "//input[@name='phone']"
    And I type "01/01/1901" into element with xpath "//input[@id='dateOfBirth']"
    And I click on element with xpath "//select[@name='countryOfOrigin']"
    And I click on element with xpath "//option[contains(text(),'Belarus')]"
    And I click on element with xpath "//input[@name='allowedToContact']"
    And I click on element with xpath "//label[@class='checkbox_radio']/input[@value='male']"
    And I click on element with xpath "//input[@name='allowedToContact']"
    And I type "55 Mueller Drive, San Juan, CA 99321" into element with xpath "//textarea[@id='address']"
    And I click on element with xpath "//option[contains(text(),'Toyota')]"
    And I scroll to the element with xpath "//button[@id='thirdPartyButton']" with offset 1
    And I click on element with xpath "//button[@id='thirdPartyButton']"
    Then I accept alert
    And I type "01/01/1901" into element with xpath "//input[@id='dateOfBirth']"
    And I click on element with xpath "//input[@name='agreedToPrivacyPolicy'][@type='checkbox']"
    And I click on element with xpath "//button[@id='formSubmit']"
    Then element with xpath "//b[@name='password']" should have text as "[entered]"
    Then I wait for 5 sec
    And element with xpath "//b[@name='name']" should have text as "King Kong"
    And element with xpath "//b[@name='location']" should contain text "Los Altos"
    And element with xpath "//b[@name='carMake']" should contain text "Toyota"
#
#  @quote
#  Feature: Quote OOP

  @quote1
  Scenario: Required fields for quote oop
    Given I open "quote" page oop
    When I fill out required fields for "user" oop
    And I submit the form oop
    Then I verify required fields for "user" oop

  @quote2
  Scenario: Required and optional fields for quote oop
    Given I open "quote" page oop
    When I fill out required fields for "user" oop
    When I fill out optional fields for "user" oop
    And I submit the form oop
    Then I verify required fields for "user" oop
    Then I verify optional fields for "user" oop

  @quote3
  Scenario: Required fields test oop
    Given I open "quote" page oop
    And I submit the form oop
    Then I see "username" error message "This field is required."
    And I see "email" error message "This field is required."
    And I see "password" error message "This field is required."
    And I see "name" error message "This field is required."
    And I see "agreedToPrivacyPolicy" error message "- Must check!"

  @quote4
  Scenario: Market username test oop
    Given I open "quote" page oop
    When I fill out "username" field with "a"
    And I submit the form oop
    Then I see "username" error message "Please enter at least 2 characters."
    When I fill out "username" field with "ab"
    Then I don't see "username" error message

  @quote5
  Scenario: Market email test oop
    Given I open "quote" page oop
    When I fill out "email" field with "john"
    And I submit the form oop
    Then I see "email" error message "Please enter a valid email address."
    When I fill out "email" field with "john@example.com"
    Then I don't see "email" error message

  @quote6
  Scenario: Market passwords test oop
    Given I open "quote" page oop
    When I fill out "password" field with "1234"
    And I submit the form oop
    Then I see "password" error message "Please enter at least 5 characters."
    When I fill out "password" field with "12345"
    Then I don't see "password" error message
    When I fill out "confirmPassword" field with "1234"
    And I submit the form oop
    Then I see "confirmPassword" error message "Please enter at least 5 characters."
    When I fill out "confirmPassword" field with "54321"
    Then I see "confirmPassword" error message "Passwords do not match!"
    When I fill out "confirmPassword" field with "12345"
    Then I don't see "confirmPassword" error message

  @quote7
  Scenario: Market name test oop
    Given I open "quote" page oop
    When I fill out name field with first name "John" and last name "Doe"
    Then I verify "name" field value "John Doe"
    When I fill out name field with first name "John", middle name "Richard", last name "Doe"
    Then I verify "name" field value "John Richard Doe"