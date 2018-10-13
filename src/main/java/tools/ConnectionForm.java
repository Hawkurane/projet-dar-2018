package tools;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.User;

public class ConnectionForm {

	private static final String USER = "login_username";
	private static final String PASS = "login_password";
	
	private String result;
	private Map<String, String> errors = new HashMap<String, String>();
	
	public String getResult() { return result; }
	public Map<String, String> getErrors(){ return errors; }
	
	public User connectUser(HttpServletRequest request) {
		
		String login_username = getValue(request, USER);
		String login_password = getValue(request, PASS);
		
		User user = new User();
		
		try {
			checkUsername(USER);
		} catch (Exception e) {
			setError(USER, e.getMessage());
		}
		
		try {
			checkPassword(login_password);
		} catch (Exception e) {
			setError(PASS, e.getMessage());
		}
		
		user.setPassword(PASS);
		
		if(errors.isEmpty())
			result = "Connection suceeded";
		else
			result = "Connection failed.";
		
		
		return user;
	}
	
	private String getValue(HttpServletRequest request, String field) {
		String value = request.getParameter(field);
		if(value == null || value.trim().length() == 0) 
			return null;
		else
			return value;
		
	}
	private void setError(String key, String message) {
		errors.put(key, message);
		
	}
	private void checkPassword(String login_password) {
		// TODO
		
	}
	private void checkUsername(String user2) throws Exception{
		// TODO
	}
	
	
	
}
