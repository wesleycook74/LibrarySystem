import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Member {
	 String firstName, lastName, middleInitial;
	 int memberID;
	 String phoneNumber;
	 String userName, password;
	 Book[] checkedOut;
	 String address;
	 double fines;

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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		String getmemid = "SELECT MemberID\n" +
			       "FROM MEMBERS BD\n" +
			       "WHERE Username = '" + userName + "'";

		try {
			PreparedStatement mid = con.prepareStatement(getmemid);
			ResultSet rs = mid.executeQuery();
			System.out.println("rs = " + rs);
			
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

		String query = "SELECT MemberID, Fname, Lname, Minit, Address, PhoneNumber, Username, Password, Fines\n" +
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
	
	public void payFines (double amountpaid, int memID) {
		Connection con = Database.getConnection();

		String query = "UPDATE MEMBERS\n" +
				"SET Fines = " + (fines-amountpaid) + 
				"\nWHERE MEMBERS.MemberID =" + memID + ";";
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
		Connection con = Database.getConnection();

		String query = "UPDATE BOOKS \n" +
				       "SET Is_active=TRUE \n" +
					   "WHERE MemberID=" + memberID + ";";

		PreparedStatement ps2 = null;
		try {
			//create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ps.executeUpdate();

			ps.close();
			con.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}

	}

	public void reactivateAccount() {
		Connection con = Database.getConnection();

		String query = "UPDATE BOOKS \n" +
				"SET Is_active=FALSE \n" +
				"WHERE MemberID=" + memberID + ";";

		PreparedStatement ps2 = null;
		try {
			//create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ps.executeUpdate();

			ps.close();
			con.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}

	}

	// Returns true if the memberID is valid (member exists in the database)
	public boolean isValid() {
		// Needs to be implemented
		return false;
	}
}
