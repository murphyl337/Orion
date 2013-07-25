package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResponseHeader {
	public static final String HTTP = "HTTP/1.1 ";
	public static final String NEWLINE = "\r\n";

	private String status;
	private Long contentLength;
	private String contentType;

	public void setStatus(int code) {
		status = HTTP + String.valueOf(code) + " ";
		switch (code) {
		case 200:
			status += "OK";
			break;

		case 404:
			status += "Not Found";
			break;

		case 500:
			status += "Internal Server Error";
		}
	}

	public String getStatus() {
		return status;
	}

	public String formatDate() {
		DateFormat dateFormat = new SimpleDateFormat(
				"E, dd MMM yyyy HH:mm:ss zz");
		return "Date: " + dateFormat.format(new Date());
	}

	public String getServer() {
		return "Server: Orion";
	}

	public void setContentLength(Long fileLength) {
		contentLength = fileLength;
	}

	public String getContentLength() {
		return "Content-Length: " + contentLength;
	}

	public void setContentType(String cType) {
		contentType = cType;
	}

	public String getContentType() {
		return "Content-Type: " + contentType;
	}

	public String composeHeader() {
		StringBuilder header = new StringBuilder();
		header.append(status);
		header.append(NEWLINE);
		header.append(formatDate());
		header.append(NEWLINE);
		header.append(getServer());
		header.append(NEWLINE);
		header.append(getContentLength());
		header.append(NEWLINE);
		header.append(getContentType());
		header.append(NEWLINE);
		header.append(NEWLINE);

		return header.toString();
	}
}
