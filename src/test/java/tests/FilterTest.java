package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.InventoryPage;

import java.util.Collections;
import java.util.List;

public class FilterTest extends BaseTest{
    private InventoryPage inventoryPage;
    List<String> itemsNameCollected;
    List<Double> allPricesCollected;

    @BeforeClass(groups = {"Master", "Sanity"})
    public void logInPage() {
        logInPage.setUserName(p.getProperty("user"));
        logInPage.setPassword(p.getProperty("password"));
        inventoryPage = logInPage.clickLogInBtn();
        itemsNameCollected = inventoryPage.getAllItemsName();
        allPricesCollected = inventoryPage.getAllPrices();
    }

    @Test(groups = {"Master", "Sanity"})
    public void testFilterOptions(){
        List<String> filterOptions = List.of(
                "Name (A to Z)",
                "Name (Z to A)",
                "Price (low to high)",
                "Price (high to low)"
        );
        Assert.assertEquals(inventoryPage.getAllFilterOptions(), filterOptions,
                "Filter options do not match the expected values.");

    }

    @Test(groups = {"Master", "Sanity"})
    public void testSelectedOption(){
        inventoryPage.selectFilter("Price (low to high)");
        Assert.assertEquals(inventoryPage.getSelectedFilter(), "Price (low to high)",
                "Test fails: Expected \"Price (low to high)\", but found different filter option.");

    }

    @Test(groups = {"Master", "Sanity"})
    public void testFilterByNameDescending() {
        itemsNameCollected.sort(Collections.reverseOrder());

        inventoryPage.selectFilter("Name (Z to A)");

        Assert.assertEquals(inventoryPage.getAllItemsName(), itemsNameCollected,
                "Test failed: Items are not sorted by name in descending order.");
    }
    @Test(groups = {"Master", "Sanity"})
    public void testFilterByPriceAscending() {
        Collections.sort(allPricesCollected);

        inventoryPage.selectFilter("Price (low to high)");

        Assert.assertEquals(inventoryPage.getAllPrices(), allPricesCollected,
                "Test failed: Items are not sorted by price in ascending order.");
    }

    @Test(groups = {"Master", "Sanity"})
    public void testFilterByPriceDescending() {
        allPricesCollected.sort(Collections.reverseOrder());

        inventoryPage.selectFilter("Price (high to low)");

        Assert.assertEquals(inventoryPage.getAllPrices(), allPricesCollected,
                "Test failed: Items are not sorted by price in descending order.");
    }
}
