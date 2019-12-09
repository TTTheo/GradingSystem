package backend;

import dao.SemesterDao;
import objects.Semester;
import objects.User;
import objects.Course;

import java.sql.SQLException;
import java.util.ArrayList;

// Logic for everything course related, including
// semesters, courses, categories, and parts
public class SemesterBackend {
    private SemesterDao semesterDao = new SemesterDao();

    // Current user would be used to show info specific to each user
    private User currentUser;
    private Semester currentSemester;

    // Temporary constructor until gui is fixed
    public SemesterBackend() {
        this.currentUser = null;
        this.currentSemester = null;
    }

    public SemesterBackend(User currentUser) {
        this.currentUser = currentUser;
    }

    public Semester getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(Semester currentSemester) {
        this.currentSemester = currentSemester;
    }

    public void addSemester(Semester s) throws SQLException {
        semesterDao.insert(s);
    }

    public ArrayList<Semester> getAllSemesters() throws SQLException {
        return semesterDao.getAll();
    }

    // Get all courses in currectSemester
    public ArrayList<Course> getCourses() throws SQLException {
        throw new SQLException("Not implemented yet");
    }
}
