package com.cengage.apprentice.app.response;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.common.io.Files;
import com.google.common.primitives.Bytes;

public class FileResponseTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	FileResponse response;
	File tempFile;
	
	@Before
	public void setup() throws IOException{
		tempFile = folder.newFile("temp.txt");
		response = new FileResponse(folder.getRoot().getAbsolutePath(), "/temp.txt");
	}

	@Test
	public void fileResponseForExistingFileHas200InHeader() throws Exception {
		assertTrue(response.getHeader().contains("200"));
	}

	@Test
	public void fileResponseForExistingFileHasCorrectFileSizeHeader() throws Exception {
		assertTrue(response.getHeader().contains(String.valueOf(tempFile.length())));
	}
	
	@Test
	public void fileResponseForExistingTxtFileHasTxtMimeTypeInHeader() throws Exception {
		assertTrue(response.getHeader().contains("plain/text"));
	}
	
	@Test
	(expected=FileNotFoundException.class)
	public void fileResponseForNonExistingFileThrowsFileNotFoundException() throws Exception {
		@SuppressWarnings("unused")
		FileResponse badResponse = new FileResponse("bad", "response");
	}
	
	@Test
	public void copyStreamWritesContentsOfInputToOutput() throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		response.copyToStream(response.getBody(), baos);
		assertTrue(Arrays.equals(baos.toByteArray(), Files.toByteArray(tempFile)));
	}
	
	@Test
	public void writeWillWriteHeaderAndContentsOfFileToOutputStream() throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] responseBytes = Bytes.concat(response.getHeader().getBytes(), Files.toByteArray(tempFile));
		response.write(baos, response.getBody());
		assertTrue(Arrays.equals(responseBytes, baos.toByteArray()));
	}
}
