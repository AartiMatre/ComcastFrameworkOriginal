package com.comcast.crm.orgtest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.listenerutility.ListImpClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTestWithTestNG extends BaseClass
{
	@Test(groups = "smokeTest")
	public void createOrgTest() throws Throwable {

		// read TestScripts data from excel file
		//ListImpClass.test.log(Status.INFO, "read data from Excel");  //not participate in parallel execution because of class is staic
		UtilityClassObject.getTest().log(Status.INFO, "read data from Excel"); //participate in parallel execution
		
		String orgName = eLib.getDataFromExcel("Org", 1, 2) + jLib.getRandomNumber();
		
		// step 2: navigate to organization module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to organization module page");
		
		HomePage h = new HomePage(driver);
		h.getOrgLink().click();

		// step 3: click on "create organization" button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create organization module page");
		
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// step 4: enter all the details & create new organization
		UtilityClassObject.getTest().log(Status.INFO, "create a New organization");
		
		CreatingNewOrganizationPage c = new CreatingNewOrganizationPage(driver);
		c.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO, orgName+" created a New organization");
		Thread.sleep(5000);
		// verify Header msg Expected result

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		
		String actOrgName = oip.getHeaderMsg().getText();
		Thread.sleep(5000);
		System.out.println(actOrgName);
		Thread.sleep(5000);
		System.out.println(orgName);
		/*
		 * boolean status = actOrgName.contains(orgName); Assert.assertEquals(status,
		 * true); Reporter.log("create Org testScript executed", true);
		 */
		Assert.assertTrue(actOrgName.contains(orgName));
		Reporter.log("create Org testScript executed", true);
	}
	@Test(groups = "regressionTest")
	public void createOrgWithTndustry() throws Throwable
	{
		
		//read TestScripts data from excel file
		
		String orgName = eLib.getDataFromExcel("Org", 4, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("Org", 4, 3);
		String type = eLib.getDataFromExcel("Org", 4, 4);
		
		//step 2: navigate to organization module
		HomePage h = new HomePage(driver);
		h.getOrgLink().click();

		//step 3: click on "create organization" button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		//step 4: enter all the details & create new organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, industry, type);
		
		//verify the industries and type info
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualIndustries = oip.getOutIndus().getText();
		String actualType = oip.getOutType().getText();
		
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(actualIndustries.equals(industry));
		sa.assertTrue(actualType.equals(type));
		Reporter.log("create Org with industry and type testScript executed", true);
		sa.assertAll();
	}
	@Test(groups = "regressionTest")
	public void createOrgWithPhoneNum() throws Throwable
	{
		//read TestScripts data from excel file
	    String orgName = eLib.getDataFromExcel("Org", 7, 2) + jLib.getRandomNumber();
	    String phoneNumber = eLib.getDataFromExcel("Org", 7, 3);
	   
		//step 2: navigate to organization module
	    HomePage h = new HomePage(driver);
		h.getOrgLink().click();
		
		//step 3: click on "create organization" button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		//step 4: enter all the details & create new organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrgPhone(orgName, phoneNumber);
		
		//verify the phone number and OrgName
		
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		
		String actualPhoneNum = oip.getOutPhno().getText();
		String actOrgName = oip.getOrgName().getText();
		
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(actualPhoneNum.equals(phoneNumber));
		sa.assertTrue(actOrgName.equals(orgName));
		Reporter.log("create Org with phoneNumber testScript executed", true);
		sa.assertAll();
	}
}
