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

	public void createManager(String firstName, String middleInitial, String lastName, String address, String phoneNumber,
								  String userName, String password){
		int memberID = 0;
		Connection con = Database.getConnection();
		String query = "INSERT INTO MEMBERS (Fname, Minit, Lname, Address, PhoneNumber, Username, Password, IsActive, MemberLevel)"
				+ " VALUES (?, ?, ?, ?, ?, ? ,?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, firstName);
			ps.setString(2, middleInitial);
			ps.setString(3, lastName);
			ps.setString(4, address);
			ps.setString(5, phoneNumber);
			ps.setString(6, userName);
			ps.setString(7, password);
			ps.setBoolean(8, true);//IsActive
			ps.setInt(9, 2);//MemberLevel
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//why do we have this part? - Arron
		String getmemid = "SELECT MemberID\n" + "FROM MEMBERS\n" + "WHERE Username = '" + userName + "'";
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
	}

	public void createAssociate(String firstName, String middleInitial, String lastName, String address,
			String phoneNumber, String userName, String password) {
		int memberID = 0;
		Connection con = Database.getConnection();
		String query = "INSERT INTO MEMBERS (Fname, Minit, Lname, Address, PhoneNumber, Username, Password, IsActive, MemberLevel)"
				+ " VALUES (?, ?, ?, ?, ?, ? ,?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, firstName);
			ps.setString(2, middleInitial);
			ps.setString(3, lastName);
			ps.setString(4, address);
			ps.setString(5, phoneNumber);
			ps.setString(6, userName);
			ps.setString(7, password);
			ps.setBoolean(8, true);//IsActive
			ps.setInt(9, 1);//MemberLevel
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// why do we have this part? - Arron
		String getmemid = "SELECT MemberID\n" + "FROM MEMBERS\n" + "WHERE Username = '" + userName + "'";
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
