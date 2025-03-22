package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = ".inventory_item_name[data-test='inventory-item-name']")
    private List<WebElement> itemsName;

    @FindBy(css = ".inventory_item_price[data-test='inventory-item-price']")
    private List<WebElement> itemsPrice;

    @FindBy(css = ".cart_button")
    private List<WebElement> removeButtons;

    @FindBy(css = ".shopping_cart_link[data-test='shopping-cart-link']")
    private WebElement shoppingCart;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public WebElement getItemByName(String itemName){
        waitForAllVisible(itemsName);
        return itemsName.stream()
                .filter(item ->item.getText().equals(itemName))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Item with name: " +
                        itemName +" does not exist"));
    }

    public int getNumOfItemsOnPage(){
        return itemsName.size();
    }

    public ItemPage clickOnItemByName(String itemName){
    getItemByName(itemName).click();
    return new ItemPage(driver);
    }

    public void removeItemFromCart(String itemName) {
        String id = getFullItemName("remove-", itemName);
        clickOnRemoveBtn(id).click();
    }

    private String getFullItemName(String addRemove, String itemName){
        return addRemove +
                itemName.strip().toLowerCase().replace(" ", "-");
    }

    private WebElement clickOnRemoveBtn(String itemName){
        waitForAllVisible(removeButtons);
        return removeButtons.stream()
                //.filter(item->item.getAttribute("id").equals(itemName))//deprecated getAttribute()
                .filter(item->item.getDomAttribute("id").equals(itemName))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Item with name: " +
                        itemName + "does not exist"));
    }

    public List<Double> getAllPrices() {
        waitForAllVisible(itemsPrice);
        return itemsPrice.stream()
                .map(WebElement::getText)
                .map(priceText -> priceText.replace("$", ""))
                .map(Double::parseDouble)
                //.collect(Collectors.toList());
                .toList();
    }

    public int getNumOfItemsInCart(){
        return waitForVisible(shoppingCart).getText().isEmpty() ? 0 : Integer.parseInt(shoppingCart.getText());
    }

    public InventoryPage clickContinueShopping(){
        waitForClickable(continueShoppingButton).click();
        return new InventoryPage(driver);
    }

    public CheckoutOnePage clickCheckout(){
        waitForClickable(checkoutButton).click();
        return new CheckoutOnePage(driver);
    }
}
