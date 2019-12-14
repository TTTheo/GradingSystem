package backend;

import java.sql.SQLException;
import java.util.ArrayList;

import objects.*;
import dao.*;

// Logic for managing the interaction between GUI and DB
public class Backend {
    // Initialize DB classes
	private UserDao userDao = new UserDao();
	private SemesterDao semesterDao = new SemesterDao();
	private CourseDao courseDao = new CourseDao();
	private CategoryDao categoryDao = new CategoryDao();
	private PartDao partDao = new PartDao();

	// Track current user, current semester, and current course
	private User user;  // Unused, multiple users is a low priority feature
	private Semester semester;
	private Course course;

	public Backend() {
		this(null, null, null);
	}

	public Backend(User user, Semester semester, Course course) {
		this.user = user;
		this.semester = semester;
		this.course = course;
	}

	/* User Methods Start */

	public void setUser(User user) {
		this.user = user;
	}

	// Returns the User if username and password were correct
	// else returns null if no such user exists
	public User loginUser(String user, String pass) throws SQLException {
		ArrayList<User> userList = userDao.getUsersByLogin(user, pass);
		if (userList.size() == 0) {
			return null;
		}
		User existingUser = userList.get(0);
		return existingUser;
	}

	// Adds a user to the database.
	// Returns null if another user already exists with the same username.
	public User signUp(String user, String pass) throws SQLException {
	    // First check if username already exists
		ArrayList<User> userList = userDao.getUsersByName(user);
		if (userList.size() != 0) {
			return null;
		}

		User newUser = new User(user, pass);
		userDao.insert(newUser);
		return newUser;
	}

	/* Semester Methods Start */

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public void addSemester(Semester s) throws SQLException {
		semesterDao.insert(s);
	}

	public ArrayList<Semester> getAllSemesters() throws SQLException {
		return semesterDao.getAll();
	}

	/* Course Methods Start */

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

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

	public void addCourse(Course course) throws SQLException {
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
			throw e;
		}
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

	/* Category Methods Start */

	// Get all categories for a course
	public ArrayList<Category> getCategories(Course c) throws SQLException {
		ArrayList<Category> categories = new ArrayList<>();
		categories = categoryDao.getAll(c.getCourseId());
		if (categories.size() == 0) return null;
		for (Category cat : categories) {
			ArrayList<Part> parts = getParts(cat);
			cat.setPartList(parts);
		}
		return categories;
	}

	public void addCategory(Category category) throws SQLException {
		categoryDao.insert(category);
		ArrayList<Part> parts = category.getPartList();
		if (parts != null && parts.size() != 0) {
			for (Part part : parts) {
				addPart(part);
			}
		}
	}

	/* Part Methods Start */

	// Get all parts for a category
	public ArrayList<Part> getParts(Category c) throws SQLException {
		ArrayList<Part> parts = partDao.getAll(c.getCid());
		if (parts.size() == 0) return null;
		return parts;
	}

	public void addPart(Part part) throws SQLException {
		partDao.insert(part);
	}

	/* Student Methods Start */

	/* Grade Methods Start */
}
