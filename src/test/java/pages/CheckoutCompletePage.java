package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage{

    public CheckoutCompletePage(WebDriver driver){
        super(driver);
    }

    @FindBy (className = "complete-header")
    private WebElement completeHeader;

    @FindBy(id = "back-to-products")
    private WebElement bachHomeButton;

    public String getCheckCompletedMessage(){
        return waitForVisible(completeHeader).getText();
    }

    public InventoryPage clickBack(){
        waitForClickable(bachHomeButton).click();
        return new InventoryPage(driver);
    }
}
