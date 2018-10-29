package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tools.Match;
import tools.ServerRequest;
import tools.User;
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

	//la servlet retourne le json correspondant a la recherche effectue
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		//la difference entre les deux: searchmatches retourne TOUT les matchs selon les criteres,
		//								searchbets retourne les PARIS de l'utilisateur selon les criteres
		if(request.getParameter(searchType).equals(searchMatches))
			sendMatches(request,response);
		else if(request.getParameter(searchType).equals(searchBets))
			sendMatches(request,response);
	}
	
	public void sendMatches(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)(request.getSession(false).getAttribute(LoginServlet.ATT_USER));
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
					ServerRequest.getMatches(Integer.parseInt(day), league,
							teamName, status,user.getName()));

			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			out.write(gson.toJson(listMatches));
		}catch(Exception e){}
	}
	
	public void sendBets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)(request.getSession(false).getAttribute(LoginServlet.ATT_USER));
		String won = request.getParameter("won");
		if(won.isEmpty())won=null;
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
					ServerRequest.getBets(user.getName(), won, status,Integer.parseInt(day), league, teamName));

			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			out.write(gson.toJson(listMatches));
		}catch(Exception e){}
	}

}
