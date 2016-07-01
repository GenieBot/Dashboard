package io.sponges.bot.dashboard.pages.platform;

import io.sponges.bot.dashboard.Database;
import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import spark.Request;
import spark.Response;
import spark.Session;

public class DeletePlatformPage extends Page {

    private final Database database;

    public DeletePlatformPage(Database database) {
        super("/delete_platform", Method.POST, false, true);
        this.database = database;
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        Session session = request.session();
        int id = session.attribute("id");
        if (!request.queryParams().contains("platform")) {
            session.attribute("alert", "Body does not contain a platform?");
        } else {
            String platform = request.queryParams("platform");
            database.updateUserPlatformValue(id, platform, null);
            session.attribute("alert", "Successfully deleted platform \"" + platform + "\"!");
        }
        response.redirect("/platforms");
        return null;
    }
}
