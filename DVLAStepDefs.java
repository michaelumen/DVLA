package stepdefinition;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.pageobjects.DVLAElementFinder;
import libs.helper;

public class DVLAStepDefs {
	WebDriver driver = null;
	private String baseUrl = "https://www.gov.uk/get-vehicle-information-from-dvla";

	DVLAElementFinder page = null;
	helper helpme = new helper();

	@Before
	public void setUp() throws SecurityException, IOException {
		driver = new FirefoxDriver();
		page = PageFactory.initElements(driver, DVLAElementFinder.class);
	}

	@Given("^User is on the dvla vehicle search page$")
	public void user_is_on_the_dvla_vehicle_search_page() throws Throwable {
		driver.get(baseUrl);
	}

	@When("^User starts the vehicle enquiry process$")
	public void user_starts_the_vehicle_enquiry_process() throws Throwable {
		page.startEnquiry();
	}

	@Then("^Vehicle details displayed should match details on file in \"(.*?)\"$")
	public void vehicle_details_displayed_should_match_details_on_file_in(String direct) throws Throwable {
		try {
			page.validatePlate(helpme.getFile(direct, new String[] { "txt", "csv" }));
		} catch (IOException e) {e.printStackTrace();}

	}

	@After
	public void tearDown() throws Exception {
		helpme.takeScreenShot(driver, "enterVehicleReg");
		driver.quit();
	}
}
