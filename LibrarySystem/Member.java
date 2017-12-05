import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Member {
	private String firstName, lastName, middleInitial;
	private int memberID;
	private String phoneNumber;
	private String username, password;
	private ArrayList<Copy> checkedOut;
	private String address;
	private double fines;

	public Member(int memID) {
		getCheckedOut();
		Connection con = Database.getConnection();
		String query = "SELECT MemberID, Fname, Lname, Minit, Address, PhoneNumber, Username, Password, Fines, IsActive\n"
				+ "FROM MEMBERS \n" + "WHERE MEMBERS.MemberID = " + memID + ";";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				this.memberID = rs.getInt("MemberID");
				this.firstName = rs.getString("Fname");
				this.lastName = rs.getString("Lname");
				this.middleInitial = rs.getString("Minit");
				this.address = rs.getString("Address");
				this.phoneNumber = rs.getString("PhoneNumber");
				this.username = rs.getString("Username");
				this.password = rs.getString("Password");
				this.fines = rs.getDouble("Fines");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public Member(String username)
	{
		getCheckedOut();
		Connection con = Database.getConnection();
		String query = "SELECT MemberID, Fname, Lname, Minit, Address, PhoneNumber, Username, Password, Fines, IsActive\n"
				+ "FROM MEMBERS\n" + "WHERE MEMBERS.Username = '" + username + "';";
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				this.memberID = rs.getInt("MemberID");
				this.firstName = rs.getString("Fname");
				this.lastName = rs.getString("Lname");
				this.middleInitial = rs.getString("Minit");
				this.address = rs.getString("Address");
				this.phoneNumber = rs.getString("PhoneNumber");
				this.username = rs.getString("Username");
				this.password = rs.getString("Password");
				this.fines = rs.getDouble("Fines");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException se) {
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
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}

	public double getFines() {
		return fines;
	}

	public ArrayList<Copy> getCheckedOut() {
		checkedOut = new ArrayList<Copy>();
		Connection con = Database.getConnection();
		String query = "SELECT ID\n" + "FROM BOOKS\n" + "WHERE CheckedOutMemberID = " + memberID;
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				checkedOut.add(new Copy(rs.getInt("ID")));
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return checkedOut;
	}

	public void payFines(double amountpaid) {
		fines -= amountpaid;
		Connection con = Database.getConnection();

		String query = "UPDATE MEMBERS\n" + "SET Fines = Fines - " + amountpaid + "\nWHERE MEMBERS.MemberID =" + memberID
				+ ";";
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ps.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public void checkOut(Copy copy) {
		// check to see if member account is not suspended
		if (isActive()) {
			// check to see if books checked out is less than 10
			if (getCheckedOut().size() < 10) {
				checkedOut.add(copy);
				try {
					Connection con = Database.getConnection();

					String query = "UPDATE BOOKS\n" + "SET CheckedOut = TRUE, CheckedOutMemberID = ?, DateOut = NOW(), OnHold=FALSE, OnHoldMemberID = NULL\n"
							+ "WHERE ID = ? AND CheckedOut = FALSE AND ((OnHold = FALSE) OR (OnHoldMemberID = ? AND OnHold = TRUE ));";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, this.memberID);
					ps.setInt(2, copy.getId());
					ps.setInt(3, this.memberID);
					ps.executeUpdate();
					ps.close();
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	public void returnBook(Copy copy) {
		try {
			checkedOut.remove(copy);
			Connection con = Database.getConnection();
			String query = "UPDATE BOOKS\n" + "SET CheckedOut = FALSE, CheckedOutMemberID = NULL, DateOut = NULL, RenewCount = 0\n"
					+ "WHERE CheckedOut = TRUE AND ID = ?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, copy.getId());
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public void renewBook(Copy copy) {
		// check to see if member account is not suspended
		if (isActive()) {
			try {
				Connection con = Database.getConnection();
				String query = "UPDATE BOOKS\n" + "SET DateOut = NOW(), RenewCount = RenewCount + 1\n"
						+ "WHERE ID = ? AND CheckedOutMemberID = ? AND CheckedOut = TRUE AND RenewCount < 2 AND\n"
						+ "OnHold = FALSE;";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, copy.getId());
				ps.setInt(2, this.memberID);
				ps.executeUpdate();

				ps.close();
				con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void placeHold(Book book) {
		Copy copy = book.getAvailableCopy();
		if (copy == null)
			copy = book.getCheckedOutCopy();
		if (copy != null) {
			try {
				Connection con = Database.getConnection();
				String query = "UPDATE BOOKS\n" + "SET OnHold = TRUE, OnHoldMemberID = ?\n"
						+ "WHERE ID = ?;";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, this.memberID);
				ps.setInt(2, copy.getId());
				ps.executeUpdate();

				ps.close();
				con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void releaseHold(Copy copy) {
		if (copy != null) {
			try {
				Connection con = Database.getConnection();
				String query = "UPDATE BOOKS\n" + "SET OnHold = FALSE, OnHoldMemberID = NULL\n"
						+ "WHERE ID = ?;";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, copy.getId());
				ps.executeUpdate();
				ps.close();
				con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void reportLost(Copy copy) {
		//to do
	}

	public void suspendAccount() {
		String update = "UPDATE MEMBERS \n" + "SET IsActive=FALSE \n" + "WHERE MemberID=" + memberID + ";";
		Database.executeStatement(update);
	}

	public void activateAccount() {
		String update = "UPDATE MEMBERS \n" + "SET IsActive=TRUE \n" + "WHERE MemberID=" + memberID + ";";
		Database.executeStatement(update);
	}

	// returns true if the member account is valid and is not suspended
	public boolean isActive() {
		String query = "SELECT IsActive\n" + "FROM MEMBERS M\n" + "WHERE M.MemberID=" + this.memberID;
		Connection con = Database.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getBoolean("IsActive");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String toString() {
		return "Member [firstName=" + firstName + ", middleInitial=" + middleInitial + ", lastName=" + lastName
				+ ", memberID=" + memberID + ", phoneNumber=" + phoneNumber + ", address=" + address + ", userName="
				+ username + ", checkedOut=" + checkedOut + ", fines=" + fines + "]";
	}
}
