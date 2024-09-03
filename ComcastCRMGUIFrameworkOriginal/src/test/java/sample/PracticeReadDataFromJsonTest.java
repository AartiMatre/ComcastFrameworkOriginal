package sample;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PracticeReadDataFromJsonTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException 
	{
		//step 1: parse or convert JSon physical file into java object using JSonParse class
		
		JSONParser parser = new JSONParser();
		//convert physical object into another java JSon object
		Object obj = parser.parse(new FileReader("C:\\Users\\Rohit Pandey\\Desktop\\Data\\appCommonData.json")); //we can use FIleInputStream as well
		
		//step 2: convert or catch or transfer the data of java object into JSon object using DOWNCASTING
		
		JSONObject map = (JSONObject)obj;//DOWNCASTING
		
		//step 3: get the value from JSon file using key
		String BROWSER = (map.get("browser")).toString();
		System.out.println(map.get("url"));
		System.out.println(map.get("un"));
		System.out.println(map.get("pw"));
		System.out.println(map.get("timeOut"));
		System.out.println(BROWSER);
	}

}
