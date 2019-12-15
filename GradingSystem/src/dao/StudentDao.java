package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects.Course;
import objects.Student;

// Interacts with both the Student table and the Student_Course table
public class StudentDao extends Dao<Student> {
    @Override
    public ArrayList<Student> executeQuery(String query) throws SQLException {
        ArrayList<Student> students = new ArrayList<>() ;
        try (
                Connection conn = getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
        ) {
            while (rs.next()) {
                String sid = rs.getString("sid") ;
                String fname = rs.getString("fname") ;
                String lname = rs.getString("lname") ;
                Student student = new Student(fname, lname, sid);
                students.add(student);
            }
        }
        return students;
    }

    @Override
    public void insert(Student student) throws SQLException {
        String query = String.format(
                "INSERT INTO Student (sid, fname, lname) VALUES ('%s', '%s', '%s')",
                student.getSid(),
                student.getFname(),
                student.getLname()
        );
        executeUpdate(query);
    }

    // Get by student id
    public ArrayList<Student> getById(String sid) throws SQLException {
        String query = String.format(
                "SELECT * from Student where sid = '%s'",
                sid
        );
        return executeQuery(query);
    }

    // Get all students in course
    public ArrayList<Student> getAll(Course course) throws SQLException {
        String query = String.format(
                "SELECT * from Student NATURAL JOIN Student_Course WHERE course_id = '%s'",
                course.getCourseId()
        );
        return executeQuery(query);
    }

    // Get EVERY student in the system
    public ArrayList<Student> getAll() throws SQLException {
        String query = String.format("SELECT * from Student");
        return executeQuery(query);
    }

    // Registers a student for a course
    public void insertStudentCourse(String sid, String cid) throws SQLException {
        String query = String.format(
                "INSERT INTO Student_Course (sid, course_id) VALUES ('%s', '%s')",
                sid,
                cid
        );
        executeUpdate(query);
    }
    
 // Withdraw a student for a course
    public void deleteStudentCourse(String sid, String cid) throws SQLException {
        String query = String.format(
                "DELETE FROM Student_Course WHERE sid = '%s' and course_id ='%s'",
                sid,
                cid
        );
        executeUpdate(query);
    }

    // Deletes student from Student table as well as from many-to-many table
    public void delete(Student student) throws SQLException {
        String query = String.format(
                "DELETE FROM Student WHERE sid = '%s'",
                student.getSid()
        );
        executeUpdate(query);

        query = String.format(
                "DELETE FROM Student_Course WHERE sid = '%s'",
                student.getSid()
        );
        executeUpdate(query);
    }

}
