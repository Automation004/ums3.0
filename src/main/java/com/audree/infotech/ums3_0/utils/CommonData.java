package com.audree.infotech.ums3_0.utils;

import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CommonData {
	public static WebDriver driver;
	public WebDriverWait wait;// globally declared
	private static ExtentTest test;
	public static Properties pro;

	@SuppressWarnings("deprecation")
	public CommonData(WebDriver driver, ExtentTest _test, Properties _pro)// constructor call
	{
		CommonData.driver = driver;
		CommonData.setTest(_test);
		CommonData.pro = _pro;
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@FindBy(xpath = "//button[normalize-space()='Submit']")
	public WebElement submitButton1;

	@FindBy(xpath = "(//button[normalize-space()='Submit'])[2]")
	public WebElement submitButton2;

	@FindBy(xpath = "(//button[normalize-space()='Return'])[1]")
	public WebElement ReturnButton;

	@FindBy(xpath = "(//button[normalize-space()='No'])[3]")
	WebElement noButton;

	@FindBy(xpath = "//button[normalize-space()='Yes']")
	WebElement yesButton;

	@FindBy(xpath = "//input[@formcontrolname='password']")
	protected WebElement password;

	@FindBy(xpath = "//button[normalize-space()='Ok']")
	public WebElement okButton;

	@FindBy(xpath = "//button[normalize-space()='Update']")
	public WebElement updateButton1;

	@FindBy(xpath = "//*[@formcontrolname='comments']")
	public WebElement comments;

	@FindBy(xpath = "(//*[@class='dropdown-btn'])[1]")
	public WebElement multiSelectClick01;

	@FindBy(xpath = "(//*[@class='dropdown-btn'])[2]")
	public WebElement multiSelectClick02;

	@FindBy(xpath = "//*[contains(text(),'Get')]")
	public WebElement getButton;

	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public boolean isElementPresent(WebElement element) {
		try {
			// Adjust the wait time based on your requirements
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public void scrollPagedown() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
	}

	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public void enterPassword(String passwordValue) {
		password.sendKeys(passwordValue);
		test.log(Status.PASS, "Entered password.");
	}

	public void submitEsignatureActions(boolean useSecondButton) throws Exception {
		getTest().info("Starting e-signature submission process");
		waitForWebElementToAppear(submitButton1);
		getTest().info("First submit button appeared");
		submitButton1.click();
		getTest().info("Clicked submit button");
		noButton.click();
		getTest().info("Clicked 'No' button");
		submitButton1.click();
		getTest().info("Clicked submit button again");
		yesButton.click();
		getTest().info("Clicked 'Yes' button");
		WebElement submitButton = useSecondButton ? submitButton2 : submitButton1;
		getTest().info("Using " + (useSecondButton ? "second" : "first") + " submit button");
		submitButton.click();
		getTest().info("Clicked submit button");
		password.sendKeys(pro.getProperty("Password"));
		getTest().info("Entered password");
		submitButton.click();
		getTest().info("Clicked submit button after entering password");
		okButton.click();
		getTest().info("Clicked OK button");
		getTest().pass("Submission of record successfully processed");
	}

	public void updateEsignatureActions(boolean useSecondButton) throws Exception {
		getTest().info("Starting e-signature update process");
		waitForWebElementToAppear(updateButton1);
		getTest().info("First update button appeared");
		updateButton1.click();
		getTest().info("Clicked first update button");
		noButton.click();
		getTest().info("Clicked 'No' button");
		updateButton1.click();
		getTest().info("Clicked first update button again");
		yesButton.click();
		getTest().info("Clicked 'Yes' button");
		WebElement submitButton = useSecondButton ? submitButton2 : submitButton1;
		getTest().info("Using " + (useSecondButton ? "second" : "first") + " submit button");
		submitButton.click();
		getTest().info("Clicked submit button");
		password.sendKeys(pro.getProperty("Password"));
		getTest().info("Entered password");
		submitButton.click();
		getTest().info("Clicked submit button after entering password");
		okButton.click();
		getTest().info("Clicked OK button");

		getTest().pass("Updation of record successfully processed");
	}

	public void closeOpenedFile() throws Exception {
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		driver.close();
		driver.switchTo().window(parent);
		Thread.sleep(2000);
		JavascriptExecutor jj = (JavascriptExecutor) driver;
		jj.executeScript("window.scrollTo(0,1000)", "");
		Thread.sleep(2000);
	}

	@FindBy(xpath = "//button[normalize-space()='Save']")
	public WebElement saveButton;

	@FindBy(xpath = "(//input[@placeholder='search' or @placeholder='Search...'])[1]")
	public WebElement searchPlaceHolder;

	@FindBy(xpath = "//input[@type='search']")
	public WebElement searchType;
	
	@FindBy(xpath = "(//*[@placeholder='Search'])[1]")
	public WebElement placeHolder;

	@FindBy(xpath = "//*[contains(text(),'Select All')]")
	public WebElement checkBox01;

	@FindBy(xpath = "(//input[@type='checkbox'])[2]")
	public WebElement checkBox02;

	public void searchPlaceHolder(String x) {
		searchPlaceHolder.sendKeys(x);

	}

	@FindBy(xpath = "(//button[@class='btn btn-primary xsBtn'])[1]")
	public WebElement editActionClick;

	public void editActionButton() {
		editActionClick.click();
		getTest().info("Edit action Button Clicked Successfully");
	}

	// Radio_Button_1
	@FindBy(xpath = "(//*[@type='radio'])[1]")
	public WebElement Radio_Button_1;

	@FindBy(xpath = "(//*[@type='radio'])[2]")
	public WebElement Radio_Button_2;

	@FindBy(xpath = "(//*[@type='radio'])[3]")
	public WebElement Radio_Button_3;

	public void radioButton1() throws Exception {
		Radio_Button_1.click();
		Thread.sleep(300);
	}

	public void radioButton2() throws Exception {
		Radio_Button_2.click();
		Thread.sleep(300);
	}

	public void radioButton3() throws Exception {
		Radio_Button_3.click();
		Thread.sleep(300);

	}

	public static ExtentTest getTest() {
		return test;
	}

	public static void setTest(ExtentTest test) {
		CommonData.test = test;
	}
}

//// Method to verify the validation message
//public void verifyValidationMessage(WebElement validationElement, String expectedValidationMessage) {
//	SoftAssert softAssert = new SoftAssert();
//	try {
//		test.log(Status.INFO, "Verifying the validation message");
//		String actualValidationMessage = validationElement.getText();
//		System.out.println(actualValidationMessage);
//		softAssert.assertTrue(validationElement.isDisplayed(), "Validation message is not displayed");
//		softAssert.assertEquals(actualValidationMessage, expectedValidationMessage,
//				"Validation message does not match");
//		if (validationElement.isDisplayed() && actualValidationMessage.equals(expectedValidationMessage)) {
//			test.log(Status.PASS, "Validation message verified successfully: " + actualValidationMessage);
//		} else {
//			test.log(Status.FAIL, "Validation message verification failed: Expected [" + expectedValidationMessage
//					+ "] but found [" + actualValidationMessage + "]");
//		}
//	} catch (Exception e) {
//		test.log(Status.FAIL, "Exception occurred while verifying the validation message: " + e.getMessage());
//	}
//	softAssert.assertAll(); // This will log the assertion results but will not stop the test immediately
//}
