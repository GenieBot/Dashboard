package io.sponges.bot.dashboard.pages.account;

import io.sponges.bot.dashboard.Database;
import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import spark.Request;
import spark.Response;
import spark.Session;

public class ChangePasswordPage extends Page {

    private final Database database;

    public ChangePasswordPage(Database database) {
        super("/change_password", Method.POST, false, true);
        this.database = database;
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        Session session = request.session();
        int userId = session.attribute("id");
        String currentPassword = request.queryParams("current");
        String storedPassword = database.getUserValueSync(userId, "password");
        String storedSalt = database.getUserValueSync(userId, "salt");
        String hashed = database.hash(storedSalt + currentPassword);
        if (!hashed.equals(storedPassword)) {
            session.attribute("alert", "Current password is incorrect.");
            response.redirect("/account");
            return null;
        }
        String newPassword = request.queryParams("new");
        String confirmPassword = request.queryParams("confirm");
        if (!newPassword.equals(confirmPassword)) {
            session.attribute("alert", "New password and new password confirmation do not match!");
            response.redirect("/account");
            return null;
        }
        String newHash = database.hash(storedSalt + newPassword);
        database.updateUserValue(userId, "password", newHash, () -> System.out.println("ok done"));
        session.attribute("alert", "Updated your password!");
        response.redirect("/account");
        return null;
    }
}
