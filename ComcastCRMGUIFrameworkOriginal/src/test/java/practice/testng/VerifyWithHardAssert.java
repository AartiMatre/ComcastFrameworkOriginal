package practice.testng;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyWithHardAssert 
{
	@Test
	public void homePageTest(Method mtd)
	{
		System.out.println(mtd.getName()+" Test Start");
		//intentionally giving wrong value to failed the TestCase and
		//it'll stop the execution on that particular failed statement and will not execute further statement of that TestCase 
		//but will execute next TestCase and give red report in Emailable-Report 
		//that is the reason we go for Hard statement
		
		System.out.println("Step-1");
		Assert.assertEquals("Home", "Home_Page"); //failed
		System.out.println("Step-2");
		Assert.assertNotEquals("Home", "Home-Page"); 
		System.out.println("Step-3");
		Assert.assertEquals("Home_CRM", "Home_CRM"); 
		System.out.println("Step-4");
		
		System.out.println(mtd.getName()+" Test end");
	}
	
	@Test
	public void verifyLogoHomePageTest(Method mtd)
	{
		System.out.println(mtd.getName()+" Test Start");
		
		System.out.println("Step-1");
		System.out.println("Step-2");
		Assert.assertTrue(true); 		//pass
		System.out.println("Step-3");
		Assert.assertTrue(false);  
		System.out.println("Step-4");
	
		System.out.println(mtd.getName()+" Test End");
		
	}
	
}
