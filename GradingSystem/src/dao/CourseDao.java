package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects.*;

public class CourseDao extends Dao<Course>{
	@Override
    public ArrayList<Course> executeQuery(String query) throws SQLException {
        ArrayList<Course> courses = new ArrayList<>() ;
        try (
                Connection conn = getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
        ) {
            while (rs.next()) {
            	String term = rs.getString("term") ;
            	int year = rs.getInt("year") ;
                String name = rs.getString("name") ;
                Course course = new Course(new Semester(term,year),name) ;
                courses.add(course);
            }
        }
        return courses;
    }

    @Override
    public void insert(Course course) throws SQLException {

        String query = String.format(
                "INSERT INTO Course (term, year, courseid, name) VALUES ('%s',%d,'%s', '%s')",
                course.getSemster().getTerm(),
                course.getSemster().getYear(),
                course.getCourseid(),
                course.getName()
        );

        executeUpdate(query);
    }

    public ArrayList<Course> getAll(String term, int year) throws SQLException {
        String query = String.format(
        		"SELECT * FROM Course WHERE term = '%s' and year = %d",
        		term,
        		year
        );
        return executeQuery(query);
    }
    
    public ArrayList<Course> getCourse(String courseid) throws SQLException {
    	String query = String.format(
        		"SELECT * FROM Course WHERE courseid = '%s'",
        		courseid
        );
        return executeQuery(query);
    }
    
    public boolean update(Course course) throws SQLException {
    	String query = String.format(
        		"UPDATE Course SET term = '%s', year = %d, courseid = '%s', name = '%s'",
        		course.getSemster().getTerm(),
        		course.getSemster().getYear(),
        		course.getCourseid(),
        		course.getName()
        		);
    	try {
    		executeUpdate(query);
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return false ;
    	}
    	return true ;
    }

}
