package io.sponges.bot.dashboard.pages;

import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class DonatePage extends Page {

    public DonatePage() {
        super("/donate", Method.GET, true, false);
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        return new ModelAndView(builder.build(), "donate.ftl");
    }
}
