package com.audree.infotech.ums3_0.tests.Reports;

import org.testng.annotations.Test;
import com.audree.infotech.ums3_0.pages.transcations.AuditTrailPom;
import com.audree.infotech.ums3_0.testcomponents.BaseTest;
import com.aventstack.extentreports.Status;

public class UserSoftwareSummaryTest extends BaseTest {
	AuditTrailPom reportPom;
	int startRow;
	int endRow;

	@Test
	public void testPendingRequest() throws Exception {
		logger.info("Starting test for User Software Summary");
		test.log(Status.INFO, "Starting User Software Summary Test");

		startRow = Integer.parseInt(pro.getProperty("startRow"));
		endRow = Integer.parseInt(pro.getProperty("endRow"));

		try {
			for (int i = startRow; i <= endRow; i++) {
				// Fetch data from Excel
				String plantName = xls.getCellData("MasterData", "plantName", i);
				String softwareName = xls.getCellData("MasterData", "softwareName", i);
				String fromDate = xls.getCellData("MasterData", "fromDate", i);
				String toDate = xls.getCellData("MasterData", "toDate", i);

				loginTest(pro.getProperty("Initiator"), pro.getProperty("Password"));

				reportPom = new AuditTrailPom(driver, test, pro);
				reportPom.clickReports();
				reportPom.clickUserSoftwareSummary();
				reportPom.SelectPlantName(plantName);
				reportPom.SelectSoftwareName(softwareName);
				reportPom.fromData(fromDate);
				reportPom.toData(toDate);
				reportPom.getButton.click();
				reportPom.searchPlaceHolder(plantName);

				test.log(Status.PASS, "User Software Summary Test completed for row: " + i);
			}
		} catch (Exception ex) {
			logger.error("Error occurred in User Software Summary: " + ex);
			test.log(Status.FAIL, "Error occurred in User Software Summary Test: " + ex.getMessage());
			throw ex;
		}
	}
}