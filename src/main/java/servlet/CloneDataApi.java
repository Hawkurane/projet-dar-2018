package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import tools.ServerRequest;

/**
 * Servlet implementation class EchoServlet
 */
@WebServlet(
		name = "CloneDataApi",
		urlPatterns = {"/cloneData"})
public class CloneDataApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String apiUrl = "http://api.football-data.org/v2/";
	private static String apiKey = "d4ea1cbe81c243299546f1cfbfa9b211";
	private static String dateFrom = "2018-10-01";
	private static String dateTo = "2018-12-31";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");		

		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h3>");

		out.println("request:"+cloneAllFromApi());
		out.println("</h3>");
		out.println("</body></html>");
	}

	public static String cloneAllFromApi(){
		String[] competitions = {"FL1","PL","PD"};
		String response = "";
		for(String competitionCode : competitions){
			response=cloneMatchFromApi(competitionCode,dateFrom,dateTo);
			if(response!="")
				return response;
			/*if(!cloneStandingsFromApi(competitionCode))
				b = false;
			if(!cloneTeamsFromApi(competitionCode))
				b = false;*/
		}
		return response;
	}

	public static String cloneMatchFromApi(String competitionCode,String dateFrom, String dateTo){

		String sURL = apiUrl+"competitions/"+competitionCode+"/matches?dateFrom="+dateFrom+"&dateTo="+dateTo;
		try{
			URL url = new URL(sURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Auth-Token", apiKey);
			con.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
			StringBuilder sb = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			JsonParser jsonParser = new JsonParser();
			JsonObject json = jsonParser.parse(sb.toString()).getAsJsonObject(); //sb.toString();
			JsonArray jsonA =json.getAsJsonArray("matches");
			for(int i=0;i<jsonA.size();i++){
				JsonObject match = (JsonObject) jsonA.get(i);
				int id = match.get("id").getAsInt();
				String status = match.get("status").getAsString();
				String date = match.get("utcDate").getAsString();
				date = date.substring(0, 10)+" "+date.substring(11,19);
				int matchDay = match.get("matchday").getAsInt();
				String result = "DRAW";
				if(!match.getAsJsonObject("score").get("winner").isJsonNull()){
					result = match.getAsJsonObject("score").get("winner").getAsString();
				}
				if(result.equals("HOME_TEAM")) result = "WIN";
				if(result.equals("AWAY_TEAM")) result = "LOSE";
				int homeTeamGoal = 0;
				if(!match.getAsJsonObject("score").getAsJsonObject("fullTime").get("homeTeam").isJsonNull()){
					homeTeamGoal = match.getAsJsonObject("score").getAsJsonObject("fullTime").get("homeTeam").getAsInt();
				}
				int awayTeamGoal = 0;
				if(!match.getAsJsonObject("score").getAsJsonObject("fullTime").get("homeTeam").isJsonNull()){
					awayTeamGoal = match.getAsJsonObject("score").getAsJsonObject("fullTime").get("awayTeam").getAsInt();
				}
				//int awayTeamGoal = match.get("awayTeam").getAsInt();
				int homeTeamId = match.getAsJsonObject("homeTeam").get("id").getAsInt();
				int awayTeamId = match.getAsJsonObject("homeTeam").get("id").getAsInt();
				String league = json.getAsJsonObject("competition").get("code").getAsString();
				//System.out.println("id: "+id +" status: "+status+" date: "+date+" homegoal: "+homeTeamGoal+" homeid "+homeTeamId+ " mday: "+matchDay);
				ServerRequest.insertMatch(id, matchDay, date, status,
						homeTeamId, awayTeamId, result, homeTeamGoal, awayTeamGoal,league);
			}

		}catch(Exception e){
			return e.getMessage();

		}
		return "";
	}
	
	public static boolean updateMatchFromApi(String competitionCode,String dateFrom){
		String dateTo = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String sURL = apiUrl+"competitions/"+competitionCode+"/matches?status=FINISHED?dateFrom="+dateFrom+"&dateTo="+dateTo;
		try{
			URL url = new URL(sURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Auth-Token", apiKey);
			con.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
			StringBuilder sb = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			JsonParser jsonParser = new JsonParser();
			JsonObject json = jsonParser.parse(sb.toString()).getAsJsonObject(); //sb.toString();
			JsonArray jsonA =json.getAsJsonArray("matches");
			for(int i=0;i<jsonA.size();i++){
				JsonObject match = (JsonObject) jsonA.get(i);
				int id = match.get("id").getAsInt();
				String status = match.get("status").getAsString();
				String result = match.getAsJsonObject("score").get("winner").getAsString();
				if(result.equals("HOME_TEAM")) result = "WIN";
				if(result.equals("AWAY_TEAM")) result = "LOSE";
				int homeTeamGoal = match.getAsJsonObject("score").getAsJsonObject("fullTime").get("homeTeam").getAsInt();
				int awayTeamGoal = match.getAsJsonObject("score").getAsJsonObject("fullTime").get("awayTeam").getAsInt();

				ServerRequest.updateMatch(id, status, result, homeTeamGoal, awayTeamGoal);
			}

		}catch(Exception e){
			return false;

		}
		return true;
	}

	public static boolean cloneStandingsFromApi(String competitionCode){
		String sURL = apiUrl+"competitions/"+competitionCode+"/standings";
		// Connect to the URL using java's native library
		try{
			URL url = new URL(sURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Auth-Token", apiKey);
			con.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
			StringBuilder sb = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			JsonParser jsonParser = new JsonParser();
			JsonObject json = jsonParser.parse(sb.toString()).getAsJsonObject(); //sb.toString();
			JsonArray table =json.getAsJsonArray("standings").get(0).getAsJsonObject().get("table").getAsJsonArray();
			for(int i=0;i<table.size();i++){
				JsonObject standing = (JsonObject) table.get(i);
				int position = standing.get("position").getAsInt();
				int id = standing.get("team").getAsJsonObject().get("id").getAsInt();
				int playedGames = standing.get("playedGames").getAsInt();
				int won = standing.get("won").getAsInt();
				int draw = standing.get("draw").getAsInt();
				int lost = standing.get("lost").getAsInt();
				int points = standing.get("points").getAsInt();
				int goalsFor= standing.get("goalsFor").getAsInt();
				int goalsAgainst = standing.get("goalsAgainst").getAsInt();
				int goalDifference = standing.get("goalDifference").getAsInt();
				ServerRequest.insertStanding(id, competitionCode, playedGames
						, won, draw, lost, points, goalsFor, goalsAgainst, goalDifference,position);

			}

		}catch(Exception e){
			return false;

		}
		return true;
	}
	
	public static boolean updateStandingsFromApi(String competitionCode){
		String sURL = apiUrl+"competitions/"+competitionCode+"/standings";
		// Connect to the URL using java's native library
		try{
			URL url = new URL(sURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Auth-Token", apiKey);
			con.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
			StringBuilder sb = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			JsonParser jsonParser = new JsonParser();
			JsonObject json = jsonParser.parse(sb.toString()).getAsJsonObject(); //sb.toString();
			JsonArray table =json.getAsJsonArray("standings").get(0).getAsJsonObject().get("table").getAsJsonArray();
			for(int i=0;i<table.size();i++){
				JsonObject standing = (JsonObject) table.get(i);
				int position = standing.get("position").getAsInt();
				int id = standing.get("team").getAsJsonObject().get("id").getAsInt();
				int playedGames = standing.get("playedGames").getAsInt();
				int won = standing.get("won").getAsInt();
				int draw = standing.get("draw").getAsInt();
				int lost = standing.get("lost").getAsInt();
				int points = standing.get("points").getAsInt();
				int goalsFor= standing.get("goalsFor").getAsInt();
				int goalsAgainst = standing.get("goalsAgainst").getAsInt();
				int goalDifference = standing.get("goalDifference").getAsInt();
				ServerRequest.updateStanding(id, competitionCode, playedGames
						, won, draw, lost, points, goalsFor, goalsAgainst, goalDifference,position);

			}

		}catch(Exception e){
			return false;

		}
		return true;
	}

	public static boolean cloneTeamsFromApi(String competitionCode){
		String sURL = apiUrl+"competitions/"+competitionCode+"/standings";
		// Connect to the URL using java's native library
		try{
			URL url = new URL(sURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Auth-Token", apiKey);
			con.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
			StringBuilder sb = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			JsonParser jsonParser = new JsonParser();
			JsonObject json = jsonParser.parse(sb.toString()).getAsJsonObject(); //sb.toString();
			JsonArray table =json.getAsJsonArray("standings").get(0).getAsJsonObject().get("table").getAsJsonArray();
			for(int i=0;i<table.size();i++){
				JsonObject standing = (JsonObject) table.get(i);
				int id = standing.get("team").getAsJsonObject().get("id").getAsInt();
				String name = standing.get("team").getAsJsonObject().get("name").getAsString();

				String imgUrl = standing.get("team").getAsJsonObject().get("crestUrl").getAsString();
				ServerRequest.insertTeam(id,name,imgUrl);

			}

		}catch(Exception e){
			return false;

		}
		return true;
	}



	

}
