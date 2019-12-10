package backend;

import java.sql.SQLException;
import java.util.ArrayList;

import objects.Course;
import objects.Category;
import objects.Part;
import objects.Semester;
import dao.*;

// Logic for everything specific to one course, including
// categories, parts, students, grades
public class CourseBackend {
    private CourseDao cd = new CourseDao() ;
    private CategoryBackend catb = new CategoryBackend() ;

    Course currentCourse;

	public CourseBackend () {
		currentCourse = null;
	}

	public CourseBackend(Course course) {
		currentCourse = course;
	}

	public void setCurrentCourse(Course c) {
		currentCourse = c;
	}
    
    public ArrayList<Course> getAllCourse(Semester semester) {
    	ArrayList<Course> courses = null;
    	try {
			courses = cd.getAll(semester.getTerm(), semester.getYear()) ;
			if(courses != null) {
				for(Course course : courses) {
					ArrayList<Category> cats = catb.getCategories(course.getCourseid()) ;
					course.setCategories(cats);
					course.setCategoryCount(cats.size());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return courses ;
    }
    
    public Course getCourse(String courseid) {
    	ArrayList<Course> courses = null ;
    	Course course = null ;
    	try {
    		courses = cd.getCourse(courseid) ;
    		if(courses == null) return null ;
    		course = courses.get(0) ;
    		ArrayList<Category> cats = new ArrayList<>() ;
    		cats = catb.getCategories(courseid) ;
    		course.setCategories(cats);
    		course.setCategoryCount(cats.size());
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	return course ;
    }
    
    public boolean addCourse(Course course) {
    	if(course == null) return false ;
    	try {
    		cd.insert(course);
    		ArrayList<Category> cats = course.getCategories() ;
    		if(cats != null) {
    			for(Category cat : cats) {
    				catb.addCategory(cat) ;
    			}
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return false ;
    	}
    	return true ;
    }
    
    public boolean updateCourse(Course course) {
    	if(course == null) return false ;
    	try {
    		cd.update(course);
    		ArrayList<Category> cats = course.getCategories() ;
    		if(cats != null) {
    			for(Category cat : cats) {
    				catb.updateCategory(cat) ;
    			}
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return false ;
    	}
    	return true ;
    }

	/* Category Backend Methods */
}
