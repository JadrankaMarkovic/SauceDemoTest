@e2e
Feature: End-to-End Shopping Flow

  Scenario: Purchase all products, complete checkout, and verify order confirmation
    Given the user navigates to the SauceDemo page
    And the user logs in with username "standard_user" and password "secret_sauce"
    When the user adds the following items to the cart
      | Sauce Labs Backpack                |
      | Sauce Labs Bike Light              |
      | Sauce Labs Bolt T-Shirt            |
      | Sauce Labs Fleece Jacket           |
      | Sauce Labs Onesie                  |
      | Test.allTheThings() T-Shirt (Red)  |
    Then the cart badge should display 6 items
    When the user opens the cart
    Then the cart should contain the following items
      | Sauce Labs Backpack                |
      | Sauce Labs Bike Light              |
      | Sauce Labs Bolt T-Shirt            |
      | Sauce Labs Fleece Jacket           |
      | Sauce Labs Onesie                  |
      | Test.allTheThings() T-Shirt (Red)  |
    When the user proceeds to checkout
    And the user enters the checkout details
    And the user completes the purchase
    Then the order confirmation message "Thank you for your order!" should be displayed