package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import baseClass.BasePage;

public class FlightResultPage extends BasePage {

	public FlightResultPage(WebDriver driver) {
//		here this is to call constructor of parent
		super(driver);
	}	
	
	
	@FindBy(xpath="//h4[contains(@class,'new-main-tit')]")
	WebElement resultCount;
	
	@FindBy(xpath="//button[@data-value='desc']")
	WebElement descendingButton;
	
	@FindBy(xpath="//button[@data-value='asc']")
	WebElement ascendingButton;
	
	@FindBy(xpath="//input[contains(@id,'oneway_flights')]")
	WebElement stops;
	
	@FindBy(css="ul.pagination")
	WebElement pagination;
	//ul[@id='flight-list']/li
	
	@FindBy(xpath="//li[button[contains(text(),'»')]]")
	WebElement nextList;
	
	@FindBy(xpath="//ul[contains(@class,'pagination')]/li[last()]")
	WebElement nextbtn;
	
	@FindBy(xpath="//ul[contains(@class,'pagination')]/li[1]")
	WebElement prevbtn;
	
	@FindBy(xpath = "//ul[@id='flight-list']/li")
	private WebElement flightList;
	
	private By flightListLocator = By.xpath("//ul[@id='flight-list']/li");

	
	public String getFlightResult() {
	    String resultText = "";
	    try {
	        WebElement resultElement = toBeVisible(resultCount);
	        if (resultElement != null && resultElement.isDisplayed()) {
	            resultText = resultElement.getText().trim();
	        } else {
	            System.err.println("ERROR ====>>> Element not visible (getFlightResult).");
	        }
	    } catch (TimeoutException e) {
	        System.err.println("ERROR ====>>> Timeout waiting for flight result: " + e.getMessage());
	    } catch (Exception e) {
	        System.err.println("ERROR ====>>> Unexpected error in getFlightResult: " + e.getMessage());
	    }

	    return resultText;
	}

	
	public int getResultCount(String resultText) {
	    if (resultText == null || resultText.isEmpty()) {
	        System.err.println("ERROR ====>>> Result text is empty or null.");
	        return 0;
	    }

	    try {
	        String numberPart = resultText.split(" ")[0].replaceAll("[^0-9]", ""); // remove any non-digit chars
	        return Integer.parseInt(numberPart);
	    } catch (NumberFormatException e) {
	        System.err.println("ERROR ====>>> Failed to parse number from result text: " + resultText);
	        return 0;
	    }
	}
	
	public void navigateAllPages() throws InterruptedException {
	   
	    while (true) {
	        // Process current page (e.g., extract data)
	        System.out.println("Reading page...");

	        // Wait for pagination to be visible
	        WebElement paginationMenu=toBeVisible(pagination);

	        // Locate the Next button's parent <li> to check if it's disabled
	        String nextClass = nextList.getAttribute("class");

	        // If Next is disabled, break the loop
	        if (nextClass.contains("disabled")) {
	            System.out.println("Reached last page.");
	            break;
	        }

	        // Click the Next button
	        nextbtn.click();
	        Thread.sleep(2000);
	        // Wait for page content to load (adjust selector as needed)
	        List <WebElement> flightList=presenceofAllElement(flightListLocator);
	    }
	}
	
}
