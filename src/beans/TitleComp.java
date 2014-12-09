package beans;

import java.util.Comparator;

public class TitleComp implements Comparator<Book> {

	@Override
	public int compare(Book b1, Book b2) {
		return b1.getTitre().compareTo(b2.getTitre());
	}

}
