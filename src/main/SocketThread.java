package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import response.OrionResponse;
import utils.RequestParser;
import utils.Responder;

public class SocketThread extends Thread{

	Socket socket;
    InputStream input;
    OutputStream output;
    String rootDir;
    ServerSocket serverSocket;
    private boolean listening = true;

    public SocketThread(ServerSocket serverSocket, String rootDir) {
        this.serverSocket = serverSocket;
        this.rootDir = rootDir;
    }

    public void run() {
        while (listening) {
            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                OrionRequest request = getRequest(input);
                OrionResponse response = getResponse(request);
                
                response.write(output);
                
                socket.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private OrionRequest getRequest(InputStream input) {
		BufferedReader rawRequest = readRequest(input);
		OrionRequest request = parseRequest(rawRequest);
		return request;
	}

	private OrionResponse getResponse(OrionRequest request) {
		OrionResponse response = new Responder(rootDir).respond(request);
		return response;
	}
	
	private BufferedReader readRequest(InputStream input) {
		return new BufferedReader(new InputStreamReader(input));
	}

	private OrionRequest parseRequest(BufferedReader in) {
		return new RequestParser().parse(in);
	}

}
