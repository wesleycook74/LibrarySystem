import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Search {

	public static ArrayList<BookDetail> searchBooksByTitle(String title) {
		ArrayList<BookDetail> bookDetails = new ArrayList<>();

		Connection con = Database.getConnection();
		String query = "SELECT ISBN\n" +
				       "FROM BOOK_DETAILS BD\n" +
				       "WHERE BD.Title LIKE '%" + title + "%'";

		try {
			//create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {

				bookDetails.add(new BookDetail(rs.getString("ISBN")));
			}

			rs.close();
			ps.close();
			con.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		return bookDetails;
	}

	public static ArrayList<BookDetail> searchBooksByISBN(String isbn) {
		ArrayList<BookDetail> bookDetails = new ArrayList<>();

		Connection con = Database.getConnection();
		String query = "SELECT ISBN\n" +
				"FROM BOOK_DETAILS BD\n" +
				"WHERE BD.ISBN='" + isbn + "'";

		try {
			//create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				bookDetails.add(new BookDetail(rs.getString("ISBN")));
			}

			rs.close();
			ps.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		return bookDetails;
	}

	public static ArrayList<BookDetail> searchBooksByYear(String year) {
		ArrayList<BookDetail> bookDetails = new ArrayList<>();

		Connection con = Database.getConnection();
		String query = "SELECT ISBN\n" +
				"FROM BOOK_DETAILS BD\n" +
				"WHERE BD.Year LIKE '%" + year + "%'";

		try {
			//create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {

				bookDetails.add(new BookDetail(rs.getString("ISBN")));
			}

			rs.close();
			ps.close();
			con.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		return bookDetails;
	}

	public static BookDetail[] searchBooksByAuthor(String author) {
		return null;
	}
}
