public class Associate extends Member {
	public void createMember(String firstName, String lastName, String middleInitial, 
                              String memberID, String phoneNumber, String userName, String password;){
           this.firstName = firstName;
           this.lastName = lastName;
           this.middleInitial = middleInitial;
           this.memberID = memberID;
           this.phoneNumber = phoneNumber;
           this.userName = userName;
           this.password = password;
            String query = "INSERT INTO members VALUES(";
				query += firstName;
            query += lastName;
            query += middleInitial;
            query += memberID;
            query += phoneNumber;
            query += userName;
            query += password;
				query += ");";
				System.out.println(query);
				stmt.execute(query);
	}

	}
}
