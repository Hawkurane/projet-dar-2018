package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

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
		Date date = new Date(request.getParameter("birthdayYear"));
		int region = Integer.parseInt(request.getParameter("region"));
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try{
			if(!ServerRequest.existName(newusername)){
				if(ServerRequest.createAccount(newusername,password,date,region))
					response.setStatus(HttpServletResponse.SC_CREATED);
				else
					response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			}
		}catch(SQLException e){
			response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.sendRedirect("/");
	}

}
