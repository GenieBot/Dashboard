package io.sponges.bot.dashboard.newdesign.pages.auth;

import io.sponges.bot.dashboard.newdesign.Model;
import io.sponges.bot.dashboard.newdesign.Page;
import io.sponges.bot.dashboard.newdesign.user.UserManager;
import spark.Request;
import spark.Response;
import spark.Session;

public class AuthLogoutPage extends Page {

    private final UserManager userManager;

    public AuthLogoutPage(UserManager userManager) {
        super("/auth/logout", Method.POST, false, true);
        this.userManager = userManager;
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        Session session = request.session();
        int id = session.attribute("id");
        SESSIONS.remove(id);
        userManager.removeUser(session);
        session.removeAttribute("id");
        session.removeAttribute("email");
        session.removeAttribute("token");
        session.attribute("alert", "Logged out!");
        response.redirect("/");
        return null;
    }
}
