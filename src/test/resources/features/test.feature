@Homepage
Feature: Check the different functionality

  Background: 
    Given User Navigated to URL

  @urlcheck
  Scenario: Application URL Redirection
    When User navigate to home page
    Then user get redirected to  "http://automationpractice.com/index.php"

  @logo
  Scenario: Application Logo Visibility
    When User navigate to home page
    Then Logo is displayed
    And width of logo is 350 and height is 99

  @Search
  Scenario: Application Search Functionality
    When search a product with name "T-shirt"
    Then search result contain "T-shirt" as text

  @TwitterHandle
  Scenario: Application Socail Media Handles Validation
    When User click on twitter link
    And User navigated to new window
    Then url contain "seleniumfrmwrk"
    And Twitter account name is "Selenium Framework"

  @Application
  Scenario: product main category list validation
    When User navigate to home page
    Then main product category count is 3
    Then  "<Product>" the displayed three categories is as shown below
   Examples: 
	| Product |
	| WOMEN |
	| DRESSES |
	| T-SHIRTS | 
