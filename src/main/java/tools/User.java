package tools;

public class User {
	
	private String name;
	private String birthday;
	private int region;
	private int score;
	private int betLost;
	private int betScheduled;

	
	public User(String n,String b, int r, int s,int lose, int scheduled){
		name = n;
		birthday= b;
		region = r; 
		score = s;
		betLost = lose;
		betScheduled = scheduled;
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
}
