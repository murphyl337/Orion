package com.cengage.apprentice.app.main;

import static java.lang.Thread.getAllStackTraces;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.cengage.apprentice.app.utils.ResponseRunner;

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

		while (true) {
			System.out.println("Getting the client socket...");
			final Socket clientConnection = serverSocket.accept();

			try{
				ResponseRunner responseRunner = new ResponseRunner(
						clientConnection, rootDir);
				
				Thread responseThread = new Thread(responseRunner, "orion-response-thread " + getAllStackTraces().size());
				System.out.println("Starting thread: " + responseThread.getName());
				responseThread.run();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				clientConnection.close();
			}
		}
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
