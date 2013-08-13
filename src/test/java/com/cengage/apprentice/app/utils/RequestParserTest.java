package com.cengage.apprentice.app.utils;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Hashtable;

import org.junit.Before;
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

    RequestParser parser;

    @Before
    public void setup() {
        parser = new RequestParser();
    }

    @Test
    public void parseMethodSetsGETAsRequestMethodForGetRequest()
            throws Exception {
        String requestString = "GET / HTTP/1.1\n";
        OrionRequest request = parser.parse(requestString);
        assertEquals(MOCK_HTTP_METHOD, request.getMethod());
    }

    @Test
    public void parseRouteAddsTrailingSlashToRouteIfNoFileExtensionFound() {
        String requestString = "GET /doop HTTP/1.1\n derp";
        OrionRequest request = parser.parse(requestString);
        assertEquals("/doop/", request.getRoute());
    }
    
    @Test
    public void parseRawRouteReturnsRouteWithQueryStrings() throws Exception {
        String requestString = "GET /index.html?move=1&player=X HTTP/1.1\n derp";
        OrionRequest request = parser.parse(requestString);
        assertTrue("/index.html?move=1&player=X".equals(request.getRawRoute()));
    }

    @Test
    public void hasTrailingSlashIsFalseForStringsNotEndingInBackSlash()
            throws Exception {
        String route = "/doop";
        assertFalse(parser.hasTrailingSlash(route));
    }

    @Test
    public void hasTrailingSlashIsTrueForStringsEndingWithBackSlash()
            throws Exception {
        String route = "doop/";
        assertTrue(parser.hasTrailingSlash(route));
    }

    @Test
    public void parseRouteDoesNotAddTrailingSlashToRouteIfFileExtensionFound()
            throws Exception {
        String requestString = "GET /index.html HTTP/1.1\n derp";
        OrionRequest request = parser.parse(requestString);
        assertEquals("/index.html", request.getRoute());
    }
    
    @Test
    public void containsQueryStringReturnsTrueForRequestWithQueries() throws Exception {
        String requestString = "GET /index.html?move=1&player=X HTTP/1.1\n derp";
        assertTrue(parser.containsQueryString(requestString));
    }
    
    @Test
    public void parseRouteDoesNotIncludeQueryStrings() throws Exception {
        String requestString = "GET /index.html?move=1&player=X HTTP/1.1\n derp";
        OrionRequest request = parser.parse(requestString);
        assertEquals("/index.html", request.getRoute());
    }

    @Test
    public void hasFileExtensionIsTrueForFileExtensionsWithLessThanFiveCharactersAndMoreThanTwoChars()
            throws Exception {
        assertTrue(parser.hasFileExtension("shoop/doop.js"));
        assertTrue(parser.hasFileExtension("shoop/doop.xml"));
        assertTrue(parser.hasFileExtension("index.html"));
    }

    @Test
    public void hasFileExtensionIsFalseForNonFileStrings() throws Exception {
        assertFalse(parser.hasFileExtension("shoop"));
    }

    @Test
    public void hasFileExtensionIsFalseForFileExtensionsGreaterThanFiveCharacters()
            throws Exception {
        assertFalse(parser.hasFileExtension("shoop.superlongfileext"));
    }

    @Test
    public void hasFileExtensionIsFalseForFileExtensionsLessThanTwoCharacters()
            throws Exception {
        assertFalse(parser.hasFileExtension("shoop.s"));
    }

    @Test
    public void parseThrowsArrayIndexOutOfBoundsExceptionForIncompleteRequest()
            throws Exception {
        String badRequest = "doop";
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        parser.parse(badRequest);
    }
    
    @Test
    public void parseQueryStringsReturnsHashTableWithCorrectKeysAndValuesForRequestWithQueries() throws Exception {
        String requestString = "GET /index.html?move=1&player=X HTTP/1.1\n derp";
        final Hashtable<String, String> queryStringTable = parser.parseQueryStrings(requestString);
        assertEquals(queryStringTable.get("move"), "1");
        assertEquals(queryStringTable.get("player"), "X");
    }
    
    @Test
    public void parseQueryStringReturnsEmptyHashTableForRequestWithoutQueries() throws Exception {
        String requestString = "GET /index.html HTTP/1.1\n derp";
        final Hashtable<String, String> queryStringTable = parser.parseQueryStrings(requestString);
        assertEquals(queryStringTable.size(), 0);
    }
}
