package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;

public class InventoryTest extends BaseTest{

    private InventoryPage inventoryPage;

    @BeforeClass(groups = {"Master", "Sanity"})
    public void logInPage() {
        logInPage.setUserName(p.getProperty("user"));
        logInPage.setPassword(p.getProperty("password"));
        inventoryPage = logInPage.clickLogInBtn();
    }

    @Test(groups = {"Master", "Sanity"})
    public void testAddAndRemoveOneItem(){
        String itemName = "Sauce Labs Bolt T-Shirt";
        inventoryPage.addItemToCart(itemName);
        Assert.assertEquals(inventoryPage.getRemoveTextButton(itemName),"Remove",
                "Test failed: Expected button text to be 'Remove' for item " + itemName +
                        ", but found something else.");
        Assert.assertEquals(inventoryPage.getNumOfItemsInCart(), 1,
                "Test failed: Expected 1 item in the cart, but found a different number.");
        inventoryPage.removeItemFromCart(itemName);
        Assert.assertEquals(inventoryPage.getAddTextButton(itemName),"Add to cart",
                "Test failed: Expected button text to be 'Add to cart' for item " + itemName +
                        ", but found something else.");
        Assert.assertEquals(inventoryPage.getNumOfItemsInCart(), 1,
                "Test failed: Expected 0 items in the cart, but found a different number.");
    }

    @Test(groups = {"Master", "Sanity"})
    public void testAddTwoAndRemoveOneItem(){
        String itemName1 = "Sauce Labs Bolt T-Shirt";
        String itemName2 = "Sauce Labs Onesie";
        inventoryPage.addItemToCart(itemName1);
        Assert.assertEquals(inventoryPage.getRemoveTextButton(itemName1),"Remove",
                "Test failed: Expected button text to be 'Remove' for item " + itemName1 +
                        ", but found something else.");
        inventoryPage.addItemToCart(itemName2);
        Assert.assertEquals(inventoryPage.getRemoveTextButton(itemName1),"Remove",
                "Test failed: Expected button text to be 'Remove' for item " + 2 +
                        ", but found something else.");
        Assert.assertEquals(inventoryPage.getNumOfItemsInCart(), 2,
                "Test failed: Expected 2 item in the cart, but found a different number.");
        inventoryPage.removeItemFromCart(itemName1);
        Assert.assertEquals(inventoryPage.getAddTextButton(itemName1),"Add to cart",
                "Test failed: Expected button text to be 'Add to cart' for item " + itemName1 +
                        ", but found something else.");
        Assert.assertEquals(inventoryPage.getNumOfItemsInCart(), 1,
                "Test failed: Expected 1 item in the cart, but found a different number.");
    }

    @Test(expectedExceptions = IllegalArgumentException.class, groups = {"Master", "Sanity"})
    public void testAddNotExistingItem(){
        String notExistingItem = "noItem";
        inventoryPage.addItemToCart(notExistingItem);
        inventoryPage.clickOnItemByName(notExistingItem);
        inventoryPage.clickOnItemByImage(notExistingItem);
    }

}
