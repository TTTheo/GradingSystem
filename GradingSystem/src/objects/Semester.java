package objects;

import java.util.*;

public class Semester {
    private int semesterId;
    private String term;
    private Integer year;
    private List<Course> courses ;

    public static final String[] TERMS = new String[] {"SPRING","SUMMER","FALL","WINTER"};

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    @Override
    public String toString() {
        return term + " " + year;
    }

    public Semester() {
        this("", 0, new ArrayList<Course>());
    }

    public Semester(String term, int year) {
        this(term, year, new ArrayList<Course>());
    }

    public Semester(String term, int year, ArrayList<Course> courses) {
        this.term = term;
        this.year = year;
        this.courses = courses;
    }

    public Integer getNumCourses () {
        return courses.size();
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public static boolean isValidTerm(String term) {
        term = term.toUpperCase();
        return Arrays.asList(TERMS).contains(term);
    }
}
