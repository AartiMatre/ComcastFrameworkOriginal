package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class UnitTestCheckProjectInBackend 
{
	@Test
	public void projectCheckTest() throws SQLException
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("http://106.51.90.215:8084/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys("Epson_20");
		
//		JavascriptExecutor jse = (JavascriptExecutor) driver;
//		jse.executeScript("document.getElementsById('teamSize').value='10';");
		
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("John Jude");
		//driver.findElement(By.xpath("(//select[@name='status'])[2]")).click();
		Select s = new Select(driver.findElement(By.xpath("(//select[@name='status'])[2]")));
		s.selectByVisibleText("Created");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		driver.close();
		
		String expectedProjNam ="Epson_20";
		
		Driver ref = new Driver();
		 
		DriverManager.registerDriver(ref);
		
		//Step 2: connect to database
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		System.out.println("=========Done==========");
		
		//step 3: create SQL statement
		
		Statement stat = conn.createStatement();
		
		//step 4: execute select query and get the result
		
		ResultSet resultset = stat.executeQuery("select * from project;");
		
		boolean flag = false;
		
		while(resultset.next())
		{
			String actual = resultset.getString(4);
			if(actual.equals(expectedProjNam))
			{
				flag=true;
				System.out.println(expectedProjNam+" is available in the database");
			}
		}
		if(flag==false)
		{
			System.out.println(expectedProjNam+" is not available in the database");
		}
		
	
		conn.close();
	}

}
