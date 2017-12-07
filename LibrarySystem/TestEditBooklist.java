public class TestEditBooklist {
	
	public static void main(String[] args) {
	
		Manager man = new Manager("atcroft");

		//1 Test adding books
//		man.addBook("000000009", "Micro", "2012",
//				new String[]{"Michael Crichton","Richard Preston"},
//				new String[]{"Fiction","Adventure","Thriller"}, 2);
				// Note: two copies of added book ^^^

		//2 Test Adding copy of a book
//		man.addCopy("000000009");

		//3 Test Editing a book
//		man.editBook("000000009", "MicroManaging", "2014",
//				new String[]{"Richard Changed"},
//				new String[]{"Fiction","Adventure","Thriller", "Action"});
				// Note: Authors, Keywords, Title, and Year changed
//
		//4 Test deleting a book
//		man.deleteBook(new Book("000000009"));
	
	}
}
