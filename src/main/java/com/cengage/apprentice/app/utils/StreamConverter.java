package com.cengage.apprentice.app.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class StreamConverter {

	public static String inputStreamToString(InputStream inputStream) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		return readerToString(reader);
	}
	
	public static String readerToString(BufferedReader requestReader) {
		Scanner s = new Scanner(requestReader).useDelimiter("\r\n\r\n");
		return s.hasNext() ? s.next() : "";
	}

}
