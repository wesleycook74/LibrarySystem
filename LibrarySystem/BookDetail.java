import java.sql.*;

// Represents all book information when at least one copy of the book is in inventory
public class BookDetail {
	private String isbn;
	private String year;
	private String title;
	private String[] authors;
	private int count;
	private String[] keywords;

	public BookDetail(String isbn) {
		//Pulls book data from database

		Connection con = Database.getConnection();
		String query = "SELECT Title, Year\n" +
				"FROM BOOK_DETAILS BD\n" +
				"WHERE BD.ISBN=" + isbn;
		try {
			//create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				this.title = rs.getString("Title");
				this.year = rs.getString("Year");
			}


			rs.close();
			ps.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
	}

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