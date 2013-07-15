package response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import utils.HeaderBuilder;

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
	
	public FileResponse(String root, String requestedFile){
		this.filePath = root + requestedFile;
		setBody();
		setHeader();
	}
	
	@Override
	public void setHeader() {
		HeaderBuilder builder = new HeaderBuilder();
		builder.setStatus(200);
		builder.setDate();
		builder.setServer();
		builder.setContentType("text/html");
		builder.setContentLength(new File(filePath).length());

		this.header = builder.getHeader();
	}

	@Override
	public void setBody(){
		try {
			this.body = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void write(OutputStream output) {
		try {
			output.write(getHeader().getBytes(Charset.forName("UTF-8")));
			copyStream(body, output);
			body.close();
			output.flush();
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

	@Override
	public String getHeader() {
		return header;
	}

	@Override
	public FileInputStream getBody() {
		return body;
	}

}
