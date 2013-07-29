package com.cengage.apprentice.app.response;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.google.common.primitives.Bytes;

public class StatusCodeResponseTest {

	StatusCodeResponse response;

	@Before
	public void setup() {
		response = new StatusCodeResponse(200);
	}

	@Test
	public void statusCodeResponseFor200Has200InHeader() throws Exception {

		assertTrue(response.getHeader().contains("200"));
	}

	@Test
	public void statusCodeResponseFor200HasStatusCodeInHTML() throws Exception {
		assertTrue(response.getBody().contains("<h2>Status Code: 200</h2>"));
	}
	
	@Test
	public void writeWillWriteHeaderAndBodyToStream() throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] responseBytes = Bytes.concat(response.getHeader().getBytes(), response.getBody().getBytes());
		response.write(baos, response.getBody());
		assertTrue(Arrays.equals(responseBytes, baos.toByteArray()));
	}
}
