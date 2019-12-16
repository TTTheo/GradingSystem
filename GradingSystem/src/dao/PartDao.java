package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects.Category;
import objects.Part;
import objects.Semester;

public class PartDao extends Dao<Part>{

	@Override
    public ArrayList<Part> executeQuery(String query) throws SQLException {
        ArrayList<Part> parts = new ArrayList<>();
        try (
                Connection conn = getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
        ) {
            while (rs.next()) {
                int pid = rs.getInt("pid");
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                double total_score = rs.getDouble("total_score");
                double percentage = rs.getDouble("percentage");

                Part part = new Part(name, cid, total_score, percentage) ;
                part.setPid(pid);
                parts.add(part);
            }
        }
        return parts;
    }

    @Override
    public void insert(Part part) throws SQLException {
        String query = String.format(
                "INSERT INTO Part (cid, name, total_score, percentage) VALUES (%d,'%s', %f,%f)",
                part.getCid(),
                part.getName(),
                part.getTotalScore(),
                part.getPercentage()
        );

        int pid = executeUpdate(query);
        part.setPid(pid);
    }

    // Get all parts of a category
    public ArrayList<Part> getAll(int cid) throws SQLException {
        String query = String.format(
        		"SELECT * FROM Part WHERE cid = %d",
        		cid
        );
        return executeQuery(query);
    }

    public ArrayList<Part> getById(int pid) throws SQLException {
    	String query = String.format(
        		"SELECT * FROM Part WHERE pid = %d",
        		pid
        );
        return executeQuery(query);
    }
    
    //////////////
    public boolean delete(Part p) {
    	String query = String.format(
        		"DELETE FROM Part WHERE pid = %d",
        		p.getPid()
        );
    	try {
    		executeUpdate(query);
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return false ;
    	}
    	return true ;
    }
    
    public boolean update(Part part) {
    	String query = String.format(
        		"UPDATE Part SET name = '%s', total_score = %f, percentage = %f WHERE pid = %d",
        		part.getName(),
        		part.getTotalScore(),
        		part.getPercentage(),
        		part.getPid()
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
