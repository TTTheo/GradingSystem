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
            	int partNum = rs.getInt("part_num") ;
            	String courseid = rs.getString("courseid") ;
            	double percentage = rs.getDouble("percentage") ;
                ArrayList<Part> parts = pd.getAll(cid) ;
                Category cat = new Category(name, partNum, courseid, percentage) ;
                cat.setPartList(parts);
                cats.add(cat);
            }
        }
        return cats;
    }

    @Override
    public void insert(Category category) throws SQLException {

        String query = String.format(
                "INSERT INTO Category (cid, name, part_num, percentage, courseid) VALUES ('%s','%s',%d,%f,'%s')",
                category.getCid(),
                category.getName(),
                category.getPartNum(),
                category.getPercentage(),
                category.getCourseid()
        );

		int cid = executeUpdate(query);
		category.setCid(cid);
	}

	public ArrayList<Category> getAll(String courseid) throws SQLException {
        String query = String.format(
        		"SELECT * FROM Category WHERE courseid = '%s'",
        		courseid
        		);
        return executeQuery(query);
    }
    
    public ArrayList<Category> select(String cid) throws SQLException {
    	String query = String.format(
        		"SELECT * FROM Category WHERE cid = '%s'",
        		cid
        		);
        return executeQuery(query);
    }
    
    public boolean delete(String cid) {
    	String query = String.format(
        		"DELETE FROM Category WHERE cid = '%s'",
        		cid
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
        		"UPDATE Category SET name = '%s', part_num = '%d', percentage = '%f' WHERE cid = '%s'",
        		category.getName(),
        		category.getPartNum(),
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
