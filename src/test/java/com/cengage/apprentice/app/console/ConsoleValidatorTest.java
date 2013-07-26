
package com.cengage.apprentice.app.console;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static com.cengage.apprentice.app.console.ConsoleValidator.isInteger;
import static com.cengage.apprentice.app.console.ConsoleValidator.isValidPortInput;


import org.junit.Test;

import com.cengage.apprentice.app.console.ConsoleValidator;

public class ConsoleValidatorTest {
	
	@Test
	public void isIntegerTest(){
		assertTrue(isInteger("0"));
		assertTrue(isInteger("1025"));
		assertFalse(isInteger(""));
	}
	
	@Test
	public void validatesPortInputTest() {
		assertTrue(isValidPortInput(""));
		assertTrue(isValidPortInput("1025"));
		assertFalse(isValidPortInput("65536"));
		assertFalse(isValidPortInput("lksjdflkjsldfj"));
	}
	
	@Test
	public void validatesYesOrNoInputTest(){
		assertTrue(ConsoleValidator.isValidYesNoInput("y"));
		assertTrue(ConsoleValidator.isValidYesNoInput("Y"));
		assertTrue(ConsoleValidator.isValidYesNoInput("n"));
		assertFalse(ConsoleValidator.isValidYesNoInput("lksjdf"));
		assertFalse(ConsoleValidator.isValidYesNoInput(""));
	}
}
