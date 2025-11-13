package baseClass;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	public WebDriver driver;

//	private static final Duration SHORT_TIMEOUT = Duration.ofSeconds(10);   // quick waits
	private static final Duration MEDIUM_TIMEOUT = Duration.ofSeconds(30);  // standard waits
	private static final Duration LONG_TIMEOUT = Duration.ofSeconds(40);    // long waits

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement toBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, LONG_TIMEOUT);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public WebElement toBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, MEDIUM_TIMEOUT);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public List<WebElement> presenceofAllElement(By element) {
		WebDriverWait wait = new WebDriverWait(driver, MEDIUM_TIMEOUT);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
	}

	public List<WebElement> tillvisibilityOfAllElements(List<WebElement> element) {
		WebDriverWait wait = new WebDriverWait(driver, MEDIUM_TIMEOUT);
		return wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public String getTitle() {
		String text = "";
		try {
			driver.manage().timeouts().pageLoadTimeout(MEDIUM_TIMEOUT);
		} catch (Exception e) {
			System.err.println("ERROR ====>>> Title not Found: " + e.getMessage());
		} finally {
			text = driver.getTitle();
		}
		return text;
	}

	public String getCurrentURL() {
		String url = "";
		try {
			driver.manage().timeouts().pageLoadTimeout(MEDIUM_TIMEOUT);
		} catch (Exception e) {
			System.err.println("ERROR ====>>> URL not Found: " + e.getMessage());
		} finally {
			url = driver.getCurrentUrl();
		}
		return url;
	}

	public boolean waitForTitle(String expectedTitle) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, MEDIUM_TIMEOUT);
			return wait.until(ExpectedConditions.titleIs(expectedTitle));
		} catch (TimeoutException e) {
			System.err.println("ERROR ====>>> Timeout: Expected title '" + expectedTitle + "' not found.");
		} catch (Exception e) {
			System.err.println("ERROR ====>>> Unexpected error while waiting for title: " + e.getMessage());
		}
		return false;
	}

	public void pageLoadTime(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(MEDIUM_TIMEOUT);
	}

	public Boolean toBeInvisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, LONG_TIMEOUT);
		return wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitForPageToLoad() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, LONG_TIMEOUT);

	        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

	    } catch (TimeoutException e) {
	        System.err.println("ERROR ====>>> Page did not load within 30 seconds.");
	    } catch (Exception e) {
	        System.err.println("ERROR ====>>> Unexpected error while waiting for page to load: " + e.getMessage());
	    }
	}
	
	public String getAlertMessage() {
	    try {
	        return driver.switchTo().alert().getText();
	    } catch (NoAlertPresentException e) {
	        System.out.println("No alert present: " + e.getMessage());
	        return null;
	    }
	}

	public boolean acceptAlert() {
	    try {
	        driver.switchTo().alert().accept();
	        return true;
	    } catch (NoAlertPresentException e) {
	        System.out.println("No alert present: " + e.getMessage());
	        return false;
	    }
	}

}
