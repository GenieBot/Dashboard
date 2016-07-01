package io.sponges.bot.dashboard.entity.discord;

public class DiscordGuild {

    private final String id;
    private final String name;
    private final String icon;
    private final boolean owner;
    private final long permissions;

    protected DiscordGuild(String id, String name, String icon, boolean owner, long permissions) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.owner = owner;
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public boolean isOwner() {
        return owner;
    }

    public long getPermissions() {
        return permissions;
    }
}
