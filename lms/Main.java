package lms;

// import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        LibraryDAOImplementation dao = new LibraryDAOImplementation();
        Library library = new Library();

        dao.connect();

        Book book1 = new Book("DBMS", "Swati Ma'am", "Programming", 5);
        Book book2 = new Book("OS", "HOD Sir", "Operating System", 5);

        User user1 = new User("Zaid", "developerzaid@gmail.com", "Nalasopara");
        User user2 = new User("Anas", "anas@gmail.com", "Jogeshwari");

        library.addBook(book1);
        library.addBook(book2);
        library.addUser(user1);
        library.addUser(user2);

        dao.addBook(book1);
        dao.addBook(book2);
        dao.addUser(user1);
        dao.addUser(user2);

        library.borrowBook(1, 1);
        library.returnBook(1, 1);

        
        // library.returnBook(1, 1);
        // Book b1 = new Book("C Programming","Yashavant Kanetkar","Programming",15);
        // dao.addBook(b1);

        // Book b2 = new Book("Adv Java", "Faizan", "Programming", 10);
        // dao.updateBook(2, b2);
        // dao.deleteBook(3);

        // get all book
        // for(Book x : dao.getAllBook() ){
        // System.out.println( x.getBookId() + " : " + x.getAuthor() + " : "
        // + x.getGenre() + " : " + x.getQuantity() );
        // }

        // User user1 = new User("Faizan", "enrollwebsite@gmail.com", "sakinaka");
        // User user2 = new User("Ritesh", "guptaritesh@gmail.com", "sewri");
        // User user3 = new User("Surya", "surya@gmail.com", "Thane");
        // dao.addUser(user2);

        // dao.updateUser(1, user1);

        // dao.deleteUser(3);

        // for(User user : dao.getAllUser()){
        // System.out.println(user.getUserId() + " : " +
        // user.getUserName() + " : " +
        // user.getUserEmail() + " : " +
        // user.getUserAddress() );
        // }

        // Transaction t1 = new Transaction(2, 2, LocalDate.of(2024, 2, 10),
        // LocalDate.of(2024, 3, 10),
        // LocalDate.of(2024, 4, 10));

        // dao.addTransaction(t1);
        // dao.updateTransaction(4, t1);
        // dao.deleteTransaction(4);

        // for(Transaction x : dao.getAllTransaction()){
        // System.out.println(x.getTransactionId() + " : " +
        // x.getUserId() + " : " +
        // x.getBookId() + " : " +
        // x.getBorrowDate() + " : " +
        // x.getDueDate() + " : " +
        // x.getReturnDate());
        // }

        // Book book = dao.fetchBook(1);
        // System.out.println(book.getBookId());
        // System.out.println(book.getTitle());
        // System.out.println(book.getAuthor());
        // System.out.println(book.getGenre());
        // System.out.println(book.getQuantity());

        // System.out.println(book.getBookId() + " : " + book.getTitle()
        // + " : " + book.getAuthor() + " : " + book.getGenre() + " : " +
        // book.getQuantity());

        // User user = dao.fetchUser(1);

        // System.out.println(user.getUserId() + " : " + user.getUserName()
        // + " : " + user.getUserEmail() + " : " + user.getUserAddress());

        // Transaction t1 = dao.fetchTransaction(6);
        // System.out.println(t1.getTransactionId() + " : " +
        // t1.getUserId() + " : " +
        // t1.getBookId() + " : " +
        // t1.getBorrowDate() + " : " +
        // t1.getDueDate() + " : " +
        // t1.getReturnDate() );

        dao.closeConnection();

    }
}
