package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage 
{
	@FindBy(name = "accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath ="//input[@title = 'Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDB;
	
	@FindBy(name="accounttype")
	private WebElement selectAccType;
	
	@FindBy(id="phone")
	private WebElement phoneTbx;
	
	
	WebDriver driver;
	
	public WebElement getSelectAccType() 
	{
		return selectAccType;
	}
	
	public CreatingNewOrganizationPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this); //current class object
	}

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}
	
	public WebElement getPhoneTbx() {
		return phoneTbx;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrg(String orgName)
	{
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrg(String orgName, String industry)
	{
		orgNameEdt.sendKeys(orgName);
		Select s = new Select(industryDB);
		s.selectByVisibleText(industry);
		saveBtn.click();
	}
	
	public void createOrgPhone(String orgName, String phone)
	{
		orgNameEdt.sendKeys(orgName);
		phoneTbx.sendKeys(phone);
		saveBtn.click();
	}
	
	public void createOrg(String orgName, String industry, String type)
	{
		orgNameEdt.sendKeys(orgName);
		Select s = new Select(industryDB);
		s.selectByVisibleText(industry);
		Select s1 = new Select( selectAccType);
		s1.selectByVisibleText(type);
		saveBtn.click();
	}
}
