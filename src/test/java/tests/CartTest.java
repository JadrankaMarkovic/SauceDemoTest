package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;

public class CartTest extends BaseTest{

    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeClass(groups = {"Master", "Sanity"})
    public void logInPage() {
        logInPage.setUserName(p.getProperty("user"));
        logInPage.setPassword(p.getProperty("password"));
        inventoryPage = logInPage.clickLogInBtn();
    }

    @Test (priority = 1, groups = {"Master", "Sanity"})
    public void testCartWithTwoItems() {
        String itemName1 ="Sauce Labs Fleece Jacket";
        String itemName2 ="Sauce Labs Bolt T-Shirt";
        inventoryPage.addItemToCart(itemName1);
        inventoryPage.addItemToCart(itemName2);
        cartPage = inventoryPage.clickCart();
        Assert.assertEquals(cartPage.getItemByName(itemName1).getText(), itemName1,
               "Test failed: Expected item name: " + itemName1 );
        Assert.assertEquals(cartPage.getItemByName(itemName2).getText(), itemName2,
                "Test failed: Expected item name: " + itemName2 );
        Assert.assertEquals(cartPage.getNumOfItemsOnPage(), 2,
                "Test failed: Expected to be seen 2 items on cart page");
        Assert.assertEquals(cartPage.getNumOfItemsInCart(), 2,
                "Test failed: Expected number of item in cart is 2");
        cartPage.removeItemFromCart(itemName2);
        Assert.assertEquals(cartPage.getNumOfItemsOnPage(), 1,
                "Test failed: Expected to be seen 1 item on cart page");
        Assert.assertEquals(cartPage.getNumOfItemsInCart(), 1,
                "Test failed: Expected number of item in cart is 1");
        cartPage.removeItemFromCart(itemName1);
        Assert.assertEquals(cartPage.getNumOfItemsOnPage(), 0,
                "Test failed: Expected empty cart page");
        Assert.assertEquals(cartPage.getNumOfItemsInCart(), 0,
                "Test failed: Expected number of item in cart is 0");
        cartPage.clickContinueShopping();
    }


    @Test(priority = 2, groups = {"Master", "Sanity"})
    public void testCartWithThreeItems() {
        String itemName1 ="Sauce Labs Backpack";
        String itemName2 ="Sauce Labs Onesie";
        String itemName3 = "Test.allTheThings() T-Shirt (Red)";
        inventoryPage.addItemToCart(itemName1);
        inventoryPage.clickCart();
        Assert.assertEquals(cartPage.getNumOfItemsOnPage(), 1,
                "Test failed: Expected to be seen 1 item on cart page");
        cartPage.clickContinueShopping();
        inventoryPage.addItemToCart(itemName2);
        inventoryPage.clickCart();
        Assert.assertEquals(cartPage.getNumOfItemsOnPage(), 2,
                "Test failed: Expected to be seen 2 items on cart page");
        Assert.assertEquals(cartPage.getNumOfItemsInCart(), 2,
                "Test failed: Expected number of item in cart is 2");
        cartPage.clickContinueShopping();
        inventoryPage.addItemToCart(itemName3);
        inventoryPage.clickCart();
        Assert.assertEquals(cartPage.getNumOfItemsOnPage(), 3,
                "Test failed: Expected to be seen 3 items on cart page");
        Assert.assertEquals(cartPage.getNumOfItemsInCart(), 3,
                "Test failed: Expected number of item in cart is 3");
    }

}
