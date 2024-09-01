import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryServiceTest {

    @InjectMocks
    private LibraryService libraryService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testAddBook_shouldAddBookToRepository() {
        String isbn = "1234567890";
        String title = "Test Book";
        String author = "John Doe";
        int publicationYear = 2023;

        Book book = new Book(isbn, title, author, publicationYear, true);

        libraryService.addBook(book);

        Mockito.verify(bookRepository).save(book); // Verify save method is called on BookRepository
    }

    @Test
    public void testBorrowBook_shouldBorrowAvailableBook() {
        String userId = "user123";
        String isbn = "1234567890";

        Book book = new Book(isbn, "Test Book", "John Doe", 2023, true);
        User user = new User(userId, "John Doe", "john.doe@example.com", false);

        // Mock book and user lookups
        Mockito.when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        boolean borrowed = libraryService.borrowBook(userId, isbn);

        assertTrue(borrowed);
        assertFalse(book.isAvailable()); // Verify book availability is updated
        Mockito.verify(bookRepository).save(book); // Verify book is saved after borrowing
    }

    @Test
    public void testBorrowBook_shouldReturnFalseForUnavailableBook() {
        String userId = "user123";
        String isbn = "1234567890";

        Book book = new Book(isbn, "Test Book", "John Doe", 2023, false);
        User user = new User(userId, "John Doe", "john.doe@example.com", false);

        // Mock book and user lookups
        Mockito.when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        boolean borrowed = libraryService.borrowBook(userId, isbn);

        assertFalse(borrowed);
    }

    @Test
    public void testReturnBook_shouldReturnBookAndMarkAvailable() {
        String userId = "user123";
        String isbn = "1234567890";

        Book book = new Book(isbn, "Test Book", "John Doe", 2023, false);
        User user = new User(userId, "John Doe", "john.doe@example.com", false);

        // Mock book and user lookups
        Mockito.when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        libraryService.returnBook(userId, isbn);

        assertTrue(book.isAvailable()); // Verify book availability is updated
        Mockito.verify(bookRepository).save(book); // Verify book is saved after returning
    }

    @Test
    public void testFindBookByIsbn_shouldFindExistingBook() {
        String isbn = "1234567890";
        String title = "Test Book";
        String author = "John Doe";
        int publicationYear = 2023;

        Book book = new Book(isbn, title, author, publicationYear, true);

        // Mock book retrieval
        Mockito.when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));

        Book foundBook = libraryService.findBookByIsbn(isbn);

        assertNotNull(foundBook);
        assertEquals(book, foundBook);
    }

    @Test
    public void testFindBookByIsbn_
