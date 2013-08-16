package com.cengage.apprentice.app.utils;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.OrionResponse;

public class ResponderTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
	RequestParser parser;
	Responder responder;
	File tempFile;

	@Before
	public void setup() throws IOException {
		parser = new RequestParser();
		responder = new Responder(folder.getRoot().getAbsolutePath());
		tempFile = folder.newFile("temp.txt");
	}

	@Test
	public void properRequestFormatWithRootRouteReturns200StatusCode() throws FileNotFoundException {
		String requestString = "GET / HTTP/1.1\r\nHost: localhost:5000\r\n";
		OrionRequest request = parser.parse(requestString);

		OrionResponse response = responder.respond(request);
		assertTrue(response.getHeader().contains("200"));
	}

	@Test
	public void returnsFileResponseForExistingFile() throws FileNotFoundException{
	    String requestString = "GET /temp.txt HTTP/1.1\r\nHost: localhost:5000\r\n";
	    OrionRequest request = parser.parse(requestString);

        OrionResponse response = responder.respond(request);
        assertTrue(response.getHeader().contains("200"));
	}
	
	@Test
	public void returns404StatusCodeForNonExistingFile()
			throws Exception {
		String requestString = "GET /derp.html HTTP/1.1\r\nHost: localhost:5000\r\n";
		OrionRequest request = parser.parse(requestString);

		OrionResponse response = responder.respond(request);
		assertTrue(response.getHeader().contains("404"));
	}
}
