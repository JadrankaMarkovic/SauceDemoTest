package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends BasePage{

    public ItemPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = ".inventory_details_name.large_size")
    private WebElement itemName;

    @FindBy(css = ".inventory_details_desc.large_size")
    private WebElement itemDescription;

    @FindBy(css = ".inventory_details_price")
    private WebElement itemPrice;

    @FindBy(css = ".btn_inventory")
    private WebElement addToCartOrRemoveButton;

    @FindBy(css = ".shopping_cart_link")
    private WebElement shoppingCart;

    @FindBy(css = ".inventory_details_back_button")
    private WebElement backToProductButton;

    public String getItemName(){
        return waitForVisible(itemName).getText();
    }

    public double getItemsPrice(){
        return Double.parseDouble(waitForVisible(itemPrice).getText().replace("$",""));
    }

    public void clickAddRemoveButton() {
        waitForClickable(addToCartOrRemoveButton).click();
    }

    public int getNumOfItemsInCart(){
        return waitForVisible(shoppingCart).getText().isEmpty() ? 0 : Integer.parseInt(shoppingCart.getText());
    }

    public void clickBackToProducts(){
        waitForVisible(backToProductButton).click();
    }
}
