package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteSelectQueryTest 
{
	public static void main(String[] args) throws SQLException 
	{
		//step 1: Load/register the database driver
		Driver driverRef = new Driver();
		DriverManager.registerDriver( driverRef);
		
		//Step 2: connect to database
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		System.out.println("=========Done==========");
		
		//step 3: create SQL statement
		
		Statement stat = conn.createStatement();
		
		//step 4: execute select query and get the result
		
		ResultSet resultset = stat.executeQuery("select * from project");
		
		while(resultset.next())
		{
			System.out.println(resultset.getInt(1)+"\t"+resultset.getString(2)+"\t"+resultset.getString(3)+"\t"+resultset.getString(4)+"\t"+resultset.getString(5));
		}
		
		//step 5: close the connection
		
		conn.close();
	}

}
