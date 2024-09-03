package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewContactPage 
{
	WebDriver driver;
	WebDriverUtility wLib = new WebDriverUtility();
	
	@FindBy(name = "lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement addOrgBtn;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name = "phone")
	private WebElement phoneNumEdt;
	
	@FindBy(name = "support_start_date")
	private WebElement supStartDate;
	
	@FindBy(name = "support_end_date")
	private WebElement supEndDate;
	
	public CreatingNewContactPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getLastNameEdt()
	{
		return lastNameEdt;
	}
	
	public WebElement getAddOrgBtn()
	{
		return addOrgBtn;
	}
	
	public WebElement getSaveBtn()
	{
		return saveBtn;
	}
	
	public WebElement getphoneNumEdt()
	{
		return phoneNumEdt;
	}
	
	public WebElement getSupStartDate()
	{
		return supStartDate;
	}
	
	public WebElement getSupEndDate()
	{
		return supEndDate;
	}
	
	public void createContact(String lastName)
	{
		lastNameEdt.sendKeys(lastName);
		saveBtn.click();
	}
	
	public void createContact(String lastName, String phoneNum)
	{
		lastNameEdt.sendKeys(lastName);
		phoneNumEdt.sendKeys(phoneNum);
		saveBtn.click();
	}
	public void createContact(String lastName, String startDate, String endDate)
	{
		lastNameEdt.sendKeys(lastName);
		supStartDate.clear();
		supEndDate.clear();
		
		supStartDate.sendKeys(startDate);
		supEndDate.sendKeys(endDate);
		saveBtn.click();
	}
}
