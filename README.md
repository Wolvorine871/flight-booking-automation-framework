
# PHPTravels Automation Framework

## Overview
PHPTravels Automation Framework is a Proof of Concept (POC) automation framework for testing the functionalities of the PHPTravels web application (https://phptravels.com). It uses Selenium WebDriver, TestNG, and supports detailed reporting for automated UI testing.

## Features
- Automated UI tests for login, flight search, booking, and dashboard pages
- Page Object Model (POM) design pattern
- Data-driven testing using TestNG and Excel files
- HTML and XML test reports
- Log4j logging for test execution
- Screenshots on failure

## Project Structure
PHPTravels-Automation-Framework/
  src/
	 main/java/baseClass/           # Base page and setup
	 main/java/pageObjects/         # Page Object Model classes
	 main/java/utility/             # Utilities (Excel, logging, etc.)
	 resources/                     # Resource files
  test/java/loginTest/             # TestNG test cases
  test/java/testBase/              # Base test class
  test/resources/                  # Test resources (config, log4j)
  testData/                        # Test data files
  logs/                            # Log files
  reports/                         # Test reports
  screenShots/                     # Screenshots on failure
  test-output/                     # TestNG output
  pom.xml                          # Maven build file

## Setup & Installation
1. Clone the repository:
	```
	git clone <repo-url>
	```
2. Import as Maven Project
3. Open in Eclipse/IntelliJ/VS Code
4. Ensure Maven is installed
5. Configure properties:
	- Edit `test/resources/config.properties` for browser, URL, etc.
6. Install dependencies:
	- Maven will auto-download dependencies from `pom.xml`

## Running Tests
To run all tests:
```
mvn clean test
```
To run specific TestNG suites:
```
mvn test -DsuiteXmlFile=master.xml
mvn test -DsuiteXmlFile=grouping.xml
```
Test reports are generated in `test-output/emailable-report.html` and `test-output/index.html`

## Dependencies
Main dependencies (see `pom.xml`):
- Selenium Java
- TestNG
- WebDriverManager
- Log4j
- Apache POI (Excel data)
- Commons IO


## Author
Deepak Jain (deepak.j)

## License
This project is for demonstration and educational purposes.
