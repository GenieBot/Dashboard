package io.sponges.bot.dashboard.pages.auth;

import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RegisterPage extends Page {

    public RegisterPage() {
        super("/register", Method.GET, true);
    }

    @Override
    public Object execute(Request request, Response response, Model.Builder builder) {
        return new ModelAndView(builder.build(), "register.ftl");
    }
}
