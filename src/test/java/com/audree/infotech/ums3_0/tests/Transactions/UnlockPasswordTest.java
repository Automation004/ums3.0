package com.audree.infotech.ums3_0.tests.Transactions;

import org.testng.annotations.Test;
import com.audree.infotech.ums3_0.pages.transcations.ResetPasswordPom;
import com.audree.infotech.ums3_0.testcomponents.BaseTest;
import com.aventstack.extentreports.Status;

public class UnlockPasswordTest extends BaseTest {
	ResetPasswordPom unlockPasswordPom;
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

				unlockPasswordPom = new ResetPasswordPom(driver, test, pro);
				loginTest(pro.getProperty("Initiator"), pro.getProperty("Password"));

				unlockPasswordPom.clickUnlockPassword();
				unlockPasswordPom.plantNameDropdown(plantName);
				unlockPasswordPom.selectSoftwareName(softwareName);
				System.out.println(softwareName);
				unlockPasswordPom.submitButton1.click();
				unlockPasswordPom.clickYes();
				unlockPasswordPom.submitButton2.click();
				unlockPasswordPom.enterPassword(pro.getProperty("Password"));
				unlockPasswordPom.submitButton2.click();
				test.log(Status.PASS, "Clicked Submit Button Successfully");
				unlockPasswordPom.okButton.click();
				test.log(Status.PASS, "Clicked Ok Button Successfully");
				logger.info("Unlock Password creation completed successfully.");
			}
		} catch (Exception ex) {
			System.out.println("Error Occured at Unlock Password Of Initiator :" + ex);
		}
	}
}
