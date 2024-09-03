package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class JsonUtility 
{
	public String getDataFromJsonFile(String key) throws IOException, ParseException
	{
		FileReader fileR = new FileReader("./configAppData/appCommonData.properties");
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fileR);
		//convert java object into JSon object using DOWNCASTING
		JSONObject map = (JSONObject)obj;//DOWNCASTING
		//get the value from JSon file using key
		String data = (String)map.get(key);
		return data;
	}
}
