package com.cengage.apprentice.app.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MimeTypesTest {

    @Test
    public void getForHtmlReturnsTextHtmlMimeType() throws Exception {
        assertTrue("text/html".equals(MimeTypes.get("html")));
    }
    
    @Test
    public void getForJsReturnsTextJavascriptMimeType() throws Exception {
        assertTrue("text/javascript".equals(MimeTypes.get("js")));
    }
}
