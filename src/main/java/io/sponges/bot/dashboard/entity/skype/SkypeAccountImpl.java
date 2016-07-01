package io.sponges.bot.dashboard.entity.skype;

import spark.Session;

public class SkypeAccountImpl implements SkypeAccount {

    private final Session session;

    public SkypeAccountImpl(Session session) {
        this.session = session;
    }

    @Override
    public Session getSession() {
        return session;
    }
}
