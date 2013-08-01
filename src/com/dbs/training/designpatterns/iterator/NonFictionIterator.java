package com.dbs.training.designpatterns.iterator;

import java.util.List;

/**
 * NonFiction Iterator.
 * 
 * @author John T Day (https://github.com/johntday)
 */
public class NonFictionIterator implements LibraryIterator {

	List<Book>	bookList;
	private int	position;

	public NonFictionIterator(List<Book> bookList) {
		this.bookList = bookList;
	}

	@Override
	public Book nextBook() {
		Book book = null;
		for (; position < bookList.size(); position++) {
			if ("NonFiction".equals((bookList.get(position)).getBookType())) {
				book = bookList.get(position);
				position++;
				break;
			}
		}
		return book;
	}

	@Override
	public boolean isLastBook() {
		for (int i = position; i < bookList.size(); i++) {
			if ("NonFiction".equals((bookList.get(i)).getBookType())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Book currentBook() {
		if (position < bookList.size()) {
			return bookList.get(position);
		}
		return null;
	}

}
