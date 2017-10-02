import java.sql.*;

public class Book {
	private String isbn;
	private int id;
	private String year;
	private String title;
	private String[] authors;
	private String[] keywords;

	public Book(String isbn, int id, String year, String title, String[] authors, String[] keywords) {
		this.isbn = isbn;
		this.id = id;
		this.year = year;
		this.title = title;
		this.authors = authors;
		this.keywords = keywords;
	}

	public static Book[] searchBooksByTitle(String query) {
		return null;
	}

	public static Book[] searchBooksByAuthor(String query) {
		return null;
	}

	public boolean isAvailable() {

		return false;
	}
}