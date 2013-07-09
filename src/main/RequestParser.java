package main;

public class RequestParser {

	public OrionRequest parse(String requestString) {
		OrionRequest request = new OrionRequest();
		request.setHeader(parseHeader(requestString));
		request.setMethod(parseMethod(requestString));
		request.setRoute(parseRoute(requestString));
		return request;
	}

	private String[] parseHeader(String requestString) {
		String[] header = requestString.split("\\r?\\n");
		return header[0].split(" ");
	}

	private String parseMethod(String requestString) {
		String[] header = parseHeader(requestString);
		return header[0];
	}

	private String parseRoute(String requestString) {
		String[] header = parseHeader(requestString);
		String route = header[1];
		if(hasTrailingSlash(route) || hasFileExtension(route))
			return route;
		return route + "/";
	}

	public boolean hasTrailingSlash(String route) {
		return route.charAt(route.length()-1) == ('/');
	}

	public boolean hasFileExtension(String filePath) {
		int charactersAfterPeriod = (filePath.length() - 1) - filePath.lastIndexOf(".");
        return charactersAfterPeriod >= 2 && charactersAfterPeriod <= 4;
	}
}
