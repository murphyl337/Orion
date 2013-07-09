package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ResponseBuilder {
	public static final String HTTP = "HTTP/1.1 ";
	public static final String NEWLINE = "\r\n";

	public static String generateStatus(int code) {
		String status = HTTP;
		switch(code){
		case 200:
			status += code + " OK" + NEWLINE;
			break;
		
		case 404:
			status += code + " Not Found" + NEWLINE;
			break;
			
		case 500:
			status += code + " Internal Server Error" + NEWLINE;
		}
		return status;
	}

	public static String generateDate() {
		DateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss zz");
		return "Date: " + dateFormat.format(new Date()) + NEWLINE;
	}

	public static String generateServer() {
		return "Server: Orion" + NEWLINE;
	}

	public static String generateContentLength(Long fileLength) {
		return "Content-Length: " + fileLength + NEWLINE; 
	}

	public static String generateContentType(String contentType) {
		return "Content-Type: " + contentType + NEWLINE;
	}

}
