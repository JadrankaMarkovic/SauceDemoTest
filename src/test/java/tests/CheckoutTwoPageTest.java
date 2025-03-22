package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutOnePage;
import pages.CheckoutTwoPage;
import pages.InventoryPage;
import utilities.JavaFaker;

public class CheckoutTwoPageTest extends BaseTest{

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
    public void testCheckoutTwoPage(){
        Assert.assertEquals(checkoutTwoPage.getItemByName(itemName1).getText(), itemName1,
                "Test failed: Expected item name: " + itemName1 );
        Assert.assertEquals(checkoutTwoPage.getItemByName(itemName2).getText(), itemName2,
                "Test failed: Expected item name: " + itemName2 );
        Assert.assertEquals(checkoutTwoPage.getNumOfItemsInCart(), 2,
                "Test failed: Expected number of item in cart is 2");
        Assert.assertEquals(checkoutTwoPage.getFinalPrice(), checkoutTwoPage.calculateFinalPrice(),
                "Test failed: Calculated final price " + checkoutTwoPage.calculateFinalPrice() +
                        "does not match the displayed final price " + checkoutTwoPage.getFinalPrice());

    }

    @Test(groups = {"Master", "Sanity"})
    public void testCheckoutTwoPageAndAddOneItem(){
        checkoutTwoPage.clickCancel();
        String itemName3 = "Sauce Labs Backpack";
        inventoryPage.addItemToCart(itemName3);
        String firstName = faker.getFirstName();
        String lastName = faker.getLastName();
        String postalCode = faker.getPostalCode();

        cartPage = inventoryPage.clickCart();
        checkoutOnePage =  cartPage.clickCheckout();
        checkoutOnePage.enterFirstName(firstName);
        checkoutOnePage.enterLastName(lastName);
        checkoutOnePage.enterPostalCode(postalCode);
        checkoutTwoPage = checkoutOnePage.clickCheckout();
        Assert.assertEquals(checkoutTwoPage.getItemByName(itemName3).getText(), itemName3,
                "Test failed: Expected item name: " + itemName3 );
        Assert.assertEquals(checkoutTwoPage.getNumOfItemsInCart(), 3,
                "Test failed: Expected number of item in cart is 3");
        Assert.assertEquals(checkoutTwoPage.getFinalPrice(), checkoutTwoPage.calculateFinalPrice(),
                "Test failed: Calculated final price " + checkoutTwoPage.calculateFinalPrice() +
                        "does not match the displayed final price " + checkoutTwoPage.getFinalPrice());

    }
}
