public class Main {

    public static void main(String[] args) {

        // Student s1 = new Student(2, "Deepak", 26);
        JDBCStudentDAO obj1 = new JDBCStudentDAO();
        obj1.connect();
        // obj1.updateStudent(3, "Surya", 27);
        obj1.getStudent(3);
        obj1.closeConnection();
    }

}