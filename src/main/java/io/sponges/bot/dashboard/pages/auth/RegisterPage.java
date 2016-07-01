package io.sponges.bot.dashboard.pages.auth;

import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RegisterPage extends Page {

    public RegisterPage() {
        super("/register", Method.GET, true, false);
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        if (isAuthorised(request.session())) {
            request.session().attribute("alert", "Already logged in!");
            response.redirect("/");
            return null;
        }
        return new ModelAndView(builder.build(), "auth/register.ftl");
    }
}
