package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;

public class LogInTest extends BaseTest{

    @BeforeMethod(groups = {"Master", "Sanity"})
    public void navigateToLoginPage() {
        goLogInPage();
    }

    @Test(groups = {"Master", "Sanity"})
    public void testLogInSuccessful(){

        logInPage.setUserName(p.getProperty("user"));
        logInPage.setPassword(p.getProperty("password"));
        InventoryPage inventoryPage = logInPage.clickLogInBtn();
        Assert.assertEquals(inventoryPage.getCurrentURL(),
                "https://www.saucedemo.com/inventory.html",
                "Login failed: User was not redirected to the inventory page. Current URL: "
                        + inventoryPage.getCurrentURL());
    }

    @Test(groups = {"Master", "Sanity"})
    public void testLogInWithWrongUserName(){
        logInPage.setUserName("abc");
        logInPage.setPassword("secret_sauce");
        Assert.assertEquals(logInPage.fetchErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service","" +
                        "Error message is incorrect when logging in with an invalid username.");
        Assert.assertEquals(logInPage.getCurrentURL(),"https://www.saucedemo.com/",
                "User should remain on the login page after failed login attempt.");
    }

}
