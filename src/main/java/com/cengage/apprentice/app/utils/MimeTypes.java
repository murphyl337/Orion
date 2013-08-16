package com.cengage.apprentice.app.utils;

import java.util.HashMap;

public final class MimeTypes {
    private static HashMap<String, String> mimeTypes;
    
    private MimeTypes() {}

    public static String get(final String fileExtension) {
        return getMimeTypes().get(fileExtension);
    }

    private static HashMap<String, String> getMimeTypes() {
        if(mimeTypes == null){
            populateMimeTypes();
        }
        return mimeTypes;
    }

    private static void populateMimeTypes() {
        mimeTypes = new HashMap<String, String>();
        mimeTypes.put("html", "text/html");
        mimeTypes.put("js", "text/javascript");
        mimeTypes.put("txt", "text/plain");
        mimeTypes.put("css", "text/css");
    }
}
