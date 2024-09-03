package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteNonSelectQueryTest 
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
		int result = stat.executeUpdate(" insert into project values(125,'LinkedIn','pooja',15,'Completed', 45);");
		System.out.println(result);
				
	}
}
