package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	@FindBy(linkText = "Products")
	private WebElement productLink;
	
	@FindBy(linkText = "Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactLink;

	@FindBy(linkText = "Campaigns")
	private WebElement campaignLink;
	
	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy(xpath ="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement SignOutLink;
	
	
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this); //current class object
	}
	
	public WebElement getProductLink() {
		return productLink;
	}

	public WebElement getOrgLink() {
		return orgLink;
	}


	public WebElement getContactLink() {
		return contactLink;
	}


	public WebElement getCampaignLink() {
		return campaignLink;
	}


	public WebElement getSignOutLink() {
		return SignOutLink;
	}


	public WebElement getMoreLink() {
		return moreLink;
	}
	
	public WebElement getAdminImg() {
		return adminImg;
	}
	
	public void clickContact()
	{
		Actions a = new Actions(driver);
	 a.moveToElement(contactLink).perform();
	 contactLink.click();
	
	}

	public void navigateToCampaignPage() //business libraries
	{
		Actions a = new Actions(driver);
		a.moveToElement(moreLink).perform();
		campaignLink.click();
	}
	
	public void logout() 
	{
		Actions a = new Actions(driver);
		a.moveToElement(adminImg).perform();	
		//adminImg.click();
		SignOutLink.click();
	}

}
