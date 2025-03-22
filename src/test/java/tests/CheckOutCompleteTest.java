package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import utilities.JavaFaker;

public class CheckOutCompleteTest extends BaseTest{
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutOnePage checkoutOnePage;
    private CheckoutTwoPage checkoutTwoPage;
    private JavaFaker faker = new JavaFaker();
    private String itemName1 ="Sauce Labs Fleece Jacket";
    private String itemName2 ="Sauce Labs Bolt T-Shirt";

    @BeforeClass(groups = {"Master", "Sanity"})
    public void logInPage() {
        logInPage.setUserName(p.getProperty("user"));
        logInPage.setPassword(p.getProperty("password"));
        inventoryPage = logInPage.clickLogInBtn();

        String firstName = faker.getFirstName();
        String lastName = faker.getLastName();
        String postalCode = faker.getPostalCode();
        inventoryPage.addItemToCart(itemName1);
        inventoryPage.addItemToCart(itemName2);
        cartPage = inventoryPage.clickCart();
        checkoutOnePage =  cartPage.clickCheckout();
        checkoutOnePage.enterFirstName(firstName);
        checkoutOnePage.enterLastName(lastName);
        checkoutOnePage.enterPostalCode(postalCode);
        checkoutTwoPage = checkoutOnePage.clickCheckout();
    }

    @Test(groups = {"Master", "Sanity"})
    public void testCheckoutComplete(){
        String msg = "Thank you for your order!";
        CheckoutCompletePage checkoutCompletePage = checkoutTwoPage.clickFinish();
        Assert.assertEquals(checkoutCompletePage.getCheckCompletedMessage(), msg,
                "Test failed: Expected completion message should be: " + msg);
    }
}
