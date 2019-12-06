package objects;

import java.util.*;

public class Course {
	private String name ;
	private String cid ;
	private int categoryCount;
	private ArrayList<Student> students ;
	private ArrayList<Category> categories ;
	
	public Course() {
		//do nothing
	}
	
	public Course(String name) {
		this();
		this.name = name ;
		this.cid = "C" + User.cidCount++ ;
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

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
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
