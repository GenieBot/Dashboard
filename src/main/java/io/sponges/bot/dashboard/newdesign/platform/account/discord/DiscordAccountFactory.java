package io.sponges.bot.dashboard.newdesign.platform.account.discord;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.sponges.bot.dashboard.newdesign.ConfigurationLoader;
import io.sponges.bot.dashboard.newdesign.Database;
import io.sponges.bot.dashboard.newdesign.platform.account.Account;
import io.sponges.bot.dashboard.newdesign.platform.account.AccountFactory;
import io.sponges.bot.dashboard.newdesign.user.User;
import org.json.JSONObject;
import spark.Session;

import java.util.concurrent.TimeUnit;

public class DiscordAccountFactory extends AccountFactory {

    private final Database database;

    private final String clientId;
    private final String clientSecret;

    public DiscordAccountFactory(Database database, ConfigurationLoader.Configuration configuration) {
        super("discord");
        this.database = database;
        JSONObject discordConfig = configuration.getJsonObject().getJSONObject("discord");
        this.clientId = discordConfig.getString("client_id");
        this.clientSecret = discordConfig.getString("client_secret");
    }

    @Override
    protected Account create(User user, String refreshToken) {
        String refreshUrl = String.format(DiscordAccount.REFRESH_TOKEN_URL, clientId,
                clientSecret, refreshToken, DiscordAccount.REDIRECT_URL);
        JSONObject object;
        try {
            object = Unirest.post(refreshUrl).asJson().getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }
        Session session = user.getSession();
        String token = object.getString("access_token");
        String newRefreshToken = object.getString("refresh_token");
        int expiryTime = object.getInt("expires_in");
        int startTime = (int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        database.updateUserPlatformValue(session.attribute("id"), "discord", newRefreshToken);
        return new DiscordAccountImpl(database, session, clientId, clientSecret, token, newRefreshToken, expiryTime,
                startTime);
    }
}
