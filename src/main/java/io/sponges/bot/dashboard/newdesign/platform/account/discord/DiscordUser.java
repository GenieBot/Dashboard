package io.sponges.bot.dashboard.newdesign.platform.account.discord;

public class DiscordUser {

    private final String id;
    private final String username;
    private final String discriminator;
    private final String avatar;
    private final boolean mfaEnabled; // i think this is 2fa?

    protected DiscordUser(String id, String username, String discriminator, String avatar, boolean mfaEnabled) {
        this.id = id;
        this.username = username;
        this.discriminator = discriminator;
        this.avatar = avatar;
        this.mfaEnabled = mfaEnabled;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean isMfaEnabled() {
        return mfaEnabled;
    }
}
