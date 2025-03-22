package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutOnePage;
import pages.InventoryPage;
import utilities.JavaFaker;

public class CheckoutOnePageTest extends BaseTest{

    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private JavaFaker faker = new JavaFaker();

    @BeforeClass(groups = {"Master", "Sanity"})
    public void logInPage() {
        logInPage.setUserName(p.getProperty("user"));
        logInPage.setPassword(p.getProperty("password"));
        inventoryPage = logInPage.clickLogInBtn();
        String itemName1 ="Sauce Labs Fleece Jacket";
        String itemName2 ="Sauce Labs Bolt T-Shirt";
        inventoryPage.addItemToCart(itemName1);
        inventoryPage.addItemToCart(itemName2);
        cartPage = inventoryPage.clickCart();

    }

    @Test(groups = {"Master", "Sanity"})
    public void testCheckoutPage(){
        String firstName = faker.getFirstName();
        String lastName = faker.getLastName();
        String postalCode = faker.getPostalCode();
        CheckoutOnePage checkoutOnePage =  cartPage.clickCheckout();
        checkoutOnePage.enterFirstName(firstName);
        checkoutOnePage.enterLastName(lastName);
        checkoutOnePage.enterPostalCode(postalCode);
        Assert.assertEquals(checkoutOnePage.getFirstNameFromBox(), firstName,
                "Test failed: We should expect first name: " + firstName);
        Assert.assertEquals(checkoutOnePage.getLastNameFromBox(), lastName,
                "Test failed: We should expect last name: " + lastName);
        Assert.assertEquals(checkoutOnePage.getPostalCodeFromBox(), postalCode,
                "Test failed: We should expect postal Code: " + postalCode);
        Assert.assertEquals(checkoutOnePage.getNumOfItemsInCart(), 2,
                "Tested failed: Expected two items in cart");
        checkoutOnePage.clickCancelShopping();
        cartPage.removeItemFromCart("Sauce Labs Bolt T-Shirt");
        cartPage.clickCheckout();
        Assert.assertEquals(checkoutOnePage.getNumOfItemsInCart(), 1,
                "Tested failed: Expected two items in cart");
    }

}
