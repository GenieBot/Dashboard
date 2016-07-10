package io.sponges.bot.dashboard.entity.skype;

import io.sponges.bot.dashboard.network.Network;
import spark.Session;

import java.util.Map;

public class SkypeAccountImpl implements SkypeAccount {

    private final Session session;

    public SkypeAccountImpl(Session session) {
        this.session = session;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Network[] getNetworks() {
        return null; // TODO
    }

    @Override
    public Map<String, Network> getNetworkCache() {
        return null;
    }

    @Override
    public void updateNetworkCache(Network[] networks) {

    }
}
