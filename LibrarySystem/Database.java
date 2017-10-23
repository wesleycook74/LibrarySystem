import java.sql.*;
public class Database {

	// For opening Connections to the database
	public static Connection getConnection() {
		Connection con = null;
		//JDBC URL, user name and password of MySQL server
		String url = "jdbc:mysql://localhost:3306/library?useSSL=false";
		String user = "root";
		String password = "root";

		try {
			con = DriverManager.getConnection(url, user, password);
		}
		catch(Exception ex) {
			System.out.println("Unable to connect to database");

			ex.printStackTrace();

		}

		return con;
	}
}