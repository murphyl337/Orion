package com.cengage.apprentice.app.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.cengage.apprentice.app.utils.ResponseRunner;

public class OrionServer {
    private static final Logger LOGGER = Logger.getLogger(OrionServer.class);
    private final String rootDir;
    private final ServerSocket serverSocket;
    private boolean listening;
    private ThreadGroup threadGroup;
    
    public OrionServer(final ServerSocket serverSocket, final String rootDir) {
        this.rootDir = rootDir;
        this.serverSocket = serverSocket;
        listening = true;
        threadGroup = new ThreadGroup("Response Group");
    }

    public void listen() throws IOException {

        while (listening) {
            final Socket clientConnection = serverSocket.accept();
            respondToRequest(clientConnection);
            clientConnection.close();
        }
    }

    private void respondToRequest(final Socket clientConnection)
            throws IOException {
        final ResponseRunner responseRunner = new ResponseRunner(
                clientConnection.getInputStream(),
                clientConnection.getOutputStream(), rootDir);

        final Thread responseThread = new Thread(threadGroup, responseRunner,
                "orion-response-thread " + Thread.activeCount());
        LOGGER.info("Creating responseThread: " + responseThread.getName());
        responseThread.run();
    }

    public void stopServer() {
        try {
            serverSocket.close();
            listening = false;
        } catch (IOException e) {
            LOGGER.error("IOException stopping server");
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public boolean isListening() {
        return listening;
    }

}
