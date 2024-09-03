package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactTestWithTestNG extends BaseClass
{
	@Test(groups = "smokeTest")
	public void createContactTest() throws Throwable 
	{
		// read TestScripts data from excel file 
		String lastName = eLib.getDataFromExcel("Contact", 1, 2) + jLib.getRandomNumber();

		//step 2: navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		
		//step 3: click on "create Contact" button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();
		
		//step 4: enter all the details & create new contact
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContact(lastName);
				
		// verify header LastName info expected result
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actualLast_Name = cip.getlastNameTxtFld().getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualLast_Name, lastName);
		
		String actHeaderMsg = cip.getheaderMsg().getText();
		boolean status = actHeaderMsg.contains(lastName);
		
		Assert.assertEquals(status, true);
		Reporter.log("create contact testScript executed", true);
		sa.assertAll();

	}
	
	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws Throwable 
	{
		WebDriverUtility wLib = new WebDriverUtility();
		
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
		
		hp.clickContact();
		
		//step 6: click on "create Contact" button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();
		
		//step 7: enter all the details & create new contact
		CreatingNewContactPage cnc = new CreatingNewContactPage(driver);
		cnc.getLastNameEdt().sendKeys(contactLastName);
		cnc.getAddOrgBtn().click();
		
		//step 8:switch to child window
		wLib.switchToTabOnURL(driver, "module=Accounts");
		
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText(orgName)).click(); //Dynamic XPath
		
		//step 9: switch back to parent window
		wLib.switchToTabOnURL(driver, "module=Contacts&action");
		
		driver.findElement(By.xpath("(//input[contains(@title,'Save')])[2]")).click();
		
		//verify header orgName info expected result
		ContactInfoPage cip = new ContactInfoPage(driver);
		String created_cont = cip.getheaderMsg().getText();
		String createdWithOrg = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		
		System.out.println(created_cont);
		System.out.println(createdWithOrg);
		System.out.println(contactLastName);
		System.out.println(orgName);
		
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(created_cont.contains(contactLastName));
		sa.assertTrue(createdWithOrg.trim().equals(orgName));
		Reporter.log("create contact with Org testScript executed", true);
		sa.assertAll();
	}
	
	@Test(groups = "regressionTest")
	public void createContactWithSupportDateTest() throws Throwable 
	{	 
		//step 2: navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		
		//step 3: click on "create Contact" button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();
		
		//step 4: enter all the details & create new contact
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);

		
		//read TestScripts data from excel file
		String lastName = eLib.getDataFromExcel("Contact", 4, 2) + jLib.getRandomNumber();
		
		cncp.getSupStartDate().clear();
		cncp.getSupEndDate().clear();
		
		//use utility methods to capture the dates
		String  Start_Date = jLib.getSystemDateYYYYDDMM();
		String  End_Date = jLib.getRequiredDateYYYYDDMM(30);
		
		System.out.println(Start_Date);
		System.out.println(End_Date);
		
		cncp.createContact(lastName, Start_Date, End_Date);
		
		//verify header StartDate info expected result
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actual_StartDate = cip.getsupportStartDate().getText().trim();
		String actual_EndDate = cip.getsupportEndtDate().getText().trim();
		
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actual_StartDate, Start_Date);
		sa.assertEquals(actual_EndDate, End_Date);
		Reporter.log("create contact with support date testScript executed", true);
		sa.assertAll();
	}
}
