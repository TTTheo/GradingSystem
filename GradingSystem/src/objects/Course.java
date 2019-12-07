package objects;

import java.util.*;

public class Course {
	private String name ;
	private String courseid ;
	private int categoryCount;
	private ArrayList<Student> students ;
	private ArrayList<Category> categories ;
	
	public Course() {
		//do nothing
	}
	
	public Course(String name) {
		this();
		this.name = name ;
		this.courseid = "CAS" + User.courseidCount++ ;
	}
	
	public Course(String name, int categoryCount) {
		this(name);
		this.categoryCount = categoryCount;
		this.students = new ArrayList<Student>();
		this.categories = new ArrayList<Category>();
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void addCategory(Category category) {
		this.categories.add(category);
	}

	
	
	
}
