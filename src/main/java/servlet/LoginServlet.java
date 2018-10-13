package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import tools.ConnectionForm;
import tools.HTMLBuilder;



@WebServlet(
		name = "IndexServlet",
		urlPatterns = {"/aaaa"}
		)
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String ATT_USER 			= "user";
	public static final String ATT_FORM 			= "form";
	public static final String ATT_SESSION_USER 	= "sessionUser";
	public static final String VIEW 				= "src/main/webapp/index.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ConnectionForm form = new ConnectionForm();
		
		User user = form.connectUser(request);
		
		
		HttpSession session = request.getSession();
		
		if( form.getErrors().isEmpty() )
			session.setAttribute(ATT_SESSION_USER, user);
		else
			session.setAttribute(ATT_SESSION_USER, null);
		
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER,  user);
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}
	

}
