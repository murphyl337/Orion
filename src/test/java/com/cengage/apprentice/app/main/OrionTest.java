package com.cengage.apprentice.app.main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.cengage.apprentice.app.utils.FileChecker;

public class OrionTest {
	@Test
	public void parseArgsChangesPortForValidPortInput() throws Exception {
		String[] arguments = new String[] {"-p", "5000"};
		
		Orion.parseArgs(arguments);
		
		assertEquals(5000, Orion.port);
	}
	
	@Test
	(expected = NumberFormatException.class)
	public void parseArgsThrowsNumberFormatExceptionForInvalidPortArgument() throws Exception {
		String[] arguments = new String[] {"-p", "notANumber"};
		
		Orion.parseArgs(arguments);
	}
	
	@Test
	public void parseArgsChangesRootDirectory() throws Exception {
		String[] arguments = new String[] {"-d", "someDir"};
		
		Orion.parseArgs(arguments);
		
		assertEquals("someDir", Orion.rootDirectory);
	}
	
//	@Test
//	public void parseArgsChecksIfDirectoryExists() throws Exception {
//		String[] arguments = new String[] {"-d", "someDir"};
//		FileChecker checker = Mockito.mock(FileChecker.class);
//		Orion.parseArgs(arguments);
//		Mockito.verify(checker).directoryExists("someDir");
//	}
}
