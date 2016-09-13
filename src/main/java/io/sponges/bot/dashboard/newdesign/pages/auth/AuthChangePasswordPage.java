package io.sponges.bot.dashboard.newdesign.pages.auth;

import io.sponges.bot.dashboard.newdesign.Database;
import io.sponges.bot.dashboard.newdesign.Model;
import io.sponges.bot.dashboard.newdesign.Page;
import spark.Request;
import spark.Response;
import spark.Session;

public class AuthChangePasswordPage extends Page {

    private final Database database;

    public AuthChangePasswordPage(Database database) {
        super("/auth/password", Page.Method.POST, false, true);
        this.database = database;
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        Session session = request.session();
        int userId = session.attribute("id");

        String currentPassword = request.queryParams("current");
        String newPassword = request.queryParams("new");
        String confirmPassword = request.queryParams("confirm");

        if (currentPassword == null || newPassword == null || confirmPassword == null) {
            request.session().attribute("alert", "Current password, new password or confirm password were not provided!");
            response.redirect("/account/password");
            return null;
        }

        String storedPassword = database.getUserValueSync(userId, "password");
        String storedSalt = database.getUserValueSync(userId, "salt");
        String hashed = database.hash(storedSalt + currentPassword);
        if (!hashed.equals(storedPassword)) {
            session.attribute("alert", "Current password is incorrect.");
            response.redirect("/account/password");
            return null;
        }
        if (!newPassword.equals(confirmPassword)) {
            session.attribute("alert", "New password and new password confirmation do not match!");
            response.redirect("/account/password");
            return null;
        }
        String newHash = database.hash(storedSalt + newPassword);
        database.updateUserValue(userId, "password", newHash, () -> System.out.println("ok done"));
        session.attribute("alert", "Updated your password!");
        response.redirect("/account");
        return null;
    }
}
