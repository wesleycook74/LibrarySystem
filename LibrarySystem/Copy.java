import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Represents a physical copy of a book in inventory; linked to book detail by ISBN
// Database can store multiple books with the same ISBN
public class Copy {
	int id;
	String isbn;

	public Copy(int id) {
		this.id = id;
		Connection con = Database.getConnection();
		String query = "SELECT ISBN\n" + "FROM COPIES C\n" + "WHERE C.ID=" + this.id + ";";
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				this.isbn = rs.getString("ISBN");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	public void reportLost(String id) {
		this.isbn = isbn;
		Connection con = Database.getConnection();
		String update = "UPDATE COPY " +
				"SET IsLost = TRUE\n" +
				"WHERE ID = " + id;
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(update);
			ps.executeQuery();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	public int getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}

	public boolean isAvailable() {
		return true;
	}

	public boolean equals(Object o){
		if(((Copy)o).getId() == (this.id))
			return true;
		else
			return false;
	}
}
