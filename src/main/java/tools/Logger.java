package tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Logger {
	
	final static String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	final static int N = alphabet.length();
	final static int KEY_LENGTH = 64;

	public static boolean logIn(String username, String password) {
		try{
			ResultSet res = ServerRequest.login(username, password);
			res.next();
			return (res.getString("password").equals(password));

		}catch(SQLException e){	}
		return false;
	}
	
	public static boolean logOut(String username) {
		return true;
	}
	
	public static boolean verifySession(String username,String sessionKey){
		//TODO
		return true;
	}
	
	public static String generateSessionKey() {
		Random rand = new Random();
		StringBuilder str = new StringBuilder();
		for(int i = 0; i<KEY_LENGTH; i++) {
			str.append(alphabet.charAt(rand.nextInt(N)));
		}
		return str.toString();
	}
	
	

}
