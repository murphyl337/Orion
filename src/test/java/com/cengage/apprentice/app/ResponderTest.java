package com.cengage.apprentice.app;

import static org.junit.Assert.*;
import com.cengage.apprentice.app.main.OrionRequest;

import org.junit.Before;
import org.junit.Test;

import com.cengage.apprentice.app.response.OrionResponse;
import com.cengage.apprentice.app.utils.RequestParser;
import com.cengage.apprentice.app.utils.Responder;

public class ResponderTest {
	RequestParser parser;
	Responder responder;
	
	@Before
	public void setup(){
		parser = new RequestParser();
		responder = new Responder("");
	}
	
	@Test
	public void statusCodeRespondTest() {
		String requestString = "GET / HTTP/1.1\r\nHost: localhost:5000\r\n";
		OrionRequest request = parser.parse(requestString);
		
		OrionResponse response = responder.respond(request);
		assertTrue(response.getHeader().contains("200"));
	}

}
