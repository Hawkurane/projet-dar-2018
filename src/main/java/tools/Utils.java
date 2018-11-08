package tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Utils {

	private static final Map<String,String> leagueNames = createMapLeagues();
	public static final int nbrMatchDay = 38;
	public static final String[] status = {"SCHEDULED","FINISHED","IN_LIVE"};
	//current league available
	public static final String[] leaguesAvailable = {"FL1","PL","PD"};
	private static Map<String,String> createMapLeagues(){
		Map<String,String> m = new HashMap<String,String>();
		m.put("FL1", "Ligue 1");
		m.put("PL", "Premier League");
		m.put("SA", "Serie A");
		m.put("PD", "La Liga");
		return Collections.unmodifiableMap(m);
	}

	public static String[] getLeaguesId(){
		return leagueNames.keySet().toArray(new String[leagueNames.size()]);
	}

	public static String[] getLeaguesNames(){
		String[] names = new String[leagueNames.size()];
		int i=0;
		for (String key: leagueNames.keySet()) {
			names[i]=leagueNames.get(key);i++;
		}
		return names;
	}
	
	public static String getLeagueIdFromName(String leagueName){
		for (String key: leagueNames.keySet()) {
			if(leagueNames.get(key).equals(leagueName))
				return key;
		}
		return "";
	}

	public static User getProfil(ResultSet profilRequest) throws SQLException{
		ResultSet res = profilRequest;
			res.next();
			return new User(res.getString(UsersBase.NAME),res.getTimestamp(UsersBase.BIRTHDAY).toString()
					,res.getInt(UsersBase.REGION),res.getInt("score")
					,res.getInt("betlost"),res.getInt("betscheduled"),res.getInt("rank"));
		

	}
	
	public static Standing getStanding(ResultSet standingRequest) throws SQLException{
		ResultSet res = standingRequest;
			res.next();
			return new Standing(res.getString(TeamsBase.TEAM_NAME), res.getString(TeamsBase.TEAM_LOGO),
					res.getInt(StandingsBase.POINTS), res.getInt(StandingsBase.PLAYED_GAMES), 
					res.getInt(StandingsBase.WON), res.getInt(StandingsBase.LOST), res.getInt(StandingsBase.DRAW),
					res.getInt(StandingsBase.GOALS_FOR), res.getInt(StandingsBase.GOALS_AGAINST), res.getString(StandingsBase.LEAGUE),
					res.getInt(StandingsBase.GOAL_DIFF), res.getInt(StandingsBase.POSIITON));

	}
	

	public static Match[] getMatches(ResultSet matchesRequest) throws SQLException{

			ResultSet res = matchesRequest;
			res.last();
			Match[] matches = new Match[res.getRow()];
			res.beforeFirst();

			while(res.next()){
				int id = Integer.parseInt(res.getString(MatchesBase.MATCH_ID));
				int day = Integer.parseInt(res.getString(MatchesBase.DAY));
				String t = res.getString(MatchesBase.START);
				String status = res.getString(MatchesBase.STATUS);
				String homename =res.getString("home"+TeamsBase.TEAM_NAME);
				String awayname =res.getString("away"+TeamsBase.TEAM_NAME);
				String winner = res.getString(MatchesBase.RESULT);
				int homeg = Integer.parseInt(res.getString(MatchesBase.HOMETEAM_GOAL));
				String homeUrl = res.getString("home"+TeamsBase.TEAM_LOGO);
				int awayg = Integer.parseInt(res.getString(MatchesBase.AWAYTEAM_GOAL));
				String awayUrl = res.getString("away"+TeamsBase.TEAM_LOGO);
				String league = leagueNames.get(res.getString(MatchesBase.LEAGUE));
				String bet = res.getString(BetsBase.BET);
				Match m = new Match(id,day, t, status, homename, homeUrl, awayname, awayUrl, winner, homeg, awayg, league,bet);
				
				matches[res.getRow()-1] = m;
			}
			return matches;

	}

	public static String[] getAllTeamsName(){
		String[] listTeamsName = null;
		try{
			ResultSet res = ServerRequest.getAllTeamsName();
			res.last();
			listTeamsName = new String[res.getRow()];
			res.beforeFirst();
			while(res.next())
				listTeamsName[res.getRow()-1] = res.getString(TeamsBase.TEAM_NAME);

		} catch (SQLException e){}

		return listTeamsName;
	}

}
