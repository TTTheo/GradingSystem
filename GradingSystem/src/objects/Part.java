package objects;

public class Part {
	private String partName;
	private double totalScore;
	private double percentage;
	
	public Part(String partName, double totalScore, double percentage) {
		this.partName = partName;
		this.totalScore = totalScore;
		this.percentage = percentage;
	}
	
	public String getName() {
		return this.partName;
	}
	
	public double getScore() {
		return this.totalScore;
	}
	
	public double getPercentage() {
		return this.percentage;
	}
	
	public void setScore(double score) {
		this.totalScore = score;
	}
	
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
}