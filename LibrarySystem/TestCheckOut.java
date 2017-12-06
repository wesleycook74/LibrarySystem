public class TestCheckOut {
	public static void main(String[] args) {
		Member ck = new Member("cakimble");
		ck.checkOut(new Copy(2));
	}
}