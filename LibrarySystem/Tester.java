import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {
   
   //I think the throws have to go there when you connect the database
	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException{ 
		
      BookDetail[] bookDetails = Search.searchBooksByTitle("The");
  
       //testing members
         Member mem1 = new Member("Chloe", "Kimble", "A", "0001", "1500 N Patterson St Valdosta, GA 31698", "123456789", "cakimble", "password");
        



      
	}

}
