package io.sponges.bot.dashboard.pages.networks;

import io.sponges.bot.dashboard.Database;
import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import io.sponges.bot.dashboard.Routes;
import io.sponges.bot.dashboard.entity.Account;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.List;

public class NetworksPage extends Page {

    private final Database database;
    private final Routes.AccountManager accountManager;

    public NetworksPage(Database database, Routes.AccountManager accountManager) {
        super("/networks", Method.GET, true, true);
        this.database = database;
        this.accountManager = accountManager;
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
        System.out.println("Size of accounts: " + accounts.size());
        for (Account account1 : accounts) {
            account = account1;
        }
        if (account == null) {
            session.attribute("alert", "No accounts for that platform?");
            response.redirect("/platforms");
            return null;
        }
        // TODO api request shit here
        //Network[] networks = account.getNetworks();
        builder.with("networks", "kek");
        return new ModelAndView(builder.build(), "network/networks.ftl");
    }
}
