package backend;

import java.sql.SQLException;
import java.util.*;

import dao.PartDao;
import objects.Category;
import objects.Part;

public class PartBackEnd {
	PartDao pd = new PartDao() ;
	ArrayList<Part> parts = new ArrayList<>() ;
	
	public ArrayList<Part> getParts(String cid) throws SQLException{
		parts = pd.getAll(cid) ;
		if(parts.size() == 0) return null ;
		return parts ;
	}
	
	public boolean addPart(Part part) throws SQLException {
		parts = getParts(part.getCid());
		if(parts == null) return false ;
		else {
			pd.insert(part);
			return true ;
		}
	}
	
	public Part select(String cid) throws SQLException {
		parts = pd.select(cid) ;
		if(parts.size() != 1) return null ;
		else {
			return parts.get(0) ;
		}
	}
	
	public boolean createPart(Part part) {
		try {
			pd.insert(part);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false ;
		}
		return true ;
	}
	
	public boolean deletePart(String pid) {
		return pd.delete(pid) ;
	}
	
	public boolean updatePart(Part part) {
		return pd.update(part) ;
	}
}
