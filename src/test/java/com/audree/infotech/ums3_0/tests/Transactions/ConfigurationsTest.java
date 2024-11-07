package com.audree.infotech.ums3_0.tests.Transactions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.audree.infotech.ums3_0.pages.masters.ConfigurationsPom;
import com.audree.infotech.ums3_0.testcomponents.BaseTest;
import com.aventstack.extentreports.ExtentReports;

public class ConfigurationsTest extends BaseTest {
	public ConfigurationsPom configurationsPom;
	ExtentReports extent;
	int startRow;
	int endRow;

	private static final Logger logger = LogManager.getLogger(JobRoleTest.class);

	@Test
	public void testJobRoleCreation() {
		logger.info("Starting test for Job Role Creation...");
		startRow = Integer.parseInt(pro.getProperty("startRow"));
		endRow = Integer.parseInt(pro.getProperty("endRow"));
		
		loginTest(pro.getProperty("Admin"), pro.getProperty("Password"));

		configurationsPom = new ConfigurationsPom(driver, test, pro);
		configurationsPom.clickConfigurationsLink();
		configurationsPom.clickSoftwareFlowLink();

		try {
			for (int i = startRow; i <= endRow; i++) {
				// Fetch data from Excel
				String plantName = xls.getCellData("MasterData", "plantName", i);
				String softwareName = xls.getCellData("MasterData", "softwareName", i);
				String flowName1 = xls.getCellData("MasterData", "flowName1", i);
				String flowName2 = xls.getCellData("MasterData", "flowName2", i);
				String reviewer = xls.getCellData("MasterData", "reviewer", i);
				String finalReviewer = xls.getCellData("MasterData", "finalReviewer", i);
				String approver = xls.getCellData("MasterData", "approver", i);
				String creator = xls.getCellData("MasterData", "creator", i);

				logger.info("Row " + i + ": Processing data for Plant: " + plantName);
				// Job role flow
				configurationsPom.selectPlantByName(plantName);
				configurationsPom.selectSoftwareName(softwareName);
				configurationsPom.selectFlow(flowName1);
				configurationsPom.getButton();
				//scrollElementIntoView(configurationsPom.trueCheckbox);
				configurationsPom.clickTrueCheckbox();
				scrollPagedown();
				configurationsPom.saveButton();
				configurationsPom.yesButton();
				configurationsPom.passwordFill();
				configurationsPom.submitButton();
				configurationsPom.okButton.click();
				// Work Flow flow
				configurationsPom.selectFlow(flowName2);
				configurationsPom.getButton();
				configurationsPom.initiatorDepartment();
				configurationsPom.selectReviewer(reviewer);
				configurationsPom.selectFinalReviewer(finalReviewer);
				configurationsPom.selectApprover(approver);
				configurationsPom.selectCreator(creator);
				scrollPagedown();
				configurationsPom.saveButton();
				configurationsPom.yesButton();
				configurationsPom.passwordFill();
				configurationsPom.submitButton();
				configurationsPom.okButton.click();;
				logger.info("Row " + i + ": Configuration Creation Completed Successfully.");
			}
		} catch (Exception ex) {
			logger.error("An error occurred during Job Role Creation: ", ex);
		} finally {
			xls.closeWorkbook();
			logger.info("Excel workbook closed successfully.");
		}
	}
}
