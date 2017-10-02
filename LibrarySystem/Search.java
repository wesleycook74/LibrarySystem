import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Search {
	public static BookDetail[] searchBooksByTitle(String title) {
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

	public static BookDetail[] searchBooksByYear(String year) {
		return null;
	}

	public static BookDetail[] searchBooksByAuthor(String author) {
		return null;
	}
}
