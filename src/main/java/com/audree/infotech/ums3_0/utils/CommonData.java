package com.audree.infotech.ums3_0.utils;

import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class CommonData {
	public static WebDriver driver;
	public WebDriverWait wait;// globally declared
	private static ExtentTest test;
	public static Properties pro;

	@SuppressWarnings("deprecation")
	public CommonData(WebDriver driver, ExtentTest _test, Properties _pro)// constructor call
	{
		CommonData.driver = driver;
		CommonData.test = _test;
		CommonData.pro = _pro;
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@FindBy(xpath = "//button[normalize-space()='Submit']")
	WebElement submitButton1;
	@FindBy(xpath = "(//button[normalize-space()='Submit'])[2]")
	WebElement submitButton2;
	@FindBy(xpath = "(//button[normalize-space()='No'])[3]")
	WebElement noButton;
	@FindBy(xpath = "//button[normalize-space()='Yes']")
	WebElement yesButton;
	@FindBy(xpath = "//input[@formcontrolname='password']")
	WebElement password;
	@FindBy(xpath = "//button[normalize-space()='Ok']")
	WebElement okButton;

	
	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));

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

	public void submitEsigantureActions(boolean useSecondButton) throws Exception {
		waitForWebElementToAppear(submitButton1);
		submitButton1.click();
		noButton.click();
		submitButton1.click();
		yesButton.click();
	    WebElement submitButton = useSecondButton ? submitButton2 : submitButton1;
	    submitButton.click();
		password.sendKeys(pro.getProperty("Password"));
		submitButton.click();
		okButton.click();
	}

	// Helper method to check if the element is present
	public boolean isElementPresent(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
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
