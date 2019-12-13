package objects;

import java.util.*;

public class Student {
	private String sid ;
	private String fname ;
	private String lname ;
	private List<Course> courses ;

	public Student(String fname, String sid) {
		this.fname = fname ;
		this.sid=sid;
	}

	public Student(String fname, String lname, String sid, List<Course> courses) {
	    this(fname, lname);
		this.sid = sid;
		this.courses = courses;
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
