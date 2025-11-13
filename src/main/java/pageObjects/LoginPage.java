package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import baseClass.BasePage;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
//		here this is to call constructor of parent
		super(driver);
	}

	@FindBy(id = "email")
	WebElement txt_Email;

	@FindBy(id = "password")
	WebElement txt_Password;

	@FindBy(id = "submitBTN")
	WebElement btn_Login;

	@FindBy(xpath = "//div[@class='text-group']/h4")
	WebElement errorHeader;

	@FindBy(xpath = "//div[@class='text-group']/p")
	WebElement errorSubtitle;

	public String getInvErrMsg() {
		String msg = "";
		try {
			WebElement errMsgHead = toBeVisible(errorHeader);
			msg = errMsgHead.getText();

		} catch (Exception e) {
			System.out.println("ERROR ====>>> Element not visible (getInvErrMsg) : " + e.getMessage());
		}
		return msg;
	}

	public String getInvSubErrMsg() {
		String msg = "";
		try {
			WebElement errMsgSub = toBeVisible(errorSubtitle);
			msg = errMsgSub.getText();

		} catch (Exception e) {
			System.out.println("ERROR ====>>> Element not visible (getInvSubErrMsg) : " + e.getMessage());
		}
		return msg;
	}

	public void setEmailAddress(String email) {
		try {
			WebElement inputUser = toBeVisible(txt_Email);
			inputUser.sendKeys(email);
		} catch (Exception e) {
			System.out.println("ERROR ====>>> Element not visible (setEmailAddress) : " + e.getMessage());
		}
	}

	public void setPassword(String pass) {
		try {
			WebElement inputPass = toBeVisible(txt_Password);
			inputPass.sendKeys(pass);
		} catch (Exception e) {
			System.out.println("ERROR ====>>> Element not visible (setPassword) : " + e.getMessage());
		}
	}

	public void clickLoginBtn() {
		try {
			WebElement clickLogin = toBeClickable(btn_Login);
			clickLogin.click();
		} catch (Exception e) {
			System.out.println("ERROR ====>>> Element not clickable (clickLoginBtn) : " + e.getMessage());
		}
	}
}
