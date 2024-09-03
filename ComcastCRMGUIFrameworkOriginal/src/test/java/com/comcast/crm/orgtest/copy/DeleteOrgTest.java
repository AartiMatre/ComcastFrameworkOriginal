package com.comcast.crm.orgtest.copy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest 
{
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
		String orgName = eLib.getDataFromExcel("Org", 10, 2) + jLib.getRandomNumber();

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
		
		//Go back to organizations page
		h.getOrgLink().click();
		
		//Search for Organization
		op.getSearchEdt().sendKeys(orgName);
		WebDriverUtility wLib = new WebDriverUtility();
		wLib.select(op.getSearchDD(), "Organization Name");
		op.getSearchBtn().click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		
		//In dynamic webTable select & delete org

		
		// step 5: logout
		h.logout();
		
		driver.quit();
	}
}
