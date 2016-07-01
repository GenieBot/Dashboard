package io.sponges.bot.dashboard.pages.platform;

import io.sponges.bot.dashboard.Database;
import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.HashMap;
import java.util.Map;

public class PlatformsPage extends Page {

    private final Database database;

    public PlatformsPage(Database database) {
        super("/platforms", Method.GET, true, true); // TODO enable auth again
        this.database = database;
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        Session session = request.session();
        int id = session.attribute("id");
        if (database.isUserPlatformSync(id)) {
            Map<String, Object> mappings = new HashMap<>();
            if (database.hasUserPlatformColumnSync(id, "skype")) {
                String skype = database.getUserPlatformValueSync(id, "skype");
                if (skype != null) mappings.put("Skype", "skype");
            }
            if (database.hasUserPlatformColumnSync(id, "discord")) {
                String discord = database.getUserPlatformValueSync(id, "discord");
                if (discord != null) mappings.put("Discord", "discord");
            }
            builder.with("platforms", mappings);
        }
        return new ModelAndView(builder.build(), "platform/platforms.ftl");
    }
}
