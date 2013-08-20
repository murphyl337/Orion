package com.cengage.apprentice.app.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.cengage.apprentice.app.utils.RequestProcessor;
import com.cengage.apprentice.app.utils.Respondable;
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

    public void listen(final Respondable responder) throws IOException {
        while (listening) {
            final Socket clientConnection = serverSocket.accept();
            final RequestProcessor requestProcessor = new RequestProcessor(
                    clientConnection, rootDir, responder);

            respondToRequest(requestProcessor);
            clientConnection.close();
        }
    }

    private void respondToRequest(final RequestProcessor processor) throws IOException {
        final ResponseRunner runner = new ResponseRunner(processor);
        final Thread responseThread = new Thread(threadGroup, runner,
                "orion-response-thread");
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
