package library;

/** Represents a book in the library inventory. */
public class Book {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;

    public Book(String title, String author, String isbn, int publicationYear) {
        setTitle(title);
        setAuthor(author);
        setIsbn(isbn);
        setPublicationYear(publicationYear);
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getPublicationYear() { return publicationYear; }

    public void setTitle(String title) { this.title = requireText(title, "Title"); }
    public void setAuthor(String author) { this.author = requireText(author, "Author"); }
    public void setIsbn(String isbn) { this.isbn = requireText(isbn, "ISBN"); }

    public void setPublicationYear(int year) {
        int currentYear = java.time.Year.now().getValue();
        if (year < 1450 || year > currentYear) {
            throw new IllegalArgumentException("Publication year must be between 1450 and " + currentYear + ".");
        }
        publicationYear = year;
    }

    private String requireText(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " cannot be empty.");
        }
        return value.trim();
    }

    @Override
    public String toString() {
        return String.format("ISBN: %s | Title: %s | Author: %s | Year: %d",
                isbn, title, author, publicationYear);
    }
}
