import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Associate extends Member {

	public Associate(String firstName, String lastName, String middleInitial, String memberID, String address,
			String phoneNumber, String userName, String password) throws SQLException {
		super(firstName, lastName, middleInitial, memberID, address, phoneNumber, userName, password);
//		Member mem1 = new Member("Chloe", "Kimble", "A", "0001", "1500 N Patterson St Valdosta, GA 31698", "123456789", "cakimble", "password");
//		// TODO Auto-generated constructor stub
//		Connection con = Database.getConnection();
//		PreparedStatement ps = con.prepareStatement();
//		String query = "INSERT INTO MEMBERS VALUES(";
//		query += "'" + firstName + "', ";
//        query += "'" + middleInitial + "', ";
//        query += "'" + lastName + "', ";
//        query += memberID + ", ";
//        query += "'" + address + "', ";
//        query += phoneNumber + ", ";
//        query += "'" + userName + "', ";
//        query += "'" + password;
//				query += ");";
//		System.out.println(query);
//		stmt.execute(query);
	}
	public void createMember(String firstName, String lastName, String middleInitial,
            String memberID, String phoneNumber, String userName, String password) throws SQLException{

		Connection con = Database.getConnection();
		Statement stmt = con.createStatement();
		String query = "INSERT INTO members VALUES(";
		query += firstName;
        query += middleInitial;
        query += lastName;
        query += memberID;
        query += address;
        query += phoneNumber;
        query += userName;
        query += password;
				query += ");";
				System.out.println(query);
				stmt.execute(query);
	}


}
