package e2e;

import tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.JavaFaker;

public class EndToEndTest extends BaseTest {

    String itemName  = "Sauce Labs Bolt T-Shirt";
    String itemName2 = "Sauce Labs Bike Light";
    String itemName3 ="Sauce Labs Fleece Jacket";
    private JavaFaker faker = new JavaFaker();
    String firstName = faker.getFirstName();
    String lastName = faker.getLastName();
    String postalCode = faker.getPostalCode();
    String msg = "Thank you for your order!";

    @Test(groups = {"Master", "EndToEnd"})
    public void testEndToEnd(){
        logger.info("Logging in as 'standard_user'");
        logInPage.setUserName("standard_user");
        logInPage.setPassword("secret_sauce");
        InventoryPage inventoryPage = logInPage.clickLogInBtn();
        logger.info("Login successful");

        logger.info("Adding item to cart: Sauce Labs Bolt T-Shirt");
        inventoryPage.addItemToCart(itemName);
        Assert.assertEquals(inventoryPage.getRemoveTextButton(itemName), "Remove",
                "Test failed: Expected button text to be 'Remove' for item " + itemName +
                        ", but found something else.");
        Assert.assertEquals(inventoryPage.getNumOfItemsInCart(), 1,
                "Test failed: Expected 1 item in the cart, but found a different number.");
        logger.info("Item added to cart successfully: Sauce Labs Bolt T-Shirt");

        logger.info("Opening page for item: Sauce Labs Bike Light");
        ItemPage item = inventoryPage.clickOnItemByName(itemName2);
        Assert.assertEquals(item.getItemName(), itemName2,
                "Test failed: Expected item name: " + itemName2);
        Assert.assertEquals(item.getItemsPrice(), 9.99,
                "Test failed: Expected item price is: 9.99");
        logger.info("Adding item to cart: Sauce Labs Bike Light");
        item.clickAddRemoveButton();
        Assert.assertEquals(item.getNumOfItemsInCart(), 2,
                "Test failed: Expected number of items in cart is 2");
        item.clickBackToProducts();
        logger.info("Returned to product listing page");

        logger.info("Removing item from cart: Sauce Labs Bolt T-Shirt");
        inventoryPage.removeItemFromCart(itemName);
        Assert.assertEquals(inventoryPage.getNumOfItemsInCart(), 1,
                "Test failed: Expected 1 item in the cart, but found a different number.");

        logger.info("Adding item to cart: Sauce Labs Fleece Jacket");
        inventoryPage.addItemToCart(itemName3);
        Assert.assertEquals(inventoryPage.getNumOfItemsInCart(), 2,
                "Test failed: Expected number of items in cart is 2");

        logger.info("Reviewing cart contents");
        CartPage cartPage = inventoryPage.clickCart();
        Assert.assertEquals(cartPage.getItemByName(itemName2).getText(), itemName2,
                "Test failed: Expected item name: " + itemName2 );
        Assert.assertEquals(cartPage.getItemByName(itemName3).getText(), itemName3,
                "Test failed: Expected item name: " + itemName3 );
        cartPage.removeItemFromCart(itemName2);
        Assert.assertEquals(cartPage.getNumOfItemsOnPage(), 1,
                "Test failed: Expected to be seen 1 item on cart page");
        Assert.assertEquals(cartPage.getNumOfItemsInCart(), 1,
                "Test failed: Expected number of item in cart is 1");
        logger.info("Cart contents verified");

        logger.info("Initiating checkout with valid data");
        CheckoutOnePage checkoutOnePage =  cartPage.clickCheckout();
        checkoutOnePage.enterFirstName(firstName);
        checkoutOnePage.enterLastName(lastName);
        checkoutOnePage.enterPostalCode(postalCode);
        CheckoutTwoPage checkoutTwoPage = checkoutOnePage.clickCheckout();
        Assert.assertEquals(checkoutTwoPage.getItemByName(itemName3).getText(), itemName3,
                "Test failed: Expected item name: " + itemName3 );
        Assert.assertEquals(checkoutTwoPage.getNumOfItemsInCart(), 1,
                "Test failed: Expected number of item in cart is 1");
        CheckoutCompletePage checkoutCompletePage = checkoutTwoPage.clickFinish();
        Assert.assertEquals(checkoutCompletePage.getCheckCompletedMessage(), msg,
                "Test failed: Expected completion message should be: " + msg);
        logger.info("Checkout completed successfully");
    }
}
