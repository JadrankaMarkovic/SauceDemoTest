package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutTwoPage extends BasePage{

    public CheckoutTwoPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = ".inventory_item_name[data-test='inventory-item-name']")
    private List<WebElement> itemsName;

    @FindBy(css = ".inventory_item_price[data-test='inventory-item-price']")
    private List<WebElement> itemsPrice;

    @FindBy(css = ".summary_subtotal_label")
    private WebElement finalPrice;

    @FindBy(css = ".shopping_cart_link[data-test='shopping-cart-link']")
    private WebElement shoppingCart;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    public WebElement getItemByName(String itemName){
        waitForAllVisible(itemsName);
        return itemsName.stream()
                .filter(item ->item.getText().equals(itemName))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Item with name: " +
                        itemName +" does not exist"));
    }

    public int getNumOfItemsOnPage(){
        return waitForAllVisible(itemsName).size();
    }

    public ItemPage clickOnItemByName(String itemName){
        getItemByName(itemName).click();
        return new ItemPage(driver);
    }

    public List<Double> getAllPrices() {
        waitForAllVisible(itemsPrice);
        return itemsPrice.stream()
                .map(WebElement::getText)
                .map(priceText -> priceText.replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public double calculateFinalPrice(){
        return getAllPrices().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public double getFinalPrice(){
        return Double.parseDouble(waitForVisible(finalPrice).getText().replace("Item total: $", ""));
    }

    public int getNumOfItemsInCart(){
        return waitForVisible(shoppingCart).getText().isEmpty() ? 0 : Integer.parseInt(shoppingCart.getText());
    }

    public InventoryPage clickCancel(){
        waitForClickable(cancelButton).click();
        return new InventoryPage(driver);
    }

    public CheckoutCompletePage clickFinish(){
        waitForClickable(finishButton).click();
        return new CheckoutCompletePage(driver);
    }

}
