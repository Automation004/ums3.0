package com.audree.infotech.ums3_0.tests.Transactions;
import org.testng.annotations.Test;
import com.audree.infotech.ums3_0.pages.masters.SoftwarePom;
import com.audree.infotech.ums3_0.testcomponents.BaseTest;
import com.aventstack.extentreports.ExtentReports;

public class SoftwareTest extends BaseTest {
	public SoftwarePom softwarePom;
	ExtentReports extent;
	int startRow;
	int endRow;

	@Test
	public void testSoftwareCreation() throws Exception {
		logger.info("Starting test for Software Creation...");
		startRow = Integer.parseInt(pro.getProperty("startRow"));
		endRow = Integer.parseInt(pro.getProperty("endRow"));

		loginTest(pro.getProperty("Admin"), pro.getProperty("Password"));
		softwarePom = new SoftwarePom(driver, test, pro);

		// Perform the actions in sequence
		softwarePom.clickMasters();
		softwarePom.clickSoftware();
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
				softwarePom.clickCreate();
				softwarePom.selectPlant(plantName);
				softwarePom.enterSoftwareName(softwareName);
				softwarePom.enterSoftwareVersion(softwareVersion);
				softwarePom.enterSoftwareDescription(softwareDescription);
				softwarePom.selectSoftwareOwner(softwareOwner);
				scrollElementIntoView(softwarePom.licenseCountInput);
				softwarePom.enterInstrument(instrument);
				softwarePom.applicapableDepartment(softwareOwner);
				softwarePom.selectNoOfApproverLevels(noOfApprovals);
				softwarePom.enterLicenseCount(licenseCount);
				softwarePom.submitEsignatureActions(false);
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

	@Test
	public void testSoftwareUpdation() {

		logger.info("Starting test for Software Creation...");
		try {
			for (int i = startRow; i <= endRow; i++) {
				// Fetch data from Excel
				String softwareName = xls.getCellData("MasterData", "softwareName", i);
				String softwareVersionUpdate = xls.getCellData("MasterData", "softwareVersionUpdate", i);
				String softwareDescriptionUpdate = xls.getCellData("MasterData", "softwareDescriptionUpdate", i);
				String softwareOwnerUpdate = xls.getCellData("MasterData", "softwareOwnerUpdate", i);
				String instrumentUpdate = xls.getCellData("MasterData", "instrumentUpdate", i);
				String licenseCount = xls.getCellData("MasterData", "licenseCount", i);
				String noOfApprovalsUpdate = xls.getCellData("MasterData", "noOfApprovalsUpdate", i);
				String comments = xls.getCellData("MasterData", "comments", i);

				softwarePom = new SoftwarePom(driver, test, pro);

				softwarePom.searchPlaceHolder(softwareName);
				softwarePom.editActionButton();
				softwarePom.enterSoftwareVersion(softwareVersionUpdate);
				softwarePom.enterSoftwareDescription(softwareDescriptionUpdate);
				softwarePom.selectSoftwareOwner(softwareOwnerUpdate);
				softwarePom.enterInstrument(instrumentUpdate);
				softwarePom.selectNoOfApproverLevels(noOfApprovalsUpdate);
				scrollElementIntoView(softwarePom.licenseCountInput);
				softwarePom.addLicenseCount(licenseCount);// here not updating just adding only
				softwarePom.enterComments(comments);
				softwarePom.updateEsignatureActions(false);
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
