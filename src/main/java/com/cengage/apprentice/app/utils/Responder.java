package com.cengage.apprentice.app.utils;

import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.OrionResponse;
import com.cengage.apprentice.app.response.StatusCodeResponse;

public class Responder {

    private static final int NOT_FOUND = 404;
    private static final int OK = 200;
    @SuppressWarnings("unused")
    private String root;

    public Responder(final String root) {
        this.root = root;
    }

    public OrionResponse respond(final OrionRequest request) {
        //FileChecker fileChecker = new FileChecker(root);
        if("/".equals(request.getRoute())){
            return new StatusCodeResponse(OK);
        }
        return new StatusCodeResponse(NOT_FOUND);
    }
}
