import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Associate extends Member {

	public Associate(String firstName, String lastName, String middleInitial,  String address,
			String phoneNumber, String userName, String password) throws SQLException {
		super(firstName, lastName, middleInitial, address, phoneNumber, userName, password);
		// TODO Auto-generated constructor stub

		Connection con = Database.getConnection();
		String query = "insert into ASSOCIATES (MemberID, Manager)"
				+ " values (?, ?)";

		PreparedStatement ps2 = con.prepareStatement(query);
		ps2.setInt(1, 2);
		ps2.setInt(2, 0);

		ps2.execute();
		con.close();

	}


}
