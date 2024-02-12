package lms;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LibraryDAOImplementation dao = new LibraryDAOImplementation();
        Library library = new Library();

        dao.connect();

        while (true) {
            System.out.println("              +----------------------------------------+");
            System.out.println("                 WELCOME TO LIBRARY MANAGEMENT SYSTEM");
            System.out.println("              +----------------------------------------+");

            System.out.println();
            System.out.println("                      +----------------------+");
            System.out.println("                         FEATURES AVAILABLE");
            System.out.println("                      +----------------------+");

            System.out.println();
            System.out.println("------------------------");
            System.out.println("1. ADD A BOOOK");
            System.out.println("2. DELETE A BOOOK");
            System.out.println("3. UPDATE A BOOK");
            System.out.println("4. GET ALL BOOKS");

            System.out.println("------------------------");
            System.out.println("5. ADD A USER");
            System.out.println("6. DELETE A USER");
            System.out.println("7. UPDATE A USER");
            System.out.println("8. GET ALL USERS");

            System.out.println("------------------------");
            System.out.println("9.  ADD A TRANSACTION");
            System.out.println("10. DELETE A TRANSACTION");
            System.out.println("11. UPDATE A TRANSACTION");
            System.out.println("12. GET ALL TRANSACTIONS");

            System.out.println("------------------------");
            System.out.println("13. EXIT");
            System.out.println("------------------------");
            System.out.println();

            System.out.println("ENTER YOUR CHOICE : ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter the book title ");
                    String title = sc.nextLine();
                    System.out.println("Enter a author of book ");
                    String author = sc.nextLine();
                    System.out.println("Enter a genre of book ");
                    String genre = sc.nextLine();
                    System.out.println("Enter a quantity of book ");
                    int quantity = sc.nextInt();

                    Book book1 = new Book(title, author, genre, quantity);
                    library.books.add(book1);
                    dao.addBook(book1);
                    System.out.println("Book added successfully and also added in the DB");
                    break;

                case 2:
                    System.out.println("Enter a book id you want to delete");
                    System.out.println("check the book id present in the DB");
                    int id = sc.nextInt();
                    dao.deleteBook(id);

                    System.out.println("Book Deleted successfully!");
                    break;

                case 3:
                    System.out.println("Enter a book id you want to update!");
                    int bookIdForUpdate = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter new details for the book!");
                    System.out.println("Enter the book title ");
                    String updatedTitle = sc.nextLine();

                    System.out.println("Enter a author of book ");
                    String updatedAuthor = sc.nextLine();
                    System.out.println("Enter a genre of book ");
                    String updatedGenre = sc.nextLine();
                    System.out.println("Enter a quantity of book ");
                    int updatedQuantity = sc.nextInt();

                    Book updatedBook = new Book(updatedTitle, updatedAuthor, updatedGenre, updatedQuantity);
                    dao.updateBook(bookIdForUpdate, updatedBook);
                    System.out.println("Book Updated Successfully!");
                    break;

                case 4:
                    for (Book book : dao.getAllBook()) {
                        System.out.println(book.getBookId() + " : " + book.getTitle() + " : " +
                                book.getAuthor() + " : " + book.getQuantity());
                    }
                    break;

                case 5:
                    System.out.println("Enter the user name ");
                    String userName = sc.nextLine();
                    System.out.println("Enter a user email ");
                    String userEmail = sc.nextLine();
                    System.out.println("Enter a user address");
                    String userAddress = sc.nextLine();

                    User user1 = new User(userName, userEmail, userAddress);
                    library.users.add(user1);
                    dao.addUser(user1);
                    System.out.println("User added successfully and also added in the DB");
                    break;

                case 6:
                    System.out.println("Enter a user id you want to delete");
                    System.out.println("check the user id present in the DB");
                    int deletedUserId = sc.nextInt();
                    dao.deleteUser(deletedUserId);

                    System.out.println("User Deleted successfully!");
                    break;

                case 7:
                    System.out.println("Enter a user id you want to update!");
                    int userIdForUpdate = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter new details for the user!");
                    System.out.println("Enter the user name ");
                    String updatedUserName = sc.nextLine();
                    System.out.println("Enter a user email ");
                    String updatedUserEmail = sc.nextLine();
                    System.out.println("Enter a user address");
                    String updatedUserAddress = sc.nextLine();

                    User updatedUser = new User(updatedUserName, updatedUserEmail, updatedUserAddress);
                    dao.updateUser(userIdForUpdate, updatedUser);
                    System.out.println("User Updated Successfully!");
                    break;

                case 8:
                    for (User user : dao.getAllUser()) {
                        System.out.println(user.getUserId() + " : " + user.getUserName()
                                + " : " + user.getUserEmail() + " : " + user.getUserAddress());
                    }
                    break;

                case 9:
                    System.out.println("Enter a user id : ");
                    int userIdForTransaction = sc.nextInt();
                    System.out.println("Enter a book id");
                    int bookIdForTransaction = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter borrow date in format (YYYY-MM-DD)");
                    String inputBorrrowDate = sc.nextLine();
                    System.out.println("Enter due date in format (YYYY-MM-DD)");
                    String inputDueDate = sc.nextLine();
                    System.out.println("Enter return date in format (YYYY-MM-DD)");
                    String inputReturnDate = sc.nextLine();
                    LocalDate borrowDate;
                    LocalDate dueDate;
                    LocalDate returnDate;
                    try {
                        borrowDate = LocalDate.parse(inputBorrrowDate);
                        dueDate = LocalDate.parse(inputDueDate);
                        returnDate = LocalDate.parse(inputReturnDate);
                        Transaction transaction = new Transaction(userIdForTransaction, bookIdForTransaction,
                                borrowDate, dueDate, returnDate);
                        dao.addTransaction(transaction);
                        System.out.println("Transaction added successfully!");
                    } catch (Exception e) {
                        System.out.println("Enter in a proper format!");
                    }
                    break;

                case 10:
                    System.out.println("Enter a transaction id you want to delete!");
                    int transactionIdForDeletion = sc.nextInt();
                    sc.nextLine();
                    dao.deleteTransaction(transactionIdForDeletion);
                    System.out.println("Transaction deleted successfully!");
                    break;

                case 11:
                    System.out.println("Enter a transaction id you want to update!");
                    int transactionIdForUpdation = sc.nextInt();

                    System.out.println("Enter a user id : ");
                    int updatedUserId = sc.nextInt();

                    System.out.println("Enter a book id : ");
                    int updatedBookId = sc.nextInt();

                    sc.nextLine();

                    System.out.println("Enter a borrowDate in (YYYY-MM-DD) format : ");
                    String updatedInputBorrowDate = sc.nextLine();
                    LocalDate updatedBorrowDate = LocalDate.parse(updatedInputBorrowDate);

                    System.out.println("Enter a dueDate in (YYYY-MM-DD) format : ");
                    String updatedInputDueDate = sc.nextLine();
                    LocalDate updatedDueDate = LocalDate.parse(updatedInputDueDate);

                    System.out.println("Enter a returnDate in (YYYY-MM-DD) format : ");
                    String updatedInputReturnDate = sc.nextLine();
                    LocalDate updatedReturnDate = LocalDate.parse(updatedInputReturnDate);

                    // Ensure that the due date comes before the return date
                    if (updatedDueDate.isAfter(updatedReturnDate)) {
                        System.out.println("Error: Due date cannot be after return date.");
                        // Handle the error and return, or prompt the user again for correct dates
                    } else {
                        Transaction transaction1 = new Transaction(updatedUserId, updatedBookId,
                                updatedBorrowDate, updatedDueDate, updatedReturnDate);

                        dao.updateTransaction(transactionIdForUpdation, transaction1);
                        System.out.println("Transaction updated successfully!");
                    }
                    break;

                case 12:
                    for (Transaction transaction : dao.getAllTransaction()) {
                        System.out.println(transaction.getUserId() + " : " + transaction.getBookId() + " : " +
                                transaction.getBorrowDate() + " : " + transaction.getDueDate() + " : "
                                + transaction.getReturnDate());
                    }
                    break;

                case 13:
                    sc.close();
                    dao.closeConnection();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! ");
                    System.out.println("choice should be between (1-12)");
                    break;
            }

        }
    }
}
