import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
//these were just the same ones as my database prject thing

//connect the database
//	JDBC URL, username and password of MySQL server
	private static final String url = "jdbc:mysql://localhost:3306/group_project?useSSL=false";
	private static final String user = "root";
	private static final String password = "root";

	// JDBC variables for opening and managing connection
	private static Connection con;
	private static Statement stmt;
//end connection


public class Tester {
   
   //I think the throws have to go there when you connect the database
	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException{ 
		
      BookDetail[] bookDetails = Search.searchBooksByTitle("The");
  
      //I just feel like this has to  be here
         //insert statement for creating members, not really sure where it should go, but anyhoo.
         // opening database connection to MySQL server
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Database connected successfully");

			// getting Statement object to execute query
			stmt = con.createStatement();
         
         //testing members
         Member cakimble = new Member("Chloe", "Kimble", "A", "1", "123456789", "cakimble", "password");
        



      
	}

}
