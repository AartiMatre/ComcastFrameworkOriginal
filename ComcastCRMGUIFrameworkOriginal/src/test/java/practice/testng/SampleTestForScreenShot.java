package practice.testng;

//import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
//import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class SampleTestForScreenShot 
{
	@Test
	public void sampleScreenshot() throws Throwable
	{
		WebDriver driver = new ChromeDriver();
		driver.get("http://amazon.com");
		
		//step-1 : create an object to EventFiring WebDriver (Selenium 4.15 and common io 4.15)
	    //EventFiringWebDriver edDriver = new EventFiringWebDriver(driver);
	    
	    //step-2 : use getScreenshotAs method to get file type of screenshot
	   // File srcFile = edDriver.getScreenshotAs(OutputType.FILE);
	    
	    //step-3 : store screen on local driver
		//FileUtils.copyFile(srcFile, new File("./screenshot/test.png"));
	}
}
