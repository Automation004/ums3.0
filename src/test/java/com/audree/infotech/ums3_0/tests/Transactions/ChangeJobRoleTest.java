package com.audree.infotech.ums3_0.tests.Transactions;

import org.testng.annotations.Test;
import com.audree.infotech.ums3_0.pages.transcations.ResetPasswordPom;
import com.audree.infotech.ums3_0.testcomponents.BaseTest;

public class ChangeJobRoleTest extends BaseTest {
	ResetPasswordPom changeJobRolePom;
	int startRow;
	int endRow;

	@Test
	public void testResetPassword() throws Exception {
		logger.info("Starting test for Unlock Password Of Initiator");
		startRow = Integer.parseInt(pro.getProperty("startRow"));
		endRow = Integer.parseInt(pro.getProperty("endRow"));
		try {
			for (int i = startRow; i <= endRow; i++) {
				// Fetch data from Excel
				String softwareName = xls.getCellData("MasterData", "softwareName", i);
				String plantName = xls.getCellData("MasterData", "plantName", i);
				String jobRoleName = xls.getCellData("MasterData", "jobRoleName", i);

				changeJobRolePom = new ResetPasswordPom(driver, test, pro);
				loginTest(pro.getProperty("Initiator"), pro.getProperty("Password"));

				changeJobRolePom.ClickchangeJobRole();
				changeJobRolePom.plantNameDropdown(plantName);
				changeJobRolePom.selectSoftwareName(softwareName);
				System.out.println(softwareName);
				changeJobRolePom.jobRole(jobRoleName);
				changeJobRolePom.submitButton1.click();
				changeJobRolePom.clickYes();
				changeJobRolePom.submitButton2.click();
				changeJobRolePom.enterPassword(pro.getProperty("Password"));
				changeJobRolePom.submitButton2.click();
				changeJobRolePom.okButton.click();
				logger.info("Unlock Password creation completed successfully.");
			}
		} catch (Exception ex) {
			System.out.println("Error Occured at Unlock Password Of Initiator :" + ex);
		}
	}
}
