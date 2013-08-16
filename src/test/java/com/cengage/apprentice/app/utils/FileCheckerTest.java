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
	
	File tempFile;
	String rootDir;
	
	
	@Before
	public void setup() throws IOException{
		tempFile = folder.newFile("temp.txt");
		rootDir = folder.getRoot().getAbsolutePath();
	}
	
	@Test
	public void fileExistsReturnsTrueForFileWithoutFrontSlashTest() {
		assertTrue(FileChecker.fileExists(rootDir, "temp.txt"));
	}
	
	@Test
	public void fileExistsReturnsTrueForFileWithFrontSlash(){
		assertTrue(FileChecker.fileExists(rootDir, "/temp.txt"));
	}
	
	@Test
	public void fileExistsReturnsFalseForFileThatDoesNotExist(){
		assertFalse(FileChecker.fileExists(rootDir, "doop.doop"));
	}
	
	@Test
	public void directoryExistsReturnsTrueForRootDirectory(){
		assertTrue(FileChecker.directoryExists(rootDir, ""));
	}
	
	@Test
	public void directoryExistsReturnsFalseForNonExistingDirectory(){
		assertFalse(FileChecker.directoryExists(rootDir, "shoopdawoop"));
	}
	
	@Test
	public void directoryExistsReturnsFalseForExistingFile() throws Exception {
		assertFalse(FileChecker.directoryExists(rootDir, "temp.txt"));
	}
	@Test
	public void getFileExtensionTest(){
		assertTrue(FileChecker.getFileExtension("index.html").equals("html"));
		assertTrue(FileChecker.getFileExtension("doop.pdf").equals("pdf"));
	}
	
}
