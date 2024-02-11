package lms;

import java.util.ArrayList;

public interface LibraryDAO {
    void connect();


    void addBook(Book book);
    void updateBook(int bookId,Book updatedBook);
    void deleteBook(int bookId);
    ArrayList<Book> getAllBook();


    void addUser(User user);
    void updateUser(int userId, User updatedUser);
    void deleteUser(int userId);
    ArrayList<User> getAllUser();


    void addTransaction(Transaction transaction);
    void updateTransaction(int transactionId, Transaction updatedTransaction);
    void deleteTransaction(int transactionId);
    ArrayList<Transaction> getAllTransaction();


    Book fetchBook(int bookId);
    User fetchUser(int userId);
    Transaction fetchTransaction(int transactionId);

    
    void closeConnection();

}
