package com.audree.infotech.ums3_0.pages.masters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

public class LoginPage {
	WebDriver driver;
	ExtentTest test;

	// Constructor to initialize the WebDriver
	public LoginPage(ExtentTest _test, WebDriver _driver) {
		this.driver = _driver;
		this.test = _test;
	}

	// Locators
	By userIdField = By.xpath("//input[@placeholder='User ID']");
	By passwordField = By.xpath("//input[@placeholder='Password']");
	By loginButton = By.xpath("//button[normalize-space()='Login']");
    By errorMessage = By.xpath("//div[@class='error-message']"); 


	// Methods to interact with the login page
	public void setUserId(String userId) {
		WebElement userIdElement = driver.findElement(userIdField);
		userIdElement.clear();
		userIdElement.sendKeys(userId);
	}

	public void setPassword(String password) {
		WebElement passwordElement = driver.findElement(passwordField);
		passwordElement.clear();
		passwordElement.sendKeys(password);
	}

	public void clickLogin() {
		driver.findElement(loginButton).click();
	}
	public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isLoginSuccessful() {
        // Logic to determine if login is successful (e.g., checking the presence of a specific element post-login)
        return !driver.findElements(errorMessage).isEmpty();  // Update logic based on your app
    }
}
