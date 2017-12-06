import java.sql.*;
import java.util.ArrayList;

// Represents all book information when at least one copy of the book is in inventory
public class Book {
	private String isbn;
	private String year;
	private String title;
	private ArrayList<String> authors;
	private int count;
	private ArrayList<String> keywords;

	public Book(String isbn) {
		this.isbn = isbn;
		// Pulls book data from database
		// Query to get the title and year from BOOKS table
		Connection con = Database.getConnection();
		String query = "SELECT Title, Year\n" + "FROM BOOKS BD\n" + "WHERE BD.ISBN='" + this.isbn + "';";
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				this.title = rs.getString("Title");
				this.year = rs.getString("Year");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		// Runs queries to get the count, authors, and keywords from the
		// database
		extractCount();
		extractAuthors();
		extractKeywords();

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

	public ArrayList<Copy> getAllCopies() {
		ArrayList<Copy> copies = new ArrayList<>();
		Connection con = Database.getConnection();
		String query = "SELECT ID \n" + "FROM COPIES B\n" + "WHERE B.ISBN='" + this.isbn + "';";
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				copies.add(new Copy(rs.getInt("ID")));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return copies;
	}



	public int getCount() {
		return count;
	}

	public ArrayList<String> getKeywords() {
		return keywords;
	}

	public Copy getAvailableCopy() {
		Copy b = null;
		Connection con = Database.getConnection();
		String query = "SELECT ID " +
				       "FROM COPIES " +
				       "WHERE ISBN=? AND CheckedOut=FALSE AND OnHold=FALSE";
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, this.isbn);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				b = new Copy(rs.getInt("ID"));
			}
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return b;
	}

	public Copy getCheckedOutCopy() {
		Copy b = null;
		Connection con = Database.getConnection();
		String query = "SELECT ID " +
				       "FROM COPIES " +
				       "WHERE ISBN=? AND CheckedOut=TRUE AND OnHold=FALSE";
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, this.isbn);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				b = new Copy(rs.getInt("ID"));
			}
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return b;
	}

	public void reportLost(String isbn) {
		this.isbn = isbn;
		Connection con = Database.getConnection();
		String query8 = "UPDATE COPY " +
				"SET IsLost = FALSE\n" +
				"WHERE ISBN = " +isbn;
		try {
			// create the prepared statement
			PreparedStatement ps8 = con.prepareStatement(query8);
			ps8.setString(1, this.isbn);
			ResultSet rs8 = ps8.executeQuery();
			if (rs8.next()) {
				System.out.println("Reported book lost.");
			}
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	public void assessFees() {
		Connection con = Database.getConnection();
		String query9 = "UPDATE MEMBERS " +
				"SET Fines = Fines + 0.05" +
				"WHERE CurDate()+14 > Books.DateOut";
		try {
			// create the prepared statement
			PreparedStatement ps9 = con.prepareStatement(query9);
			ps9.setString(1, this.isbn);
			ResultSet rs9 = ps9.executeQuery();
			if (rs9.next()) {
				System.out.println("Assessed Overdue Fines.");
			}
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	private void extractCount() {
		Connection con = Database.getConnection();
		String query = "SELECT COUNT(ID) AS 'count'" + "FROM COPIES B\n" + "WHERE B.ISBN='" + this.isbn + "';";
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				this.count = rs.getInt("count");
			} else {
				this.count = 0;
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	private void extractAuthors() {
		ArrayList<String> authors = new ArrayList<String>();
		Connection con = Database.getConnection();
		String query = "SELECT AName\n" + "FROM AUTHORS A\n" + "WHERE A.ISBN='" + this.isbn + "'";
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				authors.add(rs.getString("AName"));
			}
			this.authors = authors;
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	private void extractKeywords() {
		ArrayList<String> keywords = new ArrayList<>();
		Connection con = Database.getConnection();
		String query = "SELECT keyword\n" + "FROM KEYWORDS K\n" + "WHERE K.ISBN='" + this.isbn + "'";
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				keywords.add(rs.getString("keyword"));
			}
			this.keywords = keywords;
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public String toString() {
		String retval = "ISBN: " + isbn + "\n";
		retval += "Title: " + title + "\n";
		retval += "No. of Copies: " + count + "\n";
		retval += "Author(s): ";

		for (int i = 0; i < authors.size(); i++) {
			if (i < authors.size() - 1) {
				retval += authors.get(i) + ", ";
			} else {
				retval += authors.get(i) + "\n";
			}
		}

		retval += "KeyWords(s): ";
		for (int i = 0; i < keywords.size(); i++) {
			if (i < keywords.size() - 1) {
				retval += keywords.get(i) + ", ";
			} else {
				retval += keywords.get(i) + "\n";
			}
		}
		retval += "Year: " + year + "\n";
		return retval;
	}
}