package response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import utils.FileChecker;
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
	private FileChecker fileChecker;
	
	public FileResponse(String root, String requestedFile){
		this.filePath = root + requestedFile;
		this.fileChecker = new FileChecker(root);
		setBody();
		setHeader();
	}
	
	@Override
	public void setHeader() {
		HeaderBuilder builder = new HeaderBuilder();
		builder.setStatus(200);
		builder.setDate();
		builder.setServer();
		builder.setContentType(fileChecker.getMimeType(fileChecker.getFileExtension(filePath)));
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

	@Override
	public String getHeader() {
		return header;
	}

	@Override
	public FileInputStream getBody() {
		return body;
	}

}
