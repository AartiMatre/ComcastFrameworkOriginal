package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class OrganizationInfoPage 
{
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerMsg;

	@FindBy(xpath="//input[@name='search_text']")
	private WebElement search;
	
	@FindBy(id="dtlview_Industry")
	private WebElement outIndus;
	
	@FindBy(id="mouseArea_Type")
	private WebElement outType;
	
	@FindBy(id="bas_searchfield")
	private WebElement dropDown;
	
	@FindBy(id="dtlview_Phone")
	private WebElement outPhno;
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement orgName;
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	
	public WebElement getOrgName() {
		return orgName;
	}

	public WebElement getSearch() {
		return search;
	}

	public WebElement getOutIndus() {
		return outIndus;
	}

	public WebElement getOutType() {
		return outType;
	}

	public WebElement getOutPhno() {
		return outPhno;
	}
	
	public WebElement getDropDown() {
		return dropDown;
	}
	
	WebDriver driver;
	WebDriverUtility wLib = new WebDriverUtility();
	
	public OrganizationInfoPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this); //current class object
	}

	public String headerInfo()
	{
		String orgName = headerMsg.getText();
		return orgName;
	}

	public void deleteOrg(String orgName)
	{
		search.sendKeys(orgName);
		wLib.select(dropDown, "Organization Name");
	}
	
}
