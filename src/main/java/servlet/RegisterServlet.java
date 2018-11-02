package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.ServerRequest;

@WebServlet(
		name = "RegisterServlet",
		urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE  = "/register.jsp";

	public static final String CHAMP_REGISTER_NAME       = "userregister";
	public static final String CHAMP_REGISTER_PWD        = "pwdregister";
	public static final String CHAMP_REGISTER_PDW2       = "pwdregisterconfirm";
	public static final String CHAMP_REGISTER_MAIL       = "mailregister";
	public static final String CHAMP_REGISTER_BIRTHDAY   = "birthday";
	public static final String CHAMP_REGISTER_REGION     = "region";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String newusername = request.getParameter(CHAMP_REGISTER_NAME);
		String password = request.getParameter(CHAMP_REGISTER_PWD);
		String confirmpassword = request.getParameter(CHAMP_REGISTER_PDW2);
		String mail = request.getParameter(CHAMP_REGISTER_MAIL);
		Date date;
		if(request.getParameter(CHAMP_REGISTER_BIRTHDAY)!=null)
			date = Date.valueOf(request.getParameter(CHAMP_REGISTER_BIRTHDAY));
		else
			date = Date.valueOf("1996-01-01");

		int region;
		if(request.getParameter(CHAMP_REGISTER_REGION)!=null)
			region = Integer.parseInt(request.getParameter(CHAMP_REGISTER_REGION));
		else
			region = 75;

		boolean b = false;
		try{
			//if(!ServerRequest.existName(newusername) && password.equals(confirmpassword))
				if(ServerRequest.createAccount(newusername,password,date,region,mail));

		}catch(Exception e){}
		
		request.setAttribute("register", b);

		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

}