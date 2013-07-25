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

public class FileResponse implements OrionResponse{
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
	
	public FileResponse(String root, String requestedFile){
		this.filePath = root + requestedFile;
		this.fileChecker = new FileChecker(root);
		setBody();
		setHeader();
	}
	
	public void setHeader() {
		ResponseHeader header = new ResponseHeader();
		header.setStatus(200);
		header.setContentType(fileChecker.getMimeType(fileChecker.getFileExtension(filePath)));
		if(fileChecker.fileExists(filePath))
			header.setContentLength(new File(filePath).length());
		this.header = header.composeHeader();
	}

	public void setBody(){
		try {
			this.body = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void write(OutputStream output) {
		try {
			output = (FileOutputStream) output;
			output.write(getHeader().getBytes(Charset.forName("UTF-8")));
			output.flush();
			copyStream(body, output);
			body.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void copyStream(InputStream input, OutputStream output)
		    throws IOException
		{
			File file = new File(filePath);
		    byte[] bytes = new byte[(int)file.length()];
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
