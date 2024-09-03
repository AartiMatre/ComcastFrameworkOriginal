package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage 
{
	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(id = "dtlview_Last Name")
	private WebElement lastNameTxtFld;
	
	@FindBy(id = "dtlview_Support Start Date")
	private WebElement supportStartDate;
	
	@FindBy(id = "dtlview_Support End Date")
	private WebElement supportEndtDate;
	
	WebDriver driver;
	
	public ContactInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getheaderMsg() 
	{
		return headerMsg;
	}
	public WebElement getlastNameTxtFld()
	{
		return lastNameTxtFld;
	}
	public WebElement getsupportStartDate()
	{
		return supportStartDate;
	}
	public WebElement getsupportEndtDate()
	{
		return supportEndtDate;
	}
}
