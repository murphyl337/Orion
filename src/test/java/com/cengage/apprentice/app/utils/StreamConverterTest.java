package com.cengage.apprentice.app.utils;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.junit.Test;

public class StreamConverterTest {
	
	@Test
	public void inputStreamToStringReturnsContentsOfStreamAsString()
			throws Exception {
		String testString = "This is a test.";
		ByteArrayInputStream bais = new ByteArrayInputStream(testString.getBytes());
		assertEquals(testString, StreamConverter.inputStreamToString(bais));		
	}
	
	@Test
	public void convertsBufferedReaderToStringForReaderWithContent() throws Exception {
		String request = "GET / HTTP/1.1\r\nHost: localhost:5000\r\n\r\n";
		String expectedRequestString = request.replaceAll("\r\n\r\n", "");
		BufferedReader requestReader = new BufferedReader(
				new InputStreamReader(new ByteArrayInputStream(
						request.getBytes())));
		assertEquals(expectedRequestString, StreamConverter.readerToString(requestReader));
	}
	
	@Test
	public void convertsBufferedReaderToStringReturnsEmptyStringForEmptyReader() throws Exception {
		String request = "";
		BufferedReader requestReader = new BufferedReader(
				new InputStreamReader(new ByteArrayInputStream(
						request .getBytes())));
		assertEquals("", StreamConverter.readerToString(requestReader));
	}
}
