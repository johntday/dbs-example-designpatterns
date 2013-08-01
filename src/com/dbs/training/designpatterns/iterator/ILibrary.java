package com.dbs.training.designpatterns.iterator;

import java.util.List;

/**
 * Library Interface.
 * 
 * @author John T Day (https://github.com/johntday)
 */
public interface ILibrary {

	public List<Book> getBooks();

	public void addBook(Book book);

	public void removeBook(Book book);

	public LibraryIterator createIterator(String iteratorType);
}
