package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/feature_files",
        plugin = {"pretty",
                "html:target/cucumber.html"
        }, glue = {"stepDefs"}, tags = "@testToRun")

public class TestRunner {
}
