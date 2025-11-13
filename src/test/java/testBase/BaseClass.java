package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public Logger logger; // Log4j
	public WebDriver driver;
	public static Properties properties;
	public FileInputStream fis;

	@BeforeClass(groups= {"sanity","regression","master"})
	public void readConfigPropertyFile() throws IOException {
		String configFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";
		try {
			logger = LogManager.getLogger(this.getClass());
			logger.info("Reading config.properties file located at..." + configFilePath);
			properties = new Properties();
			fis = new FileInputStream(configFilePath);
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String get(String key) {
		logger.info("Get value for key : " + key + " =====>>> " + properties.getProperty(key));
		return properties.getProperty(key);
	}

	@Parameters({ "os", "browser" })
	@BeforeClass(groups= {"sanity","regression","master"})
	public void setUp(String os, String br) {
		logger.info("Initializing browser and navigating to site...");

		switch (br.toLowerCase()) {
		case "chrome":
			logger.info("Launching chrome...");
			driver = new ChromeDriver();
			break;
		case "edge":
			logger.info("Launching edge...");
			driver = new EdgeDriver();
			break;
		case "firefox":
			logger.info("Launching firefox...");
			driver = new FirefoxDriver();
			break;
		default:
			logger.error("Invalid browser name...");
			return;
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.get(get("appurl"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		logger.info("Closing the browser...");
		driver.quit();
	}

	@AfterClass
	public void closeFile() throws IOException {
		logger.info("Closing the file...");
		fis.close();
	}

}
