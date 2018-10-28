package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//nom a modif?
@WebServlet(
		name = "InsertServlet",
		urlPatterns = {"/insert"})
public class InsertServlet extends HttpServlet{


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//y traiter les requetes post pour enregistrer les bets de l'utilisateur
		//les demandes d'ami, etc..
	}
}
