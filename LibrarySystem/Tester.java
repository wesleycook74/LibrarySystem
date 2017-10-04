import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) throws SQLException {
		Member mem1 = new Member("Chloe", "A", "Kimble", "1500 N Patterson St Valdosta GA", "123456789", "cakimble", "password");
		Associate ass1 = new Associate ("Arron", "T", "Croft", "123 Croft Rd Valdosta GA", "9998885555", "atcroft" , "somepassword" );
		
		ArrayList<BookDetail> bookDetails = Search.searchBooksByTitle("The");
		for(BookDetail bd : bookDetails) {
			System.out.println(bd);
		}
	}

}
