package tools;


public class Standing {
	private String teamName;
	private String teamUrl;
	private int points;
	private int playedgames;
	private int won;
	private int lost;
	private int draw;
	private int goalsFor;
	private int goalsAgainst;
	private String league;
	private int goalDifference;
	private int position;
	
	public Standing(String teamName, String teamUrl, int points, int playedgames, int won, int lost, int draw,
			int goalsFor, int goalsAgainst, String league, int goalDifference, int position) {
		super();
		this.teamName = teamName;
		this.teamUrl = teamUrl;
		this.points = points;
		this.playedgames = playedgames;
		this.won = won;
		this.lost = lost;
		this.draw = draw;
		this.goalsFor = goalsFor;
		this.goalsAgainst = goalsAgainst;
		this.league = league;
		this.goalDifference = goalDifference;
		this.position = position;
	}
	
	public String getTeamName() {
		return teamName;
	}
	public String getTeamUrl() {
		return teamUrl;
	}
	public int getPoints() {
		return points;
	}
	public int getPlayedgames() {
		return playedgames;
	}
	public int getWon() {
		return won;
	}
	public int getLost() {
		return lost;
	}
	public int getDraw() {
		return draw;
	}
	public int getGoalsFor() {
		return goalsFor;
	}
	public int getGoalsAgainst() {
		return goalsAgainst;
	}
	public String getLeague() {
		return league;
	}
	public int getGoalDifference() {
		return goalDifference;
	}
	public int getPosition() {
		return position;
	}
	

}
