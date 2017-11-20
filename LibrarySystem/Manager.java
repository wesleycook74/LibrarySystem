import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager extends Associate {

	public Manager(int id) {
		super(id);
	}

	public Manager(String username) {
		super(username);
	}


	public void createAssociate(String firstName, String middleInitial, String lastName, String address, String phoneNumber,
								  String userName, String password){

		int memberID=0;

		Connection con = Database.getConnection();

		String query = "insert into MEMBERS (Fname, Minit, Lname, Address, PhoneNumber, Username, Password, Is_active)"
				+ " values (?, ?, ?, ?, ?, ? ,?, ?)";

		PreparedStatement ps2 = null;
		try {
			ps2 = con.prepareStatement(query);
			ps2.setString(1, firstName);
			ps2.setString(2, middleInitial);
			ps2.setString(3, lastName);
			ps2.setString(4, address);
			ps2.setString(5, phoneNumber);
			ps2.setString(6, userName);
			ps2.setString(7, password);
			ps2.setBoolean(8, true);

			ps2.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String getmemid = "SELECT MemberID\n" + "FROM MEMBERS BD\n" + "WHERE Username = '" + userName + "'";

		try {
			PreparedStatement mid = con.prepareStatement(getmemid);
			ResultSet rs = mid.executeQuery();

			// int id = ((Integer) rs.getObject(1)).intValue();
			// int id = Integer.parseInt(rs.getObject(1).toString());
			while (rs.next()) {
				memberID = rs.getInt("MemberID");
			}
			rs.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query3 = "insert into ASSOCIATES (MemberID, Manager)" + " values (?, ?)";
		try {

			PreparedStatement ps3 = con.prepareStatement(query3);
			ps3.setInt(1, memberID);
			ps3.setInt(2, 0);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	public void editMember() {

	}

	public void deleteMember() {

	}

	public void assessFines() {

	}

	public void addBooks(String isbn) {

	}

	public void addBooks(String title, String isbn, String year, String[] authors, String[] keywords, int count) {

	}

	// Removes a physical copy of the book from inventory
	public void removeBooks(Book book) {

	}

	// Removes book detail and all corresponding books from library database
	public void removeAllBooks(BookDetail bookDetail) {

	}

	public void editBooks(String isbn) {

	}
}
