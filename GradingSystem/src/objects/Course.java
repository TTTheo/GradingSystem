package objects;

import java.util.*;

public class Course {
	private String name ;
	private String cid ;
	private List<Student> students ;
	private List<Category> categories ;
	
	public Course(String name) {
		this.name = name ;
		this.cid = "C" + User.cidCount++ ;
	}
	
	public Course(String name, List<Student> students, List<Category> categories) {
		this.name = name ;
		this.cid = "C" + User.cidCount++ ;
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
