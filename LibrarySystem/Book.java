// Represents a physical copy of a book in inventory; linked to book detail by ISBN
// Database can store multiple books with the same ISBN
public class Book {
	int id;
	String isbn;

	public Book(int id) {
      this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}
}
