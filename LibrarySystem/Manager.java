import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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


	public void assessFines() {
		if (isManager()) {
			String query =  "UPDATE MEMBERS " +
							"SET Fines = Fines + 0.10 " +
							"WHERE MemberID IN ( " +
							"	SELECT CheckedOutMemberID " +
							"	FROM COPIES " +
							"	WHERE DATEDIFF(CURDATE(), COPIES.DateOut) >= 14 AND IsLost=FALSE " +
							"); ";
			Database.executeStatement(query);

			query = "UPDATE MEMBERS " +
					"SET IsActive = False " +
					"WHERE IsActive = True AND Fines >= 25.00;";
			Database.executeStatement(query);
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

	public void addCopy(String isbn) {
		if (isManager()) {
			String query = "INSERT INTO COPIES\n" +
					"VALUES(DEFAULT,'" + isbn + "', FALSE, FALSE, NULL, NULL, NULL , 0, FALSE);";
			Database.executeStatement(query);
		}
	}

	public void deleteCopy(Copy copy) {
		if (isManager()) {
			String query = "DELETE FROM COPIES\n"
					+ "WHERE COPIES.ID = " + copy.id + ";";
			Database.executeStatement(query);
		}
	}

	public void addBook(String isbn, String title, String year, String[] authors, String[] keywords, int count) {
		if (isManager()) {
			Connection con = Database.getConnection();
			String query = "INSERT INTO BOOKS\n" +
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
					addCopy(isbn);
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void editBook(String isbn, String title, String year, String[] authors, String[] keywords) {
		if (isManager()) {
			Connection con = Database.getConnection();
			String query = "UPDATE BOOKS\n" +
					"SET Title = ?, Year = ?\n" +
					"WHERE BOOKS.ISBN = '" + isbn + "';";
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, title);
				ps.setString(2, year);
				ps.execute();
				ps.close();
				con.close();
				deleteAuthors(new Book(isbn));
				deleteKeywords(new Book(isbn));
				insertAuthors(isbn, authors);
				insertKeywords(isbn, keywords);
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	// Removes books and all corresponding copies from library database
	public void deleteBook(Book book) {
		if (isManager()) {
			ArrayList<Copy> copies = book.getAllCopies();
			for(Copy b : copies) {
				deleteCopy(b);
			}
			deleteAuthors(book);
			deleteKeywords(book);
			String query = "DELETE FROM BOOKS\n"
					+ "WHERE BOOKS.ISBN = '" + book.getIsbn() + "';";
			Database.executeStatement(query);
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

	private void deleteAuthors(Book book) {
		if (isManager()) {
			String query = "DELETE FROM AUTHORS\n"
					+ "WHERE AUTHORS.ISBN = '" + book.getIsbn() + "';";
			Database.executeStatement(query);
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

	private void deleteKeywords(Book book) {
		if (isManager()) {
			String query = "DELETE FROM KEYWORDS\n"
					+ "WHERE KEYWORDS.ISBN = '" + book.getIsbn() + "';";
			Database.executeStatement(query);
		}
	}
}
