package io.sponges.bot.dashboard.newdesign.pages.dashboard;

import io.sponges.bot.dashboard.newdesign.platform.account.Account;
import io.sponges.bot.dashboard.newdesign.Model;
import io.sponges.bot.dashboard.newdesign.Page;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class DashboardPlatformsPage extends Page {

    public DashboardPlatformsPage() {
        super("/platforms", Method.GET, true, true);
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        Collection<Account> accounts = getUser().getOrLoadAccounts();
        Map<String, Object> bottomTabs = new LinkedHashMap<>();
        if (!accounts.isEmpty()) {
            for (Account account : accounts) {
                String platform = account.getPlatform();
                String url = "/platforms/" + platform;
                bottomTabs.put(String.valueOf(platform.charAt(0)).toUpperCase() + platform.substring(1), url);
            }
        }
        bottomTabs.put("Manage Platforms", "/platforms/manage");
        builder.with("bottom_tabs", bottomTabs);
        return new ModelAndView(builder.build(), "new/dashboard/dashboard_platforms.ftl");
    }
}
