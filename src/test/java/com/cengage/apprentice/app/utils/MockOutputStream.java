package com.cengage.apprentice.app.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MockOutputStream extends ByteArrayOutputStream {
	StringBuffer buffer;
	
	public MockOutputStream(){
		buffer = new StringBuffer();
	}

	@Override
	public void write(int b) {
		buffer.append(String.valueOf(b));

	}
	
	public void write(String s) throws IOException {
		buffer.append(s);
	}
	
	public String toString(){
		return buffer.toString();
	}

}
