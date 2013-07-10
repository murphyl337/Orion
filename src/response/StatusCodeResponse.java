package response;

import utils.HeaderBuilder;

public class StatusCodeResponse implements OrionResponse {
	private int code;
	private String header;
	private String body;
	
	public StatusCodeResponse(int code){
		this.code = code;
		setHeader();
		setBody();
	}
	
	@Override
	public void setHeader() {
		HeaderBuilder builder = new HeaderBuilder();
		
	}

	@Override
	public void setBody() {
		// TODO Auto-generated method stub

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
