package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;



@WebServlet(
		name = "IndexServlet",
		urlPatterns = {"/"}
		)
public class IndexServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



}
