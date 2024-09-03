package com.comcast.crm.contacttest;

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

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;

public class CreateContactWithSupportDateTest extends BaseClass
{
	@Test
	public void CreateContactWithSupportDateTest() throws Throwable
	{
		
		//read TestScripts data from excel file
		String lastName = eLib.getDataFromExcel("Contact", 4, 2) + jLib.getRandomNumber();
		 
		//step 2: navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		
		//step 3: click on "create Contact" button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();
		
		//step 4: enter all the details & create new contact
		String  Start_Date = jLib.getSystemDateYYYYDDMM();
		String  End_Date = jLib.getRequiredDateYYYYDDMM(30);
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContact(lastName, Start_Date, End_Date);
		
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
		
	}
}
