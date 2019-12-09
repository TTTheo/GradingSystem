package backend;

import java.sql.SQLException;
import java.util.ArrayList;

//import objects.Course;
//import objects.Category;
//import objects.Part;

import dao.SemesterDao;
import objects.Semester;
import objects.User;


// Logic for everything course related, including
// semesters, courses, categories, and parts
public class SemesterBackend {
    private SemesterDao semesterDao = new SemesterDao();

    private Semester currentSemester;
    

    public Semester getCurrentSemester() {
        return currentSemester;
    }

    public void addSemester(Semester s) throws SQLException {
        semesterDao.insert(s);
    }

    public void setCurrentSemester(Semester currentSemester) {
        this.currentSemester = currentSemester;
    }

    public ArrayList<Semester> getAllSemesters() throws SQLException {
        return semesterDao.getAll();
    }

}
