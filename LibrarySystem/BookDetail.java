import java.sql.*;
import java.util.ArrayList;

// Represents all book information when at least one copy of the book is in inventory
public class BookDetail {
	private String isbn;
	private String year;
	private String title;
	private ArrayList<String> authors;
	private int count;
	private ArrayList<String> keywords;

	public BookDetail(String isbn) {
		this.isbn = isbn;
		//Pulls book data from database
		// Query to get the title and year from BOOK_DETAILS table
		Connection con = Database.getConnection();
		String query = "SELECT Title, Year\n" +
				"FROM BOOK_DETAILS BD\n" +
				"WHERE BD.ISBN='" + this.isbn + "';";
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
			con.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}

		//Runs queries to get the count, authors, and keywords from the database
		extractCount();
		extractAuthors();
		extractKeywords();

	}

	public BookDetail(String isbn, String year, String title, ArrayList<String> authors, ArrayList<String> keywords, int count) {
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

	public ArrayList<String> getAuthors() {
		return authors;
	}

	public int getCount() {
		return count;
	}

	public ArrayList<String> getKeywords() {
		return keywords;
	}

	private void extractCount() {
		Connection con = Database.getConnection();
		String query = "SELECT COUNT(ID) AS 'count'" +
				"FROM BOOKS B\n" +
				"WHERE B.ISBN='" + this.isbn + "';";
		try {
			//create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				this.count = rs.getInt("count");

			}
			else {
				this.count = 0;
			}

			rs.close();
			ps.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public String toString() {
		String retval ="ISBN: " + isbn + "\n";
		retval += "Title: " + title + "\n";
		retval += "No. of Copies: " + count + "\n";
		retval += "Author(s): ";

		for(int i=0; i<authors.size(); i++) {
			if(i< authors.size() -1) {
				retval += authors.get(i) + ", ";
			}
			else {
				retval += authors.get(i) + "\n";
			}
		}

		retval += "KeyWords(s): ";
		for(int i=0; i<keywords.size(); i++) {
			if(i< keywords.size() -1) {
				retval += keywords.get(i) + ", ";
			}
			else {
				retval += keywords.get(i) + "\n";
			}
		}
		retval += "Year: " + year + "\n";
		return retval;
	}

	private void extractAuthors() {
		ArrayList<String> authors = new ArrayList<String>();

		Connection con = Database.getConnection();
		String query = "SELECT AName\n" +
				       "FROM AUTHORS A\n" +
				       "WHERE A.ISBN='" + this.isbn + "'";

		try {
			//create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				authors.add(rs.getString("AName"));
			}
			this.authors = authors;

			rs.close();
			ps.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
	}

	private void extractKeywords() {
		ArrayList<String> keywords = new ArrayList<>();

		Connection con = Database.getConnection();
		String query = "SELECT keyword\n" +
				       "FROM KEYWORDS K\n" +
				       "WHERE K.ISBN='" + this.isbn + "'";

		try {
			//create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				keywords.add(rs.getString("keyword"));
			}
			this.keywords = keywords;
			rs.close();
			ps.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
	}
}