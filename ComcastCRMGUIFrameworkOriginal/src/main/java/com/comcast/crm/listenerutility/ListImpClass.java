package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.basetest.BaseClassCopy;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListImpClass implements ITestListener, ISuiteListener
{
	public ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) 
	{
		System.out.println("Report Configuration");
	    String time = new Date().toString().replace(":", "_").replace(" ", "_");

		//spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);                    //coming from ISuiteListener
		
		// add Env information & create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Window-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
		
		
	}

	@Override
	public void onFinish(ISuite suite) 
	{
		System.out.println("Report backUP");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		
		System.out.println("=========>"+result.getMethod().getMethodName()+"<=====START=====");
		test = report.createTest(result.getMethod().getMethodName());  // to create the TestCase inside the Extent report
		//because this method before executing each TestCase
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"=======> STARTED <========");
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{

		System.out.println("=========>"+result.getMethod().getMethodName()+"<=====END=====");
		test.log(Status.PASS, result.getMethod().getMethodName()+"=======> COMPLETED <========");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		String testName = result.getMethod().getMethodName();
		//step-1 : create an object to EventFiring WebDriver (Selenium 4.15 and common io 4.15)
		TakesScreenshot edriver = (TakesScreenshot) BaseClass.sdriver;
		    
	    //step-2 : use getScreenshotAs method to get file type of screenshot
		String filePath = edriver.getScreenshotAs(OutputType.BASE64); //take Screenshot
	    
	    String time = new Date().toString().replace(":", "_").replace(" ", "_");
	        
	    //step-3 : store screen on local driver
//		try {
//			FileUtils.copyFile(srcFile, new File("./screenshot/"+testName+"+"+time+".png"));
//		} catch (IOException e) {
//			
//		}
	    test.addScreenCaptureFromBase64String(filePath, testName+"_"+time); //attach the screenshot in extent report
		test.log(Status.FAIL, result.getMethod().getMethodName()+"=======> FAILED <========");
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		test.log(Status.SKIP, result.getMethod().getMethodName()+"=======> SKIPPED <========");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

}
