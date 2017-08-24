package cucumber.runner;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		format = {"pretty", "jason:target/cucumber.json"},
		features = {"src/cucumber"}
		)
public class CucumberRunner {

}
