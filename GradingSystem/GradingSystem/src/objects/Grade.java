package objects;

public class Grade {
	private String wid ;
	private String sid ;
	private String cid ;
	private double grade ;
	
	public Grade(String wid, String sid, String cid, double grade) {
		this.wid = wid ;
		this.sid = sid ;
		this.cid = cid ;
		this.grade = grade ;
	}

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	
}
