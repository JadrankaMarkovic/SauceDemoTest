package tests;

import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.LogInPage;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    protected LogInPage logInPage;
    public Logger logger;
    public Properties p;

//    @BeforeClass(groups = {"Master", "Sanity", "DataDriven", "EndToEnd"})
    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException {
        FileReader file=new FileReader("src/test/config/config.properties");
        p=new Properties();
        p.load(file);

        driver = new ChromeDriver();
        driver.get(p.getProperty("URL"));
        driver.manage().window().maximize();
        logInPage = new LogInPage(driver);

        logger= LogManager.getLogger(this.getClass());  //lOG4J2
    }

    public void goLogInPage() {
        driver.get(p.getProperty("URL"));
    }

//    @AfterClass (groups = {"Master", "Sanity", "DataDriven", "EndToEnd"})
    @AfterClass (alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";

        File targetFile=new File(targetFilePath);

        FileUtils.copyFile(sourceFile, targetFile);

        return "../screenshots/" + tname + "_" + timeStamp + ".png";
    }
}
