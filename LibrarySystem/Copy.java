// Represents a physical copy of a book in inventory; linked to book detail by ISBN
// Database can store multiple books with the same ISBN
public class Copy {
	int id;
	String isbn;

	public Copy(int id) {
		this.id = id;

	}

	public int getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}

	public boolean isAvailable() {
		return true;
	}

	public boolean equals(Object o){
		if(((Copy)o).getId() == (this.id))
			return true;
		else
			return false;
	}
}
