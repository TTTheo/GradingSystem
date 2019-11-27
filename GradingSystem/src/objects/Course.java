package objects;

import java.util.*;

public class Course {
	private String name ;
	private String cid ;
	private List<Instructor> instructors ; 
	private int capacity ;
	private List<Student> students ;
	private List<Work> works ;
	
	public Course(String name, String cid, List<Instructor> instructors, int capacity, List<Student> students, List<Work> works) {
		this.name = name ;
		this.cid = cid ;
		this.instructors = instructors ;
		this.capacity = capacity ;
		this.students = students ;
		this.works = works ;
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

	public List<Work> getWorks() {
		return works;
	}

	public void setWorks(List<Work> works) {
		this.works = works;
	}
	
	
}
