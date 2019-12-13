package objects;

public class Part {
	private int pid;
	private int cid; // This part belongs to category cid
	private String name;
	private double totalScore;
	private double percentage;

	// pid gets set when you insert it into the database
	public Part(String name, int cid, double totalScore, double percentage) {
		this.cid = cid ;
		this.name = name;
		this.totalScore = totalScore;
		this.percentage = percentage;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
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