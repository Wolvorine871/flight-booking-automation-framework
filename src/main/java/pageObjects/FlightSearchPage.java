package pageObjects;

import java.time.Month;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import baseClass.BasePage;

public class FlightSearchPage extends BasePage {

	public FlightSearchPage(WebDriver driver) {
//		here this is to call constructor of parent
		super(driver);
	}

	@FindBy(xpath = "//label[normalize-space()='Flying From']/preceding::input[1]")
	WebElement flyingFromInput;

	@FindBy(xpath = "//label[normalize-space()='Destination To']/preceding::input[1]")
	WebElement destinationToInput;

	@FindBy(css = "button#flights-search")
	WebElement flightSearchBtn;

	@FindBy(xpath = "//div[contains(@class,'result-option')]")
	List<WebElement> airportSearchResults;

	@FindBy(id = "departure")
	WebElement departureDatePicker;
	
	@FindBy(css="div.loading-animation")
	WebElement loader;

	@FindBy(xpath = "//div[@class='datepicker dropdown-menu'][contains(@style,'block')]/div[@class='datepicker-days']//td[not(contains(@class, 'old'))]")
	List<WebElement> days;

	@FindBy(xpath = "//div[@class='datepicker dropdown-menu'][contains(@style,'block')]/div[@class='datepicker-months']//span")
	List<WebElement> months;

	@FindBy(xpath = "//div[@class='datepicker dropdown-menu'][contains(@style,'block')]/div[@class='datepicker-years']//span")
	List<WebElement> years;

	@FindBy(xpath = "//div[@class='datepicker dropdown-menu'][contains(@style,'block')]/div[contains(@class,'datepicker')][contains(@style,'block')]//th[@class='prev']")
	WebElement prevBtn;

	@FindBy(xpath = "//div[@class='datepicker dropdown-menu'][contains(@style,'block')]/div[contains(@class,'datepicker')][contains(@style,'block')]//th[@class='next']")
	WebElement nextBtn;

	@FindBy(xpath = "//div[@class='datepicker dropdown-menu'][contains(@style,'block')]/div[@class='datepicker-days']//th[@class='switch']")
	WebElement switchBtn;
	
	public boolean searchAndSelectFlyingFrom(String optionText) {
	    String searchText = optionText;

	    while (!searchText.isEmpty()) { // Keep retrying with shortened text
	        int attempts = 0;

	        // Step 1: Enter current search text into the field
	        try {
	            WebElement inputField = toBeClickable(flyingFromInput);
	            if (inputField != null) {
	                inputField.click();
	                Thread.sleep(2000);
	                inputField.clear();
	                Thread.sleep(2000);
	                inputField.sendKeys(searchText);
	                Thread.sleep(5000);
	            } else { 
	                System.err.println("ERROR ====>>> Flying From input not clickable.");
	                return false;
	            }
	        } catch (Exception e) {
	            System.err.println("ERROR ====>>> Failed while entering text in Flying From: " + e.getMessage());
	            return false;
	        }

	        // Step 2: Try finding & clicking the option
	        while (attempts < 2) { // retry once for transient issues
	            try {
	                List<WebElement> results = tillvisibilityOfAllElements(airportSearchResults);
	                Thread.sleep(2000);
	                for (WebElement airport : results) {
	                	Thread.sleep(2000);
	                    WebElement clickable = toBeClickable(airport);
	                    if (clickable != null && clickable.isDisplayed() && clickable.getText().contains(searchText)) {
	                    	Thread.sleep(2000);
	                        clickable.click();
	                        return true;
	                    }
	                }

	                System.out.println("WARN ====>>> No match found for: " + searchText + " (will retry with shorter text)");
	                break; // exit retry loop if no match

	            } catch (StaleElementReferenceException e) {
	                System.err.println("WARN ====>>> Stale element, retrying... Attempt " + (attempts + 1));
	            } catch (ElementClickInterceptedException e) {
	                System.err.println("WARN ====>>> Click intercepted, retrying... Attempt " + (attempts + 1));
	            } catch (TimeoutException e) {
	                System.err.println("ERROR ====>>> Airport search results not visible in expected time.");
	                return false;
	            } catch (Exception e) {
	                System.err.println("ERROR ====>>> Unexpected error while selecting flight option: " + e.getMessage());
	                return false;
	            }
	            attempts++;
	        }

	        // Step 3: Shorten search text and try again
	        searchText = searchText.substring(0, searchText.length() - 1);
	    }

	    System.err.println("ERROR ====>>> No matching option found after retries for: " + optionText);
	    return false;
	}
	
