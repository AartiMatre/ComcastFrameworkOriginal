package practice.testng;

import org.testng.annotations.Test;

public class DependsOnMethodsTest
{
	@Test
	public void createOrderTest()
	{
		System.out.println("createOrderTest===>123");
		String s1 = null;
		System.out.println(s1.equals("123"));
	}
	@Test(dependsOnMethods = "createOrderTest")
	public void billingAnOrderTest()
	{
		System.out.println("billingAnOrderTest===>123");
	}
}
