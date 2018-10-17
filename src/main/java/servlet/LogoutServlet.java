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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("/");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//String username = request.getParameter("user");
		//String sessionKey = request.getParameter("session");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.getSession().invalidate();
		response.sendRedirect("/");
		//JsonObject json = new JsonObject();
		/*if(Logger.verifySession(username, sessionKey)){
			Logger.logOut(username);
			response.setStatus(HttpServletResponse.SC_OK);
			json.addProperty("message", "OK");
		} else {
			response.setStatus(HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED);
			json.addProperty("message", "NOT AUTHORIZED");
		}*/
		
		//response.getWriter().write(json.toString());
	}
}
