package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import baseClass.BasePage;

public class DashBoardPage extends BasePage {

	public DashBoardPage(WebDriver driver) {
//		here this is to call constructor of parent
		super(driver);
	}

	@FindBy(xpath = "//div[@class='nav-item--right']//li[@class='nav-item dropdown'][2]/a")
	WebElement userProfileDropdown;

	@FindBy(xpath = "//a[normalize-space()='Logout']")
	WebElement logoutBtn;
	
	@FindBy(xpath="//a[normalize-space()='Flights']")
	WebElement flightLink;

	public void clickUserProfile() {
		try {
			WebElement clickCustomer = toBeClickable(userProfileDropdown);
			clickCustomer.click();
		} catch (Exception e) {
			System.err.println("ERROR ====>>> Element not clickable (clickUserProfile) : " + e.getMessage());
		}
	}

	public void clickLogout() {
		try {
			WebElement clickLogout = toBeClickable(logoutBtn);
			clickLogout.click();
		} catch (Exception e) {
			System.err.println("ERROR ====>>> Element not clickable (clickLogout) : " + e.getMessage());
		}
	}

	public String getLoggedInUserName() {
		String msg = "";
		try {
			WebElement userProfile = toBeVisible(userProfileDropdown);
			msg = userProfile.getText();

		} catch (Exception e) {
			System.err.println("ERROR ====>>> Element not visible (getLoggedInUserName) : " + e.getMessage());
		}
		return msg;
	}
	
	public boolean clickOnFlights() {
	    try {
	        WebElement clickFlight = toBeClickable(flightLink);
	        if (clickFlight != null) {
	            clickFlight.click();
	            return true;
	        } else {
	            System.err.println("ERROR ====>>> Element not clickable (clickOnFlights).");
	            return false;
	        }
	    } catch (Exception e) {
	        System.err.println("ERROR ====>>> Error clicking on flight: " + e.getMessage());
	        return false;
	    }
	}



}
