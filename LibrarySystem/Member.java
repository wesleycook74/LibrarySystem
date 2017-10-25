import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Member {
	 private String firstName, lastName, middleInitial;
	 private int memberID;
	 private String phoneNumber;
	 private String userName, password;
	 private Book[] checkedOut;
	 private String address;
	 private double fines;

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
			ps2.setBoolean(8, true);

			ps2.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		String getmemid = "SELECT MemberID\n" +
			       "FROM MEMBERS BD\n" +
			       "WHERE Username = '" + userName + "'";

		try {
			PreparedStatement mid = con.prepareStatement(getmemid);
			ResultSet rs = mid.executeQuery();
			
			//int id =  ((Integer) rs.getObject(1)).intValue();
			//int id = Integer.parseInt(rs.getObject(1).toString());
			while(rs.next()) {
				this.memberID = rs.getInt("MemberID");
			}
			rs.close();
			con.close(); 

		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
	
	public Member(int memID) {
		
		this.memberID = memID; 
		
		Connection con = Database.getConnection();

		String query = "SELECT MemberID, Fname, Lname, Minit, Address, PhoneNumber, Username, Password, Fines, Is_active\n" +
				"FROM MEMBERS \n" +
				"WHERE MEMBERS.MemberID =" + memID + ";";

		PreparedStatement ps2 = null;
		try {
			//create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {

				this.memberID = rs.getInt("MemberID");
				this.firstName = rs.getString("Fname");
		        this.lastName = rs.getString("Lname");
		        this.middleInitial = rs.getString("Minit");
		        this.address = rs.getString("Address");
		        this.phoneNumber = rs.getString("PhoneNumber");
		        this.userName = rs.getString("Username");
		        this.password = rs.getString("Password");
		        this.fines = rs.getDouble("Fines");
			}

			rs.close();
			ps.close();
			con.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
	
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public int getMemberID() {
		return memberID;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public Book[] getCheckedOut() {
		ArrayList<String> books = new ArrayList<String>();

		Connection con = Database.getConnection();
		String query = "SELECT Title\n" +
				       "FROM BOOK_DETAILS D, BOOKS B\n" +
				       "WHERE D.ISBN= B.ISBN AND B.MemberID = " + memberID;

		try {
			//create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				books.add(rs.getString("Title"));
				System.out.println("Titles: " + rs.getString("Title"));
			}
			//this.books = books;

			rs.close();
			ps.close();
			con.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		return checkedOut;
	}

	public String getAddress() {
		return address;
	}

	public double getFines() {
		return fines;
	}
	
	public void payFines (double amountpaid) {
		fines -= amountpaid;
		Connection con = Database.getConnection();

		String query = "UPDATE MEMBERS\n" +
				"SET Fines=Fines - " + amountpaid +
				"\nWHERE MEMBERS.MemberID =" + memberID + ";";
		try {
			//create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ps.close();
			con.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
	}


	
	@Override
	public String toString() {
		return "Member [firstName=" + firstName +  ", middleInitial=" + middleInitial + ", lastName=" + lastName 
				+ ", memberID=" + memberID + ", phoneNumber=" + phoneNumber + ", address=" + address +", userName=" + userName + ", checkedOut="
				+ Arrays.toString(checkedOut)  + ", fines=" + fines + "]";
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

	public void suspendAccount () {
		String update = "UPDATE MEMBERS \n" +
				       "SET Is_active=FALSE \n" +
					   "WHERE MemberID=" + memberID + ";";

		Database.runUpdate(update);

	}

	public void activateAccount() {

		String update = "UPDATE MEMBERS \n" +
				"SET Is_active=TRUE \n" +
				"WHERE MemberID=" + memberID + ";";

		Database.runUpdate(update);
	}

	// Returns true if the memberID is valid
	public boolean isValid() {
		// Needs to be finished
		return true;
	}

	// returns true if the member account is valid and is not suspended
	public boolean isActive() {
		if(isValid()) {
			String query = "SELECT Is_active\n" +
						   "FROM MEMBERS M\n" +
						   "WHERE M.MemberID=" + this.memberID;
			Connection con = Database.getConnection();
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					return rs.getBoolean("Is_active");
				}
				rs.close();
				ps.close();
				con.close();

			}catch(SQLException e) {
				e.printStackTrace();
			}

		}
		return false;
	}
}
