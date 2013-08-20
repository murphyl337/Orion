package com.cengage.apprentice.app.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cengage.apprentice.app.main.OrionRequest;

public class RequestProcessorTest {
    private static final String BAD_REQUEST = "doopdoopdoop";
    private static final String GOOD_REQUEST = "GET / HTTP/1.1";
    private RequestProcessor processor;
    private MockServerSocket serverSocket;

    @Before
    public void setup() {
        processor = new RequestProcessor();
    }

    @Test
    public void getRequestReturnsProperRequestObjectFromStream()
            throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(
                GOOD_REQUEST.getBytes());

        OrionRequest request = processor.getRequest(bais);

        assertEquals(request.getMethod(), "GET");
        assertEquals(request.getRoute(), "/");
    }

    @Test
    public void getRequestReturnsEmptyRequestForBadRequestString()
            throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(
                BAD_REQUEST.getBytes());

        OrionRequest request = processor.getRequest(bais);

        assertNotNull(request);
    }

    @Test
    public void getResponseCallsResponseRunnersResponderRespondMethod()
            throws Exception {
        OrionRequest request = RequestParser.parse(GOOD_REQUEST);
        Responder mockResponder = Mockito.mock(Responder.class);
        processor.setResponder(mockResponder);

        processor.getResponse(request);

        Mockito.verify(mockResponder).respond(request);

    }

    @Test
    public void processRequestWritesSocketsResponseForInputStreamToOutputStream()
            throws Exception {
        serverSocket = new MockServerSocket(GOOD_REQUEST);
        MockSocket socket = (MockSocket) serverSocket.accept();
        processor = new RequestProcessor(socket, "", new Responder(""));

        processor.processRequest();
        ByteArrayOutputStream baos = (ByteArrayOutputStream) socket
                .getOutputStream();

        assertTrue(baos.toString().contains("Status Code: 200"));
    }
}
