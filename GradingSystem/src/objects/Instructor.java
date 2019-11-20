package objects;

import java.util.List;

public class Instructor {
	private String fname ;
	private String lname ;
	private String gender ;
	private String iid ;
	private List<Course> courses ;
	
	public Instructor(String fname, String lname, String gender, String iid, List<Course> courses) {
		this.fname = fname ;
		this.lname = lname ;
		this.gender = gender ;
		this.iid = iid ;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	
}