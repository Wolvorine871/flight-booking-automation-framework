package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import baseClass.BasePage;

public class HomePage extends BasePage{
	BasePage bp;
	public HomePage(WebDriver driver){
//		here this is to call constructor of parent
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='Customer']")
	WebElement customerdropdown;
	
	@FindBy(xpath="//span[normalize-space()='Customer']/ancestor::li//a[normalize-space()='Login']")
	WebElement loginbtn;
	
	@FindBy(xpath="//div[@class='text-group']/h4")
	WebElement SuccessMsgHeader;
	
	@FindBy(xpath="//div[@class='text-group']/p")
	WebElement SuccessMsgSubtitle;
	
	
	public void clickCustomer() {
		try {
			WebElement clickCustomer=toBeClickable(customerdropdown);
			clickCustomer.click();
		} catch (Exception e) {
			System.err.println("ERROR ====>>> Element not clickable (clickCustomer) : "+e.getMessage());
		}
	}
	
	public void clickLogin() {
		try {
			WebElement clickLogin=toBeClickable(loginbtn);
			clickLogin.click();
		} catch (Exception e) {
			System.err.println("ERROR ====>>> Element not clickable (clickLogin) : "+e.getMessage());
		}
	}
	
	
	public String getSuccessMsg() {	
		String msg="";
		try {
			WebElement successmsgHead=toBeVisible(SuccessMsgHeader);
			msg=successmsgHead.getText();
			
		} catch (Exception e) {
			System.err.println("ERROR ====>>> Element not visible (getSuccessMsg) : "+e.getMessage());
		}
		return msg;
	}
	
	public String getSuccessSubMsg() {
		String msg="";
		try {
			WebElement successMsgSub=toBeVisible(SuccessMsgSubtitle);
			msg=successMsgSub.getText();
			
		} catch (Exception e) {
			System.err.println("ERROR ====>>> Element not visible (getSuccessSubMsg) : "+e.getMessage());
		}
		return msg;
	}
}
