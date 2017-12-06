import java.util.ArrayList;
public class Tester {

	public static void main(String[] args) {
		ArrayList<Book> books = Search.searchBooksByTitle("");
		for(Book book : books) {
			System.out.println(book);
		}
//		Manager manager = new Manager(1);
//		manager.createManager("Arron", "T", "Croft", "111 Test Drive, Valdosta, GA 31602",
//				"2295551234", "atcroft", "mypassword");
//		manager.createAssociate("Wesley", "I", "Cook", "1313 Test Drive, Valdosta, GA 31602",
//				"2295554321", "wicook", "wesleypassword");
//		manager.createMember("Chloe", "A", "Kimble", "1234 Test Drive, Nashville, GA 31639",
//				"2295550000", "cakimble", "testpass");
//		Associate associate = new Associate(4);
//		associate.createMember("Dustin", null, "Geoghagan", "000 Null Drive, Valdosta, GA",
//				"1234567890", "dgeoghagan", "passpass");
////
//		Member m1 = new Member("atcroft");
//		Copy book2 = new Copy(2);
//		m1.checkOut(book2);

//		// Does not have permission to create associate as an associate
//		Member m1 = new Manager("wicook");
//		((Manager)m1).createAssociate("Testman", null, "Testerson", "000 Null Drive, Valdosta, GA",
//				"0123987654", "testy", "tests");

//		Manager man = new Manager("atcroft");
//		man.addCopy("0060853980");
//		man.addBook("9780060873172", "Micro", "2012",
//				new String[]{"Michael Crichton","Richard Preston"},
//				new String[]{"Fiction","Adventure","Thriller"}, 4);
//		man.deleteBook(new Book("0060853980"));

		Member chloe = new Member("cakimble");
		Copy book3 = new Copy(3);
		chloe.checkOut(book3);
	}
}
