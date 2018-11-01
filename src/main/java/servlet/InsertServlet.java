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

	public static final String insertType = "insertType";
	public static final String insertBet = "bet";
	public static final String insertnewUser = "newUser";
	public static final String insertnewFriend = "newFriend";
	public static final String CHAMP_REGISTER_NAME       = "username";
	public static final String CHAMP_REGISTER_PWD        = "password";
	public static final String CHAMP_REGISTER_PDW2       = "passwordconfirm";
	public static final String CHAMP_REGISTER_MAIL       = "mail";
	public static final String CHAMP_REGISTER_BIRTHDAY   = "birthday";
	public static final String CHAMP_REGISTER_REGION     = "region";
	public static final String CHAMP_ADDFRIEND_NAME     = "username";

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JsonObject json = new JsonObject();
		try{
			boolean b = false;
			if(request.getParameter(insertType).equals(insertBet)){
				b = insertBet(request, response);
			}else if(request.getParameter(insertType).equals(insertnewUser)){
				b = insertnewUser(request, response);
			}
			else if(request.getParameter(insertType).equals(insertnewFriend)){
				b = insertnewUser(request, response);
			}
			json.addProperty("insert", b);
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
	
	public boolean insertFriend(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		User user = (User)(request.getSession(false).getAttribute(LoginServlet.ATT_USER));
		String friend = request.getParameter(CHAMP_ADDFRIEND_NAME);
		return ServerRequest.insertFriend(user.getName(), friend);
	}

	public boolean insertnewUser(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		String newusername = request.getParameter(CHAMP_REGISTER_NAME);
		String password = request.getParameter(CHAMP_REGISTER_PWD);
		String confirmpassword = request.getParameter(CHAMP_REGISTER_PDW2);
		String mail = request.getParameter(CHAMP_REGISTER_MAIL);
		Date date;
		if(request.getParameter("birthdayYear")!=null)
			date = Date.valueOf(request.getParameter("birthdayYear"));
		else
			date = Date.valueOf("1996-01-01");

		int region;
		if(request.getParameter("region")!=null)
			region = Integer.parseInt(request.getParameter("region"));
		else
			region = 75;

		if(!ServerRequest.existName(newusername) && password.equals(confirmpassword))
			return ServerRequest.createAccount(newusername,password,date,region,mail);
		else
			return false;
				
	}

}
