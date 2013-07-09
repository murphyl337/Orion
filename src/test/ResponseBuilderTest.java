package test;

import static org.junit.Assert.assertEquals;

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
		assertEquals("HTTP/1.1 200 OK\r\n", responseBuilder.generateStatus(200));
		assertEquals("HTTP/1.1 404 Not Found\r\n", responseBuilder.generateStatus(404));
		assertEquals("HTTP/1.1 500 Internal Server Error\r\n", responseBuilder.generateStatus(500));
	}

}
