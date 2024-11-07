package com.audree.infotech.ums3_0.pages.masters;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.audree.infotech.ums3_0.utils.CommonData;
import com.aventstack.extentreports.ExtentTest;

public class ConfigurationsPom extends CommonData {
	WebDriver driver;
	ExtentTest test;
	Properties pro;
	WebDriverWait wait;

	public ConfigurationsPom(WebDriver _driver, ExtentTest _test, Properties _pro) {
		super(_driver, _test, _pro); // Call the parent class (CommonData) constructor
		this.driver = _driver;
		this.test = _test;
		this.pro = _pro;
		PageFactory.initElements(driver, this); // Initialize PageFactory elements
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//a[normalize-space()='Configurations']")
	WebElement configurationsLink;

	@FindBy(xpath = "//a[normalize-space()='SoftwareFlow']")
	WebElement softwareFlowLink;

	@FindBy(xpath = "//select[@placeholder='--Select--']")
	WebElement selectPlantDropdown;

	@FindBy(xpath = "//select[@formcontrolname='software']")
	WebElement softwareNameDropdown;

	@FindBy(xpath = "//select[@formcontrolname='software']")
	WebElement jobRoleDropdown;

	@FindBy(xpath = "//select[@formcontrolname='flow']")
	WebElement flowDropdown;

	@FindBy(xpath = "//button[normalize-space()='Get']")
	WebElement getButton;

	@FindBy(xpath = "//tbody/tr[1]/td[3]/input[1]")
	public WebElement trueCheckbox01;
	
	@FindBy(xpath = "//tbody/tr[2]/td[3]/input[1]")
	public WebElement trueCheckbox02;

	@FindBy(xpath = "//button[normalize-space()='Re-Assign']")
	WebElement assignButton;

	@FindBy(xpath = "//button[normalize-space()='Re-Assign']")
	WebElement reassignButton;

	@FindBy(xpath = "//button[normalize-space()='Yes']")
	public WebElement yesButton;

	@FindBy(xpath = "//select[@formcontrolname='reviewer']")
	public WebElement selectReviewer;

	@FindBy(xpath = "//select[@formcontrolname='finalreviewer']")
	public WebElement selectFinalReviewer;

	@FindBy(xpath = "//select[@formcontrolname='approver']")
	public WebElement selectApprover;

	@FindBy(xpath = "//select[@formcontrolname='creator']")
	public WebElement selectCreator;
	// Actions

	/**
	 * Clicks on the "Configurations" link
	 */
	public void clickConfigurationsLink() {
		configurationsLink.click();
	}

	/**
	 * Clicks on the "Software Flow" link
	 */
	public void clickSoftwareFlowLink() {
		softwareFlowLink.click();
	}

	/**
	 * Selects a plant from the dropdown by visible text
	 * 
	 * @param plantName the name of the plant to select
	 */
	public void selectPlantByName(String plantName) {
		selectFromDropdown(selectPlantDropdown, plantName);
	}

	/**
	 * Selects a calibration tool from the dropdown by visible text
	 * 
	 * @param calibrationTool the name of the calibration tool to select
	 */
	public void selectSoftwareName(String software) {
		softwareNameDropdown.click();
		selectFromDropdown(softwareNameDropdown, software);
	}

	public void selectFlow(String flow) {
		flowDropdown.click();
		selectFromDropdown(flowDropdown, flow);
	}

	public void initiatorDepartment() {
		waitForWebElementToAppear(multiSelectClick01);
		multiSelectClick01.click();
		checkBox01.click();
		multiSelectClick01.click();
	}
	
	public void selectReviewer(String selectReviewerValue) {
		selectReviewer.click();
		selectFromDropdown(selectReviewer, selectReviewerValue);
	}

	public void selectFinalReviewer(String selectFinalReviewerValue) {
		selectFinalReviewer.click();
		selectFromDropdown(selectFinalReviewer, selectFinalReviewerValue);
	}

	public void selectApprover(String selectApproverValue) {
		selectApprover.click();
		selectFromDropdown(selectApprover, selectApproverValue);
	}
	

	public void selectCreator(String selectCreatorValue) {
		selectCreator.click();
		selectFromDropdown(selectCreator, selectCreatorValue);
	}

	public void getButton() {
		waitForWebElementToAppear(getButton);
		getButton.click();
	}

	/**
	 * Clicks on the checkbox to mark "true"
	 */
	public void clickTrueCheckbox() {
		trueCheckbox01.click();
		trueCheckbox02.click();
	}

	public void saveButton() {
		saveButton.click();
		test.pass("Save Button Clicked Successfully");
	}

	public void yesButton() {
		yesButton.click();
		test.pass("Yes Button Clicked Successfully");
	}

	public void passwordFill() {
		password.sendKeys(pro.getProperty("Password"));
		test.pass("Password given Successfully");
	}

	public void submitButton() {
		submitButton1.click();
		test.pass("Submit Button Clicked Successfully");
	}

	/**
	 * Clicks on the "Re-Assign" button
	 */
	public void clickReassignButton() {
		reassignButton.click();
	}

	/**
	 * Clicks on the "Yes" button
	 */
	public void clickYesButton() {
		yesButton.click();
	}

	// Utility method to select from dropdown
	private void selectFromDropdown(WebElement dropdown, String value) {
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
	}
}