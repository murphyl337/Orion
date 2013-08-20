package com.cengage.apprentice.app.utils;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.mockito.Mockito;

public class ResponseRunnerTest {
    
    
    @Test
    public void runCallsProcessorsProcessRequest() throws Exception {
        final RequestProcessor processor = mock(RequestProcessor.class);
        final ResponseRunner runner = new ResponseRunner(processor);
        
        runner.run();
        
        Mockito.verify(processor).processRequest();
    }
}
