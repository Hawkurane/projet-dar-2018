package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import tools.ServerRequest;
import tools.User;

//nom a modif?
@WebServlet(
		name = "InsertServlet",
		urlPatterns = {"/insert"})
public class InsertServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public static final String insertType = "searchType";
	public static final String insertBet = "bet";
	public static final String insertnewUser = "newUser";

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JsonObject json = new JsonObject();
		try{
			if(request.getParameter(insertType).equals(insertBet)){
				insertBet(request, response);
			}else if(request.getParameter(insertType).equals(insertnewUser)){
				insertnewUser(request, response);
			}
			json.addProperty("insert", true);
			out.write(json.toString());
		}catch(SQLException e){
			json.addProperty("insert", false);
			out.write(json.toString());
		}
		//y traiter les requetes post pour enregistrer les bets de l'utilisateur
		//les demandes d'ami, etc..
	}
	
	public boolean insertBet(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		User user = (User)(request.getSession(false).getAttribute(LoginServlet.ATT_USER));
		int matchId = Integer.parseInt(request.getParameter("matchId"));
		String bet = request.getParameter("bet");
		return ServerRequest.insertBet(user.getName(), matchId, bet);
	}
	
	public boolean insertnewUser(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		String newusername = request.getParameter("newlogin");
		String password = request.getParameter("pwdnewlogin");
		Date date = Date.valueOf(request.getParameter("birthdayYear"));
		int region = Integer.parseInt(request.getParameter("region"));
		return ServerRequest.createAccount(newusername,password,date,region);
	}
}
