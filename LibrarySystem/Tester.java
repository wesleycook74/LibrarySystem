import java.util.ArrayList;
public class Tester {

	public static void main(String[] args) {
//		Member m1 = new Member("atcroft");
//		Copy book2 = new Copy(2);
//		m1.checkOut(book2);

//		// Does not have permission to create associate as an associate
//		Member m1 = new Manager("wicook");
//		((Manager)m1).createAssociate("Testman", null, "Testerson", "000 Null Drive, Valdosta, GA",
//				"0123987654", "testy", "tests");

		Manager man = new Manager("atcroft");
//		man.addCopy("0060853980");
//		man.addBook("0000000012", "Micro", "2012",
//				new String[]{"Michael Crichton","Richard Preston"},
//				new String[]{"Fiction","Adventure","Thriller"}, 4);
//		man.deleteBook(new Book("0060853980"));
//
		Member chloe = new Member("cakimble");
		Copy book1 = new Copy(1);
		book1.reportLost();
		
		
		man.editBook("0000000012", "MicroManaging", "2014",
				new String[]{"Chloe Crichton","Richard Peterson"},
				new String[]{"Fiction","Adventure","Thriller", "Stupid"});
		
		
		chloe.payFines(2.35);
		man.assessFines();
		
		
		
	}
}
