package com.cengage.apprentice.app.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MockSocket extends Socket {
    private boolean closed = false;
    InputStream input;
    OutputStream output;

    public MockSocket(String mockInput) {
        this.input = new ByteArrayInputStream(mockInput.getBytes());
        this.output = new ByteArrayOutputStream();
    }

    public boolean isClosed() {
        return closed;
    }

    public OutputStream getOutputStream() {
        return output;
    }

    public InputStream getInputStream() {
        return input;
    }

    public void close() {
        this.closed = true;
    }

}
