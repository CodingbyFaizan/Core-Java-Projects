public interface StudentDAO {
    void connect();
    void addStudent(Student s);
    void getStudent(int id);
    void updateStudent(int id,String name,int age);
    void deleteStudent(int id);
    void closeConnection();
}
