package com.audree.infotech.ums3_0.pages.transcations;

import java.util.Properties;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.audree.infotech.ums3_0.utils.CommonData;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class AuditTrailPom extends CommonData {
	WebDriver driver;
	Actions action;
	ExtentTest test;
	Properties pro;

	public AuditTrailPom(WebDriver driver, ExtentTest test, Properties pro) {
		super(driver, test, pro);
		this.driver = driver;
		this.test = test;
		this.pro = pro;
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
	}

	// Locators
	@FindBy(xpath = "//a[normalize-space()='Reports']")
	WebElement ReportsClick;

	@FindBy(xpath = "(//a[normalize-space()='AuditTrail'])[1]")
	WebElement auditTrail;

	@FindBy(xpath = "//a[normalize-space()='PendingRequest']")
	WebElement pendingRequest;

	@FindBy(xpath = "//a[normalize-space()='UserSoftwareSummary']")
	WebElement userSoftwareSummary;

	@FindBy(xpath = "//a[normalize-space()='UserStatusReport']")
	WebElement UserStatusReport;

	@FindBy(xpath = "//a[normalize-space()='InactiveRequestTracking']")
	WebElement InactiveRequestTracking;

	@FindBy(xpath = "/html/body/app-root/app-audittrail/div[1]/div/div/div[2]/div[1]/div/select")
	WebElement plantName1;

	@FindBy(xpath = "//select[@formcontrolname='plantId']")
	WebElement plantName2;

	@FindBy(xpath = "//select[@formcontrolname='softwareName']")
	WebElement softwareName;

	@FindBy(xpath = "/html/body/app-root/app-audittrail/div[1]/div/div/div[2]/div[2]/div/select")
	WebElement userName;

	@FindBy(xpath = "/html/body/app-root/app-audittrail/div[1]/div/div/div[2]/div[3]/div/select")
	WebElement transcationCode;

	@FindBy(xpath = "(//input[@type='date'])[1]")
	WebElement fromDate;

	@FindBy(xpath = "(//input[@type='date'])[2]")
	WebElement toDate;

	public void clickReports() {
		ReportsClick.click();
		test.log(Status.INFO, "Clicked on Reports");
	}

	public void clickAuditTrail() {
		auditTrail.click();
		test.log(Status.INFO, "Clicked on Audit Trail");
	}

	public void clickPendingRequest() {
		pendingRequest.click();
		test.log(Status.INFO, "Clicked on Pending Request");
	}

	public void clickUserSoftwareSummary() {
		userSoftwareSummary.click();
		test.log(Status.INFO, "Clicked on User Software Summary");
	}

	public void clickUserStatusReport() {
		UserStatusReport.click();
		test.log(Status.INFO, "Clicked on User Status Report");
	}

	public void clickInactiveRequestTracking() {
		InactiveRequestTracking.click();
		test.log(Status.INFO, "Clicked on In Active Request Tracking");
	}

	public void SelectPlantNameAuditTrail(String x) {
		plantName1.click();
		plantName1.sendKeys(x, Keys.ENTER);
		test.log(Status.INFO, "Selected Plant Name: " + x);
	}

	public void SelectPlantName(String x) {
		plantName2.click();
		plantName2.sendKeys(x, Keys.ENTER);
		test.log(Status.INFO, "Selected Plant Name: " + x);
	}

	public void SelectSoftwareName(String x) throws InterruptedException {
		multiSelectClick01.click();
		Thread.sleep(1000);
		placeHolder.sendKeys(x);
		Thread.sleep(1000);
		checkBox01.click();
		multiSelectClick01.click();
		test.log(Status.INFO, "Selected Software Name: " + x);
	}

	public void selectEmployeeId(String x) throws InterruptedException {
		multiSelectClick01.click();
		Thread.sleep(1000);
		placeHolder.sendKeys(x);
		Thread.sleep(1000);
		checkBox01.click();
		multiSelectClick01.click();
		test.log(Status.INFO, "Selected Employee Id: " + x);
	}

	public void SelectUserName(String x) {
		userName.click();
		userName.sendKeys(x, Keys.ENTER);
		test.log(Status.INFO, "Selected User Name: " + x);
	}

	public void fromData(String x) {
		fromDate.click();
		fromDate.sendKeys(x, Keys.ENTER);
		test.log(Status.INFO, "Entered From Date: " + x);
	}

	public void toData(String x) {
		toDate.click();
		toDate.sendKeys(x, Keys.ENTER);
		test.log(Status.INFO, "Entered To Date: " + x);
	}
}
