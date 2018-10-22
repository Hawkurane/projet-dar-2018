package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import tools.Logger;



@WebServlet(
		name = "LoginServlet",
		urlPatterns = {"/login"}
		)
public class LoginServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	public static final String VUE              = "src/main/webapp/bootstrap/js/";
	public static final String ATT_USERNAME     = "name";
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

		if(Logger.logIn(username, password)) {
			//response.setStatus(200);
			//json.addProperty("sessionKey",Logger.generateSessionKey());
			HttpSession session = request.getSession();
			session.setAttribute(ATT_USERNAME,username);
		}

		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}
	

}
