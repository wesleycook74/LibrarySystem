public class Member {
	 String firstName, lastName, middleInitial;
	 String memberID;
	 String phoneNumber;
	 String userName, password;
	 Book[] checkedOut;
	 String address;

	public Member(String firstName, String lastName, String middleInitial, String address,
            String memberID, String phoneNumber, String userName, String password){
		this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.address = address;
        this.memberID = memberID;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
         
	}
	public void checkOut (Book[] books){
      
	}

	public void returnBooks (Book[] books){

	}

	public void renewBooks (Book[] books){

	}

	public void placeHold (BookDetail[] bookDetails){

	}

	public void reportLost (Book[] book){

	}
}
