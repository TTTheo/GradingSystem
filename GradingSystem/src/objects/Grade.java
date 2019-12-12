package objects;

public class Grade {
	private String sid ;
	private String pid ;
	private double grade ;
	private String comment;
	
	public Grade(String sid, String pid, double grade) {
		this.sid = sid ;
		this.pid = pid ;
		this.grade = grade ;
		this.comment=null;
	}

	public void setComment(String comment) {
		this.comment=comment;
	}
	
	public String getComment() {
		return this.comment;
	}
	
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}


	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	
}
