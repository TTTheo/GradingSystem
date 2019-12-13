package dao;

import java.sql.*;
import java.util.ArrayList;

import objects.Semester;

public class SemesterDao extends Dao<Semester> {
    @Override
    public ArrayList<Semester> executeQuery(String query) throws SQLException {
        ArrayList<Semester> semesters = new ArrayList<Semester>();
        try (
                Connection conn = getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
        ) {
            while (rs.next()) {
                String term = rs.getString("term");
                Integer year = (Integer) rs.getObject("year");
                Semester sem = new Semester(term, year);
                semesters.add(sem);
            }
        }
        return semesters;
    }

    @Override
    public void insert(Semester semester) throws SQLException {
        String query = String.format(
                "INSERT INTO Semester (term, year) VALUES ('%s', %d)",
                semester.getTerm(),
                semester.getYear()
        );
        int sid = executeUpdate(query);
        semester.setSemester_id(sid);
    }

    public ArrayList<Semester> getAll() throws SQLException {
        String query = "SELECT * FROM Semester";
        return executeQuery(query);
    }
}
