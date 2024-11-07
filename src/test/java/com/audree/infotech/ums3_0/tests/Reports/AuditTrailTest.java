package com.audree.infotech.ums3_0.tests.Reports;
import org.testng.annotations.Test;
import com.audree.infotech.ums3_0.pages.transcations.AuditTrailPom;
import com.audree.infotech.ums3_0.testcomponents.BaseTest;
import com.aventstack.extentreports.Status;

public class AuditTrailTest extends BaseTest {
	AuditTrailPom reportPom;
	int startRow;
	int endRow;

	@Test
	public void testAuditTrail() throws Exception {
		logger.info("Starting test for Audit Trails");
		test.log(Status.INFO, "Starting Audit Trail Test");

		startRow = Integer.parseInt(pro.getProperty("startRow"));
		endRow = Integer.parseInt(pro.getProperty("endRow"));

		try {
			for (int i = startRow; i <= endRow; i++) {
				// Fetch data from Excel
				String plantName = xls.getCellData("MasterData", "plantName", i);
				String fromDate = xls.getCellData("MasterData", "fromDate", i);
				String toDate = xls.getCellData("MasterData", "toDate", i);

				loginTest(pro.getProperty("Initiator"), pro.getProperty("Password"));

				reportPom = new AuditTrailPom(driver, test, pro);
				reportPom.clickReports();
				reportPom.clickAuditTrail();
				reportPom.SelectPlantNameAuditTrail(plantName);
				reportPom.SelectUserName(pro.getProperty("userName"));
				reportPom.fromData(fromDate);
				reportPom.toData(toDate);
				reportPom.getButton.click();
				reportPom.searchType.sendKeys(plantName);

				test.log(Status.PASS, "Audit Trail Test completed for row: " + i);
			}
		} catch (Exception ex) {
			logger.error("Error occurred in Audit Trail Test: " + ex);
			test.log(Status.FAIL, "Error occurred in Audit Trail Test: " + ex.getMessage());
			throw ex;
		}
	}
}
