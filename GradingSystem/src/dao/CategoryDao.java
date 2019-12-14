package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import objects.*;

public class CategoryDao extends Dao<Category> {
	
	private PartDao pd = new PartDao() ;

	@Override
    public ArrayList<Category> executeQuery(String query) throws SQLException {
        ArrayList<Category> cats = new ArrayList<>();
        try (
                Connection conn = getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
        ) {
            while (rs.next()) {
            	int cid = rs.getInt("cid") ;
            	String name = rs.getString("name") ;
            	String courseid = rs.getString("course_id") ;
            	double percentage = rs.getDouble("percentage") ;
				ArrayList<Part> parts = pd.getAll(cid);
				Category cat = new Category(name, parts.size(), courseid, percentage) ;
				cat.setCid(cid);
				cat.setPartList(parts);
                cats.add(cat);
            }
        }
        return cats;
    }

    @Override
    public void insert(Category category) throws SQLException {
        String query = String.format(
                "INSERT INTO Category (name, percentage, course_id) VALUES ('%s',%f,'%s')",
                category.getName(),
                category.getPercentage(),
                category.getCourseId()
        );

		int cid = executeUpdate(query);
		category.setCid(cid);
	}

	public ArrayList<Category> getAll(String courseid) throws SQLException {
        String query = String.format(
        		"SELECT * FROM Category WHERE course_id = '%s'",
        		courseid
		);
        return executeQuery(query);
    }

    // Get a specific category if you know the ID
    public ArrayList<Category> getById(int cid) throws SQLException {
    	String query = String.format(
        		"SELECT * FROM Category WHERE cid = %d",
        		cid
		);
        return executeQuery(query);
    }
    
    public boolean delete(Category c) {
    	String query = String.format(
        		"DELETE FROM Category WHERE cid = %d",
        		c.getCid()
		);
    	try {
    		executeUpdate(query);
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return false ;
    	}
    	return true ;
    }
    
    public boolean update(Category category) {
    	String query = String.format(
        		"UPDATE Category SET name = '%s', percentage = '%f' WHERE cid = %d",
        		category.getName(),
        		category.getPercentage(),
        		category.getCid()
		);
    	try {
    		executeUpdate(query);
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return false ;
    	}
    	return true ;
    }
}
