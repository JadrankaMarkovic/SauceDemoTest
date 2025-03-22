package cucumber.stepDefinitions;

import cucumber.factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Properties;

public class Hooks {
    WebDriver driver;
    Properties p;
    @Before
    public void setUp() throws IOException {
        BaseClass.setUp();
        driver = BaseClass.getDriver();
        p = BaseClass.getProperties();
        driver.get(p.getProperty("URL"));
    };
    @After
    public void tearDown(Scenario scenario) {
        BaseClass.tearDown();
    }
}


