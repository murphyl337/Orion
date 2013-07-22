package utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import main.OrionRequest;
import response.OrionResponse;

public class ResponseRunner implements Runnable{
	private Socket connection;
	private String rootDir;
	
	public ResponseRunner(Socket socket, String rootDir){
		this.connection = socket;
		this.rootDir = rootDir;
	}
	
	@Override
	public void run() {
		try{
			InputStream input = connection.getInputStream();
			OutputStream output = connection.getOutputStream();
			OrionRequest request = getRequest(input);
			OrionResponse response = getResponse(request);
			
			System.out.println("Got response with header...");
			System.out.println(response.getHeader());
			
			response.write(output);
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
		return new RequestParser().parse(in);
	}
	
}
