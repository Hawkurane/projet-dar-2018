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
		name = "StandingsServlet",
		urlPatterns = {"/standings/*"})
public class StandingsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String VUE  = "/standings.jsp";

	


	//redirige vers le .jsp avec le formulaire et sans classement
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("form", Utils.getLeaguesNames() );
		try{
			Standing[] standing = Utils.getStanding(ServerRequest.getStandings(request.getContentType()));
			request.setAttribute("standing", standing);
		}catch(Exception e){}
		//retourne formulaire
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );


	}

}
