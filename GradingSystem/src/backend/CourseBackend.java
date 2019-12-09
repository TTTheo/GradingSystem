package backend;

import java.sql.SQLException;
import java.util.ArrayList;

import objects.Course;
import objects.Category;
import objects.Part;

import dao.*;

// Logic for everything specific to one course, including
// categories, parts, students, grades
public class CourseBackend {
    private CategoryDao cd = new CategoryDao();
    private PartDao pd = new PartDao();

    private Course currentCourse;

    public CourseBackend () {
        currentCourse = null;
    }

    public CourseBackend(Course course) {
        currentCourse = course;
    }

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(Course c) {
        currentCourse = c;
    }


    /* Category Backend Methods */

    // Get all categories belonging to currentCourse
    // Returns null if course has no categories
    public ArrayList<Category> getCourseCategories() throws SQLException {
        ArrayList<Category> categories = cd.getAll(currentCourse.getCourseid());
        if (categories.size() == 0) {
            return null;
        }
        return categories;
    }

    // Adds one category (and all its parts, if any) to the currentCourse
    // Returns true if successfully added, false if category already exists
    public boolean addCategory(Category category) throws SQLException {
        // TODO: add check if category already exists
        cd.insert(category);

        ArrayList<Part> parts = category.getPartList();
        if (parts != null && parts.size() != 0) {
            PartDao pd = new PartDao();
            for (Part part : parts) {
                pd.insert(part);
            }
        }
        return true;
    }


    /* Part Backend Methods */

    // Get all parts belonging to currentCourse
    // Returns null if course has no parts
    public ArrayList<Part> getCourseParts() throws SQLException {
        ArrayList<Part> parts = pd.getAll(currentCourse.getCourseid());
        if (parts.size() == 0) {
            return null;
        }
        return parts;
    }

    // Adds a part to current course, returns true if successful,
    // returns false if part already exists
    public boolean addPart(Part part) throws SQLException {
        // TODO: add check if already exists
        pd.insert(part);
        return true;
    }

    public boolean deletePart(Part part) {
        return pd.delete(part.getPid());
    }

    public boolean updatePart(Part part) {
        return pd.update(part);
    }
}
