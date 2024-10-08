import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    public void testAddBook_shouldAddBookToLibrary() {
        Library library = new Library();
        String isbn = "1234567890";
        String title = "Test Book";
        String author = "John Doe";
        int year = 2023;

        library.addBook(isbn, title, author, year);

        Book book = library.getBook(isbn); // Add a getter method for Book

        assertNotNull(book);
        assertEquals(isbn, book.getIsbn());
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(year, book.getPublicationYear());
        assertTrue(book.isAvailable());
    }
}
