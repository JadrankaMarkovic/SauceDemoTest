package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOnePage extends BasePage{

    public CheckoutOnePage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "first-name")
    private WebElement firstNameBox;

    @FindBy(id = "last-name")
    private WebElement lastNameBox;

    @FindBy(id = "postal-code")
    private WebElement postalCodeBox;

    @FindBy(css = ".shopping_cart_link[data-test='shopping-cart-link']")
    private WebElement shoppingCart;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public void enterFirstName(String firstName){
        waitForVisible(firstNameBox).sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        waitForVisible(lastNameBox).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode){
        waitForVisible(postalCodeBox).sendKeys(postalCode);
    }

    public int getNumOfItemsInCart(){
        return waitForVisible(shoppingCart).getText().isEmpty() ? 0 : Integer.parseInt(shoppingCart.getText());
    }

    public CartPage clickCancelShopping(){
        waitForClickable(cancelButton).click();
        return new CartPage(driver);
    }

    public String getFirstNameFromBox(){
        return waitForVisible(firstNameBox).getDomAttribute("value");
    }

    public String getLastNameFromBox(){
        return waitForVisible(lastNameBox).getDomAttribute("value");
    }

    public String getPostalCodeFromBox(){
        return waitForVisible(postalCodeBox).getDomAttribute("value");
    }

    public CheckoutTwoPage clickCheckout(){
        waitForClickable(continueButton).click();
        return new CheckoutTwoPage(driver);
    }
}
