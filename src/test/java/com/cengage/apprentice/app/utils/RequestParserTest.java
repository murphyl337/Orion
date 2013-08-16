package com.cengage.apprentice.app.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Hashtable;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.cengage.apprentice.app.main.OrionRequest;

public class RequestParserTest {
    public @Rule
    ExpectedException thrown = ExpectedException.none();

    private static final String MOCK_HTTP_VERSION = "HTTP/1.1";
    private static final String MOCK_HTTP_ROUTE = "/";
    private static final String MOCK_HTTP_METHOD = "GET";

    @Test
    public void parseMethodSetsGETAsRequestMethodForGetRequest()
            throws Exception {
        String requestString = "GET / HTTP/1.1\n";
        OrionRequest request = RequestParser.parse(requestString);
        assertEquals(MOCK_HTTP_METHOD, request.getMethod());
    }

    @Test
    public void parseRouteAddsTrailingSlashToRouteIfNoFileExtensionFound() {
        String requestString = "GET /doop HTTP/1.1\n derp";
        OrionRequest request = RequestParser.parse(requestString);
        assertEquals("/doop/", request.getRoute());
    }
    
    @Test
    public void parseRawRouteReturnsRouteWithQueryStrings() throws Exception {
        String requestString = "GET /index.html?move=1&player=X HTTP/1.1\n derp";
        OrionRequest request = RequestParser.parse(requestString);
        assertTrue("/index.html?move=1&player=X".equals(request.getRawRoute()));
    }

    @Test
    public void hasTrailingSlashIsFalseForStringsNotEndingInBackSlash()
            throws Exception {
        String route = "/doop";
        assertFalse(RequestParser.hasTrailingSlash(route));
    }

    @Test
    public void hasTrailingSlashIsTrueForStringsEndingWithBackSlash()
            throws Exception {
        String route = "doop/";
        assertTrue(RequestParser.hasTrailingSlash(route));
    }

    @Test
    public void parseRouteDoesNotAddTrailingSlashToRouteIfFileExtensionFound()
            throws Exception {
        String requestString = "GET /index.html HTTP/1.1\n derp";
        OrionRequest request = RequestParser.parse(requestString);
        assertEquals("/index.html", request.getRoute());
    }
    
    @Test
    public void containsQueryStringReturnsTrueForRequestWithQueries() throws Exception {
        String requestString = "GET /index.html?move=1&player=X HTTP/1.1\n derp";
        assertTrue(RequestParser.containsQueryString(requestString));
    }
    
    @Test
    public void parseRouteDoesNotIncludeQueryStrings() throws Exception {
        String requestString = "GET /index.html?move=1&player=X HTTP/1.1\n derp";
        OrionRequest request = RequestParser.parse(requestString);
        assertEquals("/index.html", request.getRoute());
    }

    @Test
    public void hasFileExtensionIsTrueForFileExtensionsWithLessThanFiveCharactersAndMoreThanTwoChars()
            throws Exception {
        assertTrue(RequestParser.hasFileExtension("shoop/doop.js"));
        assertTrue(RequestParser.hasFileExtension("shoop/doop.xml"));
        assertTrue(RequestParser.hasFileExtension("index.html"));
    }

    @Test
    public void hasFileExtensionIsFalseForNonFileStrings() throws Exception {
        assertFalse(RequestParser.hasFileExtension("shoop"));
    }

    @Test
    public void hasFileExtensionIsFalseForFileExtensionsGreaterThanFiveCharacters()
            throws Exception {
        assertFalse(RequestParser.hasFileExtension("shoop.superlongfileext"));
    }

    @Test
    public void hasFileExtensionIsFalseForFileExtensionsLessThanTwoCharacters()
            throws Exception {
        assertFalse(RequestParser.hasFileExtension("shoop.s"));
    }

    @Test
    public void parseReturnsRequestWith404AsRouteForEmptyRequest()
            throws Exception {
        final OrionRequest request = RequestParser.parse("");
        
        assertEquals("404", request.getRoute());
    }
    
    @Test
    public void parseQueryStringsReturnsHashTableWithCorrectKeysAndValuesForRequestWithQueries() throws Exception {
        final String requestString = "GET /index.html?move=1&player=X HTTP/1.1\n derp";
        
        final OrionRequest request = RequestParser.parse(requestString);
        
        assertEquals(request.getQueries().get("move"), "1");
        assertEquals(request.getQueries().get("player"), "X");
    }
    
    @Test
    public void parseQueryStringReturnsEmptyHashTableForRequestWithoutQueries() throws Exception {
        final String requestString = "GET /index.html HTTP/1.1\n derp";
        
        final OrionRequest request = RequestParser.parse(requestString);
        
        assertEquals(request.getQueries().size(), 0);
    }
}
