package cucumber_BDD_Assignment.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src\\test\\resources\\features",
		glue="cucumber_BDD_Assignment.stepdefs",
		plugin = {"pretty",
				"html:target/html/htmlreport.html",
				"json:target/json/file.json",
		},
		publish=true,
		monochrome= true,
		dryRun= false
				
		)
public class TestshopRunner {

}
