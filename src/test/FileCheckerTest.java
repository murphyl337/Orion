package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import utils.FileChecker;

public class FileCheckerTest {
	FileChecker checker;
	
	@Before
	public void setup(){
		checker = new FileChecker("/Users/tmurphy/Documents/workspace/Orion/web/");
	}
	
	@Test
	public void fileExistsTest() {
		assertTrue(checker.fileExists("index.html"));
		assertFalse(checker.fileExists("doop.doop"));
	}
	
	@Test
	public void getFileExtensionTest(){
		assertTrue(checker.getFileExtension("index.html").equals("html"));
		assertTrue(checker.getFileExtension("doop.pdf").equals("pdf"));
	}

	@Test
	public void getMIMETypeTest() throws Exception {
		assertEquals("text/html", checker.getMimeType("html"));
		assertEquals("text/css", checker.getMimeType("css"));
		assertEquals("text/javascript", checker.getMimeType("js"));
	}
	
}
