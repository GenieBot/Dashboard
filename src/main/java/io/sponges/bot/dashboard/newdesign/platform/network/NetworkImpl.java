package io.sponges.bot.dashboard.newdesign.platform.network;

public class NetworkImpl implements Network {

    private final String name;
    private final String id;
    private final String icon;

    public NetworkImpl(String name, String id, String icon) {
        this.name = name;
        this.id = id;
        this.icon = icon;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
