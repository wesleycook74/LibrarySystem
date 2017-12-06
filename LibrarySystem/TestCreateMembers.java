public class TestCreateMembers {
	public static void main(String[] args) {

		Manager manager = new Manager(1);
		manager.createManager("Arron", "T", "Croft", "111 Test Drive, Valdosta, GA 31602",
				"2295551234", "atcroft", "mypassword");
		manager.createAssociate("Wesley", "I", "Cook", "1313 Test Drive, Valdosta, GA 31602",
				"2295554321", "wicook", "wesleypassword");
		manager.createMember("Chloe", "A", "Kimble", "1234 Test Drive, Nashville, GA 31639",
				"2295550000", "cakimble", "testpass");
		Associate associate = new Associate("wicook");
		associate.createMember("Dustin", null, "Geoghagan", "000 Null Drive, Valdosta, GA",
				"1234567890", "dgeoghagan", "passpass");
	}
}
