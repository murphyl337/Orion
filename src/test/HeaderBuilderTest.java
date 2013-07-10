package test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.HeaderBuilder;

public class HeaderBuilderTest {
	HeaderBuilder headerBuilder;

	@Before
	public void setup(){
		headerBuilder = new HeaderBuilder();
	}
	
	@After
	public void teardown(){
		headerBuilder.setHeader("");
	}
	
	@Test
	public void set200StatusTest() {
		headerBuilder.setStatus(200);
		assertEquals("HTTP/1.1 200 OK\r\n", headerBuilder.getHeader());				
	}

	@Test
	public void set404StatusTest() {
		headerBuilder.setStatus(404);
		assertEquals("HTTP/1.1 404 Not Found\r\n", headerBuilder.getHeader());
	}
	
	@Test
	public void set500StatusTest() throws Exception {
		headerBuilder.setStatus(500);
		assertEquals("HTTP/1.1 500 Internal Server Error\r\n", headerBuilder.getHeader());
	}
	
	@Test
	public void generateDateTest(){
		headerBuilder.setDate();
		assertTrue(headerBuilder.getHeader().contains("Date: ")); //pointless test?
	}
	
	@Test
	public void generateServerTest() throws Exception {
		headerBuilder.setServer();
		assertTrue(headerBuilder.getHeader().equals("Server: Orion\r\n"));
	}
	
	@Test
	public void generateContentLengthTest() throws Exception {
		headerBuilder.setContentLength(new Long(500));
		assertTrue(headerBuilder.getHeader().equals("Content-Length: 500\r\n"));
	}
	
	@Test
	public void generateContentTypeTest() throws Exception {
		headerBuilder.setContentType("text/html");
		assertEquals("Content-Type: text/html\r\n", headerBuilder.getHeader());
	}

}
