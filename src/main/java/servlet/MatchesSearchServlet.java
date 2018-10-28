package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import tools.Match;
import tools.ServerRequest;
import tools.Utils;

@WebServlet(
		name = "MatchesSearchServlet",
		urlPatterns = {"/matches"})
public class MatchesSearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String VUE  = "src/main/webapp/bootstrap/js/matches.js";

	public static final int nbrMatchDay = 38;
	public static final String[] status = {"Terminé", "A venir"};
	public static final String[] teamsName = Utils.getAllTeamsName();


	/*
	 * redirige vers le .jsp avec form =>id de chaque champ avec la liste
	 * des valeurs possible.
	 * 
	 * si il y a des parametres, retourne un json contenant la liste des reponses
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameterMap().isEmpty()){
			//init form
			String[] matchDay = new String[nbrMatchDay];
			for(int i=0;i<nbrMatchDay;i++)
				matchDay[i] = ""+i;
			Map<String,String[]> form = new HashMap<String,String[]>();
			form.put("matchday", matchDay);
			form.put("status", status);
			form.put("teamName", teamsName);
			form.put("league", Utils.getLeaguesNames());
			request.setAttribute("form", form );
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		}
		else{
			String day = request.getParameter("matchday");
			if(day.isEmpty())day="0";
			String league = request.getParameter("league");
			if(league=="")league=null;
			String teamName = request.getParameter("temaname");
			if(teamName=="")teamName=null;
			String status = request.getParameter("status");
			if(status=="")status=null;
			Match[] listMatches;
			try{
				listMatches = Utils.getMatches(
						ServerRequest.getMatches(Integer.parseInt(day), league, teamName, status));
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				out.println("<h3>");
				Gson gson = new Gson();
				out.println("request:"+gson.toJson(listMatches));
				out.println("</h3>");
				out.println("</body></html>");
			}catch(Exception e){
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				out.println("<h3>");
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				out.println(sw.toString());
				out.println("</h3>");
				out.println("</body></html>");}
			
			
			
			//out.write(gson.toJson(listMatches));
		}


	}

}
