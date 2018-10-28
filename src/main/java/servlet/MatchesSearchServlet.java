package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * envoie une hashmap form qui retourne l'id de chaque champ avec la liste
	 * des valeurs possible.
	 * Si la requete contient des parametres, alors retourne la reponse a celle ci aussi
	 * 
	 * (requete pour avoir la liste des matchs pour l'instant)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//init form
		String[] matchDay = new String[nbrMatchDay];
		for(int i=0;i<nbrMatchDay;i++)
			matchDay[i] = ""+i;
		Map<String,String[]> form = new HashMap<String,String[]>();
		form.put("matchday", matchDay);
		form.put("status", status);
		form.put("teamName", teamsName);
		form.put("league", Utils.getLeaguesNames());

		if(!request.getParameterMap().isEmpty()){
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
			}catch(Exception e){listMatches=null;}
			request.setAttribute("listMatches", listMatches);
		}

		request.setAttribute("form", form );
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

}
