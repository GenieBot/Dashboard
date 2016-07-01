package io.sponges.bot.dashboard;

import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public final class Main {

    private static Logger logger;

    public static void main(String[] args) {
        SimpleLoggerFactory factory = new SimpleLoggerFactory();
        logger = factory.getLogger("dashboard");
        Database database = new Database();
        Routes routes;
        try {
            routes = new Routes(database);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PipedOutputStream pipeOut = new PipedOutputStream();
        PipedInputStream pipeIn;
        try {
            pipeIn = new PipedInputStream(pipeOut);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }

    public static Logger getLogger() {
        return logger;
    }
}
