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

	public Member(int memID) {

		this.memberID = memID;
		checkedOut = new ArrayList<Book>();
		getCheckedOut();

		Connection con = Database.getConnection();

		String query = "SELECT MemberID, Fname, Lname, Minit, Address, PhoneNumber, Username, Password, Fines, Is_active\n"
				+ "FROM MEMBERS \n" + "WHERE MEMBERS.MemberID =" + memID + ";";

		PreparedStatement ps2 = null;
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

	public Member(String username)
	{
		checkedOut = new ArrayList<Book>();
		getCheckedOut();

		Connection con = Database.getConnection();

		String query = "SELECT MemberID, Fname, Lname, Minit, Address, PhoneNumber, Username, Password, Fines, Is_active\n"
				+ "FROM MEMBERS \n" + "WHERE MEMBERS.Username =" + username + ";";

		PreparedStatement ps2 = null;
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

	public ArrayList<Book> getCheckedOut() {
		checkedOut = new ArrayList<Book>();

		Connection con = Database.getConnection();
		String query = "SELECT ID\n" + "FROM BOOKS\n" + "WHERE MemberID = " + memberID;

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

	public String getAddress() {
		return address;
	}

	public double getFines() {
		return fines;
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
			if (getCheckedOut().size() < 10) {
				checkedOut.add(book);

				try {
					Connection con = Database.getConnection();
					String query = "UPDATE BOOKS\n" + "SET Checked_Out = TRUE, MemberID = ?, Date_Out = NOW(), On_Hold=FALSE\n"
							+ "WHERE ID = ? AND ((Checked_Out = FALSE AND On_Hold = FALSE) OR (MemberID = ? AND On_Hold = TRUE ));";

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
			String query = "UPDATE BOOKS\n" + "SET Checked_Out = FALSE, MemberID = NULL, Date_Out = NULL\n"
					+ "WHERE Checked_Out = TRUE AND ID = ?;";

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
	//to do
	}

	public void placeHold(BookDetail bookDetail) {
		Book book = bookDetail.getAvailableCopy();
		if (book != null) {
			try {
				Connection con = Database.getConnection();
				String query = "UPDATE BOOKS\n" + "SET On_Hold = TRUE, MemberID = ?\n"
						+ "WHERE Checked_Out = FALSE AND ID = ?;";

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

	public void reportLost(Book book) {
	//to do
	}

	public void suspendAccount() {
		String update = "UPDATE MEMBERS \n" + "SET Is_active=FALSE \n" + "WHERE MemberID=" + memberID + ";";
		Database.runUpdate(update);
	}

	public void activateAccount() {
		String update = "UPDATE MEMBERS \n" + "SET Is_active=TRUE \n" + "WHERE MemberID=" + memberID + ";";
		Database.runUpdate(update);
	}

	// Returns true if the memberID is valid
	public boolean isValid() {
		// Needs to be finished
		return true;
	}

	// returns true if the member account is valid and is not suspended
	public boolean isActive() {
		if (isValid()) {
			String query = "SELECT Is_active\n" + "FROM MEMBERS M\n" + "WHERE M.MemberID=" + this.memberID;
			Connection con = Database.getConnection();
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return rs.getBoolean("Is_active");
				}
				rs.close();
				ps.close();
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
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
