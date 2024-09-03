package com.comcast.crm.contacttest;

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

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class createContactWithOrgTest extends BaseClass
{
	@Test
	public void createContactWithOrgTest() throws Throwable
	{
		WebDriverUtility wLib = new WebDriverUtility();
		wLib.waitForPageToLoad(driver);
		String orgName = eLib.getDataFromExcel("Contact", 7, 2) + jLib.getRandomNumber();
		String contactLastName = eLib.getDataFromExcel("Contact", 7, 3) + jLib.getRandomNumber();
	    
		//step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		 
		//step 3: click on "create organization" button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();
		
		//step 4: enter all the details & create new organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		Thread.sleep(3000);
		
		//step 5: navigate to contact module
		
		hp.getContactLink().click();
		
		//step 6: click on "create Contact" button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();
		
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
	}
}
