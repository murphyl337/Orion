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
	private String rootDir;
	private String request;
	private ServerSocket serverSocket;
	private int port;

	public OrionServer(int port, String rootDir) {
		this.setPort(port);
		this.rootDir = rootDir;
		running = false;
		serverSocket = null;
	}

	public void run() throws IOException {
		Socket clientConnection = null;
		InputStream input = null;
		OutputStream output = null;
		
		while (true) {
			System.out.println("Getting the client socket...");
			clientConnection = serverSocket.accept();
			input = clientConnection.getInputStream();
			output = clientConnection.getOutputStream();
			
			OrionRequest request = getRequest(input);
			OrionResponse response = getResponse(request);

			System.out.println("Got response with header...");
			System.out.println(response.getHeader());
			
			response.write(output);
			System.out.println("Wrote response to socket!");
			
			clientConnection.close();
			System.out.println("Closed client socket");
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

	public void startServer() {
		try {
			serverSocket = new ServerSocket(port);
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

	public String getRootDir() {
		return rootDir;
	}

	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
