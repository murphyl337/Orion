package com.cengage.apprentice.app.main;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class OrionServerTest {
	private OrionServer server;
	private ServerSocket serverSocket;
	
	@Before
	public void setup() throws IOException {
		serverSocket = new ServerSocket(8000);
		server = new OrionServer(serverSocket, "");
	}
	
	@After
	public void tearDown(){
		server.stopServer();
	}
	
	@Test
	public void serverIsListeningWhenInstantiated() throws Exception {
		OrionServer newServer = new OrionServer(serverSocket, "");
		assertTrue(newServer.isListening());
		newServer.stopServer();
	}

	@Test
	public void stopServerClosesServerSocket() throws Exception {
		server.stopServer();
		assertTrue(server.getServerSocket().isClosed());
	}
	
	@Test
	public void stopServerSetsListeningToFalse() throws Exception {
		server.stopServer();
		assertFalse(server.isListening());
	}
	
	@Test
	public void stopServerSetsErrorMessageWhenClosingSocketThrowsIOException() throws Exception {
		ServerSocket mockSocket = Mockito.mock(ServerSocket.class);
		doThrow(new IOException()).when(mockSocket).close();
		OrionServer someServer = new OrionServer(mockSocket, "/");
		
		someServer.stopServer();
		
		assertTrue(someServer.getErrorMessage().contains("IOException when closing socket"));
	}
	
	
}
