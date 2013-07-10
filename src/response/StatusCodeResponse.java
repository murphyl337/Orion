package response;

import utils.HeaderBuilder;

public class StatusCodeResponse implements OrionResponse {
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
	public void setBody() {
		this.body = "<html><body><h2>" + "Status Code: " + String.valueOf(code) + "</h2></body></html>";

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
