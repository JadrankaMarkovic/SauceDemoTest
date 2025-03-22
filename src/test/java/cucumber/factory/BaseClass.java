package cucumber.factory;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    static WebDriver driver;
    static Properties p;

    public static void setUp() throws IOException {
        if (p == null) {
            FileReader file = new FileReader("src/test/config/config.properties");
            p = new Properties();
            p.load(file);
        }

        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        //logger= LogManager.getLogger(this.getClass());  //lOG4J2
    }

    public static WebDriver getDriver() {
        return driver;
    }
    public static Properties getProperties() {
        return p;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}