package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.ServerRequest;

@WebServlet(
		name = "RegisterServlet",
		urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE              = "src/main/webapp/bootstrap/js/loginmodal.js";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newusername = request.getParameter("newlogin");
		String password = request.getParameter("pwdnewlogin");
		Date date = Date.valueOf(request.getParameter("birthdayYear"));
		int region = Integer.parseInt(request.getParameter("region"));
		try{
			if(!ServerRequest.existName(newusername)){
				if(ServerRequest.createAccount(newusername,password,date,region))
					request.setAttribute("form", true);
				else
					request.setAttribute("form", false);
			}
		}catch(SQLException e){
			request.setAttribute("form", false);
		}
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

}
