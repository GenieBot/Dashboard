package io.sponges.bot.dashboard.newdesign.pages.auth;

import io.sponges.bot.dashboard.newdesign.ConfigurationLoader;
import io.sponges.bot.dashboard.newdesign.Database;
import io.sponges.bot.dashboard.newdesign.Model;
import io.sponges.bot.dashboard.newdesign.Page;
import spark.Request;
import spark.Response;
import spark.Session;

public class AuthRegisterPage extends Page {

    private final Database database;
    private final ConfigurationLoader.Configuration configuration;

    public AuthRegisterPage(Database database, ConfigurationLoader.Configuration configuration) {
        super("/auth/register", Page.Method.POST, false, false);
        this.database = database;
        this.configuration = configuration;
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        Session session = request.session();
        String email = request.queryParams("email");
        String password = request.queryParams("password");
        String confirmPassword = request.queryParams("confirm_password");
        //String recaptchaResponse = request.queryParams("g-recaptcha-response");
        if (email == null || password == null || confirmPassword == null/* || recaptchaResponse == null*/) {
            request.session().attribute("alert", "Email, password, confirm password or recaptcha was not provided!");
            response.redirect("/register");
            return null;
        }
        if (!password.equals(confirmPassword)) {
            request.session().attribute("alert", "Passwords do not match!");
            response.redirect("/register");
            return null;
        }
        /*boolean success;
        try {
            JSONObject object = Unirest.post(Routes.RECAPTCHA_URL)
                    .field("secret", configuration.getJsonObject().getString("recaptcha_secret"))
                    .field("response", recaptchaResponse)
                    .field("remoteip", request.ip())
                    .asJson().getBody().getObject();
            success = object.getBoolean("success");
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }*/
        boolean isUser = database.isUserSync(email);
        if (isUser) {
            request.session().attribute("alert", "That email already exists! Try a different one or login to an existing account.");
            response.redirect("/register");
            return null;
        }
        database.createUser(email, password, new String(database.getNewSalt()));
        request.session().attribute("alert", "Successfully registered! Proceed to login.");
        response.redirect("/login");
        return null;
    }
}
