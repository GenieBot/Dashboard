package io.sponges.bot.dashboard.pages.platform;

import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import io.sponges.bot.dashboard.entity.ConfigurationLoader;
import io.sponges.bot.dashboard.entity.discord.DiscordAccount;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

public class AddPlatformPage extends Page {

    private final ConfigurationLoader.Configuration configuration;

    public AddPlatformPage(ConfigurationLoader.Configuration configuration) {
        super("/add_platform", Method.GET, true, true);
        this.configuration = configuration;
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        Session session = request.session();
        int userId = session.attribute("id");
        String email = session.attribute("email");
        builder.with("user_id", userId);
        builder.with("user_email", email);
        String discordAuthUrl = String.format(DiscordAccount.AUTHORIZATION_URL,
                configuration.getJsonObject().getJSONObject("discord").getString("client_id"));
        builder.with("discord_authorization_url", discordAuthUrl);
        return new ModelAndView(builder.build(), "platform/add_platform.ftl");
    }
}