	public boolean searchAndSelectFlyingTo(String optionText) {
	    String searchText = optionText;

	    while (!searchText.isEmpty()) { // Keep retrying with shortened text
	        int attempts = 0;

	        // Step 1: Enter current search text into the field
	        try {
	            WebElement inputField = toBeClickable(destinationToInput);
	            if (inputField != null) {
	                inputField.click();
	                Thread.sleep(2000);
	                inputField.clear();
	                Thread.sleep(2000);
	                inputField.sendKeys(searchText);
	                Thread.sleep(5000);
	            } else { 
	                System.err.println("ERROR ====>>> Flying From input not clickable.");
	                return false;
	            }
	        } catch (Exception e) {
	            System.err.println("ERROR ====>>> Failed while entering text in Flying To: " + e.getMessage());
	            return false;
	        }

	        // Step 2: Try finding & clicking the option
	        while (attempts < 2) {
	            try {
	                List<WebElement> results = tillvisibilityOfAllElements(airportSearchResults);
	                Thread.sleep(2000);
	                for (WebElement airport : results) {
	                	Thread.sleep(2000);
	                    WebElement clickable = toBeClickable(airport);
	                    if (clickable != null && clickable.isDisplayed() && clickable.getText().contains(searchText)) {
	                    	Thread.sleep(2000);
	                        clickable.click();
	                        return true;
	                    }
	                }

	                System.out.println("WARN ====>>> No match found for: " + searchText + " (will retry with shorter text)");
	                break; // exit retry loop if no match

	            } catch (StaleElementReferenceException e) {
	                System.err.println("WARN ====>>> Stale element, retrying... Attempt " + (attempts + 1));
	            } catch (ElementClickInterceptedException e) {
	                System.err.println("WARN ====>>> Click intercepted, retrying... Attempt " + (attempts + 1));
	            } catch (TimeoutException e) {
	                System.err.println("ERROR ====>>> Airport search results not visible in expected time.");
	                return false;
	            } catch (Exception e) {
	                System.err.println("ERROR ====>>> Unexpected error while selecting flight option: " + e.getMessage());
	                return false;
	            }
	            attempts++;
	        }

	        searchText = searchText.substring(0, searchText.length() - 1);
	    }

	    System.err.println("ERROR ====>>> No matching option found after retries for: " + optionText);
	    return false;
	}

	public void clickOnSearchBtn() {
		try {
			WebElement clickSearch = toBeClickable(flightSearchBtn);
			clickSearch.click();
		} catch (Exception e) {
			System.err.println("ERROR ====>>> Element not clickable (clickOnSearchBtn) : " + e.getMessage());
		}
	}
	
	public void clickOnDepartureDate() {
		try {
			WebElement clickDatePicker = toBeClickable(departureDatePicker);
			clickDatePicker.click();
		} catch (Exception e) {
			System.err.println("ERROR ====>>> Element not clickable (clickOnDepartureDate) : " + e.getMessage());
		}
	}

	public String getMonth(String input) {
		return input.split(" ")[0];
	}

	public String getYear(String input) {
		return input.split(" ")[1];
	}

	public void selectDepartureDate(String date, String month, String year) throws InterruptedException {
		Thread.sleep(2000);
		if (Integer.parseInt(getYear(switchBtn.getText())) <= Integer.parseInt(year)) {
			while (Integer.parseInt(getYear(switchBtn.getText())) <= Integer.parseInt(year)) {
				if (getYear(switchBtn.getText()).equals(year)) {
					break;
				}
				Thread.sleep(1000);
				nextBtn.click();
				Thread.sleep(1000);
			}
			if (convertMonth(month).compareTo(convertMonth(getMonth(switchBtn.getText()))) >= 0) {
				while (convertMonth(month).compareTo(convertMonth(getMonth(switchBtn.getText()))) >= 0) {
					if (convertMonth(month).compareTo(convertMonth(getMonth(switchBtn.getText()))) == 0) {
						break;
					}
					Thread.sleep(1000);
					nextBtn.click();
					Thread.sleep(1000);
				}
			} else {
				while (convertMonth(month).compareTo(convertMonth(getMonth(switchBtn.getText()))) <= 0) {
					if (convertMonth(month).compareTo(convertMonth(getMonth(switchBtn.getText()))) == 0) {
						break;
					}
					Thread.sleep(1000);
					prevBtn.click();
					Thread.sleep(1000);
				}
			}
			while (convertMonth(month).compareTo(convertMonth(getMonth(switchBtn.getText()))) >= 0) {
				if (convertMonth(month).compareTo(convertMonth(getMonth(switchBtn.getText()))) == 0) {
					break;
				}
				Thread.sleep(1000);
				nextBtn.click();
				Thread.sleep(1000);
			}
		} else {
			while (Integer.parseInt(getYear(switchBtn.getText())) >= Integer.parseInt(year)) {
				if (getYear(switchBtn.getText()).equals(year)) {
					break;
				}
				Thread.sleep(1000);
				prevBtn.click();
				Thread.sleep(1000);
			}
			while (convertMonth(month).compareTo(convertMonth(getMonth(switchBtn.getText()))) <= 0) {
				if (convertMonth(month).compareTo(convertMonth(getMonth(switchBtn.getText()))) == 0) {
					break;
				}
				Thread.sleep(1000);
				prevBtn.click();
				Thread.sleep(1000);
			}
		}
		for (WebElement webElement : days) {
			if (webElement.getText().equals(date)) {
				webElement.click();
				break;
			}
			Thread.sleep(1000);
		}
	}

	public Month convertMonth(String month) {

		HashMap<String, Month> mapCal = new HashMap<>();
		mapCal.put("January", Month.JANUARY);
		mapCal.put("February", Month.FEBRUARY);
		mapCal.put("March", Month.MARCH);
		mapCal.put("April", Month.APRIL);
		mapCal.put("May", Month.MAY);
		mapCal.put("June", Month.JUNE);
		mapCal.put("July", Month.JULY);
		mapCal.put("August", Month.AUGUST);
		mapCal.put("September", Month.SEPTEMBER);
		mapCal.put("October", Month.OCTOBER);
		mapCal.put("November", Month.NOVEMBER);
		mapCal.put("December", Month.DECEMBER);

		return mapCal.get(month);
	}
	
	public boolean waitForLoaderToDisappear() {
	    try {
	    	return toBeInvisible(loader);
	    } catch (TimeoutException e) {
	        System.err.println("ERROR ====>>> Loader did not disappear within the expected time.");
	        return false;
	    }
		
	}
	
}
