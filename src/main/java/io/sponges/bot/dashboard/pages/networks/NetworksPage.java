package io.sponges.bot.dashboard.pages.networks;

import io.sponges.bot.dashboard.Database;
import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

public class NetworksPage extends Page {

    private final Database database;

    public NetworksPage(Database database) {
        super("/networks", Method.GET, true, true);
        this.database = database;
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
        // TODO api request shit here
        return new ModelAndView(builder.build(), "network/networks.ftl");
    }
}
