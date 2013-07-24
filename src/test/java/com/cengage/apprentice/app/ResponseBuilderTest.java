package com.cengage.apprentice.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static com.cengage.apprentice.app.utils.ResponseBuilder.generateContentLength;
import static com.cengage.apprentice.app.utils.ResponseBuilder.generateContentType;
import static com.cengage.apprentice.app.utils.ResponseBuilder.generateDate;
import static com.cengage.apprentice.app.utils.ResponseBuilder.generateServer;
import static com.cengage.apprentice.app.utils.ResponseBuilder.generateStatus;

import org.junit.Before;
import org.junit.Test;

import com.cengage.apprentice.app.utils.ResponseBuilder;

public class ResponseBuilderTest {
	ResponseBuilder responseBuilder;

	@Before
	public void setup(){
		responseBuilder = new ResponseBuilder();
	}
	
	@Test
	public void generateStatusTest() {
		assertEquals("HTTP/1.1 200 OK\r\n", generateStatus(200));
		assertEquals("HTTP/1.1 404 Not Found\r\n", generateStatus(404));
		assertEquals("HTTP/1.1 500 Internal Server Error\r\n", generateStatus(500));
	}
	
	@Test
	public void generateDateTest(){
		assertTrue(generateDate().contains("Date: ")); //pointless test?
	}
	
	@Test
	public void generateServerTest() throws Exception {
		assertTrue(generateServer().equals("Server: Orion\r\n"));
	}
	
	@Test
	public void generateContentLengthTest() throws Exception {
		assertTrue(generateContentLength(new Long(500)).equals("Content-Length: 500\r\n"));
	}
	
	@Test
	public void generateContentTypeTest() throws Exception {
		assertEquals("Content-Type: text/html\r\n", generateContentType("text/html"));
	}

}
