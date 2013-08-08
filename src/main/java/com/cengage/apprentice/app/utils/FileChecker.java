package com.cengage.apprentice.app.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileChecker {

    private String rootDirectory;
    private final Map<String, String> mimeTypes;

    public FileChecker(final String rootDirectory) {
        this.setRootDirectory(rootDirectory);
        mimeTypes = new HashMap<String, String>();
        mimeTypes.put("html", "text/html");
        mimeTypes.put("css", "text/css");
        mimeTypes.put("js", "text/javascript");
        mimeTypes.put("txt", "plain/text");
    }

    public boolean fileExists(final String fileName) {
        final File file = new File(getRootDirectory() + "/" + fileName);
        return file.exists();
    }

    public boolean directoryExists(final String directory) {
        final boolean exists = fileExists(directory);

        return exists && new File(getRootDirectory() + directory)
        .isDirectory();
    }

    public String getFileExtension(final String file) {
        final char dot = '.';
        final int extensionIndex = file.indexOf(dot);
        return file.substring(extensionIndex + 1);
    }

    public String getMimeType(final String extension) {
        return mimeTypes.get(extension);
    }

    public String getRootDirectory() {
        return rootDirectory;
    }

    public void setRootDirectory(final String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

}
