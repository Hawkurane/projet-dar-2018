package tools;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HTMLBuilder {
	
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	private static ServletOutputStream out;
	
	public static void init(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		request = req;
		response = resp;
		out = response.getOutputStream();
	}
	
	public static void setToHtmlContentType() {
		response.setContentType("text/html");
	}
	
	public static void append(String s) throws IOException {
		out.write(s.getBytes());
	}
}
