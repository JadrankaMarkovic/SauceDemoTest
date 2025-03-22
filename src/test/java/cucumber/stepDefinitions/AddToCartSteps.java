package cucumber.stepDefinitions;

import cucumber.factory.BaseClass;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.InventoryPage;
import pages.LogInPage;
import utilities.JavaFaker;

public class AddToCartSteps {
    private LogInPage logInPage;
    private InventoryPage inventoryPage;

    @Given("user log in with correct user name {string} and password {string}")
    public void user_log_in_with_correct_user_name_and_password(String userName, String password) {
        logInPage = new LogInPage(BaseClass.getDriver());
        logInPage.setUserName(userName);
        logInPage.setPassword(password);
        inventoryPage = logInPage.clickLogInBtn();
    }

    @When("user adds item {string} in cart")
    public void user_adds_item_in_card(String item) {
        System.out.println("The name of the item being added is: " + item);
        inventoryPage.addItemToCart(item);
    }

    @Then("user should see that Cart icon shows one item")
    public void user_should_see_that_card_icon_shows_one_item() {
        Assert.assertEquals(inventoryPage.getNumOfItemsInCart(), 1,
                "Test failed: Expected 1 item in the cart, but found a different number.");
    }

    @When("user adds item {string}")
    public void user_adds_item(String item) {
        System.out.println("The name of the second item being added is: " + item);
        inventoryPage.addItemToCart(item);
    }

    @Then("user should see that Cart icon shows two item")
    public void user_should_see_that_card_icon_shows_two_item() {
        Assert.assertEquals(inventoryPage.getNumOfItemsInCart(), 2,
                "Test failed: Expected 2 items in the cart, but found a different number.");
    }
}
