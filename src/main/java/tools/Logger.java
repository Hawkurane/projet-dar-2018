package tools;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Logger {

	public static boolean logIn(String username, String password) {
		try{
			password = encrypt(password);
			ResultSet res = ServerRequest.login(username, password);
			res.next();
			return (res.getString("password").equals(password));

		}catch(SQLException e){	}
		return false;
	}	

	
	public static String encrypt(String x) {
        java.security.MessageDigest d = null;
        try {
			d = java.security.MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("ai");
			e.printStackTrace();
		}
        d.reset();
        d.update(x.getBytes());
        return byteArrayToHexString(d.digest());
    }
	
	private static String byteArrayToHexString(byte[] b) {
		  String result = "";
		  for (int i=0; i < b.length; i++) {
		    result +=
		          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		  }
		  return result;
		}
	

}
