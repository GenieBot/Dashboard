package io.sponges.bot.dashboard.newdesign.pages.store;

import io.sponges.bot.dashboard.newdesign.Model;
import io.sponges.bot.dashboard.newdesign.Page;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class StoreIndexPage extends Page {

    public StoreIndexPage() {
        super("/store", Method.GET, true, false);
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        return new ModelAndView(builder.build(), "new/store/store_index.ftl");
    }
}
