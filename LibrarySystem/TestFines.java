
public class TestFines {

	public static void main(String[] args) {

		Manager man = new Manager("atcroft");

     	//1 Test accruing fines
		for(int i = 0; i < 10; i++)
			man.assessFines();

		//2 Test Pay fines
//		Member min = new Member(1);
//		min.payFines(0.25);
//
		//3 Stop accruing fines after reported lost
//		Copy book1 = new Copy(1);
//		book1.reportLost();
//		man.assessFines();
	}
}
