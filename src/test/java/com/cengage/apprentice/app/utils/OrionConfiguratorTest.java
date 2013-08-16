package com.cengage.apprentice.app.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class OrionConfiguratorTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    @Test
    public void parseArgsChangesPortForValidPortInput() throws Exception {
        String[] arguments = new String[] {"-p", "5000"};
        
        OrionConfigurator.parseArgs(arguments);
        
        assertEquals(5000, OrionConfigurator.getPort());
    }
    
    @Test
    (expected = NumberFormatException.class)
    public void parseArgsThrowsNumberFormatExceptionForInvalidPortArgument() throws Exception {
        String[] arguments = new String[] {"-p", "notANumber"};
        
        OrionConfigurator.parseArgs(arguments);
    }
    
    @Test
    public void parseArgsChangesRootForExistingDirectory() throws Exception {
        String[] arguments = new String[] {"-d", folder.getRoot().getAbsolutePath()};
        
        OrionConfigurator.parseArgs(arguments);
        
        assertEquals(folder.getRoot().getAbsolutePath(), OrionConfigurator.getRootDirectory());
    }
    
    @Test
    public void parseArgsDoesNotChangeRootForNonExistingDirectory() throws Exception {
        String[] arguments = new String[] { "-d",
                "nope" };

        OrionConfigurator.parseArgs(arguments);

        assertFalse(OrionConfigurator.getRootDirectory().equals(folder.getRoot().getAbsolutePath()));
    }
}
