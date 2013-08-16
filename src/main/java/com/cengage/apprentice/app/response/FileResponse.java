package com.cengage.apprentice.app.response;

import static org.apache.commons.io.FileUtils.copyFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import com.cengage.apprentice.app.utils.FileChecker;
import com.cengage.apprentice.app.utils.MimeTypes;

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
    private File body;

    public FileResponse(final String root, final String requestedPath)
            throws FileNotFoundException {
        this.filePath = root + requestedPath;
        setBody();
        setHeader();
    }

    public void setHeader() {
        final ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setStatus(STATUSCODE200);
        responseHeader.setContentType(MimeTypes.get(FileChecker.getFileExtension(filePath)));
        responseHeader.setContentLength(new File(filePath).length());
        this.header = responseHeader.composeHeader();
    }

    public void setBody(){
        this.body = new File(filePath);
    }

    public void write(final OutputStream output, final Object body) throws IOException {
        output.write(getHeader().getBytes(Charset.forName("UTF-8")));
        copyFile((File)body, output);
    }

    public String getHeader() {
        return header;
    }

    public File getBody() {
        return body;
    }

}
