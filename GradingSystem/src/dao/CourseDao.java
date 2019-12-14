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
            	String courseId = rs.getString("course_id") ;
            	int semesterId = rs.getInt("semester_id");
                String name = rs.getString("name") ;
                Course course = new Course(courseId, semesterId, name) ;
                courses.add(course);
            }
        }
        return courses;
    }

    @Override
    public void insert(Course course) throws SQLException {

        String query = String.format(
                "INSERT INTO Course (course_id, semester_id, name) VALUES ('%s', %d, '%s')",
                course.getCourseId(),
                course.getSemesterId(),
                course.getName()
        );
        executeUpdate(query);
    }

    // Get EVERY course (in all semesters)
    public ArrayList<Course> getAll() throws SQLException {
        String query = String.format("SELECT * FROM Course");
        return executeQuery(query);
    }

    // Get all courses in a semester
    public ArrayList<Course> getAll(Semester sem) throws SQLException {
        String query = String.format(
        		"SELECT * FROM Course WHERE semester_id = '%d'",
        		sem.getSemesterId()
        );
        return executeQuery(query);
    }

    // Get a specific course using the course's ID
    public ArrayList<Course> getCourse(String courseId) throws SQLException {
    	String query = String.format(
        		"SELECT * FROM Course WHERE course_id = '%s'",
        		courseId
        );
        return executeQuery(query);
    }
    
    public boolean update(Course course) throws SQLException {
    	String query = String.format(
                "UPDATE Course SET course_id = '%s', semester_id = '%d', name = '%s'",
                course.getCourseId(),
                course.getSemesterId(),
                course.getName()
        );

    	try {
    		executeUpdate(query);
    	} catch(SQLException e) {
    		e.printStackTrace();
    		return false ;
    	}
    	return true ;
    }

}
