package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage{

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "user-name")
    private WebElement userNameText;

    @FindBy(id = "password")
    private WebElement passwordText;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorText;

    public void setUserName(String username){
        waitForVisible(userNameText).sendKeys(username);
    }

    public void setPassword(String password){
        waitForVisible(passwordText).sendKeys(password);

    }

    public InventoryPage clickLogInBtn(){
        waitForClickable(loginButton).click();
        return new InventoryPage(driver);
    }

    public String fetchErrorMessage(){
        waitForClickable(loginButton).click();
        return waitForVisible(errorText).getText();
    }

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

}
