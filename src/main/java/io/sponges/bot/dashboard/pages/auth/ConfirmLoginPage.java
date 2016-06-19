package io.sponges.bot.dashboard.pages.auth;

import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import spark.Request;
import spark.Response;

public class ConfirmLoginPage extends Page {

    public ConfirmLoginPage() {
        super("/confirm_login", Method.POST, true);
    }

    @Override
    public Object execute(Request request, Response response, Model.Builder builder) {
        return 200;
    }
}
