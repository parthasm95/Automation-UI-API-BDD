package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/featurefiles/ui"},
		glue = {"stepdefinitions", "apphooks"},
		tags = "not @ignore",
		plugin = {
				"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"json:target/cucumber-report.json",
				"html:target/cucumber-report/cucumber-report.html",
				"timeline:test-output-thread"
		}
		)


public class TestRunner {

}
