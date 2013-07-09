package test;

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

}
