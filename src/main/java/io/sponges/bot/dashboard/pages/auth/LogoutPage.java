package io.sponges.bot.dashboard.pages.auth;

import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import spark.Request;
import spark.Response;
import spark.Session;

public class LogoutPage extends Page {

    public LogoutPage() {
        super("/logout", Method.GET, false, true);
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        Session session = request.session();
        int id = session.attribute("id");
        SESSIONS.remove(id);
        session.removeAttribute("id");
        session.removeAttribute("email");
        session.removeAttribute("token");
        session.attribute("alert", "Logged out!");
        response.redirect("/login");
        return null;
    }
}
