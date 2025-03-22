package cucumber.stepDefinitions;

import cucumber.factory.BaseClass;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.InventoryPage;
import pages.LogInPage;

public class LoginSteps {

    LogInPage logInPage;
    InventoryPage inventoryPage;

    @Given("user is on the saucedemo login page")
    public void user_is_on_the_saucedemo_login_page() {
        logInPage = new LogInPage(BaseClass.getDriver());

    }

    @When("the user enters user name {string} and password {string}")
    public void the_user_enters_user_name_and_password(String userName, String password) {
        logInPage.setUserName(userName);
        logInPage.setPassword(password);

    }

    @And("the user clicks on Login button")
    public void theUserClicksOnLoginButton() {
        inventoryPage = logInPage.clickLogInBtn();
    }

    @Then("the user should see the dashboard")
    public void the_user_should_see_the_dashboard() {
        inventoryPage.getCurrentURL();
        System.out.println(inventoryPage.getCurrentURL());
        Assert.assertEquals(inventoryPage.getCurrentURL(),"https://www.saucedemo.com/inventory.html",
                "Login failed: User was not redirected to the inventory page. Current URL: "
                        + logInPage.getCurrentURL());
    }

    @Then("the error message {string} should be displayed")
    public void theErrorMessageShouldBeDisplayed(String message) {
        Assert.assertEquals(logInPage.fetchErrorMessage(),
                message,"" +
                        "Error message is incorrect when logging in with an invalid username.");
        Assert.assertEquals(logInPage.getCurrentURL(),"https://www.saucedemo.com/",
                "User should remain on the login page after failed login attempt.");
    }


}
