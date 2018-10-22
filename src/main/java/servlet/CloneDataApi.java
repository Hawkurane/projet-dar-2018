package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");		
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h3>");

		out.println("request:"+cloneAllMatchFromApi());
		out.println("</h3>");
		out.println("</body></html>");
	}

	public static boolean cloneAllMatchFromApi(){
		String[] competitions = {"FL1","PL","PD"};
		boolean b = true;
		for(String competitionCode : competitions){
			if(!cloneMatchFromApi(competitionCode))
				b = false;
		}
		return b;
	}

	public static boolean cloneMatchFromApi(String competitionCode){

		String sURL = apiUrl+"competitions/"+competitionCode+"/matches?dateFrom=2018-10-01&dateTo=2018-12-31";
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
			JsonArray jsonA =json.getAsJsonArray("matches");
			for(int i=0;i<1;i++){
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
				int homeTeamGoal = 0;
				if(!match.getAsJsonObject("score").getAsJsonObject("fullTime").get("homeTeam").isJsonNull()){
					homeTeamGoal = match.getAsJsonObject("score").getAsJsonObject("fullTime").get("homeTeam").getAsInt();
				}
				int awayTeamGoal = 0;
				if(!match.getAsJsonObject("score").getAsJsonObject("fullTime").get("homeTeam").isJsonNull()){
					homeTeamGoal = match.getAsJsonObject("score").getAsJsonObject("fullTime").get("awayTeam").getAsInt();
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
			return false;
			
		}
		return true;
	}


}
