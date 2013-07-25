package com.cengage.apprenctice.app.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.cengage.apprentice.app.main.OrionServer;

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
	public void stopServerClosesServerSocket() throws Exception {
		server.startServer();
		server.stopServer();
		assertTrue(server.getServerSocket().isClosed());
	}
}
