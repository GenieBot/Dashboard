package io.sponges.bot.dashboard.pages.auth;

import io.sponges.bot.dashboard.Database;
import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import spark.Request;
import spark.Response;
import spark.Session;

public class ConfirmRegisterPage extends Page {

    private final Database database;

    public ConfirmRegisterPage(Database database) {
        super("/confirm_register", Method.POST, false, false);
        this.database = database;
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        String email = request.queryParams("email");
        String password = request.queryParams("password");
        String confirmPassword = request.queryParams("confirm_password");
        Session session = request.session();
        if (!password.equals(confirmPassword)) {
            session.attribute("alert", "Passwords do not match!");
            response.redirect("/register");
            return null;
        }
        boolean isUser = database.isUserSync(email);
        if (isUser) {
            session.attribute("alert", "That email already exists! Try a different one or login to an existing account.");
            response.redirect("/register");
            return null;
        }
        database.createUser(email, password, new String(database.getNewSalt()));
        session.attribute("alert", "Successfully registered! Proceed to login.");
        response.redirect("/login");
        return null;
    }
}
