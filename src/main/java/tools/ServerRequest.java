package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.google.gson.JsonObject;

public class ServerRequest {
	public static final String USERS_BASE = "users";
	public static final String MATCHS_BASE = "matches";
	public static final String BETS_BASE = "bets";
	public static final String STANDINGS_BASE = "standings";
	public static final String TEAMS_BASE = "teams";
	Connection connection = getConnection();

	private static Connection getConnection(){
		try{
			String dbUrl = System.getenv("JDBC_DATABASE_URL");
			return DriverManager.getConnection(dbUrl);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private static ResultSet makeRequest(String request) throws SQLException {
		Connection connexionDataBase = getConnection();
		Statement stmt = connexionDataBase.createStatement();
		ResultSet res = stmt.executeQuery(request);
		return res;

	}

	private static int makeUpdate(String request) throws SQLException {
		Connection connexionDataBase = getConnection();
		Statement stmt = connexionDataBase.createStatement();
		return stmt.executeUpdate(request);
	}

	public static boolean insertMatch(int matchId, int matchDay,String time,String status,
			int homeTeamId, int awayTeamId,String winner,int homeTeamg,int awayTeamg,String league)throws SQLException{
		time = time.substring(0, 10)+" "+time.substring(11,19);
		String request = "INSERT INTO "+MATCHS_BASE +" values ("+matchId+","+homeTeamg+","+
				awayTeamg+","+"timestamp'"+time+"',"+matchDay+","+
				homeTeamId+","+awayTeamId+",'"+status+"','"+winner+"','"+league+"');";
		int res = makeUpdate(request);
		return (res==1);
	}
	
	public static boolean updateMatch(int matchId,String status
			,String winner,int homeTeamg,int awayTeamg)throws SQLException{
		String request = "UPDATE "+MATCHS_BASE +"WHERE "+
							"matchsid = "+matchId+" SET "+
							"status = '"+status+"'"+
							",hometeamgoal = "+homeTeamg+
							",awayteamgoal = "+awayTeamg+
							",result = "+winner+"';";
		int res = makeUpdate(request);
		return (res==1);
	}

	public static boolean insertStanding(int id, String league,int playedGames,int won,int draw,
			int lost,int points,int goalsFor,int goalsAgainst,int goalDifference,int position)throws SQLException{
		String request = "INSERT INTO "+STANDINGS_BASE +" values ("+id+",'"+league+"',"+
				playedGames+","+won+","+draw+","+lost+","+
				goalsFor+","+goalsAgainst+","+goalDifference+","+position+");";
		int res = makeUpdate(request);
		return (res==1);
	}
	
	public static boolean updateStanding(int id, String league,int playedGames,int won,int draw,
			int lost,int points,int goalsFor,int goalsAgainst,int goalDifference,int position)throws SQLException{
		String request = "UPDATE "+STANDINGS_BASE +
				"WHERE "+"id = "+id+" AND "+" league = "+"'"+league+"'"+" SET "+
				"playedGames = "+playedGames+",won = "+won+",draw = "+draw+
				",lost = "+lost+",goalsFor = "+ goalsFor+",goalsAgainst = "+goalsAgainst+
				",goalDifference = "+goalDifference+", position = "+position+");";
		int res = makeUpdate(request);
		return (res==1);
	}
	
	public static boolean insertTeam(int id, String name, String imgUrl)throws SQLException{
		String request = "INSERT INTO "+TEAMS_BASE +" values ("+id+",'"
									+name+"','"+imgUrl+"');";
		int res = makeUpdate(request);
		return (res==1);
	}

	public static boolean createAccount(String newusername,String password,Date date,int region) throws SQLException{
		String request = "INSERT INTO "+USERS_BASE+" ('"+newusername+
				","+password+","+date.toString()+region+";";
		int res = makeUpdate(request);
		return (res==1);

	}


	public static boolean login(String username,String password) throws SQLException{
		String loginRequest = "SELECT password FROM "+USERS_BASE+" WHERE name='"+username+"';";
		ResultSet res = makeRequest(loginRequest);
		res.next();
		if(res.getString("password").equals(password))
			return true;
		else
			return false;

	}

	public static JsonObject profil(String username)throws SQLException{
		JsonObject json= new JsonObject();
		String profilRequest = "SELECT name, age, region"+
				"FROM "+USERS_BASE +
				"WHERE name = '"+username+"';";
		String pointRequest = "SELECT sum(odd) AS(score)"+
				"FROM "+BETS_BASE+" b, "+MATCHS_BASE+" m"+
				"WHERE b.id = m.id AND"+
				"b.name ='"+username+"' AND"+
				"m.result !=NULL AND"+
				"b.result = m.result;";
		ResultSet res = makeRequest(profilRequest);
		res.next();
		json.addProperty("name", res.getString("name"));
		json.addProperty("age", res.getDate("birthday").toString());
		json.addProperty("region", res.getString("region"));
		res = makeRequest(pointRequest);
		res.next();
		json.addProperty("score", res.getInt("score"));

		return json;

	}

	public static boolean existName(String newName) throws SQLException{
		String request = "SELECT name FROM "+USERS_BASE+" WHERE name = '"+newName+"';";
		ResultSet res = makeRequest(request);
		return (res.getFetchSize()!=0);

	}

	public static boolean search(int matchDay,String league,String teamName){
		String request = "SELECT ";
		return false;
	}




}
