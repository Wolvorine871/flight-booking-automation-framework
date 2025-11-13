package loginTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.DashBoardPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utility.DataProviders;

public class TC001_LoginTest extends BaseClass{
	
	SoftAssert softAssert;
	HomePage hp;
	LoginPage lp;
	DashBoardPage dp;
	
	@Test(priority=1,dataProvider="LoginData", dataProviderClass=DataProviders.class, groups= {"regression","master"})
	public void validLogin(String email,String pass,String expectedResult) throws InterruptedException {
		logger.info("************ Starting TC001_LoginTest ************");
		try {
			//HomePage
			logger.info("Click on Customer Dropdown and then click on Login option.");
			softAssert=new SoftAssert();
			hp=new HomePage(driver);
			hp.clickCustomer();
			hp.clickLogin();
			
			
			//LoginPage
			logger.info("Validating Login Page");
			lp=new LoginPage(driver);
			Assert.assertEquals(lp.getTitle(), "Login");
			
			logger.info("Providing valid login credential");
			lp.setEmailAddress(email);
			lp.setPassword(pass);
			lp.clickLoginBtn();
			
			Thread.sleep(5000);
			
//			Data is valid	--	1.	Login Success	->	TestPass	->	Logout
//								2.	Login Fail		->	TestFail

//			Data is Invalid --	1.	Login Fail		->	TestPass	
//								2.	Login Success	->	TestFail	->	Logout
			
			//DashBoardPage
			dp=new DashBoardPage(driver);
			if (expectedResult.equalsIgnoreCase("Valid")) {
				if (dp.getTitle().equalsIgnoreCase("Dashboard")) {
					logger.info("Validating Valid Login as ==>>> " + dp.getLoggedInUserName());
					logger.info("Validate Logout");
					dp.clickUserProfile();
					dp.clickLogout();
					logger.info("Expected error message when invalid credential is provided ==> "+hp.getSuccessMsg());
					logger.info("Expected error message when invalid credential is provided ==> "+hp.getSuccessSubMsg());
					Assert.assertEquals(hp.getSuccessMsg() , "Logout Successful" );
					Assert.assertEquals(hp.getSuccessSubMsg() , "You have been logout successfully");
				}else {
					logger.error("Expected [Dashboard] but ["+lp.getInvErrMsg()+"]");
					Assert.assertEquals(lp.getInvErrMsg(),"Dashboard");
				}
			}
			if (expectedResult.equalsIgnoreCase("Invalid")) {
				if (dp.getTitle().equalsIgnoreCase("Dashboard")) {
					logger.info("Validating Invalid Login as ==>>> " + dp.getLoggedInUserName());		
					String actualRes=dp.getTitle();
					logger.info("Validate Logout");
					dp.clickUserProfile();
					dp.clickLogout();
					logger.error("Expected [Invalid Login] but ["+actualRes+"]");
					Assert.assertEquals(actualRes , "Invalid Login");
				} else {
					logger.info("Validating Invalid Login");
					logger.info("Expected error message when invalid credential is provided ==> "+lp.getInvErrMsg());
					logger.info("Expected error message when invalid credential is provided ==> "+lp.getInvSubErrMsg());
					Assert.assertEquals(lp.getInvErrMsg() , "Invalid Login" );
					Assert.assertEquals(lp.getInvSubErrMsg() , "Please check your email and password");
				}
			}
		} catch (Exception e) {
			logger.error("Test Failed..");
			logger.debug("Debug logs....");
			softAssert.fail();
		}
		logger.info("************ Finished TC001_LoginTest ************");
	}
}
