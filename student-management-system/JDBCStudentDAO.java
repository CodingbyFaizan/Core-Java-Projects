import java.sql.*;

public class JDBCStudentDAO implements StudentDAO {

    Connection con = null;

    public void connect() {
        String url = "jdbc:mysql://localhost/studentdb";
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

    public void addStudent(Student obj) {
        String query = "insert into student values (?,?,?)";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, obj.getId());
            pst.setString(2, obj.getName());
            pst.setInt(3, obj.getAge());
            int rowAffected = pst.executeUpdate();
            System.out.println(rowAffected + "row/s affected");
        } catch (SQLException s) {
            System.out.println(s);
        }

    }

    public void getStudent(int id) {

        // Student obj = new Student();
        // obj.getId() = id;

        String query = "select * from student where sid = ?";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            System.out.println(rs.getInt("sid")
                    + " : " + rs.getString("name")
                    + " : " + rs.getInt("age"));

        } catch (SQLException s) {
            System.out.println(s);
        }
        // return null;
    }

    public void updateStudent(int id, String name, int age) {

        String query = " update student set name = ? , age = ? where sid = ?";
        try {

            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setInt(2, age);
            pst.setInt(3, id);
            int rowAffected = pst.executeUpdate();
            System.out.println(rowAffected + " row/s affected");
            

        } catch (SQLException s) {
            System.out.println(s);
        }


    }

    public void deleteStudent(int id) {

        String query = "delete from student where sid = ?";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            int rowAffected = pst.executeUpdate();
            System.out.println(rowAffected + "row/s affected");
        } catch (SQLException s) {
            System.out.println(s);
        }

    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException s) {
            System.out.println(s);
        }
    }

}
