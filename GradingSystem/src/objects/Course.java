package objects;

import java.util.*;

public class Course {
	private String name ;
	private String cid ;
	private List<Instructor> instructors ; 
	private int capacity ;
	private List<Student> students ;
	private List<Category> categories ;
	
	public Course(String name, String cid, List<Instructor> instructors, int capacity, List<Student> students, List<Category> categories) {
		this.name = name ;
		this.cid = cid ;
		this.instructors = instructors ;
		this.capacity = capacity ;
		this.students = students ;
		this.categories = categories ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public List<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(List<Instructor> instructors) {
		this.instructors = instructors;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
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
