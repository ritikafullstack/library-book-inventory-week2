package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Handles the in-memory book collection and CRUD operations. */
public class LibraryManager {
    private final List<Book> books = new ArrayList<>();

    /** Adds a book, rejecting duplicate ISBNs. */
    public void addBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Book cannot be null.");
        if (findBookByIsbn(book.getIsbn()) != null) {
            throw new IllegalArgumentException("A book with this ISBN already exists.");
        }
        books.add(book);
    }

    /** Returns a read-only view of the current inventory. */
    public List<Book> getAllBooks() {
        return Collections.unmodifiableList(books);
    }

    /** Finds a book by ISBN, or returns null if it does not exist. */
    public Book findBookByIsbn(String isbn) {
        if (isbn == null) return null;
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn.trim())) return book;
        }
        return null;
    }

    /** Updates all fields of a book identified by its current ISBN. */
    public void updateBook(String currentIsbn, String title, String author,
                           String newIsbn, int year) {
        Book existing = findBookByIsbn(currentIsbn);
        if (existing == null) throw new IllegalArgumentException("No book found with that ISBN.");

        Book isbnOwner = findBookByIsbn(newIsbn);
        if (isbnOwner != null && isbnOwner != existing) {
            throw new IllegalArgumentException("Another book already uses this ISBN.");
        }

        // Validate all new values before mutating the existing object.
        Book validated = new Book(title, author, newIsbn, year);
        existing.setTitle(validated.getTitle());
        existing.setAuthor(validated.getAuthor());
        existing.setIsbn(validated.getIsbn());
        existing.setPublicationYear(validated.getPublicationYear());
    }

    /** Removes a book by ISBN; returns false when no match is found. */
    public boolean deleteBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        return book != null && books.remove(book);
    }
}
