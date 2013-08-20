package com.cengage.apprentice.app.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.OrionResponse;

public class RequestProcessor {
    private static final Logger LOGGER = Logger.getLogger(ResponseRunner.class);
    private Socket clientConnection;
    private String rootDir;
    private Respondable responder;

    public RequestProcessor() {
    }

    public RequestProcessor(final Socket clientConnection, final String rootDir,
            final Respondable responder) {
        this.clientConnection = clientConnection;
        this.rootDir = rootDir;
        this.responder = responder;
    }

    public OrionRequest getRequest(final InputStream inputStream) {
        final String requestString = StreamConverter
                .inputStreamToString(inputStream);
        LOGGER.info("Getting OrionRequest for requestString: " + requestString);
        return RequestParser.parse(requestString);
    }

    public OrionResponse getResponse(final OrionRequest request)
            throws FileNotFoundException {
        return responder.respond(request);
    }
    
    public void processRequest() throws IOException {
        final OrionRequest request = getRequest(clientConnection.getInputStream());
        final OrionResponse response = getResponse(request);
        response.write(clientConnection.getOutputStream(), response.getBody());
    }
    
    public void setResponder(final Responder responder) {
        this.responder = responder;
    }

}
