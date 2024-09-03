package com.comcast.crm.orgtest.copy;

import java.io.FileInputStream;
import java.io.IOException;
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

public class CreateOrganizationWithPhoneNumberTest
{
	@Test
	public void createOrgWithPhoneNum() throws IOException, InterruptedException
	{
		//read the data from property
		FileInputStream fis = new FileInputStream("./configAppData/CommanData.properties");
		Properties p = new Properties();
		p.load(fis);
		String Browser = p.getProperty("browser");
		String URL = p.getProperty("url");
		String UserName = p.getProperty("un");
		String Password = p.getProperty("pw");
		
		//generate the random number
		
		Random r = new Random();
		int r1 = r.nextInt();
		
		//read TestScripts data from excel file

		FileInputStream fis1 = new FileInputStream("./testdata/testScriptdata.xlsx");
	    Workbook wb = WorkbookFactory.create(fis1);
	    Sheet sh = wb.getSheet("Org");
	    Row row = sh.getRow(7);
	    String orgName = row.getCell(2).getStringCellValue()+r1;
	    String phoneNumber = row.getCell(3).toString();
	    wb.close();
	    System.out.println("-----Executed Succesfully------");
	    
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
		
		driver.findElement(By.id("phone")).sendKeys(phoneNumber);
		
		driver.findElement(By.xpath("(//input[contains(@title,'Save')])[2]")).click();
		
		//verify the phone number and type info
		
		String actualPhoneNum = driver.findElement(By.id("dtlview_Phone")).getText();
		if(actualPhoneNum.contains(phoneNumber))
		{
			System.out.println(phoneNumber+" information is verified==Pass");
		}
		else
		{
			System.out.println(phoneNumber+" information is not verified==Fail");
		}
		//step 5: logout
		driver.quit();
	}
}
