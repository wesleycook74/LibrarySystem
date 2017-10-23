import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Associate extends Member {

	public Associate(String firstName, String middleInitial, String lastName,   String address,
			String phoneNumber, String userName, String password) {
		super(firstName, middleInitial, lastName, address, phoneNumber, userName, password);
		// TODO Auto-generated constructor stub
		this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
		Connection con = Database.getConnection();
		String getmemid = "SELECT MemberID\n" +
			       "FROM MEMBERS BD\n" +
			       "WHERE Username LIKE '%" + userName + "%'";
		String query = "insert into ASSOCIATES (MemberID, Manager)"
				+ " values (?, ?)";


		try {
			PreparedStatement mid = con.prepareStatement(getmemid);
			ResultSet rs = mid.executeQuery();
			System.out.println("rs = " + rs);
			
			//int id =  ((Integer) rs.getObject(1)).intValue();
			//int id = Integer.parseInt(rs.getObject(1).toString());
			int id = 0;
			while(rs.next()) {
				id = rs.getInt("memberID");
			}
			rs.close();
		
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
