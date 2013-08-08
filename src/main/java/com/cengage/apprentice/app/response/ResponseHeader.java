package com.cengage.apprentice.app.response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ResponseHeader {
    private static final String HTTP = "HTTP/1.1 ";
    private static final String NEWLINE = "\r\n";
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int NOT_FOUND = 404;
    private static final int OK = 200;


    private String status;
    private Long contentLength;
    private String contentType;

    public void setStatus(final int code) {
        status = HTTP + code + " ";
        switch (code) {
        case OK:
            status += "OK";
            break;

        case NOT_FOUND:
            status += "Not Found";
            break;

        case INTERNAL_SERVER_ERROR:
            status += "Internal Server Error";
            break;
            
        default:
            status += "";
            break;
        }
    }

    public String getStatus() {
        return status;
    }

    public String formatDate() {
        final DateFormat dateFormat = new SimpleDateFormat(
                "E, dd MMM yyyy HH:mm:ss zz", Locale.US);
        return "Date: " + dateFormat.format(new Date());
    }

    public String getServer() {
        return "Server: Orion";
    }

    public void setContentLength(final Long fileLength) {
        contentLength = fileLength;
    }

    public String getContentLength() {
        return "Content-Length: " + contentLength;
    }

    public void setContentType(final String cType) {
        contentType = cType;
    }

    public String getContentType() {
        return "Content-Type: " + contentType;
    }

    public String composeHeader() {
        final StringBuilder header = new StringBuilder();
        header.append(status);
        header.append(NEWLINE);
        header.append(formatDate());
        header.append(NEWLINE);
        header.append(getServer());
        header.append(NEWLINE);
        header.append(getContentLength());
        header.append(NEWLINE);
        header.append(getContentType());
        header.append(NEWLINE);
        header.append(NEWLINE);

        return header.toString();
    }
}
