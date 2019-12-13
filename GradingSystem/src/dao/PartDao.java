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
                "INSERT INTO Part (pid, cid, name, total_score, percentage) VALUES ('%s','%s','%s', %f,%f)",
                part.getPid(),
                part.getCid(),
                part.getName(),
                part.getTotalScore(),
                part.getPercentage()
        );

        int pid = executeUpdate(query);
        part.setPid(pid);
    }

    public ArrayList<Part> getAll(int cid) throws SQLException {
        String query = String.format(
        		"SELECT * FROM Part WHERE cid = '%s'",
        		cid
        	);
        return executeQuery(query);
    }

    public ArrayList<Part> select(String pid) throws SQLException {
    	String query = String.format(
        		"SELECT * FROM Part WHERE pid = '%s'",
        		pid
        		);
        return executeQuery(query);
    }
    
    public boolean delete(String pid) {
    	String query = String.format(
        		"DELETE FROM Part WHERE pid = '%s'",
        		pid
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
        		"UPDATE Part SET name = '%s', total_score = '%f', percentage = '%f' WHERE pid = '%s'",
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
