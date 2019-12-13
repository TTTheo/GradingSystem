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
	private CourseDao courseDao = new CourseDao();
	private CategoryDao categoryDao = new CategoryDao();
	private PartDao partDao = new PartDao();

	Course currentCourse;

	public CourseBackend() {
		currentCourse = null;
	}

	public CourseBackend(Course course) {
		currentCourse = course;
	}

	public Course getCurrentCourse() {
		return currentCourse;
	}

	public void setCurrentCourse(Course c) {
		currentCourse = c;
	}

	// Course methods

	// Gets every single course semester
	public ArrayList<Course> getAllCourse(Semester semester) {
		ArrayList<Course> courses = null;
		try {
			courses = courseDao.getAll(semester);
			if (courses != null) {
				for (Course course : courses) {
					ArrayList<Category> cats = getCategories(course);
					course.setCategories(cats);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}

	// Get a course by it's course id (eg. "cs591")
	// Populates the course's categories (and parts) as well
	public Course getCourse(String courseid) {
		ArrayList<Course> courses = null;
		Course course = null;
		try {
			courses = courseDao.getCourse(courseid);
			if (courses == null) return null;
			course = courses.get(0);
			ArrayList<Category> cats = getCategories(course);
			course.setCategories(cats);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}

	public boolean addCourse(Course course) {
		if (course == null) return false;
		try {
			courseDao.insert(course);
			ArrayList<Category> cats = course.getCategories();
			if (cats != null) {
				for (Category cat : cats) {
					addCategory(cat);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateCourse(Course course) {
		if (course == null) return false;
		try {
			courseDao.update(course);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/* Category Backend Methods */

	// Get all categories for a course
	public ArrayList<Category> getCategories(Course c) throws SQLException {
		ArrayList<Category> categories = new ArrayList<>();
		categories = categoryDao.getAll(c.getCourseid());
		if (categories.size() == 0) return null;
		for (Category cat : categories) {
			ArrayList<Part> parts = getParts(cat);
			cat.setPartList(parts);
		}
		return categories;
	}

	public boolean addCategory(Category category) throws SQLException {
		categoryDao.insert(category);
		ArrayList<Part> parts = category.getPartList();
		if (parts != null && parts.size() != 0) {
			for (Part part : parts) {
				addPart(part);
			}
		}
		return true;
	}

	/* Part backend methods */

	// Get all parts for a category
	public ArrayList<Part> getParts(Category c) throws SQLException {
		ArrayList<Part> parts = partDao.getAll(c.getCid());
		if (parts.size() == 0) return null;
		return parts;
	}

	public void addPart(Part part) throws SQLException {
		partDao.insert(part);
	}

	/* Student backend methods */

	/* Grade backend methods */
}
