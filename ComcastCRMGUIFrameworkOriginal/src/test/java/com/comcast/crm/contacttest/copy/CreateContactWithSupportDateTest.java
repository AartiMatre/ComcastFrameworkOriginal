package com.comcast.crm.contacttest.copy;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactWithSupportDateTest 
{
	@Test
	public void CreateContactWithSupportDateTest() throws Throwable
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
		String lastName = eLib.getDataFromExcel("Contact", 4, 2) + jLib.getRandomNumber();
		 
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
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(By.name("user_name")).sendKeys(UserName);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();
		Thread.sleep(2000);
		
		//step 2: navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		
		//step 3: click on "create Contact" button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//step 4: enter all the details & create new contact
		String  Start_Date = jLib.getSystemDateYYYYDDMM();
		
		String  End_Date = jLib.getRequiredDateYYYYDDMM(30);
		
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		driver.findElement(By.name("support_start_date"));
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(Start_Date);
		
		driver.findElement(By.name("support_end_date"));
		driver.findElement(By.name("support_end_date")).sendKeys(End_Date);
		
		driver.findElement(By.xpath("(//input[contains(@title,'Save')])[2]")).click();
		

		//verify header StartDate info expected result
		String actual_StartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actual_StartDate.contains(Start_Date))
		{
			System.out.println(Start_Date+" is verified==Pass");
		}
		else
		{
			System.out.println(Start_Date+" is not verified==Fail");
		}
		
		//verify header StartDate info expected result
		String actual_EndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actual_EndDate.contains(End_Date))
		{
			System.out.println(End_Date+" is verified==Pass");
		}
		else
		{
			System.out.println(End_Date+" is not verified==Fail");
		}
		//step 5: logout
		driver.quit();
	}
}
