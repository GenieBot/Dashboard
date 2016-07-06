package io.sponges.bot.dashboard.pages.auth;

import io.sponges.bot.dashboard.Database;
import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import io.sponges.bot.dashboard.entity.ConfigurationLoader;
import spark.Request;
import spark.Response;
import spark.Session;

public class ConfirmLoginPage extends Page {

    private final Database database;
    private final ConfigurationLoader.Configuration configuration;

    public ConfirmLoginPage(Database database, ConfigurationLoader.Configuration configuration) {
        super("/confirm_login", Method.POST, false, false);
        this.database = database;
        this.configuration = configuration;
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        Session session = request.session();
        String email = request.queryParams("email");
        String password = request.queryParams("password");
        //String recaptchaResponse = request.queryParams("g-recaptcha-response");
        if (email == null || password == null/* || recaptchaResponse == null*/) {
            session.attribute("alert", "Email, password or recaptcha was not provided!");
            response.redirect("/login");
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
        }
        if (!success) {
            session.attribute("alert", "Recaptcha failed!");
            response.redirect("/login");
            return null;
        }*/
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
            session.attribute("id", userId);
            session.attribute("email", email);
            String token = database.hash(email + password);
            SESSIONS.put(userId, token);
            session.attribute("token", token);
            String redirect;
            if (session.attributes().contains("requested-url")) {
                redirect = session.attribute("requested-url");
                session.removeAttribute("requested-url");
            } else {
                redirect = "/";
            }
            response.redirect(redirect);
            return null;
        }
        session.attribute("alert", "Not logged in!");
        response.redirect("/login");
        return null;
    }
}
