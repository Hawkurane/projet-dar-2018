package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tools.Match;
import tools.ServerRequest;
import tools.Utils;

@WebServlet(
		name = "SearchServlet",
		urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String VUE  = "src/main/webapp/bootstrap/js/search.js";
	public static final String searchMatches = "matches";
	public static final String searchBets = "bets";
	public static final String searchType = "searchType";

	/*
	 * redirige vers le .jsp avec form =>id de chaque champ avec la liste
	 * des valeurs possible.
	 * 
	 * si il y a des parametres, retourne un json contenant la liste des reponses
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		if(request.getParameter(searchType).equals(searchMatches))
			sendMatches(request,response);

	}
	
	public void sendMatches(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String day = request.getParameter("matchday");
		if(day.isEmpty())day="0";
		String league = request.getParameter("league");
		if(league=="")league=null;
		String teamName = request.getParameter("temaname");
		if(teamName=="")teamName=null;
		String status = request.getParameter("status");
		if(status=="")status=null;
		Match[] listMatches;
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try{
			listMatches = Utils.getMatches(
					ServerRequest.getMatches(Integer.parseInt(day), league, teamName, status));

			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			out.write(gson.toJson(listMatches));
		}catch(Exception e){}
	}

}
