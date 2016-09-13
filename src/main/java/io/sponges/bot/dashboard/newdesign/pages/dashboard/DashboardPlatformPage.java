package io.sponges.bot.dashboard.newdesign.pages.dashboard;

import io.sponges.bot.dashboard.newdesign.Model;
import io.sponges.bot.dashboard.newdesign.Page;
import io.sponges.bot.dashboard.newdesign.platform.account.Account;
import io.sponges.bot.dashboard.newdesign.platform.network.Network;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class DashboardPlatformPage extends Page {

    public DashboardPlatformPage() {
        super("/platforms/:platform", Method.GET, true, true);
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        String platform = request.params("platform");
        builder.with("platform", platform);
        Collection<Account> accounts = getUser().getOrLoadAccounts();
        Map<String, Object> topTabs = new LinkedHashMap<>();
        if (!accounts.isEmpty()) {
            for (Account account : accounts) {
                String p = account.getPlatform();
                String url = "/platforms/" + p;
                Object value;
                if (p.equalsIgnoreCase(platform)) {
                    value = new Object[]{url, true};
                } else {
                    value = url;
                }
                topTabs.put(String.valueOf(p.charAt(0)).toUpperCase() + p.substring(1), value);
            }
        }
        topTabs.put("Manage Platforms", "/platforms/manage");
        builder.with("top_tabs", topTabs);
        Map<String, Object> bottomTabs = new LinkedHashMap<>();
        Account account = getUser().getAccount(platform);
        Network[] networks = account.getNetworks();
        for (Network network : networks) {
            bottomTabs.put(network.getName(), "/platforms/" + platform + "/networks/" + network.getId());
        }
        bottomTabs.put("Manage Networks", "/platforms/" + platform + "/networks/manage");
        builder.with("bottom_tabs", bottomTabs);
        return new ModelAndView(builder.build(), "new/dashboard/dashboard_platform.ftl");
    }
}
