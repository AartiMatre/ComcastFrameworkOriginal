package sample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Property 
{
	public static void main (String[]args) throws IOException, InterruptedException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\Rohit Pandey\\Desktop\\Data\\CommanData.properties");
		Properties p = new Properties();
		p.load(fis);
		String Browser = p.getProperty("browser");
		String URL = p.getProperty("Url");
		String UserName = p.getProperty("UN");
		String Password = p.getProperty("PW");
		
		Random r = new Random();
		int r1 = r.nextInt();
	
		FileInputStream fis1 = new FileInputStream("\\Users\\Rohit Pandey\\Desktop\\createOrgTest.xlsx");
	    Workbook wb = WorkbookFactory.create(fis1);
	   String orgName = wb.getSheet("CreateOrganisation").getRow(1).getCell(2).getStringCellValue()+r1;

	   

	   System.out.println("-----Executed Succesfully------");
		
		WebDriver driver = null;
		if(Browser.equals("Chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(Browser.equals("Firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(Browser.equals("Edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			driver = new InternetExplorerDriver();
		}
	
		
		
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("test@123");
		driver.findElement(By.id("submitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.name("website")).sendKeys("https://qspiders.com/");
		driver.findElement(By.xpath("//img[@title='Select']")).click();
		Set<String> allWindows = driver.getWindowHandles();
		String parentWindow = driver.getWindowHandle();
		for(String window : allWindows)
		{
			if(!window.equals(parentWindow))
			{
				Thread.sleep(2000);
				driver.switchTo().window(window);
				Thread.sleep(2000);
			}
			
		}
		driver.findElement(By.linkText("Qspiders")).click();
		driver.switchTo().alert().accept();Thread.sleep(3000);
		driver.switchTo().window(parentWindow);
		
		driver.findElement(By.xpath("//input[@name='employees']")).sendKeys("50");
		driver.findElement(By.id("email2")).sendKeys("abc12@gmail.com");
		WebElement industry = driver.findElement(By.name("industry"));
		Select s = new Select(industry);
		s.selectByValue("Banking");
		WebElement type = driver.findElement(By.name("accounttype"));
		Select s1 = new Select(type);
		s1.selectByValue("Investor");
		driver.findElement(By.name("emailoptout")).click();
		driver.findElement(By.xpath("(//input[@name='assigntype'])[1]")).click();
		WebElement assignedTo = driver.findElement(By.name("assigned_user_id"));
		Select s3 = new Select(assignedTo);
		s3.selectByValue("1");
		driver.findElement(By.name("bill_street")).sendKeys("BTM");
		driver.findElement(By.name("bill_pobox")).sendKeys("Layout");
		driver.findElement(By.name("bill_city")).sendKeys("Bengaluru");
		driver.findElement(By.name("bill_state")).sendKeys("Karnataka");
		driver.findElement(By.name("bill_code")).sendKeys("590019");
		driver.findElement(By.name("bill_country")).sendKeys("India");
		driver.findElement(By.xpath("(//input[@name='cpy'])[2]")).click();
		driver.findElement(By.xpath("//textarea[@name='description']"))
		.sendKeys("This is about company information");
		driver.findElement(By.xpath("(//input[contains(@title,'Save')])[2]")).click();
//		Alert a = driver.switchTo().alert();
//		a.accept();
//		System.out.println("Alert handled successfully");
		//driver.findElement(By.cssSelector(".crmbutton.small.save")).click();
		String created_Org = driver.findElement(By.className("dvHeaderText")).getText();
		FileInputStream fis2 = new FileInputStream("\\Users\\Rohit Pandey\\Desktop\\createOrgTest.xlsx");
		  Workbook wb1 = WorkbookFactory.create(fis2);
		   Sheet sh1 = wb1.getSheet("CreateOrganisation");
		   Row row1 = sh1.getRow(1);
		   Cell cel1 = row1.createCell(6);
		   cel1.setCellValue("Pass");
		 
		
		   FileOutputStream fos1 = new FileOutputStream("\\Users\\Rohit Pandey\\Desktop\\createOrgTest.xlsx");
		   wb.write(fos1);
		   wb.close();
		   wb1.close();
		   
		if(created_Org.contains(orgName))
		{
			System.out.println("The Org Name"+created_Org+"Created Successfully");
		}
		else
		{
			System.out.println("The organisation Name"+created_Org+"already exist");
		}
		WebElement signOut = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		a.moveToElement(signOut).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}
}