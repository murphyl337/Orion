package com.cengage.apprentice.app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.OrionResponse;

public class ResponseRunner implements Runnable{
    private static final Logger LOGGER = Logger.getLogger(ResponseRunner.class);
    private InputStream inputStream;
    private OutputStream outputStream;
    private String rootDir;
    private String errorMessage;

    public ResponseRunner(){errorMessage = "";}
    public ResponseRunner(final InputStream input, final OutputStream output, final String rootDir){
        this.inputStream = input;
        this.outputStream = output;
        this.rootDir = rootDir;
        errorMessage = "";
    }

    public OrionRequest getRequest(final InputStream inputStream) {
        final String requestString = StreamConverter.inputStreamToString(inputStream);
        LOGGER.info("Getting OrionRequest for requestString: " + requestString);
        OrionRequest request = null;
        try{
            request = new RequestParser().parse(requestString);
        }
        catch(ArrayIndexOutOfBoundsException e){
            LOGGER.error("Error while parsing request: ArrayIndexOutOfBounds");
            request = new OrionRequest();
        }
        return request;
    }

    public OrionResponse getResponse(final OrionRequest request) {
        return new Responder(rootDir).respond(request);
    }

    public void processRequest(final InputStream input, final OutputStream output, final String rootDir){
        final OrionRequest request = getRequest(input);
        final OrionResponse response = getResponse(request);
        try {
            response.write(output, response.getBody());
        } catch (IOException e) {
            LOGGER.error("ResponseRunner: IOException while writing response to socket");
        }
    }

    public void run(){
        processRequest(getInputStream(), getOutputStream(), rootDir);
    }

    public String getRootDir(){
        return rootDir;
    }

    public void setRootDir(final String dir){
        this.rootDir = dir;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
    public InputStream getInputStream() {
        return inputStream;
    }
    public void setInputStream(final InputStream inputStream) {
        this.inputStream = inputStream;
    }
    public OutputStream getOutputStream() {
        return outputStream;
    }
    public void setOutputStream(final OutputStream outputStream) {
        this.outputStream = outputStream;
    }

}
