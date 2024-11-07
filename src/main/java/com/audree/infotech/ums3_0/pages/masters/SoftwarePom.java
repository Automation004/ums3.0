package com.audree.infotech.ums3_0.pages.masters;

import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.audree.infotech.ums3_0.utils.CommonData;
import com.aventstack.extentreports.ExtentTest;

public class SoftwarePom extends CommonData {
	WebDriver driver;
	ExtentTest test;
	Actions action;
	Properties pro;

	public SoftwarePom(WebDriver _driver, ExtentTest _test, Properties _pro) {
		super(_driver, _test, _pro); // Call the parent class (CommonData) constructor
		this.driver = _driver;
		this.test = _test;
		this.pro = _pro;
		PageFactory.initElements(driver, this); // Initialize PageFactory elements
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	// Locators
	@FindBy(xpath = "//a[normalize-space()='Masters']")
	WebElement mastersLink;

	@FindBy(xpath = "//a[normalize-space()='Software']")
	WebElement softwareLink;

	@FindBy(xpath = "//button[normalize-space()='Create']")
	WebElement createButton;

	@FindBy(xpath = "//select[@formcontrolname='plantId']")
	WebElement plantNameDropdown;

	@FindBy(xpath = "//input[@formcontrolname='text']")
	WebElement softwareNameInput;

	@FindBy(xpath = "//input[@formcontrolname='softwareVersion']")
	WebElement softwareVersionInput;

	@FindBy(xpath = "//div[4]//div[1]//input[1]") // Update if necessary
	WebElement softwareDescriptionInput;

	@FindBy(xpath = "//select[@formcontrolname='departmentId']")
	WebElement softwareOwner;

	@FindBy(xpath = "//span[@class='dropdown-multiselect__caret']")
	WebElement applicapableDepartmentMultiSelector;

	@FindBy(xpath = "//div[contains(text(),'Select All')]")
	WebElement selectAllData;

	@FindBy(xpath = "(//input[@type='checkbox'])[1]")
	WebElement checkBox;

	@FindBy(xpath = "(//input[@type='checkbox'])[2]")
	WebElement checkBox02;

	@FindBy(xpath = "//input[@formcontrolname='instrument']")
	WebElement instrumentInput;

	@FindBy(xpath = "//select[@formcontrolname='noofApprovals']")
	WebElement noofApprovals;

	@FindBy(xpath = "//span[@class='dropdown-btn']")
	WebElement selectButton;

	@FindBy(xpath = "//div[normalize-space()='Quality control']")
	WebElement qualityControlOption;

	@FindBy(xpath = "//span[@class='dropdown-multiselect__caret']")
	WebElement removeQualityControlOption;

	@FindBy(xpath = "//select[@class='form-control ng-untouched ng-dirty ng-valid']")
	WebElement dropdown345;

	@FindBy(xpath = "//input[@formcontrolname='licenseCount']")
	public WebElement licenseCountInput;

	@FindBy(xpath = "//input[@formcontrolname='addLicenseCount']")
	WebElement addLicenseCountInput;

	@FindBy(xpath = "//input[@formcontrolname='comments']")
	WebElement commentsInput;

	public void clickMasters() throws Exception {
		Thread.sleep(1000);
		action.moveToElement(mastersLink).click().perform();
		test.info("Clicked on Masters.");
	}

	public void clickSoftware() {
		action.moveToElement(softwareLink).click().perform();
		softwareLink.click();
		test.info("Clicked on Software.");
	}

	public void clickCreate() {
		createButton.click();
		test.info("Clicked on Create.");
	}

	public void selectPlant(String plantName) throws InterruptedException {
		plantNameDropdown.click();
		Thread.sleep(100);
		plantNameDropdown.sendKeys(plantName, Keys.ENTER);
		test.info("Selected plantName: " + plantName);
	}

	public void enterSoftwareName(String softwareName) {
		softwareNameInput.clear();
		softwareNameInput.sendKeys(softwareName);
		test.info("Entered software name: " + softwareName);
	}

	public void enterSoftwareVersion(String version) {
		softwareVersionInput.clear();
		softwareVersionInput.sendKeys(version);
		test.info("Entered software version: " + version);
	}

	public void enterSoftwareDescription(String description) {
		softwareDescriptionInput.clear();
		softwareDescriptionInput.sendKeys(description);
		test.info("Entered software description: " + description);
	}

	public void selectSoftwareOwner(String softwareOwnerDD) throws InterruptedException {
		waitForWebElementToAppear(softwareOwner);
		softwareOwner.click();
		softwareOwner.sendKeys(softwareOwnerDD, Keys.ENTER);
		test.info("Selected Software Owner : " + softwareOwnerDD);
	}

	public void enterInstrument(String instrument) {
		instrumentInput.clear();
		instrumentInput.sendKeys(instrument);
		test.info("Entered instrument: " + instrument);
	}

	public void applicapableDepartment(String applicapableDepartment) throws Exception {
		// applicapableDepartmentMultiSelector.click();
		// test.info("Applicapable Department Dropdown clicked");
		// inputPlaceHolder.sendKeys(applicapableDepartment);
		// test.info("Searched data on Placeholder with data : " +
		// applicapableDepartment);
		// checkBox02.click();
		// applicapableDepartmentMultiSelector.click();
// If we want to select all the below method will useful
		waitForWebElementToAppear(applicapableDepartmentMultiSelector);
		applicapableDepartmentMultiSelector.click();
		Thread.sleep(500);
		selectAllData.click();
		applicapableDepartmentMultiSelector.click();

	}

	public void selectNoOfApproverLevels(String noOfApprovals) {
		waitForWebElementToAppear(noofApprovals);
		noofApprovals.click();
		noofApprovals.sendKeys(noOfApprovals, Keys.ENTER);
		test.info("Entered instrument: " + noOfApprovals);
	}

	public void enterLicenseCount(String count) {
		licenseCountInput.clear();
		licenseCountInput.sendKeys(count);
		test.info("Entered license count: " + count);
	}

	public void addLicenseCount(String count) {
		addLicenseCountInput.clear();
		addLicenseCountInput.sendKeys(count);
		test.info("Added License Count : " + count);
	}

	public void enterComments(String comments) {
		commentsInput.clear();
		commentsInput.sendKeys(comments);
		test.info("entered comments Successfully");
	}

}
