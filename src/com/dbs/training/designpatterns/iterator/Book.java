package com.dbs.training.designpatterns.iterator;

/**
 * Book.
 * 
 * @author John T Day (https://github.com/johntday)
 */
public class Book {
	private String	bookName;
	private String	bookType;

	public Book(String bookName, String bookType) {
		this.bookName = bookName;
		this.bookType = bookType;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
}
