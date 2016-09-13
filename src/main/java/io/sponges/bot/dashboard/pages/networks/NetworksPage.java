package io.sponges.bot.dashboard.pages.networks;

import io.sponges.bot.dashboard.Database;
import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import io.sponges.bot.dashboard.Routes;
import io.sponges.bot.dashboard.entity.Account;
import io.sponges.bot.dashboard.entity.ConfigurationLoader;
import io.sponges.bot.dashboard.entity.discord.DiscordAccount;
import io.sponges.bot.dashboard.entity.discord.DiscordAccountImpl;
import io.sponges.bot.dashboard.network.Network;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.List;

public class NetworksPage extends Page {

    private final Database database;
    private final Routes.AccountManager accountManager;
    private final ConfigurationLoader.Configuration configuration;

    public NetworksPage(Database database, Routes.AccountManager accountManager, ConfigurationLoader.Configuration configuration) {
        super("/networks", Method.GET, true, true);
        this.database = database;
        this.accountManager = accountManager;
        this.configuration = configuration;
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        Session session = request.session();
        int userId = session.attribute("id");
        if (!request.queryParams().contains("platform")) {
            session.attribute("alert", "Invalid platform! Please press the \"Manage networks\" button on the " +
                    "desired network.");
            response.redirect("/platforms");
            return null;
        }
        String platform = request.queryParams("platform");
        if (!database.isUserPlatformSync(userId)) {
            session.attribute("alert", "You do not have any platforms! Please add one.");
            response.redirect("/platforms");
            return null;
        }
        builder.with("platform_id", platform);
        String platformUser = database.getUserPlatformValueSync(userId, platform);
        builder.with("platform_user", platformUser);
        Account account = null;
        List<Account> accounts = accountManager.getAccounts(session.id());
        if (accounts != null && accounts.size() > 0) {
            for (Account account1 : accounts) {
                if (account1.getPlatform().equalsIgnoreCase(platform)) {
                    account = account1;
                    break;
                }
            }
        }
        if (account == null) {
            JSONObject discordObject = configuration.getJsonObject().getJSONObject("discord");
            String clientId = discordObject.getString("client_id");
            String clientSecret = discordObject.getString("client_secret");
            DiscordAccount discordAccount = new DiscordAccountImpl(database, session, clientId, clientSecret, "invalid-token", platformUser, 0, 0);
            discordAccount.refreshToken();
            accountManager.add(discordAccount);
            return execute(request, response, builder);
        }
        Network[] networks = account.getNetworks();
        JSONArray json = new JSONArray();
        for (Network network : networks) {
            //json.put(network.toJson());
        }
        builder.data("networks", json);
        builder.data("platform", platform);
        return new ModelAndView(builder.build(), "network/networks.ftl");
    }
}
