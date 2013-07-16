package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class HeaderBuilder {
	public static final String HTTP = "HTTP/1.1 ";
	public static final String NEWLINE = "\r\n";
	
	private String header = "";
	
	public void setStatus(int code) {
		header += HTTP + String.valueOf(code) + " ";
		switch(code){
		case 200:
			header += "OK";
			break;
		
		case 404:
			header += "Not Found";
			break;
			
		case 500:
			header += "Internal Server Error";
		}
		header += NEWLINE;
	}

	public void setDate() {
		DateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss zz");
		header += "Date: " + dateFormat.format(new Date()) + NEWLINE;
	}

	public void setServer() {
		header += "Server: Orion" + NEWLINE;
	}

	public void setContentLength(Long fileLength) {
		header += "Content-Length: " + fileLength + NEWLINE; 
	}

	public void setContentType(String contentType) {
		header += "Content-Type: " + contentType + NEWLINE;
	}

	public String getHeader() {
		return header + NEWLINE;
	}

	public void setHeader(String header) {
		this.header = header;
	}
}
