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
	 ArrayList<Book> checkedOut = new ArrayList<Book>();
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
		
		
		String getmemid = "SELECT memberID\n" +
			       "FROM Members BD\n" +
			       "WHERE userName = '" + userName + "'";

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

	public ArrayList<Book> getCheckedOut() {
		return checkedOut;
	}

	public String getAddress() {
		return address;
	}

	public double getFines() {
		return fines;
	}

	
	@Override
	public String toString() {
		return "Member [firstName=" + firstName +  ", middleInitial=" + middleInitial + ", lastName=" + lastName 
				+ ", memberID=" + memberID + ", phoneNumber=" + phoneNumber + ", address=" + address +", userName=" + userName + ", checkedOut="
				+ checkedOut  + ", fines=" + fines + "]";
	}

	public void checkOut (Book book){
		if(checkedOut.size() < 10) {
			checkedOut.add(book);
			
			try {
				Connection con = Database.getConnection();
				String query = "UPDATE BOOKS\n" +
						"SET Checked_Out = '1'\n" +
						"WHERE Checked_Out = '0' AND ID = ?;";
				
				//create the prepared statement
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1,book.getId());
				ps.executeUpdate();
	
				ps.close();
				con.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void returnBooks (Book book){
		
	}

	public void renewBooks (Book book){

	}

	public void placeHold (BookDetail bookDetail){

	}

	public void reportLost (Book book){

	}

	public void suspendAccount () {

	}

	public void suspendAccount () {

	}
}
