@predefined
Feature: Smoke steps

  @predefined1
  Scenario: Predefined steps for Google
    Given I open url "https://google.com"
    Then I should see page title as "Google"
    Then element with xpath "//input[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='q']"
    Then I click on element using JavaScript with xpath "(//input[@name='btnK'])[1]"
    Then I wait for element with xpath "//*[@id='res']" to be present
    Then element with xpath "//*[@id='res']" should contain text "Cucumber"

  @predefined1
  Scenario: Predefined steps for Yahoo
    Given I open url "https://yahoo.com"
    Then I should see page title as not "Blah"
    Then element with xpath "//input[@id='uh-search-box']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='uh-search-box']"
    Then I click on element using JavaScript with xpath "//button[@id='uh-search-button']"
    Then I wait for element with xpath "//ol[contains(@class, 'mb')]" to be present
    Then element with xpath "//ol[contains(@class, 'mb')]" should contain text "cucumber"

  @predefined2
  Scenario: Predefined steps for Duckduckgo
    Given I open url "https://duckduckgo.com"
    Then I should see page title contains "DuckDuckGo"
    Then element with xpath "//input[@id='search_form_input_homepage']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='search_form_input_homepage']"
    Then I click on element using JavaScript with xpath "//input[@id='search_button_homepage']"
    Then I wait for element with xpath "//div[@class='results--main']" to be present
    Then element with xpath "//div[@class='results--main']" should contain text "cucumber"

  @predefined3
  Scenario: Predefined steps for Bing
    Given I maximize window
    Given I open url "https://bing.com"
    Then I wait for 1 sec
    When I should see page title does not contain "Trump"
    Then I type "Behavior Driven Development" into element with xpath "//input[@aria-label='Enter your search term']"
    Then I click on element using JavaScript with xpath "//input[@id='sb_form_go']"
    Then I wait for element with xpath "//div[@id='b_content']" to be present
    Then element with xpath "//div[@id='b_content']" should contain text "BDD"

  @predefined4
  Scenario: Predefined steps for Gibiru
    Given I maximize window
    Given I open url "https://gibiru.com"
    When I should see page title contains "Gibiru"
    Then I type "Behavior Driven Development" into element with xpath "//input[@id='q']"
    Then I click on element using JavaScript with xpath "//i[@class='fas fa-search new']"
    Then I wait for element with xpath "//div[@class='gsc-wrapper']" to be present
    Then element with xpath "//div[@class='gsc-wrapper']" should contain text "BDD"

  @predefined5
  Scenario: Predefined steps for Swisscows
    Given I open url "https://www.swisscows.com/"
    When I should see page title contains "Swisscows"
    When I wait for element with xpath "//input[@name='query']" to be present
    Then I take screenshot
    Then I type "Behavior Driven Development" into element with xpath "//input[@name='query']"
    Then I click on element with xpath "//button[@class='search-submit']//*[@class='icon']"
    And I wait for 1 sec
    Then I wait for element with xpath "//div[@class='web-results']" to be present
    Then element with xpath "//article/a/h2[contains(text(),'BDD:')]/../p" should contain text "BDD"

  @predefined6
  Scenario: Random steps for Startpage
    Given I open url "https://www.startpage.com"
    Then I should see page title contains "Startpage"
    And I should see page title does not contain "Trump"
    Then I click on element with xpath "//input[@id='q']"
    Then I type "Behavior Driven Development" into element with xpath "//input[@id='q']"
    And I click on element with xpath "//div[@class='ico']"
    Then I click on element with xpath "//button[contains(text(),'Images')]"
    Then element with xpath "//div[@id='images-filter-size']" should be present
    Then element with xpath "//button[contains(text(),'Videos')]" should be displayed

  @predefined7
  Scenario: Convoluted execution directions for Yandex
    Given I open url "https://yandex.com/"
    Then I type "Behavior Driven Development" into element with xpath "//input[@id='text']"
    And I click on element with xpath "//button[@type='submit']"
    Then element with xpath "//span[@class='input__clear mini-suggest__input-clear input__clear_visibility_visible']" should be displayed
    When I click on element with xpath "//span[@class='input__clear mini-suggest__input-clear input__clear_visibility_visible']"
    And I wait for 2 sec
    Then element with xpath "//span[@class='input__clear mini-suggest__input-clear input__clear_visibility_visible']" should not be present

  @predefined8
  Scenario: Predefined steps for Boardreader
    Given I open url "http://boardreader.com"
    Then I should see page title as "Boardreader - Forum Search Engine"
    Then element with xpath "//input[@id='title-query']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='title-query']"
    Then I click on element with xpath "//button[@id='title-submit']//img"
    And I wait for 3 sec
    Then element with xpath "//button[@value='Go']" should be present

  @predefined9
  Scenario: Predefined steps for Ecosia
    Given I open url "https://www.ecosia.org/"
    Then I maximize window
    Then I should see page title as "Ecosia - the search engine that plants trees"
    When I type "Behavior Driven Development" into element with xpath "//input[@type='search']"
    Then I mouse over element with xpath "//button[@type='submit']"
    Then I click on element with xpath "//button[@type='submit']"
    Then element with xpath "//span[@class='result-count']" should contain text "00,000"