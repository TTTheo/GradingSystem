package backend;

import java.sql.SQLException;
import java.util.*;

import dao.*;
import objects.Category;
import objects.Part;

public class CategoryBackend {
	private CategoryDao cd = new CategoryDao() ;
	private ArrayList<Category> cats ;
	
	
	public ArrayList<Category> getCategories(String courseid) throws SQLException{
		cats = new ArrayList<>() ;
		cats = cd.getAll(courseid) ;
		if(cats.size() == 0) return null ;
		return cats ;
	}
	
	public boolean addCategory(Category category) throws SQLException {
		cats = getCategories(category.getCourseid()) ;
		if(cats == null) return false ;
		else {
			cd.insert(category);
			ArrayList<Part> parts = category.getPartList() ;
			if(parts != null && parts.size() != 0) {
				PartDao pd = new PartDao() ;
				for(Part part : parts) {
					pd.insert(part);
				}
			}
			return true ;
		}
	}
	
	public Category selectCategory(String cid) throws SQLException {
		cats = cd.select(cid) ;
		if(cats.size() != 1) return null ;
		else {
			return cats.get(0) ;
		}
	}
	
	public boolean createCategory(Category category) {
		try {
			cd.insert(category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false ;
		}
		return true ;
	}
	
	public boolean deleteCategory(String cid) {
		return cd.delete(cid) ;
	}
	
	public boolean updateCategory(Category category) {
		if(cd.update(category)) {
			ArrayList<Part> parts = new ArrayList<>() ;
			if(parts != null && parts.size() != 0) {
				PartDao pd = new PartDao() ;
				for(Part part : parts) {
					 pd.update(part) ;
				}
			}
			return true ;
		}else return false ;
	}
}
