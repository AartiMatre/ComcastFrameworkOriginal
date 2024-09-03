package com.comcast.crm.orgtest.copy;

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
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTest {
	@Test
	public void createOrgTest() throws Throwable {
		// read the data from property

		FileUtility fLib = new FileUtility();

		String Browser = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String UserName = fLib.getDataFromPropertiesFile("un");
		String Password = fLib.getDataFromPropertiesFile("pw");

		// generate the random number

		JavaUtility jLib = new JavaUtility();
		jLib.getRandomNumber();

		// read TestScripts data from excel file

		ExcelUtility eLib = new ExcelUtility();
		String orgName = eLib.getDataFromExcel("Org", 1, 2) + jLib.getRandomNumber();

		WebDriver driver = null;
		if (Browser.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else if (Browser.equals("Edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new InternetExplorerDriver();
		}

		// Step 1: Login to app
		driver.get(URL);

		// LoginPage lp = PageFactory.initElements(driver, LoginPage.class); //Lazy
		// Initialization (or)

		LoginPage lp = new LoginPage(driver); // constructor will take care of all execution of @FindBy annotations

//		lp.getUsernameEdt().sendKeys("admin");
//		lp.getPasswordEdt().sendKeys("test@123");  //all the public getters methods (First way)
//		lp.getLoginBtn().click();

		// or

		lp.loginToApp("url","admin", "test@123");

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

		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + " is created==Pass");
		} else {
			System.out.println(orgName + " is not created==Fail");
		}
		
		Thread.sleep(5000);
		// step 5: logout
		h.logout();
		
		driver.quit();

		/*
		 * //verify Header msg Expected result String header_Info =
		 * driver.findElement(By.className("dvHeaderText")).getText();
		 * if(header_Info.contains(orgName)) {
		 * System.out.println(orgName+" is created==Pass"); } else {
		 * System.out.println(orgName+" is not created==Fail"); } //verify header
		 * orgName info expected result String actualOrg_Name =
		 * driver.findElement(By.id("dtlview_Organization Name")).getText();
		 * if(actualOrg_Name.contains(orgName)) {
		 * System.out.println(orgName+" is created==Pass"); } else {
		 * System.out.println(orgName+" is not created==Fail"); } //step 5: logout
		 * driver.quit();
		 */
	}
}
