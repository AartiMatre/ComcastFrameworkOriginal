package com.comcast.crm.contacttest.copy;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactTest 
{
	@Test
	public void createContTest() throws Throwable
	{
		//create an object
		FileUtility fLib = new FileUtility();
		//read the data from property
		String Browser = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String UserName = fLib.getDataFromPropertiesFile("un");
		String Password = fLib.getDataFromPropertiesFile("pw");
		
		//generate the random number
		JavaUtility jLib = new JavaUtility();
		jLib.getRandomNumber();
		
		//read TestScripts data from excel file
		ExcelUtility eLib = new ExcelUtility();
		String lastName = eLib.getDataFromExcel("Contact", 1, 2) + jLib.getRandomNumber();
		
		WebDriver driver = null;
		if(Browser.equals("Chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(Browser.equals("Firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(Browser.equals("Edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			driver = new InternetExplorerDriver();
		}
	
		
		//Step 1: Login to app
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, UserName, Password);
		Thread.sleep(2000);
		
		//step 2: navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		
		//step 3: click on "create Contact" button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();
		
		//step 4: enter all the details & create new contact
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContact(lastName);
		

		//verify header LastName info expected result
		String actualLast_Name = driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actualLast_Name.contains(lastName))
		{
			System.out.println(lastName+" is created==Pass");
		}
		else
		{
			System.out.println(lastName+" is not created==Fail");
		}
		//step 5: logout
		driver.quit();
	}
}