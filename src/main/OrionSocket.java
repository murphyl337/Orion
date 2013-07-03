package main;

import java.io.IOException;
import java.net.ServerSocket;

public class OrionSocket {

	private int port;
	private ServerSocket socket;

	public OrionSocket(int port) {
		this.port = port;
		try {
			this.socket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getPort() {
		return this.port;
	}

	public void close() {
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isClosed() {
		return this.socket.isClosed();
	}

}
