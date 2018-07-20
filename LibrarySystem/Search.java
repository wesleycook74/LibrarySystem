import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Search {

	public static ArrayList<Book> searchBooksByTitle(String title) {
		ArrayList<Book> books = new ArrayList<>();
		Connection con = Database.getConnection();
		String query = "SELECT ISBN\n" + "FROM BOOKS BD\n" + "WHERE BD.Title LIKE '%" + title + "%'";
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				books.add(new Book(rs.getString("ISBN")));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return books;
	}

	public static ArrayList<Book> searchBooksByISBN(String isbn) {
		ArrayList<Book> books = new ArrayList<>();
		Connection con = Database.getConnection();
		String query = "SELECT ISBN\n" + "FROM BOOKS BD\n" + "WHERE BD.ISBN='" + isbn + "'";
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				books.add(new Book(rs.getString("ISBN")));
			}
			rs.close();
			ps.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return books;
	}

	public static ArrayList<Book> searchBooksByYear(String year) {
		ArrayList<Book> books = new ArrayList<>();
		Connection con = Database.getConnection();
		String query = "SELECT ISBN\n" + "FROM BOOKS BD\n" + "WHERE BD.Year LIKE '%" + year + "%'";
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				books.add(new Book(rs.getString("ISBN")));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return books;
	}

	public static ArrayList<Book> searchBooksByAuthor(String author) {
		ArrayList<Book> books = new ArrayList<>();
		Connection con = Database.getConnection();
		String query = "SELECT DISTINCT(B.ISBN)\n" + "FROM BOOKS B, AUTHORS A\n" + "WHERE B.ISBN = A.ISBN AND A.AName LIKE '%" + author + "%'";
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				books.add(new Book(rs.getString("ISBN")));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return books;
	}

	public static ArrayList<Book> searchBooksByKeyword(String keyword) {
		ArrayList<Book> books = new ArrayList<>();
		Connection con = Database.getConnection();
		String query = "SELECT DISTINCT(B.ISBN)\n" + "FROM BOOKS B, KEYWORDS K\n" + "WHERE B.ISBN = K.ISBN AND K.Keyword LIKE '%" + keyword + "%'";
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				books.add(new Book(rs.getString("ISBN")));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return books;
	}
}
