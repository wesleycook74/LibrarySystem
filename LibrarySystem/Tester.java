import java.util.ArrayList;
import java.sql.SQLException;

public class Tester {

	public static void main(String[] args) {
		Member mem1 = new Member("Chloe", "A", "Kimble", "1500 N Patterson St Valdosta GA", "123456789", "cakimble", "password");
		Member mem2 = new Member ("Arron", "T", "Croft", "123 Croft Rd Valdosta GA", "9998885555", "atcroft" , "somepassword" );
		Member mem3 = new Member("Dominique", "S", "Adkins", "555 Forest Rd Valdosta GA", "5552223333", "dsadkins", "diffpassword");
		Associate as1 = new Associate("Wes4", "I", "Cook", "111 New Lane Rd Valdosta GA", "4445557777", "wicook", "thispassword");


		ArrayList<BookDetail> bookDetails = Search.searchBooksByTitle("");

		for(BookDetail bd : bookDetails) {
			System.out.println(bd);
		}
	}

}

