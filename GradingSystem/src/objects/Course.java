package objects;

import java.util.*;

public class Course {
	private String name ;
	private String courseid ;
	private List<Student> students ;
	private List<Category> categories ;
	
	public Course(String name) {
		this.name = name ;
		this.courseid = "C" + User.courseidCount++ ;
	}
	
	public Course(String name, List<Student> students, List<Category> categories) {
		this.name = name ;
		this.courseid = "C" + User.cidCount++ ;
		this.students = students ;
		this.categories = categories ;
	}

	public String getName() {
		return name;
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	
	
	
}
