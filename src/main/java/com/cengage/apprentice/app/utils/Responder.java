package com.cengage.apprentice.app.utils;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;

import com.cengage.apprentice.app.game.GameController;
import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.FileResponse;
import com.cengage.apprentice.app.response.OrionResponse;
import com.cengage.apprentice.app.response.StatusCodeResponse;

public class Responder {
    private static final Logger LOGGER = Logger.getLogger(Responder.class);
    private static final int NOT_FOUND = 404;
    private static final int OK = 200;
    private String root;
    private static GameController controller;

    public Responder(final String root, final GameController gameController) {
        this.root = root;
        Responder.setController(gameController);
    }

    public OrionResponse respond(final OrionRequest request)
            throws FileNotFoundException {
        LOGGER.info("Responding to request with route: " + request.getRoute());
        final FileChecker fileChecker = new FileChecker(root);
        if(request.getRoute().contains("game"))
            return controller.processRequest(request, root);
        if ("/".equals(request.getRoute())) {
            return new StatusCodeResponse(OK);
        } else if (fileChecker.fileExists(request.getRoute())) {
            return new FileResponse(root, request.getRoute());
        }
        
        return new StatusCodeResponse(NOT_FOUND);
    }

    public static void setController(final GameController controller) {
        Responder.controller = controller;
    }
}
