package utils;

import java.io.File;

public class FileChecker {

	private String rootDirectory;

	public FileChecker(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	public boolean fileExists(String fileName) {
		File file = new File(rootDirectory + fileName);
		return file.exists();
	}

	public String getFileExtension(String file) {
		int extensionIndex = file.indexOf(".");
		return file.substring(extensionIndex + 1);
	}
	
	

}
