package com.cengage.apprentice.app.utils;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;

import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.FileResponse;
import com.cengage.apprentice.app.response.OrionResponse;
import com.cengage.apprentice.app.response.StatusCodeResponse;

public class Responder {
    private static final Logger LOGGER = Logger.getLogger(Responder.class);
    private static final int NOT_FOUND = 404;
    private static final int OK = 200;
    private String root;

    public Responder(final String root) {
        this.root = root;
    }

    public OrionResponse respond(final OrionRequest request)
            throws FileNotFoundException {
        LOGGER.info("Responding to request with route: " + request.getRoute());
        final FileChecker fileChecker = new FileChecker(root);
        if ("/".equals(request.getRoute())) {
            return new StatusCodeResponse(OK);
        } else if (fileChecker.fileExists(request.getRoute())) {
            return new FileResponse(root, request.getRoute());
        }
        return new StatusCodeResponse(NOT_FOUND);
    }
}
