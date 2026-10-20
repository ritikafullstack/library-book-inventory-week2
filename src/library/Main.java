package library;

import java.util.List;
import java.util.Scanner;

/** Menu-driven command-line interface for the library inventory. */
public class Main {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final LibraryManager LIBRARY = new LibraryManager();

    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("   LIBRARY BOOK INVENTORY SYSTEM");
        System.out.println("==================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            try {
                switch (choice) {
                    case 1: addBook(); break;
                    case 2: listBooks(); break;
                    case 3: updateBook(); break;
                    case 4: deleteBook(); break;
                    case 5:
                        running = false;
                        System.out.println("Thank you for using the Library System!");
                        break;
                    default:
                        System.out.println("Please choose a number from 1 to 5.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            System.out.println();
        }
        INPUT.close();
    }

    private static void printMenu() {
        System.out.println("1. Add a book");
        System.out.println("2. List all books");
        System.out.println("3. Update a book");
        System.out.println("4. Delete a book");
        System.out.println("5. Exit");
    }

    private static void addBook() {
        System.out.println("--- Add Book ---");
        String title = readRequiredText("Title: ");
        String author = readRequiredText("Author: ");
        String isbn = readRequiredText("ISBN: ");
        int year = readInt("Publication year: ");
        LIBRARY.addBook(new Book(title, author, isbn, year));
        System.out.println("Book added successfully.");
    }

    private static void listBooks() {
        System.out.println("--- Book Inventory ---");
        List<Book> books = LIBRARY.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books have been added yet.");
            return;
        }
        System.out.println("Total books: " + books.size());
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }

    private static void updateBook() {
        System.out.println("--- Update Book ---");
        String currentIsbn = readRequiredText("Enter the current ISBN: ");
        Book existing = LIBRARY.findBookByIsbn(currentIsbn);
        if (existing == null) {
            System.out.println("No book found with that ISBN.");
            return;
        }
        System.out.println("Current details: " + existing);
        String title = readRequiredText("New title: ");
        String author = readRequiredText("New author: ");
        String isbn = readRequiredText("New ISBN: ");
        int year = readInt("New publication year: ");
        LIBRARY.updateBook(currentIsbn, title, author, isbn, year);
        System.out.println("Book updated successfully.");
    }

    private static void deleteBook() {
        System.out.println("--- Delete Book ---");
        String isbn = readRequiredText("Enter the ISBN of the book to delete: ");
        if (LIBRARY.deleteBook(isbn)) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("No book found with that ISBN.");
        }
    }

    /** Keeps asking until the user enters non-empty text. */
    private static String readRequiredText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = INPUT.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("This field cannot be empty. Please try again.");
        }
    }

    /** Reads a whole number and handles non-numeric input safely. */
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = INPUT.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }
}
