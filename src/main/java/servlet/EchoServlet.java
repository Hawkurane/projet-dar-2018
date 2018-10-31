package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EchoServlet
 */
@WebServlet(
		name = "EchoServlet",
		urlPatterns = {"/echo"})
public class EchoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public EchoServlet() {
        // TODO Auto-generated constructor stub
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        return DriverManager.getConnection(dbUrl);
    }


    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		
		String DATA = request.getParameter("data");
		
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h3>");
		try {
			String basiqueRequest = "SELECT * FROM users";
			Connection connexionDataBase = getConnection();
			Statement stmt = connexionDataBase.createStatement();
			ResultSet res = stmt.executeQuery(basiqueRequest);
			res.toString();
			while(res.next()){
				out.println(""+res.toString());
			}
		} catch (Exception e){
			out.println(e.getMessage());
		}
		
		if(DATA != null) out.println(DATA); else out.println("No text entered.");
		out.println("</h3>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
