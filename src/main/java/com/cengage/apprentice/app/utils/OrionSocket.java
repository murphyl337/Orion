package com.cengage.apprentice.app.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class OrionSocket {
    private int port;
    private ServerSocket serverSocket;
    private boolean closed = true;
    private final static Logger LOGGER = Logger.getLogger(OrionSocket.class);

    public OrionSocket(final int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            LOGGER.error("Socket initialization failed: ");
            e.printStackTrace();
        }
    }

    public OrionSocket(final int port, final ServerSocket serverSocket) {
        this.port = port;
        this.serverSocket = serverSocket;
    }

    public Socket accept() {
        Socket clientConnection = null;
        try{
            clientConnection = serverSocket.accept();
            closed = false;
        }catch(IOException e){
            close();
            LOGGER.error("Socket accept failed: ");
        }
        
        return clientConnection;
    }

    public int getLocalPort() {
        return this.port;
    }

    public boolean isClosed() {
        return closed;
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            LOGGER.error("IOException closed socket: ");
            e.printStackTrace();
        }
    }

}
