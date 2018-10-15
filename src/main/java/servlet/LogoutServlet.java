package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import tools.Logger;

@WebServlet(
		name = "LogoutServlet",
		urlPatterns = {"/logout"}
		)
public class LogoutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("/");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("user");
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
			
		Map<String, String> options = new LinkedHashMap<String, String>();
		if(Logger.logOut(username)) {
			response.setStatus(HttpServletResponse.SC_OK);
			options.put("code", "200");
			options.put("message", "OK");
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			options.put("code","500");
			options.put("message", "INTERNAL SERVER ERROR");
		}
		String json = new Gson().toJson(options);	
		out.write(json);
		
	}
}
