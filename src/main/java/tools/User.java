package tools;

public class User {
	
	private String name;
	private String birthday;
	private int region;
	private int score;
	private int betLost;
	private int betScheduled;
	private int rank;

	
	public User(String n,String b, int r, int s,int lose, int scheduled,int rank){
		name = n;
		birthday= b;
		region = r; 
		score = s;
		betLost = lose;
		betScheduled = scheduled;
		this.rank = rank;
	}
	
	public User(String n,int rank, int score){
		this.name = n;
		this.rank = rank;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}


	public String getBirthday() {
		return birthday;
	}


	public int getRegion() {
		return region;
	}


	public int getScore() {
		return score;
	}
	
	public int getBetLost() {
		return betLost;
	}

	public int getBetScheduled() {
		return betScheduled;
	}
	
	public int getRank() {
		return rank;
	}
}
