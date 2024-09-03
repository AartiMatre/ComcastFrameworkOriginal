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
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationWithIndustriesTest extends BaseClass
{
	@Test
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
//		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
//		String indus = oip.getOutIndus().getText();
//		SoftAssert sa = new SoftAssert();
//		sa.assertEquals(indus, industry);
//		String type1 = oip.getOutType().getText();
//		sa.assertEquals(type1, type);
//		Reporter.log("create Org with industry and type testScript executed", true);
//		sa.assertAll();
		/*
		 * String actualIndustries =
		 * driver.findElement(By.id("dtlview_Industry")).getText();
		 * if(actualIndustries.contains(industry)) {
		 * System.out.println(industry+" information is verified==Pass"); } else {
		 * System.out.println(industry+" information is not verified==Fail"); }
		 * 
		 * String actualType = driver.findElement(By.id("dtlview_Type")).getText();
		 * if(actualType.contains(type)) {
		 * System.out.println(type+" information is verified==Pass"); } else {
		 * System.out.println(type+" information is not verified==Fail"); } //step 5:
		 * logout driver.quit();
		 */
	
	}
}
