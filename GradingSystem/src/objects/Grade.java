package objects;

public class Grade {
	private String cid ;
	private String sid ;
	private double grade ;
	
	public Grade(String cid, String sid, double grade) {
		this.sid = sid ;
		this.cid = cid ;
		this.grade = grade ;
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
