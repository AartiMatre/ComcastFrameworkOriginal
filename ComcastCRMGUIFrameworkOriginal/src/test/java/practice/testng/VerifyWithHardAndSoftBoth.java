package practice.testng;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyWithHardAndSoftBoth 
{
	@Test
	public void homePageTest(Method mtd)
	{
		System.out.println(mtd.getName()+" Test Start");
		//intentionally giving wrong value to failed the TestCase and
		//it'll not stop the execution on that particular failed statement and will execute further statement of that TestCase 
		//and will execute next TestCase and give red report in Emailable-Report 
		//that is the reason we go for Soft statement
		SoftAssert assertObj = new SoftAssert();
		
		System.out.println("Step-1");
		Assert.assertEquals("Home", "Home_Page"); //Used HardAssert for mandatory field
		System.out.println("Step-2");
		assertObj.assertEquals("Title", "Title"); //Used SoftAssert for not mandatory field
		System.out.println("Step-3");
		
		assertObj.assertAll(); //It is mandatory to used assertAll() statement because whenever TestCase is failed it'll collect
		//the exception and print that in console and also it'll fail the testcase
		
		System.out.println(mtd.getName()+" Test end");
	}
	
	@Test
	public void verifyLogoHomePageTest(Method mtd)
	{
		System.out.println(mtd.getName()+" Test Start");
		
		SoftAssert assertObj = new SoftAssert();
		
		System.out.println("Step-1");
		System.out.println("Step-2");
		assertObj.assertTrue(true); 		//pass
		System.out.println("Step-3");
		System.out.println("Step-4");
		
		assertObj.assertAll(); 
	
		System.out.println(mtd.getName()+" Test End");
		
	}
	
}
