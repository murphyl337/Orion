package com.cengage.apprentice.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import com.cengage.apprentice.app.main.OrionRequest;

import org.junit.Before;
import org.junit.Test;

import com.cengage.apprentice.app.utils.RequestParser;

public class RequestParserTest {
	RequestParser parser;

	@Before
	public void setup() {
		parser = new RequestParser();
	}

	@Test
	public void parsesHeader() {
		String requestString = "GET / HTTP/1.1\nHost: localhost:5000\n";
		OrionRequest request = parser.parse(requestString);
		assertEquals("GET", request.getHeader()[0]);
		assertEquals("/", request.getHeader()[1]);
		assertEquals("HTTP/1.1", request.getHeader()[2]);
	}

	@Test
	public void parsesMethod() throws Exception {
		String requestString = "GET / HTTP/1.1\nHost: localhost:5000\n";
		OrionRequest request = parser.parse(requestString);
		assertEquals("GET", request.getMethod());
	}

	@Test
	public void parsesRoute() {
		String requestString = "GET /doop HTTP/1.1\n derp";
		OrionRequest request = parser.parse(requestString);
		assertEquals("/doop/", request.getRoute());

		requestString = "GET /index.html HTTP/1.1\n derp";
		request = parser.parse(requestString);
		assertEquals("/index.html", request.getRoute());
	}

	@Test
	public void hasTrailingSlashTest() throws Exception {
		String route = "/doop";
		assertFalse(parser.hasTrailingSlash(route));

		route = "doop/";
		assertTrue(parser.hasTrailingSlash(route));
	}

	@Test
	public void hasFileExtensionTest() throws Exception {
		assertFalse(parser.hasFileExtension("/doop/"));
		assertTrue(parser.hasFileExtension("shoop/doop.js"));
	}

	@Test
	public void convertsBufferedReaderToStringTest() throws Exception {
		String request = "GET / HTTP/1.1\r\nHost: localhost:5000\r\n\r\n";
		String expectedRequestString = request.replaceAll("\r\n\r\n", "");
		BufferedReader requestReader = new BufferedReader(
				new InputStreamReader(new ByteArrayInputStream(
						request.getBytes())));
		assertEquals(expectedRequestString, parser.readerToString(requestReader));
	}
}
