package io.sponges.bot.dashboard.newdesign.pages;

import io.sponges.bot.dashboard.newdesign.Model;
import io.sponges.bot.dashboard.newdesign.Page;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndexPage extends Page {

    public IndexPage() {
        super("/", Method.GET, true, false);
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        return new ModelAndView(builder.build(), "new/index.ftl");
    }
}
