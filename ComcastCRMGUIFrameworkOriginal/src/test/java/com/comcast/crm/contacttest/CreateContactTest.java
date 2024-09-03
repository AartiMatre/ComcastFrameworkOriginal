package com.comcast.crm.contacttest;

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
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactTest extends BaseClass {
	@Test
	public void createContactTest() throws Throwable {
		// read TestScripts data from excel file
		String lastName = eLib.getDataFromExcel("Contact", 1, 2) + jLib.getRandomNumber();

		// step 2: navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step 3: click on "create Contact" button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		// step 4: enter all the details & create new contact
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContact(lastName);

		// verify header LastName info expected result
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actualLast_Name = cip.getheaderMsg().getText();
		if (actualLast_Name.contains(lastName)) {
			System.out.println(lastName + " is created==Pass");
		} else {
			System.out.println(lastName + " is not created==Fail");
		}

	}
}
