package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.ItemPage;

public class InventoryItemTest extends BaseTest{

    private InventoryPage inventoryPage;

    @BeforeClass(groups = {"Master", "Sanity"})
    public void logInPage() {
        logInPage.setUserName(p.getProperty("user"));
        logInPage.setPassword(p.getProperty("password"));
        inventoryPage = logInPage.clickLogInBtn();
    }

    @Test(priority = 1, groups = {"Master", "Sanity"})
    public void testOpenOneItemPage(){
        String itemName = "Sauce Labs Onesie";
        ItemPage item = inventoryPage.clickOnItemByName(itemName);
        Assert.assertEquals(item.getItemName(),itemName,
                "Test failed: Expected item name: " + itemName);
        Assert.assertEquals(item.getItemsPrice(), 7.99,
                "Test failed: Expected item price is: 7.99");
        item.clickAddRemoveButton();
        Assert.assertEquals(item.getNumOfItemsInCart(), 1,
                "Test failed: Expected number of item in cart is 1");
        item.clickAddRemoveButton();
        Assert.assertEquals(item.getNumOfItemsInCart(), 0,
                "Test failed: Expected number of item in cart is 0");
        item.clickBackToProducts();

    }

    @Test(priority = 2, groups = {"Master", "Sanity"})
    public void testAddFirstItemAndOpenSecondItemPage(){
        String itemName1 ="Sauce Labs Fleece Jacket";
        String itemName2 ="Sauce Labs Bolt T-Shirt";
        inventoryPage.addItemToCart(itemName1);
        Assert.assertEquals(inventoryPage.getNumOfItemsInCart(), 1,
                "Test failed: Expected 1 items in the cart, but found a different number.");
        ItemPage item = inventoryPage.clickOnItemByImage(itemName2);
        Assert.assertEquals(item.getItemName(),itemName2,
                "Test failed: Expected item name: " + itemName2);
        item.clickAddRemoveButton();
        Assert.assertEquals(item.getNumOfItemsInCart(), 2,
                "Test failed: Expected 2 items in the cart, but found a different number.");
    }
}
