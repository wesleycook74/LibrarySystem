import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Associate extends Member {

	public Associate(String firstName, String middleInitial, String lastName, String address, String phoneNumber,
			String userName, String password) {
		super(firstName, middleInitial, lastName, address, phoneNumber, userName, password);

		String query = "insert into ASSOCIATES (MemberID, Manager)" + " values (?, ?)";

		int id = this.getMemberID();
		Connection con = Database.getConnection();
		try {

			PreparedStatement ps2 = con.prepareStatement(query);
			ps2.setInt(1, id);
			ps2.setInt(2, 0);

			ps2.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
