package main;

import java.io.IOException;


public class Orion {
	
	public static void main(String[] args) throws IOException {
		OrionServer server = new OrionServer(9900, "/Users/tmurphy/Documents/workspace/Orion/web");
		server.startServer();
		server.run();
	}

}
