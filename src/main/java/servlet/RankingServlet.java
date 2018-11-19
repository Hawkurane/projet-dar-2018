package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.ServerRequest;
import tools.Standing;
import tools.Utils;

@WebServlet(
		name = "RankingsServlet",
		urlPatterns = {"/ranking/"})
public class RankingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String VUE  = "/ranking.jsp";

	


	//redirige vers le .jsp avec le formulaire et sans classement
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("form", Utils.getLeaguesNames() );
		try{
			int size = Integer.parseInt(request.getParameter("matchday"));
			request.setAttribute("ranking", Utils.getRanking(ServerRequest.getRanking(size)));
		}catch(Exception e){e.printStackTrace();}
		//retourne formulaire
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );


	}

}
