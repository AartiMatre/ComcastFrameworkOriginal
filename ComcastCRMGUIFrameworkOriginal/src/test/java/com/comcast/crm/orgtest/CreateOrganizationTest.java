package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTest extends BaseClass{
	@Test
	public void createOrgTest() throws Throwable {

		// read TestScripts data from excel file
		String orgName = eLib.getDataFromExcel("Org", 1, 2) + jLib.getRandomNumber();
		
		// step 2: navigate to organization module
		HomePage h = new HomePage(driver);
		h.getOrgLink().click();

		// step 3: click on "create organization" button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// step 4: enter all the details & create new organization
		CreatingNewOrganizationPage c = new CreatingNewOrganizationPage(driver);
		c.createOrg(orgName);
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
}
