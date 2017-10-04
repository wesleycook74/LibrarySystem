import java.sql.SQLException;

public class Manager extends Associate {
	
	public Manager(String firstName, String lastName, String middleInitial, String address, String memberID,
			String phoneNumber, String userName, String password) throws SQLException {
		super(firstName, lastName, middleInitial, address, phoneNumber, userName, password);
	}

	public void createAssociate (String firstName, String lastName, String middleInitial, String address, String memberID,
			String phoneNumber, String userName, String password) throws SQLException{
		Associate associate = new Associate(firstName, lastName, middleInitial, address,
			phoneNumber, userName, password);
	}

	public void editMember(){

	}

	public void deleteMember(){

	}

	public void suspendAccount(){
		
	}

	public void activateAccount(){

	}

	public void assessFines(){

	}

	public void addBooks(String isbn) {

	}

	public void addBooks(String title, String isbn, String year, String[] authors, String[] keywords, int count){
      
	}


	// Removes a physical copy of the book from inventory
	public void removeBooks(Book book){

	}

	// Removes book detail and all corresponding books from library database
	public void removeAllBooks(BookDetail bookDetail) {

	}

	public void editBooks(String isbn){

	}
}
