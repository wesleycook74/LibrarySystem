import java.util.ArrayList;
import java.sql.SQLException;

public class Tester {

	public static void main(String[] args) {
		//Member mem1 = new Member("Chloe", "A", "Kimble", "1500 N Patterson St Valdosta GA", "123456789", "cakimble", "password");
		//Associate ass1 = new Associate ("Arron", "T", "Croft", "123 Croft Rd Valdosta GA", "9998885555", "atcroft" , "somepassword" );
		//Member mem2 = new Member("Dominique", "S", "Adkins", "555 Forest Rd Valdosta GA", "5552223333", "dsadkins", "diffpassword");

		ArrayList<BookDetail> bookDetails = Search.searchBooksByTitle("");

		for(BookDetail bd : bookDetails) {
			System.out.println(bd);
		}
	}

}

