package practice.testng;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class VerifyWithJavaStatement 
{
	@Test
	public void homePageTest(Method mtd)
	{
		System.out.println(mtd.getName()+" Test Start");
		String expectedPage = "Home Page" ; //intentionally giving wrong value to failed the TestCase and
		//it is giving Green report in Emailable-Report that is the reason we don't go if else java statement
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("test@123");
		driver.findElement(By.id("submitButton")).click();
		
		String actTitle = driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		
		if(actTitle.trim().equals(expectedPage))
		{
			System.out.println(expectedPage+" page is verified==>PASSED");
		}
		else 
		{
			System.out.println(expectedPage+" page is verified==>FAILED");
		}
		driver.close();
		System.out.println(mtd.getName()+" Test end");
	}
	
	@Test
	public void verifyLogoHomePageTest(Method mtd)
	{
		System.out.println(mtd.getName()+" Test Start");
		String expectedPage = "Home" ;
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("test@123");
		driver.findElement(By.id("submitButton")).click();
		
		boolean status = driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isEnabled();
		if(status)
		{
			System.out.println(status+" ==>logo is verified==>PASSED");
		}
		else 
		{
			System.out.println(status+" ==>logo is not verified==>FAILED");
		}
		driver.close();
		System.out.println(mtd.getName()+" Test End");
		
	}
	
}
