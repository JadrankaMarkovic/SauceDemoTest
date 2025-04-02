# SauceDemoTest

**SauceDemoTest** is an automated testing framework designed to verify the functionality of the SauceDemo web application using **Selenium WebDriver**, **Cucumber** for Behavior-Driven Development (BDD), and the **Page Object Model (POM)** design pattern.
## Technology Used

- **Selenium WebDriver**: For automating web interactions.
- **TestNG Framework**: For managing and executing tests.
- **Java**: Programming language used to write the tests and logic.
- **Maven**: For managing dependencies and automating builds.
- **IntelliJ IDEA**: Integrated Development Environment (IDE) used for development.

## Project Structure
The structure is designed to keep tests and page logic separate, making it easier to maintain and expand. By using the **Page Object Model (POM)**, the logic for interacting with pages is isolated, which improves maintainability. **Cucumber** allows for writing readable tests in Gherkin, making them accessible to both technical and non-technical teams. The **EndToEndTest** class ensures a comprehensive validation of the entire user journey from login to checkout.

- **src/main/java**: Contains page object classes for different pages of the SauceDemo application.
  - **BasePage.java**: Contains common methods for WebDriver initialization and WebDriverWait.
  - **CartPage.java**: Methods and elements related to the shopping cart.
  - **CheckoutCompletePage.java**: Interactions with the checkout completion page.
  - **CheckoutOnePage.java**: Interactions for the first checkout step.
  - **CheckoutTwoPage.java**: Interactions for the second checkout step.
  - **InventoryPage.java**: Methods for interacting with the inventory.
  - **ItemPage.java**: Details for individual item interactions.
  - **LogInPage.java**: Contains methods for logging in.

- **src/test/java**: Contains test classes and step definitions for Cucumber.
  - **LoginTest.java**: Test for login functionality.
  - **InventoryTest.java**: Test for the inventory page.
  - **CartTest.java**: Verifies shopping cart functionality.
  - **CheckoutTest.java**: Verifies the checkout process.
  - **CheckoutCompleteTest.java**: Verifies the checkout completion.
  - **EndToEndTest.java (located in `src/test/java/e2e`)**: An end-to-end test to validate the complete purchasing flow from login to checkout completion.

- **src/test/resources/cucumberFeatures**: Contains Gherkin feature files for Cucumber.
  - **AddToCart.feature**: Scenario for adding items to the cart.
  - **EndToEnd.feature**: Full end-to-end feature for testing the entire purchase process.
  - **LogIn.feature**: Scenario for logging in. 

- **src/test/java/cucumber**: Contains Cucumber-related files.
  - **factory**: Handles WebDriver initialization for Cucumber tests.
  - **stepDefinitions**: Contains the implementations of Cucumber step definitions.
  - **testRunners**: Includes Cucumber test runner classes to execute the tests.
