package io.sponges.bot.dashboard;

import java.io.IOException;

public final class Main {

    public static void main(String[] args) {
        try {
            new Routes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
