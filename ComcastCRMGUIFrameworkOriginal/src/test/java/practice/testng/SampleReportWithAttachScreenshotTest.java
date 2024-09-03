package practice.testng;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportWithAttachScreenshotTest 
{
	public ExtentReports report;
	@BeforeSuite
	public void configBS()
	{
		//spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		// add Env information & create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Window-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}
	
	@AfterSuite
	public void configAS()
	{
		report.flush();  //it is used to save the reports
	}
	
	@Test
	public void createContactTest()
	{
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888/");
		
		TakesScreenshot edriver = (TakesScreenshot) driver;
		String filePath = edriver.getScreenshotAs(OutputType.BASE64); //take Screenshot
		
		
		ExtentTest test = report.createTest("createContactTest");
		
		test.log(Status.INFO, "Login to app");
		test.log(Status.INFO,"navigate to contact page");
		test.log(Status.INFO,"create contact");
		if("HDFC".equals("HF"))
		{
			test.log(Status.PASS,"contact is created");
		}
		else
		{
			test.addScreenCaptureFromBase64String(filePath, "ErrorFile"); //attach the screenshot in extent reports
		}
		
		driver.quit();
	}
	
}
