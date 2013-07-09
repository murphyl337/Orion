package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.ResponseBuilder.generateContentLength;
import static utils.ResponseBuilder.generateContentType;
import static utils.ResponseBuilder.generateDate;
import static utils.ResponseBuilder.generateServer;
import static utils.ResponseBuilder.generateStatus;

import org.junit.Before;
import org.junit.Test;

import utils.ResponseBuilder;

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
