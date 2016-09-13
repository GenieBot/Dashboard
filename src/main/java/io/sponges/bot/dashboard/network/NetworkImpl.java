package io.sponges.bot.dashboard.network;

public class NetworkImpl implements Network {

    private final String name;
    private final String id;

    public NetworkImpl(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }
}
