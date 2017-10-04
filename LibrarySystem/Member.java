import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Member {
	 String firstName, lastName, middleInitial;
	 String memberID;
	 String phoneNumber;
	 String userName, password;
	 Book[] checkedOut;
	 String address;

	public Member(String firstName, String middleInitial, String lastName,
            String address, String phoneNumber, String userName, String password) {
		this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        
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
			ps2.setInt(8, 1);

			ps2.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	public void checkOut (Book[] books){
      
	}

	public void returnBooks (Book[] books){

	}

	public void renewBooks (Book[] books){

	}

	public void placeHold (BookDetail[] bookDetails){

	}

	public void reportLost (Book[] book){

	}
}
