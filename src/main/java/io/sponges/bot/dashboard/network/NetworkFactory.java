package io.sponges.bot.dashboard.network;

import io.sponges.bot.dashboard.entity.discord.DiscordGuild;

public final class NetworkFactory {

    public static Network create(String id, String name) {
        return new Network() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getId() {
                return id;
            }
        };
    }

    public static Network create(DiscordGuild guild) {
        return create(guild.getId(), guild.getName());
    }

}
