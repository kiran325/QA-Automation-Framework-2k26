# QA-Automation-Framework-2k26
End-to-end automation framework for 2k26 project

A scalable and reusable automation testing framework developed using Java, Selenium WebDriver, TestNG, Maven and REST Assured to automate web application UI and REST API testing.

The framework follows the Page Object Model (POM) design pattern and incorporates reusable utilities, data-driven testing, reporting and test execution management.

1. Project Overview

This project demonstrates the design and implementation of a structured automation testing framework covering UI and API automation.
The framework is designed to provide:
Reusable and maintainable automation scripts.
Page Object Model (POM) implementation.
Data-driven testing.
Cross-browser testing support.
API request and response validation.
TestNG-based test execution and grouping.
Automated screenshots for failed test cases.
Detailed execution reports.
CI/CD integration.
Project Type: Hybrid Automation Framework (UI + API)

2. Tech Stack

Technology
Purpose
Java
Programming Language
Selenium WebDriver 4
UI Automation
TestNG
Test Execution Framework
Maven
Build and Dependency Management
REST Assured
API Automation
Apache POI
Excel Data Handling
Log4j2
Logging
Extent Reports
Test Reporting
Git
Version Control
GitHub
Source Code Management
Jenkins
CI/CD Integration
Docker
Containerized Test Execution

3. Framework Architecture

The framework follows a modular architecture based on the Page Object Model design pattern.
Architecture Components
Test Layer: Contains UI and API test classes.
Page Layer: Maintains web page locators and reusable page actions.
Base Layer: Handles WebDriver initialization and test setup.
Utility Layer: Contains reusable methods for waits, screenshots, Excel and configuration.
API Layer: Handles API specifications, requests and response validations
Configuration Layer: Maintains environment and browser configurations.
Reporting Layer: Generates execution reports and captures failures.
Execution Flow
TestNG → Test Classes → Page Objects / API Layer → Application → Assertions → Reports

4. Folder Structure

QA-Automation-Framework/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── base/
│   │       ├── pages/
│   │       ├── utils/
│   │       ├── config/
│   │       └── api/
│   │
│   └── test/
│       ├── java/
│       │   ├── tests/
│       │   │   ├── ui/
│       │   │   └── api/
│       │   └── listeners/
│       │
│       └── resources/
│           ├── testdata/
│           ├── config.properties
│           └── testng.xml
│
├── test-output/
├── screenshots/
├── reports/
├── pom.xml
├── README.md
└── .gitignore

5. Prerequisites

Make sure the following tools are installed:
Java JDK 17 or above.
Maven 3.8 or above.
IntelliJ IDEA or Eclipse.
Google Chrome or another supported browser.
Git.
GitHub account.

Verify the installations:
java -version
mvn -version
git --version

6. Installation Instructions
Step 1: Clone the Repository
git clone https://github.com/kiran325/QA-Automation-Framework-2k26.git

Step 2: Navigate to Project Directory
cd QA-Automation-Framework-2k26

Step 3: Import the Project
Import the project into IntelliJ IDEA or Eclipse as an existing Maven project.

Step 4: Install Dependencies
mvn clean install

Step 5: Configure Test Environment
Update the required application URL, browser and environment settings in:
src/test/resources/config.properties

7. How to Execute Tests
Execute All Tests
mvn clean test
Execute Smoke Tests
mvn clean test -Dgroups=smoke
Execute Regression Tests
mvn clean test -Dgroups=regression
Execute Using TestNG XML
mvn clean test -Dsurefire.suiteXmlFiles=testng.xml
Execute a Specific Test Class
mvn clean test -Dtest=LoginTest

8. Test Scenarios Covered
UI Automation
Login with valid credentials.
Login with invalid credentials.
Validate error messages.
Verify product listings.
Verify product sorting.
Add products to shopping cart.
Remove products from shopping cart.
Validate checkout functionality.
Verify order confirmation.
Validate logout functionality.
API Automation
Validate GET requests.
Validate POST requests.
Validate PUT and PATCH requests.
Validate DELETE requests.
Verify HTTP status codes.
Validate response body.
Validate response headers.
Validate response time.
Validate JSON response schema.
Handle authentication and authorization scenarios.
Framework-Level Testing
TestNG annotations and groups.
Data-driven testing using DataProvider.
Page Object Model implementation.
Reusable WebDriver methods.
Explicit waits.
TestNG listeners.
Retry failed test cases.
Screenshot capture on failure.
Logging and reporting.

9. Reports and Screenshots
The framework generates execution reports to provide visibility into automation results.

Reporting features include:
Total test cases executed.
Passed, failed and skipped test cases.

Execution duration.
Failure details.

Screenshots of failed UI test cases.
Detailed test execution logs.

Report Location:
reports/
screenshots/
test-output/

Note: Report examples and screenshots will be added as the framework implementation progresses.

10. Future Enhancements
The following enhancements are planned:
Parallel test execution.
Cross-browser execution.
Jenkins pipeline integration.
Docker-based test execution.
Environment-based test execution.
Advanced API automation.
Allure reporting integration.
Database validation.
Cloud-based browser execution.
Automated test execution scheduling.

Author
Kiran Kumar A L

Technical Skills: Java | Selenium | TestNG | Maven | REST Assured | API Testing | Jenkins | Git

GitHub: https://github.com/kirankk325

This is an evolving automation framework. New features, test scenarios and framework enhancements will be added continuously.
