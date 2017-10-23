import java.util.ArrayList;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;

public class Tester {

	public static void main(String[] args) {
		Member mem1 = new Member("Chloe", "A", "Kimble", "1500 N Patterson St Valdosta GA", "123456789", "cakimble", "password");
		Member mem2 = new Member ("Arron", "T", "Croft", "123 Croft Rd Valdosta GA", "9998885555", "atcroft" , "somepassword" );
		Member mem3 = new Member("Dominique", "S", "Adkins", "555 Forest Rd Valdosta GA", "5552223333", "dsadkins", "diffpassword");
		
		Associate as1 = new Associate("Wesley", "I", "Cook", "111 New Lane Rd Valdosta GA", "4445557777", "wicook", "thispassword");
		
		Member mem4 = new Member("Blah", "X", "Blahblah", "675 Gritt Rd Valdosta GA", "2296869999", "blah", "blahpassword");
		System.out.println(mem4.toString());
		
		Member mem5 = new Member("Newbie", "C", "Newbern", "555 Newbie Rd Valdosta GA", "2296864444", "newbieiee", "newpassword");
		System.out.println(mem5.toString());
		
		Member mem6 = new Member("Dennis", "A", "Vegas", "999 Dum Rd Valdosta GA", "229688814", "dennisvegas", "dennispassword");
		System.out.println(mem6.toString());
		
		
		
		System.out.println("\n\nNow for searches:");
		ArrayList<BookDetail> bookDetails = Search.searchBooksByTitle("");

		for(BookDetail bd : bookDetails) {
			System.out.println(bd.getTitle());
		}
	}

}

