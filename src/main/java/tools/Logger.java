package tools;

import java.util.Random;

public class Logger {
	
	final static String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	final static int N = alphabet.length();
	final static int KEY_LENGTH = 64;

	public static boolean logIn(String username, String password) {
		//TODO
		return username.equals(password);
		
	}
	
	public static boolean logOut(String username) {
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
