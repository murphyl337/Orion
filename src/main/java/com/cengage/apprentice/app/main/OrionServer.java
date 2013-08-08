package com.cengage.apprentice.app.main;

import static java.lang.Thread.getAllStackTraces;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.cengage.apprentice.app.utils.ResponseRunner;

public class OrionServer {
    private final String rootDir;
    private final ServerSocket serverSocket;
    private String errorMessage;
    private boolean listening;

    public OrionServer(final ServerSocket serverSocket, final String rootDir) {
        this.rootDir = rootDir;
        this.serverSocket = serverSocket;
        listening = true;
    }

    public void listen() throws IOException {

        while (listening) {
            final Socket clientConnection = serverSocket.accept();
            respondToRequest(clientConnection);
        }
    }

    private void respondToRequest(final Socket clientConnection)
            throws IOException {
        final ResponseRunner responseRunner = new ResponseRunner(
                clientConnection.getInputStream(),
                clientConnection.getOutputStream(), rootDir);

        final Thread responseThread = new Thread(responseRunner,
                "orion-response-thread " + getAllStackTraces().size());
        responseThread.run();
    }

    public void stopServer() {
        try {
            serverSocket.close();
            listening = false;
        } catch (IOException e) {
            setErrorMessage("IOException when closing socket");
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String message) {
        this.errorMessage = message;
    }

    public boolean isListening() {
        return listening;
    }

}
