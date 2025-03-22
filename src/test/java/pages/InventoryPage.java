package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage{

    public InventoryPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = ".inventory_item_name[data-test='inventory-item-name']")
    private List<WebElement> itemsName;

    @FindBy(css = ".inventory_item_price[data-test='inventory-item-price']")
    private List<WebElement> itemsPrice;

    @FindBy(css = "img.inventory_item_img")
    private List<WebElement> itemsImage;

    @FindBy(css = ".btn")
    private List<WebElement> addToCartOrRemoveButtons;

    @FindBy(css = ".shopping_cart_link[data-test='shopping-cart-link']")
    private WebElement shoppingCart;

    @FindBy(css = ".product_sort_container[data-test='product-sort-container']")
    private WebElement dropDownFilter;

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public WebElement getItemByName(String itemName) {
        waitForAllVisible(itemsName);
        return itemsName.stream()
                .filter(item -> item.getText().equals(itemName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item with name: " +
                        itemName +" does not exist"));
    }

    public ItemPage clickOnItemByName(String itemName){
        getItemByName(itemName).click();
        return new ItemPage(driver);
    }

    public WebElement getItemByImage(String itemName) {
        waitForAllVisible(itemsImage);
        return itemsImage.stream()
                .filter(item -> item.getDomAttribute("alt").equals(itemName))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Item with name: "+
                        itemName +" does not exist."));
    }

    public ItemPage clickOnItemByImage(String itemName){
        getItemByImage(itemName).click();
        return new ItemPage(driver);
    }

    public List<String> getAllItemsName() {
        waitForAllVisible(itemsName);
        return itemsName.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getAllPrices() {
        waitForAllVisible(itemsPrice);
        return itemsPrice.stream()
                .map(WebElement::getText)
                .map(priceText -> priceText.replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public void addItemToCart(String itemName) {
        String id = getFullItemName("add-to-cart-", itemName);
        waitForClickable(clickAddRemoveButton(id)).click();
   }

    public void removeItemFromCart(String itemName) {
        String id = getFullItemName("remove-", itemName);
        waitForClickable(clickAddRemoveButton(id)).click();
            }

    private WebElement clickAddRemoveButton(String id){
        waitForAllVisible(addToCartOrRemoveButtons);
        return addToCartOrRemoveButtons.stream()
                .filter(item ->item.getDomAttribute("id").equals(id))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Item with name: " +
                        id + " does not exist."));
    }

    public String getAddTextButton(String itemName) {
        String id = getFullItemName("add-to-cart-", itemName);
        return getButtonText(id);
    }

    public String getRemoveTextButton(String itemName) {
        String id = getFullItemName("remove-", itemName);
        return getButtonText(id);
    }

    private String getButtonText(String id){
        waitForAllVisible(addToCartOrRemoveButtons);
        return addToCartOrRemoveButtons.stream()
                .filter(item -> item.getDomAttribute("id").equals(id))
                .findFirst()
                .map(WebElement::getText)
                .orElseThrow(()-> new IllegalArgumentException("Item with name: " +
                        id + " does not exist."));    }

    public int getNumOfItemsInCart(){
        return waitForVisible(shoppingCart).getText().isEmpty() ? 0 :
                Integer.parseInt(shoppingCart.getText());

    }

    private String getFullItemName(String addRemove, String itemName){
        return addRemove +
                itemName.strip().toLowerCase().replace(" ", "-");
    }

    public void selectFilter(String filterOption){
        findFilterDropDown().selectByVisibleText(filterOption);
    }

    public String getSelectedFilter(){
        return findFilterDropDown().getFirstSelectedOption().getText();
    }

    private Select findFilterDropDown(){
        waitForClickable(dropDownFilter);
        return new Select(dropDownFilter);
    }

    public List<String> getAllFilterOptions(){
        List<WebElement> filterOptions = findFilterDropDown().getOptions();
        return filterOptions.stream()
                .map(WebElement:: getText)
                .collect(Collectors.toList());
    }

    public CartPage clickCart(){
        waitForClickable(shoppingCart).click();
        return new CartPage(driver);
    }

}
