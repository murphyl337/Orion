package com.cengage.apprentice.app.utils;

import com.cengage.apprentice.app.main.OrionRequest;

public class RequestParser {

    private static final int MAX_FILE_EXTENSION_LENGTH = 4;
    private static final int MIN_FILE_EXTENSION_LENGTH = 2;

    public OrionRequest parse(final String requestString) {
        final String method = parseMethod(requestString);
        final String route = parseRoute(requestString);
        final OrionRequest request = new OrionRequest(method, route);
        
        return request;

    }

    private String[] parseHeader(final String requestString) {
        final String[] header = requestString.split("\\r?\\n");
        return header[0].split(" ");
    }

    private String parseMethod(final String requestString) {
        final String[] header = parseHeader(requestString);
        return header[0];
    }

    private String parseRoute(final String requestString) {
        final String[] header = parseHeader(requestString);
        final String route = header[1];
        if (hasTrailingSlash(route) || hasFileExtension(route)) {
            return route;
        }
        return route + "/";
    }

    public boolean hasTrailingSlash(final String route) {
        return route.charAt(route.length() - 1) == ('/');
    }

    public boolean hasFileExtension(final String filePath) {
        final char dot = '.';
        final int charactersAfterPeriod = (filePath.length() - 1)
                - filePath.lastIndexOf(dot);
        return charactersAfterPeriod >= MIN_FILE_EXTENSION_LENGTH
                && charactersAfterPeriod <= MAX_FILE_EXTENSION_LENGTH;
    }
}
