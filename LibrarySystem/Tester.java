import java.util.ArrayList;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) throws SQLException {
		Member mem1 = new Member("Chloe", "A", "Kimble", "1500 N Patterson St Valdosta GA", "123456789", "cakimble", "password");
		Associate ass1 = new Associate ("Arron", "T", "Croft", "123 Croft Rd Valdosta GA", "9998885555", "atcroft" , "somepassword" );
		Member mem2 = new Member("Dominique", "S", "Adkins", "555 Forest Rd Valdosta GA", "5552223333", "dsadkins", "diffpassword");

		
		ArrayList<BookDetail> bookDetails = Search.searchBooksByTitle("The");
		for(BookDetail bd : bookDetails) {
			System.out.println(bd.getTitle());
		}
	}

}

