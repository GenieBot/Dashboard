package io.sponges.bot.dashboard.newdesign.pages.account;

import io.sponges.bot.dashboard.newdesign.Model;
import io.sponges.bot.dashboard.newdesign.Page;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AccountLoginPage extends Page {

    public AccountLoginPage() {
        super("/account/login", Method.GET, true, false);
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        if (isAuthorised(request.session())) {
            request.session().attribute("alert", "Already logged in!");
            response.redirect("/");
            return null;
        }
        return new ModelAndView(builder.build(), "new/account/account_login.ftl");
    }
}
