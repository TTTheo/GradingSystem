package objects;

import java.util.*;

public class Course {
	private String courseId ;
	private int semesterId;
	private String name;
	private ArrayList<Student> students ;
	private ArrayList<Category> categories ;
	
	public Course() {
		this("", 0, "", new ArrayList<Student>(), new ArrayList<Category>());
	}

	public Course(String courseId, int semesterId) {
		this(courseId, semesterId, "", new ArrayList<Student>(), new ArrayList<Category>());
	}

	public Course(String courseId, int semesterId, String name) {
		this(courseId, semesterId, name, new ArrayList<Student>(), new ArrayList<Category>());
	}

	public Course(String courseId, int semesterId, String name, ArrayList<Student> students, ArrayList<Category> categories) {
		this.courseId = courseId;
		this.semesterId = semesterId;
		this.name = name;
		this.students = students;
		this.categories = categories;
	}

	public int getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}
	
	public Category getCategoryAt(int index) {
		return categories.get(index);
	}

	public void addCategory(Category category) {
		this.categories.add(category);
	}
}
