@add2Cart
Feature:Add items in card and remove them

  Background: Go to SauceDemo cite

  Scenario: Add 2 item in card
    Given user log in with correct user name "standard_user" and password "secret_sauce"
    When user adds item "Sauce Labs Bolt T-Shirt" in cart
    Then user should see that Cart icon shows one item
    When user adds item "Sauce Labs Onesie"
    Then user should see that Cart icon shows two item
