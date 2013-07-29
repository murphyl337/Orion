package com.cengage.apprentice.app.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.cengage.apprentice.app.utils.FileChecker;

public class FileResponse implements OrionResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String filePath;
	private String header;
	private FileInputStream body;
	private FileChecker fileChecker;

	public FileResponse(String root, String requestedPath)
			throws FileNotFoundException {
		this.filePath = root + requestedPath;
		this.fileChecker = new FileChecker(root);
		setBody();
		setHeader();
	}

	public void setHeader() {
		ResponseHeader header = new ResponseHeader();
		header.setStatus(200);
		header.setContentType(fileChecker.getMimeType(fileChecker
				.getFileExtension(filePath)));
		header.setContentLength(new File(filePath).length());
		this.header = header.composeHeader();
	}

	public void setBody() throws FileNotFoundException {
		this.body = new FileInputStream(filePath);
	}

	//This had a try/catch for IOException...
	public void write(OutputStream output, Object body) throws IOException {
		output.write(getHeader().getBytes(Charset.forName("UTF-8")));
		copyToStream(body, output);
	}

	public void copyToStream(Object input, OutputStream output)
			throws IOException {
		File file = new File(filePath);
		byte[] bytes = new byte[(int) file.length()];
		body.read(bytes);
		output.write(bytes);
	}

	public String getHeader() {
		return header;
	}

	public FileInputStream getBody() {
		return body;
	}

}
