package beans;

import java.util.Comparator;

public class IsbnComp implements Comparator<Book> {

	@Override
	public int compare(Book b1, Book b2) {
		return Integer.toString(b1.getIsbn()).compareTo(Integer.toString(b2.getIsbn()));
	}

}