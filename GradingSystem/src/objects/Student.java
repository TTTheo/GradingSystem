package objects;

import java.util.*;

public class Student {
	private String fname ;
	private String lname ;
	private String sid ;
	private List<Course> courses ;
	
	public Student(String fname, String lname) {
		this.fname = fname ;
		this.lname = lname ;
		this.sid = "U" + User.sidCount++ ;
	}
	public Student(String fname, String lname, String sid, List<Course> courses) {
		this.fname = fname ;
		this.lname = lname ;
		this.sid = "U" + User.sidCount++ ;
		this.courses = courses ;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	
}
