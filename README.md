<h2>Introduction</h2>
The intent of an iterator is to provide a way to traverse through a collection in a sequential way without exposing the underlying collection. The Java Iterator is a nice implementation of iterator design pattern, and allows us to traverse through a collection in a sequential way. The collection has a method to create an iterator instance, which we use to traverse.

In the Iterator design pattern, an iterator is an interface which provides a simple interaction mechanism. More importantly the user of the iterator interface does not need to know about the working mechanisms or complexity of the related collection. This encapsulates the the logic in one spot.

<h2>Iterator can do more than traversal</h2>

Iterators are not limited to traversal alone. It is entirely left to the purpose and implementation. There can be functional logic involved in an iterator. For example, we can have a <code>FilteringIterator</code>, which can filter out certain required values for traversal. 

<h2>Iterator Design Pattern</h2>

A factory method available in the collection will return the instance of the iterator.

 
<h2>Polymorphic Iterator</h2>

If an iterator has common contract across multiple collections, the client code which interacts with the iterator need not be changed for different collections. This is called polymorphic iterator.

<h2>External vs Internal Iterator</h2>

When the client controls the iteration sequence and index position then it is called an <b>external iterator</b>. Otherwise if the iterator controls the traversal then it is called an <b>internal iterator</b>. For an external iterator, the client should explicitly invoke the method to move the pointer to next element. For internal iterator, when an element is accessed the pointer will be automatically moved to next index by the iterator. In general, internal iterators are easier to use than the external iterators.

<h2>Robust Iterator</h2>

This is a bit more complex. Imagine an iterator instance is instantiated and transversed.  What would happen if the contents of the underlying collection is modified - for example, a new element is inserted or an existing element is deleted?  It's behavior will be unpredictable.  A simple implementation of an iterator will create a copy of the collection when it is instantiated, and use that for traversal. That way, during the iteration process, even if the original collection is modified the "iterator copy" would be unchanged and the behavior will be predictable.

The behavior of a <b>robust iterator</b> is consistent, even if the associated collection is modified. It works with the original collection without making a copy. This is usually implemented by using an event listener that notifies the iterator of changes to the collection.

<h2>Null Iterator</h2>

Imagine we are traversing a tree data structure. During the tree traversal, when we ask for the nextElement, we will get a concrete iterator which will help us to traverse through the tree. If we ask for the nextElement in the leaf node, we will get a <b>null iterator</b> returned by the collection signifying the leaf node. This behavior will allow us to design the tree traversal pattern.

<h2>Key Points for Iterator Design Pattern</h2>
<ul>
	<li>Iterators can have complex traversal algorithms and provide a variety of transversal mechanisms.</li>
	<li>All at the same moment in time, many iterator instances may be working and pointing to different indexes.  This deals with the multi-thread properties of an iterator.</li>
</ul>

<h2>Java Code Example of Iterator Design Pattern</h2>

Source Code.

<h3>Book.java</h3>
<pre>
public class Book {
  private String bookName;
  private String bookType;
 
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
</pre>


<h3>ILibrary.java</h3>
<pre>
import java.util.List;
 
public interface ILibrary {
 
  public List<Book> getBooks();
 
  public void addBook(Book book);
 
  public void removeBook(Book book);
 
  public LibraryIterator createIterator(String iteratorType);
}
</pre>


<h3>LibraryImpl.java</h3>
<pre>
import java.util.ArrayList;
import java.util.List;
 
public class LibraryImpl implements ILibrary {
 
  List<Book> bookList;
 
  public LibraryImpl() {
    bookList = new ArrayList<Book>();
  }
 
  @Override
  public List<Book> getBooks() {
 
    return bookList;
  }
 
  @Override
  public void addBook(Book book) {
    bookList.add(book);
  }
 
  @Override
  public void removeBook(Book book) {
    bookList.remove(book);
  }
 
  @Override
  public LibraryIterator createIterator(String iteratorType) {
    if ("Fiction".equals(iteratorType)) {
      return new FictionIterator(bookList);
    } else if ("NonFiction".equals(iteratorType)) {
      return new NonFictionIterator(bookList);
    } else {
      throw new RuntimeException("Unknown iteratorType=" + iteratorType);
    }
  }
}
</pre>


<h3>LibraryIterator.java</h3>
<pre>
public interface LibraryIterator {
  public Book nextBook();
 
  public boolean isLastBook();
 
  public Book currentBook();
}
</pre>


<h3>FictionIterator.java</h3>
<pre>
import java.util.List;
 
public class FictionIterator implements LibraryIterator {
 
  public List<Book> bookList;
  private int position;
 
  public FictionIterator(List<Book> bookList) {
    this.bookList = bookList;
  }
 
  @Override
  public Book nextBook() {
    Book book = null;
    for (; position &lt; bookList.size(); position++) {
      if ("Fiction".equals((bookList.get(position)).getBookType())) {
        book = bookList.get(position);
        position++;
        break;
      }
    }
    return book;
  }
 
  @Override
  public boolean isLastBook() {
    for (int i = position; i &lt; bookList.size(); i++) {
      if ("Fiction".equals((bookList.get(i)).getBookType())) {
        return false;
      }
    }
    return true;
  }
 
  @Override
  public Book currentBook() {
    if (position &lt; bookList.size()) {
      return bookList.get(position);
    }
    return null;
  }
 
}
</pre>


<h3>NonFictionIterator.java</h3>
<pre>
import java.util.List;
 
public class NonFictionIterator implements LibraryIterator {
 
  List<Book> bookList;
  private int position;
 
  public NonFictionIterator(List<Book> bookList) {
    this.bookList = bookList;
  }
 
  @Override
  public Book nextBook() {
    Book book = null;
    for (; position &lt; bookList.size(); position++) {
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
    for (int i = position; i &lt; bookList.size(); i++) {
      if ("NonFiction".equals((bookList.get(i)).getBookType())) {
        return false;
      }
    }
    return true;
  }
 
  @Override
  public Book currentBook() {
    if (position &lt; bookList.size()) {
      return bookList.get(position);
    }
    return null;
  }
 
}
</pre>


<h3>IteratorSample.java</h3>
<pre>
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
      System.out.println("Fiction Book: "
          + fictionIterator.nextBook().getBookName());
    }
 
    LibraryIterator nonfictionIterator = library.createIterator("NonFiction");
    while (!nonfictionIterator.isLastBook()) {
      System.out.println("NonFiction Book: "
          + nonfictionIterator.nextBook().getBookName());
    }
  }
}
</pre>


<h3>Output</h3>
<pre>
Fiction Book: Moby Dick 
Fiction Book: A Tale of Two Cities 
Fiction Book: The Theory of Everything 
NonFiction Book: A Brief History of Time 
NonFiction Book: The Large Scale Structure of Space-Time
</pre>
