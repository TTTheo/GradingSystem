package objects;

public class Part {
	private String pid ;
	private String cid ;
	private String name;
	private double totalScore;
	private double percentage;
	
	public Part(String name, String cid, double totalScore, double percentage) {
		this.pid = "P" + User.pidCount++ ;
		this.cid = cid ;
		this.name = name;
		this.totalScore = totalScore;
		this.percentage = percentage;
	}
	public Part(String pid, String name, String cid, double totalScore, double percentage) {
		this.pid = pid ;
		this.cid = cid ;
		this.name = name;
		this.totalScore = totalScore;
		this.percentage = percentage;
	}
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	public double getPercentage() {
		return percentage;
	}
	
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	
}