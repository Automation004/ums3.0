package com.audree.infotech.ums3_0.tests.masters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.audree.infotech.ums3_0.pages.masters.SoftwarePom;
import com.audree.infotech.ums3_0.testcomponents.BaseTest;
import com.aventstack.extentreports.ExtentReports;

public class SoftwareTest extends BaseTest {
	public SoftwarePom softwarePom;
	ExtentReports extent;
	int startRow;
	int endRow;
	// Initialize Log4j logger
	private static final Logger logger = LogManager.getLogger(SoftwareTest.class);
	
	@Test
	public void testSoftwareCreation() {
		logger.info("Starting test for Software Creation...");
		startRow = Integer.parseInt(pro.getProperty("startRow"));
		endRow = Integer.parseInt(pro.getProperty("endRow"));
		try {
			for (int i = startRow; i <= endRow; i++) {
				// Fetch data from Excel
				String plantName = xls.getCellData("MasterData", "plantName", i);
				String softwareName = xls.getCellData("MasterData", "softwareName", i);
				String softwareVersion = xls.getCellData("MasterData", "softwareVersion", i);
				String softwareDescription = xls.getCellData("MasterData", "softwareDescription", i);
				String softwareOwner = xls.getCellData("MasterData", "softwareOwner", i);
				String instrument = xls.getCellData("MasterData", "instrument", i);
				String licenseCount = xls.getCellData("MasterData", "licenseCount", i);
				String noOfApprovals = xls.getCellData("MasterData", "noOfApprovals", i);

				logger.info("Row " + i + ": Processing data for Plant: " + plantName);
				softwarePom = new SoftwarePom(driver, test, pro);
				loginTest();
				// Perform the actions in sequence
				softwarePom.clickMasters();
				softwarePom.clickSoftware();
				softwarePom.selectPlant(plantName);
				softwarePom.enterSoftwareName(softwareName);
				softwarePom.enterSoftwareVersion(softwareVersion);
				softwarePom.enterSoftwareDescription(softwareDescription);
				softwarePom.selectSoftwareOwner(softwareOwner);
				softwarePom.enterInstrument(instrument);
				softwarePom.applicapableDepartment(softwareOwner);
				softwarePom.selectNoOfApproverLevels(noOfApprovals);
				softwarePom.enterLicenseCount(licenseCount);
				softwarePom.submitEsigantureActions(false);
				logger.info("Row " + i + ": Software Creation Completed Successfully.");
			}
			
		} catch (Exception ex) {
			logger.error("An error occurred during Software Creation: ", ex);
		} finally {
			// Closing the workbook after reading all the data
			xls.closeWorkbook();
			logger.info("Excel workbook closed successfully.");
		}
	}
}
