import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Member {
	private String firstName, lastName, middleInitial;
	private int memberID;
	private String phoneNumber;
	private String userName, password;
	private ArrayList<Book> checkedOut;
	private String address;
	private double fines;

	public Member(String firstName, String middleInitial, String lastName, String address, String phoneNumber,
			String userName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleInitial = middleInitial;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.password = password;
		checkedOut = new ArrayList<Book>();
		getCheckedOut();
		Connection con = Database.getConnection();
		String query = "insert into MEMBERS (Fname, Minit, Lname, Address, PhoneNumber, Username, Password, IsActive)"
				+ " values (?, ?, ?, ?, ?, ? ,?, ?)";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, firstName);
			ps.setString(2, middleInitial);
			ps.setString(3, lastName);
			ps.setString(4, address);
			ps.setString(5, phoneNumber);
			ps.setString(6, userName);
			ps.setString(7, password);
			ps.setBoolean(8, true);
			ps.execute();
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
		checkedOut = new ArrayList<Book>();
		getCheckedOut();
		Connection con = Database.getConnection();
		String query = "SELECT MemberID, Fname, Lname, Minit, Address, PhoneNumber, Username, Password, Fines, IsActive\n"
				+ "FROM MEMBERS \n" + "WHERE MEMBERS.MemberID =" + memID + ";";
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
				this.userName = rs.getString("Username");
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
		return userName;
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

	public ArrayList<Book> getCheckedOut() {
		checkedOut = new ArrayList<Book>();
		Connection con = Database.getConnection();
		String query = "SELECT ID\n" + "FROM BOOKS\n" + "WHERE CheckedOutMemberID = " + memberID;
		try {
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				checkedOut.add(new Book(rs.getInt("ID")));
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

		String query = "UPDATE MEMBERS\n" + "SET Fines=Fines - " + amountpaid + "\nWHERE MEMBERS.MemberID =" + memberID
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

	public void checkOut(Book book) {
		// check to see if member account is not suspended
		if (isActive()) {
			// check to see if books checked out is less than 10
			if (getCheckedOut().size() < 10) {
				checkedOut.add(book);

				try {
					Connection con = Database.getConnection();

					String query = "UPDATE BOOKS\n" + "SET CheckedOut = TRUE, CheckedOutMemberID = ?, DateOut = NOW(), OnHold=FALSE, OnHoldMemberID = NULL\n"
							+ "WHERE ID = ? AND CheckedOut = FALSE AND ((OnHold = FALSE) OR (OnHoldMemberID = ? AND OnHold = TRUE ));";
					// create the prepared statement
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, this.memberID);
					ps.setInt(2, book.getId());
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

	public void returnBook(Book book) {
		try {
			checkedOut.remove(book);
			Connection con = Database.getConnection();
			String query = "UPDATE BOOKS\n" + "SET CheckedOut = FALSE, CheckedOutMemberID = NULL, DateOut = NULL, RenewCount = 0\n"
					+ "WHERE CheckedOut = TRUE AND ID = ?;";
			// create the prepared statement
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, book.getId());
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public void renewBook(Book book) {
		// check to see if member account is not suspended
		if (isActive()) {
			try {
				Connection con = Database.getConnection();
				String query = "UPDATE BOOKS\n" + "SET DateOut = NOW(), RenewCount = RenewCount + 1\n"
						+ "WHERE ID = ? AND CheckedOutMemberID = ? AND CheckedOut = TRUE AND RenewCount < 2 AND\n"
						+ "OnHold = FALSE;";

				// create the prepared statement
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, book.getId());
				ps.setInt(2, this.memberID);
				ps.executeUpdate();

				ps.close();
				con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void placeHold(BookDetail bookDetail) {
		Book book = bookDetail.getAvailableCopy();
		if (book == null)
			book = bookDetail.getCheckedOutCopy();
		if (book != null) {
			try {
				Connection con = Database.getConnection();
				String query = "UPDATE BOOKS\n" + "SET OnHold = TRUE, OnHoldMemberID = ?\n"
						+ "WHERE ID = ?;";

				// create the prepared statement
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, this.memberID);
				ps.setInt(2, book.getId());
				ps.executeUpdate();

				ps.close();
				con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void releaseHold(Book book) {
		if (book != null) {
			try {
				Connection con = Database.getConnection();
				String query = "UPDATE BOOKS\n" + "SET OnHold = FALSE, OnHoldMemberID = NULL\n"
						+ "WHERE ID = ?;";
				// create the prepared statement
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, book.getId());
				ps.executeUpdate();
				ps.close();
				con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void reportLost(Book book) {
		//to do
	}

	public void suspendAccount() {
		String update = "UPDATE MEMBERS \n" + "SET IsActive=FALSE \n" + "WHERE MemberID=" + memberID + ";";
		Database.runUpdate(update);
	}

	public void activateAccount() {
		String update = "UPDATE MEMBERS \n" + "SET IsActive=TRUE \n" + "WHERE MemberID=" + memberID + ";";
		Database.runUpdate(update);
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
				+ userName + ", checkedOut=" + checkedOut + ", fines=" + fines + "]";
	}
}
