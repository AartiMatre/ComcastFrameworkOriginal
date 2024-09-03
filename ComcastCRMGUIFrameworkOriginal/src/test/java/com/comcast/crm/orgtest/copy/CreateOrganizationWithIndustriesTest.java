package com.comcast.crm.orgtest.copy;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationWithIndustriesTest
{
	@Test
	public void createOrgWithTndustry() throws Throwable
	{
		//read the data from property
		
		FileUtility fLib = new FileUtility();
		
		String Browser = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String UserName = fLib.getDataFromPropertiesFile("un");
		String Password = fLib.getDataFromPropertiesFile("pw");
		
		//generate the random number
		
		JavaUtility jLib = new JavaUtility();
		jLib.getRandomNumber();
		
		//read TestScripts data from excel file

		ExcelUtility eLib = new ExcelUtility();
		String orgName = eLib.getDataFromExcel("Org", 4, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("Org", 4, 3);
		String type = eLib.getDataFromExcel("Org", 4, 4);
	
	    
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
		Thread.sleep(4000);
		
		//step 2: navigate to organization module
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		
		//step 3: click on "create organization" button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//step 4: enter all the details & create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		WebElement web1 = driver.findElement(By.name("industry"));
		Select s = new Select(web1);
		s.selectByVisibleText(industry);
		
		WebElement web2 = driver.findElement(By.name("accounttype"));
		Select s1 = new Select(web2);
		s1.selectByVisibleText(type);
		
		driver.findElement(By.xpath("(//input[contains(@title,'Save')])[2]")).click();
		
		//verify the industries and type info
		String actualIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
		if(actualIndustries.contains(industry))
		{
			System.out.println(industry+" information is verified==Pass");
		}
		else
		{
			System.out.println(industry+" information is not verified==Fail");
		}
		
		String actualType = driver.findElement(By.id("dtlview_Type")).getText();
		if(actualType.contains(type))
		{
			System.out.println(type+" information is verified==Pass");
		}
		else
		{
			System.out.println(type+" information is not verified==Fail");
		}
		//step 5: logout
		driver.quit();
	
	}
}
