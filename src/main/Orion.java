package main;

import java.io.IOException;


public class Orion {
	
	public static void main(String[] args) throws IOException {
		OrionServer server = new OrionServer();
		server.startServer(5000, "derp");
		server.run();
	}

}
