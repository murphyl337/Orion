package com.cengage.apprentice.app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.OrionResponse;

public class ResponseRunner implements Runnable{

	private Socket socket;
	private String rootDir;
	private String errorMessage;
	
	public ResponseRunner(){}
	public ResponseRunner(Socket socket, String rootDir){
		setSocket(socket);
		setRootDir(rootDir);
	}
	
	public OrionRequest getRequest(InputStream inputStream) {
		String requestString = StreamConverter.inputStreamToString(inputStream);
		OrionRequest request = new OrionRequest();
		try{
			request = new RequestParser().parse(requestString);			
		}
		catch(Exception e){
			System.out.println("Error while parsing request");
			request = new OrionRequest();
		}
		return request;
	}

	public OrionResponse getResponse(OrionRequest request) {
		return new Responder(null).respond(request);
	}
	
	public void processRequest(InputStream input, OutputStream output, String rootDir) throws IOException{
		OrionRequest request = getRequest(input);
		OrionResponse response = getResponse(request);
		response.write(output, response.getBody());
	}

	public void run() {
		try {
			processRequest(getSocket().getInputStream(), getSocket().getOutputStream(), rootDir);
		} catch (IOException e) {
			errorMessage = "ResponseRunner: IOException processingRequest";
			System.out.println(errorMessage);
		}
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public String getRootDir(){
		return rootDir;
	}
	
	public void setRootDir(String dir){
		this.rootDir = dir;
	}
	
	public String getErrorMessage(){
		return errorMessage;
	}
	
}
