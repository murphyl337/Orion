package main;

import java.io.IOException;
import java.net.ServerSocket;

public class OrionServer {
	private boolean running;
	private String docRoot;
	private ServerSocket serverSocket;
	
	public OrionServer(){
		running = false;
		serverSocket = null;
	}

	public void run() {
		// TODO Auto-generated method stub		
	}

	public synchronized void startServer(int port, String docRoot) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setRunning(true);
	}

	public void stopServer() {
		setRunning(false);
		try{
			serverSocket.close();
		}
		catch(IOException e){
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


}
