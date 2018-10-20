package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.google.gson.JsonObject;

public class ServerRequest {

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
						int homeTeamId, int awayTeamId,String winner,int homeTeamg,int awayTeamg)throws SQLException{
		time = time.substring(0, 10)+" "+time.substring(11,19);
		String request = "INSERT INTO matches values ("+matchId+","+homeTeamg+","+
						awayTeamg+","+"timestamp'"+time+"',"+matchDay+","+
						homeTeamId+","+awayTeamId+",'"+status+"','"+winner+"');";
		int res = makeUpdate(request);
		return (res==1);
	}
	
	public static boolean createAccount(String newusername,String password,Date date,int region) throws SQLException{
		String request = "INSERT INTO users ('"+newusername+
						","+password+","+date.toString()+region+";";
		int res = makeUpdate(request);
		return (res==1);
		
	}


	public static boolean login(String username,String password) throws SQLException{
		String loginRequest = "SELECT password FROM users WHERE name='"+username+"';";
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
								"FROM users"+
								"WHERE name = '"+username+"';";
		String pointRequest = "SELECT sum(odd) AS(score)"+
								"FROM bets b,matchs m"+
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
		String request = "SELECT name FROM users WHERE name = '"+newName+"';";
		ResultSet res = makeRequest(request);
		return (res.getFetchSize()!=0);
		
	}
	

	

}
