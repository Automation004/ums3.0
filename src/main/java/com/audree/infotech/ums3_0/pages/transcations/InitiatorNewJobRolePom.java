package com.audree.infotech.ums3_0.pages.transcations;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.audree.infotech.ums3_0.utils.CommonData;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class InitiatorNewJobRolePom extends CommonData {
	WebDriver driver;
	Actions action;
	ExtentTest test;
	Properties pro;
	public String requestText;

	public InitiatorNewJobRolePom(WebDriver driver, ExtentTest test, Properties pro) {
		super(driver, test, pro);
		this.driver = driver;
		this.test = test;
		this.pro = pro;
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
	}

	// Locators
	@FindBy(xpath = "//a[normalize-space()='New Job Role']")
	WebElement newJobRoleButton;

	@FindBy(xpath = "//select[@formcontrolname='plantId']")
	WebElement plantName;

	@FindBy(xpath = "//select[@formcontrolname='softwareId']")
	WebElement software;

	@FindBy(xpath = "//div[@class='multiselect-dropdown']")
	WebElement jobRole;

	@FindBy(xpath = "//div[normalize-space()='Select All']")
	WebElement selectAll;

	@FindBy(xpath = "//select[@formcontrolname='trainingId']")
	WebElement training;

	@FindBy(xpath = "//button[normalize-space()='Add']")
	WebElement addButton;

	@FindBy(xpath = "//button[normalize-space()='Submit']")
	WebElement submitButton;

	@FindBy(xpath = "//button[normalize-space()='Yes']")
	public WebElement yesButton;

	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement passwordField;

	@FindBy(xpath = "//button[normalize-space()='Ok']")
	WebElement okButton;

	@FindBy(xpath = "//*[@id='dataTable']/tbody/tr/td[2]/a")
	WebElement requestId;

	@FindBy(xpath = "//*[@formcontrolname='assignUserId']")
	public WebElement inputAssignuserId;
	
	// Methods to perform actions with Extent logging
	public void clickNewJobRole() {
		newJobRoleButton.click();
		test.log(Status.PASS, "Clicked on New Job Role button.");
	}

	public void plantName(String plantNameValue) {
		String getPlantName = plantName.getText();
		if (getPlantName.isEmpty()) {
			plantName.click();
			Select select = new Select(plantName);
			select.selectByVisibleText(plantNameValue);
			test.log(Status.PASS, "Plant name selected: " + plantNameValue);
		}
	}

	public void selectSoftwareName(String softwareValue) {
		software.click();
		Select select = new Select(software);
		select.selectByVisibleText(softwareValue);
		test.log(Status.PASS, "Software name selected: " + softwareValue);
	}

	public void selectJobRole(String jobRoleName) {
		jobRole.click();
		selectAll.click();
		test.log(Status.PASS, "Job role selected: All roles.");
	}

	public void selectTraining(String trainingValue) {
		training.click();
		Select select = new Select(training);
		select.selectByVisibleText("Yes");
		test.log(Status.PASS, "Training selected: " + "All");
	}

	public void clickAdd() {
		waitForWebElementToAppear(addButton);
		addButton.click();
		test.log(Status.PASS, "Clicked on Add button.");
	}

	public void clickYes() {
		yesButton.click();
		test.log(Status.PASS, "Clicked on Yes button.");
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
		test.log(Status.PASS, "Entered password.");
	}

	public void clickOk() {
		okButton.click();
		test.log(Status.PASS, "Clicked on OK button.");
	}

	public void clickRequestId() {
		requestText = requestId.getText();
		test.log(Status.INFO, "Request ID: " + requestText);
		requestId.click();
		test.log(Status.PASS, "Clicked on request ID.");
	}
	public void assignUserId(String userIdName) {
		inputAssignuserId.clear();
		inputAssignuserId.sendKeys(userIdName);
		test.log(Status.PASS, "Clicked on OK button.");
	}
}
