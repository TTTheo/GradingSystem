package objects;

import java.util.*;

public class College {
	private String name ;
	private List<Course> allCourses ;
	private List<Instructor> allInstructors ;
	// Semester ?
	
	public College(List<Course> allCourses, List<Instructor> allInstructors) {
		this.name = "CAS" ;
		this.allCourses = allCourses ;
		this.allInstructors = allInstructors ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getAllCourses() {
		return allCourses;
	}

	public void setAllCourses(List<Course> allCourses) {
		this.allCourses = allCourses;
	}

	public List<Instructor> getAllInstructors() {
		return allInstructors;
	}

	public void setAllInstructors(List<Instructor> allInstructors) {
		this.allInstructors = allInstructors;
	}
	
	
}
