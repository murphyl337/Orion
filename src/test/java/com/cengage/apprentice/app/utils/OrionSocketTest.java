package com.cengage.apprentice.app.utils;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrionSocketTest {
    OrionSocket socket;
    MockServerSocket serverSocket;
    
    @Before
    public void setup() throws IOException{
        socket = new OrionSocket(2000);
        serverSocket = new MockServerSocket("test");
    }

    @After
    public void tearDown(){
        socket.close();
    }
    
    @Test
    public void portConstructorTest() throws Exception {
        assertEquals(2000, socket.getLocalPort());
    }
    
    @Test
    public void socketIsClosedOnInstantiation() throws Exception {
        assertTrue(socket.isClosed());
    }

    @Test
    public void successfulAcceptOpensSocket() throws Exception {
        OrionSocket socket = new OrionSocket(2000, serverSocket);
        
        socket.accept();
        
        assertFalse(socket.isClosed());
    }
    
}
