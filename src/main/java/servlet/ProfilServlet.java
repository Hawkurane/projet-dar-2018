package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import tools.ServerRequest;

@WebServlet(
		name = "ProfilServlet",
		urlPatterns = {"/profil"}
		)
public class ProfilServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;


	//return json with basic user info
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		JsonObject json = new JsonObject();
		if(request.getSession(false)!=null){
			String username = (String) request.getSession(false).getAttribute("name");
			if(username!=null)
				try{
					json = ServerRequest.profil(username);
					response.setStatus(HttpServletResponse.SC_OK);
				}catch(SQLException e){
					response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
				}
		} else {
			response.setStatus(HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED);
			json.addProperty("message", "NOT AUTHORIZED");
		}
		response.getWriter().write(json.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("/");
	}

}
