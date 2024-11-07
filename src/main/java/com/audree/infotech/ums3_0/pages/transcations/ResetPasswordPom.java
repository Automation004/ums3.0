package com.audree.infotech.ums3_0.pages.transcations;
import java.util.Properties;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.audree.infotech.ums3_0.utils.CommonData;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ResetPasswordPom extends CommonData {
    WebDriver driver;
    ExtentTest test;
    Properties pro;

    // Locators using @FindBy
    @FindBy(xpath = "//a[contains(text(),'Reset Password')]")
    public WebElement resetPasswordLink;
    
    @FindBy(xpath = "(//a[normalize-space()='Unlock Password'])[1]")
    public WebElement unlockPasswordLink;
    
    @FindBy(xpath = "(//a[normalize-space()='Change Job Role'])[1]")
    public WebElement changeJobRole;
    
    @FindBy(xpath = "//a[@id='Inactive']")
    public WebElement inActive;
    
    @FindBy(xpath = "//select[@formcontrolname='requestForId']")
    public WebElement inputInActive;

    @FindBy(xpath = "//select[@formcontrolname='plantId']")
    private WebElement plantNameDropdown;

    @FindBy(xpath = "//select[@formcontrolname='softwareId']")
    private WebElement softwareDropdowm;
    
    @FindBy(xpath = "//button[normalize-space()='Yes']")
    private WebElement yesButton;

    @FindBy(xpath = "//button[@type='button' and @aria-label='Close']")
    private WebElement closeButton;

    // Constructor
    public ResetPasswordPom(WebDriver driver, ExtentTest test, Properties pro) {
        super(driver, test, pro);
        this.driver = driver;
        this.test = test;
        this.pro = pro;
        PageFactory.initElements(driver, this);
    }

    // Methods to interact with elements
    public void clickResetPassword() {
        resetPasswordLink.click();
        test.log(Status.INFO, "Clicked on Reset Password link");
    }
    
    public void clickUnlockPassword() {
        unlockPasswordLink.click();
        test.log(Status.INFO, "Clicked on Unlock Password link");
    }
    
    public void ClickchangeJobRole() {
        changeJobRole.click();
        test.log(Status.INFO, "Clicked on Change Job Role link");
    }
    
    public void ClickInActive() {
        inActive.click();
        test.log(Status.INFO, "Clicked on Inactive link");
    }
    
    public void plantNameDropdown(String plantName) {
        String PlantNameValue = plantNameDropdown.getText();
        if (PlantNameValue.isEmpty()) {
            plantNameDropdown.click();
            plantNameDropdown.sendKeys(plantName);
            test.log(Status.INFO, "Selected plant name: " + plantName);
        }
        test.log(Status.INFO, "Plant Name already Exsists.Proceeding with that data");

    }
    
    public void selectSoftwareName(String softwareDropdowmValue) throws Exception {
        softwareDropdowm.click();
        softwareDropdowm.sendKeys(softwareDropdowmValue);
        Thread.sleep(1000);
        softwareDropdowm.sendKeys(Keys.ENTER);
        test.log(Status.INFO, "Selected software name: " + softwareDropdowmValue);
    }
    
    public void selectSoftwareNameMultiSelect(String x) throws InterruptedException {
        multiSelectClick01.click();
        Thread.sleep(1000);
        placeHolder.sendKeys(x);
        Thread.sleep(1000);
        checkBox01.click();
        multiSelectClick01.click();
        test.log(Status.INFO, "Selected software name (multi-select): " + x);
    }

    public void selectinputInActive(String softwareDropdowmValue) throws Exception {
        inputInActive.click();
        inputInActive.sendKeys(softwareDropdowmValue, Keys.ENTER);
        Thread.sleep(1000);
        test.log(Status.INFO, "Selected input inactive value: " + softwareDropdowmValue);
    }
    
    public void jobRole(String jobRole) throws Exception {
        multiSelectClick01.click();
        placeHolder.sendKeys(jobRole);
        Thread.sleep(1000);
        checkBox01.click();
        multiSelectClick01.click();
        Thread.sleep(1000);
        test.log(Status.INFO, "Selected job role: " + jobRole);
    }

    public void clickYes() {
        yesButton.click();
        test.pass("Clicked on Yes Button");
    }

    public void clickClose() {
        closeButton.click();
        test.pass("Clicked on Close Button");
    }
}
