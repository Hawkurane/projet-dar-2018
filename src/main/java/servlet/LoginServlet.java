package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import tools.HTMLBuilder;
import tools.Logger;



@WebServlet(
		name = "LoginServlet",
		urlPatterns = {"/login"}
		)
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*public static final String ATT_USER 			= "user";
	public static final String ATT_FORM 			= "form";
	public static final String ATT_SESSION_USER 	= "sessionUser";
	public static final String VIEW 				= "src/main/webapp/index.jsp";
	*/

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> options = new LinkedHashMap<String, String>();
		options.put("value1", "label1");
		options.put("value2", "label2");
		options.put("value3", "label3");
		String json = new Gson().toJson(options);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		//this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("userlogin");
		String password = request.getParameter("pwdlogin");
		
		Map<String, String> options = new LinkedHashMap<String, String>();
		
		
		if(Logger.logIn(username, password)) {
			options.put("success", "true");
			options.put("user", username);
		} else 
			options.put("success", "false");
			
		String json = new Gson().toJson(options);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}
	

}
