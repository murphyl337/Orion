package com.cengage.apprentice.app.utils;

import java.io.IOException;

import org.apache.log4j.Logger;

public class ResponseRunner implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(ResponseRunner.class);
    private RequestProcessor requestProcessor;
    
    public ResponseRunner(final RequestProcessor processor){
        this.requestProcessor = processor;
    }
    
    public void run() {
        try {
            requestProcessor.processRequest();
        } catch (IOException e) {
            LOGGER.error("IOException while processing request: ");
            e.printStackTrace();
        }
    }

}
