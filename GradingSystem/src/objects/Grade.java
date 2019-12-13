package objects;

public class Grade {
	private String sid ;  // This grade belongs to student sid
	private int pid ;     // This is the grade for part pid
	private double grade ;
	private String comment;
	
	public Grade(String sid, int pid, double grade) {
		this.sid = sid;
		this.pid = pid;
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

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}


	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	
}
