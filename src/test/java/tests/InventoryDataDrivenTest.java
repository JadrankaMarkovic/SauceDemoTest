package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.InventoryPage;
import utilities.DataProviders;

public class InventoryDataDrivenTest extends BaseTest {

    private InventoryPage inventoryPage;
    int numOfItems;

    @BeforeClass(groups = {"Master", "DataDriven"})
    public void logInPage() {
        logInPage.setUserName(p.getProperty("user"));
        logInPage.setPassword(p.getProperty("password"));
        inventoryPage = logInPage.clickLogInBtn();
        numOfItems = 0;
    }

    @Test(dataProvider = "userInventoryItemsProvider", dataProviderClass = DataProviders.class,
            groups = {"Master", "DataDriven"})
    public void testAddAllItemsToCart(int id, String name, String description, double price) {
        inventoryPage.addItemToCart(name);
        Assert.assertEquals(inventoryPage.getItemByName(name).getText(), name,
                "Test failed: Expected item name is " + name);
        Assert.assertEquals(inventoryPage.getAllPrices().get(id), price,
                "Test failed: Expected price is " + price);
        Assert.assertEquals(inventoryPage.getRemoveTextButton(name), "Remove",
                "Test failed for item: " + name + ": Expected 'Remove' button after adding to cart.");
        numOfItems++;
        Assert.assertEquals(inventoryPage.getNumOfItemsInCart(), numOfItems);
    }

    @Test(dataProvider = "userInventoryItemsProvider", dataProviderClass = DataProviders.class,
            groups = {"Master", "DataDriven"})
    public void testRemoveAllItemsFromCart(int id, String name, String description, double price) {
        inventoryPage.removeItemFromCart(name);
        Assert.assertEquals(inventoryPage.getAddTextButton(name), "Add to cart",
                "Test Failed: Expected for item: " + name + "expected 'Add to cart' button after " +
                        "remove item from cart ");
        numOfItems--;
        Assert.assertEquals(inventoryPage.getNumOfItemsInCart(), numOfItems);
    }

}
