package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"sanity","regression","master"})
	@Parameters({"browser"})
	public void setUp(String br) throws IOException { 
		 
		//Loading config.properties file
		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		switch(br.toLowerCase()) 
		{
		case "chrome" : driver = new ChromeDriver(); break;
		case "safari" : driver = new SafariDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		default : System.out.println("Invalid Browser name..."); return;
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	
	@AfterClass(groups= {"sanity","regression","master"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		  String generatedString = RandomStringUtils.randomAlphabetic(5);
		  return generatedString;
		}
		
	public String randomNumber() {
			  String generatedInteger = RandomStringUtils.randomNumeric(10);
			  return generatedInteger;
			}
	public String randomAlphaNumeric() {
			  String generatedString = RandomStringUtils.randomAlphabetic(5);
			  String generatedInteger = RandomStringUtils.randomNumeric(10);
			  return (generatedString+"#"+generatedInteger);
			}
	public String captureScreen(String tname) throws IOException{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourcefile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+ tname + "_" + timeStamp+".png";
		File targetFile = new File(targetFilePath);
		sourcefile.renameTo(targetFile);
		return targetFilePath;
	}
}
