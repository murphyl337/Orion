package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import utils.ResponseHeader;

public class ResponseHeaderTest {
	ResponseHeader responseHeader;

	@Before
	public void setup(){
		responseHeader = new ResponseHeader();
	}
	
	@Test
	public void set200StatusTest() {
		responseHeader.setStatus(200);
		assertEquals("HTTP/1.1 200 OK", responseHeader.getStatus());				
	}

	@Test
	public void set404StatusTest() {
		responseHeader.setStatus(404);
		assertEquals("HTTP/1.1 404 Not Found", responseHeader.getStatus());
	}
	
	@Test
	public void set500StatusTest() throws Exception {
		responseHeader.setStatus(500);
		assertEquals("HTTP/1.1 500 Internal Server Error",responseHeader.getStatus());
	}
	
	@Test
	public void generateDateTest(){
		assertTrue(responseHeader.formatDate().contains("Date: "));
	}
	
	@Test
	public void generateServerTest() throws Exception {
		assertEquals("Server: Orion", responseHeader.getServer());
	}
	
	@Test
	public void generateContentLengthTest() throws Exception {
		responseHeader.setContentLength(new Long(500));
		assertEquals("Content-Length: 500", responseHeader.getContentLength());
	}
	
	@Test
	public void generateContentTypeTest() throws Exception {
		responseHeader.setContentType("text/html");
		assertEquals("Content-Type: text/html", responseHeader.getContentType());
	}

	@Test
	public void composeHeaderTest() throws Exception {
		StringBuilder expectedResult = new StringBuilder();
		String date = responseHeader.formatDate();
		
		expectedResult.append("HTTP/1.1 200 OK\r\n");
		expectedResult.append(date);
		expectedResult.append("\r\n");
		expectedResult.append("Server: Orion\r\n");
		expectedResult.append("Content-Length: 500\r\n");
		expectedResult.append("Content-Type: text/html\r\n");
		expectedResult.append("\r\n");
		
		responseHeader.setStatus(200);
		responseHeader.setContentLength(500L);
		responseHeader.setContentType("text/html");
		
		assertEquals(expectedResult.toString(), responseHeader.composeHeader());
	}
	
}
