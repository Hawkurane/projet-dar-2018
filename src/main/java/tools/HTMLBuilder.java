package tools;

import java.io.BufferedReader;
import java.io.FileReader;
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
	
	public static void appendHtml(String file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/main/webapp/res/"+file));
		String line;
		while ((line = in.readLine())!=null) {
			out.write(line.getBytes());
		}
		in.close();
	}
	
	public static void flush() throws IOException {
		out.flush();
	}
	
	public static void insertHead() throws IOException {
		appendHtml("head.html");
	}
}
