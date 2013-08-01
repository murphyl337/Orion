package com.cengage.apprentice.app.main;

import static java.lang.Thread.getAllStackTraces;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.cengage.apprentice.app.utils.ResponseRunner;

public class OrionServer {
	private String rootDir;
	private ServerSocket serverSocket;
	private int port;
	private String errorMessage;

	public OrionServer(ServerSocket serverSocket, String rootDir) {
		this.rootDir = rootDir;
		this.serverSocket = serverSocket;
	}

	public void listen() throws IOException {

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

	public void stopServer() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			setErrorMessage("IOException when closing port: " + port);
			System.err.println(errorMessage);
		}
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

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public String getErrorMessage(){
		return errorMessage;
	}
	
	public void setErrorMessage(String message){
		this.errorMessage = message;
	}

}
