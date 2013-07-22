package main;

import static java.lang.Runtime.getRuntime;
import static java.util.concurrent.Executors.newFixedThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import utils.OrionThreadFactory;
import utils.ResponseRunner;

public class OrionServer {
	private boolean running;
	private String rootDir;
	private String request;
	private ServerSocket serverSocket;
	private int port;
	private ExecutorService threadPool = newFixedThreadPool(getRuntime().availableProcessors(), new OrionThreadFactory());

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

			ResponseRunner responseRunner = new ResponseRunner(
					clientConnection, rootDir);

			threadPool.execute(responseRunner);
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
