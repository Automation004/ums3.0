package com.audree.infotech.ums3_0.tests.Transactions;

import org.testng.annotations.Test;
import com.audree.infotech.ums3_0.pages.transcations.ResetPasswordPom;
import com.audree.infotech.ums3_0.testcomponents.BaseTest;

public class InActiveTest extends BaseTest {
	ResetPasswordPom inActivePom;
	int startRow;
	int endRow;

	@Test
	public void testResetPassword() throws Exception {
		logger.info("Starting test for In Active in Initiator");
		startRow = Integer.parseInt(pro.getProperty("startRow"));
		endRow = Integer.parseInt(pro.getProperty("endRow"));
		try {
			for (int i = startRow; i <= endRow; i++) {
				// Fetch data from Excel
				String softwareName = xls.getCellData("MasterData", "softwareName", i);
				String plantName = xls.getCellData("MasterData", "plantName", i);

				inActivePom = new ResetPasswordPom(driver, test, pro);
				loginTest(pro.getProperty("Initiator"), pro.getProperty("Password"));

				inActivePom.ClickInActive();
				inActivePom.plantNameDropdown(plantName);
				inActivePom.selectinputInActive("Self");
				inActivePom.selectSoftwareNameMultiSelect(softwareName);
				inActivePom.submitButton1.click();
				inActivePom.clickYes();
				inActivePom.submitButton2.click();
				inActivePom.enterPassword(pro.getProperty("Password"));
				inActivePom.submitButton2.click();
				inActivePom.okButton.click();
				logger.info("In Active creation completed successfully.");
			}
		} catch (Exception ex) {
			System.out.println("Error Occured at Unlock Password Of Initiator :" + ex);
		}
	}
}
