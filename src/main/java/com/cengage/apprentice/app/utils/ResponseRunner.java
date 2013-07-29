package com.cengage.apprentice.app.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.OrionResponse;

public class ResponseRunner implements Runnable{
	private Socket connection;
	private String rootDir;
	
	public ResponseRunner(Socket socket, String rootDir){
		this.connection = socket;
		this.rootDir = rootDir;
	}
	
	public void run() {
		try{
			InputStream input = connection.getInputStream();
			OutputStream output = connection.getOutputStream();
			OrionRequest request = getRequest(input);
			OrionResponse response = getResponse(request);
			
			System.out.println("Got response with header...");
			System.out.println(response.getHeader());
			
			response.write(output, response.getBody());
			System.out.println("Wrote response to socket!");
			
			connection.close();
			System.out.println("Closed client socket");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private OrionRequest getRequest(InputStream input) {
		BufferedReader rawRequest = readRequest(input);
		OrionRequest request = parseRequest(rawRequest);
		return request;
	}

	private OrionResponse getResponse(OrionRequest request) {
		OrionResponse response = new Responder(rootDir).respond(request);
		return response;
	}

	private BufferedReader readRequest(InputStream input) {
		return new BufferedReader(new InputStreamReader(input));
	}

	private OrionRequest parseRequest(BufferedReader in) {
		RequestParser parser = new RequestParser();
		String requestString = parser.readerToString(in);
		return parser.parse(requestString);
	}
	
}
