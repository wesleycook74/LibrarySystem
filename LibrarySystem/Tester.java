import java.util.ArrayList;
<<<<<<< HEAD
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
		
		ArrayList<BookDetail> bookDetails = Search.searchBooksByTitle("The");
=======
import java.sql.SQLException;

public class Tester {

	public static void main(String[] args) {
		Member mem1 = new Member("Chloe", "A", "Kimble", "1500 N Patterson St Valdosta GA", "123456789", "cakimble", "password");
		Member mem2 = new Member ("Arron", "T", "Croft", "123 Croft Rd Valdosta GA", "9998885555", "atcroft" , "somepassword" );
		Member mem3 = new Member("Dominique", "S", "Adkins", "555 Forest Rd Valdosta GA", "5552223333", "dsadkins", "diffpassword");
		Associate as1 = new Associate("Wes4", "I", "Cook", "111 New Lane Rd Valdosta GA", "4445557777", "wicook", "thispassword");


		ArrayList<BookDetail> bookDetails = Search.searchBooksByTitle("");

>>>>>>> 3f5ddc87a4cf295a9c553ed25d2e4ac36bfde5d0
		for(BookDetail bd : bookDetails) {
			System.out.println(bd.getTitle());
		}
	}

}

