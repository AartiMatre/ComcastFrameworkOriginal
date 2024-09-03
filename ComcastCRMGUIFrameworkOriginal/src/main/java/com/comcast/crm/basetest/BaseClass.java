package com.comcast.crm.basetest;


import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)
public class BaseClass 
{
	/* Create Object */
	public DataBaseUtility dbLib = new DataBaseUtility();
	public FileUtility fLib = new FileUtility();
	public JavaUtility jLib = new JavaUtility();
	public ExcelUtility eLib = new ExcelUtility();
	WebDriverUtility wlib = new WebDriverUtility();
	
	public WebDriver driver = null; /*if we make static then it'll not participate in parallel execution in testNG
	(static variable will have only one instance)*/
	public static WebDriver sdriver = null;
	//public ExtentSparkReporter spark;
	//public ExtentReports report;

	@BeforeSuite(groups = {"smokeTest", "regressionTest"})
	public void configBS() throws SQLException
	{
		System.out.println("===Connect to DB, Report config===");
		dbLib.getDbConnection();
		
//		//spark report config
//		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
//		spark.config().setDocumentTitle("CRM Test Suite Results");
//		spark.config().setReportName("CRM Report");
//		spark.config().setTheme(Theme.DARK);
//		
//		// add Env information & create test
//		report = new ExtentReports();
//		report.attachReporter(spark);
//		report.setSystemInfo("OS", "Window-10");
//		report.setSystemInfo("BROWSER", "CHROME-100");
	}
	
	//@Parameters("BROWSER")

	@BeforeClass
	public void configBC(/*String browser*/) throws Throwable
	{
		System.out.println("===Launch the Browser===");
		String BROWSER=fLib.getDataFromPropertiesFile("browser");
	    String url=fLib.getDataFromPropertiesFile("url");
		//String BROWSER=browser;
		if(BROWSER.equalsIgnoreCase("Chrome"))
			driver=new ChromeDriver();
		else if(BROWSER.equalsIgnoreCase("Firefox"))
			driver=new FirefoxDriver();
		
		sdriver = driver;
		
		UtilityClassObject .setDriver(driver);
		
		driver.get(url);
		driver.manage().window().maximize();
		wlib.waitForPageToLoad(driver);

}
	@BeforeMethod(groups = {"smokeTest", "regressionTest"})
	public void configBM() throws Throwable
	{
		System.out.println("===Login===");
		wlib.waitForPageToLoad(driver);
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("un");
		String PASSWORD = fLib.getDataFromPropertiesFile("pw");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}
	@AfterMethod(groups = {"smokeTest", "regressionTest"})
	public void configAM()
	{
		System.out.println("===Logout===");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}
	@AfterClass(groups = {"smokeTest", "regressionTest"})
	public void configAC() 
	{
		System.out.println("===Close the browser");
		driver.quit();
	}
	@AfterSuite(groups = {"smokeTest", "regressionTest"})
	public void configAS() throws Throwable
	{
		System.out.println("===Close DB, Report backUp===");
		dbLib.closeDbConnection();
		//report.flush();  //it is used to save the reports
	}
}
