package objects;

import java.util.*;

public class Course {
	private Semester semster ;
	private String name ;
	private String courseid ;
	private int categoryCount;
	private ArrayList<Student> students ;
	private ArrayList<Category> categories ;
	
	public Course() {
		//do nothing
	}
	
	public Course(Semester semester, String name) {
		this.semster = semester ;
		this.name = name ;
		this.courseid = "CAS" + User.courseidCount++ ;
	}
	
	public Course(String name, int categoryCount) {
		this.name = name ;
		this.categoryCount = categoryCount;
		this.students = new ArrayList<Student>();
		this.categories = new ArrayList<Category>();
	}
	
	public Course(Semester semester,String name, int categoryCount) {
		this(semester,name);
		this.categoryCount = categoryCount;
		this.students = new ArrayList<Student>();
		this.categories = new ArrayList<Category>();
	}

	public Semester getSemster() {
		return semster;
	}

	public void setSemster(Semester semster) {
		this.semster = semster;
	}

	public String getName() {
		return name;
	}
	
	public int getCategoryCount() {
		return categoryCount;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public void setCategoryCount(int categoryCount) {
		this.categoryCount = categoryCount;
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
