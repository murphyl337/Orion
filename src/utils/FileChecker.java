package utils;

import java.io.File;
import java.util.HashMap;

public class FileChecker {

	private String rootDirectory;
	private HashMap<String,String> mimeTypes;

	public FileChecker(String rootDirectory) {
		this.rootDirectory = rootDirectory;
		mimeTypes = new HashMap<String,String>();
		mimeTypes.put("html", "text/html");
		mimeTypes.put("css", "text/css");
		mimeTypes.put("js", "text/javascript");
	}

	public boolean fileExists(String fileName) {
		File file = new File(rootDirectory + fileName);
		return file.exists();
	}

	public String getFileExtension(String file) {
		int extensionIndex = file.indexOf(".");
		return file.substring(extensionIndex + 1);
	}

	public String getMimeType(String extension) {
		return mimeTypes.get(extension);
	}
	
	

}
