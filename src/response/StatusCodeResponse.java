package response;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

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
		builder.setContentType("text/html");
		builder.setContentLength(new Long(getBody().length()));

		this.header = builder.getHeader();
	}

	@Override
	public void write(OutputStream output) {
		try {
			output.write(getBody().getBytes(Charset.forName("UTF-8")));
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setBody() {
		this.body = "<html><body><h2>" + "Status Code: " + String.valueOf(code) + "</h2>" +
				"<p>Sorry, the file you've requested was not found :(</p>" + "</body></html>";

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
