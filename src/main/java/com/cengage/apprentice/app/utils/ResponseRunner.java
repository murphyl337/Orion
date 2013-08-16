package com.cengage.apprentice.app.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.OrionResponse;
import com.cengage.apprentice.app.response.StatusCodeResponse;

public class ResponseRunner implements Runnable{
    private static final int FILE_NOT_FOUND = 404;
    private static final Logger LOGGER = Logger.getLogger(ResponseRunner.class);
    private InputStream inputStream;
    private OutputStream outputStream;
    private String rootDir;

    public ResponseRunner(){}
    public ResponseRunner(final InputStream input, final OutputStream output, final String rootDir){
        this.inputStream = input;
        this.outputStream = output;
        this.rootDir = rootDir;
    }

    public OrionRequest getRequest(final InputStream inputStream) {
        final String requestString = StreamConverter.inputStreamToString(inputStream);
        LOGGER.info("Getting OrionRequest for requestString: " + requestString);
        OrionRequest request = new OrionRequest();
        try{
            request = new RequestParser().parse(requestString);
        }
        catch(ArrayIndexOutOfBoundsException e){
            LOGGER.error("Error while parsing request: ArrayIndexOutOfBounds");
        }
        return request;
    }

    public OrionResponse getResponse(final OrionRequest request) {
        try{
            return new Responder(rootDir).respond(request);
        }catch(FileNotFoundException e){
            return new StatusCodeResponse(FILE_NOT_FOUND);
        }
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
