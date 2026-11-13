
package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"sanity", "regression", "master"})
    @Parameters({"os", "browser"})
    public void setUp(String os, String br) throws IOException {

        // Loading config.properties file
        try (FileReader file = new FileReader("./src/test/resources/config.properties")) {

            p = new Properties();
            p.load(file);
        }

        logger = LogManager.getLogger(this.getClass());

        // Reading execution environment from properties
        String executionEnv = p.getProperty("execution_env");

        // 1. Remote Execution Logic (Selenium Grid)
        if (executionEnv.equalsIgnoreCase("remote")) {

            DesiredCapabilities capabilities = new DesiredCapabilities();

            // Set Operating System Platform
            if (os.equalsIgnoreCase("windows")) { capabilities.setPlatform(Platform.WINDOWS);
            }
            else if (os.equalsIgnoreCase("mac")) { capabilities.setPlatform(Platform.MAC);
            }
            else if (os.equalsIgnoreCase("linux")) { capabilities.setPlatform(Platform.LINUX);
            }
            else { throw new IllegalArgumentException("Invalid OS specified: " + os);
            }

            // Set Browser
            switch (br.toLowerCase()) {

                case "chrome": capabilities.setBrowserName("chrome"); break;
                case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
                case "firefox": capabilities.setBrowserName("firefox"); break;
                default: throw new IllegalArgumentException("Invalid browser specified: " + br);
            }

            // Initialize RemoteWebDriver
            String gridURL = p.getProperty("grid_url");
            driver = new RemoteWebDriver(new URL(gridURL), capabilities);
        }

        // 2. Local Execution Logic
        else if (executionEnv.equalsIgnoreCase("local")) {

            switch (br.toLowerCase()) {

                case "chrome": driver = new ChromeDriver(); break;
                case "edge": driver = new EdgeDriver(); break;
                case "firefox": driver = new FirefoxDriver(); break;
                default: throw new IllegalArgumentException("Invalid browser specified: " + br);
            }
        }

        else {
            throw new IllegalArgumentException(
                    "Invalid execution environment: " + executionEnv);
        }

        // 3. Common Browser Configuration (Local + Remote)
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appURL"));
        driver.manage().window().maximize();
        logger.info("Browser launched successfully: " + br);
        logger.info("Execution environment: " + executionEnv);
    }

    @AfterClass(groups = {"sanity", "regression", "master"})
    public void tearDown() {

        if (driver != null) {
            driver.quit();
            logger.info("Browser closed successfully");
        }
    }

    // Generate Random String
    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    // Generate Random Number
    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    // Generate Random Alphanumeric
    public String randomAlphaNumeric() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        String generatedInteger = RandomStringUtils.randomNumeric(10);
        return generatedString + "#" + generatedInteger;
    }

    // Capture Screenshot
    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        // Create screenshots directory
        String targetDirectory = System.getProperty("user.dir") + File.separator + "screenshots";
        Files.createDirectories(Paths.get(targetDirectory));
        // Create target screenshot path
        String targetFilePath = targetDirectory + File.separator + tname + "_" + timeStamp + ".png";
        Path targetPath = Paths.get(targetFilePath);
        // Copy screenshot to target location
        Files.copy(sourceFile.toPath(), targetPath,StandardCopyOption.REPLACE_EXISTING);

        return targetFilePath;
    }
}
