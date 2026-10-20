# Library Book Inventory System — Week 2

A menu-driven Java command-line application for managing a library's book inventory. It uses an in-memory `ArrayList`, so no database or external library is required.

## Features

- Add books (Create)
- List all books (Read)
- Update book details using the current ISBN (Update)
- Delete books using ISBN (Delete)
- Prevent duplicate ISBN values
- Validate required text fields and publication years
- Handle invalid numeric input and invalid menu choices
- Commented code with separate responsibilities for the book entity, CRUD logic, and command-line interface

## Requirements

- JDK 8 or later
- A terminal or command prompt

Check your Java installation:

```bash
java -version
javac -version
```

## Project Structure

```text
Library-Book-Inventory-Week2/
├── README.md
└── src/
    └── library/
        ├── Book.java
        ├── LibraryManager.java
        └── Main.java
```

## Compile and Run

Open a terminal in the project folder.

Compile the Java source files:

```bash
javac -d out src/library/Book.java src/library/LibraryManager.java src/library/Main.java
```

Run the application:

```bash
java -cp out library.Main
```

## Menu Options

1. Add a book
2. List all books
3. Update a book
4. Delete a book
5. Exit

Follow the prompts to enter a title, author, ISBN, and publication year.

## Code Overview

- `Book.java`: Defines the book entity and validates its properties.
- `LibraryManager.java`: Stores books in an `ArrayList` and implements the CRUD operations.
- `Main.java`: Displays the menu, reads user input, and presents messages.

## Data Storage Limitation

Books are stored in memory only. The inventory is cleared when the program exits. Saving to a file or database could be implemented as a future enhancement.

## Week 2 Deliverable

This project demonstrates Java collections, object-oriented programming, CRUD operations, input validation, exception handling, and basic documentation for a command-line application.
