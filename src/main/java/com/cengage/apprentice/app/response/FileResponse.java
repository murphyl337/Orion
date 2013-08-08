package com.cengage.apprentice.app.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.cengage.apprentice.app.utils.FileChecker;

public class FileResponse implements OrionResponse {
    private static final int STATUSCODE200 = 200;
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private final String filePath;
    private String header;
    private FileInputStream body;
    private final FileChecker fileChecker;

    public FileResponse(final String root, final String requestedPath)
            throws FileNotFoundException {
        this.filePath = root + requestedPath;
        this.fileChecker = new FileChecker(root);
        setBody();
        setHeader();
    }

    public void setHeader() {
        final ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setStatus(STATUSCODE200);
        responseHeader.setContentType(fileChecker.getMimeType(fileChecker
                .getFileExtension(filePath)));
        responseHeader.setContentLength(new File(filePath).length());
        this.header = responseHeader.composeHeader();
    }

    public void setBody() throws FileNotFoundException {
        this.body = new FileInputStream(filePath);
    }

    public void write(final OutputStream output, final Object body) throws IOException {
        output.write(getHeader().getBytes(Charset.forName("UTF-8")));
        copyToStream(body, output);
    }

    
    protected void copyToStream(final Object input, final OutputStream output)
            throws IOException {
//        final File file = new File(filePath);
//        final byte[] bytes = new byte[(int) file.length()];
//        body.read(bytes);
//        output.write(bytes);
    }

    public String getHeader() {
        return header;
    }

    public FileInputStream getBody() {
        return body;
    }

}
