package main;

import java.io.IOException;


public class Orion {
	public static int port = 10000;
	public static String rootDirectory = "public";
	
	public static void main(String[] args) throws IOException {
		parseArgs(args);
		OrionServer server = new OrionServer(port, rootDirectory);
		server.startServer();
		server.run();
	}
		
	public static void parseArgs(String[] args) {
        int index = 0;
        for(String arg : args) {
            if (arg.equals("-p") || arg.equals("-port")) { port = Integer.parseInt(args[index + 1]); }
            if (arg.equals("-d") || arg.equals("-directory")) { rootDirectory = args[index + 1]; }
            index ++;
        }
    }
}
