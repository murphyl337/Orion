package com.cengage.apprentice.app.main;

import java.io.IOException;
import java.net.ServerSocket;

import com.cengage.apprentice.app.utils.FileChecker;

public class Orion {
	public static int port = 10000;
	public static String rootDirectory = "public";

	public static void main(String[] args) throws IOException {
		parseArgs(args);
		ServerSocket serverSocket = new ServerSocket(port);
		OrionServer server = new OrionServer(serverSocket, rootDirectory);
		server.listen();
	}

	public static void parseArgs(String[] args) throws NumberFormatException {
		FileChecker checker = new FileChecker("");
		int index = 0;
		for (String arg : args) {
			if (arg.equals("-p")) {
				port = Integer.parseInt(args[index + 1]);
			}
			if (arg.equals("-d")) {
				String tempDir = args[index + 1];
				if(checker.directoryExists(tempDir))
					rootDirectory = tempDir;
			}
			index++;
		}
	}
}
