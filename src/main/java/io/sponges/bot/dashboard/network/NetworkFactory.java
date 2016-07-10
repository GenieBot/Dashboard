package io.sponges.bot.dashboard.network;

import io.sponges.bot.dashboard.entity.discord.DiscordGuild;
import org.json.JSONObject;

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

            @Override
            public JSONObject toJson() {
                return new JSONObject().put("name", name).put("id", id);
            }
        };
    }

    public static Network create(DiscordGuild guild) {
        return create(guild.getId(), guild.getName());
    }

}
