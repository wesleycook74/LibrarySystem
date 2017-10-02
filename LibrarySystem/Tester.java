import java.util.ArrayList;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		ArrayList<BookDetail> bookDetails = Search.searchBooksByTitle("The");
		for(BookDetail bd : bookDetails) {
			System.out.println(bd.getTitle());
		}
	}

}
