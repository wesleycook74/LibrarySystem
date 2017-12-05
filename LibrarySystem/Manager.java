import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager extends Associate {

	public Manager(int id) {
		super(id);
	}

	public Manager(String username) {
		super(username);
	}

	public void createManager(String firstName, String middleInitial, String lastName, String address, String phoneNumber,
								  String userName, String password){
		if (isManager()) {
			Connection con = Database.getConnection();
			String query = "INSERT INTO MEMBERS (Fname, Minit, Lname, Address, PhoneNumber, Username, Password, IsActive, MemberLevel)"
					+ " VALUES (?, ?, ?, ?, ?, ? ,?, ?, ?)";
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
				ps.setBoolean(8, true);//IsActive
				ps.setInt(9, 2);//MemberLevel
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void createAssociate(String firstName, String middleInitial, String lastName, String address,
			String phoneNumber, String userName, String password) {
		if (isManager()) {
			Connection con = Database.getConnection();
			String query = "INSERT INTO MEMBERS (Fname, Minit, Lname, Address, PhoneNumber, Username, Password, IsActive, MemberLevel)"
					+ " VALUES (?, ?, ?, ?, ?, ? ,?, ?, ?)";
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, firstName);
				ps.setString(2, middleInitial);
				ps.setString(3, lastName);
				ps.setString(4, address);
				ps.setString(5, phoneNumber);
				ps.setString(6, userName);
				ps.setString(7, password);
				ps.setBoolean(8, true);//IsActive
				ps.setInt(9, 1);//MemberLevel
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isManager(){
		Connection con = Database.getConnection();
		String query = "SELECT MemberLevel\n"
				+ "FROM MEMBERS\n" + "WHERE MEMBERS.MemberID = " + this.getMemberID() + ";";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("MemberLevel") == 2;
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return false;
	}

	public void editMember(int memberID, String firstName, String middleInitial, String lastName, String address, String phoneNumber,
			  String password) {
		if (isManager()) {
			Connection con = Database.getConnection();
			String query = "UPDATE Members\n" +
					"SET Fname = ?, Minit = ?, Lname = ?, Address = ?, PhoneNumber = ?, Password = ?\n" +
					"WHERE MEMBERS.MemberID = " + memberID + ";";
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, firstName);
				ps.setString(2, middleInitial);
				ps.setString(3, lastName);
				ps.setString(4, address);
				ps.setString(5, phoneNumber);
				ps.setString(6, password);
				ps.execute();
				ps.close();
				con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void deleteMember(int memberID) {
		if (isManager()) {
			Connection con = Database.getConnection();
			String query = "DELETE FROM Members\n"
					+ "WHERE MEMBERS.MemberID = " + memberID + ";";
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.execute();
				ps.close();
				con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void addBookCopy(String isbn) {
		if (isManager()) {
			Connection con = Database.getConnection();
			String query = "INSERT INTO BOOKS\n" +
					"VALUES(DEFAULT,'" + isbn + "', FALSE, FALSE, NULL, NULL, NULL , 0);";
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.execute();
				ps.close();
				con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void addBookDetail(String isbn, String title, String year, String[] authors, String[] keywords, int count) {
		if (isManager()) {
			Connection con = Database.getConnection();
			String query = "INSERT INTO BOOK_DETAILS\n" +
					"VALUES(?, ?, ?);";
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, isbn);
				ps.setString(2, title);
				ps.setString(3, year);
				ps.execute();
				ps.close();
				con.close();
				insertAuthors(isbn, authors);
				insertKeywords(isbn, keywords);
				for (int i = 0; i < count; i++){
					addBookCopy(isbn);
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	private void insertAuthors(String isbn, String[] authors) {
		if (isManager()) {
			try {
				Connection con = Database.getConnection();
				for (int i = 0; i < authors.length; i++) {
					String query = "INSERT INTO AUTHORS\n" + "VALUES(?, ?);";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, isbn);
					ps.setString(2, authors[i]);
					ps.execute();
					ps.close();
				}
				con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	private void insertKeywords(String isbn, String[] keywords) {
		if (isManager()) {
			try {
				Connection con = Database.getConnection();
				for (int i = 0; i < keywords.length; i++) {
					String query = "INSERT INTO KEYWORDS\n" + "VALUES(?, ?);";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, isbn);
					ps.setString(2, keywords[i]);
					ps.execute();
					ps.close();
				}
				con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void editBookDetail(String title, String isbn, String year, String[] authors, String[] keywords) {
		if (isManager()) {

		}
	}

	// Removes a physical copy of the book from inventory
	public void removeBookCopy(Book book) {
		if (isManager()) {

		}
	}

	// Removes book detail and all corresponding books from library database
	public void removeBookDetail(BookDetail bookDetail) {
		if (isManager()) {

		}
	}

	public void assessFines() {
		if (isManager()) {

		}
	}
}
