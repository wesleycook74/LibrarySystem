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

		String query = "insert into MEMBERS values (?, ?, ?, ?, ?, ? ,?, ?)";

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
			ps2.setInt(9,0);

			ps2.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	

}
