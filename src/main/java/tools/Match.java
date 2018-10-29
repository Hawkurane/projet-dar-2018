package tools;



public class Match {
	
	private int matchId;
	private int matchDay;
	private String time;
	private String status;
	private String homeTeamName;
	private String homeTeamLogoUrl;
	private String awayTeamName;
	private String awayTeamLogoUrl;
	private String winner;
	private int homeTeamg;
	private int awayTeamg;
	private String league;
	private String bet;
	

	
	public Match(int id,int mday,String t,String sta,
			String homeName,String homeUrl, String awayName,String awayUrl,
			String w,int homeg,int awayg,String l,String bet){
		matchId=id;
		matchDay=mday;
		time = t;
		status = sta;
		homeTeamName = homeName;
		awayTeamName = awayName;
		winner = w;
		homeTeamg = homeg;
		awayTeamg = awayg;
		league = l;
		bet = null;
	}
	
	public int getMatchId(){
		return matchId;
	}
	
	public int getMatchDay() {
		return matchDay;
	}

	public String getTime() {
		return time;
	}

	public String getStatus() {
		return status;
	}

	public String getHomeTeamName() {
		return homeTeamName;
	}

	public String getHomeTeamLogoUrl() {
		return homeTeamLogoUrl;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public String getAwayTeamLogoUrl() {
		return awayTeamLogoUrl;
	}

	public String getWinner() {
		return winner;
	}

	public int getHomeTeamg() {
		return homeTeamg;
	}

	public int getAwayTeamg() {
		return awayTeamg;
	}

	public String getLeague() {
		return league;
	}
	
	public String getBet() {
		return bet;
	}
}
