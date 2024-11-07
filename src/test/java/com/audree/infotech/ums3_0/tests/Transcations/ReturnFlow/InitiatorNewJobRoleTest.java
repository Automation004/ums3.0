package com.audree.infotech.ums3_0.tests.Transcations.ReturnFlow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.audree.infotech.ums3_0.pages.transcations.InitiatorNewJobRolePom;
import com.audree.infotech.ums3_0.testcomponents.BaseTest;
import com.aventstack.extentreports.ExtentReports;

public class InitiatorNewJobRoleTest extends BaseTest {
	InitiatorNewJobRolePom jobRolePom;
	ExtentReports extent;
	int startRow;
	int endRow;

	private static final Logger logger = LogManager.getLogger(InitiatorNewJobRoleTest.class);

	@Test
	public void testCreateJobRole() throws Exception {
		
		logger.info("Starting test for Initiator Creation");
		startRow = Integer.parseInt(pro.getProperty("startRow"));
		endRow = Integer.parseInt(pro.getProperty("endRow"));
		try {
			for (int i = startRow; i <= endRow; i++) {
				// Fetch data from Excel
				String plantName = xls.getCellData("MasterData", "plantName", i);
				String softwareName = xls.getCellData("MasterData", "softwareName", i);
				String jobRoleName = xls.getCellData("MasterData", "jobRoleName", i);
				String training = xls.getCellData("MasterData", "training", i);

				loginTest(pro.getProperty("Initiator"),pro.getProperty("Password"));

				logger.info("Starting Job Role creation test...");
				jobRolePom = new InitiatorNewJobRolePom(driver, test, pro);

				jobRolePom.clickNewJobRole();
				jobRolePom.plantName(plantName);
				jobRolePom.selectSoftwareName(softwareName);
				jobRolePom.selectJobRole(jobRoleName);
				jobRolePom.selectTraining(training);

				AttachFile();
				UploadFile("C:\\Users\\sharuk.k\\Downloads\\Equipment History Log (1) (1)");

				jobRolePom.clickAdd();
				jobRolePom.waitForWebElementToAppear(jobRolePom.submitButton1);
				jobRolePom.submitButton1.click();
				jobRolePom.clickYes();
				jobRolePom.submitButton2.click();
				jobRolePom.enterPassword(pro.getProperty("Password"));
				jobRolePom.submitButton2.click();
				jobRolePom.clickOk();
				jobRolePom.searchPlaceHolder.sendKeys(softwareName +" "+"Initiator Rahul (797970)");
				jobRolePom.clickRequestId();
				logger.info("Job Role creation completed successfully.");
			}
		} catch (Exception ex) {
			System.out.println("Error Occured at New Job Role Creation :" + ex);
		}
	}
}
