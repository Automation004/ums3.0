package com.audree.infotech.ums3_0.tests.Transcations.ReturnFlow;

import org.testng.annotations.Test;
import com.audree.infotech.ums3_0.pages.transcations.InitiatorNewJobRolePom;
import com.audree.infotech.ums3_0.testcomponents.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

public class InitiatorNewJobRoleReInitiateTest extends BaseTest {
	InitiatorNewJobRolePom jobRolePom;
	ExtentReports extent;
	int startRow;
	int endRow;

	@Test
	public void testCreateJobRole() throws Exception {

		logger.info("Starting test for Re-Initiator Creation");
		startRow = Integer.parseInt(pro.getProperty("startRow"));
		endRow = Integer.parseInt(pro.getProperty("endRow"));
		try {
			for (int i = startRow; i <= endRow; i++) {
				// Fetch data from Excel
				String softwareName = xls.getCellData("MasterData", "softwareName", i);

				loginTest(pro.getProperty("Initiator"), pro.getProperty("Password"));

				jobRolePom = new InitiatorNewJobRolePom(driver, test, pro);

				jobRolePom.clickNewJobRole();
				jobRolePom.searchPlaceHolder.sendKeys(softwareName + " " + "Initiator Rahul (797970)");
				test.log(Status.INFO, "Searching the created record with :" + softwareName + " " + "Initiator");
				jobRolePom.clickRequestId();
				AttachFile();
				UploadFile("C:\\Users\\sharuk.k\\Downloads\\Work Order Log ");
				jobRolePom.waitForWebElementToAppear(jobRolePom.submitButton1);
				jobRolePom.submitButton1.click();
				jobRolePom.clickYes();
				jobRolePom.submitButton2.click();
				jobRolePom.enterPassword(pro.getProperty("Password"));
				jobRolePom.submitButton2.click();
				jobRolePom.clickOk();
				jobRolePom.searchPlaceHolder.sendKeys(softwareName + " " + "Initiator Rahul (797970)");
				jobRolePom.clickRequestId();
				logger.info("Job Role creation completed successfully.");
			}
		} catch (Exception ex) {
			System.out.println("Error Occured at New Job Role Creation :" + ex);
		}
	}
}
