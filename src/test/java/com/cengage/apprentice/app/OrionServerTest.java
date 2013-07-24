package com.cengage.apprentice.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import com.cengage.apprentice.app.main.OrionServer;

import org.junit.Before;
import org.junit.Test;

public class OrionServerTest {
	private OrionServer server;

	@Before
	public void setup() {
		server = new OrionServer(8000, "");
	}

	@Test
	public void startServerOpensServerSocket() throws Exception {
		server.startServer();
		assertEquals(8000, server.getServerSocket().getLocalPort());
		server.getServerSocket().close();
	}

	@Test
	public void startServerSetsRunningToTrueWhenSocketOpened()
			throws IOException {
		server.startServer();
		assertTrue(server.isRunning());
		server.getServerSocket().close();
	}

	@Test
	public void stopServerClosesServerSocket() throws Exception {
		server.startServer();
		assertTrue(server.isRunning());
		server.stopServer();
		assertTrue(server.getServerSocket().isClosed());
	}

	@Test
	public void stopServerSetsRunningToFalse() throws Exception {
		server.startServer();
		assertTrue(server.isRunning());
		server.stopServer();
		assertFalse(server.isRunning());
	}

}
