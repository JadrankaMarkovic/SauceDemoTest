@logIn
Feature: Saucedemo login feature

  Background: Open Saucedemo Demo page

  @login_in
  Scenario: Login with correct user name and password
    Given user is on the saucedemo login page
    When the user enters user name "standard_user" and password "secret_sauce"
    And the user clicks on Login button
    Then the user should see the dashboard

  @login_in_incorrect
  Scenario: Login with incorrect user name
    Given user is on the saucedemo login page
    When the user enters user name "abc" and password "secret_sauce"
    And the user clicks on Login button
    Then the error message "Epic sadface: Username and password do not match any user in this service" should be displayed