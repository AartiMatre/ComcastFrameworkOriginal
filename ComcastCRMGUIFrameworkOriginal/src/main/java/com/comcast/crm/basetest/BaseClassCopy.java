package com.comcast.crm.basetest;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClassCopy {
	/* Create Object */
	public DataBaseUtility dbLib = new DataBaseUtility();
	public FileUtility fLib = new FileUtility();
	public JavaUtility jLib = new JavaUtility();
	public ExcelUtility eLib = new ExcelUtility();
	WebDriverUtility wlib = new WebDriverUtility();

	public WebDriver driver = null; //if we make static then it'll not participate in parallel execution in testNG
	//(static variable will have only one instance)
	public static WebDriver sdriver = null;

	@BeforeSuite
	public void configBS() throws SQLException {
		System.out.println("===Connect to DB, Report config===");
		dbLib.getDbConnection();
	}

	@BeforeClass
	public void configBC() throws Throwable {
		 String BROWSER=fLib.getDataFromPropertiesFile("browser");
		String url = fLib.getDataFromPropertiesFile("url");
		//String BROWSER = browser;
		if (BROWSER.equals("Chrome"))
			driver = new ChromeDriver();
		else if (BROWSER.equals("Firefox"))
			driver = new FirefoxDriver();
		
		sdriver = driver; //(while launching the browser we are storing the session id in sdriver)
		
		driver.get(url);
		driver.manage().window().maximize();
		wlib.waitForPageToLoad(driver);

	}

	@BeforeMethod
	public void configBM() throws Throwable {
		System.out.println("===Login===");
		wlib.waitForPageToLoad(driver);
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("un");
		String PASSWORD = fLib.getDataFromPropertiesFile("pw");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}

	@AfterMethod
	public void configAM() {
		System.out.println("===Logout===");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}

	@AfterClass
	public void configAC() {
		System.out.println("===Close the browser");
		driver.quit();
	}

	@AfterSuite
	public void configAS() throws Throwable {
		System.out.println("===Close DB, Report backUp===");
		dbLib.closeDbConnection();
	}
	

}
