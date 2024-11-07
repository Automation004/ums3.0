package com.audree.infotech.ums3_0.tests.Transcations.ReturnFlow;

import com.audree.infotech.ums3_0.pages.transcations.ResetPasswordPom;
import com.audree.infotech.ums3_0.testcomponents.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class ResetPasswordTest extends BaseTest {
	ResetPasswordPom resetPasswordPom;
	int startRow;
	int endRow;
	private static final Logger logger = LogManager.getLogger(ResetPasswordTest.class);

	@Test
	public void testResetPassword() throws Exception {
		logger.info("Starting test for Reset Password Of Initiator");
		startRow = Integer.parseInt(pro.getProperty("startRow"));
		endRow = Integer.parseInt(pro.getProperty("endRow"));
		try {
			for (int i = startRow; i <= endRow; i++) {
				// Fetch data from Excel
				String softwareName = xls.getCellData("MasterData", "softwareName", i);
				String plantName = xls.getCellData("MasterData", "plantName", i);

				resetPasswordPom = new ResetPasswordPom(driver, test, pro);
				loginTest(pro.getProperty("Initiator"), pro.getProperty("Password"));

				resetPasswordPom.clickResetPassword();
				resetPasswordPom.plantNameDropdown(plantName);
				resetPasswordPom.selectSoftwareName(softwareName);
				System.out.println(softwareName);
				resetPasswordPom.submitButton1.click();
				resetPasswordPom.clickYes();
				resetPasswordPom.submitButton2.click();
				resetPasswordPom.enterPassword(pro.getProperty("Password"));
				resetPasswordPom.submitButton2.click();
				resetPasswordPom.clickClose();
				logger.info("Job Role creation completed successfully.");
			}
		} 
		catch (Exception ex) {
			System.out.println("Error Occured at Reset Password Of Initiator :" + ex);
		}
	}
}
