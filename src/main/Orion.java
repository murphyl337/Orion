package main;

import java.io.IOException;


public class Orion {
	
	public static void main(String[] args) throws IOException {
		OrionServer server = new OrionServer(5000, "/Users/tmurphy/Documents/workspace/Orion/web");
		server.startServer();
		server.run();
	}

}
