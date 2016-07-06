package io.sponges.bot.dashboard.pages.discord;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.sponges.bot.dashboard.Database;
import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import io.sponges.bot.dashboard.Routes;
import io.sponges.bot.dashboard.entity.ConfigurationLoader;
import io.sponges.bot.dashboard.entity.discord.DiscordAccount;
import io.sponges.bot.dashboard.entity.discord.DiscordAccountImpl;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.concurrent.TimeUnit;

public class DiscordEndpointPage extends Page {

    private final ConfigurationLoader.Configuration configuration;
    private final Database database;
    private final Routes.AccountManager accountManager;

    public DiscordEndpointPage(ConfigurationLoader.Configuration configuration, Database database, Routes.AccountManager accountManager) {
        super("/discord_endpoint", Method.GET, false, true);
        this.configuration = configuration;
        this.database = database;
        this.accountManager = accountManager;
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        String code = request.queryParams("code");
        JSONObject discordConfig = configuration.getJsonObject().getJSONObject("discord");
        String clientId = discordConfig.getString("client_id");
        String clientSecret = discordConfig.getString("client_secret");
        String url = String.format(DiscordAccount.TOKEN_URL, clientId, clientSecret, code,
                DiscordAccount.REDIRECT_URL);
        JSONObject body;
        try {
            body = Unirest.post(url).asJson().getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }
        DiscordAccount account = new DiscordAccountImpl(database, request.session(), clientId, clientSecret, body.getString("access_token"),
                body.getString("refresh_token"), body.getInt("expires_in"),
                (int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        accountManager.add(account);
        int userId = account.getSession().attribute("id");
        if (database.isUserPlatformSync(userId)) {
            database.updateUserPlatformValue(userId, "discord", account.getRefreshToken());
        } else {
            database.createUserPlatformSync(userId, null, account.getRefreshToken());
        }
        account.getSession().attribute("alert", "Added discord as a platform!");
        response.redirect("/platforms");
        return null;
    }
}
