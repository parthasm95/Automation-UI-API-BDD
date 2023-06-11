package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import io.cucumber.core.cli.Main;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features"},
		glue = {"stepdefinitions"},
		dryRun = false,
		plugin = {
				"pretty",
				"json:target/cucumber-report.json",
				"html:target/cucumber-report/cucumber-report.html"
		}
		)


public class TestRunner {

}
