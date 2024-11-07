package com.audree.infotech.ums3_0.pages.masters;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.audree.infotech.ums3_0.utils.CommonData;
import com.aventstack.extentreports.ExtentTest;

public class JobRolePom extends CommonData {
    WebDriver driver;
    ExtentTest test;
    Actions action;
    Properties pro;
    WebDriverWait wait;

    public JobRolePom(WebDriver _driver, ExtentTest _test, Properties _pro) {
        super(_driver, _test, _pro); // Call the parent class (CommonData) constructor
        this.driver = _driver;
        this.test = _test;
        this.pro = _pro;
        PageFactory.initElements(driver, this); // Initialize PageFactory elements
        action = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Locators
    @FindBy(xpath = "//a[normalize-space()='Masters']")
    WebElement mastersLink;

    @FindBy(xpath = "//a[normalize-space()='JobRole']")
    WebElement jobRoleLink;

    @FindBy(xpath = "//select[@class='selectpicker ng-untouched ng-pristine ng-valid']")
    WebElement plantNameDropdown;

    @FindBy(xpath = "//input[@type='text']")
    WebElement jobRoleInput;

    // Actions

    /**
     * Clicks on the "Masters" link
     */
    public void clickMastersLink() {
        wait.until(ExpectedConditions.elementToBeClickable(mastersLink));
        mastersLink.click();
        test.info("Clicked on Masters link");
    }

    /**
     * Clicks on the "Job Role" link
     */
    public void clickJobRoleLink() {
        wait.until(ExpectedConditions.elementToBeClickable(jobRoleLink));
        jobRoleLink.click();
        test.info("Clicked on Job Role link");
    }

    /**
     * Selects a plant from the dropdown by visible text
     * 
     * @param plantName the name of the plant to select
     */
    public void selectPlantByName(String plantName) {
        wait.until(ExpectedConditions.visibilityOf(plantNameDropdown));
        plantNameDropdown.click();
        Select select = new Select(plantNameDropdown);
        select.selectByVisibleText(plantName);
        test.info("Selected plant: " + plantName);
    }

    /**
     * Enters a job role in the input field
     * 
     * @param jobRole the job role name to enter
     */
    public void enterJobRole(String jobRole) {
        wait.until(ExpectedConditions.visibilityOf(jobRoleInput));
        jobRoleInput.clear();
        jobRoleInput.sendKeys(jobRole);
        test.info("Entered Job Role: " + jobRole);
    }

    /**
     * Verifies if the Job Role link is displayed
     * 
     * @return true if displayed, false otherwise
     */
    public boolean isJobRoleLinkDisplayed() {
        return jobRoleLink.isDisplayed();
    }
}
