package com.cengage.apprentice.app.utils;

import java.io.InputStream;
import java.io.OutputStream;

public class MockServerSocket{
	private InputStream inputStream;
	private OutputStream outputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public OutputStream getOutputStream() {
		return outputStream;
	}
	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
}
