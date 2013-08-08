package com.cengage.apprentice.app.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class OrionTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Test
	public void parseArgsChangesPortForValidPortInput() throws Exception {
		String[] arguments = new String[] {"-p", "5000"};
		
		Orion.parseArgs(arguments);
		
		assertEquals(5000, Orion.getPort());
	}
	
	@Test
	(expected = NumberFormatException.class)
	public void parseArgsThrowsNumberFormatExceptionForInvalidPortArgument() throws Exception {
		String[] arguments = new String[] {"-p", "notANumber"};
		
		Orion.parseArgs(arguments);
	}
	
	@Test
	public void parseArgsChangesRootForExistingDirectory() throws Exception {
		String[] arguments = new String[] {"-d", folder.getRoot().getAbsolutePath()};
		
		Orion.parseArgs(arguments);
		
		assertEquals(folder.getRoot().getAbsolutePath(), Orion.getRootDirectory());
	}
	
	@Test
	public void parseArgsDoesNotChangeRootForNonExistingDirectory() throws Exception {
		String[] arguments = new String[] { "-d",
				"nope" };

		Orion.parseArgs(arguments);

		assertFalse(Orion.getRootDirectory().equals(folder.getRoot().getAbsolutePath()));
	}
}
