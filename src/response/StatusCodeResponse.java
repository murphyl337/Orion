package response;

import java.io.IOException;
import java.io.OutputStream;

import utils.HeaderBuilder;

public class StatusCodeResponse implements OrionResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1176223168018748216L;
	private int code;
	private String header;
	private String body;
	
	public StatusCodeResponse(int code){
		this.code = code;
		setBody();
		setHeader();
	}
	
	@Override
	public void setHeader() {
		HeaderBuilder builder = new HeaderBuilder();
		builder.setStatus(code);
		builder.setContentType("text/plain");
		builder.setContentLength(new Long(getBody().length()));

		this.header = builder.getHeader();
	}

	@Override
	public void write(OutputStream output) {
		try {
			output.write(getBody().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setBody() {
		this.body = "HELLO WORLD";
//		this.body = "<html><body><h2>" + "Status Code: " + String.valueOf(code) + "</h2></body></html>";

	}

	@Override
	public String getHeader() {
		return header;
	}

	@Override
	public String getBody() {
		return body;
	}


}
