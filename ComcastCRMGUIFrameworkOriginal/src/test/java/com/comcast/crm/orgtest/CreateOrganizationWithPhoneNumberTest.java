package com.comcast.crm.orgtest;

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
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationWithPhoneNumberTest extends BaseClass
{
	@Test
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
		
		
		
//		String actualPhoneNum = driver.findElement(By.id("dtlview_Phone")).getText();
//		if(actualPhoneNum.contains(phoneNumber))
//		{
//			System.out.println(phoneNumber+" information is verified==Pass");
//		}
//		else
//		{
//			System.out.println(phoneNumber+" information is not verified==Fail");
//		}
		
	}
}
