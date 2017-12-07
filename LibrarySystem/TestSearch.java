import java.util.ArrayList;
public class TestSearch {
	public static void main (String[] args) {
//		ArrayList<Book> books = Search.searchBooksByTitle("");
		ArrayList<Book> books = Search.searchBooksByAuthor("George");
//		ArrayList<Book> books = Search.searchBooksByYear("2012");
		for(Book book : books) {
			System.out.println(book);
		}
	}
}
