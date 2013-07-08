package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
			if (running) {
				input = clientConnection.getInputStream();
				output = clientConnection.getOutputStream();
				
				BufferedReader in = new BufferedReader(new InputStreamReader(input));
				String requestLine;
				while(!(requestLine = in.readLine()).equals(""))
					System.out.println(requestLine);
				in.close();
			}
		}
	}

	public void startServer(int port, String docRoot) {
		try {
			serverSocket = new ServerSocket(port);
			this.docRoot = docRoot;
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
