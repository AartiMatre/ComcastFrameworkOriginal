package practice.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClassCopy;

public class InvoiceTestWithRetryAnalyser extends BaseClassCopy
{
	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImp.class)
	public void activateSim()
	{
		System.out.println("Execute activateSim");
		
		//Assert.assertEquals("", "Login"); //failed testcases will retry 5 times
		
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
}
