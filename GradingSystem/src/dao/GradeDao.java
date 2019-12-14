package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects.Grade;

// This dao class hasn't been tested yet, feel free to try it out and report any bugs

// Interacts with both the Student table and the Student_Course table
public class GradeDao extends Dao<Grade> {
    @Override
    public ArrayList<Grade> executeQuery(String query) throws SQLException {
        ArrayList<Grade> grades = new ArrayList<>() ;
        try (
                Connection conn = getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
        ) {
            while (rs.next()) {
                String sid = rs.getString("sid") ;
                int pid = rs.getInt("pid") ;
                double gradeValue = rs.getDouble("grade") ;
                String comment = rs.getString("comment") ;
                Grade grade = new Grade(sid, pid, gradeValue, comment);
                grades.add(grade);
            }
        }
        return grades;
    }

    @Override
    public void insert(Grade grade) throws SQLException {
        String query = String.format(
                "INSERT INTO Grade (sid, pid, grade, comment) VALUES ('%s', %d, %f, '%s')",
                grade.getSid(),
                grade.getPid(),
                grade.getGrade(),
                grade.getComment()
        );
        executeUpdate(query);
    }

    public ArrayList<Grade> getById(String sid, int pid) throws SQLException {
        String query = String.format(
                "SELECT * from Grade WHERE sid = '%s' AND pid = %d",
                sid,
                pid
        );
        return executeQuery(query);
    }

    public ArrayList<Grade> getByStudentId(String sid) throws SQLException {
        String query = String.format(
                "SELECT * from Grade WHERE sid = '%s'",
                sid
        );
        return executeQuery(query);
    }

    public ArrayList<Grade> getByPartId(int pid) throws SQLException {
        String query = String.format(
                "SELECT * from Grade WHERE pid = %d",
                pid
        );
        return executeQuery(query);
    }

    // Get every grade in the DB
    public ArrayList<Grade> getAll() throws SQLException {
        String query = String.format("SELECT * from Grade");
        return executeQuery(query);
    }

    // Updates grade value and comment
    public void update(Grade grade) throws SQLException {
        String query = String.format(
                "UPDATE Grade SET grade = %f, comment = '%s' WHERE sid = '%s' AND pid = %d",
                grade.getGrade(),
                grade.getComment(),
                grade.getSid(),
                grade.getPid()
        );
        executeUpdate(query);
    }

    public void delete(String sid, int pid) throws SQLException {
        String query = String.format(
                "DELETE FROM Grade WHERE sid = '%s' AND pid = %d",
                sid,
                pid
        );
        executeUpdate(query);
    }

}
