package main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;


public class SocketService {
	private int port;
	private String root;
	private boolean listening;
	private ServerSocket serverSocket;
	
	public SocketService(int port, String root){
		this.port = port;
		this.root = root;
	}
	
	public void listen() {
        try {
            serverSocket =  new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
            SocketThread socketThread = new SocketThread(serverSocket, root);
            socketThread.run();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getRoot() {
		return root;
	}
	public void setRoot(String root) {
		this.root = root;
	}
	public boolean isListening() {
		return listening;
	}
	public void setListening(boolean listening) {
		this.listening = listening;
	}
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
}
