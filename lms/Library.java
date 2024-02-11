package lms;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class Library {
    List<Book> books = new ArrayList<>();
    List<User> users = new ArrayList<>();
    List<Transaction> transactions = new ArrayList<>();

    // BOOKS
    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(int bookId, Book updatedBook) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setGenre(updatedBook.getGenre());
                book.setQuantity(updatedBook.getQuantity());
            }
        }
    }

    public void deleteBook(int bookId) {
        books.removeIf(book -> book.getBookId() == bookId);
    }

    // USERS
    public void addUser(User user) {
        users.add(user);
    }

    public void updateUser(int userId, User updatedUser) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                user.setUserName(updatedUser.getUserName());
                user.setUserEmail((updatedUser.getUserEmail()));
                user.setUserAddress(updatedUser.getUserAddress());
            }
        }
    }

    public void deleteUser(int userId) {
        users.removeIf(user -> user.getUserId() == userId);
    }



    // TRANSACTIONS

    public void borrowBook(int userId, int bookId) {
        LibraryDAOImplementation dao = new LibraryDAOImplementation();
        dao.connect();

        User user = dao.fetchUser(userId);
        Book book = dao.fetchBook(bookId);

        if (user == null) {
            System.out.println("Failed to borrow book. User not found.");
            return;
        }

        if (book == null) {
            System.out.println("Failed to borrow book. Book not found.");
            return;
        }

        if (book.getQuantity() <= 0) {
            System.out.println("Failed to borrow book. Book is out of stock.");
            return;
        }

        // Adjust transaction dates as needed
        Transaction t1 = new Transaction(userId, bookId, LocalDate.now(), LocalDate.of(2024, 3, 11),
                LocalDate.of(2024, 4, 11));

        transactions.add(t1);
        dao.addTransaction(t1);
        System.out.println("Book borrowed successfully!");
    }

    public void returnBook(int userId, int bookId) {

        List<Transaction> transactionsToRemove = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getUserId() == userId && transaction.getBookId() == bookId) {
                transactionsToRemove.add(transaction);
            }
        }

        if (!transactionsToRemove.isEmpty()) {
            transactions.removeAll(transactionsToRemove);
            System.out.println("Book(s) returned successfully!");
        } else {
            System.out.println("Failed to return book. Transaction not found.");
        }
    }

    // TRANSACTIONS ENDS HERE


    // Getters
    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

}
