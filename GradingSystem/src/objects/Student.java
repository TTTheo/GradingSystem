package objects;

import java.util.*;

public class Student {
	private String sid ;
	private String fname ;
	private String lname ;

	private String email;
	private List<Course> courses ;

	public Student(String fname, String lname, String sid) {
		this.fname = fname ;
		this.lname=lname;
	    this.sid = sid;
	}

	public Student(String fname, String lname, String sid, String email) {
		this(fname, lname, sid);
		this.email = email;
	}

	public Student(String fname, String lname, String sid, String email, List<Course> courses) {
	    this(fname, lname, sid, email);
		this.courses = courses;
	}

    public Student(String fname) {
		this(fname, "", "" );
		this.courses = new ArrayList<>();
    }

    public void addCourse(Course c) {
		courses.add(c);
	}

    public String toString() {
		return fname + " " + lname ;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
