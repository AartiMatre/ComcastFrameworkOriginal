package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility 
{
	Connection conn; //declare here so that we can close the connection in another method

	public void getDbConnection(String url, String un, String pw) throws SQLException
	{
		try 
		{
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			conn = DriverManager.getConnection(url, un, pw);
		} 
		catch (Exception e) 
		{

		}
	}
	// we can call either one of the method 
	public void getDbConnection() throws SQLException //for calling this method is good(no need to pass credential) //HardCode the data because in entire application we'll have one database
	{
		try 
		{
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		} 
		catch (Exception e) 
		{

		}
	}
	
	public ResultSet executeSelectQuery(String query) throws SQLException //SQL query
	{
		ResultSet result = null;
		try 
		{
			Statement stat = conn.createStatement();
			result = stat.executeQuery(query);
		} 
		catch (Exception e) {
		}
		return result;
	}
	
	public int executeNonSelectQuery(String query) //NonSQL query
	{
		int result = 0;
		try 
		{
			Statement stat = conn.createStatement();
			result = stat.executeUpdate(query);
		} 
		catch (Exception e) {
		}
		return result;
	}
	
	public void closeDbConnection() throws SQLException 
	{
		try 
		{
			conn.close();
		} catch (Exception e) {
		}
	}
	
}
