import java.sql.*;

public class Book {
	private String isbn;
	private int id;
	private String year;
	private String title;
	private String[] authors;
	private int count;
	private String[] keywords;

	public Book(int id, String isbn, String year, String title, String[] authors, String[] keywords) {
		this.isbn = isbn;
		this.id = id;
		this.year = year;
		this.title = title;
		this.authors = authors;
		this.keywords = keywords;
	}


	public static Book[] searchBooksByTitle(String title) {
		Connection con = Database.getConnection();
		String query = "";
		try {
			//create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

			}
			rs.close();
			ps.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		return null;
	}

	public static Book[] searchBooksByYear(String year) {
		return null;
	}

	public static Book[] searchBooksByAuthor(String author) {
		return null;
	}

	public boolean isAvailable() {
		return false;
	}
	public String getIsbn() {
		return isbn;
	}

	public int getId() {
		return id;
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