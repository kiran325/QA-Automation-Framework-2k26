package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups={"regression", "master"})
	void verify_account_registration() {
		
		logger.info("**** Starting TC001_AccountRegistrationTest");
		
		try {
		HomePage hp = new  HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("Providing Customer details");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String pwd = randomAlphaNumeric();
		regpage.setPassword(pwd);
		regpage.setpwdConfirm(pwd);
		regpage.setNewsletter();
		regpage.setAgree();
		regpage.setContinue();
		
		logger.info("Validating expected message");
		String confirmMeg = regpage.getConfirmationMsg();
		if(confirmMeg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Failed: Exception occurred during account registration");
			logger.debug("debug logs...");
			Assert.assertTrue(false);
		}
		
		}
		catch(Exception e) {
		    Assert.fail("Account registration test failed: " + e.getMessage());
		}
		
		logger.info("**** Finished TC001_AccountRegistrationTest");
	}
	

}
