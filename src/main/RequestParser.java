package main;

public class RequestParser {

	public OrionRequest parse(String requestString) {
		OrionRequest request = new OrionRequest();
		request.setHeader(parseHeader(requestString));
		return request;
	}

	private String[] parseHeader(String requestString) {
		String[] header = requestString.split("\\r?\\n");
		return header[0].split(" ");
	}

}
