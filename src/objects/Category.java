package objects;

import java.util.*;

public class Category {
	private String categoryName ;//hw, midterm, quiz, etc.
	private int partNum;// 1,2,3, ...
	private String wid;
	private double percentage;
	private ArrayList<Part> partList;
	
	public Category (String categoryName, int partNum, double percentage) {
		this.categoryName = categoryName;
		this.partNum = partNum;
		this.percentage = percentage;
		this.partList = new ArrayList<Part>();
	}
	
	public String getName() {
		return this.categoryName;
	}
	
	public double getPartNum() {
		return this.partNum;
	}
	
	public double getPercentage() {
		return this.percentage;
	}
	
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	public void setPartNum(int partNum) {
		this.partNum = partNum;
	}
	
	public void addPart(Part part) {
		this.partList.add(part);
	}
}
