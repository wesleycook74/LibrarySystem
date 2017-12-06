public class TestEditBooklist {
	
	public static void main(String[] args) {
	
		Manager man = new Manager("atcroft");
		
//		man.addBook("000000009", "Micro", "2012",
//				new String[]{"Michael Crichton","Richard Preston"},
//				new String[]{"Fiction","Adventure","Thriller"}, 2);
//		
//		man.addCopy("000000009");

//		man.editBook("000000009", "MicroManaging", "2014",
//				new String[]{"Richard Peterson"},
//				new String[]{"Fiction","Adventure","Thriller", "Action"});
//		
		
		man.deleteBook(new Book("000000009"));
	
	}
}
