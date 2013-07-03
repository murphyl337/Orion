package test;

import static org.junit.Assert.*;

import main.OrionSocket;

import org.junit.Before;
import org.junit.Test;

public class OrionSocketTest {
	private OrionSocket orionSocket;
	
	@Before
	public void setup(){
		orionSocket = new OrionSocket(8080);
	}

	@Test
	public void constructorTest() {
		assertEquals(8080, orionSocket.getPort());
		orionSocket.close();
	}
	
	@Test
	public void testClose() throws Exception {
		orionSocket.close();
		assertTrue(orionSocket.isClosed());
	}

}
