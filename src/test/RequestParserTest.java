package test;

import static org.junit.Assert.*;
import main.OrionRequest;
import main.RequestParser;

import org.junit.Before;
import org.junit.Test;

public class RequestParserTest {
	RequestParser parser;
	
	@Before
	public void setup(){
		parser = new RequestParser();
	}
	
	@Test
	public void parsesHeader() {
		String requestString = "GET / HTTP/1.1\nHost: localhost:5000\nConnection: keep-alive\nCache-Control: max-age=0\nAccept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\nUser-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.57 Safari/537.17\nAccept-Encoding: gzip,deflate,sdch\nAccept-Language: en-US,en;q=0.8\nAccept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.3";
		OrionRequest request = parser.parse(requestString);
		assertEquals("GET", request.getHeader()[0]);
		assertEquals("/", request.getHeader()[1]);
		assertEquals("HTTP/1.1", request.getHeader()[2]);
	}

}
