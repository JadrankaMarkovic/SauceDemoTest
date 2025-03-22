package cucumber.stepDefinitions;

import cucumber.factory.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.*;
import utilities.JavaFaker;

import java.util.List;

public class EndToEnd {

    private LogInPage logInPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutOnePage checkoutOnePage;
    private CheckoutTwoPage checkoutTwoPage;
    private CheckoutCompletePage checkoutCompletePage;
    private JavaFaker faker = new JavaFaker();

    @Given("the user navigates to the SauceDemo page")
    public void the_user_navigates_to_the_sauce_demo_page() {
        logInPage = new LogInPage(BaseClass.getDriver());

    }

    @And("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String userName, String password) {
        logInPage.setUserName(userName);
        logInPage.setPassword(password);
        inventoryPage = logInPage.clickLogInBtn();
    }

    @When("the user adds the following items to the cart")
    public void the_user_adds_the_following_items_to_the_cart(DataTable dataTable) {
        List<String> itemNames = dataTable.asList(String.class);
        for (String item : itemNames) {
            inventoryPage.addItemToCart(item);
        }
    }

    @Then("the cart badge should display {int} items")
    public void the_cart_badge_should_display_items(Integer itemsNum) {
        Assert.assertEquals(inventoryPage.getNumOfItemsInCart(), itemsNum,
                "Test failed: Expected " + itemsNum +
                        " items in the cart, but found a different number.");
    }

    @When("the user opens the cart")
    public void the_user_opens_the_cart() {
        cartPage = inventoryPage.clickCart();

    }

    @Then("the cart should contain the following items")
    public void the_cart_should_contain_the_following_items(DataTable dataTable) {
        List<String> itemNames = dataTable.asList(String.class);
        for (String itemName : itemNames) {
            Assert.assertEquals(cartPage.getItemByName(itemName).getText(), itemName,
                    "Test failed: Expected item name: " + itemName);
        }
    }

    @When("the user proceeds to checkout")
    public void the_user_proceeds_to_checkout() {
        checkoutOnePage = cartPage.clickCheckout();
    }

    @And("the user enters the checkout details")
    public void the_user_enters_the_checkout_details() {
        String firstName = faker.getFirstName();
        String lastName = faker.getLastName();
        String postalCode = faker.getPostalCode();
        checkoutOnePage.enterFirstName(firstName);
        checkoutOnePage.enterLastName(lastName);
        checkoutOnePage.enterPostalCode(postalCode);
    }

    @When("the user completes the purchase")
    public void the_user_completes_the_purchase() {
        // Write code here that turns the phrase above into concrete actions
        checkoutTwoPage = checkoutOnePage.clickCheckout();
    }

    @Then("the order confirmation message {string} should be displayed")
    public void the_order_confirmation_message_should_be_displayed(String message) {
        checkoutCompletePage = checkoutTwoPage.clickFinish();
        Assert.assertEquals(checkoutCompletePage.getCheckCompletedMessage(), message,
                "Test failed: Expected completion message should be: " + message);
    }

}
