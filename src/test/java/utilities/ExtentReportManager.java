package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.URL;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		/* SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentdatetimestamp=df.format(dt); */
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
		
		repName = "Test-Report-"+ timeStamp + ".html";
		
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+ repName); //specify location of report file
		sparkReporter.config().setDocumentTitle("Automation Report"); // Title of the report
		sparkReporter.config().setReportName("Functional Testing"); // Name of the report
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","opencart");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("Sub Module","Customers");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os= testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser= testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); // create a new entry in the report
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report 
		test.log(Status.PASS, "Test case PASSED is:"+ result.getName()); // update status p/f/s
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName()); // create a new entry in the report
		test.assignCategory(result.getMethod().getGroups()); 
		test.log(Status.FAIL, "Test Case FAILED is:"+ result.getName()); // update status p/f/s
		test.log(Status.INFO, result.getThrowable().getMessage());// display console Error
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch(IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName()); // create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());  
		test.log(Status.SKIP, "Test case SKIPPED is:"+ result.getName()); // update status p/f/s
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) {
        extent.flush(); // IMPORTANT: Write all report data into HTML file
        System.out.println("Extent Report Generated Successfully");
        String pathExtentReport = System.getProperty("user.dir")+ File.separator + "reports"+ File.separator + repName;
        File extentReport = new File(pathExtentReport);

        try {
            if (Desktop.isDesktopSupported()&& extentReport.exists()) {
                Desktop.getDesktop().open(extentReport);
                System.out.println("Extent Report Opened Successfully");
            }
            else {
                System.out.println("Report file not found: " + extentReport.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
     /*   try {
        	URL url = new URL("file:///"+System.getProperty("user.dir"+"\\reports\\"+repName));
        	
        	// Create the mail message 
        	ImageHtmlEmail email = new ImageHtmlEmail();
        	email.setDataSourceResolver(new DataSourceUrlResolver(url));
        	email.setHostName("smpt.googlemail.com");
        	email.setSmtpPort(465);
        	email.setAuthenticator(new DefaultAuthenticator("qa.test@gmail.com","Test@12345"));
        	email.setSSLOnConnect(true);
        	email.setFrom("itskk_024@gmail.com"); // Sender
        	email.setSubject("Test Result");
        	email.setMsg("Please find Attached Report...");
        	email.addTo("itskk_024@gmail.com"); // Receiver 
        	email.attach(url, "extent report", "Please check report...");
        	email.send();
        
        	// dummy email and password  Email ID: qa.test@gmail.com Password: Test@12345 Email: selenium.demo.test@gmail.com Password: Selenium@123
        }
        catch(Exception e) {
        	e.printStackTrace();
        } */
	}  
	
}
