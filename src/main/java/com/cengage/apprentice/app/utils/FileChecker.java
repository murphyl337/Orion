package com.cengage.apprentice.app.utils;

import java.io.File;

public class FileChecker {

    private String rootDirectory;

    public static boolean fileExists(final String rootDir, final String fileName) {
        final File file = new File(rootDir + "/" + fileName);
        return file.exists();
    }

    public static boolean directoryExists(final String root, final String directory) {
        final boolean exists = fileExists(root, directory);

        return exists && new File(root + directory)
        .isDirectory();
    }

    public static String getFileExtension(final String file) {
        final char dot = '.';
        final int extensionIndex = file.lastIndexOf(dot);
        return file.substring(extensionIndex + 1);
    }

    public String getRootDirectory() {
        return rootDirectory;
    }

    public void setRootDirectory(final String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

}
