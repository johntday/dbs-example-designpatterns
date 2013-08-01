package com.dbs.training.designpatterns.iterator;

/**
 * Library Iterator Interface.
 * 
 * @author John T Day (https://github.com/johntday)
 */
public interface LibraryIterator {
	public Book nextBook();

	public boolean isLastBook();

	public Book currentBook();
}
