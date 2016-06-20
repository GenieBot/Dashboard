package io.sponges.bot.dashboard.pages.auth;

import io.sponges.bot.dashboard.Database;
import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import spark.Request;
import spark.Response;
import spark.Session;

public class ConfirmLoginPage extends Page {

    private final Database database;

    public ConfirmLoginPage(Database database) {
        super("/confirm_login", Method.POST, false, false);
        this.database = database;
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        Session session = request.session();
        String email = request.queryParams("email");
        String password = request.queryParams("password");
        boolean isUser = database.isUserSync(email);
        if (!isUser) {
            session.attribute("alert", "Invalid username or password.");
            response.redirect("/login");
            return null;
        }
        int userId = database.getUserIdSync(email);
        String storedPassword = database.getUserValueSync(userId, "password");
        String storedSalt = database.getUserValueSync(userId, "salt");
        String hashed = database.hash(storedSalt + password);
        if (hashed.equals(storedPassword)) {
            request.session().attribute("alert", "Logged in!");
            request.session().attribute("email", email);
            String token = database.hash(email + password);
            SESSIONS.put(email, token);
            request.session().attribute("token", token);
            response.redirect("/");
            return null;
        }
        request.session().attribute("alert", "Not logged in!");
        response.redirect("/login");
        return null;
    }
}
