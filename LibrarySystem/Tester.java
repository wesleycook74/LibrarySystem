import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		ArrayList<BookDetail> bookDetails = Search.searchBooksByTitle("The");
		for(BookDetail bd : bookDetails) {
			System.out.println(bd.getTitle() + ", " + bd.getCount());
		}
	}

}
