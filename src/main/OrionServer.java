package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import response.OrionResponse;

import utils.RequestParser;
import utils.Responder;

public class OrionServer {
	private boolean running;
	private String docRoot;
	private String request;
	private ServerSocket serverSocket;

	public OrionServer() {
		running = false;
		serverSocket = null;
	}

	public void run() throws IOException {
		Socket clientConnection = null;
		InputStream input = null;
		OutputStream output = null;
		while (true) {
			clientConnection = serverSocket.accept();
			input = clientConnection.getInputStream();
			output = clientConnection.getOutputStream();
			
			OrionRequest request = getRequest(input);
			OrionResponse response = getResponse(request);
			//get response for request
			//write response to output
			clientConnection.close();
		}
	}


	private OrionRequest getRequest(InputStream input) {
		BufferedReader rawRequest = readRequest(input);
		OrionRequest request = parseRequest(rawRequest);
		return request;
	}

	private OrionResponse getResponse(OrionRequest request) {
		OrionResponse response = new Responder(docRoot).respond(request);
		return response;
	}

	private BufferedReader readRequest(InputStream input) {
		return new BufferedReader(new InputStreamReader(input));
	}

	private OrionRequest parseRequest(BufferedReader in) {
		return new RequestParser().parse(in);
	}

	public void startServer(int port, String docRoot) {
		try {
			serverSocket = new ServerSocket(port);
			this.docRoot = docRoot;
			System.out.println("Orion server has started on port: " + port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setRunning(true);
	}

	public void stopServer() {
		setRunning(false);
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public String getDocRoot() {
		return docRoot;
	}

	public void setDocRoot(String docRoot) {
		this.docRoot = docRoot;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

}
