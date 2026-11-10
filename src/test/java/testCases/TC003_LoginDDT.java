package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="datadriven")// getting DataProvider from different Class
	public void verify_loginDDT(String email, String password, String exp)
	{
		logger.info("***** Starting Login_DDT Test ****");
		
		try {
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
			
		//LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.SetEmail(email);
		lp.SetPassword(password);
		lp.clickLogin();
		
		//MyAccount
		MyAccountPage macc= new MyAccountPage(driver);
		boolean targetPage= macc.isMyaccountPageExists();
		
		if(exp.equalsIgnoreCase("Valid")) {
			if(targetPage==true) {
				macc.clicklogout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid")) {
			if(targetPage==true) {
				macc.clicklogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
				
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("***** Finished Login_DDT Test ****");
	}
}
