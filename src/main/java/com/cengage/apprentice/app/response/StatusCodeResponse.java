package com.cengage.apprentice.app.response;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class StatusCodeResponse implements OrionResponse {
    private static final String UTF8 = "UTF-8";
    /**
     * 
     */
    private static final long serialVersionUID = -1176223168018748216L;
    private final int code;
    private String header;
    private String body;

    public StatusCodeResponse(final int code) {
        this.code = code;
        setBody();
        setHeader();
    }

    public void setHeader() {
        final ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setStatus(code);
        responseHeader.setContentType("text/html");
        responseHeader.setContentLength(Long.valueOf(getBody().length()));

        this.header = responseHeader.composeHeader();
    }

    public void write(final OutputStream output, final Object body) throws IOException {
        output.write(getHeader().getBytes(Charset.forName(UTF8)));
        output.write(((String) body).getBytes(Charset.forName(UTF8)));
        output.flush();
    }

    public void setBody() {
        this.body = "<html><body><h2>" + "Status Code: " + code
                + "</h2>"
                + "<p>Sorry, the file you've requested was not found :(</p>"
                + "</body></html>";

    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

}
