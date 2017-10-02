import java.sql.*;

public class BookDetail {
	private String isbn;
	private String year;
	private String title;
	private String[] authors;
	private int count;
	private String[] keywords;

	public BookDetail(String isbn, String year, String title, String[] authors, String[] keywords, int count) {
		this.isbn = isbn;
		this.count = count;
		this.year = year;
		this.title = title;
		this.authors = authors;
		this.keywords = keywords;
	}

	public boolean isAvailable() {
		return false;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getYear() {
		return year;
	}

	public String getTitle() {
		return title;
	}

	public String[] getAuthors() {
		return authors;
	}

	public int getCount() {
		return count;
	}

	public String[] getKeywords() {
		return keywords;
	}
}