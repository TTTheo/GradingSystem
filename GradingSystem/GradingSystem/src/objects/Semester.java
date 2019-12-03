package objects;

public class Semester {
    private String term;
    private int year;

    public Semester() {
        term = "";
        year = 0;
    }

    public Semester(String term, int year) {
        this.term = term;
        this.year = year;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
