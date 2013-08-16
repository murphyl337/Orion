package com.cengage.apprentice.app.main;

import java.io.IOException;
import java.net.ServerSocket;

import org.apache.log4j.Logger;

import com.cengage.apprentice.app.utils.OrionConfigurator;

final class Orion {
    private static final Logger LOGGER = Logger.getLogger(Orion.class);

    private Orion() {
    }

    public static void main(final String[] args) throws IOException {
        OrionConfigurator.parseArgs(args);
        final ServerSocket serverSocket = new ServerSocket(OrionConfigurator.getPort());
        final OrionServer server = new OrionServer(serverSocket,
                OrionConfigurator.getRootDirectory());
        LOGGER.info("Orion server listening on port: " + OrionConfigurator.getPort());
        server.listen();
    }
}
