package io.sponges.bot.dashboard.pages.platform;

import io.sponges.bot.dashboard.Database;
import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import spark.Request;
import spark.Response;
import spark.Session;

public class ConfirmAddPlatformPage extends Page {

    private final Database database;

    public ConfirmAddPlatformPage(Database database) {
        super("/confirm_add_platform", Method.POST, false, true);
        this.database = database;
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        Session session = request.session();
        int id = session.attribute("id");
        String platform = request.queryParams("platform");
        String userId = request.queryParams("id");
        String secret = request.queryParams("secret");
        String skypeId = null;
        String discordId = null;
        if (platform.equals("skype")) {
            skypeId = userId;
        } else {
            discordId = userId;
        }
        if (!database.isUserPlatformSync(id)) {
            database.createUserPlatformSync(id, skypeId, discordId);
        } else {
            database.updateUserPlatformValue(id, platform, userId);
        }
        session.attribute("alert", "Added new platform \"" + platform + "\"!");
        response.redirect("/platforms");
        return null;
    }
}
