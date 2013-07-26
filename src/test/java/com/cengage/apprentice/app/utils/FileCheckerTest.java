package com.cengage.apprentice.app.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileCheckerTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	FileChecker checker;
	File tempFile;
	
	@Before
	public void setup() throws IOException{
		tempFile = folder.newFile("temp.txt");
		checker = new FileChecker(folder.getRoot().getAbsolutePath());
	}
	
	@Test
	public void fileExistsReturnsTrueForFileWithoutFrontSlashTest() {
		assertTrue(checker.fileExists("temp.txt"));
	}
	
	@Test
	public void fileExistsReturnsTrueForFileWithFrontSlash() throws Exception {
		assertTrue(checker.fileExists("/temp.txt"));
	}
	
	@Test
	public void fileExistsReturnsFalseForFileThatDoesNotExist() throws Exception {
		assertFalse(checker.fileExists("doop.doop"));
	}
	
	@Test
	public void directoryExistsReturnsTrueForRootDirectory() throws Exception {
		assertTrue(checker.directoryExists(""));
	}
	
	@Test
	public void directoryExistsReturnsFalseForNonExistingDirectory() throws Exception {
		assertFalse(checker.directoryExists("shoopdawoop"));
	}
	
	@Test
	public void directoryExistsReturnsFalseForExistingFile() throws Exception {
		assertFalse(checker.directoryExists("temp.txt"));
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
