package com.cengage.apprentice.app.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

final class StreamConverter {
    private StreamConverter(){}

    public static String inputStreamToString(final InputStream inputStream) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return readerToString(reader);
    }

    public static String readerToString(final BufferedReader requestReader) {
        final Scanner s = new Scanner(requestReader).useDelimiter("\r\n\r\n");
        return s.hasNext() ? s.next() : "";
    }

}
