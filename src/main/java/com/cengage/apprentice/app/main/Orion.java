package com.cengage.apprentice.app.main;

import java.io.IOException;
import java.net.ServerSocket;

import com.cengage.apprentice.app.utils.FileChecker;

final class Orion {
    private static final int DEFAULT_PORT = 10000;
    private static int port = DEFAULT_PORT;
    private static String rootDirectory = "public";

    private Orion() {}

    public static void main(final String[] args) throws IOException {
        parseArgs(args);
        final ServerSocket serverSocket = new ServerSocket(getPort());
        final OrionServer server = new OrionServer(serverSocket,
                getRootDirectory());
        server.listen();
    }

    public static void parseArgs(final String[] args) {
        final FileChecker checker = new FileChecker("");
        int index = 0;
        for (String arg : args) {
            checkArgument(args, checker, index, arg);
            index++;
        }
    }

    private static void checkArgument(final String[] args,
            final FileChecker checker, final int index, final String arg) {
        if ("-p".equals(arg)) {
            setPort(Integer.parseInt(args[index + 1]));
        }
        if ("-d".equals(arg)) {
            final String tempDir = args[index + 1];
            if (checker.directoryExists(tempDir)) {
                setRootDirectory(tempDir);
            }
        }
    }

    public static String getRootDirectory() {
        return rootDirectory;
    }

    public static void setRootDirectory(final String rootDirectory) {
        Orion.rootDirectory = rootDirectory;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(final int port) {
        Orion.port = port;
    }
}
