import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Associate extends Member {

	public Associate(int id)
	{
		super(id);
	}

	public Associate(String username)
	{
		super(username);
	}

	public void createMember(String firstName, String middleInitial, String lastName, String address, String phoneNumber,
							String userName, String password){
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
			ps.setInt(9,0);//MemberLevel
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
