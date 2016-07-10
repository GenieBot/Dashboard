package io.sponges.bot.dashboard.pages.auth;

import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import io.sponges.bot.dashboard.Routes;
import spark.Request;
import spark.Response;
import spark.Session;

public class LogoutPage extends Page {

    private final Routes.AccountManager accountManager;

    public LogoutPage(Routes.AccountManager accountManager) {
        super("/logout", Method.GET, false, true);
        this.accountManager = accountManager;
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        Session session = request.session();
        int id = session.attribute("id");
        SESSIONS.remove(id);
        accountManager.remove(session.id());
        session.removeAttribute("id");
        session.removeAttribute("email");
        session.removeAttribute("token");
        session.attribute("alert", "Logged out!");
        response.redirect("/");
        return null;
    }
}
