package com.cengage.apprentice.app.utils;

import java.util.Hashtable;

import com.cengage.apprentice.app.main.OrionRequest;

public class RequestParser {

    private static final String QUESTION_MARK = "\\?";
    private static final int MAX_FILE_EXTENSION_LENGTH = 4;
    private static final int MIN_FILE_EXTENSION_LENGTH = 2;

    public OrionRequest parse(final String requestString) {
        final OrionRequest request = new OrionRequest();
        request.setMethod(parseMethod(requestString));
        request.setRawRoute(parseRawRoute(requestString));
        request.setRoute(parseRoute(requestString));
        request.setQueryStringTable(parseQueryStrings(requestString));
      
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

    private String parseRawRoute(final String requestString) {
        return parseHeader(requestString)[1];
    }
    
    private String parseRoute(final String requestString) {
        final String[] header = parseHeader(requestString);
        final String rawRoute = header[1];
        final String route = rawRoute.split(QUESTION_MARK)[0];
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

    public boolean containsQueryString(final String requestString) {
        return parseRawRoute(requestString).split(QUESTION_MARK).length > 1;
    }


    public Hashtable<String, String> parseQueryStrings(final String requestString) {
        if (containsQueryString(requestString)) {
            final String rawQueryStrings = parseRawRoute(requestString).split(QUESTION_MARK)[1];
            final String[] queryStrings = rawQueryStrings.split("&");

            final Hashtable<String, String> queryStringHash = new Hashtable<String, String>();
            populateQueryHashTable(queryStrings, queryStringHash);
            return queryStringHash;
        } else {
            return new Hashtable<String, String>();
        }
    }


    private void populateQueryHashTable(final String[] queryStrings,
            final Hashtable<String, String> queryStringHash) {
        for(String string : queryStrings) {
            if(string.contains("=")) {
                final String[] queryString = string.split("=");
                queryStringHash.put(queryString[0], queryString[1]);
            }
        }
    }
}
