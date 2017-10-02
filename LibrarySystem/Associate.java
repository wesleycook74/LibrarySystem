import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Associate extends Member {
	
	
	
	public Associate(String firstName, String lastName, String middleInitial, String address, String memberID,
			String phoneNumber, String userName, String password) throws SQLException {
		super(firstName, lastName, middleInitial, address, memberID, phoneNumber, userName, password);
		// TODO Auto-generated constructor stub
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
