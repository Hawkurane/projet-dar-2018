package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Utils;

@WebServlet(
		name = "MatchesServlet",
		urlPatterns = {"/matches"})
public class MatchesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String VUE  = "/matches.jsp";




	//redirige vers le .jsp avec le formulaire approprier
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//init form
		String[] matchDay = new String[Utils.nbrMatchDay];
		for(int i=0;i<Utils.nbrMatchDay;i++)
			matchDay[i] = ""+i;
		Map<String,String[]> form = new HashMap<String,String[]>();
		form.put("matchday", matchDay);
		form.put("status", Utils.status);
		form.put("teamName", Utils.getAllTeamsName());
		form.put("league", Utils.getLeaguesNames());
		request.setAttribute("form", form );
		//retourne formulaire
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );



	}

}
