package sample;

import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class PracticeReadDataFromTestNGXmlTest 
{
	@Test
	public void sampleTest(XmlTest test)
	{
		System.out.println(test.getParameter("browser"));
		System.out.println(test.getParameter("url"));
		System.out.println(test.getParameter("un"));
		System.out.println(test.getParameter("pw"));
		System.out.println("TestNG Executed successfully");
	}

}
