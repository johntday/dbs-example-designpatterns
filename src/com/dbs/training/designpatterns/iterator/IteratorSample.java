package com.dbs.training.designpatterns.iterator;

/**
 * Test of Iterator design patter.
 * 
 * @author John T Day (https://github.com/johntday)
 */
public class IteratorSample {
	public static void main(String args[]) {
		LibraryImpl library = new LibraryImpl();
		library.addBook(new Book("Moby Dick", "Fiction"));
		library.addBook(new Book("A Tale of Two Cities", "Fiction"));
		library.addBook(new Book("A Brief History of Time", "NonFiction"));
		library.addBook(new Book("The Theory of Everything", "Fiction"));
		library.addBook(new Book("The Large Scale Structure of Space-Time", "NonFiction"));

		LibraryIterator fictionIterator = library.createIterator("Fiction");
		while (!fictionIterator.isLastBook()) {
			System.out.println("Fiction Book: " + fictionIterator.nextBook().getBookName());
		}

		LibraryIterator nonfictionIterator = library.createIterator("NonFiction");
		while (!nonfictionIterator.isLastBook()) {
			System.out.println("NonFiction Book: " + nonfictionIterator.nextBook().getBookName());
		}
	}
}
