package io.sponges.bot.dashboard.entity.discord;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.sponges.bot.dashboard.Database;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Session;

import java.util.concurrent.TimeUnit;

public class DiscordAccountImpl implements DiscordAccount {

    private final Database database;
    private final Session session;
    private final String clientId;
    private final String clientSecret;
    private String token;
    private String refreshToken;
    private int expiryTime;
    private int startTime;

    public DiscordAccountImpl(Database database, Session session, String clientId, String clientSecret, String token, String refreshToken, int expiryTime, int startTime) {
        this.database = database;
        this.session = session;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.token = token;
        this.refreshToken = refreshToken;
        this.expiryTime = expiryTime;
        this.startTime = startTime;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public int getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(int expiryTime) {
        this.expiryTime = expiryTime;
    }

    @Override
    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    @Override
    public DiscordGuild[] getGuilds() throws UnirestException {
        if (hasTokenExpired()) refreshToken();
        JSONArray array = appendAuthorizationHeader(Unirest.get(LIST_GUILDS_URL))
                .asJson().getBody().getArray();
        DiscordGuild[] guilds = new DiscordGuild[array.length()];
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonGuild = array.getJSONObject(i);
            String id = jsonGuild.getString("id");
            String name = jsonGuild.getString("name");
            String icon = jsonGuild.getString("icon");
            boolean owner = jsonGuild.getBoolean("owner");
            long permissions = jsonGuild.getLong("permissions");
            DiscordGuild guild = new DiscordGuild(id, name, icon, owner, permissions);
            guilds[i] = guild;
        }
        return guilds;
    }

    @Override
    public DiscordUser getUser() throws UnirestException {
        if (hasTokenExpired()) refreshToken();
        JSONObject object = appendAuthorizationHeader(Unirest.get(USER_INFO_URL))
                .asJson().getBody().getObject();
        String id = object.getString("id");
        String username = object.getString("username");
        String discriminator = object.getString("discriminator");
        String avatar = object.getString("avatar");
        boolean mfaEnabled = object.getBoolean("mfa_enabled");
        return new DiscordUser(id, username, discriminator, avatar, mfaEnabled);
    }

    @Override
    public void refreshToken() {
        String refreshUrl = String.format(REFRESH_TOKEN_URL, clientId,
                clientSecret, refreshToken, REDIRECT_URL);
        JSONObject object;
        try {
            object = Unirest.post(refreshUrl).asJson().getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
            return;
        }
        this.token = object.getString("access_token");
        this.refreshToken = object.getString("refresh_token");
        this.expiryTime = object.getInt("expires_in");
        this.startTime = (int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        database.updateUserPlatformValue(session.attribute("id"), "discord", refreshToken);
    }
}
