package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;



public class ServerRequest {
	/*public static final String USERS_BASE = "users";
	public static final String MATCHS_BASE = "matches";
	public static final String BETS_BASE = "bets";
	public static final String STANDINGS_BASE = "standings";
	public static final String TEAMS_BASE = "teams";*/
	

	private static Connection getConnection()throws SQLException{
		String dbUrl = System.getenv("JDBC_DATABASE_URL");
		return DriverManager.getConnection(dbUrl);

	}

	private synchronized static ResultSet makeRequest(String request) throws SQLException {
		Connection connexionDataBase = getConnection();
		Statement stmt = connexionDataBase.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);
		ResultSet res = stmt.executeQuery(request);
		connexionDataBase.close();
		return res;

	}

	private synchronized static int makeUpdate(String request) throws SQLException {
		Connection connexionDataBase = getConnection();
		Statement stmt = connexionDataBase.createStatement();
		int i = stmt.executeUpdate(request);
		connexionDataBase.close();
		return i;
	}
	
	public static ResultSet getUsers(String username, String research) throws SQLException {
		String request ="" ;
		//si username!= null on reserche dans la liste d'ami de username
		if(username!=null){
			request+="SELECT u."+UsersBase.NAME+" FROM "
					+UsersBase.BASENAME+" u, "+FollowsBase.BASENAME+" f"
					+" WHERE "+" f."+FollowsBase.FOLLOW+" = u."+UsersBase.NAME
					+"AND f."+FollowsBase.NAME+" = '"+username+"'";
			if(research!=null)
				request+=" AND f."+FollowsBase.FOLLOW+" LIKE '"+research+"'";
			request+=";";
		//sinon on recher dans toute la liste des users
		}else{
			request+="SELECT "+UsersBase.NAME+" FROM "+UsersBase.BASENAME+"WHERE "
					+UsersBase.NAME+" LIKE '"+research+"';";
		}
		ResultSet res = makeRequest(request);
		return res;
	}

	public static ResultSet getProfil(String username)throws SQLException{
		String pointRequest = "SELECT count(*) AS score"+
				"FROM "+BetsBase.BASENAME+" b, "+MatchesBase.BASENAME+" m"+
				"WHERE b."+BetsBase.MATCH_ID+" = m."+MatchesBase.MATCH_ID+"+ AND"+
				"b."+BetsBase.GAMBLER+" ='"+username+"' AND"+
				"m."+MatchesBase.STATUS+" ='FINISHED' AND"+
				"b."+BetsBase.BET+" = m."+MatchesBase.RESULT;
		String profilRequest = "SELECT "+UsersBase.NAME+", "+UsersBase.BIRTHDAY
				+", "+UsersBase.REGION+",("+pointRequest+") as score"+
				"FROM "+UsersBase.BASENAME +
				"WHERE "+UsersBase.NAME+" = '"+username+"';";

		ResultSet res = makeRequest(profilRequest);
		return res;

	}

	//parameters == null if no condition 
	public static ResultSet getBets(String username,String won,String status,
			int matchDay,String league,String teamName)throws SQLException{

		String request = "SELECT m.*,t1."+TeamsBase.TEAM_NAME+" AS "+"home"+TeamsBase.TEAM_NAME
				+",t1."+TeamsBase.TEAM_LOGO+" AS "+"home"+TeamsBase.TEAM_LOGO
				+",t2."+TeamsBase.TEAM_NAME+" AS "+"away"+TeamsBase.TEAM_NAME
				+",t2."+TeamsBase.TEAM_LOGO+" AS "+"away"+TeamsBase.TEAM_LOGO
				+",b."+BetsBase.BET+" "+
				"FROM (("+"("+BetsBase.BASENAME+" b"+" INNER JOIN "+MatchesBase.BASENAME
				+" m ON b.matchid = m.matchsid )"+" INNER JOIN "+TeamsBase.BASENAME+" t1 "
				+" ON t1.id = m.hometeamid )"+" INNER JOIN "+TeamsBase.BASENAME+" t2 "
				+" ON t2.id = m.awayteamid )"
				+" WHERE b.name = '"+username+"'";
		if(won!=null){
			boolean b = Boolean.getBoolean(won);
			request +=" AND b."+BetsBase.BET+" "+(b ? "!" : "")+"= m."+MatchesBase.RESULT;
		}
		if(status !=null)
			request +="AND m."+MatchesBase.RESULT+" = '"+status+"'";
		if(matchDay>0)
			request+= " AND m."+MatchesBase.DAY+" = "+matchDay;
		if(league!=null)
			request += " AND m."+MatchesBase.LEAGUE+" = '"+league+"'";
		if(teamName!=null)
			request += "AND (t1."+TeamsBase.TEAM_NAME+" = '"+teamName+"'"
					+ " OR t2."+TeamsBase.TEAM_NAME+" = '"+teamName+"' )";


		request+=" ;";
		ResultSet res = makeRequest(request);
		return res;
	}

	//pre status!=null
	//parameters == null if no condition, matchday==0 if no condition
	public static ResultSet getMatches(int matchDay,String league,String teamName,String status,String username)
			throws SQLException{
		if(status==null)status="SCHEDULED";
		String bet = "SELECT b."+BetsBase.BET+" FROM "+BetsBase.BASENAME+" b"
				+"WHERE "+"b."+BetsBase.MATCH_ID+" = "+" m."+MatchesBase.MATCH_ID
				+" AND "+"b."+BetsBase.GAMBLER+" = '"+username+"'";
		String request =  "SELECT m.*,t1."+TeamsBase.TEAM_NAME+" AS "+"home"+TeamsBase.TEAM_NAME
				+",t1."+TeamsBase.TEAM_LOGO+" AS "+"home"+TeamsBase.TEAM_LOGO
				+",t2."+TeamsBase.TEAM_NAME+" AS "+"away"+TeamsBase.TEAM_NAME
				+",t2."+TeamsBase.TEAM_LOGO+" AS "+"away"+TeamsBase.TEAM_LOGO+" "
				+"( "+bet+" ) as "+BetsBase.BET+
				"FROM ("+"("+MatchesBase.BASENAME+" m "+" INNER JOIN "+TeamsBase.BASENAME+" t1 "
				+" ON t1.id = m.hometeamid )"+" INNER JOIN "+TeamsBase.BASENAME+" t2 "
				+" ON t2.id = m.awayteamid )"
				+" WHERE m."+MatchesBase.STATUS+" = '"+status+"'";
		if(matchDay>0)
			request+= " AND m."+MatchesBase.DAY+" = "+matchDay;
		if(league!=null)
			request += " AND m."+MatchesBase.LEAGUE+" = '"+league+"'";

		if(teamName!=null)
			request += "AND (t1."+TeamsBase.TEAM_NAME+" = '"+teamName+"'"
			+ " OR t2."+TeamsBase.TEAM_NAME+" = '"+teamName+"' )";

		request+=" ;";
		ResultSet res = makeRequest(request);
		return res;
	}

	//pre league!=null
	public static ResultSet getStandings(String league)
			throws SQLException{
		String request = "SELECT * "+
				"FROM ("+"("+StandingsBase.BASENAME+" s "+" INNER JOIN "+TeamsBase.BASENAME+" t "
				+" ON s."+StandingsBase.TEAM_ID+" = t."+TeamsBase.TEAM_ID+" )"
				+" WHERE s."+StandingsBase.LEAGUE+" = '"+league+"';";

		ResultSet res = makeRequest(request);
		return res;
	}

	public static ResultSet getAllTeamsName()throws SQLException{
		String request = "SELECT "+TeamsBase.TEAM_NAME+" FROM "+TeamsBase.BASENAME;
		ResultSet res = makeRequest(request);
		return res;
	}

	public static boolean insertMatch(int matchId, int matchDay,String time,String status,
			int homeTeamId, int awayTeamId,String winner,int homeTeamg,int awayTeamg,String league)throws SQLException{
		time = time.substring(0, 10)+" "+time.substring(11,19);
		String request = "INSERT INTO "+MatchesBase.BASENAME +" values ("+matchId+","+homeTeamg+","+
				awayTeamg+","+"timestamp'"+time+"',"+matchDay+","+
				homeTeamId+","+awayTeamId+",'"+status+"','"+winner+"','"+league+"');";
		int res = makeUpdate(request);
		return (res==1);
	}

	public static boolean updateMatch(int matchId,String status
			,String winner,int homeTeamg,int awayTeamg)throws SQLException{
		String request = "UPDATE "+MatchesBase.BASENAME +"WHERE "+
				"matchsid = "+matchId+" SET "+
				"status = '"+status+"'"+
				",hometeamgoal = "+homeTeamg+
				",awayteamgoal = "+awayTeamg+
				",result = '"+winner+"';";
		int res = makeUpdate(request);
		return (res==1);
	}

	public static boolean insertStanding(int id, String league,int playedGames,int won,int draw,
			int lost,int points,int goalsFor,int goalsAgainst,int goalDifference,int position)throws SQLException{
		String request = "INSERT INTO "+StandingsBase.BASENAME +" values ("+id+",'"+league+"',"+
				playedGames+","+won+","+draw+","+lost+","+
				goalsFor+","+goalsAgainst+","+goalDifference+","+position+");";
		int res = makeUpdate(request);
		return (res==1);
	}

	public static boolean updateStanding(int id, String league,int playedGames,int won,int draw,
			int lost,int points,int goalsFor,int goalsAgainst,int goalDifference,int position)throws SQLException{
		String request = "UPDATE "+StandingsBase.BASENAME +
				"WHERE "+"id = "+id+" AND "+" league = "+"'"+league+"'"+" SET "+
				"playedGames = "+playedGames+",won = "+won+",draw = "+draw+
				",lost = "+lost+",goalsFor = "+ goalsFor+",goalsAgainst = "+goalsAgainst+
				",goalDifference = "+goalDifference+", position = "+position+");";
		int res = makeUpdate(request);
		return (res==1);
	}

	public static boolean insertTeam(int id, String name, String imgUrl)throws SQLException{
		String request = "INSERT INTO "+TeamsBase.BASENAME +" values ("+id+",'"
				+name+"','"+imgUrl+"');";
		int res = makeUpdate(request);
		return (res==1);
	}

	public static boolean createAccount(String newusername,String password,Date date,int region) throws SQLException{
		String request = "INSERT INTO "+UsersBase.BASENAME+" values ('"+newusername+
				","+password+","+date.toString()+region+";";
		int res = makeUpdate(request);
		return (res==1);

	}
	
	public static boolean insertBet(String username,int matchId,String bet) throws SQLException{
		String request = "INSERT INTO "+BetsBase.BASENAME+" ("
				+BetsBase.GAMBLER+","+BetsBase.MATCH_ID+","+BetsBase.BET
				+"VALUES ("+username+","+matchId+","+bet+");";
		int res = makeUpdate(request);
		return (res==1);

	}

	public static ResultSet login(String username,String password) throws SQLException{
		String loginRequest = "SELECT "+UsersBase.PASSWORD+" FROM "+UsersBase.BASENAME
				+" WHERE "+UsersBase.NAME+"='"+username+"';";
		return makeRequest(loginRequest);


	}



	public static boolean existName(String newName) throws SQLException{
		String request = "SELECT "+UsersBase.NAME+" FROM "+UsersBase.BASENAME
				+" WHERE "+UsersBase.NAME+" = '"+newName+"';";
		ResultSet res = makeRequest(request);
		return (res.getFetchSize()!=0);

	}






}
