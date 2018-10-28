package tools;

public class User {
	
	private String name;
	private String birthday;
	private int region;
	private int score;

	
	public User(String n,String b, int r, int s){
		name = n;
		birthday= b;
		region = r; 
		score = s;
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
}
