package com.cengage.apprentice.app.response;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;


public class StatusCodeResponse implements OrionResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1176223168018748216L;
	private int code;
	private String header;
	private String body;
	
	public StatusCodeResponse(int code){
		this.code = code;
		setBody();
		setHeader();
	}
	
	public void setHeader() {
		ResponseHeader header = new ResponseHeader();
		header.setStatus(code);
		header.setContentType("text/html");
		header.setContentLength(new Long(getBody().length()));

		this.header = header.composeHeader();
	}

	public void write(OutputStream output) {
		try {
			output.write(getHeader().getBytes(Charset.forName("UTF-8")));
			output.write(getBody().getBytes(Charset.forName("UTF-8")));
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBody() {
		this.body = "<html><body><h2>" + "Status Code: " + String.valueOf(code) + "</h2>" +
				"<p>Sorry, the file you've requested was not found :(</p>" + "</body></html>";

	}

	public String getHeader() {
		return header;
	}

	public String getBody() {
		return body;
	}


}
