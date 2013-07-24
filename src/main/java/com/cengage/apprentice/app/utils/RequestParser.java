package com.cengage.apprentice.app.utils;

import java.io.BufferedReader;
import java.util.Scanner;

import com.cengage.apprentice.app.main.OrionRequest;

public class RequestParser {

	public OrionRequest parse(BufferedReader requestReader) {
		String requestString = readerToString(requestReader);
		return parse(requestString);
	}

	public OrionRequest parse(String requestString) {
		OrionRequest request = new OrionRequest();
		try {
			request.setHeader(parseHeader(requestString));
			request.setMethod(parseMethod(requestString));
			request.setRoute(parseRoute(requestString));

		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
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
		if (hasTrailingSlash(route) || hasFileExtension(route))
			return route;
		return route + "/";
	}

	public boolean hasTrailingSlash(String route) {
		return route.charAt(route.length() - 1) == ('/');
	}

	public boolean hasFileExtension(String filePath) {
		int charactersAfterPeriod = (filePath.length() - 1)
				- filePath.lastIndexOf(".");
		return charactersAfterPeriod >= 2 && charactersAfterPeriod <= 4;
	}

	public String readerToString(BufferedReader requestReader) {
		Scanner s = new Scanner(requestReader).useDelimiter("\r\n\r\n");
		return s.hasNext() ? s.next() : "";
	}
}
