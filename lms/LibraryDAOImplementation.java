package lms;

import java.sql.*;
import java.util.ArrayList;

public class LibraryDAOImplementation implements LibraryDAO {

    Connection con = null;

    public void connect() {
        String url = "jdbc:mysql://localhost/librarydb";
        String user = "root";
        String pass = "Faizan@786";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException c) {
            System.out.println(c);
        } catch (SQLException s) {
            System.out.println(s);
        }

    }

    // BOOKS CODE START HERE

    public void addBook(Book book) {
        String query = " INSERT INTO books " + "(title, author, genre, quantity)" +
                " VALUES (?, ?, ?, ?) ";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, book.getTitle());
            pst.setString(2, book.getAuthor());
            pst.setString(3, book.getGenre());
            pst.setInt(4, book.getQuantity());
            int rowAffected = pst.executeUpdate();
            System.out.println(rowAffected + " row/s affected");


            pst.close();
        } catch (SQLException s) {
            System.out.println(s);
        }
        
    }

    public void updateBook(int bookId, Book updatedBook) {
        String query = " Update books set " + "title = ?, author = ? , genre = ? , quantity = ?" +
                " where book_id = ?";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, updatedBook.getTitle());
            pst.setString(2, updatedBook.getAuthor());
            pst.setString(3, updatedBook.getGenre());
            pst.setInt(4, updatedBook.getQuantity());
            pst.setInt(5, bookId);
            int rowAffected = pst.executeUpdate();
            System.out.println(rowAffected + " row/s affected");
            pst.close();
        } catch (SQLException s) {
            System.out.println(s);
        }
    }

    public void deleteBook(int bookId) {
        String query = "Delete from books where book_id = ?";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, bookId);
            int rowAffected = pst.executeUpdate();
            System.out.println(rowAffected + " row/s affected");
            pst.close();
        } catch (SQLException s) {
            System.out.println(s);
        }
    }

    public ArrayList<Book> getAllBook() {
        ArrayList<Book> books = new ArrayList<>();

        String query = "Select * from books";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                books.add(new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)));

                // System.out.println(rs.getInt(1) + " : "
                // + rs.getString(2) + " : "
                // + rs.getString(3) + " : "
                // + rs.getString(4) + " : "
                // + rs.getInt(5));

            }
            return books;

        } catch (SQLException s) {
            System.out.println(s);
        }
        return null;

    }

    // BOOKS CODE END HERE

    // USERS CODE START HERE

    public void addUser(User user) {
        String query = "insert into users" + "(name,email,address)" +
                "values(?,?,?)";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getUserEmail());
            pst.setString(3, user.getUserAddress());
            int rowAffected = pst.executeUpdate();
            System.out.println(rowAffected + " row/s affected");
            pst.close();
        } catch (SQLException s) {
            System.out.println(s);
        }
    }

    public void updateUser(int userId, User updatedUser) {
        String query = "update users set name = ? , email = ? , address = ? where user_id = ? ";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, updatedUser.getUserName());
            pst.setString(2, updatedUser.getUserEmail());
            pst.setString(3, updatedUser.getUserAddress());
            pst.setInt(4, userId);
            int rowAffected = pst.executeUpdate();
            System.out.println(rowAffected + " row/s affected");
            pst.close();
        } catch (SQLException s) {
            System.out.println(s);
        }
    }

    public void deleteUser(int userId) {
        String query = "delete from users where user_id = ? ";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, userId);
            int rowAffected = pst.executeUpdate();
            System.out.println(rowAffected + " row/s affected");
            pst.close();
        } catch (SQLException s) {
            System.out.println(s);
        }
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> users = new ArrayList<>();

        String query = "Select * from users";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                users.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)));

                // System.out.println(rs.getInt(1) + " : "
                // + rs.getString(2) + " : "
                // + rs.getString(3) + " : "
                // + rs.getString(4) + " : "
                // + rs.getInt(5));

            }
            return users;

        } catch (SQLException s) {
            System.out.println(s);
        }
        return null;
    }

    // USERS CODE ENDS HERE

    // TRANSACTIONS CODES START HERE

    public void addTransaction(Transaction transaction) {
        String query = "insert into transactions" + "(user_id,book_id,borrow_date,due_date,return_date)" +
                "values(?,?,?,?,?)";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, transaction.getUserId());
            pst.setInt(2, transaction.getBookId());
            pst.setDate(3, Date.valueOf(transaction.getBorrowDate()));
            pst.setDate(4, Date.valueOf(transaction.getDueDate()));
            pst.setDate(5, Date.valueOf(transaction.getReturnDate()));
            int rowAffected = pst.executeUpdate();
            System.out.println(rowAffected + " row/s affected");
            pst.close();
        } catch (SQLException s) {
            System.out.println(s);
        }
    }

    public void updateTransaction(int transactionId, Transaction updatedTransaction) {
        String query = "update transactions set user_id = ? , book_id = ? , borrow_date = ?, return_date = ?, due_date = ? where transaction_id = ? ";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, updatedTransaction.getUserId());
            pst.setInt(2, updatedTransaction.getBookId());
            pst.setDate(3, Date.valueOf(updatedTransaction.getBorrowDate()));
            pst.setDate(4, Date.valueOf(updatedTransaction.getDueDate()));
            pst.setDate(5, Date.valueOf(updatedTransaction.getReturnDate()));
            pst.setInt(6, transactionId);

            int rowAffected = pst.executeUpdate();
            System.out.println(rowAffected + " row/s affected");
            pst.close();
        } catch (SQLException s) {
            System.out.println(s);
        }
    }

    public void deleteTransaction(int transactionId) {
        String query = "delete from transactions where transaction_id = ? ";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, transactionId);
            int rowAffected = pst.executeUpdate();
            System.out.println(rowAffected + " row/s affected");
            pst.close();
        } catch (SQLException s) {
            System.out.println(s);
        }
    }

    public ArrayList<Transaction> getAllTransaction() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        String query = "Select * from transactions";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                transactions.add(new Transaction(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getDate(5).toLocalDate(),
                        rs.getDate(6).toLocalDate()));

            }
            return transactions;

        } catch (SQLException s) {
            System.out.println(s);
        }
        return null;
    }

    // TRANSACTIONS CODES ENDS HERE

    // FETCHING PARTICULAR RECORDS FROM DB

    public Book fetchBook(int bookId) {
        Book book = null;
        String query = "select * from books where book_id = ? ";

        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, bookId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                book = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
            }
            return book;

        } catch (SQLException s) {
            System.out.println(s);
        }

        return null;
    }

    public User fetchUser(int userId) {
        User user = null;
        String query = "select * from users where user_id = ? ";

        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                user = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }
            return user;

        } catch (SQLException s) {
            System.out.println(s);
        }

        return null;
    }

    public Transaction fetchTransaction(int transactionId) {
        Transaction transaction = null;

        String query = "select * from transactions where transaction_id = ?";

        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, transactionId);
            ResultSet rs = pst.executeQuery();

            rs.next();
            transaction = new Transaction(rs.getInt(1), 
                                          rs.getInt(2),
                                          rs.getInt(3),
                                          rs.getDate(4).toLocalDate(),
                                          rs.getDate(5).toLocalDate(),
                                          rs.getDate(5).toLocalDate() );

            return transaction;
        } catch (SQLException s) {
            System.out.println(s);
        }
        return null;
    }

    // ENDS HERE

    // CLOSE CONNECTION
    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException s) {
            System.out.println(s);
        }
    }

}
