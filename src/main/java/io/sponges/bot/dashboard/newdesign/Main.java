package io.sponges.bot.dashboard.newdesign;

import java.io.IOException;

public final class Main {

    public static final String[] AVAILABLE_PLATFORMS = {
            "skype", "discord"
    };

    public static void main(String[] args) {
        Database database = new Database();
        Routes routes;
        try {
            routes = new Routes(database);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
