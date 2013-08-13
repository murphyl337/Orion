package com.cengage.apprentice.app.utils;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.OrionResponse;
import com.google.common.primitives.Bytes;

public class ResponseRunnerTest {
	private static final String BAD_REQUEST = "doopdoopdoop";
	private static final String GOOD_REQUEST = "GET / HTTP/1.1";
	private ResponseRunner runner;

	@Before
	public void setup() {
		runner = new ResponseRunner();
	}

	@Test
	public void constructorSetsPassedFields() {
		ByteArrayInputStream bais = new ByteArrayInputStream("".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		ResponseRunner newRunner = new ResponseRunner(bais, baos, "rootDir");

		assertEquals(bais, newRunner.getInputStream());
		assertEquals(baos, newRunner.getOutputStream());
		assertEquals("rootDir", newRunner.getRootDir());
	}

	@Test
	public void getRequestReturnsProperRequestObjectFromStream()
			throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(
				GOOD_REQUEST.getBytes());

		OrionRequest request = runner.getRequest(bais);

		assertEquals(request.getMethod(), "GET");
		assertEquals(request.getRoute(), "/");
	}

	@Test
	public void getRequestReturnsNullBadRequestString()
			throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(
				BAD_REQUEST.getBytes());

		OrionRequest request = runner.getRequest(bais);

		assertNull(request);
	}

	@Test
	public void getResponseReturnsResponseWithStatusCode200ForRequestWithRootRoute()
			throws Exception {
		OrionRequest request = new RequestParser().parse(GOOD_REQUEST);

		OrionResponse response = runner.getResponse(request);

		assertTrue(response.getHeader().contains("200"));
	}

	@Test
	public void processRequestWritesContentsOfResponseToOutputStream()
			throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(
				GOOD_REQUEST.getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		OrionResponse response = runner.getResponse(new RequestParser()
				.parse(GOOD_REQUEST));
		byte[] responseBytes = Bytes.concat(response.getHeader().getBytes(),
				((String) response.getBody()).getBytes());

		runner.processRequest(bais, baos, "");

		assertTrue(Arrays.equals(responseBytes, baos.toByteArray()));

	}

	@Test
	public void runCallsProcessRequestWithSocketsStreams() throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(GOOD_REQUEST.getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ResponseRunner runner = Mockito.spy(new ResponseRunner(bais, baos, ""));
		
		runner.run();
		
		Mockito.verify(runner).processRequest(bais, baos, "");
	}
}
