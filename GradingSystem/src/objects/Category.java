package objects;

import java.util.*;

public class Category {
	private int cid;
	private String name ;//hw, midterm, quiz, etc.
	private int partNum;// 1,2,3, ...
	private String courseId;
	private double percentage;
	private ArrayList<Part> partList;

	// cid gets set when you insert into the database.
	public Category (String name, int partNum, String courseId, double percentage) {
		this.name = name;
		this.partNum = partNum;
		this.courseId = courseId;
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

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
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
		this.partNum ++;
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
