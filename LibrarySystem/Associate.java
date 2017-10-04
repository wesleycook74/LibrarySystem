import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Associate extends Member {

	public Associate(String firstName, String lastName, String middleInitial,  String address,
			String phoneNumber, String userName, String password) throws SQLException {
		super(firstName, lastName, middleInitial, address, phoneNumber, userName, password);
		// TODO Auto-generated constructor stub

		//JDBC URL, username and password of MySQL server
    	final String url = "jdbc:mysql://localhost:3306/library?useSSL=false";
    	final String user = "root";
    	final String password1 = "root";
    	// JDBC variables for opening and managing connection
    	Connection con;
		// opening database connection to MySQL server
		con = DriverManager.getConnection(url, user, password1);
		System.out.println("Database connected successfully");
		String query = "insert into associates (MemberID, Manager)"
				+ " values (?, ?)";

		PreparedStatement ps2 = con.prepareStatement(query);
		ps2.setInt(1, 2);
		ps2.setInt(2, 0);

		ps2.execute();
		con.close();

	}


}
