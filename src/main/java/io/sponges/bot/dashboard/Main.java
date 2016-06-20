package io.sponges.bot.dashboard;

import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

import java.io.IOException;

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
    }

    public static Logger getLogger() {
        return logger;
    }
}
