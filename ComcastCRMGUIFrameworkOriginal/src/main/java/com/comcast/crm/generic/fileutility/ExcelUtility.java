package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
	public String getDataFromExcel(String sheetName, int rowNum, int CelNum ) throws Throwable
	{
		FileInputStream fis = new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cel = row.getCell(CelNum);
		String data = cel.getStringCellValue();
		wb.close(); //close the workbook from excel side otherwise object will be open and if execute 100 times then the open object for all the sheet will get crashed
		return data;
	}
	
	public int getRowCount(String sheetName) throws Throwable //to get the row we have to pass only one argument String sheetName
	{
		FileInputStream fis = new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowCount;
	}
	
	public void setDataIntoExcel(String sheetName, int rowNum, int CelNum, String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(CelNum);
		
		FileOutputStream fos = new FileOutputStream("./testdata/testScriptdata.xlsx");
		wb.write(fos); //save the file
		wb.close(); 
	}
}
