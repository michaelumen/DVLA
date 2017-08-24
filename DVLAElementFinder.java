package cucumber.pageobjects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import libs.helper;

import org.openqa.selenium.WebElement;

public class DVLAElementFinder {

	private WebDriver driver;

	
	public DVLAElementFinder(WebDriver driver){
		this.driver = driver;
	}
	
	@FindBy(css = "#Vrm")
	private WebElement vregfield;

	@FindBy(css = ".button")
	private WebElement startbutton;
	
	@FindBy(css = ".button")
	private WebElement continuebutton;
	
	@FindBy(css = ".back-to-previous.link-back")
	private WebElement goback;

	@FindBy(css = ".reg-mark")
	private WebElement regdisplayed;
	
	@FindBy(xpath = ".//*[@id='pr3']/div/ul/li[2]/span[2]/strong")
	private WebElement make;
	
	@FindBy(xpath = ".//*[@id='pr3']/div/ul/li[3]/span[2]/strong")
	private WebElement colour;
	helper helpme = new helper();

	
	/**
	 * Populate the vehicle registration field with a value
	 * @param text
	 */
	public void enterVehicleReg(String text) {
		WebElement registration = (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.visibilityOf(vregfield));
		registration.sendKeys(text);
	}

	/**
	 * Click on the start button
	 */
	public void startEnquiry(){
		helpme.takeScreenShot(driver, "enterVehicleReg");
		startbutton.click();
	}
	
	/**
	 * Click on the continue button
	 */
	public void proceed(){
		continuebutton.click();
	}
	
	/**
	 * Click on the back link text to go back to the previous page where
	 * user can enter the vehicle reg number
	 */
	public void backToPrevious(){
		goback.click();
	}
	
	/**
	 * Assert that the vehicle make displayed matches the reference data
	 * @param maker
	 */
	public void validateMake(String maker){
		WebElement carMake = (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.visibilityOf(make));
		Assert.assertEquals(carMake.getText().toUpperCase(),maker.toUpperCase());
	}
	
	/**
	 * Assert that the vehicle colour displayed matches the reference data
	 * @param carcolour
	 */
	public void validateColour(String carcolour){
		WebElement carCol = (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.visibilityOf(colour));
		Assert.assertEquals(carCol.getText().toUpperCase(),carcolour.toUpperCase());
	}
	
	public void validatePlate(String file) {

		String whatFile = file;
		String line = "";
		String splitter = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(whatFile))) {

			while ((line = br.readLine()) != null) {

				String[] vehicle = line.split(splitter);

				enterVehicleReg(vehicle[0].replaceAll("^\"|\"$", ""));
				proceed();
				validateMake(vehicle[1].replaceAll("^\"|\"$", ""));
				validateColour(vehicle[2].replaceAll("^\"|\"$", ""));
				backToPrevious();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
