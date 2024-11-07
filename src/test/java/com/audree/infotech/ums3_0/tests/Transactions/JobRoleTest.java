package com.audree.infotech.ums3_0.tests.Transactions;

import org.testng.annotations.Test;
import com.audree.infotech.ums3_0.pages.masters.JobRolePom;
import com.audree.infotech.ums3_0.testcomponents.BaseTest;
import com.aventstack.extentreports.ExtentReports;

public class JobRoleTest extends BaseTest {
	public JobRolePom jobRolePom;
	ExtentReports extent;
	int startRow;
	int endRow;

	@Test
	public void testJobRoleCreation() {
		jobRolePom = new JobRolePom(driver, test, pro);
		logger.info("Starting test for Job Role Creation...");
		startRow = Integer.parseInt(pro.getProperty("startRow"));
		endRow = Integer.parseInt(pro.getProperty("endRow"));
		loginTest(pro.getProperty("Admin"), pro.getProperty("Password"));
		// Perform the actions in sequence
		jobRolePom.clickMastersLink();
		jobRolePom.clickJobRoleLink();
		try {
			for (int i = startRow; i <= endRow; i++) {
				// Fetch data from Excel
				String plantName = xls.getCellData("MasterData", "plantName", i);
				String jobRoleName = xls.getCellData("MasterData", "jobRoleName", i);

				logger.info("Row " + i + ": Processing data for Plant: " + plantName);

				jobRolePom.selectPlantByName(plantName);
				jobRolePom.enterJobRole(jobRoleName);
				jobRolePom.submitEsignatureActions(true);

				logger.info("Row " + i + ": Job Role Creation Completed Successfully.");
			}
		} catch (Exception ex) {
			logger.error("An error occurred during Job Role Creation: ", ex);
		} finally {
			// Closing the workbook after reading all the data
			xls.closeWorkbook();
			logger.info("Excel workbook closed successfully.");
		}
	}
}
