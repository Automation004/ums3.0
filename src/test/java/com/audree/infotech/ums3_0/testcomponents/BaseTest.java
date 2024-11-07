package com.audree.infotech.ums3_0.testcomponents;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.audree.infotech.ums3_0.pages.masters.LoginPage;
import com.audree.infotech.ums3_0.tests.Transactions.ApproverTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	public static ExtentReports extent;
	public ExtentSparkReporter Report;
	public ExtentTest test;
	public Properties pro;
	public Robot r;
	public WebDriverWait wait;
	LoginPage loginPage;

	protected static final Logger logger = LogManager.getLogger(ApproverTest.class);

	static {
		System.setProperty("log4j.configurationFile",
				"C:\\Users\\sharuk.k\\eclipse-workspace\\ums3.0\\ums3-automation\\Loggers");
	}

	public Xls_Reader xls = new Xls_Reader(
			"C:\\Users\\sharuk.k\\eclipse-workspace\\ums3.0\\ums3-automation\\src\\test\\resources\\com.exceldata\\UMS_Excel_3_0.xlsx");

	@BeforeClass(alwaysRun = true)
	public void suiteSetUp() throws Exception {
		pro = new Properties();
		FileInputStream ip = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\com.properties\\config.properties");
		pro.load(ip);
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(pro.getProperty("url"));
		// Initialize the LoginPage object
		loginPage = new LoginPage(test, driver);

	}

	public static ExtentReports getReportObject(String testName) {
		if (extent == null) { // Only initialize if extent is null
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd--HH.mm.ss").format(new Date());// time stamp
			String repName = testName + " Test-Report-" + timeStamp + ".html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(
					System.getProperty("user.dir") + "/Reports/" + repName);
			reporter.config().setEncoding("utf-8");
			reporter.config().setReportName("Automation Test Result");
			reporter.config().setTheme(Theme.STANDARD);
			reporter.config().setDocumentTitle("PWO 2.1"); // Tile of report
			reporter.config().setReportName("UMS 3.0"); // name of the report
			extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Organization", "Audree Infotech Pvt Ltd");
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("Tester", "Sharuk Komminapalli");
		}
		return extent;
	}

	@BeforeClass(alwaysRun = true)
	public void initializeExtentTest() {
		// Get the singleton instance of ExtentReports
		extent = getReportObject(this.getClass().getSimpleName());
		test = extent.createTest(this.getClass().getSimpleName());
		System.out.println(this.getClass().getSimpleName());
	}

	@AfterClass()
	public void EndReport() {
		extent.flush();
		System.out.println("Flush Completed");
	}

	public void loginTest(String username, String password) {
		loginPage.clickLogin();
		test.log(Status.PASS,
				"Clicked on the login button without entering username or password to check for validation.");
		loginPage.setUserId(username);
		test.log(Status.PASS, "Entered username: " + username);
		loginPage.clickLogin();
		test.log(Status.PASS, "Clicked on the login button without entering password to check for validation.");
		loginPage.setPassword(password);
		test.log(Status.PASS, "Entered password.");
		loginPage.clickLogin();
		test.log(Status.PASS, "Clicked on the login button to submit credentials.");
		test.log(Status.INFO, "Login test completed.");

	}

	// Takes Screenshot
	protected String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		File source = ((TakesScreenshot) (driver)).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/FailedReport/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		org.apache.commons.io.FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@SuppressWarnings("unused")
	private String getScreenshot1(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		File source = ((TakesScreenshot) (driver)).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/PassScreenshots/" + screenshotName + dateName + "png";
		File finalDestination = new File(destination);
		org.apache.commons.io.FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public WebElement waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement isExistes = wait.until(ExpectedConditions.visibilityOf(findBy));
		return isExistes;
	}

	public void scrollPagedown() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		test.info("Scrolling the page to down");
	}

	public void scrollElementIntoView(WebElement element) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		test.info("Scrolling the element into view");
		Thread.sleep(500);
	}

	public void scrollPageup() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		Thread.sleep(3000);
	}

	public void scrollPagedownSlow() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,200)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,300)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,600)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,700)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,800)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,900)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(1000);
	}

	public void UploadFile(String path) throws Exception {
		// Selenium can handle most interactions with web elements in a browser.
		// However, it cannot interact directly with file upload dialogs because they
		// are part of the operating system, not the web page.
		// This is where Java's Robot class can be used to mimic user interactions like
		// file uploads by handling OS-level dialogs, which Selenium alone can't
		// manipulate.
		r = new Robot();
		r.delay(1000);
		// put path to file in a clipboard
		StringSelection s = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		// ctrl+V press
		r.keyPress(KeyEvent.VK_CONTROL);// press on ctrl key+copy
		r.keyPress(KeyEvent.VK_V);// press on ctrl key+paste
		r.delay(500);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		r.delay(1000);
		// Enter
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		r.delay(500);
		test.pass("File uploaded Successfully");
		System.out.println("uploaded Successfully");
	}

	public void AttachFile() throws Exception {
		WebElement Color = driver.findElement(By.xpath("//*[@type='file']"));
		JavascriptExecutor Js = (JavascriptExecutor) driver;
		Js.executeScript("arguments[0].setAttribute('style', 'background: ; border: 2px solid black;');", Color);
		WebElement AttachFile = driver.findElement(By.xpath("//*[@type='file']"));
		Actions action = new Actions(driver);
		action.click(AttachFile).perform();
		test.pass("File Attached Successfully");
		Thread.sleep(2000);
	}

	public void MoveCursor() throws Exception {
		WebElement MoveCursor;
		Actions actions = new Actions(driver);
		MoveCursor = driver.findElement(By.xpath("//*[text()='Masters']"));
		Thread.sleep(2000);
		actions.moveToElement(MoveCursor).perform();
		Thread.sleep(1000);
	}

	public void scrollPagedownWithActions(WebElement element) throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		Thread.sleep(3000);
	}
}
