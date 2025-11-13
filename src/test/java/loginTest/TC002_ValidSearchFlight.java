package loginTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.DashBoardPage;
import pageObjects.FlightResultPage;
import pageObjects.FlightSearchPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utility.DataProviders;

public class TC002_ValidSearchFlight extends BaseClass {
	SoftAssert softAssert;
	HomePage hp;
	LoginPage lp;
	DashBoardPage dp;
	FlightSearchPage fs;
	FlightResultPage fr;
	String strResult;
	
	@Test(dataProvider="AirportSearch", dataProviderClass=DataProviders.class, groups= {"sanity","master"})
	public void validFlightSearch(String source,String destination,String expectedResult) throws InterruptedException {
		logger.info("************ Starting TC002_ValidSearchFlight ************");
		try {
			//HomePage
			logger.info("Click on Customer Dropdown and then click on Login option.");
			softAssert = new SoftAssert();
			hp = new HomePage(driver);
			hp.clickCustomer();
			hp.clickLogin();

			//LoginPage
			logger.info("Validating Login Page");
			lp=new LoginPage(driver);
			Assert.assertEquals(lp.getTitle(), "Login");

			logger.info("Providing valid login credential");
			lp.setEmailAddress(get("validUser"));
			lp.setPassword(get("validPass"));
			lp.clickLoginBtn();
			
			Thread.sleep(5000);
			
			//DashBoard
			dp = new DashBoardPage(driver);
			Assert.assertEquals(dp.getTitle(), "Dashboard");
			logger.info("Validating Valid Login as ==>>> " + dp.getLoggedInUserName());			
			
			Thread.sleep(8000);
			
			Assert.assertTrue(dp.clickOnFlights());
			
			//Search Flights Page
			fs=new FlightSearchPage(driver);
			driver.navigate().refresh();
			
			Thread.sleep(8000);
			
			fs.waitForPageToLoad();
			Assert.assertEquals(fs.getTitle(), "Search for best Flights");
			logger.info("Navigated to ===>>> "+fs.getTitle()+" Page");
			
			logger.info("Select flight from airport");
			Thread.sleep(8000);
			Assert.assertTrue(fs.searchAndSelectFlyingFrom(source));
			
			Thread.sleep(5000);
			
			logger.info("Select destination to airport");
			Thread.sleep(8000);
			Assert.assertTrue(fs.searchAndSelectFlyingTo(destination));
			
			logger.info("Select date of flight");
			fs.clickOnDepartureDate();
			fs.selectDepartureDate("10", "September", "2025");
			
			Thread.sleep(5000);
			
			logger.info("Click on search button");
			fs.clickOnSearchBtn();
			
			if (expectedResult.equalsIgnoreCase("Found")) {
				Assert.assertTrue(fs.waitForLoaderToDisappear());
				fr=new FlightResultPage(driver);
				Assert.assertEquals(fr.getTitle(), "Flights Result");
				strResult=fr.getFlightResult();
				if (fr.getResultCount(strResult)==0) {
					logger.info("No results found ==> "+strResult);
				} else {
					logger.info(strResult);
				}
			} else if (expectedResult.equalsIgnoreCase("Not Found")) {
				Assert.assertTrue(fs.waitForLoaderToDisappear());
				fr=new FlightResultPage(driver);
				Assert.assertEquals(fr.getTitle(), "Flights Result");
				strResult=fr.getFlightResult();
				if (fr.getResultCount(strResult)==0) {
					logger.info("No results found ==> "+strResult);
				} else {
					logger.info(strResult);
				}
			} else if (expectedResult.equalsIgnoreCase("Duplicate")) {
				Assert.assertEquals(fr.getAlertMessage(), "Flying City and Destination City Can`t be same");
				logger.info(fr.getAlertMessage());
				Assert.assertTrue(fr.acceptAlert());	
			} else {
				
			}
			logger.info("Click on User Profile");
			dp.clickUserProfile();

			logger.info("Click on Log Out");
			dp.clickLogout();
			
			logger.info("Validating Logout");
			String headerMsg=hp.getSuccessMsg();
			String subMsg=hp.getSuccessSubMsg();
			
			softAssert.assertEquals(headerMsg, "Logout Successful");
			softAssert.assertEquals(subMsg, "You have been logout successfully");

			softAssert.assertAll();
		} catch (Exception e) {
			logger.error("Test Failed..");
			logger.debug("Debug logs....");
			softAssert.fail();
		}
		logger.info("************ Finished TC002_ValidSearchFlight ************");
	}
}
