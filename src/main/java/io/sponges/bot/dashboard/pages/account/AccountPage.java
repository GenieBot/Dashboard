package io.sponges.bot.dashboard.pages.account;

import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

public class AccountPage extends Page {

    public AccountPage() {
        super("/account", Method.GET, true, true);
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        Session session = request.session();
        int userId = session.attribute("id");
        String email = session.attribute("email");
        builder.with("user_id", userId);
        builder.with("user_email", email);
        return new ModelAndView(builder.build(), "account.ftl");
    }
}
