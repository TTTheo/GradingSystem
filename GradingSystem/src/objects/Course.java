package objects;

import java.util.*;

public class Course {
	private String courseId ;
	private int semesterId;
	private String name;
	private ArrayList<Student> students ;
	private ArrayList<Category> categories ;
	
	public Course() {
		//do nothing
	}

	public Course(String courseId) {
		this.courseId = courseId ;
	}

	public Course(String courseId, String name) {
		this(courseId);
		this.name = name;
	}

	public Course(String courseId, int semesterId, String name) {
		this(courseId, name);
		this.semesterId = semesterId;
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
	
	public String getCourseid() {
		return courseId;
	}

	public void setCourseid(String courseId) {
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
