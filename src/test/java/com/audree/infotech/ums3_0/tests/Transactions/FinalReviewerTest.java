package com.audree.infotech.ums3_0.tests.Transactions;
import org.testng.annotations.Test;

import com.audree.infotech.ums3_0.pages.transcations.InitiatorNewJobRolePom;
import com.audree.infotech.ums3_0.testcomponents.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

public class FinalReviewerTest extends BaseTest {
	InitiatorNewJobRolePom jobRolePom;
	ExtentReports extent;
	int startRow;
	int endRow;
	
	@Test
	public void testFinalReviewJobRole() throws Exception {
		logger.info("Starting test for Final Reviewer.");
		startRow = Integer.parseInt(pro.getProperty("startRow"));
		endRow = Integer.parseInt(pro.getProperty("endRow"));
		try {
			for (int i = startRow; i <= endRow; i++) {
				// Fetch data from Excel
				String softwareName = xls.getCellData("MasterData", "softwareName", i);
				String comments = xls.getCellData("MasterData", "comments", i);

				loginTest(pro.getProperty("FinalReviewer"), pro.getProperty("Password"));

				logger.info("Starting Final Reviewer creation test");
				jobRolePom = new InitiatorNewJobRolePom(driver, test, pro);

				jobRolePom.searchPlaceHolder.sendKeys(softwareName + " " + "Initiator Rahul (797970)");
				test.log(Status.INFO, "Searching the created record with :" + softwareName + " " + "Initiator");
				jobRolePom.clickRequestId();
				scrollElementIntoView(jobRolePom.comments);
				jobRolePom.comments.sendKeys(comments + " " + softwareName);
				test.log(Status.INFO, "Entered Comments Succesfully");
				jobRolePom.submitButton1.click();
				jobRolePom.clickYes();
				jobRolePom.submitButton2.click();
				jobRolePom.enterPassword(pro.getProperty("Password"));
				jobRolePom.submitButton2.click();
				jobRolePom.clickOk();

				logger.info("Job Role creation completed successfully.");
			}
		} catch (Exception ex) {
			System.out.println("Error Occured at New Job Role Creation :" + ex);
		}
	}
}
