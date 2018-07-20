public class TestCheckOut {
	public static void main(String[] args) {
		Member ck = new Member("cakimble");
		Member wc = new Member("wicook");
		Member ac = new Member("atcroft");

		//1 Test Checkout
//		ck.checkOut(new Copy(2));

		//2 Test Renew
//		ck.renewBook(new Copy(2));

		//3 Test Check out on already checked out book
//		ck.checkOut(new Copy(12));
//		wc.checkOut(new Copy(12));

		//4 Test Place Hold and renew on book that has holds
//		wc.placeHold(new Book("9781451697216"));
//		ck.renewBook(new Copy(12));

		//5 Test returning a book
//		ck.returnBook(new Copy(12));

		//6 Test checkout with on hold
//		ac.checkOut(new Copy(12));
//		wc.checkOut(new Copy(12));

		//7
		wc.suspendAccount();
		wc.checkOut(new Copy(3));

	}
}