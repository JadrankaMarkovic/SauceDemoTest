package cucumber.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/cucumberFeatures",
        glue = {"cucumber.stepDefinitions"},
        plugin = {"pretty", "html:src/test/java/cucumber/reports/report.html",
                "rerun:target/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        dryRun = false,    // checks mapping between scenario steps and step definition methods
        monochrome = true,    // to avoid junk characters in output
        publish = true   // to publish report in cucumber server
        //tags = "@logIn"

)
public class TestRunner {

}
