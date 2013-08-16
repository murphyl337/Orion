package com.cengage.apprentice.app.utils;

import org.apache.log4j.Logger;

public class OrionConfigurator {
    private static final int DEFAULT_PORT = 10000;
    private static int port = DEFAULT_PORT;
    private static String rootDirectory = "public";
    
    public static void parseArgs(final String[] args) {
        int index = 0;
        for (String arg : args) {
            checkArgument(args, index, arg);
            index++;
        }
    }

    private static void checkArgument(final String[] args, final int index,
            final String arg) {
        if ("-p".equals(arg)) {
            setPort(Integer.parseInt(args[index + 1]));
        }
        if ("-d".equals(arg)) {
            final String tempDir = args[index + 1];
            if (FileChecker.directoryExists("", tempDir)) {
                setRootDirectory(tempDir);
            }
        }
    }

    public static String getRootDirectory() {
        return rootDirectory;
    }

    private static void setRootDirectory(final String rootDir) {
        rootDirectory = rootDir;
    }

    public static int getPort() {
        return port;
    }

    private static void setPort(final int thePort) {
        port = thePort;
    }
}
