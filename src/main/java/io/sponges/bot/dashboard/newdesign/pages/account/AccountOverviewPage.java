package io.sponges.bot.dashboard.newdesign.pages.account;

import io.sponges.bot.dashboard.newdesign.Model;
import io.sponges.bot.dashboard.newdesign.Page;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AccountOverviewPage extends Page {

    public AccountOverviewPage() {
        super("/account", Method.GET, true, true);
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        builder.with("email", request.session().attribute("email"));
        return new ModelAndView(builder.build(), "new/account/account_overview.ftl");
    }
}
