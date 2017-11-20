import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Associate extends Member {

	public Associate(String firstName, String middleInitial, String lastName, String address, String phoneNumber,
			String userName, String password) {
		super(firstName, middleInitial, lastName, address, phoneNumber, userName, password);
		//sendassociatetoDB(firstName, middleInitial, lastName, address, phoneNumber, userName, password);

	}
	
	
	public void sendassociatetoDB(String firstName, String middleInitial, String lastName, String address, String phoneNumber,
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
			ps2.setInt(1, memberID);
			ps2.setInt(2, 0);

			ps2.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public Member createMember(String firstName, String middleInitial, String lastName, String address, String phoneNumber,
			String userName, String password){
		Member mem = new Member( firstName,  middleInitial, lastName,  address,  phoneNumber, userName, password);
		return mem;
	}
	
	public void sendmembertoDB(Member mem){
		Connection con = Database.getConnection();

		String query = "insert into MEMBERS (Fname, Minit, Lname, Address, PhoneNumber, Username, Password, Is_active)"
				+ " values (?, ?, ?, ?, ?, ? ,?, ?)";

		PreparedStatement ps2 = null;
		try {
			ps2 = con.prepareStatement(query);
			ps2.setString(1, mem.getFirstName());
			ps2.setString(2, mem.getMiddleInitial());
			ps2.setString(3, mem.getLastName());
			ps2.setString(4, mem.getAddress());
			ps2.setString(5, mem.getPhoneNumber());
			ps2.setString(6, mem.getUserName());
			ps2.setString(7, mem.getPassword());
			ps2.setBoolean(8, true);

			ps2.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String getmemid = "SELECT MemberID\n" + "FROM MEMBERS BD\n" + "WHERE Username = '" + mem.getUserName() + "'";

		try {
			PreparedStatement mid = con.prepareStatement(getmemid);
			ResultSet rs = mid.executeQuery();

			// int id = ((Integer) rs.getObject(1)).intValue();
			// int id = Integer.parseInt(rs.getObject(1).toString());
			while (rs.next()) {
				int memberID = rs.getInt("MemberID");
			}
			rs.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

}
