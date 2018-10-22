package servlet;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		name = "LogoutServlet",
		urlPatterns = {"/logout"}
		)
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String URL_REDIRECTION = "/";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("/");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect(URL_REDIRECTION);

	}
}
