package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import tools.Logger;
import tools.ServerRequest;
import tools.User;
import tools.UsersBase;
import tools.Utils;



@WebServlet(
		name = "LoginServlet",
		urlPatterns = {"/login"}
		)
public class LoginServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	public static final String VUE              = "/index.jsp";
	public static final String ATT_USER     = "user";
	public static final String CHAMP_NAME       = "userlogin";
	public static final String CHAMP_PWD        = "pwdlogin";
	/*public static final String ATT_USER 			= "user";
	public static final String ATT_FORM 			= "form";
	public static final String ATT_SESSION_USER 	= "sessionUser";
	public static final String VIEW 				= "src/main/webapp/index.jsp";
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		String username = request.getParameter(CHAMP_NAME);
		String password = request.getParameter(CHAMP_PWD);

		HttpSession session = request.getSession();
		if(Logger.logIn(username, password)) 
			try{
				session.setAttribute(ATT_USER,Utils.getProfil(ServerRequest.profil(username)));
			}catch(SQLException e){session.setAttribute(ATT_USER,null);}
		else
			session.setAttribute(ATT_USER,null);

		//this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		response.sendRedirect("/");
	}



	//TODO
	//creer un objet user et le retourner via session
	//puis faire de meme avec getmatchs, classement, etc...

}
