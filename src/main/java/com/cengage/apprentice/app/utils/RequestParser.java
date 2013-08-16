package com.cengage.apprentice.app.utils;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.cengage.apprentice.app.main.OrionRequest;

public class RequestParser {
    private static final Logger LOGGER = Logger.getLogger(RequestParser.class);
    private static final String QUESTION_MARK = "\\?";
    private static final int MAX_FILE_EXTENSION_LENGTH = 4;
    private static final int MIN_FILE_EXTENSION_LENGTH = 2;

    public static OrionRequest parse(final String requestString) {
        try {
            return new OrionRequest.Builder()
                    .method(parseMethod(requestString))
                    .rawRoute(parseRawRoute(requestString))
                    .route(parseRoute(requestString))
                    .queries(parseQueryStrings(requestString)).build();
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.error("ArrayIndexOutOfBoundsException while parsing request: "
                    + requestString + e);
            return new OrionRequest.Builder().route("404").build();
        }
    }

    private static String[] parseHeader(final String requestString) {
        final String[] header = requestString.split("\\r?\\n");
        return header[0].split(" ");
    }

    private static String parseMethod(final String requestString) {
        final String[] header = parseHeader(requestString);
        return header[0];
    }

    private static String parseRawRoute(final String requestString) {
        return parseHeader(requestString)[1];
    }

    private static String parseRoute(final String requestString) {
        final String[] header = parseHeader(requestString);
        final String rawRoute = header[1];
        final String route = rawRoute.split(QUESTION_MARK)[0];
        if (hasTrailingSlash(route) || hasFileExtension(route)) {
            return route;
        }
        return route + "/";
    }

    public static boolean hasTrailingSlash(final String route) {
        return route.charAt(route.length() - 1) == ('/');
    }

    public static boolean hasFileExtension(final String filePath) {
        final char dot = '.';
        final int charactersAfterPeriod = (filePath.length() - 1)
                - filePath.lastIndexOf(dot);
        return charactersAfterPeriod >= MIN_FILE_EXTENSION_LENGTH
                && charactersAfterPeriod <= MAX_FILE_EXTENSION_LENGTH;
    }

    public static boolean containsQueryString(final String requestString) {
        return parseRawRoute(requestString).split(QUESTION_MARK).length > 1;
    }

    private static Hashtable<String, String> parseQueryStrings(
            final String requestString) {
        if (containsQueryString(requestString)) {
            final String rawQueryStrings = parseRawRoute(requestString).split(
                    QUESTION_MARK)[1];
            final String[] queryStrings = rawQueryStrings.split("&");

            final Hashtable<String, String> queryStringHash = new Hashtable<String, String>();
            populateQueryHashTable(queryStrings, queryStringHash);
            return queryStringHash;
        } else {
            return new Hashtable<String, String>();
        }
    }

    private static void populateQueryHashTable(final String[] queryStrings,
            final Hashtable<String, String> queryStringHash) {
        for (String string : queryStrings) {
            if (string.contains("=")) {
                final String[] queryString = string.split("=");
                queryStringHash.put(queryString[0], queryString[1]);
            }
        }
    }
}
