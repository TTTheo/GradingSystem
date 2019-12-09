package objects;

import java.util.*;

public class Category {
	private String name ;//hw, midterm, quiz, etc.
	private int partNum;// 1,2,3, ...
	private String courseid ;
	private String cid;
	private double percentage;
	private ArrayList<Part> partList;
	
	public Category (String name, int partNum, String couseid, double percentage) {
		this.name = name;
		this.partNum = partNum;
		this.courseid = couseid ;
		this.cid = "C" + User.cidCount++ ;
		this.percentage = percentage;
		this.partList = new ArrayList<Part>(partNum);
	}
	
	public Category (String name, int partNum, String cid,String courseid, double percentage) {
		this.name = name;
		this.partNum = partNum;
		this.courseid = courseid ;
		this.cid = cid ;
		this.percentage = percentage;
		this.partList = new ArrayList<Part>(partNum);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPartNum() {
		return partNum;
	}

	public void setPartNum(int partNum) {
		this.partNum = partNum;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public void addPart(Part part) {
		this.partList.add(part) ;
	}
	
	public Part getPartAt(int index) {
		return partList.get(index);
	}

	public ArrayList<Part> getPartList() {
		return partList;
	}

	public void setPartList(ArrayList<Part> partList) {
		this.partList = partList;
	}	
	
}
