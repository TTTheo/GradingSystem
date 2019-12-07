import gui.LoginFrame;
import gui.grade.ViewGradeFrame;

import java.sql.SQLException;
import java.util.*;

import backend.*;
import dao.* ;
import objects.* ;

public class Main {
	public static void main(String[] args) throws SQLException {
		LoginFrame login = new LoginFrame();
		login.setVisible(true);
		
		
		/*
		 * test
		 */
//		CategoryBackend cb = new CategoryBackend() ;
//		System.out.println(cb.getCategories("CAS1").get(0).getName()) ;
//		PartBackEnd pb = new PartBackEnd() ;
//		System.out.println(pb.getParts("C2").get(0).getName());
//		CategoryDao cd = new CategoryDao() ;
//		cb.addCategory(new Category("assignment", 3,"TESTCAT1", "TESTCOURSE1", 0.6));
//		List<Category> cats = cd.getAll("CAS1");
//		for(Category cat : cats) {
//			for(int i = 0 ; i < cat.getPartNum() ; i++) {
//				Part part = cat.getPartList().get(i) ;
//				System.out.println("------------");
//				System.out.println(part.getName());
//				System.out.println(part.getPercentage());
//				
//			}
//		}
//		PartDao pd = new PartDao() ;
//		Part part = new Part("P3","Grade", "TESTCAT1", 100, 0.2) ;
//		pb.addPart(part) ;
		//		pd.insert(part);
	}
}
