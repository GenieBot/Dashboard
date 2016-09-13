package io.sponges.bot.dashboard.newdesign.pages.account;

import io.sponges.bot.dashboard.newdesign.Model;
import io.sponges.bot.dashboard.newdesign.Page;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AccountChangePasswordPage extends Page {

    public AccountChangePasswordPage() {
        super("/account/password", Method.GET, true, true);
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        return new ModelAndView(builder.build(), "new/account/account_change_password.ftl");
    }
}
