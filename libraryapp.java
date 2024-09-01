import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    public void testAddBook() {
        Library library = new Library();
        Book book = new Book("1234567890", "Test Book", "John Doe");

        library.addBook(book);

        assertEquals(book, library.findBookByIsbn("1234567890"));
    }

    @Test
    public void testRemoveBook() {
        Library library = new Library();
        Book book = new Book("1234567890", "Test Book", "John Doe");
        library.addBook(book);

        library.removeBook("1234567890");

        assertNull(library.findBookByIsbn("1234567890"));
    }

    @Test
    public void testGetAllBooks() {
        Library library = new Library();
        Book book1 = new Book("1234567890", "Test Book 1", "Author 1");
        Book book2 = new Book("9876543210", "Test Book 2", "Author 2");

        library.addBook(book1);
        library.addBook(book2);

        List<Book> allBooks = library.getAllBooks();

        assertEquals(2, allBooks.size());
        assertTrue(allBooks.contains(book1));
        assertTrue(allBooks.contains(book2));
    }
}
