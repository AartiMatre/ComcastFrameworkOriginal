package com.comcast.crm.contacttest.copy;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class createContactWithOrgTest 
{
	@Test
	public void createContactWithOrgTest() throws Throwable
	{
		//read the data from property
		
		FileUtility fLib = new FileUtility();
		
		String Browser = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String UserName = fLib.getDataFromPropertiesFile("un");
		String Password = fLib.getDataFromPropertiesFile("pw");
		
		//generate the random number
		
		JavaUtility jLib = new JavaUtility();
		
		//read TestScripts data from excel file

		ExcelUtility eLib = new ExcelUtility();
		String orgName = eLib.getDataFromExcel("Contact", 7, 2) + jLib.getRandomNumber();
		String contactLastName = eLib.getDataFromExcel("Contact", 7, 3);
	    
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
	
		WebDriverUtility wLib = new WebDriverUtility();
		
		//Step 1: Login to app
		driver.get(URL);
		
		wLib.waitForPageToLoad(driver); //implicitly Wait
		
		driver.findElement(By.name("user_name")).sendKeys(UserName);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();
		Thread.sleep(4000);
		
		//step 2: navigate to organization module
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		
		//step 3: click on "create organization" button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//step 4: enter all the details & create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("(//input[contains(@title,'Save')])[2]")).click();
		
		//verify Header msg Expected result
		String header_Info = driver.findElement(By.className("dvHeaderText")).getText();
		if(header_Info.contains(orgName))
		{
			System.out.println(orgName+" header is verified==Pass");
		}
		else
		{
			System.out.println(orgName+" header is not verified==Fail");
		}
		//step 5: navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		
		//step 6: click on "create Contact" button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//step 7: enter all the details & create new contact
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
	
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		//step 8:switch to child window
		wLib.switchToTabOnURL(driver, "module=Accounts");
		
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click(); //Dynamic XPath
		
		//step 9: switch back to parent window
		wLib.switchToTabOnURL(driver, "Contacts&action");
		
		driver.findElement(By.xpath("(//input[contains(@title,'Save')])[2]")).click();
		
		//verify Header phone Number info Expected Result
		
	    header_Info = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(header_Info.contains(contactLastName))
		{
			System.out.println(contactLastName+" is verified==Pass");
		}
		else
		{
			System.out.println(contactLastName+" is not verified==Fail");
		}
		
		//verify header orgName info expected result
		String actualOrg_Name = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actualOrg_Name);
		if(actualOrg_Name.trim().contains(orgName))  //trim() is used to ignore space between before and after string
		{
			System.out.println(orgName+" is created==Pass");
		}
		else
		{
			System.out.println(orgName+" is not created==Fail");
		}

		//step 11: logout
		driver.quit();
	}
}
